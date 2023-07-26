package com.halan.vouchersweb.ofertaespecial;

import com.halan.vouchersservice.ofertaespecial.OfertaEspecialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan(basePackages = {"com.halan.vouchersservice.ofertaespecial",
        "com.halan.vouchersweb.config"})
@Tag(name = "API de ofertas especiais")
public class OfertaEspecialController {

    @Autowired
    private OfertaEspecialService ofertaEspecialService;
}
