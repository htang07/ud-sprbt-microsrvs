package com.in28minutes.springboot.web.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class GlobalException extends RuntimeException {
	
	public GlobalException() {
		super();
		
	}
	

	public GlobalException(String message, StackTraceElement[] trace,Exception innerException) {
		super("innerException - " + innerException.getClass().getName() + ": " + message);
		super.setStackTrace(innerException.getStackTrace());
		this.innerException = innerException;
		this.innerException.setStackTrace(trace);
	}
	private Exception innerException;

	public Exception getInnerException() {
		return innerException;
	}

	public void setInnerException(Exception innerException) {
		this.innerException = innerException;
	}
	public String getStackTraceAsString() {
		return ExceptionUtils.getStackTrace(innerException);
	}
	

	
	
	
	
	
	
}
