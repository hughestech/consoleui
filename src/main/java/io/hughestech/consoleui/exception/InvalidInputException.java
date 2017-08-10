package io.hughestech.consoleui.exception;

import io.hughestech.consoleui.ReadEventAnnotation;

@ReadEventAnnotation
public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message) {
		super(message);
	}

}
