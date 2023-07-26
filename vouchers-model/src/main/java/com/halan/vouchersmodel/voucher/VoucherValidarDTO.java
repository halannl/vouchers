package com.halan.vouchersmodel.voucher;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class VoucherValidarDTO {
    private String codigoVoucher;
    @Email(message = "Email invalido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String emailUsuario;
}
