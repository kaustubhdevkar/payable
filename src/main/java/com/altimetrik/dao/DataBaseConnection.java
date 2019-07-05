package com.altimetrik.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import com.altimetrik.dao.DatabaseException;
/**
 * class to get the database connection
 * @author kdevkar
 *
 */
public class DataBaseConnection {

	
	private static String dbPropertiesPath =
			"C:\\Users\\kdevkar\\Desktop\\java\\payable\\src\\main\\resources\\db.properties";
	
	/**
	 * Gets the connection objects
	 * @return Connection
	 * @throws DatabaseException
	 */
	public static  Connection getConnection() throws DatabaseException
	{
		
			try
			{  
				Properties properties = new Properties();
				properties.load(new FileReader(dbPropertiesPath));
				
				
				return DriverManager.getConnection(
						properties.getProperty("connectionString"),
						properties.getProperty("userName"),
						properties.getProperty("password")); 
				
			}
			catch(Exception e)
			{ 
				e.printStackTrace();
				throw new DatabaseException("Database Error Occurred");
			}  

		
	}
	
}
