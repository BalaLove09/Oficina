package br.csi.controller;

import br.csi.model.Usuario;
import br.csi.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioService = new UsuarioService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String senha = request.getParameter("senha");

        if (email.isEmpty() || senha.isEmpty()) {
            request.setAttribute("erro", "E-mail e senha são obrigatórios!");
            request.getRequestDispatcher("login.jsp").forward(request, response); 
            return;
        }

        try {
            Usuario usuario = usuarioService.autenticarUsuario(email, senha);

            if (usuario != null && usuario.isAtivo()) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogado", usuario);

                if ("CLIENTE".equalsIgnoreCase(usuario.getTipo())) {
                    int idCliente = usuarioService.getIdClienteAssociado(usuario.getIdUsuario());
                    if (idCliente != -1) {
                        session.setAttribute("idCliente", idCliente);
                        response.sendRedirect(request.getContextPath() + "/veiculo?opcao=listar");
                    } else {
                        request.setAttribute("erro", "Erro ao carregar dados do cliente. Verifique o cadastro.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else if ("MECANICO".equalsIgnoreCase(usuario.getTipo())) {
                    int idMecanico = usuarioService.getIdMecanicoAssociado(usuario.getIdUsuario());
                    if (idMecanico != -1) {
                        session.setAttribute("idMecanico", idMecanico);
                        response.sendRedirect(request.getContextPath() + "/demanda?opcao=listar");
                    } else {
                        request.setAttribute("erro", "Erro ao carregar dados do mecânico. Verifique o cadastro.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("erro", "Tipo de usuário inválido.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("erro", "Credenciais inválidas!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro interno. Tente novamente mais tarde.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect("login.jsp");
    }
}
