//package project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerMain 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = 0;
		while(n != 3) 
		{
			while(true)
			{
				System.out.println("1.Login/Sign Up");
				System.out.println("2.Explore Menu");
				System.out.println("3.Exit");
				try 
				{
					n = sc.nextInt();
					sc.nextLine();
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("\n\nWrong Input!\n\nTry Again\n\n");
				}
			}
			
			login_signUp :
			switch(n)
			{
				case 1:
				{
					int c = 0;
					while(true)
					{
						System.out.println("1.Login");
						System.out.println("2.Sign Up");
						System.out.println("3.Exit");
						try 
						{
							c = sc.nextInt();
							sc.nextLine();
							break;
						}
						catch(InputMismatchException e)
						{
							System.out.println("\n\nWrong Input!\n\nTry Again\n\n");
						}
					}

					switch(c)
					{
					
						case 1:
						{
							Login id = Login.getCredentials();
//							System.out.println("Got Credentials");
							Customer account = id.areValidCustomerCredentials();
							if(account == null)
							{
								System.out.println("\n\nAccount not found.\n\nTry Again!!!\n\n");
								break;
							}
							else
							{
								System.out.println("\n\nLogin Successful\n\n");
//								File file = account.openFile();
//								Stocks.ExploreItemMenu(account);
								account.successfulLoginMenu();
							}
							break;
						}
						case 2:
						{
							Customer acc = new Customer();
							acc.addAccount();
							break login_signUp;
						}
						case 3:
						default:
						{
							break login_signUp;
						}
					}
					break;
				}
				case 2:
				{
					Stocks.ExploreItemMenu();
					break;
				}
				case 3:
				{
					sc.close();
					System.out.println("Exited");
					System.exit(0);
				}
				default:
				{
					System.out.println("\n\nWrong Input in Menu\n\n");
					break;
				}
			}
		}
	}
}