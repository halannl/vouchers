package com.halan.vouchersservice.ofertaespecial;

import com.halan.vouchersmodel.ofertaespecial.OfertaEspecial;
import com.halan.vouchersmodel.ofertaespecial.OfertaEspecialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ComponentScan(basePackages = {"com.halan"})
public class OfertaEspecialServiceImpl implements OfertaEspecialService {

    private static final Logger logger = LoggerFactory.getLogger(OfertaEspecialServiceImpl.class);

    @Autowired
    private OfertaEspecialRepository ofertaEspecialRepository;

    @Override
    public Optional<OfertaEspecial> get(String codigo) {
        if (logger.isInfoEnabled()) {
            logger.info("Busca de oferta especial por codigo: {}", codigo);
        }
        return ofertaEspecialRepository.findById(codigo);
    }

    @Override
    public boolean save(OfertaEspecial ofertaEspecial) {
        if (logger.isInfoEnabled()) {
            logger.info("Inserindo oferta especial: {}", ofertaEspecial);
        }
        final boolean existe = ofertaEspecialRepository.existsById(ofertaEspecial.getCodigo());
        if (!existe) {
            ofertaEspecialRepository.save(ofertaEspecial);
        }
        return existe;
    }
}
