package com.halan.vouchersservice.usuario;

import com.halan.vouchersmodel.usuario.Usuario;
import com.halan.vouchersmodel.usuario.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ComponentScan(basePackages = {"com.halan"})
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> get(String email) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Busca de usuario por email: %s", email));
        }
        return usuarioRepository.findById(email);
    }

    @Override
    public boolean save(Usuario usuario) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Inserindo usuario: %s", usuario));
        }
        final boolean existe = usuarioRepository.existsById(usuario.getEmail());
        if (!existe) {
            usuarioRepository.save(usuario);
        }
        return existe;
    }
}
