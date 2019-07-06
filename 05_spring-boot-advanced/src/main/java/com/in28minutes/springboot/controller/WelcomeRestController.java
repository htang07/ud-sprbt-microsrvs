package com.in28minutes.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {
	
	@RequestMapping("/ping")
	public String ping() {
		return "Succes!";
	}
}
