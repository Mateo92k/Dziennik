package Dziennik;

public class LoadData 
{
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
			 
			} 
		});
	}

	protected void takeData(String userFolderName, String userName, String userSurName, String[] allExistClass, String[] teachingClass, String[] pupilClass) { 
		//Pobierz nazwê folderu user
		String nazwaFolderuUzytkownika = userFolderName;
		if(userFolderName!=null)
		{
			GuiMain gm = new GuiMain();
			System.out.print(gm.getClass());
		}
		//Pobierz imie usera
		
		//Pobierz nazwisko usera
		
		//Pobierz wszystkie klasy jakie s¹ w programie
		
		//Pobierz wszystkie klasy których uczy uzytkownik
		
		
		
	}
}
