package com.altimetrik.business;

public class InvoiceNotFoundException extends Exception {
	public InvoiceNotFoundException() {
		super("No such Invoice Found.");
	}
	public InvoiceNotFoundException(String str) {
		super(str);
	}
}
