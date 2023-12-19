//package project;

interface AccountManage
{
	void addAccount();
	void successfulLoginMenu();
}

class Account
{
	String userName, password, name, mobileNo, email, address ;

	Account(String uName,String pWord, String Name, String mNo, String eml, String adrs)
	{
		userName = uName;
		password = pWord;
		name = Name;
		mobileNo = mNo;
		email = eml;
		address = adrs;
	}

	//Checking for a valid Mobile Number

	static boolean isValidMobileNo(String mNo)
	{
		if(mNo.length() < 10)
		{
			return false;
		}
		for (int i = 0; i < mNo.length(); i++)
		{
			if(!Character.isDigit(mNo.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	// Checking for a valid UserName
	
	static boolean isValidUserName(String uName)
	{
		for(int i = 0;i < uName.length();i++)
		{
			if((!Character.isAlphabetic(uName.charAt(i))) && (!Character.isDigit(uName.charAt(i))) && (uName.charAt(i) != '_'))
			{
                return i == (uName.length() - 1) && (uName.charAt(i) == ' ');
            }
		}
		
		return true;
	}
	
	//Checking for valid Password
	
	static boolean isValidPassword(String pword)
	{
		if(pword.length() < 8)
		{
			return false;
		}
		for(int i = 0;i < pword.length();i++)
		{
			if(pword.charAt(i) == ' ')
			{
				return false;
			}
		}
		
		return true;
	}

	//Profile
	
	void Profile()
	{
		System.out.println("\n\n");
		System.out.println("User Name     : " + this.userName);
		System.out.println("Name          : " + this.name);
		System.out.println("Mobile Number : " + this.mobileNo);
		System.out.println("Email ID      : " + this.email);
		System.out.println("Address       : " + this.address);
	}

}