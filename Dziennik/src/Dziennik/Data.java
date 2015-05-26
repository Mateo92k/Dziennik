package Dziennik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Data {
	public static final void main(String[] args) throws InterruptedException 
	{

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
		public void run()
			{
			 LoadData loadData = new LoadData();
			 loadData.takeData(null, null, null, args, args, args);
			 setLogedUser("jakis user");
			}
		
	 

		public void setLogedUser(String FullName) 
		{
			String LogedUser = FullName;
			  
			File activeUserFolder = new File("c:\\dziennik\\data\\activeUser");
			//jesli folder 'zalogowanyUser' nie istnieje stworz go
			  if (!activeUserFolder.exists()) 
    		  {
				  activeUserFolder.mkdir();
    		  } 
			  
			  File fActiveUserTxt = new File("C:/dziennik/data/activeUser/activeUser.txt.txt");
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fActiveUserTxt, false))))
				{
				    out.println(LogedUser);  
				    System.out.println("Udalo sie dodac klase(nowa linijke do pliku txt");
					JOptionPane.showMessageDialog(null, "Dodano nazwe klasy pomyœlnie.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		} 
		 
		});
		
		
	}

	protected static String getUserName(String name) 
	{
		String fullname = name;
		return fullname;  
	}
	
	protected static String getUserDir(String name) 
	{
		File fUserDir = new File("c:\\dziennik\\users\\"+name);
		String sciezkaKataloguUzytkownika = fUserDir.toString();
		return sciezkaKataloguUzytkownika;  
	}
	
	//wydobyj klasy ktore uczy dany uzytkownik
	protected static String[] getUserClass(String name) 
	{
		File fUserDir = new File("c:\\dziennik\\users\\"+name);

		if(fUserDir.exists())
		{
			File fUserClass = new File("c:\\dziennik\\users\\"+name+"\\uczyKlasy.txt.txt");
			if(fUserClass.exists())
			{
				 if(fUserClass.length()!=0)
   				  try {
   					
   					//oblicz ile uzytkownik ma klas(czyli  ile wierszy jest w pliku uczyKlasy.txt)
   					int liczbaKlas=0;
   					//BufferedReader in = new BufferedReader(new FileReader(fKlasy.toString()));
   					BufferedReader in = new BufferedReader(new FileReader("C:\\dziennik\\users\\" + name + "\\uczyKlasy.txt.txt"));
   					while (in.readLine() != null) liczbaKlas++;
   					//in.close();
   					System.out.println("Plik ma lini: " + liczbaKlas);
   					
   					//stworz zmienna do przechowywania klas
   					String[] uczyKlasy = new String[liczbaKlas]; 
   					System.out.println("Stworzono zmienna 'string uczyKlasy'");
   					
   					//Dodaj klasy z pliku txt do zmiennej tablicowej String
						Scanner fileIn = new Scanner(new File("c:\\dziennik\\users\\"+ name + "\\uczyKlasy.txt.txt"));
						System.out.println("Stworzono zmienna 'Scanner fileIn '");
						
						List<String> list = new ArrayList<String>();
					 
						System.out.println("Stworzono zmienna 'List<String> lines ' i 'line'");
						
						//stara petla: 
						while (fileIn.hasNext()) 
						{  
							list.add(fileIn.next()); 
					    }
				   		System.out.println("Lista klas" + Arrays.toString(list.toArray()));	
						fileIn.close();
						 
						try{
							uczyKlasy = list.toArray(new String[list.size()]);
							System.out.println("udalo sie przekonwertowaæ");
							return uczyKlasy;
						}
						catch(Exception e3)
						{
							System.out.println("Nie udalo sie przekonwertowaæ");
						}
						System.out.println("przekonwertowano liste do tablicy'");
						
						for (int i = 0 ; i < uczyKlasy.length; i++)
						{
							System.out.println("Kontent: " + uczyKlasy[i]);
						}
						 
   				  } catch (FileNotFoundException e) {
   					  
						System.out.print("Plik jest pusty lub nieprawid³owy " + e);
   				  } catch (IOException e) {
   					  System.out.println("Blad podczas liczenia lini: " + e);
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		}
		 
		String sciezkaKataloguUzytkownika = fUserDir.toString();
		return null;
		 
	}
}
