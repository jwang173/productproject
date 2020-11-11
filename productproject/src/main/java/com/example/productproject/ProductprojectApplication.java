package com.example.productproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication()

//@EnableJpaRepositories("com.example.productproject.dao")
//@EntityScan("com.example.productproject.entity")
public class ProductprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductprojectApplication.class, args);
	}

}
