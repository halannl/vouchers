package com.halan.vouchersweb.ofertaespecial;

import com.halan.vouchersrepository.ofertaespecial.OfertaEspecial;
import com.halan.vouchersservice.ofertaespecial.OfertaEspecialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan(basePackages = {"com.halan.vouchersservice.ofertaespecial",
        "com.halan.vouchersweb.config"})
@Tag(name = "API de ofertas especiais")
public class OfertaEspecialController {

    @Autowired
    private OfertaEspecialService ofertaEspecialService;

    @GetMapping("/ofertasespeciais/{codigo}")
    public ResponseEntity<OfertaEspecial> get(@PathVariable String codigo) {
        return ResponseEntity.ok(ofertaEspecialService.get(codigo).orElse(null));
    }

    @PutMapping("ofertasespeciais")
    public ResponseEntity<String> save(@Valid @RequestBody OfertaEspecial ofertaEspecial) {
        final ResponseEntity<String> toReturn;
        if (!ofertaEspecialService.save(ofertaEspecial)) {
            toReturn = ResponseEntity.status(HttpStatus.CREATED).body("Oferta especial criada com sucesso");
        } else {
            toReturn = ResponseEntity.status(HttpStatus.OK).body("Oferta especial ja existe");
        }
        return toReturn;
    }
}
