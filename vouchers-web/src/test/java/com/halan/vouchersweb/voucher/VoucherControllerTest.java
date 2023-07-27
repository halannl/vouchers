package com.halan.vouchersweb.voucher;

import com.halan.vouchersmodel.voucher.Voucher;
import com.halan.vouchersmodel.voucher.VoucherDTO;
import com.halan.vouchersmodel.voucher.VoucherValidarDTO;
import com.halan.vouchersservice.ofertaespecial.OfertaEspecialService;
import com.halan.vouchersservice.usuario.UsuarioService;
import com.halan.vouchersservice.voucher.VoucherService;
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

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;

@SpringJUnitConfig
@WebMvcTest(VoucherController.class)
class VoucherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoucherService voucherService;

    @MockBean
    private OfertaEspecialService ofertaEspecialService;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetByUsuario() throws Exception {
        Voucher voucher1 = mock(Voucher.class);
        Voucher voucher2 = mock(Voucher.class);
        List<Voucher> vouchers = Arrays.asList(voucher1, voucher2);

        when(voucherService.getValidoByEmailUsuario("usuario1")).thenReturn(vouchers);

        mockMvc.perform(MockMvcRequestBuilders.get("/vouchers/usuario1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testSave() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        VoucherDTO voucherDTO = new VoucherDTO("voucher1", "pedro@email.com", tomorrow);

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        mockMvc.perform(MockMvcRequestBuilders.put("/vouchers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoOfertaEspecial\":\"voucher1\",\"emailUsuario\":\"pedro@email.com\",\"validade\":\""+format.format(tomorrow)+"\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Voucher inserido com sucesso"));

        verify(voucherService, times(1)).save(voucherDTO);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testValidarVoucher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/vouchers/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoVoucher\":\"voucher1\",\"emailUsuario\":\"pedro@email.com\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Voucher validado com sucesso"));

        verify(voucherService, times(1)).validarVoucher("voucher1", "pedro@email.com");
    }

}
