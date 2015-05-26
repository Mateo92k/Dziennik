package Dziennik;

public class Uczen {

	private int id;
	private String imie;
	private String nazwisko;
	private String pesel;
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getImie() {
        return imie;
    }
	
    public void setImie(String imie) {
        this.imie = imie;
    }
	
    public String getNazwisko() {
        return nazwisko;
    }
    
    public void setNazwisko(String nazwisko)
    {
    	this.nazwisko = nazwisko;
    }
	
    public Uczen(){}
    public Uczen(int id, String imie, String nazwisko, String pesel)
    {
    	
    }
}
