//package project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Assistance 
{
	static int intInput(Scanner sc)
	{		
		while (true)
		{
			try 
			{
				int n;
				n = sc.nextInt();
				sc.nextLine();
				return n;
			} 
			catch (InputMismatchException e) 
			{
				sc.nextLine();
				System.out.println("\n\nWrong Input!\n\nTry Again\n\n");
//				intInput(sc);
			} 
		}
	}
}
