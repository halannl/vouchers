package com.halan.vouchersrepository.voucher;

import com.halan.vouchersrepository.ofertaespecial.OfertaEspecial;
import com.halan.vouchersrepository.usuario.Usuario;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document("vouchers")
@EqualsAndHashCode
public class Voucher {
    @Id
    private String codigo;
    @DocumentReference
    private Usuario usuario;
    @DocumentReference
    private OfertaEspecial ofertaEspecial;
    @Future(message = "A validade deve ser uma data futura")
    private Date validade;
    private boolean usado;
}
