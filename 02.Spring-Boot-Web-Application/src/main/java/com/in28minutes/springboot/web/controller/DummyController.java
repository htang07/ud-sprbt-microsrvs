package com.in28minutes.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.in28minutes.springboot.dummy.DummyService;

@Controller
public class DummyController {
	
	@Autowired
	DummyService dummyService;
	
	@ResponseBody
	@RequestMapping(value = {"/ping"})
	public String acceptPing() {
		System.out.println(dummyService.testDummy1());
		return "Hello!";
	}

}
