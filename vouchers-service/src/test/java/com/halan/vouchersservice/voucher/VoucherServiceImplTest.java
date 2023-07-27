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

import java.util.*;

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
        Voucher voucher = new Voucher("voucher1", new Usuario("usuario1", "Usuário 1"),
                new OfertaEspecial("oferta1", "Oferta Especial 1", (short) 30), Calendar.getInstance().getTime(), null);

        when(voucherRepository.findById("voucher1")).thenReturn(Optional.of(voucher));
        when(usuarioRepository.existsById(any())).thenReturn(true);

        voucherService.validarVoucher("voucher1", "usuario1");

        verify(voucherRepository, times(1)).save(voucher);
    }

    @Test
    void testSave() throws UsuarioException, OfertaEspecialException {
        VoucherDTO voucherDTO = new VoucherDTO("oferta1", "pedro@email.com", getAmanha());

        Usuario usuario = new Usuario("pedro@email.com", "Usuário 1");

        OfertaEspecial ofertaEspecial = new OfertaEspecial("oferta1", "Oferta Especial 1", (short) 30);

        when(usuarioRepository.findById("pedro@email.com")).thenReturn(Optional.of(usuario));
        when(ofertaEspecialRepository.findById("oferta1")).thenReturn(Optional.of(ofertaEspecial));

        voucherService.save(voucherDTO);

        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }

    private Date getAmanha(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }
}
