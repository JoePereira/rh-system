package com.joe.rh_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.joe.rh_system")
public class RhSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhSystemApplication.class, args);
	}

}
