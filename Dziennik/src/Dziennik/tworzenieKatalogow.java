package Dziennik;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class tworzenieKatalogow {
	
	File file;
	FileWriter fileWriter;
	public void stworzFolder(String sciezka)
	{
		File file = new File(sciezka);
		//file.mkdir();
		if(file.mkdir())
		{
			System.out.println("Stworzono: " + file);
		}
		else System.out.println("failed:stworzFolder");
	}

	public void stworzFolderzPelnaSciezka(String sciezka)
	{
		File file = new File(sciezka);
		//file.mkdir();
		if(file.mkdirs())
		{
			System.out.println("Stworzono: " + file);
		}
		else  System.out.println("failed:stworzFolderzPelnaSciezka");
	}

	
	public void stworzPlikTxt(String sciezka, String kontent)
	{
		File file = new File(sciezka);
		String wyrazenie = kontent;
			 try 
			 {
				 		//tworzy plik txt
				 		fileWriter = new FileWriter(file);
						PrintWriter printWriter = new PrintWriter(fileWriter);
						
						//Dodaj tekst do pliku nie usuwaj¹c zawartoœci obecnej w pliku
						try(PrintWriter output = new PrintWriter(new FileWriter(file,false))) 
					{ 
						//zapisywanie cos do pliku
					    output.printf("%s\r\n", wyrazenie);
					     
					}  
						
		 
				}
		 	catch (IOException e2) 
			{
		 		System.out.println("Scie¿ka do pliku nie istnieje, stwórz nadrzêdne foldery aby dzia³a³o prawid³owo.  " + e2);
				 
			}
			System.out.println("Zakonczono metode 'stworzPlikTxt'<-tworzenieKatalogow.java: " + file);
		}  
}
