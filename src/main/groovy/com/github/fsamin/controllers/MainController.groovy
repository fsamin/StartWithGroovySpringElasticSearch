package com.github.fsamin.controllers

import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@ComponentScan(['com.github.fsamin','com.github.fsamin.controllers'])
class MainController {

	@RequestMapping("/")
	String home() {
		return "Hello";
	}
}
