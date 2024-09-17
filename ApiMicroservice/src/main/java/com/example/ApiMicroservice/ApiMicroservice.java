package com.example.ApiMicroservice;

import com.example.ApiMicroservice.Migrations.Init;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMicroservice {

	public static void main(String[] args) {
		Init.migrate();
		SpringApplication.run(ApiMicroservice.class, args);
	}
}
