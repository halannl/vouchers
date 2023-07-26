package com.halan.vouchersservice.ofertaespecial;

import com.halan.vouchersrepository.ofertaespecial.OfertaEspecial;
import com.halan.vouchersrepository.ofertaespecial.OfertaEspecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ComponentScan(basePackages = {"com.halan.vouchersrepository.ofertaespecial"})
public class OfertaEspecialServiceImpl implements OfertaEspecialService {

    @Autowired
    private OfertaEspecialRepository ofertaEspecialRepository;

    @Override
    public Optional<OfertaEspecial> get(String codigo) {
        return ofertaEspecialRepository.findById(codigo);
    }

    @Override
    public boolean save(OfertaEspecial ofertaEspecial) {
        return false;
    }
}
