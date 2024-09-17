package com.example.OwnerMicroservice;

import com.example.OwnerMicroservice.Migrations.Init;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OwnerMicroservice {

	public static void main(String[] args) {
		Init.migrate();
		SpringApplication.run(OwnerMicroservice.class, args);
	}
}
