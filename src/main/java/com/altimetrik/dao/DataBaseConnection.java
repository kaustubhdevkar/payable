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

public class DataBaseConnection {

	private static Connection conn;
	private static String dbPropertiesPath =
			"C:\\Users\\kdevkar\\Desktop\\java\\payable\\src\\main\\resources\\db.properties";
	public static  Connection getConnection() throws DatabaseException
	{
		if(conn == null)
		{
			try
			{  
				Properties properties = new Properties();
				properties.load(new FileReader(dbPropertiesPath));
				
				
				conn=DriverManager.getConnection(
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
		return conn;
	}
	
}
