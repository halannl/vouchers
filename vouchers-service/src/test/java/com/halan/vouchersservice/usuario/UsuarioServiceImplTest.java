package com.halan.vouchersservice.usuario;

import com.halan.vouchersrepository.usuario.Usuario;
import com.halan.vouchersrepository.usuario.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void testGetUsuarioByEmailExistente() {
        Usuario usuario = new Usuario("joao@example.com", "João");

        when(usuarioRepository.findById("joao@example.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.get("joao@example.com");

        assertTrue(resultado.isPresent());
        assertEquals("joao@example.com", resultado.get().getEmail());
        assertEquals("João", resultado.get().getNome());

        verify(usuarioRepository, times(1)).findById("joao@example.com");
    }

    @Test
    void testGetUsuarioByEmailInexistente() {
        when(usuarioRepository.findById("maria@example.com")).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioService.get("maria@example.com");

        assertFalse(resultado.isPresent());

        verify(usuarioRepository, times(1)).findById("maria@example.com");
    }

    @Test
    void testSaveUsuarioNovo() {
        Usuario usuario = new Usuario("pedro@example.com", "Pedro");

        when(usuarioRepository.existsById("pedro@example.com")).thenReturn(true);

        boolean resultado = usuarioService.save(usuario);

        assertTrue(resultado);

        verify(usuarioRepository, times(1)).existsById("pedro@example.com");
        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    void testSaveUsuarioExistente() {
        Usuario usuario = new Usuario("ana@example.com", "Ana");

        when(usuarioRepository.existsById("ana@example.com")).thenReturn(false);

        boolean resultado = usuarioService.save(usuario);

        assertFalse(resultado);

        verify(usuarioRepository, times(1)).existsById("ana@example.com");
        verify(usuarioRepository, times(1)).save(usuario);
    }
}
