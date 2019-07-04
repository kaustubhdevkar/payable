package com.altimetrik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.altimetrik.dao.DatabaseException;
import com.altimetrik.dao.DataAccess;
import com.altimetrik.parser.InvoiceParser;
import com.altimetrik.pojo.Invoice;

public class InvoiceDAO implements DataAccess<Invoice>{

	@Override
	public void addToDatabase(Invoice obj) throws DatabaseException {
		try
		{
			Connection conn = DataBaseConnection.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("insert into invoice(invoice_no,invoice_date,"
					+ "customer_po,address,amount,is_approved,email) values(?,?,?,?,?,?,?);");
			stmt.setString(1, obj.getInvoiceNo());
			if(obj.getInvoiceDate() != null)
			stmt.setString(2, obj.getInvoiceDate().toString());
			else stmt.setString(2, null);
			stmt.setString(3, obj.getCustomerPO());
			stmt.setString(4, obj.getAddress());
			stmt.setDouble(5, obj.getAmount());
			stmt.setBoolean(6, obj.isApproved());
			stmt.setString(7, obj.getEmail());
			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			throw new DatabaseException("Database Error Occurred");
		}
		
		
		
	}


	@Override
	public Invoice getFromDatabase(int id) throws DatabaseException {
		
		try
		{
			Connection conn = DataBaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from invoice where id = ? ;");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next())
			{
				Invoice invoice = new Invoice();
				invoice.setId(resultSet.getInt(1));
				invoice.setInvoiceNo(resultSet.getString(2));
				invoice.setInvoiceDate(LocalDate.parse(resultSet.getString(3)));
				invoice.setCustomerPO(resultSet.getString(4));
				invoice.setAddress(resultSet.getString(5));
				invoice.setAmount(resultSet.getDouble(6));
				invoice.setApproved(resultSet.getBoolean(7));
				invoice.setEmail(resultSet.getString(8));
				return invoice;
			}
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			throw new DatabaseException("Database Error Occurred");
		}
		return null;
		
		
		
	}
	

	@Override
	public List<Invoice> getAllFromDatabase() throws DatabaseException {
		try
		{
			Connection conn = DataBaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from invoice;");
			ResultSet resultSet = stmt.executeQuery();
			List<Invoice> list = new ArrayList<>();
			while(resultSet.next())
			{
				Invoice invoice = new Invoice();
				invoice.setId(resultSet.getInt(1));
				invoice.setInvoiceNo(resultSet.getString(2));
				invoice.setInvoiceDate(LocalDate.parse(resultSet.getString(3)));
				invoice.setCustomerPO(resultSet.getString(4));
				invoice.setAddress(resultSet.getString(5));
				invoice.setAmount(resultSet.getDouble(6));
				invoice.setApproved(resultSet.getBoolean(7));
				invoice.setEmail(resultSet.getString(8));
				list.add(invoice);
			}
			if(list.size() > 0)
				return list;
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			throw new DatabaseException("Database Error Occurred");
		}
		return null;
		
		
	}
	public Invoice getFromDatabase(String invoiceNo) throws DatabaseException {
		try
		{
			Connection conn = DataBaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from invoice where invoice_no = ? ;");
			stmt.setString(1, invoiceNo);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next())
			{
				Invoice invoice = new Invoice();
				invoice.setId(resultSet.getInt(1));
				invoice.setInvoiceNo(resultSet.getString(2));
				invoice.setInvoiceDate(LocalDate.parse(resultSet.getString(3)));
				invoice.setCustomerPO(resultSet.getString(4));
				invoice.setAddress(resultSet.getString(5));
				invoice.setAmount(resultSet.getDouble(6));
				invoice.setApproved(resultSet.getBoolean(7));
				invoice.setEmail(resultSet.getString(8));
				return invoice;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException("Database Error Occurred");
		}
		return null;
		
		
	}
	public void approveInvoice(String invoiceNo) throws DatabaseException
	{
		try
		{
			Connection conn = DataBaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("update invoice set is_approved = ? where invoice_no = ?;");
			stmt.setBoolean(1, true);
			stmt.setString(2, invoiceNo);
			stmt.executeUpdate();
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			throw new DatabaseException("Database Error Occurred");
		}
		
	}

}
