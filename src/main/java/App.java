import java.util.Scanner;

import com.altimetrik.controller.InvoiceController;
import com.altimetrik.mail.MailReaderThread;

public class App {

	public static void main(String[] args) {
		
		InvoiceController ctrl = new InvoiceController();
		Scanner sc = new Scanner(System.in);
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
			int choice = sc.nextInt();
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
	

	}

}
