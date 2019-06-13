package com.in28minutes.springboot.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

//https://www.baeldung.com/exception-handling-for-rest-with-spring

//https://www.baeldung.com/spring-boot-custom-error-page
//@Controller("error")
//@Controller
//public class MyErrorController implements ErrorController {
public class MyErrorControllerDummy extends BasicErrorController{
	public MyErrorControllerDummy(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
		super(errorAttributes, errorProperties);
		// TODO Auto-generated constructor stub
	}

	private final static ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
			.getLogger("com.in28minutes.springboot.web.controller");

//	//@ExceptionHandler(Exception.class)
//	@ExceptionHandler(RuntimeException.class)
//	public ModelAndView handleException
//		(HttpServletRequest request, Exception ex){
//		
//		if(logger.isTraceEnabled()) {
//			logger.trace("handle Exception");
//		}
//		System.out.println("handle Exception");
//		if(true) {
//			throw new RuntimeException();
//		}
//		ModelAndView mv = new ModelAndView();
//		
//		mv.addObject("exception", ex.getLocalizedMessage());
//		mv.addObject("url", request.getRequestURL());
//		mv.addObject("stacktrace", ex.getStackTrace().toString());
//		
////		mv.setViewName("error");
//		mv.setViewName("myerror");
//		return mv;
//	}

//	@RequestMapping("/error")
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
//
//	@Override
//	public String getErrorPath() {
//		// TODO Auto-generated method stub
//		// return "/myerror";
//		return "error";
//	}

}
