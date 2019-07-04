package com.altimetrik.dao;

public class DatabaseException extends Exception {
	public DatabaseException() {
		super("Database error has occurred !");
	}
	public DatabaseException(String message) {
		super(message);
	}
}
