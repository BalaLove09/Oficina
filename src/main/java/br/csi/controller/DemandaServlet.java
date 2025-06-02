package br.csi.controller;
import br.csi.model.Demanda;
import br.csi.model.Usuario;
import br.csi.service.DemandaService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("demanda")
public class DemandaServlet extends HttpServlet {

    private DemandaService demandaService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.demandaService = new DemandaService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        Integer idMecanico = (Integer) session.getAttribute("idMecanico");

        if (usuarioLogado == null || !"MECANICO".equalsIgnoreCase(usuarioLogado.getTipo()) || idMecanico == null || idMecanico == -1) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String acao = req.getParameter("acao");
        String retornoMensagem = "";

        // Se a ação for inserir ele cadastrar nova demanda
        if ("inserir".equals(acao)) {
            String titulo = req.getParameter("titulo");
            String descricao = req.getParameter("descricao");

            Demanda demanda = new Demanda(titulo, descricao, idMecanico);

            retornoMensagem = demandaService.inserir(demanda);
            session.setAttribute("mensagemSucesso", retornoMensagem);

        }
        // Se a ação for alterar ele edita a demanda existente
        else if ("alterar".equals(acao)) {
            int idDemanda = Integer.parseInt(req.getParameter("id"));
            String titulo = req.getParameter("titulo");
            String descricao = req.getParameter("descricao");

            Demanda demanda = new Demanda(idDemanda, titulo, descricao, idMecanico);

            retornoMensagem = demandaService.alterar(demanda);
            session.setAttribute("mensagemSucesso", retornoMensagem);

        }

        resp.sendRedirect(req.getContextPath() + "/demanda?opcao=listar");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        Usuario usuarioLogado = null;
        Integer idMecanico = null;

        if (session != null) {
            usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
            idMecanico = (Integer) session.getAttribute("idMecanico");
        } else {
            System.out.println("Não possui sessão ativa.");
        }


        // verifica se é mecânico e se tá logado.
        // Se não for joga pra tela de login.
        if (usuarioLogado == null || !"MECANICO".equalsIgnoreCase(usuarioLogado.getTipo()) || idMecanico == null || idMecanico == -1) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String opcao = req.getParameter("opcao"); // Pega qual "opção" veio na URL (editar, excluir, novo, listar)
        String info = req.getParameter("info"); // Pega alguma informação extra (tipo ID pra editar/excluir)

        String mensagemSucesso = (String) session.getAttribute("mensagemSucesso");
        String mensagemErro = (String) session.getAttribute("mensagemErro");
        session.removeAttribute("mensagemSucesso");
        session.removeAttribute("mensagemErro");


        if (opcao != null) {
            // Se for editar ele edita uma demanda
            if (opcao.equals("editar")) {
                int idDemanda = Integer.parseInt(info); // Pega o ID da demanda
                Demanda demanda = demandaService.getDemandaById(idDemanda, idMecanico);

                if (demanda != null) {
                    req.setAttribute("demanda", demanda);
                    RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/editarDemanda.jsp");
                    rd.forward(req, resp);
                    return;
                } else {
                    session.setAttribute("mensagemErro", "Demanda não encontrada ou você não tem permissão para editá-la.");
                    resp.sendRedirect(req.getContextPath() + "/demanda?opcao=listar");
                    return;
                }

            }
            else if (opcao.equals("excluir")) {
                int idDemanda = Integer.parseInt(info);
                boolean sucesso = demandaService.excluir(idDemanda, idMecanico);
                if (sucesso) {
                    session.setAttribute("mensagemSucesso", "Demanda excluída com sucesso!");
                } else {
                    session.setAttribute("mensagemErro", "Falha ao excluir a demanda ou você não tem permissão.");
                }
                resp.sendRedirect(req.getContextPath() + "/demanda?opcao=listar"); // Manda de volta pra lista
                return;

            }
            else if (opcao.equals("novo")) {
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/editarDemanda.jsp");
                rd.forward(req, resp);
                return;
            }
        }

        List<Demanda> demandas = demandaService.getDemandasByMecanicoId(idMecanico);
        req.setAttribute("demandas", demandas);
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/MecanicoDashboard.jsp");
        rd.forward(req, resp);
    }
}