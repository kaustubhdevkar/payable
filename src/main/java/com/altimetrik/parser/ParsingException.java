package com.altimetrik.parser;

public class ParsingException extends Exception{
	String message;
	public ParsingException() {
		message = "Parsing Error has Occurred !";
	}
	public ParsingException(String message) {
		super(message);
	}

}
