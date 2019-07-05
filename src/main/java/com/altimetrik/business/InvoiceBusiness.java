package com.altimetrik.business;

import java.util.List;

import org.hamcrest.core.IsInstanceOf;

import com.altimetrik.dao.DataAccess;
import com.altimetrik.dao.DatabaseException;
import com.altimetrik.dao.InvoiceDAO;
import com.altimetrik.mail.MailManager;
import com.altimetrik.mail.MailReaderThread;
import com.altimetrik.mail.MailingException;
import com.altimetrik.pojo.Invoice;
import com.altimetrik.view.InvoiceView;
import com.altimetrik.view.Viewable;

public class InvoiceBusiness {
	private DataAccess<Invoice> dao;
	public InvoiceBusiness() {
		dao = new InvoiceDAO();
	}
	/**
	 * Adds invoice to the database
	 * @throws DatabaseException 
	 */
	public void addInvoice(Invoice invoice) throws DatabaseException
	{
		dao.addToDatabase(invoice);
	}
	/**
	 * fetches all invoices from database and returns it in a List of invoice objects
	 * @return 
	 * @throws DatabaseException 
	 */
	public List<Invoice> getAllInvoices() throws DatabaseException
	{
			return dao.getAllFromDatabase();
	}
	/**
	 * fetches one invoice from database 
	 * @throws DatabaseException 
	 * @throws InvoiceNotFoundException 
	 */
	public Invoice getInvoice(Invoice invoice) throws DatabaseException, InvoiceNotFoundException
	{
		
		if(dao instanceof InvoiceDAO)
		{
			InvoiceDAO daoObject = (InvoiceDAO) dao;
			Invoice invoiceObject = daoObject.getFromDatabase(invoice.getInvoiceNo());
			if(invoiceObject == null)
				throw new InvoiceNotFoundException();
			return invoiceObject;
		}
		return null;
		
	}
	/**
	 * approves one invoice from database
	 * @throws DatabaseException 
	 * @throws InvoiceNotFoundException 
	 * @throws AlreadyApprovedException 
	 */
	public Invoice approveInvoice(Invoice invoice) throws DatabaseException, InvoiceNotFoundException, AlreadyApprovedException
	{
		if(dao instanceof InvoiceDAO)
		{
			
				InvoiceDAO daoObject = (InvoiceDAO) dao;
				invoice = daoObject.getFromDatabase(invoice.getInvoiceNo());
				if(invoice == null)
					throw new InvoiceNotFoundException();
				else if(invoice.isApproved() == true)
					throw new AlreadyApprovedException();
				
				daoObject.approveInvoice(invoice.getInvoiceNo());
				return invoice;
				
		}
		return null;
		
			
	}

}
