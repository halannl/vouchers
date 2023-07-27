package com.halan.vouchersweb.ofertaespecial;

import com.halan.vouchersmodel.ofertaespecial.OfertaEspecial;
import com.halan.vouchersservice.ofertaespecial.OfertaEspecialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan(basePackages = {"com.halan"})
@Tag(name = "API de ofertas especiais")
public class OfertaEspecialController {

    private static final Logger logger = LoggerFactory.getLogger(OfertaEspecialController.class);

    @Autowired
    private OfertaEspecialService ofertaEspecialService;

    @GetMapping("/ofertasespeciais/{codigo}")
    public ResponseEntity<OfertaEspecial> get(@PathVariable String codigo) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Iniciando busca de oferta especial por codigo: %s", codigo));
        }
        return ofertaEspecialService.get(codigo)
                .map(ofertaEspecial -> ResponseEntity.status(HttpStatus.OK).body(ofertaEspecial))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    @PutMapping("ofertasespeciais")
    public ResponseEntity<String> save(@Valid @RequestBody OfertaEspecial ofertaEspecial) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Iniciando inserção de oferta especial: %s", ofertaEspecial));
        }
        final boolean ofertaEspecialSalva = ofertaEspecialService.save(ofertaEspecial);
        return ResponseEntity
                .status(ofertaEspecialSalva ? HttpStatus.CONFLICT : HttpStatus.CREATED)
                .body(ofertaEspecialSalva ? "Oferta especial ja existe" : "Oferta especial criada com sucesso");
    }
}
