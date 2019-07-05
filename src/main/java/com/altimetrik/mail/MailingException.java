package com.altimetrik.mail;
/**
 * High level exception for mailing exceptions
 * @author kdevkar
 *
 */
public class MailingException extends Exception {
	
	 public MailingException() {
		 super("Mailing Error has Occurred!");
	}
	 public MailingException(String message) {
		 	super(message);
			
		}
	 
}
