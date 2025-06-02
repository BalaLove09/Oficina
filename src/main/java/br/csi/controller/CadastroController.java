package br.csi.controller;
import br.csi.model.Usuario;
import br.csi.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cadastro")
public class CadastroController extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioService = new UsuarioService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String tipo = req.getParameter("tipo");

        // Verifica se tem campo vazio. Se tiver, manda de volta pra tela de cadastro com erro.
        if (nome == null || nome.isEmpty() || email == null || email.isEmpty() || senha == null || senha.isEmpty() || tipo == null || tipo.isEmpty()) {
            req.setAttribute("mensagemErro", "Todos os campos são obrigatórios!");
            req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
            return;
        }

        // Cria um objeto Usuario com os dados que a gente pegou.
        Usuario novoUsuario = new Usuario(nome, email, senha, tipo);

        try {
            String retorno = usuarioService.inserir(novoUsuario);
            if (retorno != null && retorno.contains("sucesso")) {
                // Se deu tudo certo, manda uma mensagem de sucesso
                // e joga o cara pra tela de login.
                req.setAttribute("mensagemSucesso", retorno + " Faça login.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                // Se não deu certo, manda a mensagem de erro
                // e joga de volta pra tela de cadastro.
                req.setAttribute("mensagemErro", retorno);
                req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            // Mostra os erros no console
            e.printStackTrace();
            // E manda uma mensagem genérica de erro pro usuário.
            req.setAttribute("mensagemErro", "Ops! Algo deu errado ao cadastrar. Tente de novo mais tarde.");
            req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Direciona pra tela de Cadastro de usuario
        req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
    }
}