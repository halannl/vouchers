package com.halan.vouchersmodel.voucher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
public class VoucherDTO {
    @Size(min = 6, max = 10, message = "Código deve ter entre 6 e 10 caracteres")
    @Pattern(regexp = "\\s*\\S+\\s*", message = "Código não deve conter espaços")
    private String codigoOfertaEspecial;
    @Email(message = "Email invalido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String emailUsuario;
    @Future(message = "A validade deve ser uma data futura")
    private Date validade;
}
