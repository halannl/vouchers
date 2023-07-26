package com.halan.vouchersweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.halan"})
public class VouchersWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(VouchersWebApplication.class, args);
	}
}
