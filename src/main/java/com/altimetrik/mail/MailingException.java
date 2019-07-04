package com.altimetrik.mail;

public class MailingException extends Exception {
	
	 public MailingException() {
		 super("Mailing Error has Occurred!");
	}
	 public MailingException(String message) {
		 	super(message);
			
		}
	 
}
