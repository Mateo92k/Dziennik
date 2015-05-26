package Dziennik;

import java.io.ObjectInputStream.GetField;

import javax.swing.JTextField;
	
public class User {
	private final String firstName; // req
	private final String lastName; 	// req
	private final int age; 			//optional
	private final String phone; 	//optional

	public static void main(String[] args)
	{
		//	String m = "Michal";
		// UserBuilder ub1 = new UserBuilder(m, m);
		// ub1.getUser();
		  
		
		 //pobranie danych uzytkownika nowego
		 String userName= "Alex";
		 String userSurname = "Gorbatov";
		 
		 //jesli pola personalne nie sá puste stwórz u¿ytkownika:
		 if(userName!=null)
		 { 
			 UserBuilder user = new UserBuilder(userName, userSurname);
			 
			 
			 System.out.println("Uzytkownik: " + user.firstName);
		 }
		 
	}
	
	User(UserBuilder builder)
	{
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
	}
	
	public User(String userName, String userSurname, String p, int a) {
		this.age = a;
		this.phone = p;
		this.firstName = userName;
		this.lastName = userSurname;
		 
	}

	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getPhone()
	{
		return phone;
	}
	public int getAge()
	{
		return age;
	}
	
	public static class UserBuilder
	{
		private final String firstName; 
		private final String lastName;  
		private int age; 			 
		private String phone; 

		public UserBuilder(String firstName, String lastName)
		{
			this.firstName = firstName;
			this.lastName = lastName; 
		}
		
		public UserBuilder age(int age)
		{
			this.age = age;
			return this;
		}
		
		public UserBuilder phone(String phone)
		{
			this.phone = phone;
			return this;
		}
		
		public User build()
		{
			return new User(this);
		}
		
		public User getUser()
		{
			return new 
					User.UserBuilder("Michael", "Klic")
					.age(30)
					.phone("606505404")
					.build();
		}
	}
}
















