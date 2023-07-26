package com.halan.vouchersweb.usuario;

import com.halan.vouchersrepository.usuario.Usuario;
import com.halan.vouchersservice.usuario.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringJUnitConfig
@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    @WithMockUser(username="admin",roles="ADMIN")
    void testGetUsuario() throws Exception {
        Usuario usuario = new Usuario("joao@example.com", "João");

        when(usuarioService.get("joao@example.com")).thenReturn(Optional.of(usuario));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/joao@example.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.email").value("joao@example.com"))
                .andExpect(jsonPath("$.nome").value("João"));
    }

    @Test
    @WithMockUser(username="admin",roles="ADMIN")
    void testGetUsuarioNotFound() throws Exception {
        when(usuarioService.get("maria@example.com")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/maria@example.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @WithMockUser(username="admin",roles="ADMIN")
    void testSaveUsuarioNovo() throws Exception {
        Usuario usuario = new Usuario("pedro@example.com", "Pedro");

        when(usuarioService.save(usuario)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"pedro@example.com\",\"nome\":\"Pedro\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Usuário criado com sucesso"));
    }

    @Test
    @WithMockUser(username="admin",roles="ADMIN")
    void testSaveUsuarioExistente() throws Exception {
        Usuario usuario = new Usuario("ana@example.com", "Ana");

        when(usuarioService.save(usuario)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"ana@example.com\",\"nome\":\"Ana\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Usuário já existe"));
    }
}
