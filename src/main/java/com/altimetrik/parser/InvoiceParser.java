package com.altimetrik.parser;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.mail.internet.ParseException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.altimetrik.parser.ParsingException;
import com.altimetrik.pojo.Invoice;
/**
 * Class to parse invoices
 * @author kdevkar
 *
 */
public class InvoiceParser {
	/**
	 * Standard DateFormat to represent date
	 */
	public static DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd/MM/yy");
	/**
	 * parses the invoice on the given path
	 * @param path a string representing path to invoice
	 * @return Invoice invoice object
	 * @throws ParsingException
	 */
	public static Invoice parse(String path) throws ParsingException
	{
		File file = new File(path);
		try(PDDocument doc = PDDocument.load(file)) 
		{
			 PDFTextStripper pdfStripper = new PDFTextStripper();  
			 String text = pdfStripper.getText(doc);  
			 String[] splitText = text.split("\n");
			 double amount = 0;
			 // Local variables for all the fields
			 String invoiceNo="",customerPO="",address="";
			 LocalDate invoiceDate = null;

			 for(int i=0;i<splitText.length;i++)
			 {
				 if(splitText[i].trim().equals("Invoice No"))
					 invoiceNo = splitText[i+1].trim();
				 if(splitText[i].trim().equals("Invoice Date"))
					 invoiceDate = LocalDate.parse(splitText[i+1].trim(),dateFormat);
				 if(splitText[i].trim().equals("Customer P.O."))
					 customerPO = splitText[i+1].trim();
				 if(splitText[i].trim().equals("Sold To"))
				 {
					 i++;
					 while(!splitText[i].trim().equals("Ship To"))
					 {
						 address += splitText[i].trim();
						 address += " ";
						 i++;
					 }


				 }

			 }

			 outer: for(int i=0;i<splitText.length;i++)
			 {
				 if(splitText[i].trim().equals("Total Invoice"))
				 {

					 for(int j=i+1;j<splitText.length - 1 ;j++)
					 {
						 if(!splitText[j+1].startsWith("$"))
						 {
							 String price = splitText[j];
							 price = price.replace(",", "");
							 price = price.replace("$", "");
							 amount = Double.valueOf(price);
							 break outer;
						 }


					 }
				 }
			 }
			 /*
			 System.out.println("Invoice No is :"+invoiceNo);
			 System.out.println("Invoice Date is :"+invoiceDate);
			 System.out.println("Customer PO is :"+customerPO);
			 System.out.println("Address is  :"+address);
			 System.out.println("Total Invoice Amount is :"+amount);
			 */
			 return new Invoice(invoiceNo,invoiceDate,customerPO,address,amount);

		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
			throw new ParsingException("Unable To Parse PDF");
		}  
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ParsingException("Unable To Parse PDF");
		}
       

         
	}
}
