package com.analyzer.system.exception;

public class SystemRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7041457626558321657L;

	public SystemRuntimeException() {
		super();
	}

	public SystemRuntimeException(String message) {
		super(message);
	}

	public SystemRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
