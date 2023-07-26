package com.halan.vouchersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.halan"})
public class VouchersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VouchersServiceApplication.class, args);
	}

}
