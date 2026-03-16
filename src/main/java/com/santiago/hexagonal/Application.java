package com.santiago.hexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.santiago.hexagonal.application.interfaces.UseCase;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.santiago.hexagonal" }, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UseCase.class))
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
