package com.altimetrik.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import com.altimetrik.parser.InvoiceParser;
import com.altimetrik.pojo.Invoice;

public class InvoiceDAOTest {

	
	static InvoiceDAO dao;
	
	@BeforeClass
	public static void setUpBefore()
	{
		dao = new InvoiceDAO();
	}
	
	@Test(expected=DatabaseException.class)
	public void addOneTest() throws DatabaseException
	{
		Invoice invoice = new Invoice();
		invoice.setAddress("sfsdf");
		invoice.setAmount(23423);
		invoice.setCustomerPO("sdfsdg");
		invoice.setApproved(true);
		dao.addToDatabase(invoice);
		
	}
	
	@Test
	public void getOneTest() throws DatabaseException 
	{
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo("24442342");
		invoice.setAddress("sfsdf");
		invoice.setAmount(23423);
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setCustomerPO("sdfsdg");
		invoice.setApproved(true);
		invoice.setEmail("kk@gmail.com");
		dao.addToDatabase(invoice);
		assertEquals(invoice.getInvoiceNo(),dao.getFromDatabase(invoice.getInvoiceNo()).getInvoiceNo());
		//assertEquals(invoice.getInvoiceNo(),dao.getFromDatabase(invoice.getInvoiceNo()));
		
		
		
	}
	@Test
	public void approveInvoiceTest() throws DatabaseException 
	{
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo("24442346");
		invoice.setAddress("sfsdf");
		invoice.setAmount(23423);
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setCustomerPO("sdfsdg");
		invoice.setApproved(false);
		invoice.setEmail("kk@gmail.com");
		dao.addToDatabase(invoice);
		dao.approveInvoice(invoice.getInvoiceNo());
		Invoice obj = dao.getFromDatabase(invoice.getInvoiceNo());
		assertTrue(obj.isApproved());
		//assertEquals(invoice.getInvoiceNo(),dao.getFromDatabase(invoice.getInvoiceNo()).getInvoiceNo());
		//assertEquals(invoice.getInvoiceNo(),dao.getFromDatabase(invoice.getInvoiceNo()));
		
		
		
	}
}
