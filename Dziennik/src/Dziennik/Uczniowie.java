package Dziennik;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Uczniowie extends AddClass{
	 
	public Uczniowie(Action action) {
		super(action);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Tworzenie listy imion
	static ArrayList<String> lista_Imion = new ArrayList<String>(30); // to w <> zawsze z duzej zaczynamy 
	static ArrayList<String> lista_Nazwisk = new ArrayList<String>(30);
	static ArrayList<String> lista_Klas = new ArrayList<String>(3);	
	static Scanner sc = new Scanner(System.in);
	
	private boolean anyStudent = false;
	private static String imie;
	private static String nazwisko;
	private static String klasa;
	
	/**
	 * Static oznacza w³aœnie ¿e te pole przynale¿y do ca³ej klasy a nie do obiektu. 
	 * Takie pole mo¿e byc tylko JEDNO w ca³ej klasie a nie do ka¿ego obiektu 
	 * Postaæ tego jest taka: Uczniowie.nextId , a nie ¿e np . ( Uczniowie ucznen = new Uczniowie(); uczen.nextId. ) NOPE!
	 */
	private static int nextId; 
	private static int id;
	
	//Metody
	private static void zapytanie() 
	{
		System.out.println("Czy chcesz dodaæ ucznia? [tak/nie]");
		String odp = sc.nextLine();

		if (odp.equals("tak"))
				{
					pobierz_Personalia();
				}
		else System.out.println("Nie zawracaj gitary!");
	}
	
	private static void pobierz_Personalia() 
	{
		System.out.println("Podaj imie ucznia");
		String tempImie = sc.nextLine();
		System.out.println("Podaj nazwisko ucznia");
		String tempNazwisko = sc.nextLine(); 
		System.out.println("Podaj nazwe klasy do której przynale¿y uczeñ: ");
		String tempKlasa = sc.nextLine(); 
		dodaj_Ucznia(tempImie,  tempNazwisko,  tempKlasa);
	}
	
	private static void dodaj_Ucznia(String tempImie, String tempNazwisko, String tempKlasa) 
	{
		//imie = tempImie;
		//nazwisko = tempNazwisko;
		//klasa = tempKlasa;
		id = nextId;
		nextId++;
		lista_Imion.add(tempImie);
		lista_Nazwisk.add(tempNazwisko);
		//lista_Klas.add(tempKlasa);
	}
	
	private static void wypisz_Personalia_Ucznia() 
	{
		//Wypisanie wszystkich imion: for (String s:lista_Imion) System.out.println(s + "");
		//for (String s:lista_Imion) System.out.print(s + "");
		//for (String s:lista_Nazwisk) System.out.print(s + "");
		System.out.println(lista_Imion.get(0));
		System.out.println(id);
	}


	private static void wypisz_Ilosc_Uczniow() 
	{
	}

//	public int getId()
//	{
//		return id;
//	}

	public static void pobierz_Dane_Mysql()
	{
		try
		{
			//1. Get a connection with datasbse
			Connection myConn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "Klich");
			
			//2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			//3. Execute sql query
			ResultSet myRs = myStmt.executeQuery("select * from uczniowie");
			
			//4. Process the result set
			while (myRs.next()){
				System.out.println(myRs.getString("nazwisko") + ", " + myRs.getString("imie"));
			}
		}
		catch (Exception exc)
		{
			System.out.println("Blad: Uczniowie.pobierz_Dane_Mysql()");
		}
	}
	
	public static void main(String[] args) 
	{
		//pobierz_Dane_Mysql();
		
		
		
		//Tworzenie listy nazwisk
		//ArrayList<String> lista_Nazwisk = new ArrayList<String>(30); // to w <> zawsze z duzej zaczynamy 
 	
//		zapytanie();
//		pobierz_Personalia();
//		wypisz_Personalia_Ucznia();	
//		wypisz_Ilosc_Uczniow();
//		Uczniowie u = new Uczniowie();
//		u.getId();
	
		/**
		 *  w list równie¿ indeksowanie jest od zera:
		 * 	lista_Imion.add(0, "Jan");
		 *  spowoduje zamienienie i dodanie na drugim miejscu w liscie(bo indeksowanie od zera) slowa Jan.
		 */
		
	} // end main

	
	/**
	 *  na koñcu utworzymy blok "static" do "nextId"
	*/
	static 
	{
		
		nextId= 1; // wspólny dla Ca³ej klasy licznik który ulega inkrementacji podczas dodawania ka¿dego nowego obiektu , w tym wypadku "ucznia"
	}
	
} // end public Uczniowe()
