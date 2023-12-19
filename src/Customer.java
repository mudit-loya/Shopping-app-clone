//package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

//Class for Customer

class Customer extends Account implements AccountManage
{
	LinkedList<Item> orders;
	LinkedList<Item> cart;

	final private String customerFolder = "CustomerFiles/";
	final private static String customerAccountDetailsFile = "CustomerFiles/CustomerAccountDetails.txt" ;

	Customer( String uName, String pWord, String Name, String mNo, String eml, String adrs) 
	{
		super(uName, pWord, Name, mNo, eml, adrs);
		orders = new LinkedList<Item>();
		cart = new LinkedList<Item>();
	}

	Customer()
	{
		super("default","password","default","9999999999","abc@xyz.com","default");
		orders = new LinkedList<Item>();
		cart = new LinkedList<Item>();
	}

	//Getting User Details to create a new Account

	static Customer getUserDetails()
	{
		String name, mobileNo, address, email, userName, password;
		Scanner sc = new Scanner(System.in);

		//Name

		while(true)
		{
			try
			{
				System.out.print("\n\nEnter your Name : ");
				name = sc.nextLine();
				break;
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\nWrong Input!!\n\nTry again.\n\n");
			}
		}

		//Mobile Number

		while(true)
		{
			try
			{
				System.out.print("Enter your Mobile Number : ");
				mobileNo = sc.next();
				sc.nextLine();
				if(isValidMobileNo(mobileNo))
				{
					break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\nWrong Input!!\n\nTry again.\n\n");
			}
		}

		//Address

		while(true)
		{
			try
			{
				System.out.print("Enter your Address : ");
				address = sc.nextLine();
				break;
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\nWrong Input!!\n\nTry again.\n\n");
			}
		}

		//Email ID

		while(true)
		{
			try
			{
				System.out.print("Enter your Email ID : ");
				email = sc.next();
				sc.nextLine();
				break;
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\nWrong Input!!\n\nTry again.\n\n");
			}
		}

		//User Name

		userName :
			while (true)
			{
				try
				{
					System.out.print("Enter your User Name : ");
					userName = sc.nextLine();
					if(isValidUserName(userName))
					{
						if(noDuplicateUsername(getAllCustomerDetails(),userName))
						{
							break userName;
						}
						else
						{
							System.out.println("\n\nUser name taken!!\n\nTry another user name!!\n\n");
						}
					}
				}
				catch (InputMismatchException e)
				{
					System.out.println("\n\nWrong Input!!\n\nTry again.\n\n");
				}
			}

		//Password

		while(true)
		{
			try
			{
				System.out.print("Create your Password : ");
				password = sc.nextLine();
				if(isValidPassword(password))
				{
					while(true)
					{
						System.out.print("Re-enter your Password : ");
						String rePass = sc.nextLine();
						if(rePass.equals(password))
						{
							break;
						}
					}
					break;
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("\n\nWrong Input!!\n\nTry again.\n\n");
			}
		}

		Customer acc = new Customer(userName, password, name, mobileNo, email, address);
		return acc;
	}

	//Checking for any duplicate user name

	static boolean noDuplicateUsername(LinkedList<Customer> customers,String uName)
	{
		if(customers == null)
		{
			return true;
		}
		for (int i = 0; i < customers.size(); i++) 
		{
			if(customers.get(i).userName.equals(uName))
			{
				return false;
			}
		}
		return true;
	}

	//Function to write Customer details in file

	private void printDetails(PrintWriter pw,Customer acc)
	{
		pw.println(acc.userName);
		pw.println(acc.password);
		pw.println(acc.name);
		pw.println(acc.mobileNo);
		pw.println(acc.email);
		pw.println(acc.address);
		pw.println();
		pw.println("Orders : ");
		for (int i = 0; i < acc.orders.size(); i++) 
		{
			pw.println(acc.orders.get(i).name);
			pw.println(acc.orders.get(i).sellerName);
			pw.println(acc.orders.get(i).code);
		}
		pw.println();
		pw.println("Cart : ");
		for (int i = 0; i < acc.cart.size(); i++) 
		{
			pw.println(acc.cart.get(i).name);
			pw.println(acc.cart.get(i).sellerName);
			pw.println(acc.cart.get(i).code);
		}
		pw.println();
		pw.println();
	}

	// Adding Account Details to file

	public void addAccount()
	{
		Customer acc = getUserDetails();
		File fp = new File(customerAccountDetailsFile);
		try 
		{
			FileWriter fw = new FileWriter(fp, true);
			try(PrintWriter pw = new PrintWriter(fw))
			{
				printDetails(pw,acc);
			}
			fw.close();
		} 
		catch (IOException e) 
		{
			System.out.println("\n\nSorry! Some error occured!\n\nTry Again!!\n\n");
		}
		System.out.println("\n\nAccount successfully created!!\n\n");
	}

	//Returning all Customer details

	static LinkedList<Customer> getAllCustomerDetails()
	{
		LinkedList<Customer> customers = new LinkedList<Customer>();
		File fp = new File(customerAccountDetailsFile);
		if((!fp.exists()) || (fp.length() == 0))
		{
			return null;
		}
		try
		{
			Scanner customerReader = new Scanner(fp);
			try {
				while(customerReader.hasNextLine())
				{
					Customer account = new Customer();
					account.userName = customerReader.nextLine();					//User Name
					account.password = customerReader.nextLine();					//Password
					account.name = customerReader.nextLine();						//Name
					account.mobileNo = customerReader.nextLine();					//Mobile Number
					account.email = customerReader.nextLine();						//Email Address
					account.address = customerReader.nextLine();					//Address
					customerReader.nextLine();										//Blank Line
					customerReader.nextLine();										//word "Orders :"
					while(true)
					{
						String name = customerReader.nextLine();
						if(!name.equals(""))
						{
							String sellerName = customerReader.nextLine();
							int code = customerReader.nextInt();
							customerReader.nextLine();
							account.orders.add(Item.searchItem(code, name, sellerName));
							if(account.orders.size() == 0)
							{
								System.out.println("Zero size");
							}
						}
						else
						{
							break;
						}
					}
					customerReader.nextLine();											//Word "Cart : "
					while(true)
					{
						String name = customerReader.nextLine();
						if(!name.equals(""))
						{
							String sellerName = customerReader.nextLine();				// Reading Seller Name
							int code = customerReader.nextInt();						// Reading Item Code
							customerReader.nextLine();									// Blank Line 		
							Item item = Item.searchItem(code, name, sellerName);		// Searching for the Item
							account.cart.add(item);
						}
						else
						{
							break;
						}
					}
					customerReader.nextLine();
					customers.add(account);
				}
			} 
			catch (NoSuchElementException e) 
			{
				System.out.println("\n\nNo Such Element Exceptiion!!\n\n");
			}
			customerReader.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("\n\nFile not found.\n\nTry Again!!\n\n");
			e.printStackTrace();
		}
		

		return customers;
	}

	//Function to open file of a particular customer

	File openFile()
	{
		String path = customerFolder + this.userName + ".txt";
		return (new File(path));
	}

	//Printing all Customer Details to File

	void printCustomerDetailsinFile(LinkedList<Customer> accounts)
	{
		File file = new File(customerAccountDetailsFile);
		try(FileWriter fw = new FileWriter(file))
		{
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < accounts.size(); i++) 
			{
				printDetails(pw, accounts.get(i));
			}
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("\n\nCould not open File for updating.\n\nSorry!!\n\n");
		}
	}

	//Function to update CustomerAccountDetailsFile every time any changes occur

	void updateCustomerDetails(Customer account)
	{
		LinkedList<Customer> customers = getAllCustomerDetails();
		for (int i = 0; i < customers.size(); i++) 
		{
			if(account.userName.equals(customers.get(i).userName))
			{
				customers.remove(i);
				customers.add(i,account);
			}
		}
		printCustomerDetailsinFile(customers);
	}

	//Ordering a item

	void orderItem(Item item) 
	{
		System.out.println("Enter the quantity you wish to order : ");
		Scanner sc = new Scanner(System.in);
		int qty = 0;
		qty = Assistance.intInput(sc);
		if(qty > item.quantity)
		{
			System.out.println("\n\nSorry! Not much stock available!\n\nTry again later!!\tor\tTry decreasing the quantity!!\n\n");
			return;
		}
		else 
		{
			this.orders.add(item);
			item.quantity -= qty;
			updateCustomerDetails(this);
			Item original = new Item(item);
			Seller account = Seller.searchByUserName(item.sellerName);
			Item.updateItem(original, account);
			System.out.println("Item Ordered Successfully\n");
		}
	}

	//Adding to Cart

	void addToCart(Item item)
	{
		this.cart.add(item);
		updateCustomerDetails(this);
		System.out.println("\n\nItem added to cart!!\n\n");
	}

	//Viewing orders

	void viewOrders()
	{
		Customer account = Customer.searchByUserName(this.userName);
		Stocks.showAllItems(account.orders);
	}

	//View Cart

	void viewCart()
	{
		Customer account = Customer.searchByUserName(this.userName);
		Stocks.showAllItems(account.cart);
	}

	//Search for a seller

	static Customer searchByUserName(String uName)
	{
		LinkedList<Customer> customers = getAllCustomerDetails();
		for (int i = 0; i < customers.size(); i++) 
		{
			if(customers.get(i).userName.equals(uName))
			{
				return customers.get(i);
			}
		}
		return null;
	}

	//Menu on a successful Login

	public void successfulLoginMenu()
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while(choice != 5)
		{
			while(true)
			{
				System.out.println("1.Search for a item");
				System.out.println("2.Browse Items");
				System.out.println("3.View Orders");
				System.out.println("4.View Your Cart");
				System.out.println("5.Profile");
				System.out.println("6.Exit\n");
				try
				{
					choice = sc.nextInt();
					sc.nextLine();
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("\n\nWrong Input!!\n\nTry Again!!\n\n");
				}
			}
			switch(choice)
			{
				case 1:
				{
					Stocks.SearchItems();
					break;
				}
				case 2:
				{	
					Stocks.ExploreItemMenu(this);
					break;
				}
				case 3:
				{
					this.viewOrders();
					break;
				}
				case 4:
				{
					this.viewCart();
					break;
				}
				case 5:
				{	
					this.Profile();					
					break;
				}
				case 6:
				{
					System.out.println("\n\n\n\n\t\t!!!!!THANK YOU!!!!!");
					System.exit(0);
				}
				default:
				{
					System.out.println("\n\nInvalid Input!!\n\nTry Again!!\n\n");	
					break;
				}
			}

		}

	}

}
