package com.altimetrik.parser;
/**
 * High level Exception for Parser Exceptions
 * @author kdevkar
 *
 */
public class ParsingException extends Exception{
	String message;
	public ParsingException() {
		message = "Parsing Error has Occurred !";
	}
	public ParsingException(String message) {
		super(message);
	}

}
