//package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

class Seller extends Account implements AccountManage
{
	String businessName;
	
	final private static String sellerFolder = "SellerFiles/";
	final private static String sellerAccountDetailsFile = "SellerFiles/SellerAccountDetails.txt";
	
	Seller(String uName, String pWord,String Name, String mNo, String eml, String adrs, String bName)
	{
		super(uName, pWord, Name, mNo, eml, adrs);
		businessName = bName;
	}
	
	Seller()
	{
		super("default","password","default","9999999999","abc@xyz.com","default");
		businessName = "default";
	}
		
	//Getting User Details to create a new Account

	static Seller inputSellerDetails()
	{
		String name, mobileNo, address, email, userName, password, bName;
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
		
		while (true)
		{
			try
			{
				System.out.print("Enter your User Name : ");
				userName = sc.nextLine();
				if(isValidUserName(userName))
				{
					if(noDuplicateUsername(getAllSellerDetails(),userName))
					{
						break;
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
		
		while (true)
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
		
		//Company Name
		
		while(true)
		{
			try
			{
				System.out.print("\n\nEnter your Company Name : ");
				bName = sc.nextLine();
				break;
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\nWrong Input!!\n\nTry again.\n\n");
			}
		}

        return new Seller(userName, password, name, mobileNo, email, address, bName);
	}

	//Profile
	
	void Profile()
	{
		super.Profile();
		System.out.println("Company : " + this.businessName);
		System.out.println("\n\n");
	}
	
	//Checking for any duplicate user name
	
	static boolean noDuplicateUsername(LinkedList<Seller> sellers, String uName)
	{
		if(sellers == null)
		{
			return true;
		}
        for (Seller seller : sellers) {
            if (seller.userName.equals(uName)) {
                return false;
            }
        }
		return true;
	}

	//Function to write Seller Details to file
	
	private void printDetails(PrintWriter pw,Seller acc)
	{
		pw.println(acc.userName);
		pw.println(acc.password);
		pw.println(acc.name);
		pw.println(acc.mobileNo);
		pw.println(acc.email);
		pw.println(acc.address);
		pw.println(acc.businessName);
		pw.println();
	}
	
	// Adding Account Details to file
		
	public void addAccount()
	{
		Seller acc = inputSellerDetails();
		File fp = openFile(sellerAccountDetailsFile);
		try
		{
			fp.getParentFile().mkdirs();
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
			System.out.println(e.getMessage());
		}
		System.out.println("\n\nAccount successfully created!!\n\n");
	}

	//Returning all Seller Details
	
	static LinkedList<Seller> getAllSellerDetails()
	{
		LinkedList<Seller> sellers = new LinkedList<Seller>();
		File fp = openFile(sellerAccountDetailsFile);
//		File fp = this.openFile();
		if((!fp.exists()) || (fp.length() == 0))
		{
			return null;
		}

		try
		{
			Scanner sellerReader = new Scanner(fp);
			while(sellerReader.hasNextLine())
			{
				Seller account = new Seller();
				account.userName = sellerReader.nextLine();
				account.password = sellerReader.nextLine();
				account.name = sellerReader.nextLine();
				account.mobileNo = sellerReader.nextLine();
				account.email = sellerReader.nextLine();
				account.address = sellerReader.nextLine();
				account.businessName = sellerReader.nextLine();
				sellerReader.nextLine();
				sellers.add(account);
			}
			sellerReader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("\n\nFile not found.\n\nTry Again!!\n\n");
			e.printStackTrace();
		}

		return sellers;
	}

	//Opening file of particular Customer
	
	static File openFile(Seller account)
	{
		String path = sellerFolder + account.userName + ".txt";
		File fp = new File(path);
		fp.getParentFile().mkdirs();
		return (fp);
	}

	static File openFile(String path)
	{
//		String path = sellerFolder + this.userName + ".txt";
		File fp = new File(path);
		fp.getParentFile().mkdirs();
		return (fp);
	}

	//Function to add Item to Seller file
	
	void addItem(Item item)
	{
		File fp = openFile(this);
		try(FileWriter fw = new FileWriter(fp,true))
		{ 
			PrintWriter pw = new PrintWriter(fw);
			Item.printItemDetails(pw,item);
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("\n\nCould not open file.\n\nTry Again\n\n");
		}
	}

	//Menu on a successful Login
	
	public void successfulLoginMenu()
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while(choice != 6)
		{
			while (true) 
			{
				System.out.println("\n\n1.Add Item");
				System.out.println("2.Search Item Details");
				System.out.println("3.Update Item Details");
				System.out.println("4.Profile");
				System.out.println("5.Exit");
				try 
				{
					choice = Assistance.intInput(sc);
					break;
				} 
				catch (InputMismatchException e) 
				{
					System.out.println("\n\nWrong Input!!\n\nTry Again!!\n\n");
				}
				sc.nextLine();
			}
			switch(choice)
			{
				case 1:
				{
					try{
						this.addItem(Item.inputItemDetails(this));
					}
					catch(Exception e){
						//
					}
					break;
				}
				case 2:
				{
					LinkedList<Item> items = this.searchMenu(sc);
					if(items.isEmpty()) {
						System.out.println("\n\nCould not find the item!!\n\n");
					}
					else {
						for(int i = 0; i < items.size(); i++){
							if(items.get(i) != null){
								items.get(i).display();
							}
						}
					}
					break;
				}
				case 3:
				{
					this.updateMenu(sc);
					break;
				}
				case 4:
				{
					this.Profile();
					break;
				}
				case 5:
				{
					System.out.println("\n\nThank You\n\n");
					System.exit(0);
				}
				default:
				{
					System.out.println("\n\nInvalid Input!!\n\n");
					break;
				}
			}			
		}
		
		//To be Continued...
	}

	//Search for a seller
	
	static Seller searchByUserName(String uName)
	{
		try{
			LinkedList<Seller> sellers = getAllSellerDetails();
			for (Seller seller : sellers) {
				if (seller.userName.equals(uName)) {
					return seller;
				}
			}
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}

	//Search Menu
	
	LinkedList<Item> searchMenu(Scanner sc)
	{
		System.out.println("\n\nHow would you like to search : ");
		System.out.println("\n\n1.Item Code");
		System.out.println("2.Item Name");
		int choice = Assistance.intInput(sc);
		switch(choice)
		{
			case 1:
			{
				System.out.print("Enter Item Code :   ");
				int code = Assistance.intInput(sc);
				return Item.searchItem(code, this);
			}
			case 2:
			{
				System.out.print("Enter Item Name :   ");
				String name = sc.nextLine();
				return Item.searchItem(name, this);
			}
			default:
			{
				System.out.println("\n\nInvalid Input!!\n\nTry Again!!\n\n");
				return null;
			}
		}
	}

	//Update Menu
	
	void updateMenu(Scanner sc)
	{
		System.out.println("\n\nFor updating any item,");
		System.out.println("What would you like update : ");
		LinkedList<Item> items = this.searchMenu(sc);
		int updateChoice = 0;
		Seller account = this;
		while(true)
		{
			System.out.println("1. Name");
			System.out.println("2. Brand");
			System.out.println("3. Quantity");
			System.out.println("4. Price");
			System.out.println("5. Discount");
			System.out.println("6. Categories");
			System.out.println("7. Specifications");
			System.out.println("8. Go Back");
			updateChoice = Assistance.intInput(sc);
			switch(updateChoice)
			{
				case 1:
				{
					System.out.println("Enter new name : ");
                    items.get(0).name = sc.nextLine();
					Item.updateItem(items.get(0),account);
					break;
				}
				case 2:
				{
					System.out.println("Enter new brand : ");
                    items.get(0).brand = sc.nextLine();
					Item.updateItem(items.get(0),account);
					break;
				}
				case 3:
				{
					System.out.println("Enter new quantity : ");
					int qty = 0;
					qty = Assistance.intInput(sc);
					items.get(0).quantity = qty;
					Item.updateItem(items.get(0),account);
					break;
				}
				case 4:
				{
					System.out.println("Enter new price : ");
                    items.get(0).price = sc.nextFloat();
					Item.updateItem(items.get(0),account);
					break;
				}
				case 5:
				{
					System.out.println("Enter new discount: ");
					int discount = 0;
					discount = Assistance.intInput(sc);
					items.get(0).discount = discount;
					Item.updateItem(items.get(0),account);
					break;
				}
				case 7:
				{
					//Item Specifications
					items.get(0).specifications.clear();
					System.out.println("\n\n!!!!!To end adding items, type \"end\"!!!!!\n\n");
					System.out.println("Enter Specifications : \n\n");
					int i = 1;
					while(true)
					{
						try
						{
							System.out.print(i++);
							items.get(0).specifications.addLast(sc.nextLine());
							if((items.get(0).specifications.get(items.get(0).specifications.size()-1)).equals("end"))
							{
								items.get(0).specifications.remove(items.get(0).specifications.size()-1);
								break;
							}
						}
						catch(InputMismatchException e)
						{
							System.out.println("\n\nInvalid Input!!\n\nTry Again\n\n");
						}
					}
					Item.updateItem(items.get(0),account);
					break;
				}
				case 6:
				{
					//Item type
					
					items.get(0).categories.clear();
					System.out.println("\n\n!!!!!To end adding Categories, type \"end\"!!!!!\n\n");
					System.out.println("Enter Categories :\n ");
					int i = 1;
					while(true)
					{
						try
						{
							System.out.print(i++ + " ");
							String line = sc.nextLine();
							items.get(0).categories.addLast(line);
							if((items.get(0).categories.get((items.get(0).categories.size()-1))).equals("end"))
							{
								items.get(0).categories.remove((items.get(0).categories.size())-1);
								break;
							}
						}
						catch(InputMismatchException e)
						{
							System.out.println("\n\nInvalid Input!!\n\nTry Again\n\n");
						}
					}
					Item.updateItem(items.get(0),account);
					break;
				}
				case 8:
				{
					return;
				}
				default:
				{
					System.out.println("\n\nInvalid Input!!\n\nTry Again\n\n");
					break;
				}
			}
		}
		
	}
}