package com.altimetrik.controller;

import java.util.List;

import com.altimetrik.dao.InvoiceDAO;
import com.altimetrik.dao.DatabaseException;
import com.altimetrik.mail.MailingException;
import com.altimetrik.mail.MailManager;
import com.altimetrik.mail.MailReaderThread;
import com.altimetrik.pojo.Invoice;
import com.altimetrik.view.InvoiceView;

public class InvoiceController {
	private InvoiceDAO dao;
	private InvoiceView view;
	private MailReaderThread mailThread;
	private static int maxTryCount = 5; 
	public InvoiceController() {
		dao = new InvoiceDAO();
		view = new InvoiceView();
		mailThread = new MailReaderThread();
		mailThread.start();
	}
	
	public void sendInvoiceApproveMail(Invoice obj) throws MailingException
	{
		 String subject =  "Your Invoice is Approved !";
		 String content = "Your Invoice No "+obj.getInvoiceNo()+" has been Approved!";
		 MailManager.sendEmail(obj.getEmail(),subject,content);
	}
	public void listAllInvoices()
	{
		try {
			List<Invoice> listOfInvoices = dao.getAllFromDatabase();
			view.listAll(listOfInvoices);
		} catch (DatabaseException e) {
			//e.printStackTrace();
			view.printMessage("Unknown Error occurred ! Please Try Again.");
		}
		
	}
	public void listInvoice()
	{
		try {
			Invoice invoice = view.takeInput();
			invoice = dao.getFromDatabase(invoice.getInvoiceNo());
			if(invoice == null)
				view.printMessage("No such Invoice Found !!");
			else view.list(invoice);
		} catch (DatabaseException e) {
			//e.printStackTrace();
			view.printMessage("Unknown Error occurred ! Please Try Again.");
		}
	}
	public void approveInvoice()
	{
		try {
			Invoice invoice = view.takeInput();
			invoice = dao.getFromDatabase(invoice.getInvoiceNo());
			if(invoice == null)
				view.printMessage("No such Invoice Found !!");
			else if(invoice.isApproved() == true)
				view.printMessage("This Invoice is already approved !");
			else {
				dao.approveInvoice(invoice.getInvoiceNo());
				view.printMessage("Sending Approved Mail...");
				int currrntTryCount = 0;
				while(currrntTryCount < maxTryCount)
				{
					try {
						sendInvoiceApproveMail(invoice);
						break;
					} catch (MailingException e) {
						//e.printStackTrace();
						currrntTryCount++;
					}
				}
				view.printMessage("Invoice Approved ! Vendor is notified with email");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			view.printMessage("Unknown Error occurred ! Please Try Again.");
		}
	}
	
}
