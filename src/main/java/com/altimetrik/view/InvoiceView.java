package com.altimetrik.view;

import java.util.List;
import java.util.Scanner;

import com.altimetrik.view.Viewable;
import com.altimetrik.pojo.Invoice;
/**
 * This class forms the view layer of the application and it implements Viewable<E> Interface
 * @author kdevkar
 *
 */
public class InvoiceView implements Viewable<Invoice>{

	/**
	 * Prints one invoice
	 * @param obj an invoice object
	 */
	@Override
	public void list(Invoice obj) {
		
		if(obj != null)
		{
			System.out.println("Invoice No :"+obj.getInvoiceNo());
			System.out.println("Invoice Date:"+obj.getInvoiceDate());
			System.out.println("Customer PO :"+obj.getCustomerPO());
			System.out.println("Email :"+obj.getEmail());
			System.out.println("Address :"+obj.getAddress());
			System.out.println("Amount :"+obj.getAmount());
			System.out.println("Is Approved ? :"+(obj.isApproved() ? "YES":"NO"));
		}
		
		
	}
	
	/**
	 * Prints list of invoices
	 * @param obj an invoice list object
	 */
	@Override
	public void listAll(List<Invoice> obj) {
		if(obj == null)
			System.out.println("No Invoice Found !!");
		else{
			System.out.println("All Invoices Are :\n");
			for(Invoice currentObj: obj)
			{
				list(currentObj);
				System.out.println("================================================");
			}
				
		}
		
		
	}
	/**
	 * Takes input as invoice number puts it in an invoice object and returns it
	 * @return Invoice 
	 */
	@Override
	public Invoice takeInput() {
		System.out.println("Enter Invoice No :");
		Scanner sc = new Scanner(System.in);
		String invoiceNo = sc.next();
		Invoice obj = new Invoice();
		obj.setInvoiceNo(invoiceNo);
		return obj;
	}
	/**
	 * prints the passes message
	 * @param message
	 */
	@Override
	public void printMessage(String message) {
		System.out.println(message);
	}

}
