package com.halan.vouchersrepository.ofertaespecial;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document("ofertasespeciais")
@EqualsAndHashCode
public class OfertaEspecial {
    private String nome;
    private String descricao;
    private int descontoPercentual;
}
