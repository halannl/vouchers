package com.halan.vouchersweb.ofertaespecial;

import com.halan.vouchersrepository.ofertaespecial.OfertaEspecial;
import com.halan.vouchersservice.ofertaespecial.OfertaEspecialService;
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
@WebMvcTest(OfertaEspecialController.class)
class OfertaEspecialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private OfertaEspecialService ofertaEspecialService;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetOferta() throws Exception {
        OfertaEspecial ofertaEspecial = new OfertaEspecial("codigo1", "descricao 1", (short) 10);

        when(ofertaEspecialService.get("codigo1")).thenReturn(Optional.of(ofertaEspecial));

        mockMvc.perform(MockMvcRequestBuilders.get("/ofertasespeciais/codigo1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.codigo").value("codigo1"))
                .andExpect(jsonPath("$.descricao").value("descricao 1"))
                .andExpect(jsonPath("$.descontoPercentual").value(10));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetOfertaNotFound() throws Exception {
        when(ofertaEspecialService.get("codigo2")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/ofertasespeciais/codigo2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testSaveOfertaNova() throws Exception {
        OfertaEspecial ofertaEspecial = new OfertaEspecial("codigo3", "descricao 3", (short) 30);

        when(ofertaEspecialService.save(ofertaEspecial)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.put("/ofertasespeciais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\":\"codigo3\",\"descricao\":\"descricao 3\",\"descontoPercentual\":\"30\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Oferta especial criada com sucesso"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testSaveOfertaExistente() throws Exception {
        OfertaEspecial ofertaEspecial = new OfertaEspecial("codigo4", "descricao 4", (short) 40);

        when(ofertaEspecialService.save(ofertaEspecial)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/ofertasespeciais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\":\"codigo4\",\"descricao\":\"descricao 4\",\"descontoPercentual\":\"40\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Oferta especial ja existe"));
    }
}
