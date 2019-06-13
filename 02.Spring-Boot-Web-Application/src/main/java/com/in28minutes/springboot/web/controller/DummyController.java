package com.in28minutes.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(names = {"name"})
public class DummyController {
	
	@RequestMapping(value = {"/hello"})
	public ModelAndView hello(ModelMap model) {
		String name= (String) model.get("name");
		ModelAndView modelAndView = new ModelAndView("dummy1");
		modelAndView.addObject("name", name);
		return modelAndView;
	}
}
