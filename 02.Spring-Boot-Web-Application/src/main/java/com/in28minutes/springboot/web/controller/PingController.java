package com.in28minutes.springboot.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.in28minutes.springboot.dummy.DummyService;
import com.in28minutes.springboot.web.exceptions.GlobalException;

@Controller
@SessionAttributes("name")
public class PingController {
	private final static ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
			.getLogger("com.in28minutes.springboot.web.controller");
	@Autowired
	DummyService dummyService;
	
	@ResponseBody
	@RequestMapping(value = {"/ping"})
	public String acceptPing() {
		String message = dummyService.testDummy1();
		System.out.println(message);
		if(true) {
			try { //emulate error
				int crash = 1/0;
			}
			
			catch(Exception e) {
//				RuntimeException re = new RuntimeException("rethrown message " +e.getMessage(), e.getCause());
//				re.setStackTrace(e.getStackTrace());
//				
//					throw re;
				
				GlobalException ex = new GlobalException(e.getMessage(), e.getStackTrace(), e);
				throw ex;
			}
//			RuntimeException re = new RuntimeException();
//			re.setStackTrace(stackTrace);
//			throw new IllegalArgumentException("emulate exception");
		}
		return message;
	}
	
	//http://localhost:8080/userping?name=xxxx
	@RequestMapping(value = {"/userping"})
	public ModelAndView pingWithName(@RequestParam(name = "name") String name) {
		ModelAndView modelAndVeiw = new ModelAndView("ping");
		modelAndVeiw.addObject("name", name);
		return modelAndVeiw;
	}
	
	//TODO improve Error handling
	//@ExceptionHandler works at the Coller level solution1 https://www.baeldung.com/exception-handling-for-rest-with-spring
	//@RequestMapping("/error")
//	@ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class, IOException.class, RuntimeException.class})
//	public ModelAndView handleError(HttpServletRequest request, RuntimeException ex) {
//		ModelAndView mv = new ModelAndView();
//		System.out.println("handle Exception");
//		if (logger.isTraceEnabled()) {
//			logger.debug("trace log handle Exception");
//
//			mv.addObject("exception", ex.getLocalizedMessage());
//			mv.addObject("url", request.getRequestURL());
//			String stackTrace = ExceptionUtils.getStackTrace(ex);
//			mv.addObject("stacktrace", stackTrace);
//		}
//
////		mv.setViewName("error");
//		mv.setViewName("myerror");
//		return mv;
//	}

}
