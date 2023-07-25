package com.halan.vouchersservice.usuario;

import com.halan.vouchersrepository.usuario.Usuario;
import com.halan.vouchersrepository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ComponentScan(basePackages = {"com.halan"})
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> get(String email) {
        return usuarioRepository.findById(email);
    }

    @Override
    public boolean save(Usuario usuario) {
        final boolean existe = usuarioRepository.existsById(usuario.getEmail());
        if (!existe) {
            usuarioRepository.save(usuario);
        }
        return existe;
    }
}
