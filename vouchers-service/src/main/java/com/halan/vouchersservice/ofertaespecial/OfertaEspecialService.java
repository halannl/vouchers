package com.halan.vouchersservice.ofertaespecial;

import com.halan.vouchersrepository.ofertaespecial.OfertaEspecial;

import java.util.Optional;

public interface OfertaEspecialService {

    Optional<OfertaEspecial> get(String codigo);

    /**
     * Insere o OfertaEspecial caso não exista com cadastro de codigo informado
     * @param ofertaEspecial OfertaEspecial a ser inserido
     * @return boolean true se inserido, false se já existente (não inserido)
     */
    boolean save(OfertaEspecial ofertaEspecial);
}
