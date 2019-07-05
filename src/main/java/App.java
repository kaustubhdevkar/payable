import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.altimetrik.controller.InvoiceController;
import com.altimetrik.mail.MailReaderThread;
/**
 * This is main App class which should be run for running applications
 * @author kdevkar
 *
 */
public class App {

	public static void main(String[] args) {
		
		InvoiceController ctrl = new InvoiceController();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome To Account Payable App");
		while(true)
		{
			System.out.println("\n================================================");
			System.out.println("Menu");
			System.out.println("1.List Invoice");
			System.out.println("2.List All Invoices");
			System.out.println("3.Approve Invoice");
			System.out.println("4.Exit");
			System.out.println("\n================================================");
			System.out.println("Enter Your Choice >>");
			try{
				int choice = Integer.valueOf(br.readLine());
				switch(choice)
				{
				case 1:
					ctrl.listInvoice();
					break;
				case 2:
					ctrl.listAllInvoices();
					break;
				case 3:
					ctrl.approveInvoice();
		
					break;
				case 4:
					System.exit(0);
				default:
					System.out.println("Wrong Choice !!");
					
				}
			}
			catch(Exception e)
			{
				System.out.println("Enter Valid Choice..");
			}
			
		}
	

	}

}
