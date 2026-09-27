package com.projeto.ms_principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class BackEndTp1Application {

	public static void main(String[] args) {
		SpringApplication.run(BackEndTp1Application.class, args);
	}

}
