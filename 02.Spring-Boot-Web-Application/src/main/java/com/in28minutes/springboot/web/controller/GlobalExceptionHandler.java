package com.in28minutes.springboot.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
	
//	@RequestMapping("/error")
//	@ResponseBody
//	@ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class, IOException.class, RuntimeException.class})
//	public String handleError(HttpServletRequest request) {
//		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
//		return String.format(
//				"<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
//						+ "<div>Exception Message: <b>%s</b></div><body></html>",
//				statusCode, exception == null ? "N/A" : exception.getMessage());
//	}
	
	@ResponseBody
	@ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class, IOException.class, RuntimeException.class})
	public String handleError(Exception ex, WebRequest request) {
		
		return String.format(
				"<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
						+ "<div>Exception Message: <b>%s</b></div><div>%s</div><body></html>",
				HttpStatus.INTERNAL_SERVER_ERROR, ex == null ? "N/A" : ex.getMessage(), ExceptionUtils.getStackTrace(ex));
	}
}
