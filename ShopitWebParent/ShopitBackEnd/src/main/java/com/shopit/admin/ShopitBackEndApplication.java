package com.shopit.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.shopit.common.entity")
@EnableJpaRepositories(basePackages = "com.shopit.admin.user")
public class ShopitBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopitBackEndApplication.class, args);
	}

}
