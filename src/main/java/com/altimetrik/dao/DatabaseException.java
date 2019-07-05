package com.altimetrik.dao;

/**
 * High level Exception for Database related exceptions
 * @author kdevkar
 *
 */
public class DatabaseException extends Exception {
	public DatabaseException() {
		super("Database error has occurred !");
	}
	public DatabaseException(String message) {
		super(message);
	}
}
