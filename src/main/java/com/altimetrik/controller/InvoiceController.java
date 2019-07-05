package com.altimetrik.controller;

import java.util.List;

import com.altimetrik.dao.InvoiceDAO;
import com.altimetrik.business.AlreadyApprovedException;
import com.altimetrik.business.InvoiceBusiness;
import com.altimetrik.business.InvoiceNotFoundException;
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
	private InvoiceBusiness business;
	private Viewable<Invoice> view;
	private MailReaderThread mailThread; 
	public InvoiceController() {
		business = new InvoiceBusiness();
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
			List<Invoice> listOfInvoices = business.getAllInvoices();
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
			if(invoice == null && invoice.getInvoiceNo().length() == 0)
			{
				view.printMessage("Please Enter Invoice No");
				return;
			}
			invoice = business.getInvoice(invoice);
			view.list(invoice);
		} catch (InvoiceNotFoundException e) {
			// TODO Auto-generated catch block
			view.printMessage("No such Invoice Found..");
		}catch (Exception e) {
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
			if(invoice == null && invoice.getInvoiceNo().length() == 0)
			{
				view.printMessage("Please Enter Invoice No");
				return;
			}
			invoice = business.approveInvoice(invoice);
			sendInvoiceApproveMail(invoice);
			view.printMessage("Invoice Approved Successfully ! Vendor will be notified with email");
		} catch (InvoiceNotFoundException e) {
			view.printMessage("No such Invoice Found..");
		} catch (AlreadyApprovedException e) {
			view.printMessage("This Invoice is already Approved.");
		}
		catch (Exception e) {
			view.printMessage("Unknown Error occurred ! Please Try Again.");
		} 
	}
	
}
