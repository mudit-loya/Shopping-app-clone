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

class Item
{
	int code;
	String name,brand,sellerName;
	protected int quantity;
	int discount;
	float price, rating;
	LinkedList<String> categories;
	LinkedList<String> specifications;
	LinkedList<String> reviews;
	
	Item()
	{
		code = 0;
		name = "nothing";
		brand = "none";
		sellerName = "nothing";
		quantity = 0;
		discount = 0;
		price = 0;
		rating = 0;
		categories = new LinkedList<>();
		specifications = new LinkedList<>();
		reviews = new LinkedList<>();
	}

	Item(Item item)
	{
		code = item.code;
		name = item.name;
		brand = item.brand;
		sellerName = item.sellerName;
		quantity = item.quantity;
		discount = item.discount;
		price = item.price;
		rating = item.rating;
		categories = item.categories;
		specifications = item.specifications;
		reviews = item.reviews;
	}

	//Calculating Discounted Price

	float finalPrice(float price,int discount)
	{
        return price - (price * discount / 100);
	}

	//Display Item Details for 

	public void display()
	{
		System.out.println("\n\n");
		System.out.println("Brand : " + this.brand);
		System.out.println("Product : " + this.name);
		System.out.println("Price : ₹" + this.price);
		System.out.println("Discount : " + this.discount + "%");
		System.out.println("\n\nFinal Price : ₹" + finalPrice(this.price,this.discount) +"\n\n");
		System.out.println("Specifications :\n ");
        for (String specification : this.specifications) {
            System.out.println(specification);
        }
		
		if (!this.reviews.isEmpty())
		{
			System.out.println("\n\nReviews : \n");
            for (String review : this.reviews) {
                System.out.println(review+"\n");
            }
		}
		System.out.println("\n\n");
	}
	
	//Display Item Details 
	
	public void display(Customer account)
	{
		System.out.println("\n\n");
		System.out.println("Brand : " + this.brand);
		System.out.println("Product : " + this.name);
		System.out.println("Price : ₹" + this.price);
		System.out.println("Discount : " + this.discount + "%");
		System.out.println("\n\nFinal Price : ₹" + finalPrice(this.price,this.discount) +"\n\n");
		System.out.println("Specifications : ");
        for (String specification : this.specifications) {
            System.out.println(specification);
        }
		
		System.out.println("\n\nReviews : ");
        for (String review : this.reviews) {
            System.out.println(review);
        }
		System.out.println("\n\n");
		this.itemMenu(account);
	}
	
	//Menu for Individual Item
	
	void itemMenu(Customer account)
	{
		Scanner sc = new Scanner(System.in);
		int option = 0;
		itemMenu:
		while(true)
		{
			while(true)
			{
				System.out.println("What would you like to do ? ");
				System.out.println("\n");
				System.out.println("1.Order the Item");
				System.out.println("2.Add to Cart");
				System.out.println("3.Go Back\n");				
				try
				{
					option = sc.nextInt();
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("\n\nWrong Input!!\n\nTry Again!!\n\n");
				}
				sc.nextLine();
			}
			switch(option)
			{
				case 1:
				{
					account.orderItem(this);
					break;
				}
				case 2:
				{
					account.addToCart(this);
					break;
				}
				case 3:
				{
					break itemMenu;
				}
				default:
				{
					System.out.println("\n\nInvalid Input!!\n\nTry Again!!\n\n");
					break;
				}
			}
		}
		
	}

	//Menu for Individual Item
	
	void itemMenu()
	{
		Scanner sc = new Scanner(System.in);
		int option = 0;
		
		itemMenu:
		while(true)
		{
			while(true)
			{
				System.out.println("What would you like to do ? ");
				System.out.println("\n");
				System.out.println("1.Order the Item");
				System.out.println("2.Add to Cart");
				System.out.println("3.Go Back\n");				
				try
				{
					option = sc.nextInt();
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("\n\nWrong Input!!\n\nTry Again!!\n\n");
				}
				sc.nextLine();
			}
			switch(option)
			{
				case 1:
				case 2:
				{
					System.out.println("\t\tTo continue,please Log In\t\t");
					break;
				}
				case 3:
				{
					break itemMenu;
				}
				default:
				{
					System.out.println("\n\nInvalid Input!!\n\nTry Again!!\n\n");
					break;
				}
			}
		}
	}
	
	//Function to get item details 

	static Item inputItemDetails(Seller account) throws InterruptedException {
		LinkedList<Item> items = Stocks.getAllItemsFromSellerFile(account);
		Item item = new Item();
		item.sellerName = account.userName;
		Scanner sc = new Scanner(System.in);

		//Item Code
		
		while(true){
			System.out.print("\n\nEnter Item Code : ");
			item.code = Assistance.intInput(sc);
			boolean retry = false;
            for (Item value : items) {
                if (value.code == item.code) {
                    retry = true;
                    break;
                }
            }
			if(!retry) break;
			System.out.print("\n\nItem code already exists.!!!\n\n");
			Thread.sleep(1000);
			System.out.print("\033[2K");
			System.out.print("\033[2K");
			System.out.print("\033[2K");
		}

		//Item Name
		
		try
		{
			System.out.print("Enter Item Name : ");
			item.name = sc.nextLine();
		}
		catch(InputMismatchException e)
		{
			System.out.println("\n\nInvalid Input!!\n\nTry Again\n\n");
//			e.printStackTrace();
		}

		//Item Brand Name
		
		try
		{
			System.out.print("Enter Brand Name : ");
			item.brand = sc.nextLine();
		}
		catch(InputMismatchException e)
		{
			System.out.println("\n\nInvalid Input!!\n\nTry Again\n\n");
		}

		//Item type
		
		System.out.println("\n\n!!!!!To end adding Categories, type \"end\"!!!!!\n\n");
		System.out.println("Enter Categories :\n ");
		int i = 1;
		while(true)
		{
			try
			{
				System.out.print(i++ + ". ");
				String line = sc.nextLine();
				item.categories.addLast(line);
				if((item.categories.get((item.categories.size()-1))).equals("end"))
				{
					item.categories.remove((item.categories.size())-1);
					break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\nInvalid Input!!\n\nTry Again\n\n");
			}
		}
		
		//Item Quantity
		
		System.out.print("Enter Item Quantity : ");
		item.quantity = Assistance.intInput(sc);

		//Item Price
		
		while(true)
		{
			try
			{
				System.out.print("Enter Item Price : ");
				item.price = sc.nextFloat();
				break;
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\nInvalid Input!!\n\nTry Again\n\n");
			}
		}
		
		//Item Discount
		
		System.out.print("Enter Discount on Item : ");
		item.discount = Assistance.intInput(sc);
		
		//Item Specifications
		
		System.out.println("\n\n!!!!!To end adding specifications, type \"end\"!!!!!\n\n");
		System.out.println("\n\nEnter Specifications : \n\n");
		i = 1;
		while(true)
		{
			try
			{
				System.out.print(i++ + ". ");
				item.specifications.addLast(sc.nextLine());
				if((item.specifications.get(item.specifications.size()-1)).equals("end"))
				{
					item.specifications.remove(item.specifications.size()-1);
					break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\nInvalid Input!!\n\nTry Again\n\n");
			}
		}
		
		return item;
	}

	//Function to check if the item any particular characteristic
	
	boolean contain(String key)
	{
		if((this.name.contains(key)) || (this.brand.contains(key)))
		{
			return true;
		}
        for (String category : this.categories) {
            if (category.contains(key)) {
                return true;
            }
        }
        for (String specification : this.specifications) {
            if (specification.contains(key)) {
                return true;
            }
        }
		return false;
	}
	
	//Function to Item Details to file
	
	static void printItemDetails(PrintWriter pw,Item item)
	{
		pw.println(item.code);										//code
		pw.println(item.name);										//name
		pw.println(item.brand);										//brand
		pw.println(item.sellerName);								//sellerName
		pw.println(item.price);										//price
		pw.println(item.discount);									//discount
		pw.println(item.quantity);									//quantity
		pw.println(item.rating);									//rating
		
		pw.println("\nCategories");
		for(int i = 0; i < item.categories.size(); i++)
		{
			pw.println(item.categories.get(i));							//type
		}

		pw.println("\nSpecifications");
		for(int i = 0;i < item.specifications.size();i++)
		{
			pw.println(item.specifications.get(i));					//specifications
		}
		
		pw.println("\nReviews");
		for(int i = 0;i < item.reviews.size();i++)
		{
			pw.println(item.reviews.get(i));						//reviews
		}	
		
		pw.println("\n\n");
	}

	//Read Item details from file
	
	Item readItemDetailsFromFile(Scanner itemReader, Item item)
	{
		item.code = itemReader.nextInt();						//code
		itemReader.nextLine();
		item.name = itemReader.nextLine();						//name
		item.brand = itemReader.nextLine();						//brand
		item.sellerName = itemReader.nextLine();				//Seller Name
		item.price = itemReader.nextFloat();					//price
		itemReader.nextLine();
		item.discount = itemReader.nextInt();					//discount
		itemReader.nextLine();
		item.quantity = itemReader.nextInt();					//quantity
		itemReader.nextLine();
		item.rating = itemReader.nextFloat();					//rating
		itemReader.nextLine();

		itemReader.nextLine();
		itemReader.nextLine();                                  //"Categories" Word
		while(true)
		{
			String line = itemReader.nextLine();				//Categories
			if(!line.isEmpty())
			{
				item.categories.add(line);
			}
			else
			{
				break;
			}
		}
		
		itemReader.nextLine();									//"Specifications" word
		while(true)
		{
			String line = itemReader.nextLine();				//Specifications
			if(!line.isEmpty())
			{
				item.specifications.add(line);				
			}
			else
			{
				break;
			}
		}
		
		itemReader.nextLine();									//"Reviews" word
		while(true)
		{
			String line = itemReader.nextLine();				//Reviews
			if(!line.isEmpty())
			{
				item.reviews.add(line);				
			}	
			else
			{
				break;
			}
		}						
		itemReader.nextLine();
		itemReader.nextLine();
		return item;
	}
	
	//Update an item's details on ordering
	
	static void updateItem(Item item, Seller account)
	{
		LinkedList<Item> items = Stocks.getAllItemsFromSellerFile(account);
		for (int i = 0; i < items.size(); i++) 
		{
			if((items.get(i).name.equals(item.name)))
			{
				items.remove(i);
				items.add(i,item);
			}
		}
		Stocks.printAllItemsToASellerFile(account, items);
	}

	//Searching item
	
	static Item searchItem(int code,String name,String sellerName)
	{
		Seller seller = Seller.searchByUserName(sellerName);
		LinkedList<Item> items = Stocks.getAllItemsFromSellerFile(seller);
        for (Item item : items) {
            if ((item.code == code) && (item.name.equals(name)) && (item.sellerName.equals(sellerName))) {
                return item;
            }
        }
		System.out.println("Returning null in search");
		return null;
	}
	
	//Searching item
	
	static LinkedList<Item> searchItem(int code,Seller seller)
	{
		LinkedList<Item> items = Stocks.getAllItemsFromSellerFile(seller);
		LinkedList<Item> result = new LinkedList<>();
        for (Item item : items) {
            if ((item.code == code)) {
                result.add(item);
                return result;
            }
        }
		return null;
	}

	//Searching item
	
	static LinkedList<Item> searchItem(String name,Seller seller)
	{
		LinkedList<Item> items = Stocks.getAllItemsFromSellerFile(seller);
		LinkedList<Item> result = new LinkedList<>();
        for (Item item : items) {
            if ((item.name.contains(name))) {
                result.add(item);
//				return item;
            }
        }
		return result;
	}

}

public class Stocks 
{
	//Function to store every item in a Linked List in run time for faster working
	
	static LinkedList<Item> getAllItemsFromFiles()
	{
		LinkedList<Item> items = new LinkedList<>();
		LinkedList<Seller> sellers = Seller.getAllSellerDetails();
		for (int i = 0; i < sellers.size(); i++) 
		{
			Seller account = sellers.get(i);
//			File fp = new File("SellerFiles\\" + account.userName + ".txt");
			File fp = Seller.openFile(account);
			if(fp.exists())
			{
				try 
				{
					
					//Opening File with Items sold by Seller
					
					Scanner itemReader = new Scanner(fp);
					while(itemReader.hasNextLine())
					{
						Item item = new Item();
						items.addLast(item.readItemDetailsFromFile(itemReader,item));
					}
					itemReader.close();
				}
				catch(FileNotFoundException e)
				{
					System.out.println("\n\nFile not found.\n\nTry Again!!\n\n");
					e.printStackTrace();
				}
			}

		}

		return items;
	}
	
	//Function to store every item sold by particular seller, in a Linked List in run time for faster working
	
	static LinkedList<Item> getAllItemsFromSellerFile(Seller account)
	{
		LinkedList<Item> items = new LinkedList<>();
//		File fp = new File("SellerFiles\\" + account.userName + ".txt");
		File fp = Seller.openFile(account);
		if(fp.exists())
		{
			try 
			{
					
				//Opening File with Items sold by the Seller
					
				Scanner itemReader = new Scanner(fp);
				while(itemReader.hasNextLine())
				{
					Item item = new Item();
					items.add(item.readItemDetailsFromFile(itemReader,item));
				}
				itemReader.close();
			}
			catch(FileNotFoundException e)
			{
				System.out.println("\n\nFile not found.\n\nTry Again!!\n\n");
				e.printStackTrace();
			}
		}

		return items;
	}

	//Showing items asked to show
	
	static void showAllItems(LinkedList<Item> items)
	{
		for(int i = 0; i < items.size(); i++) 
		{
			System.out.println("\n\n");
			Item item = items.get(i);
			System.out.print((i+1) + " ");
			if(item == null)
			{
				System.out.println("Null");
			}
			System.out.print(item.brand+ " ");
			System.out.println(item.name);
			System.out.println("Original Price : " + item.price + "\nNow with " + item.discount + "% off");
			System.out.println("Final Price : " + item.finalPrice(item.price, item.discount));
			System.out.println("Rated : " + item.rating + "⭐");
			System.out.println("\n\n");
		}
	}
	
	//Explore Items for unauthorized user
	
	static void ExploreItemMenu()
	{
		try 
		{
			LinkedList<Item> items = getAllItemsFromFiles();
			try
			{
				showAllItems(items);
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter the number corresponding to item you would like to explore : ");
				int choice = sc.nextInt();
				sc.nextLine();
				Item item = items.get(choice - 1);
				item.display();	
			}
			catch(NoSuchElementException e)
			{
				System.out.println("\n\nNo Such Element Exception in showAllItems!!\n\n");
			}
		}
		catch (NoSuchElementException e) 
		{
			System.out.println("\n\nNo Such Element Exception occurred in getAllItemsFromFiles!!\n\n");
		}
	}

	//Explore Items for authorized user

	static void ExploreItemMenu(Customer account)
	{
		
		try 
		{
			LinkedList<Item> items = getAllItemsFromFiles();
			try
			{
				showAllItems(items);
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter the number corresponding to item you would like to explore : ");
				int choice = 0;
		
				Item item;
				
				while (true) 
				{
					try 
					{
						choice = Assistance.intInput(sc);
						item = items.get(choice - 1);
						break;
					}
					catch (IndexOutOfBoundsException e) 
					{
						// TODO: handle exception
					} 
				}
				item.display(account);	
			}
			catch(NoSuchElementException e)
			{
				System.out.println("\n\nNo Such Element Exception in showAllItems!!\n\n");
			}
		}
		catch (NoSuchElementException e) 
		{
			System.out.println("\n\nNo Such Element Exception occurred in getAllItemsFromFiles!!\n\n");
			return;
		}
		
	}

	//Searching for Item
	
	static void SearchItems()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter what you would like search : ");
		String key = sc.nextLine();
		LinkedList<Item> items = getAllItemsFromFiles();
		LinkedList<Item> itemsFound = new LinkedList<Item>();
		for (int i = 0; i < items.size(); i++) 
		{
			Item item = items.get(i);
			if(item.contain(key))
			{
				itemsFound.add(item);
			}
		}
		showAllItems(itemsFound);
	}
	
	//Printing all items again from a seller file, if any update occurred

	static void printAllItemsToASellerFile(Seller account,LinkedList<Item> items) 
	{
		File file = Seller.openFile(account);
		try
		{
			FileWriter fw = new FileWriter(file);
			try(PrintWriter pw = new PrintWriter(fw)) 
			{
                for (Item item : items) {
                    account.addItem(item);
                }
			}
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("\n\nCouldn't write back to File!!\n\n");
		}
	}
}
