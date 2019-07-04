package com.altimetrik.mail;
import static com.altimetrik.mail.MailManager.properties;

import com.altimetrik.mail.MailingException;
public class MailReaderThread extends Thread{
	@Override
	public void run() {
		String pop3Host = properties.getProperty("pop3Host");
		String mailStoreType = properties.getProperty("mailStoreType");	
		final String userName = properties.getProperty("userName");	
		final String password =  properties.getProperty("password");	
		while(true)
		{
			
			try {
				
				MailManager.receiveEmail(pop3Host, mailStoreType, userName, password);
				Thread.sleep(100);
			}
			catch(MailingException e)
			{
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}

	}
}
