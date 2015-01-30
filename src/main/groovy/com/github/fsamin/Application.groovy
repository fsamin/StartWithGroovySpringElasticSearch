package com.github.fsamin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.fsamin.controllers.MainController;

@Configuration
@EnableAutoConfiguration
class Application {
	static main(args) {
		SpringApplication.run(MainController, args);
	}
}