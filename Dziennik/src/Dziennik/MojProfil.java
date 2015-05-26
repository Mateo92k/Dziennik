package Dziennik;
    
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
    
import java.util.Timer;
import java.util.Vector;

    import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import Dziennik.User.UserBuilder;
     
    public class MojProfil  {
    	  
		 

		private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj;
    	private static JLabel lblInformacje, lblRokSzkolny, lblAktualnyPrzedmiot, lblObecnychStudentow, lblSeparator,lblSeparator2, lblTime,lblDay, lblVersion;
     
    	String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    	String strDay = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
    	final String zalogowanoJako = "Zalogowano jako: ";
    	 
    	
    	public JMenuBar createJMenuBar()  
    	{
    		JMenuBar mainMenuBar;
    		JMenu menuPlik, menuWidok, menuNowy, menuPrzejdz, menuNarzedzia, menuPomoc;
    		JMenuItem NowyMenuItem, OtworzBazeMenuItem, ZapiszMenuItem, Odswie¿MenuItem, DrukujMenuItem, WylogujMenuItem, WyjdzMenuItem ;
    		
    		mainMenuBar = new JMenuBar();
    		menuPlik = new JMenu("Plik");
    		mainMenuBar.add(menuPlik);
    		
    		menuNowy = new JMenu("Nowy");
    		menuPlik.add(menuNowy);
    		
    		NowyMenuItem = new JMenuItem("Utwórz plik danych");
    		menuNowy.add(NowyMenuItem);
    		
    		ZapiszMenuItem = new JMenuItem("Zapisz");
    		menuPlik.add(ZapiszMenuItem);
    		menuPlik.addSeparator();

    		Odswie¿MenuItem = new JMenuItem("Odswie¿");
    		menuPlik.add(Odswie¿MenuItem);
    		menuPlik.addSeparator();

    		DrukujMenuItem = new JMenuItem("Drukuj..");
    		menuPlik.add(DrukujMenuItem);
    		menuPlik.addSeparator();

    		WylogujMenuItem = new JMenuItem("Wyloguj");
    		menuPlik.add(WylogujMenuItem);
    		
    		WyjdzMenuItem = new JMenuItem("Zapisz i WyjdŸ");	
    		menuPlik.add(WyjdzMenuItem);
    		
    		menuNarzedzia = new JMenu("Narzedzia");
    		mainMenuBar.add(menuNarzedzia);
    		
    		menuWidok = new JMenu("Widok");
    		mainMenuBar.add(menuWidok);
    		
    		menuPomoc = new JMenu("Pomoc");
    		mainMenuBar.add(menuPomoc);
    		return mainMenuBar;
    	}
 
    	void createGui(String personalia, boolean czyUzytkownikMaKlasy) 
    	{
    		 
    		personalia = "Micha³ Klich"; // usun¹æ, bo imie i nazwisko bedzie przekazane z logowania, a obecnie jest na sztywno
    		boolean czyIstniej¹Klasy = czyUzytkownikMaKlasy;
    		czyIstniej¹Klasy = false;
    		System.out.println("Czy u¿ytkownik ma klase: " + czyIstniej¹Klasy);
     
    		/**
    		* Ustawienia okna
    		*/   
    		JFrame frame = new JFrame("Dziennik Elektroniczny - Okienko startowe");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		MojProfil app = new MojProfil();
    		frame.setJMenuBar(app.createJMenuBar());
    		frame.setSize(1362, 724);
     		frame.setResizable(false);
     		wysrodkujOkno(frame);
    	    
     		JButton bSep = new JButton();
    		bSep.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
    		bSep.setOpaque(false);
    		bSep.setBorder(null);
    		bSep.setContentAreaFilled(false);
    		bSep.setBorderPainted(false);
    		
    		JButton bSep2 = new JButton();
    		bSep2.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
    		bSep2.setOpaque(false);
    		bSep2.setBorder(null);
    		bSep2.setContentAreaFilled(false);
    		bSep2.setBorderPainted(false);
    		
    		JButton bSep3 = new JButton();
    		bSep3.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
    		bSep3.setOpaque(false);
    		bSep3.setBorder(null);
    		bSep3.setContentAreaFilled(false);
    		bSep3.setBorderPainted(false);
    		
    		JButton bSep4 = new JButton();
    		bSep4.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
    		bSep4.setOpaque(false);
    		bSep4.setBorder(null);
    		bSep4.setContentAreaFilled(false);
    		bSep4.setBorderPainted(false);
    		
    		Color blueColor = new Color( 0, 0, 0);
    		Color hrefColor = new Color( 31, 69, 252);
     		
    		/**
     	    * Create Panel1
     		*/
    		JPanel p1 = new JPanel();
    		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS)); // ustawienie ikon w lini poziomej
    		p1.setBackground(Color.WHITE);
   
    		//tworzenie komponentow do panelu1
    		ImageIcon seppion = new ImageIcon("/res/seppion.png");
    		
    		JLabel lblLogo = new JLabel();
    		lblLogo.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logo4m.png")));
    		lblLogo.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                	System.out.println("Powrót do g³ównego okna"); 
                }
            });
    		
    		JLabel lblDaneZalogowanego = new JLabel(personalia);
    		lblDaneZalogowanego.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/usericonm.png")));
     

    		
    		JLabel lblMojProfil = new JLabel("Mój profil");
   			lblMojProfil.setForeground(hrefColor);
    		bWyloguj = new JButton("Wyloguj");
    		bWyloguj.setBackground(new Color(255, 255, 255));
    		bWyloguj.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logout.png")));
    		bWyloguj.setSize(30, 60);
    		bWyloguj.setBorder(null);
    		bWyloguj.setForeground(blueColor);
    		bWyloguj.addActionListener(new ActionListener() {			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				frame.dispose();
    				JOptionPane.showMessageDialog(null, "Wylogowano.");
    				Logowanie.runClassLogowanie();
    			}
    		});

	    	
    		p1.add(lblLogo); // dodanie logo z funkcj¹ maj¹ce funkcje powrotu do glownego okna kontekstowego
    		p1.add(Box.createHorizontalGlue());	// tworzenie orientacji obiektów od prawej do lewej

    		p1.add(Box.createRigidArea(new Dimension(	3,0)));
    		p1.add(Box.createRigidArea(new Dimension(1,0)));
    		p1.add(Box.createRigidArea(new Dimension(115,0)));
    		p1.add(bSep3);
    		p1.add(lblDaneZalogowanego);
    		p1.add(Box.createRigidArea(new Dimension(15,0)));
    		p1.add(bSep);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(lblMojProfil);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(bSep2);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(bWyloguj); // ikona pierwsza od konca 
    		
    		/** Create Panel2 */
     		JPanel p2 = new JPanel();
     		p2.setVisible(true);
    		p2.setBounds(1,61,1360,50);
    		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS)); // !! SKOPIOWAC to do panelu lewego - ZMIEN w kodzie po lewej "x-axis"na "Y-AXIS" aby zobaczyc o co chodzi.
    		p2.add(Box.createHorizontalGlue()); 
    		p2.setBackground(Color.DARK_GRAY);

    		//create component of Panel2. And add to Panel2
    		JButton btnProfil = new JButton("Mój Profil");
    		btnProfil.setBackground(Color.DARK_GRAY);
    		btnProfil.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logout.png")));// dodac ikone profilu
    		btnProfil.setSize(30, 60);
    		btnProfil.setBorder(null); 
    		btnProfil.setForeground(hrefColor); 
    		p2.add(btnProfil);
    		
    		JButton btnMojeKlasy = new JButton("Moje Klasy");
    		btnMojeKlasy.setBackground(Color.DARK_GRAY);
    		btnMojeKlasy.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logout.png")));// dodac ikone profilu
    		btnMojeKlasy.setSize(30, 60);
    		btnMojeKlasy.setBorder(null); 
    		btnMojeKlasy.setForeground(Color.white); 
    		p2.add(Box.createRigidArea(new Dimension(100,0)));
    		p2.add(btnMojeKlasy);
    		p2.add(Box.createRigidArea(new Dimension(600,0)));
   			/**
			*Tworzenie panelu "cetralnego"
			*/
    		JPanel pCenter = new JPanel();
    		pCenter.setBackground(new Color(242,242,242));	
    		pCenter.setLayout(null);  	
    			
    		/**
			*Zwiêkszenie daty o jeden dzien
			*/
			    	String untildate= strDay ;//can take any date in current format    
					SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );   
					//SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" ); 
					Calendar cal = Calendar.getInstance();    
					try 
					{
						cal.setTime( dateFormat.parse(untildate));
					} 
					catch (ParseException e) 
					{
						e.printStackTrace();
					}    
					cal.add( Calendar.DATE, 1 );    
					String convertedDate=dateFormat.format(cal.getTime());    
					  
					cal.add( Calendar.DATE, 1 );    
					String convertedDate2=dateFormat.format(cal.getTime());    
			// koniec kodu zmieniajacego date
			 
    		lblVersion = new JLabel("Dziennik Elektroniczny wersja 1.3");
    		lblTime = new JLabel("Godzina: " + strTime);
    		lblDay = new JLabel("Dzis jest:  " + strDay);
    		lblDay.setBounds(0,444,220,150);
    		lblVersion.setBounds(1130,444,250,150);
 		    lblTime.setBounds(640,444,250,150);
 			 
 		    
 			JScrollPane ss = new JScrollPane(); 
 			ss.getViewport().setBackground(Color.WHITE);
 			ss.setBounds(450, 100, 440, 440);
 			ss.setBackground(Color.white);
 		  
 			JLabel lblMojePersonalia = new JLabel(personalia);
 			
 			lblMojePersonalia.setBounds(588, 110, 300, 50);
 			lblMojePersonalia.setBackground(Color.red);
 			lblMojePersonalia.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/usericonm.png")));
 			
 			JLabel lblInfoDodatkowe = new JLabel("Informacje szkolne:");
 			lblInfoDodatkowe.setBounds(460, 160, 300, 50);
 			lblInfoDodatkowe.setBackground(Color.red);
 			
 			//pobierz z klasy person informacje o tym jakiej klasy jest wychowawc¹ i dodaj
 			JLabel lblWychowawca = new JLabel("Wychowawca: " + "");
 			lblWychowawca.setBounds(480, 180, 300, 50);
 			lblWychowawca.setBackground(Color.red);
 			
			JLabel lblNauczyciel = new JLabel("Nauczyciel klas: " + " ");
			lblNauczyciel.setBounds(480, 200, 300, 50);
			lblNauczyciel.setBackground(Color.red);
 			
			JLabel lblInfOsobowe = new JLabel("Informacje osobowe:");
			lblInfOsobowe.setBounds(460, 250, 300, 50);
			 
			
			JLabel lblZamieszka³y = new JLabel("Zamieszka³y" + " ");
			lblZamieszka³y.setBounds(480, 270, 300, 50);
	 
			
			JLabel lblTelefon = new JLabel("Telefon kontaktowy: " + " ");
			lblTelefon.setBounds(480, 290, 300, 50);
	 
			
			JLabel lblUrodzony = new JLabel("Urodzony dnia" + " 26 listopad 19884" + "  w: Koszalin");
			lblUrodzony.setBounds(480, 310, 440, 50);
			 
			JTextField txtImie = new JTextField( );
			txtImie.setBounds(480, 310, 440, 50);
			
			JButton btnAcceptImie = new JButton("Potwierdz imie");
			btnAcceptImie.setBounds(480, 410, 440, 50);
			btnAcceptImie.addActionListener(new ActionListener() 
			{ 
				public void actionPerformed(ActionEvent e) {
					
					String imie = txtImie.getText();
					String nazwisko = "Kowalski"; 
					ustawImie(imie, nazwisko); 
					//System.out.println(txtImie.getText());
					 
				}
			});
			
			pCenter.add(txtImie);
			pCenter.add(btnAcceptImie);
 			pCenter.add(lblInfoDodatkowe);
 			pCenter.add(lblNauczyciel);
 			pCenter.add(lblMojePersonalia);
 			pCenter.add(lblWychowawca);
 			pCenter.add(lblInfOsobowe);
 			pCenter.add(lblZamieszka³y);
 			pCenter.add(lblTelefon);
 			pCenter.add(lblUrodzony);
 			pCenter.add(ss); 
    		pCenter.add(lblVersion);
    		pCenter.add(lblTime);
    		pCenter.add(lblDay);
   
       
			
			
		  
     	  
     
    		//Dodanie paneli do okna
    		frame.add(p1, BorderLayout.NORTH);
    		frame.add(p2);  	 
    		frame.add(pCenter, BorderLayout.CENTER);
    		frame.setVisible(true);
    		
    	} // ---koniec metody: private void create GUI(String str) 
       
		public void ustawImie(String i, String n) {
			String userName = i ;
			String userSurname = n;
			// UserBuilder user = new UserBuilder(i, n); 
			 User u = new User(userName, userSurname, "610313500", 0);
			 //UserBuilder ub = new UserBuilder(userName, userSurname);
			System.out.println("Imie: " + u.getFirstName()); 
			System.out.println("Nazwisko:" + u.getLastName ()); 
			System.out.println("Wiek:" + u.getAge ());
			System.out.println("Telefon:" + u.getPhone ());
			
		}

		public static void someFunction(String[] array)
		{
			for (int i = 0; i < array.length; i++)
			{
				System.out.println(array[i]);
			}
		}
		// main..
    	public static final void main(String[] args) throws InterruptedException {
    	
    		// Schedule a job for the event-dispatching thread:
    		// creating and showing this application's GUI.
    		javax.swing.SwingUtilities.invokeLater(new Runnable()
    		{
    		public void run()
    			{
    			  
    				MojProfil gm = new MojProfil();
    				
    			 	String[] TwojeKlasy = {null}; 
    			 	
    			 	// jesli uzytkownik nie ma klasy, to przekaz parametr do metody towrz¹cej gui 'boolean false' aby poinformowac user'a i by stworzy³ klase
    			 	if(TwojeKlasy != null)
    			 	{
        				gm.createGui(null, true); 
        				someFunction(TwojeKlasy);
    			 	}
    			 	
       			 	else gm.createGui(null, false);
    			 	
    				//createAndShowTopLevelGUI();
    				//Sprawdz czy user ma jakies klasy w pliku zapiasne
    				//String nazwaZalogowanegoUzytkownika =
//    				File f = new File("c:\\dziennik\\users\\klasy.txt");
//    				if(f.exists() && f.isFile()) {
//    					gm.createGui(null);
//    				}
    				
    			}
    		});
    	}
    
    	/**
    	 * wywoluje g³owne okno aplikacji gdy uzytkownik wprowadzi poprawne dane w oknie logowania
    	 */
    	public void UruchomKlase(String str)
    	{   
    		//String temp = str;
    		new MojProfil().createGui(str, false);
    	}
    
    	private void wysrodkujOkno(JFrame frame) {
    		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		 int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    		 int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
    		 frame.setLocation(x, y);
    	}
    
    	public void OcenyRUN(String str, boolean p2IsEnable)
    	{
    		Dziennik.Oceny oceny = new Dziennik.Oceny();
    		oceny.OcenyRUN(str, p2IsEnable);
    	}
    	
    	//uruchamia klase 'zarzadzanieKlasami.java'
    	public void ZarzadzanieKlasami(String str) {
    	    Dziennik.ZarzadzanieKlasami ZK = new Dziennik.ZarzadzanieKlasami();
    	    ZK.UruchomKlase(str);	  		
    	}
 
    	/***	 ------------------- ------------------- ----------------COMBOBOX'y ------------------- ------------------- ------------------- -------------------	*/
    
    
    
    								
    	 
     
    } // end: GuiMain.java
    
    
    
 