package com.halan.vouchersmodel.usuario;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document("usuarios")
@EqualsAndHashCode
@ToString
public class Usuario {
    @Id
    @Email(message = "Email invalido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    private String nome;
}
