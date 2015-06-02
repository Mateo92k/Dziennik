package Dziennik;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Student {
	int lp;
	static String imie;
	String nazwisko;
	double[] oceny;
	double sredniaOcen;
	double ocenaProponowana;
	double ocenaKoncowa;
	 
	
 	String nazwaZalogowanego = "XYZ ZXY";
	static int iloscStudentowWybranejKlasy = 30;
	static Student student[] = new Student[iloscStudentowWybranejKlasy];
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
            public void run() 
            {  
            	Student sss = new Student();
            	sss.getStudentFirstName(2);
            }
        });
	}
	
	String getStudentFirstName(int indexImienia)
	{
		List<String> list;
		try {
			list = Files.readAllLines(Paths.get("C:/dziennik/users/Michal Klich/studenci.txt" ), StandardCharsets.UTF_8);
		
		String[] personaliaUczniow = list.toArray(new String[list.size()]);
		int dl = personaliaUczniow.length;
		String[] imiona = new String[dl];
		String[] nazwiska = new String[dl];
		
		int indexRequest = indexImienia;
		for(int i = 0;i<dl;i++)
		{
			String string = personaliaUczniow[i];
			String[] parts = string.split(" ");
			imiona[i] = parts[0];   
			nazwiska[i] = parts[1];  
			student[i].imie = parts[0];
			System.out.println("Imie nr " + i + " to: " + student[i].imie );
			 
		}
		return imiona[indexRequest]; 
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Blad metody getStudentFirstName: " + e);
		}
		
		return null;
		
		
	}
}
