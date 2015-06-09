package Dziennik;

public class InfoOZalogowanym {
	private String imieUzytkownika;
	private String nazwiskoUzytkownika;
	private String imieInazwisko;
	private String sciezkaKataloguUzytkownika;
	private String[] listaKlasJakieUczy;
	private String[] listaWszystkichIstniejacychKlas;
	private String klasaKtoraWychowuje;
	
	public void setImieInazwisko(String imieInazwisko)
	{
		this.imieInazwisko = imieInazwisko;
		System.out.println("Test nr2.Iimie to: " + imieInazwisko);
	}
	
	public String getImieUzytkownika() {
	    return imieUzytkownika;
	  }
}
