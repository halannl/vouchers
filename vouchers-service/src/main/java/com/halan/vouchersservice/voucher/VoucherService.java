package com.halan.vouchersservice.voucher;

import com.halan.vouchersmodel.ofertaespecial.OfertaEspecialException;
import com.halan.vouchersmodel.usuario.UsuarioException;
import com.halan.vouchersmodel.voucher.Voucher;
import com.halan.vouchersmodel.voucher.VoucherDTO;
import com.halan.vouchersmodel.voucher.VoucherException;

import java.util.List;
import java.util.Optional;

public interface VoucherService {

    List<Voucher> getValidoByEmailUsuario(String email);

    void validarVoucher(String codigoVoucher, String emailUsuario) throws UsuarioException, VoucherException;

    void save(VoucherDTO voucher) throws UsuarioException, OfertaEspecialException;
}
