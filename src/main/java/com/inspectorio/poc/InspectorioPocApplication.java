package com.inspectorio.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.inspectorio.poc")
public class InspectorioPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(InspectorioPocApplication.class, args);
	}

}
