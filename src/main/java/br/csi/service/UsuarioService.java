package br.csi.service;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;

public class UsuarioService {
    public Usuario autenticar(String email, String senha) {
        // Validações antes de chamar o DAO (ex: campos vazios)
        if (email == null || senha == null) {
            throw new IllegalArgumentException("E-mail/senha inválidos");
        }
        return new UsuarioDAO().autenticar(email, senha);
    }
}