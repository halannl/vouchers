package com.halan.vouchersservice.usuario;

import com.halan.vouchersrepository.usuario.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> get(String email);

    /**
     * Insere o Usuario caso não exista com cadastro com e-mail informado
     * @param usuario
     * @return boolean true se inserido, false se já existente (não inserido)
     */
    boolean save(Usuario usuario);
}
