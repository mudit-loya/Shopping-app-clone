//package project;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Login {
	private String username;
	private String password;
	
	Login(String uName,String pWord)
	{
		username = uName;
		password = pWord;
	}

	//Getting Credentials

	static Login getCredentials()
	{
		String uName, pWord;
		Scanner sc = new Scanner(System.in);
		while (true) 
		{
			try 
			{
				System.out.print("Enter your User Name : ");
				uName = sc.nextLine();
				if (Account.isValidUserName(uName)) 
				{
					break;
				}
			} 
			catch (InputMismatchException e) 
			{
				System.out.println("Wrong Input!!");
			}
		}
		while (true)
		{
			try
			{
				System.out.print("Enter your Password : ");
				pWord = sc.nextLine();
				if (Account.isValidPassword(pWord))
				{
					break;
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Wrong Input!!");
			}
		}
//		System.out.println("\n\nLogin Credentials achieved!!\n\n");
		return(new Login(uName,pWord));
	}
	
	// Checking for valid Login Credentials of a Customer
	
	Customer areValidCustomerCredentials()
	{
		Customer account = new Customer();
		LinkedList<Customer> customers = Customer.getAllCustomerDetails();
	    for (int i = 0; i < customers.size(); i++) 
	    {
	    	account = customers.get(i);
	    	if(((this.username).equals(account.userName)) && ((this.password).equals(account.password)))
	    	{
	    		return account;
	    	}
		}
	    return null;

	}

	// Checking for valid Login Credentials of a Customer
	
	Seller areValidSellerCredentials()
	{
		Seller account = new Seller();
		LinkedList<Seller> sellers = Seller.getAllSellerDetails();
		for (int i = 0; i < sellers.size(); i++) 
		{
			account = sellers.get(i);
			if(((this.username).equals(account.userName)) && ((this.password).equals(account.password)))
			{
				return account;
			}
		}
		return null;
	}

	
}
