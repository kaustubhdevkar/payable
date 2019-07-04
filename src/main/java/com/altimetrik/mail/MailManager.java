package com.altimetrik.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Target;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.altimetrik.dao.InvoiceDAO;
import com.altimetrik.dao.DatabaseException;
import com.altimetrik.mail.MailingException;
import com.altimetrik.parser.InvoiceParser;
import com.altimetrik.pojo.Invoice;

public class MailManager{
	private static String mailPropertiesPath = 
			"C:\\Users\\kdevkar\\Desktop\\java\\payable\\src\\main\\resources\\mail.properties";
	private static String pdfFolderPath = "C:\\Users\\kdevkar\\Desktop\\alti\\";
	static Properties properties;
	
	static{
		properties = new Properties();
		try {
			properties.load(new FileReader(mailPropertiesPath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void sendEmail(String to,String subject,String content) throws MailingException
	{
			//Local properties
		   Properties props = new Properties();    
		   props.put("mail.smtp.host", "smtp.gmail.com");    
		   props.put("mail.smtp.socketFactory.port", "465");    
		   props.put("mail.smtp.socketFactory.class",    
				   "javax.net.ssl.SSLSocketFactory");    
		   props.put("mail.smtp.auth", "true");    
		   props.put("mail.smtp.port", "465");    
		   Session session = Session.getDefaultInstance(props,    
				   new javax.mail.Authenticator() {    
			   protected PasswordAuthentication getPasswordAuthentication() {    
				   return new PasswordAuthentication(properties.getProperty("userName"),
						   properties.getProperty("password"));  
			   }    
		   });    
	      
	       
		   try {    
			   MimeMessage message = new MimeMessage(session);    
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
			   message.setSubject(subject);    
			   message.setText(content);    
			   Transport.send(message);    
			   System.out.println("message sent successfully");    
		   } catch (MessagingException e) 
		   {
			   //e.printStackTrace();
			   throw new MailingException();
		   }    

	}
	
	
	
	 public static void receiveEmail(String pop3Host,
			    String mailStoreType, String userName, String password) throws MailingException{
			    Properties props = new Properties();
			    props.put("mail.store.protocol", "pop3");
			    props.put("mail.pop3.host", pop3Host);
			    props.put("mail.pop3.port", "995");
			    props.put("mail.pop3.starttls.enable", "true");
			    
			    
			    Session session = Session.getInstance(props);
			    session.setDebug(false);
			    Store store = null;
			    Folder emailFolder = null;
			   
			 
			    try {
			        //Create the POP3 store object and connect to the pop store.
				store = session.getStore("pop3s");
				store.connect(pop3Host, userName, password);
				
				//Create the folder object and open it in your mailbox.
				emailFolder = store.getFolder("INBOX");
				emailFolder.open(Folder.READ_ONLY);
				
			 
				//Retrieve the messages from the folder object.
				Message[] messages = emailFolder.getMessages();
				//System.out.println("Total Message " + messages.length);
				
				//Iterate the messages
				for (int i = 0; i < messages.length; i++) {
				   Message message = messages[i];
				   if(message.getSubject().startsWith("Invoice"))
				   {
					   Address[] toAddress = 
					             message.getRecipients(Message.RecipientType.TO);
						     
					   String senders_email = message.getFrom()[0].toString();
					   senders_email = senders_email.substring(senders_email.indexOf('<') + 1, senders_email.length() - 1);
					   if(message.getContent() instanceof Multipart)
					   {
						   Multipart multipart = (Multipart) message.getContent();
						   for(int k = 0; k < multipart.getCount(); k++){
							   BodyPart bodyPart = multipart.getBodyPart(k);  
							   if(bodyPart.getDisposition() != null && bodyPart.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
								   InputStream stream = (InputStream) bodyPart.getInputStream(); 
								   File targetFile = new File(pdfFolderPath+bodyPart.getFileName());
								   java.nio.file.Files.copy(
										   stream, 
										   targetFile.toPath(), 
										   StandardCopyOption.REPLACE_EXISTING);
								   Invoice obj =  InvoiceParser.parse(targetFile.getAbsolutePath());
								   obj.setEmail(senders_email);
								   InvoiceDAO dao = new InvoiceDAO();
								   dao.addToDatabase(obj);
					   }
				   }
				   
					       
					      }  
					   }
				 
				     }
				     
				   
				} 
			    catch (Exception e) {
					//e.printStackTrace();
			    	throw new MailingException();
				}
			    finally {
			    	try {
						//close the folder and store objects
						if(emailFolder != null)
						   emailFolder.close(false);
						if(store !=null)
						   store.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						throw new MailingException();
					}
			    }
			 
			    }
			 

}
