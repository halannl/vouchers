package com.halan.vouchersmodel.voucher;

import com.halan.vouchersmodel.ofertaespecial.OfertaEspecial;
import com.halan.vouchersmodel.usuario.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document("vouchers")
@EqualsAndHashCode
@ToString
public class Voucher {
    @Id
    private String codigo;
    @NotNull(message = "Usuário precisa ser preenchido")
    @DocumentReference
    private Usuario usuario;
    @NotNull(message = "Oferta especial precisa ser preenchida")
    @DocumentReference
    private OfertaEspecial ofertaEspecial;
    @Future(message = "A validade deve ser uma data futura")
    private Date validade;
    private Date dataUso;
}
