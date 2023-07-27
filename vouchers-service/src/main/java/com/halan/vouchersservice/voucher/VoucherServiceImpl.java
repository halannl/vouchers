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
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"com.halan"})
public class VoucherServiceImpl implements VoucherService {

    private static final Logger logger = LoggerFactory.getLogger(VoucherServiceImpl.class);

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private OfertaEspecialRepository ofertaEspecialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Voucher> getValidoByEmailUsuario(String email) {
        if (logger.isInfoEnabled()) {
            logger.info("Busca de voucher valido por email de usuario: {}", email);
        }
        return voucherRepository.findByUsuarioEmail(email)
                .stream()
                .filter(voucher -> voucher.getValidade().after(Calendar.getInstance().getTime()))
                .filter(voucher -> voucher.getDataUso() == null)
                .toList();
    }

    @Override
    public void validarVoucher(String codigoVoucher, String emailUsuario) throws UsuarioException, VoucherException {
        if (logger.isInfoEnabled()) {
            logger.info("Validando voucher de codigo: {}, do usuario de email: {}", codigoVoucher, emailUsuario);
        }
        if (!usuarioRepository.existsById(emailUsuario)) {
            throw new UsuarioException("Usuário não encontrado");
        }

        Voucher voucher = voucherRepository.findById(codigoVoucher)
                .orElseThrow(() -> new VoucherException("Voucher não encontrado"));

        if (voucher.getDataUso() != null) {
            throw new VoucherException("Voucher já utilizado");
        }

        voucher.setDataUso(new Date());
        voucherRepository.save(voucher);
    }

    @Override
    public void save(VoucherDTO voucherDTO) throws UsuarioException, OfertaEspecialException {
        Optional<Usuario> usuario = usuarioRepository.findById(voucherDTO.getEmailUsuario());
        if (usuario.isEmpty()) {
            throw new UsuarioException("Usuário não encontrado");
        }
        Optional<OfertaEspecial> ofertaEspecial = ofertaEspecialRepository.findById(voucherDTO.getCodigoOfertaEspecial());
        if (ofertaEspecial.isEmpty()) {
            throw new OfertaEspecialException("Oferta especial não encontrada");
        }

        Voucher voucher = new Voucher(gerarCodigoUnico(), usuario.get(),
                ofertaEspecial.get(), voucherDTO.getValidade(), null);
        if (logger.isInfoEnabled()) {
            logger.info("Salvando voucher: {}", voucher);
        }
        voucherRepository.save(voucher);
    }

    private String gerarCodigoUnico() {
        String codigo = null;
        while (codigo == null || voucherRepository.existsById(codigo)) {
            codigo = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
        }
        if (logger.isInfoEnabled()) {
            logger.info("Codigo de Voucher unico gerado: {}", codigo);
        }
        return codigo;
    }
}
