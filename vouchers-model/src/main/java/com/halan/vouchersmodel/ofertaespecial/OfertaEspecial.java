package com.halan.vouchersmodel.ofertaespecial;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document("ofertasespeciais")
@EqualsAndHashCode
@ToString
public class OfertaEspecial {
    @Id
    @Size(min = 6, max = 10, message = "Código deve ter entre 6 e 10 caracteres")
    @Pattern(regexp = "\\s*\\S+\\s*", message = "Código não deve conter espaços")
    private String codigo;
    private String descricao;
    @Min(value = 0, message = "O desconto mínimo é de 0%")
    @Max(value = 100, message = "O desconto máximo é de 100%")
    private short descontoPercentual;
}
