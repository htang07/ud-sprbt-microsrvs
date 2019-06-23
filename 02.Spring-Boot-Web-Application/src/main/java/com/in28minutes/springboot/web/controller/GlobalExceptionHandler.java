package com.in28minutes.springboot.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.in28minutes.springboot.web.exceptions.GlobalException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
	private final static ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
			.getLogger("com.in28minutes.springboot.web.controller");
	
	@ResponseBody
	@ExceptionHandler({GlobalException.class})
	public ModelAndView handleError(Exception ex, WebRequest request) {
		
		if(ex instanceof GlobalException) {
			System.out.println("yes");
		}
		ModelAndView mv = new ModelAndView();
		
		if(logger.isTraceEnabled()) {
			mv.addObject("stacktrace", ExceptionUtils.getStackTrace(ex));
			mv.addObject("url_to_exception", ((ServletWebRequest)request).getRequest().getRequestURL().toString());
			mv.addObject("http_status",  HttpStatus.INTERNAL_SERVER_ERROR);
			mv.addObject("error_message", ex.getMessage());
		}
		mv.setViewName("/myerror");
		return mv;
	}

//	@ResponseBody
//	@ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class, IOException.class, RuntimeException.class})
//	public String handleError(Exception ex, WebRequest request) {
//		
//		return String.format(
//				"<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
//						+ "<div>Exception Message: <b>%s</b></div><div>%s</div><body></html>",
//				HttpStatus.INTERNAL_SERVER_ERROR, ex == null ? "N/A" : ex.getMessage(), ExceptionUtils.getStackTrace(ex));
//	}
}
