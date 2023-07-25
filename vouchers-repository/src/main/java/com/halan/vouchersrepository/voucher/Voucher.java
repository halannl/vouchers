package com.halan.vouchersrepository.voucher;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document("vouchers")
@EqualsAndHashCode
public class Voucher {
    @Id
    private String codigo;
    private String email;
    private Date validade;
    private boolean usado;
}
