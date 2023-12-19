//package project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SellerMain 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int n = 0;
		while(n != 2) 
		{
			while(true)
			{
				System.out.println("1.Login");
				System.out.println("2.Exit");
				try 
				{
					n = sc.nextInt();
					sc.nextLine();
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("\n\nWrong Input!\n\nTry Again!!\n\n");
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
							System.out.println("\n\nWrong Input!\n\nTry Again!!\n\n");
						}
					}

					switch(c)
					{
						case 1:
						{
							Login id = Login.getCredentials();
							Seller account = id.areValidSellerCredentials();
							if(account == null)
							{
								System.out.println("\n\nAccount not found.\n\nTRY Again!!!\n\n");
								break;
							}
							else
							{
								System.out.println("\n\nLogin Successful\n\n");
//								File file = account.openFile();
								account.successfulLoginMenu();
							}
							break;

						}
						case 2:
						{
							Seller acc = new Seller();
							acc.addAccount();
							break login_signUp;
						}
						case 3:
						{
							break login_signUp;
						}
						default:
						{
							break login_signUp;
						}
					}
					break;
				}
				case 2:	//Main Exit
				{
					System.exit(0);
					break;
				}
				default:
				{
					System.out.println("\n\nWrong Input!\n\n");
					break;
				}
			}
		}
		
	}
}
