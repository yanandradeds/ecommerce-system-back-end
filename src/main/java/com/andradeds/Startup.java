package com.andradeds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.udemy")
public class Startup {

	public static void main(String[] args) {

		SpringApplication.run(Startup.class, args);

	}

}
