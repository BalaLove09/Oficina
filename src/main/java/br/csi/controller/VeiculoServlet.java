package br.csi.controller;

import br.csi.model.Veiculo;
import br.csi.model.Usuario;
import br.csi.service.VeiculoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("veiculo")
public class VeiculoServlet extends HttpServlet {

    private static VeiculoService veiculoService = new VeiculoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        Integer idCliente = (Integer) session.getAttribute("idCliente");

        if (usuarioLogado == null || !"CLIENTE".equalsIgnoreCase(usuarioLogado.getTipo()) || idCliente == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String acao = req.getParameter("acao");
        String retornoMensagem = "";

        if ("inserir".equals(acao)) {
            String marca = req.getParameter("marca");
            String modelo = req.getParameter("modelo");
            String placa = req.getParameter("placa");

            Veiculo veiculo = new Veiculo(idCliente, marca, modelo, placa);

            // Agora o método inserir do VeiculoService retorna String
            retornoMensagem = veiculoService.inserir(veiculo);
            session.setAttribute("mensagemSucesso", retornoMensagem);

        } else if ("alterar".equals(acao)) {
            int idVeiculo = Integer.parseInt(req.getParameter("id"));
            String marca = req.getParameter("marca");
            String modelo = req.getParameter("modelo");
            String placa = req.getParameter("placa");

            Veiculo veiculo = new Veiculo(idVeiculo, idCliente, marca, modelo, placa);

            // Agora o método alterar do VeiculoService retorna String
            retornoMensagem = veiculoService.alterar(veiculo);
            session.setAttribute("mensagemSucesso", retornoMensagem);
        }

        resp.sendRedirect(req.getContextPath() + "/veiculo?opcao=listar");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        Integer idCliente = (Integer) session.getAttribute("idCliente");

        if (usuarioLogado == null || !"CLIENTE".equalsIgnoreCase(usuarioLogado.getTipo()) || idCliente == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if (opcao != null) {
            if (opcao.equals("editar")) {
                int idVeiculo = Integer.parseInt(info);
                Veiculo veiculo = veiculoService.getVeiculoById(idVeiculo, idCliente);

                if (veiculo != null) {
                    req.setAttribute("veiculo", veiculo);
                    RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/editarVeiculo.jsp");
                    rd.forward(req, resp);
                    return;
                } else {
                    session.setAttribute("mensagemErro", "Veículo não encontrado ou você não tem permissão para editá-lo.");
                    resp.sendRedirect(req.getContextPath() + "/veiculo?opcao=listar");
                    return;
                }

            } else if (opcao.equals("excluir")) {
                int idVeiculo = Integer.parseInt(info);
                boolean sucesso = veiculoService.excluir(idVeiculo, idCliente);
                if (sucesso) {
                    session.setAttribute("mensagemSucesso", "Veículo excluído com sucesso!");
                } else {
                    session.setAttribute("mensagemErro", "Falha ao excluir o veículo ou você não tem permissão.");
                }
                resp.sendRedirect(req.getContextPath() + "/veiculo?opcao=listar");
                return;

            } else if (opcao.equals("novo")) {
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/editarVeiculo.jsp");
                rd.forward(req, resp);
                return;
            }
        }

        // Se nenhuma opção específica foi solicitada (ou após uma exclusão/redirecionamento), lista os veículos
        List<Veiculo> veiculos = veiculoService.getVeiculosByClienteId(idCliente); // Alterado para veiculoService.getVeiculosByClienteId
        req.setAttribute("veiculos", veiculos);

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/ClienteDashboard.jsp");
        rd.forward(req, resp);
    }
}