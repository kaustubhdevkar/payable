package com.altimetrik.controller;

import java.util.List;

import com.altimetrik.dao.InvoiceDAO;
import com.altimetrik.dao.DataAccess;
import com.altimetrik.dao.DatabaseException;
import com.altimetrik.mail.MailingException;
import com.altimetrik.mail.MailManager;
import com.altimetrik.mail.MailReaderThread;
import com.altimetrik.pojo.Invoice;
import com.altimetrik.view.InvoiceView;
import com.altimetrik.view.Viewable;

/**
 * This is class for Invoice Controller which handles the communication between 
 * View layer and DAO layer
 * @author kdevkar
 *
 */
public class InvoiceController {
	private DataAccess<Invoice> dao;
	private Viewable<Invoice> view;
	private MailReaderThread mailThread;
	private static int maxTryCount = 5; 
	public InvoiceController() {
		dao = new InvoiceDAO();
		view = new InvoiceView();
		mailThread = new MailReaderThread();
		mailThread.start();
	}
	/**
	 * This method is used for sending approved invoice mails
	 * @param obj an Invoice object having invoice No and Email field mandatory.
	 * @throws MailingException
	 */
	public void sendInvoiceApproveMail(Invoice obj) throws MailingException
	{
		if(obj.getEmail() == null )
			throw new MailingException();
		else if(obj.getInvoiceNo() == null)
			throw new MailingException();
		 String subject =  "Your Invoice is Approved !";
		 String content = "Your Invoice No "+obj.getInvoiceNo()+" has been Approved!";
		 MailManager.sendEmail(obj.getEmail(),subject,content);
	}
	/**
	 * fetches all invoices from database and passes it to view layer for printing
	 */
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
	/**
	 * fetches one invoice from database and passes it to view layer for printing
	 */
	public void listInvoice()
	{
		try {
			Invoice invoice = view.takeInput();
			InvoiceDAO daoObject = (InvoiceDAO) dao;
			invoice = daoObject.getFromDatabase(invoice.getInvoiceNo());
			if(invoice == null)
				view.printMessage("No such Invoice Found !!");
			else view.list(invoice);
		} catch (DatabaseException e) {
			//e.printStackTrace();
			view.printMessage("Unknown Error occurred ! Please Try Again.");
		}
	}
	/**
	 * approves one invoice from database and passes it to view layer for printing
	 */
	public void approveInvoice()
	{
		try {
			Invoice invoice = view.takeInput();
			InvoiceDAO daoObject = (InvoiceDAO) dao;
			invoice = daoObject.getFromDatabase(invoice.getInvoiceNo());
			if(invoice == null)
				view.printMessage("No such Invoice Found !!");
			else if(invoice.isApproved() == true)
				view.printMessage("This Invoice is already approved !");
			else {
				daoObject.approveInvoice(invoice.getInvoiceNo());
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
