package br.csi.service;
import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;
import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
        System.out.println("DEBUG: UsuarioService inicializado.");
    }

    public Usuario autenticarUsuario(String email, String senha) {
        if (email == null || email.trim().isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("E-mail e senha são obrigatórios!");
        }

        Usuario usuarioAutenticado = usuarioDAO.autenticar(email, senha);

        if (usuarioAutenticado != null && usuarioAutenticado.isAtivo()) {
            return usuarioAutenticado;
        } else {
            return null;
        }
    }


    public int getIdClienteAssociado(int idUsuario) {
        int idCliente = usuarioDAO.getIdClienteByUsuarioId(idUsuario);
        return idCliente;
    }


    public int getIdMecanicoAssociado(int idUsuario) {
        int idMecanico = usuarioDAO.getIdMecanicoByUsuarioId(idUsuario);
        return idMecanico;
    }


    public String inserir(Usuario usuario) {
        return usuarioDAO.inserir(usuario);
    }


    public String alterar(Usuario usuario) {
        return usuarioDAO.alterar(usuario);
    }

    public boolean excluir(int id) {
        return usuarioDAO.excluir(id);
    }


    public List<Usuario> getUsuarios() {
        return usuarioDAO.getUsuarios();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioDAO.getUsuarioById(id);
    }


    public List<Usuario> listarMecanicos() {
        return usuarioDAO.listarMecanicos();
    }
}