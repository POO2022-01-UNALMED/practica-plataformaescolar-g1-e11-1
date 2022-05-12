package interface_impl;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException 
	{
		
		while(true)
		{
			System.out.println("Identify yourself: \n");
			System.out.println("Are you a teacher or an student? \n");
			
			System.out.print("1. Student\n");
			System.out.println("2. Teacher\n");
			System.out.println("3. I don't have an account.\n");
			
			System.out.print("Enter an option: ");
			
			int opt = System.in.read();
			
			switch (opt)
			{
				case 1:
					System.out.println("Log in as a student.");
					break;
				case 2:
					System.out.println("Log in as a professor: ");
					break;
				default:
					System.out.println("Invalid option, please re-enter your option.");
					break;
			}
			
			
		}
		
	}

}
