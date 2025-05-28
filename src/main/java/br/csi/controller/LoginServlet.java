package br.csi.controller;

import br.csi.dao.*;
import br.csi.model.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String senha = request.getParameter("senha");

        if (email.isEmpty() || senha.isEmpty()) {
            request.setAttribute("erro", "E-mail e senha são obrigatórios!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        try {
            Usuario usuario = new UsuarioDAO().autenticar(email, senha);

            if (usuario != null && usuario.isAtivo()) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);

                if ("MECANICO".equals(usuario.getTipo())) {
                    List<OrdemServico> ordens = new OrdemServicoDAO().listarOrdensPorMecanico(usuario.getId());
                    request.setAttribute("ordens", ordens);
                    request.getRequestDispatcher("/WEB-INF/pages/MecanicoDashboard.jsp").forward(request, response);
                } else {
                    List<Veiculo> veiculos = new VeiculoDAO().listarVeiculosPorCliente(usuario.getId());
                    List<OrdemServico> ordensCliente = new OrdemServicoDAO().listarOrdensPorCliente(usuario.getId());
                    request.setAttribute("veiculos", veiculos);
                    request.setAttribute("ordensCliente", ordensCliente);
                    request.getRequestDispatcher("/WEB-INF/pages/ClienteDashboard.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("erro", "Credenciais inválidas ou conta inativa!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro interno. Tente novamente mais tarde.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}