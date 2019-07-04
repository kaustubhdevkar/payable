package com.altimetrik.view;

import java.util.List;
import java.util.Scanner;

import com.altimetrik.view.Viewable;
import com.altimetrik.pojo.Invoice;

public class InvoiceView implements Viewable<Invoice>{

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

	@Override
	public Invoice takeInput() {
		System.out.println("Enter Invoice No :");
		Scanner sc = new Scanner(System.in);
		String invoiceNo = sc.next();
		Invoice obj = new Invoice();
		obj.setInvoiceNo(invoiceNo);
		return obj;
	}

	@Override
	public void printMessage(String message) {
		System.out.println(message);
	}

}
