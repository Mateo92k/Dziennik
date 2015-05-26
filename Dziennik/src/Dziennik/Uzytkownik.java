package Dziennik;

public class Uzytkownik {
	
	private String imie, nazwisko;
	
	//metoda setImie pobiera zmienn¹ i ale nic nie zwraca. Ustawia pole imie
	public void setImie(String i)
	{
		imie = i; 
	}
	
	public String getImie()
	{
		return imie;
	}
	
	public static void main(String[] args)
	{
		Uzytkownik uzytkownikJeden = new Uzytkownik();
		uzytkownikJeden.imie = "Michal";
		System.out.println("Imie uzytkownika " + uzytkownikJeden + " to:" + uzytkownikJeden.getImie());
	}

}
