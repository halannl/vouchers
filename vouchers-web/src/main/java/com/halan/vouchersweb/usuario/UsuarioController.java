package com.halan.vouchersweb.usuario;

import com.halan.vouchersrepository.usuario.Usuario;
import com.halan.vouchersservice.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan(basePackages = {"com.halan.vouchersservice.usuario",
        "com.halan.vouchersweb.config"})
@Tag(name = "API de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/{email}")
    public ResponseEntity<Usuario> get(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.get(email).orElse(null));
    }

    @PutMapping("usuarios")
    public ResponseEntity<String> save(@Valid @RequestBody Usuario usuario) {
        final ResponseEntity<String> toReturn;
        if (!usuarioService.save(usuario)) {
            toReturn = ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso");
        } else {
            toReturn = ResponseEntity.status(HttpStatus.OK).body("Usuario ja existe");
        }
        return toReturn;
    }
}
