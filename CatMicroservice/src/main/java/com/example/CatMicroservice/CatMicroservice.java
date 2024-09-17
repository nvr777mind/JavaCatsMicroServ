package com.example.CatMicroservice;

import com.example.CatMicroservice.Migrations.Init;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatMicroservice {

	public static void main(String[] args) {
		Init.migrate();
		SpringApplication.run(CatMicroservice.class, args);
	}
}
