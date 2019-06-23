package com.in28minutes.springboot.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Note: This approach doesn't work as global exception handler catch exceptions
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	@RequestMapping("/error")
	@ResponseBody
	@ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class, IOException.class, RuntimeException.class})
	public String handleError(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		return String.format(
				"<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
						+ "<div>Exception Message: <b>%s</b></div><body></html>",
				statusCode, exception == null ? "N/A" : exception.getMessage());
	}

	@Override
	public String getErrorPath() {

		return "/myerror";
	}

}

//@Controller
//public class CustomErrorController extends BasicErrorController {
//
//    public CustomErrorController() {
//        super(new DefaultErrorAttributes());
//    }
//
//    @RequestMapping(value = "${error.path:/error}", produces = "text/html")
//    public ModelAndView errorHtml(HttpServletRequest request) {
//        if (request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) == null) {
//            // do not allow the user to call this action directly
//            return new ModelAndView("redirect:");
//        }
//        return super.errorHtml(request);
//    }
//
//
//}
