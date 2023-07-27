package com.halan.vouchersweb.voucher;

import com.halan.vouchersmodel.ofertaespecial.OfertaEspecialException;
import com.halan.vouchersmodel.usuario.UsuarioException;
import com.halan.vouchersmodel.voucher.Voucher;
import com.halan.vouchersmodel.voucher.VoucherDTO;
import com.halan.vouchersmodel.voucher.VoucherException;
import com.halan.vouchersmodel.voucher.VoucherValidarDTO;
import com.halan.vouchersservice.voucher.VoucherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ComponentScan(basePackages = {"com.halan"})
@Tag(name = "API de vouchers")
public class VoucherController {

    private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);

    @Autowired
    private VoucherService voucherService;

    @Operation(summary = "get by email usuario",
            description = "Obtem uma lista de vouchers para usuario com email fornecido")
    @GetMapping("/vouchers/{email}")
    ResponseEntity<List<Voucher>> getByUsuario(@PathVariable String email) {
        if (logger.isInfoEnabled()) {
            logger.info("Iniciando busca de usuario por email: {}", email);
        }
        return ResponseEntity.of(Optional.ofNullable(voucherService.getValidoByEmailUsuario(email)));
    }

    @Operation(summary = "save", description = "Cria um objeto Voucher com validade fornecida" +
            " para email usuario e codigo oferta validos fornecidos")
    @PutMapping("/vouchers")
    ResponseEntity<String> save(@Valid @RequestBody VoucherDTO voucherDTO) {
        if (logger.isInfoEnabled()) {
            logger.info("Iniciando inserção de voucher por dto: {}", voucherDTO);
        }
        ResponseEntity<String> toReturn;
        try {
            voucherService.save(voucherDTO);
            toReturn = ResponseEntity.status(HttpStatus.CREATED).body("Voucher inserido com sucesso");
        } catch (UsuarioException | OfertaEspecialException e) {
            toReturn = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return toReturn;
    }

    @Operation(summary = "validar voucher",
            description = "Valida (ou usa) o voucher com codigo do voucher e email usuario validos fornecidos")
    @PostMapping("/vouchers/validar")
    ResponseEntity<String> validarVoucher(@Valid @RequestBody VoucherValidarDTO voucherValidarDTO) {
        if (logger.isInfoEnabled()) {
            logger.info("Iniciando validacao de voucher por dto: {}", voucherValidarDTO);
        }
        ResponseEntity<String> toReturn;
        try {
            voucherService.validarVoucher(voucherValidarDTO.getCodigoVoucher(), voucherValidarDTO.getEmailUsuario());
            toReturn = ResponseEntity.status(HttpStatus.OK).body("Voucher validado com sucesso");
        } catch (UsuarioException | VoucherException e) {
            toReturn = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return toReturn;
    }
}
