package com.altimetrik.pojo;

import java.time.LocalDate;

public class Invoice {
	private int id;
    private String invoiceNo;
    private LocalDate invoiceDate;
    private String customerPO,address;
    private double amount;
    private boolean isApproved = false;
    private String email;
	
	public Invoice(int id, String invoiceNo, LocalDate invoiceDate, String customerPO, String address, double amount,
			boolean isApproved, String email) {
		super();
		this.id = id;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.customerPO = customerPO;
		this.address = address;
		this.amount = amount;
		this.isApproved = isApproved;
		this.email = email;
	}
	
	
	public Invoice(String invoiceNo, LocalDate invoiceDate, String customerPO, String address, double amount,
			boolean isApproved,String email) {
		super();
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.customerPO = customerPO;
		this.address = address;
		this.amount = amount;
		this.isApproved = isApproved;
		this.email = email;
	}
	public Invoice(String invoiceNo, LocalDate invoiceDate, String customerPO, String address, double amount) {
		super();
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.customerPO = customerPO;
		this.address = address;
		this.amount = amount;
	}
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getCustomerPO() {
		return customerPO;
	}
	public void setCustomerPO(String customerPO) {
		this.customerPO = customerPO;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", customerPO="
				+ customerPO + ", address=" + address + ", amount=" + amount + ", isApproved=" + isApproved + ", email="
				+ email + "]";
	}
	
}
