    package Dziennik;
    
    import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
 
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
    
import java.util.Scanner;
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

import com.mysql.jdbc.StringUtils;

import java.util.List;

    public class GuiMain 
    {	  
  
		private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj;
    	private static JLabel lblInformacje, lblRokSzkolny, lblAktualnyPrzedmiot, lblObecnychStudentow, lblSeparator,lblSeparator2, lblTime,lblDay, lblVersion;
      
    	String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    	String strDay = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
  
    	final String zalogowanoJako = "Zalogowano jako: ";
		public String imie, nazwisko, wychowankowie, nazwaFolderuUzytkownika, persona;
		public String[] uczeKlasy, wszystkieKlasy;
		public String[] args = {};
		//zmienna przechowujaca przedmioty tej klasy, w konstruktorze dopisac by pobieral te przedmioty z plku
		public String[] przedmioty = {"Polski", "Geografia", "Matematyka"};
		
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
 
    	//  String nazwaFolderu, imie, nazwisko, String[]listaWszystkichklas, []listaKlasKtorychUcze, String klasaKtoraWychowuje
    	//(nazwaFolderu, imie, nazwisko, listaWszystkichKlas, listaKlasKtorychUcze, wychowujeKlase);
    	private void createGui(String folderName, String firstName, String lastName, String[] listOfAllClass, String[] listClassIteach, String KlasaWychowankowie) 
    	{
    		//tworzenie podkreslenia
//    		Font font = nazwaKonkretnegoObiektu.getFont();
//    		Map attributes = font.getAttributes();
//    		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//    		bWyloguj.setFont(font.deriveFont(attributes));
    		
    		nazwaFolderuUzytkownika = folderName;
    		imie = firstName; 
    		nazwisko = lastName;
    		wszystkieKlasy = listOfAllClass;
    		uczeKlasy = listClassIteach;
    		wychowankowie = KlasaWychowankowie;
    		persona = imie + " " + nazwisko; 
    		//frame settings..
    		JFrame frame = new JFrame("Dziennik Elektroniczny - Okienko startowe");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		GuiMain app = new GuiMain();
    		frame.setJMenuBar(app.createJMenuBar());
    		frame.setSize(1362, 724);
     		frame.setResizable(false);
     		wysrodkujOkno(frame);
    	    
     		//stworzyc metode 'create sep; return sep'
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
    		lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		lblLogo.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logo4m.png")));
    		lblLogo.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                	GuiMain gm = new GuiMain();
                	gm.createDirectoryIfNeeded(persona);
                	System.out.println("Powrót do g³ównego okna."); 
                }
            });
    		
    		JLabel lblMojeKlasy = new JLabel("Moje Klasy");
    		lblMojeKlasy.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		lblMojeKlasy.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                	frame.dispose();
                	System.out.println("Powinno otworzyc TwojeKlasy.java ");  
                	TwojeKlasy tk = new TwojeKlasy();
					tk.wczytajDane(folderName, firstName, lastName, listOfAllClass, listClassIteach, KlasaWychowankowie);
//					for (int i = 0 ; i < uczeKlasy.length ; i ++)
//					{
//						System.out.println(uczeKlasy[i]); 
//						System.out.println("*GuiMain.java* przekazuje parametry klasy: " + uczeKlasy[i]);
//					}
                }
            });
    		
    		JLabel lblDaneZalogowanego = new JLabel(persona);
    		lblDaneZalogowanego.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/usericonm.png")));
     

    		
    		JLabel lblMojProfil = new JLabel("Mój profil");
   			lblMojProfil.setForeground(blueColor);
   			lblMojProfil.addMouseMotionListener(new MouseMotionAdapter() 
   			{
     		   public void mouseDragged(MouseEvent arg0) 
     		  	{
                     System.out.println("work"); 
    	   		}	 
 			}); 
    		
   			
    		bWyloguj = new JButton("Wyloguj");
    		bWyloguj.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
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
    		
    		//tworzenie podkreslenia
    		

	    	final JComboBox comboBox = new JComboBox(uczeKlasy);
	    	comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		comboBox.setRenderer(  new MyComboBoxRenderer("Wybierz dziennik klasy której uczysz"));
    		comboBox.setPreferredSize(new Dimension(90, 30));
    		comboBox.setSelectedIndex(-1); //By default it selects first item, we don't want any selection    
    		comboBox.setBorder(null); 
    		comboBox.setOpaque(false);
			comboBox.setBackground(Color.WHITE);
			 
    		JButton btnZamknijDziennik = new JButton("(Zamknij dziennik)");
    		btnZamknijDziennik.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		btnZamknijDziennik.setFont(btnZamknijDziennik.getFont().deriveFont(11.0f));
    		//clearSelectionButton.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/homepage.png")));
    		btnZamknijDziennik.setBackground(Color.WHITE);
    		btnZamknijDziennik.setPreferredSize(new Dimension(108, 48));
    	
    		btnZamknijDziennik.setOpaque(false);
    		btnZamknijDziennik.setBorder(null);
    		btnZamknijDziennik.setContentAreaFilled(false);
    		btnZamknijDziennik.setBorderPainted(false);
    		btnZamknijDziennik.setForeground(blueColor);
    		btnZamknijDziennik.setVisible(false);
       		btnZamknijDziennik.setEnabled(false); 
    
    		JLabel lblDziennik = new JLabel();
    		lblDziennik.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/dziennikm.png")));
    		//Dodanie komponentów do panelu1
    		p1.add(lblLogo); // dodanie logo z funkcj¹ maj¹ce funkcje powrotu do glownego okna kontekstowego
    		p1.add(Box.createHorizontalGlue());	// tworzenie  obiektów w orientacji od prawej do lewej
    		//p1.add(Box.createRigidArea(new Dimension(35,0)));
    		p1.add(bSep4);
    		p1.add(Box.createRigidArea(new Dimension(	3,0)));
    		p1.add(lblDziennik);
    		p1.add(Box.createRigidArea(new Dimension(1,0)));
    		p1.add(comboBox);
    		p1.add(btnZamknijDziennik);
    		p1.add(Box.createRigidArea(new Dimension(115,0)));
    		p1.add(bSep2);
    		p1.add(lblMojeKlasy);
    		p1.add(bSep3);
    		p1.add(lblMojProfil);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(bSep);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(lblDaneZalogowanego);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
   
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(bWyloguj); // ikona pierwsza od konca 
    		
    		/**
    		 * Create Panel2
    		 */
     		JPanel p2 = new JPanel();
     		p2.setVisible(false);
    		p2.setBounds(1,61,1360,50);
    		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS)); // !! SKOPIOWAC to do panelu lewego - ZMIEN w kodzie po lewej "x-axis"na "Y-AXIS" aby zobaczyc o co chodzi.
    		p2.setBackground(Color.GREEN);

	
			
			//start: cbx1
			
	        final JComboBox cbx1 = new JComboBox(opt1);
    	    cbx1.setRenderer(new MyComboBoxRenderer1("Lekcje"));
        	cbx1.setSelectedIndex(-1);  
      		cbx1.setEnabled(false);
        	cbx1.setBackground(Color.WHITE);
        	cbx1.addActionListener (new ActionListener ()
        	{
        	    public void actionPerformed(ActionEvent e) {
        	    	{
        	    		 

//        	                Object selected = cbx2.getSelectedItem();
        	                if(cbx1.getSelectedIndex() == 0 ) 
        	                { 
        	                	 System.out.println("Wybrano: Lista uczniow"); 
        	                	 try
        	                	 { 
            	                	PlanLekcji pl = new PlanLekcji();
            	                	pl.wczytajDane(persona, firstName, lastName, listOfAllClass, listClassIteach, KlasaWychowankowie);
            	                	frame.dispose();
        	                	 } catch (Exception ex ) {System.out.println("Blad przy wyborze cbx1" + ex);}
         	                }
        	               
        	                 
        	    	}
        	    }
        	});
        	p2.add(cbx1);
			//end: cbx1
 
			//start: cbx2
			
	        final JComboBox cbx2 = new JComboBox(opt2);
    	    cbx2.setRenderer(new MyComboBoxRenderer2("Oceny"));
        	cbx2.setSelectedIndex(-1);  
        	cbx2.setBackground(Color.WHITE);
        	cbx2.addActionListener (new ActionListener ()
        	{
        	    public void actionPerformed(ActionEvent e) {
        	    	{ 
//        	                Object selected = cbx2.getSelectedItem();
        	                if(cbx2.getSelectedIndex() == 0 ) 
        	                {
        	                	//uruchom oceny sem I
        	                	 System.out.println("uruchom oceny sem I");
         	                }
        	               
        	                
        	                if (cbx2.getSelectedIndex() == 1 ) 
        	                {
        	                	System.out.println("Wybrano: sem II" );
        	                }
        	                if (cbx2.getSelectedIndex() == 2 ) 
        	                {
        	                	System.out.println("Wybrano: oceny na wywiadowke" );
        	                }
        	    	//	String choosenClass = (String) comboBox.getSelectedItem();
        	    	//frame.dispose();
        	    	//	OcenyRUN( choosenClass, true) ; 
        	    	}
        	    }
        	});
        	p2.add(cbx2);
			//end: cbx2
    		        
			//start: cbx3
			
	        final JComboBox cbx3 = new JComboBox(opt3);
    	    cbx3.setRenderer(new MyComboBoxRenderer3("Obecnosci"));
        	cbx3.setSelectedIndex(-1);  
        	cbx3.setBackground(Color.WHITE);
        	cbx3.addActionListener (new ActionListener ()
        	{
        	    public void actionPerformed(ActionEvent e) {
        	    	{  
        	                if(cbx3.getSelectedIndex() == 0 ) 
        	                {
        	                	String przedmiot = opt3[0];
        	                	 ListaObecnosci lo = new ListaObecnosci();
        	                	 lo.createGui(persona, strDay, przedmiot);
         	                }
        	               
        	                
        	                if (cbx3.getSelectedIndex() == 1 ) 
        	                {
        	                	//cos ..
        	                }
        	                if (cbx3.getSelectedIndex() == 2 ) 
        	                {
        	                	 //cos ..
        	                }
        	    	//	String choosenClass = (String) comboBox.getSelectedItem();
        	    	//frame.dispose();
        	    	//	OcenyRUN( choosenClass, true) ; 
        	    	}
        	    }
        	});
        	p2.add(cbx3);
			
        	
        	
        	//end: cbx3
			
        	
    		//start: cbx4
    		final JComboBox cbx4 = new JComboBox(opt4);
    	    cbx4.setRenderer(new MyComboBoxRenderer4("Uczniowie"));
        	cbx4.setSelectedIndex(-1);  
        	cbx4.setBackground(Color.WHITE);
        	cbx4.addActionListener (new ActionListener ()
        	{
        	    public void actionPerformed(ActionEvent e) {
        	    	{
        	    		 

//        	                Object selected = cbx2.getSelectedItem();
        	                if(cbx4.getSelectedIndex() == 0 ) 
        	                { 
        	                	 System.out.println("Wybrano: Lista uczniow"); 
        	                	 try
        	                	 { 
            	                	 ListaUczniow.main(args);
        	                	 } catch (Exception ex ) {System.out.println("Blad przy wyborze cbx2" + ex);}
         	                }
        	               
        	                
        	                if (cbx4.getSelectedIndex() == 1 ) 
        	                {
        	                	frame.setVisible(false);
        	                	AddStudent.main(args);
        	                	System.out.println("Wybrano: Dodaj ucznia" );
        	                }
        	            
        	    	//	String choosenClass = (String) comboBox.getSelectedItem();
        	    	//frame.dispose();
        	    	//	OcenyRUN( choosenClass, true) ; 
        	    	}
        	    }
        	});
        	p2.add(cbx4);
			//end: cbx4 
			
			 //start: cbx5
    		final JComboBox cbx5 = new JComboBox(opt5);
    	    cbx5.setRenderer(new MyComboBoxRenderer5("Zachowanie i uwagi"));
        	cbx5.setSelectedIndex(-1);  
        	cbx5.setBackground(Color.WHITE);
        	p2.add(cbx5);
			//end: cbx5 
			
			//start: cbx6
    		final JComboBox cbx6 = new JComboBox(opt6);
    	    cbx6.setRenderer(new MyComboBoxRenderer6("Raporty i zestawienia"));
        	cbx6.setSelectedIndex(-1);  
        	cbx6.setBackground(Color.WHITE);
        	p2.add(cbx6);
			//end: cbx6 
			
			//start: cbx7
    		final JComboBox cbx7 = new JComboBox(opt7);
    	    cbx7.setRenderer(new MyComboBoxRenderer6("Zarz¹dzaj"));
        	cbx7.setSelectedIndex(-1);  
        	cbx7.setBackground(Color.WHITE);
        	p2.add(cbx7);
			//end: cbx7
			
			//start: cbx8
    		final JComboBox cbx8 = new JComboBox(opt8);
    	    cbx8.setRenderer(new MyComboBoxRenderer6("Wyszukaj"));
        	cbx8.setSelectedIndex(-1);  
        	cbx8.setBackground(Color.WHITE);
        	p2.add(cbx8);
			//end: cbx8
			
			cbx1.setEnabled(false);
			cbx2.setEnabled(false);
			cbx3.setEnabled(false);
			cbx4.setEnabled(false);
			cbx5.setEnabled(false);
			cbx6.setEnabled(false);
			cbx7.setEnabled(false);
			 
    		lblSeparator = new JLabel();
    		lblSeparator.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/sep1.png")));
    		lblSeparator.setBackground( Color.WHITE) ;
    		lblSeparator.setPreferredSize(new Dimension(10, 48));
    		
    		lblSeparator2 = new JLabel();
    		lblSeparator2.setPreferredSize(new Dimension(10, 48));
    		lblSeparator2.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/sep1.png")));
    		lblSeparator2.setBackground(Color.WHITE) ;
    		
    		bModulKlas = new JButton("Zarz¹dzaj klas¹");
    		bModulKlas.setBackground( Color.WHITE) ;
    		bModulKlas.setPreferredSize(new Dimension(240, 30));
    		bModulKlas.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/klasIcon.png")));
    		bModulKlas.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	ZarzadzanieKlasami(persona);
                	frame.setVisible(false);
                }
            });
    		 
    		/**
    		 * Create PanelPrzedmiotow
    		 */
    		
			JPanel pSubject = new JPanel();
     		pSubject.setVisible(false);
    		pSubject.setBounds(1,115,1360,50);
    		pSubject.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS)); // !! SKOPIOWAC to do panelu lewego - ZMIEN w kodzie po lewej "x-axis"na "Y-AXIS" aby zobaczyc o co chodzi.
    		pSubject.setBackground(Color.BLUE);
    	 
 
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
			 
    		
    		//lblTime = new JLabel("Godzina: " + strTime);
    		//lblDay = new JLabel("Dzis jest:  " + strDay);
    		//lblDay.setBounds(0,444,220,150);
    		 
 		   // lblTime.setBounds(640,444,250,150);
 			 
 		    int odlegloscOdP2 = 103;
 		    int odlegloscLeft = 20;
 			JScrollPane ss = new JScrollPane(); 
 			ss.getViewport().setBackground(Color.WHITE);
 			ss.setBounds(odlegloscLeft-2, odlegloscOdP2+25, 440, 322);
 			ss.setBackground(Color.white);
 		  
 			JLabel lblUzupelnijProfil = new JLabel("Aby korzystaæ ze wszystkich funkcjonalnoœci przejdŸ do zak³adki 'Moje Klasy' i uzupe³nij dane");
  			lblUzupelnijProfil.setBounds(450,50,640,15);
 			lblUzupelnijProfil.setForeground(hrefColor);
 			lblUzupelnijProfil.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/addClassM.png ")));
 			lblUzupelnijProfil.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                	System.out.println("Powrót do g³ównego okna");
                }
            });
 			pCenter.add(lblUzupelnijProfil);
 			
 			JLabel lblTwojeZajecia = new JLabel("Twój terminarz lekcyjny,");  
 			lblTwojeZajecia.setFont(new Font("default", Font.HANGING_BASELINE, 14));
 			lblTwojeZajecia.setBounds(odlegloscLeft,odlegloscOdP2 +8,240,15);
 			
 		 	JLabel lblDzis = new JLabel("Dziœ:");
 		 	lblDzis.setBounds(odlegloscLeft,27+odlegloscOdP2,140,30);
 		 	  
			//--------------dzis..
			int temp = 35; // temp to wysokosc w panelu Jscroll Termiarzu
			String[] terminarzGodzina = {"8:00-8:45", "8:55-8:35", "9:45-10:30"};  
			String[] terminarzPrzedmiot  = {"Matematyka", "Geografia" , "Godzina wychowawcza"};  
			String[] terminarzKlasa = {"6A", "4B", "6A"};  
			 
			int dlugosc_tablicy = terminarzGodzina.length;
 			JLabel[] labels = new JLabel[dlugosc_tablicy];
 			 
			//petla wypisujaca terminarz i umieszczajaca go w panelu centralnym
			for (int i = 0; i < terminarzGodzina.length; i++)
				{
						
                        labels[i] = new JLabel();
                        labels[i].setBounds(odlegloscLeft+10,temp+odlegloscOdP2,340,50);
                        labels[i].setText(terminarzGodzina[i]+ "  "  + terminarzPrzedmiot[i]+ "  "  + terminarzKlasa[i]);
                        labels[i].setForeground(new Color(128, 87, 140));
                        ss.add(labels[i]);                        
                        pCenter.add(labels[i]);
                        temp +=20; 
				}
				 
			String[] terminarzJutroGodzina = {"8:00-8:45",  "9:45-10:30"};  
			String[] terminarzJutroPrzedmiot  = {"Geografia",  "Geografia"};  
			String[] terminarzJutroKlasa = {"1A", "1B", };  	
			
			//--------------jutro
			JLabel lblJutro = new JLabel("Jutro: " + convertedDate);
			temp+=20;
			lblJutro.setBounds(odlegloscLeft,temp+odlegloscOdP2,340,50);
			pCenter.add(lblJutro);
			temp+=20;
			 		
			for (int j = 0; j < terminarzJutroGodzina.length; j++)
				{
                        labels[j] = new JLabel();
                        labels[j].setBounds(odlegloscLeft+10,temp+odlegloscOdP2,340,50);
                        labels[j].setText(terminarzJutroGodzina[j]+ "  "  + terminarzJutroPrzedmiot[j]+ "  "  + terminarzJutroKlasa[j]);
                        labels[j].setForeground(new Color(128, 87, 140));
                        ss.add(labels[j]);                        
                        pCenter.add(labels[j]);
                        temp +=20; 
				}
				 
			//--------------pojutrze..
			String[] terminarzJutroJutroGodzina = {"10:45-11:30",  "11:40-12:25", "12:30-13:15" };  
			String[] terminarzJutroJutroPrzedmiot  = {"Matematyka",  "Geografia", "Ekonomia(zastêpstwo)"};  
			String[] terminarzJutroJutroKlasa = {"1A", "2A", "6A" };  	
			
			JLabel lblJutroJutro = new JLabel("Pojutrze: " + convertedDate2);
			temp+=20;
			lblJutroJutro.setBounds(odlegloscLeft,temp+odlegloscOdP2,340,50);
			pCenter.add(lblJutroJutro);
			temp+=20;
			 	
			for (int g = 0; g < terminarzJutroJutroGodzina.length; g++)
				{
                        labels[g] = new JLabel();
                        labels[g].setBounds(odlegloscLeft+10,temp+odlegloscOdP2,340,50);
                        labels[g].setText(terminarzJutroJutroGodzina[g]+  "  "  +  terminarzJutroJutroPrzedmiot[g]+"  "  +   terminarzJutroJutroKlasa[g]);
                        labels[g].setForeground(new Color(128, 87, 140));
                       // ss.add(labels[g]);                  niepotrzebne bo scrollpane przyslania labels i trzeba dodaæ label's do frame.      
                        pCenter.add(labels[g]);
                        temp +=20; 
				}
			
			 
			
 			pCenter.add(lblTwojeZajecia);
 			pCenter.add(lblDzis);
 			pCenter.add(ss); 
    		 
    		//pCenter.add(lblTime);
    		//pCenter.add(lblDay);
   
    		JScrollPane spCenter = new JScrollPane(); 
    		spCenter.getViewport().setBackground(Color.WHITE);
    		spCenter.setBounds(odlegloscLeft+450, odlegloscOdP2+25, 350, 322);
    		spCenter.setBackground(Color.white);
    	
    		JLabel lblWydarzenia = new JLabel("Wydarzenia ");
    		lblWydarzenia.setFont(new Font("default", Font.HANGING_BASELINE, 14));
    		lblWydarzenia.setBounds(odlegloscLeft+450,  odlegloscOdP2-10 ,340,50); 

    		String[] wydarzeniaDzisGodzina = {" 8:00-11:00 "};
    		String[] wydarzeniaDzisWhat = {" Próbna matura"};
    		String[] wydarzeniaDzisWhere = {" (hala) "};
    		
    		Font font = new Font("default", Font.HANGING_BASELINE, 14);
    		Map  attributes = font.getAttributes();
    		attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
    		Font newFont = new Font(attributes);  
    		lblWydarzenia.setFont(font.deriveFont(attributes));
     
			for (int a = 0; a < wydarzeniaDzisGodzina.length; a++)
			{
                    labels[a] = new JLabel();
                    labels[a].setBounds(odlegloscLeft+450,  120 ,340,50); 
                    labels[a].setText(convertedDate  + "    " +  wydarzeniaDzisGodzina[a]+  "  "  +  wydarzeniaDzisWhat[a]+"  "  +   wydarzeniaDzisWhere[a]);
                    labels[a].setForeground(new Color(128, 87, 140));
                   // ss.add(labels[g]);                  niepotrzebne bo scrollpane przyslania labels i trzeba dodaæ label's do frame.      
                    pCenter.add(labels[a]);
                    temp +=20; 
			}
    		
			pCenter.add(lblWydarzenia);
    		pCenter.add(spCenter);
    		
    		//tworzenie scrollPane 'Aktualnosci' umieszczonego po prawej stronie
    		JLabel lblAktualnosci = new JLabel("Aktualnoœci ");
    		lblAktualnosci.setFont(new Font("default", Font.HANGING_BASELINE, 14));
    		lblAktualnosci.setBounds(odlegloscLeft+850,  odlegloscOdP2-10 ,340,50); 
    		JScrollPane spRight = new JScrollPane(); 
    		spRight.getViewport().setBackground(Color.WHITE);
    		spRight.setBounds(odlegloscLeft+850, odlegloscOdP2+25, 350, 322);
    		spRight.setBackground(Color.white);
    		pCenter.add(lblAktualnosci);
    		pCenter.add(spRight);
    		
    		comboBox.addActionListener(new ActionListener() 
    		{
				public void actionPerformed(ActionEvent evt) {
                myBox(evt);
            }
			 	
			protected void myBox(ActionEvent evt) {
				 if (comboBox.getSelectedItem() != null   ) 
				 {
           		 cbx1.setEnabled(true);
           		 cbx2.setEnabled(true);
           		 cbx3.setEnabled(true);
           		 cbx4.setEnabled(true);
           		 cbx5.setEnabled(true);
           		 cbx6.setEnabled(true);
           		 cbx7.setEnabled(true);
           		 p2.setVisible(true);
           		 btnZamknijDziennik.setVisible(true);
           		 btnZamknijDziennik.setEnabled(true); 
      			 }
				
			}
      		});
		 
     	 	btnZamknijDziennik.addActionListener(new ActionListener()
    		        {
    		            public void actionPerformed(ActionEvent e)
    		            {
    		                comboBox.setSelectedIndex(-1);
							cbx1.setEnabled(false);
							cbx2.setEnabled(false);
							cbx3.setEnabled(false);
							cbx4.setEnabled(false);
							cbx5.setEnabled(false);
							cbx6.setEnabled(false);
							cbx7.setEnabled(false);
							p2.setVisible(false);
							btnZamknijDziennik.setEnabled(false);
							btnZamknijDziennik.setVisible(false);
    		            }
    		});
    		
     	 	if(uczeKlasy==null)
    		{
    			comboBox.setEnabled(false);  
    			comboBox.setSize(800, comboBox.getHeight());
    			comboBox.setRenderer(  new MyComboBoxRenderer("Wybierz dziennik swojej klasy:(niedostêpne)"));
    			JButton btnUzupelnijListeKlas = new JButton("(Uzupe³nij informacj)"); 
     	 		btnUzupelnijListeKlas.setFont(btnZamknijDziennik.getFont().deriveFont(4.0f));
        		//clearSelectionButton.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/homepage.png")));
     	 		btnUzupelnijListeKlas.setBackground(Color.WHITE);
     	 		btnUzupelnijListeKlas.setPreferredSize(new Dimension(108, 108));  
     	 		frame.add(btnUzupelnijListeKlas);
    		}
     
     		//Jpanel informacyjny dolny
     		JPanel pDolny = new JPanel( new FlowLayout(FlowLayout.CENTER));
     		//komponenty
     		JLabel lblTime = new JLabel("Dziœ jest " + strDay + ", " +  strTime);
     		pDolny.add(lblTime);
     		//lblVersion = new JLabel("Dziennik Elektroniczny wersja 1.3");
     		//   pDolny.add(lblVersion);
    		//Dodanie paneli do okna 
    		frame.add(p1, BorderLayout.NORTH);
    		frame.add(p2);  	 
    		frame.add(pCenter, BorderLayout.CENTER);
    		frame.setVisible(true);
    		frame.add(pDolny, BorderLayout.SOUTH);	
 	
    	} // ---koniec metody: private void create GUI(String str) 
       
		public static void someFunction(String[] array)
		{
			for (int i = 0; i < array.length; i++)
			{
				System.out.println(array[i]);
			}
		}
		// main..
    	public static final void main(String[] args) throws InterruptedException 
    	{
  
    		// Schedule a job for the event-dispatching thread:
    		// creating and showing this application's GUI.
    		javax.swing.SwingUtilities.invokeLater(new Runnable()
    		{
    		public void run()
    			{
    			  
    				GuiMain gm = new GuiMain(); 
    				gm.createDirectoryIfNeeded("Michal Klich");
    				 
    			} 
    		});
    	}
    		 
    		protected void createDirectoryIfNeeded(String fullname)
    		{   
    			
    		  String nazwaFolderu = ("c:\\dziennik\\users\\"+ fullname);
    		  File userDirName = new File("c:\\dziennik\\users\\"+ fullname);
    		  File programDataPath = new File("c:\\dziennik\\data");
    		  String imie, nazwisko, wiek, telefon, wychowujeKlase;
    		  String[] listaWszystkichKlas, listaKlasKtorychUcze;
    		  String[] splitStr = fullname.split("\\s+");
    		  imie = splitStr[0];
    		  nazwisko = splitStr[1]; 
    		  
    		  
    		  
    		  //stwórz folder z danymi programu jesli nie istnieje
    		  if (!programDataPath.exists()) 
    		  {
    			  programDataPath.mkdir();
    			  if(programDataPath.isDirectory() && programDataPath.exists())
    			  {
    			  		System.out.println("Tworzenie pliku z danymi programu przebieglo pomyslnie");
    			  		File PathToClassesData = new File("c:\\dziennik\\data\\klasa\\");
    			  		PathToClassesData.mkdir();
    			  }
    		  }
    		  
    		  
    		  // jesli zalogowany uzytkownik nie ma folderu uzytkownika stwórz go.
    		  if (!userDirName.exists()) 
    		  {
      		    System.out.println("Nowy uzytkownik(nie wykryto folderu)");
    		    System.out.println("Stworzono folder: " + fullname);
    		    userDirName.mkdir();
    		    
    		    /**uruchom app przekazuj¹c parametry tj:
    		    *	Imie, Nazwisko, ListaWszystkichKlas, ListaKlasKtorychUczy, NazwaKlasyWychowywanej, IloscUczniow,
    		    */
    		    
    		    listaWszystkichKlas = null;
    		    listaKlasKtorychUcze = null;
    		    wychowujeKlase = null; 
    		    new GuiMain().createGui(nazwaFolderu, imie, nazwisko, listaWszystkichKlas, listaKlasKtorychUcze, wychowujeKlase); // uruchom program przekazuj¹c parametry:
    		  }
    		  
    		  //jesli uzytkownik istnieje
    		  if (userDirName.exists()) 
    		  { 
    			 System.out.println("Uzytkownik istnieje w bazie(Ma istniej¹cy folder o nazwie)" + fullname + ", scie¿ka: c:\\dziennik\\users\\");
    			 
    			 //sciezka do pliku txt, gdzie przechowywane powinna byæ list klas zalogowanego u¿ytkownika, których on uczy. 
    			  File fKlasy = new File("C:/dziennik/users/Michal Klich/uczyKlasy.txt.txt"); 
    			 // File fileStudentOfThisTeacher = new File("C:/dziennik/users/" + fullname + "/StudentsOfThisTeacher.txt.txt");
    			  //czy istnieje plik txt z list¹ klas zalogowanego uzytkownika
    			  if (fKlasy.exists() && !fKlasy.isDirectory())
    				  
    			  { 
    				  if(fKlasy.length()!=0)
    				  try {
    					
    					//oblicz ile uzytkownik ma klas(czyli  ile wierszy jest w pliku uczyKlasy.txt)
    					int liczbaKlas=0;
    					//BufferedReader in = new BufferedReader(new FileReader(fKlasy.toString()));
    					BufferedReader in = new BufferedReader(new FileReader("C:\\dziennik\\users\\Michal Klich\\uczyKlasy.txt.txt"));
    					while (in.readLine() != null) liczbaKlas++;
    					//in.close();
    					System.out.println("Plik ma lini: " + liczbaKlas);
    					
    					//stworz zmienna do przechowywania klas
    					String[] uczyKlasy = new String[liczbaKlas]; 
    					System.out.println("Stworzono zmienna 'string uczyKlasy'");
    					
    					//Dodaj klasy z pliku txt do zmiennej tablicowej String
						Scanner fileIn = new Scanner(new File("c:\\dziennik\\users\\"+ fullname + "\\uczyKlasy.txt.txt"));
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
						
						
						 System.out.println("Zamknieto strumien I/o"); 
						 //uruchomienie programu z parametrami pobranymi z I/O(klasy etc.)
						 new GuiMain().createGui(nazwaFolderu, imie, nazwisko, null, uczyKlasy, null); 
    					   
    				  } catch (FileNotFoundException e) {
    					  
						System.out.print("Plik jest pusty lub nieprawid³owy " + e);
    				  } catch (IOException e) {
    					  System.out.println("Blad podczas liczenia lini: " + e);
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				  else 
    					  {
    					  
    					  	System.out.println("Uzytkownik nie ma zapisanych w pliku ¿adnych klas które by uczy³");
    					  	new GuiMain().createGui(nazwaFolderu, imie, nazwisko, null, null, null); 
    					  }
    			// jesli nie istnieje
    			  }
    			  else //uzytkownik istnieje ale nie posiada klas
    				  {
    				  System.out.println("U¿ytkownik nie posiada pliku .txt z nazwami klas które móg³by uczyæ " + fKlasy);
    				  new GuiMain().createGui(nazwaFolderu, imie, nazwisko, null, null, null); 
    				  }
    			  

    		  } 
    		}
			
		 

		/**
    	 * wywoluje g³owne okno aplikacji gdy uzytkownik wprowadzi poprawne dane w oknie logowania
    	 */
    	public void UruchomKlase(String str)
    	{   
    		//String temp = str;
    		createDirectoryIfNeeded(str); 
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
    
    	//----------------Start: combox 0 (z dziennikami) ------------
    	
        String[] options = {"Klasa 6A: Matematyka ", "Klasa 4A: Matematyka" ,  "Klasa 3A: Matematyka"};
    	class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    	    {
    	        private String _title; 
    	        public MyComboBoxRenderer(String title)
    	        {
    	            _title = title;
    	             
    	        } 
    	        
    	        public Component getListCellRendererComponent(JList list, Object value,
    	                int index, boolean isSelected, boolean hasFocus)
    	        {
    	            if (index == -1 && value == null) 
    	            {
    	            	setText(_title); 
    	            }
    	            
    	            else 
    	            	{
    	            		setText(value.toString()); 
    	            	}
    	            return this;
    	        }

			 
    	    }
 	//----------------END: combox 0 ---------------
    
    
    								
    //-----------------START: Combobox 1---------------------
    String[] opt1 = {"Wyswietl plan lekcji" };
    	class MyComboBoxRenderer1 extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer1(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
    //-----------------KONIEC: Combobox 1---------------------
    
	//-----------------START: Combobox 2---------------------
    String[] opt2 = {"Semestr I", "Semestr II" ,  "Wydrukuj oceny na wywiadówke"};
    	class MyComboBoxRenderer2 extends JLabel implements ListCellRenderer
    {
    		
        private String _title;
        public MyComboBoxRenderer2(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
    //-----------------KONIEC: Combobox 2---------------------
		 
		 
	//-----------------START: Combobox 3---------------------
   // String[] opt3 = {"Bie¿¹cy tydzieñ", "Poprzedni tydzieñ" ,  "Frekwencja Semestr I", "Frekwencja Semestr II", "Polski", "Matematyka", "Geografia", przedmioty[0]};
    String[] opt3 = {"Polski", "Matematyka", "Geografia", przedmioty[0]};
    
    class MyComboBoxRenderer3 extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer3(String title)
        {
            _title = title;
        }
 
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
    //-----------------KONIEC: Combobox 3---------------------
		 
    //-----------------START: Combobox 4---------------------
    String[] opt4 = {"Lista uczniów", "Dodaj ucznia"   }; // opcjonalnie dodaæ tutaj wszystkich uczniów by byli wyœwietleni od razu w comboxie 
    	class MyComboBoxRenderer4 extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer4(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
    //-----------------KONIEC: Combobox 4---------------------
		 
			 		 	//-----------------START: Combobox 5---------------------
    String[] opt5 = {"Dziennik uwag", "Dodaj now¹ uwagê", "Podgl¹d ocen z zachowania, semestr I", "Podgl¹d ocen z zachowania, semestr II" }; // opcjonalnie dodaæ tutaj wszystkich uczniów by byli wyœwietleni od razu w comboxie 
    	class MyComboBoxRenderer5 extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer5(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
    //-----------------KONIEC: Combobox 5---------------------	 
		 
		 		 		 	//-----------------START: Combobox 6---------------------
    String[] opt6  = {"Statystyki klasy", "Podsumowanie ocen semestr I", "Podsumowanie ocen semestr I", "Podsumowanie ocen semestr II" , "Zestawienie frekwencji" }; // opcjonalnie dodaæ tutaj wszystkich uczniów by byli wyœwietleni od razu w comboxie 
    	class MyComboBoxRenderer6 extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer6(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
    //-----------------KONIEC: Combobox 6---------------------	 
		
			 		 		 	//-----------------START: Combobox 6---------------------
    String[] opt7  = {"Zarz¹dzaj klas¹", "Zarz¹dzaj ocenami", "Zarz¹dzaj obecnoœciami", "Odwo³aj lekcje" , "Zaplanuj wywiadówkê", "Zaplanuj wycieczkê" }; // opcjonalnie dodaæ tutaj wszystkich uczniów by byli wyœwietleni od razu w comboxie 
    	class MyComboBoxRenderer7 extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer7(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
    //-----------------KONIEC: Combobox 6---------------------	 	
		
					 		 		 	//-----------------START: Combobox 6---------------------
    String[] opt8  = {"Wyszukaj ucznia", "Wyszukiwanie zaawansowane" }; // opcjonalnie dodaæ tutaj wszystkich uczniów by byli wyœwietleni od razu w comboxie 
    	class MyComboBoxRenderer8 extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer8(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
    //-----------------KONIEC: Combobox 6---------------------	 
     
    } // end: GuiMain.java