package com.altimetrik.business;

public class InvoiceNotFoundException extends Exception {
	public InvoiceNotFoundException() {
		super("No such Invoice Found.");
	}
}
