package com.halan.vouchersservice.voucher;

import com.halan.vouchersmodel.ofertaespecial.OfertaEspecial;
import com.halan.vouchersmodel.ofertaespecial.OfertaEspecialException;
import com.halan.vouchersmodel.ofertaespecial.OfertaEspecialRepository;
import com.halan.vouchersmodel.usuario.Usuario;
import com.halan.vouchersmodel.usuario.UsuarioException;
import com.halan.vouchersmodel.usuario.UsuarioRepository;
import com.halan.vouchersmodel.voucher.Voucher;
import com.halan.vouchersmodel.voucher.VoucherDTO;
import com.halan.vouchersmodel.voucher.VoucherException;
import com.halan.vouchersmodel.voucher.VoucherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {

    @InjectMocks
    private VoucherServiceImpl voucherService;

    @Mock
    private VoucherRepository voucherRepository;

    @Mock
    private OfertaEspecialRepository ofertaEspecialRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void testGetValidoByEmailUsuario() {
        Usuario usuario = new Usuario("usuario1", "Usuário 1");

        OfertaEspecial ofertaEspecial = new OfertaEspecial("oferta1", "Oferta Especial 1", (short) 30);

        List<Voucher> vouchers = new ArrayList<>();
        vouchers.add(new Voucher("voucher1", usuario, ofertaEspecial, getAmanha(), null));
        vouchers.add(new Voucher("voucher2", usuario, ofertaEspecial, getAmanha(), null));

        when(voucherRepository.findByUsuarioEmail("usuario1")).thenReturn(vouchers);

        List<Voucher> resultado = voucherService.getValidoByEmailUsuario("usuario1");
        assertEquals(2, resultado.size());
    }

    @Test
    void testValidarVoucher() throws UsuarioException, VoucherException {
        // Criar um voucher fictício para o teste.
        Voucher voucher = new Voucher("voucher1", new Usuario("usuario1", "Usuário 1"),
                new OfertaEspecial("oferta1", "Oferta Especial 1", (short) 30), Calendar.getInstance().getTime(), null);

        // Configurar o comportamento do mock voucherRepository quando chamado.
        when(voucherRepository.findById("voucher1")).thenReturn(Optional.of(voucher));
        when(usuarioRepository.existsById(any())).thenReturn(true);

        // Executar o método validarVoucher e verificar o resultado.
        voucherService.validarVoucher("voucher1", "usuario1");

        // Verificar se o método save do voucherRepository foi chamado para salvar o voucher atualizado.
        verify(voucherRepository, times(1)).save(voucher);
    }

    @Test
    void testSave() throws UsuarioException, OfertaEspecialException {
        // Criar um VoucherDTO fictício para o teste.
        VoucherDTO voucherDTO = new VoucherDTO("oferta1", "pedro@email.com", getAmanha());

        // Criar um usuário fictício para o teste.
        Usuario usuario = new Usuario("pedro@email.com", "Usuário 1");

        // Criar uma oferta especial fictícia para o teste.
        OfertaEspecial ofertaEspecial = new OfertaEspecial("oferta1", "Oferta Especial 1", (short) 30);

        // Configurar o comportamento do mock usuarioRepository e ofertaEspecialRepository quando chamados.
        when(usuarioRepository.findById("pedro@email.com")).thenReturn(Optional.of(usuario));
        when(ofertaEspecialRepository.findById("oferta1")).thenReturn(Optional.of(ofertaEspecial));

        // Executar o método save e verificar o resultado.
        voucherService.save(voucherDTO);

        // Verificar se o método save do voucherRepository foi chamado para salvar o novo voucher.
        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }

    private Date getAmanha(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }
}
