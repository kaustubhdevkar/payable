package com.altimetrik.business;

/**
 * Exception for Already Approved Invoice 
 * @author kdevkar
 *
 */
public class AlreadyApprovedException extends Exception {
	public AlreadyApprovedException() {
		super("Invoice is Already Approved");
	}
	public AlreadyApprovedException(String str) {
		super(str);
	}

}
