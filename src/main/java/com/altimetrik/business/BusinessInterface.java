package com.altimetrik.business;

import java.util.List;

import com.altimetrik.dao.DatabaseException;
import com.altimetrik.dao.InvoiceDAO;
import com.altimetrik.pojo.Invoice;
/**
 * Interface for business layer implementations of Invoice App
 * @author kdevkar
 *
 */
public interface BusinessInterface {
	/**
	 * Adds invoice to the database
	 * @throws DatabaseException 
	 */
	public void addInvoice(Invoice invoice) throws DatabaseException;
	/**
	 * fetches all invoices from database and returns it in a List of invoice objects
	 * @return 
	 * @throws DatabaseException 
	 */
	public List<Invoice> getAllInvoices() throws DatabaseException;
	/**
	 * fetches one invoice from database 
	 * @throws DatabaseException 
	 * @throws InvoiceNotFoundException 
	 */
	public Invoice getInvoice(Invoice invoice) throws DatabaseException, InvoiceNotFoundException;
	
	/**
	 * approves one invoice from database
	 * @throws DatabaseException 
	 * @throws InvoiceNotFoundException 
	 * @throws AlreadyApprovedException 
	 */
	public Invoice approveInvoice(Invoice invoice) throws DatabaseException, InvoiceNotFoundException, AlreadyApprovedException;
	
}
