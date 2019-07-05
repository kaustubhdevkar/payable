package com.altimetrik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.altimetrik.dao.DatabaseException;
import com.altimetrik.dao.DataAccess;
import com.altimetrik.parser.InvoiceParser;
import com.altimetrik.pojo.Invoice;

/**
 * DataAccess Object class for Invoice implements the DataAccess interface
 * @author kdevkar
 *
 */
public class InvoiceDAO implements DataAccess<Invoice>{

	/**
	 * Adds one Invoice object to database
	 * @throws DatabaseException
	 */
	@Override
	public void addToDatabase(Invoice obj) throws DatabaseException {
		Connection conn  = null;
		try
		{
			 conn = DataBaseConnection.getConnection();
			
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
			conn.close();
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			throw new DatabaseException("Database Error Occurred");
		}
		finally{
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//throw new DatabaseException("Database Error Occurred");
				}
			
		}
		
		
		
	}
	
	/**
	 * Returns an invoice objects given and id if it exists otherwise null
	 * will be returned
	 * @param id ID in the database
	 * @return Invoice Invoice Object
	 * @throws DatabaseException
	 */
	@Override
	public Invoice getFromDatabase(int id) throws DatabaseException {
		Connection conn  = null;
		try
		{
			 conn = DataBaseConnection.getConnection();
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
		finally{
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//throw new DatabaseException("Database Error Occurred");
				}
			
		}
		return null;
		
		
		
	}
	
	/**
	 * Gets all the invoices from databases as a list of invoice objects ,
	 * if no invoice is present then null will be returned
	 * @return List<Invoice> 
	 * @throws DatabaseException
	 */

	@Override
	public List<Invoice> getAllFromDatabase() throws DatabaseException {
		Connection conn  = null;
		try
		{
			conn = DataBaseConnection.getConnection();
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
		finally{
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//throw new DatabaseException("Database Error Occurred");
				}
			
		}
		return null;
		
		
	}
	/**
	 * Returns an invoice objects given invoice NO if it exists otherwise null
	 * will be returned
	 * @param invoiceNo invoiceNo in the database
	 * @return Invoice Invoice Object
	 * @throws DatabaseException
	 */
	public Invoice getFromDatabase(String invoiceNo) throws DatabaseException {
		Connection conn  = null;
		try
		{
			 conn = DataBaseConnection.getConnection();
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
		finally{
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//throw new DatabaseException("Database Error Occurred");
				}
			
		}
		return null;
		
		
	}
	/**
	 * Approves an invoice  given invoice NO if it exists otherwise throws an exception
	 * @param invoiceNo invoiceNo in the database
	 * @throws DatabaseException
	 */
	public void approveInvoice(String invoiceNo) throws DatabaseException
	{
		Connection conn  = null;
		try
		{
			 conn = DataBaseConnection.getConnection();
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
		finally{
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//throw new DatabaseException("Database Error Occurred");
				}
			
		}
		
	}

}
