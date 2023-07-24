package com.halan.vouchersrepository.ofertaespecial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OfertaEspecial {
    private String nome;
    private String descricao;
    private int descontoPercentual;
}
