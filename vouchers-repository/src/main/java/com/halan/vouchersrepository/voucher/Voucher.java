package com.halan.vouchersrepository.voucher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Voucher {
    private String codigo;
    private String email;
    private Date validade;
    private boolean usado;
}
