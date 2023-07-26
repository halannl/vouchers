package com.halan.vouchersrepository.ofertaespecial;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document("ofertasespeciais")
@EqualsAndHashCode
public class OfertaEspecial {
    @Id
    private String codigo;
    private String descricao;
    @Min(value = 0, message = "O desconto mínimo é de 0%")
    @Max(value = 100, message = "O desconto máximo é de 100%")
    private short descontoPercentual;
}
