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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
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
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import org.omg.CORBA.Bounds;
 


import com.mysql.jdbc.StringUtils;

import java.util.List;

    public class PlanLekcji 
    {	  
		private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj;
    	private static JLabel lblInformacje, lblRokSzkolny, lblAktualnyPrzedmiot, lblObecnychStudentow, lblSeparator,lblSeparator2, lblTime,lblDay, lblVersion;
      
    	String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    	String strDay = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
  
    	final String zalogowanoJako = "Zalogowano jako: ";
		public String imie, nazwisko, wychowankowie, nazwaFolderuUzytkownika, persona;
		public String[] uczeKlasy, wszystkieKlasy;
		public String[] args = {};
		String[] planZajec3Bpn = {"Matematyka", "Marketing", "Polski", "Geografia", "Hiszpanski"}; 
		String[] planZajec3Bwt = {"", "Przedsiebiorczosc", "Podstawy ekonomi", "HIstoria", "Niemiecki", "Religia"};
		String[] planZajec3Bsr = {"Matematyka", "Marketing", "WF", "Geografia", "Niemiecki"};
		String[] planZajec3Bcz = {"Marketing", "Matematyka", " Fizyka", "Geografia", "Francuski"};
		String[] planZajec3Bpt = {"Rosyjski", "Marketing", "Angielski", "Geografia", "Niemiecki"};
		String dzien,  godzina,  nowaWartosc;
		int[] mozliwosci  ; 
		String[] dniTygodnia = {"Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek"};
		
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
   
    	private void createGui(String folderName, String firstName, String lastName, String[] listOfAllClass, String[] listClassIteach, String KlasaWychowankowie) 
    	{
//			tworzenie podkreslenia
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
    		
    		System.out.println("folder name: " + folderName);
    		
    		//frame settings..
    		JFrame frame = new JFrame("Dziennik Elektroniczny - Twoje klasy");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		PlanLekcji app = new PlanLekcji();
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
    		lblLogo.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logo4m.png")));
    		lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		lblLogo.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                	frame.dispose();
                	GuiMain gm = new GuiMain();
                	gm.loadDataAndRunApp(persona);
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
   			lblMojProfil.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
   			
    		bWyloguj = new JButton("Wyloguj");
    		bWyloguj.setBackground(new Color(255, 255, 255));
    		bWyloguj.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logout.png")));
    		bWyloguj.setSize(30, 60);
    		bWyloguj.setBorder(null);
    		bWyloguj.setForeground(blueColor); 
    		bWyloguj.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		bWyloguj.addActionListener(new ActionListener() {			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				frame.dispose();
    				JOptionPane.showMessageDialog(null, "Wylogowano.");
    				Logowanie.runClassLogowanie();
    			}
    		});
    		 
 
    		//Dodanie komponentów do panelu1
    		p1.add(lblLogo); // dodanie logo z funkcj¹ maj¹ce funkcje powrotu do glownego okna kontekstowego
    		p1.add(Box.createHorizontalGlue());	// tworzenie  obiektów w orientacji od prawej do lewej
    		//p1.add(Box.createRigidArea(new Dimension(35,0)));
 
    		p1.add(Box.createRigidArea(new Dimension(	3,0)));
 
    		p1.add(Box.createRigidArea(new Dimension(1,0))); 
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
    		p1.setBorder(BorderFactory.createLineBorder((new Color(245,245,255))));
    		/**
    		 * Create Panel2
    		 */
     		JPanel p2 = new JPanel();
     		p2.setVisible(true);
     		p2.setBorder(new EmptyBorder(10,30,0,0));
    		p2.setBounds(1,81,1360,50);
    		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));  
    		p2.setBackground(new Color(239,239,239));
		 
    		
  
   	  		JPanel panelDni = new JPanel();
   	  		panelDni.setLayout(null);  
   	  		panelDni.setBackground(new Color(242,242,242));	
   	  		
   	  		JLabel[] lblDniTygodnia = new JLabel[dniTygodnia.length];
   	  		
   	  		int  wys = 0;
   	  		int  szer = 100;
   	  		for (int a = 0; a < dniTygodnia.length; a++)
   	  		{
                lblDniTygodnia[a] = new JLabel();
                lblDniTygodnia[a].setBounds(szer, wys ,340,50); 
                lblDniTygodnia[a].setText(dniTygodnia[a]);
                lblDniTygodnia[a].setForeground(new Color(128, 87, 140)); 
                panelDni.add(lblDniTygodnia[a]);
                szer += 145;
			}
		 
   	  		
   	  		int lineStartGodzinyZajec = 0;
   	  		int lineStartHeightGodzinyZajec = 30;
   	  		String[] godzinyZajec = {"8:00-8:45", "9:00-9:45", "10:00-10:45", "11:00-11:45", "12:00-12:45", "13:00-13:45", "14:00-14:45"};
   	  		JLabel[] lblGodzinyZajec = new JLabel[godzinyZajec.length];
   	  		for (int b = 0; b < godzinyZajec.length; b++)
		{
   	  		lblGodzinyZajec[b] = new JLabel();
   	  		lblGodzinyZajec[b].setBounds(lineStartGodzinyZajec, lineStartHeightGodzinyZajec ,340,50); 
   	  		lblGodzinyZajec[b].setText(godzinyZajec[b]);
   	  		lblGodzinyZajec[b].setForeground(new Color(128, 87, 140)); 
   	  		panelDni.add(lblGodzinyZajec[b]);
   	  		lineStartHeightGodzinyZajec += 45;
 
    		Font font = lblGodzinyZajec[b].getFont();
     		Map attributes = font.getAttributes();
     		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
     		lblGodzinyZajec[b].setFont(font.deriveFont(attributes)); 
     		lblGodzinyZajec[b].setFont(new Font("default", Font.ROMAN_BASELINE, 15));
		}
   	  		
   	  		int przedmiotyPnX = 30;
	  		int przedmiotyPnY = 100;
   	  		
   	  		JLabel[] lblPrzedmiotPn = new JLabel[planZajec3Bpn.length];
   	  		for (int b = 0; b < lblPrzedmiotPn.length; b++)
		{
   	  		lblPrzedmiotPn[b] = new JLabel();
   	  		lblPrzedmiotPn[b].setBounds(przedmiotyPnY, przedmiotyPnX ,340,50); 
   	  		lblPrzedmiotPn[b].setText(planZajec3Bpn[b]);
   	  		lblPrzedmiotPn[b].setForeground(new Color(128, 87, 140)); 
   	  		panelDni.add(lblPrzedmiotPn[b]);
   	  		przedmiotyPnX += 45;

   	  		lblPrzedmiotPn[b].setFont(new Font("default", Font.BOLD, 15));
		}
     	  
   	  		int przedmiotyWtX = 245;
	  		int przedmiotyWtY = 30;
   	  		
   	  		JLabel[] lblPrzedmiotWt = new JLabel[planZajec3Bwt.length];
   	  		for (int b = 0; b < lblPrzedmiotWt.length; b++)
		{
   	  		lblPrzedmiotWt[b] = new JLabel();
   	  		lblPrzedmiotWt[b].setBounds(przedmiotyWtX, przedmiotyWtY ,340,50); 
   	  		lblPrzedmiotWt[b].setText(planZajec3Bwt[b]);
   	  		lblPrzedmiotWt[b].setForeground(new Color(128, 87, 140)); 
   	  		panelDni.add(lblPrzedmiotWt[b]);
   	  		przedmiotyWtY += 45;
   	  		lblPrzedmiotWt[b].setFont(new Font("default", Font.BOLD, 15));
		}
   	  		
   	  		int przedmiotySrX = 390;
   	  		int przedmiotySrY = 30;
   	  		JLabel[] lblPrzedmiotSr = new JLabel[planZajec3Bsr.length];
   	  		for (int b = 0; b < lblPrzedmiotSr.length; b++)
		{
   	  		lblPrzedmiotSr[b] = new JLabel();
   	  		lblPrzedmiotSr[b].setBounds(przedmiotySrX , przedmiotySrY ,340,50); 
   	  		lblPrzedmiotSr[b].setText(planZajec3Bsr[b]);
   	  		lblPrzedmiotSr[b].setForeground(new Color(128, 87, 140)); 
   	  		panelDni.add(lblPrzedmiotSr[b]);
   	  		przedmiotySrY += 45;
   	  		lblPrzedmiotSr[b].setFont(new Font("default", Font.BOLD, 15));
		}
   	  		
   	  		int przedmiotyCzwX = 390+145;
   	  		int przedmiotyCzwY = 30;
   	  		JLabel[] lblPrzedmiotCz = new JLabel[planZajec3Bcz.length];
   	  		for (int b = 0; b < planZajec3Bcz.length; b++)
		{
   	  		lblPrzedmiotCz[b] = new JLabel();
   	  		lblPrzedmiotCz[b].setBounds(przedmiotyCzwX , przedmiotyCzwY ,340,50); 
   	  		lblPrzedmiotCz[b].setText(planZajec3Bcz[b]);
   	  		lblPrzedmiotCz[b].setForeground(new Color(128, 87, 140)); 
   	  		panelDni.add(lblPrzedmiotCz[b]);
   	  		przedmiotyCzwY += 45;
   	  		lblPrzedmiotCz[b].setFont(new Font("default", Font.BOLD, 15));
		}
   	  		
   	  		int przedmiotyPtX = 390+290;
   	  		int przedmiotyPtY = 30;
   	  		JLabel[] lblPrzedmiotPt = new JLabel[planZajec3Bpt.length];
   	  		for (int b = 0; b < planZajec3Bpn.length; b++)
		{
   	  		lblPrzedmiotPt[b] = new JLabel();
   	  		lblPrzedmiotPt[b].setBounds(przedmiotyPtX , przedmiotyPtY ,340,50); 
   	  		lblPrzedmiotPt[b].setText(planZajec3Bpt[b]);
   	 		lblPrzedmiotPt[b].setForeground(new Color(128, 87, 140)); 
   	  		panelDni.add(lblPrzedmiotPt[b]);
   	  		przedmiotyPtY += 45;
   	  		lblPrzedmiotPt[b].setFont(new Font("default", Font.BOLD, 15));
		}
   	  		
   	  		JLabel lblZarzadzajPlanem = new JLabel("Edytuj plan lekcyjny");
   	  		lblZarzadzajPlanem.setFont(new Font("default", Font.BOLD, 19)); 
   	  		JPanel panelZarzadzaj = new JPanel();
   	  		panelZarzadzaj.setBounds(900, 70, 500, 30);
   	  		panelZarzadzaj.setBackground(Color.LIGHT_GRAY); 
   	  		panelZarzadzaj.add(lblZarzadzajPlanem);
   	  		
   	  		JLabel lblWprowadzZmiany = new JLabel("Dzien");
   	  		lblWprowadzZmiany.setBounds(900, 110, 500, 30);
   	  		lblWprowadzZmiany.setFont(new Font("default", Font.BOLD, 16));
   	  		
   	  		JLabel lblgodzina = new JLabel("Godzina:");
   	  		lblgodzina.setBounds(870, 140, 500, 30);
   	  		lblgodzina.setFont(new Font("default", Font.BOLD, 16));
   	  		lblgodzina.setVisible(false);
   	  		
   	  		
   	  		JComboBox comboGodziny = new JComboBox(godzinyZajec);
   	   		comboGodziny.setBounds(955, 140, 400, 30);
   	  		comboGodziny.setVisible(false );
   	  				
   	  		JLabel lblAktualny = new JLabel("Aktualnie jest to:");
   	  		lblAktualny.setBounds(840, 170, 500, 30);
   	  		lblAktualny.setFont(new Font("default", Font.BOLD, 16));
   	  		lblAktualny.setVisible(false);
   	  		
   	  		JPanel pEdycji = new JPanel(); 
     	  	GridLayout layoutEdycja = new GridLayout(3,1);
     	  	
   	  		JLabel wybierzDzien = new JLabel("Wybierz dzieñ: ");
   	  		JLabel wybierzGodzine = new JLabel("Wybierz godzine: ");
   	  		JLabel nowaWartosc = new JLabel("Nowy przedmiot: ");
   	  		
   	  		JTextField btnWybierzDzien = new JTextField();
   	  		JTextField btnWybierzGodzine = new JTextField();
   	  		JTextField btnNowaWartosc = new JTextField();
   	  		
   	  		JButton bWprowadzZmiany = new JButton("Wprowadz zmiany");
   	  		
   	  		int[] zakresDni = {};
   	  		//wypelnienie 'mozliwosci' liczbami z zakresu liczby dni
//   	  		for(int i = 0; i < dniTygodnia.length; i++)
//   	  		{ 
//				System.out.println(i+1);
//				zakresDni[i] = i+1;
//				
//   	  		}
   	  		
   	  		frame.add(comboGodziny);
   	  		JComboBox comboDzien = new JComboBox(dniTygodnia);
   	  		comboDzien.setBounds(955, 110, 400, 30);
   	  		comboDzien.addActionListener (new ActionListener ()
        	{
        	    public void actionPerformed(ActionEvent e) {
        	    	{
//        	    		String oKlasie = "3x";
//        	    		System.out.println();
//        	    		//DodajZmiany
//        	    		zmienPlan(dzien, godzina, nowaWartosc, dniTygodnia, godzinyZajec, oKlasie);
        	    		//stworz dni tygodnia wypelnione numerami
        	    		
        	    		
        	    	
//        	    		//wyswietl je
//        	    		for(int i = 0; i< mozliwosci.length; i++)
//        	    		{
//        	    			System.out.println(mozliwosci[i]);
//        	    		}
        	    		
//        	    		int zmiennaOPeracyjnaDzien = 0;
//        	    		
//        	    		for(int y = 0; y < mozliwosci.length; y++)
//        	    		{
//        	    			if(comboDzien.getSelectedIndex() == mozliwosci[y]);
//        	    			zmiennaOPeracyjnaDzien = mozliwosci[y]; 
//        	    			break; 
//        	    		}
//
//    	    			System.out.println("Operujesz zmienna : " +  zmiennaOPeracyjnaDzien);
//        	    		 if()
//        	    		   
//        	               if(comboDzien.getSelectedIndex() == 0 )
//        	               {
//        	               			 comboGodziny.setVisible(true);
//   	  								   
//   	  									lblgodzina.setVisible(true); 
//            	              			lblAktualny.setBounds(950, 170, 500, 30);
//            	              		 lblAktualny.setVisible(true);
//         	         			   
//        	               }
//        	          	   if(comboDzien.getSelectedIndex() == 1 )	
//        	          	   {
//        	          	 			comboGodziny.setVisible(true);
//   	  							lblgodzina.setVisible(true); 
//            	              		lblAktualny.setBounds(950, 170, 500, 30);
//            	              		lblAktualny.setVisible(true);
//        	          	   }                 
        	    	}
        	    }
        	});
   	  		
   	  		JLabel lblNowaWartosc = new JLabel("Wprowadz nowy:   ");
   	  		lblNowaWartosc.setBounds(835 , 240, 400, 30);
   	  		lblNowaWartosc.setVisible(false);
   	  		frame.add(lblNowaWartosc);
   	  		
   	  		JTextField txtNowy = new JTextField();
   	  		txtNowy.setBounds(955, 240, 400, 30);
   	  		txtNowy.setVisible(false);
   	  	
   	  		
   	  		JButton btnZapiszZmiany = new JButton("Zapisz zmiany");
        	btnZapiszZmiany.setBounds(835 , 280, 400, 30);
        		btnZapiszZmiany.setVisible(false);
   	  		comboGodziny.addActionListener (new ActionListener ()
        	{
        	    public void actionPerformed(ActionEvent e) {
        	    	{
        	    		   
        	               if(comboGodziny.getSelectedIndex() == 1 )
        	               {
        	               			String staraWartosc = planZajec3Bpn[1];
        	               			lblAktualny.setText("Obecnie jest to " + staraWartosc  ); 
   	  								lblNowaWartosc.setVisible(true);
   	  								txtNowy.setVisible(true);
   	  								btnZapiszZmiany.setVisible(true);
        	               }
        	                       	                 
        	    	}
        	    }
        	});
        	
        	btnZapiszZmiany.addActionListener(new ActionListener()
    		        {
    		            public void actionPerformed(ActionEvent e)
    		            {
    		           		lblNowaWartosc.setVisible(false);
   	  						txtNowy.setVisible(false);
   	  						btnZapiszZmiany.setVisible(false);
   	  						lblgodzina.setVisible(false);  
            	            lblAktualny.setVisible(false);
            	            comboGodziny.setVisible(false);
            	            System.out.println(planZajec3Bpn[1]);
            	            planZajec3Bpn[1] = txtNowy.getText();
            	             System.out.println(planZajec3Bpn[1]);
            	             frame.dispose();
            	             wczytajDane(folderName, firstName, lastName, listOfAllClass, listClassIteach, KlasaWychowankowie);
    		            }
    		});
        	
        	JButton btnPowrot = new JButton("Zamknij i wróæ do strony startowej");
        	btnPowrot.setBackground(Color.white);
        	btnPowrot.addActionListener(new ActionListener()
    		        {
    		            public void actionPerformed(ActionEvent e)
    		            {
    		           		 
            	             GuiMain gm = new GuiMain();
            	             gm.loadDataAndRunApp(folderName);
    		            }
    		});
        	frame.add(btnPowrot, BorderLayout.SOUTH); 
      		frame.add(txtNowy);
        	frame.add(btnZapiszZmiany); 
   	  		frame.add(lblAktualny);
   	  		frame.add(lblgodzina);
   	  		frame.add(comboDzien);
   	  		frame.add(lblWprowadzZmiany);
   	  		frame.add(panelZarzadzaj);
    		frame.add(p1, BorderLayout.NORTH);
    		frame.add(panelDni); 
    		frame.setVisible(true);
    	 
 	
    	
    		
//    		for (int i = 0 ; i < uczeKlasy.length ; i ++)
//			{
//				System.out.println(uczeKlasy[i]); 
//				System.out.println("*TwojeKlasy.java* przyjmuje parametry klasy: " + uczeKlasy[i]);
//			}
    	} // ---koniec: private void create GUI(String str) 
        
    	public void zmienPlan(String dzien, String godzina, String nowaWartosc2, String[] dniTygodnia, String[] godzinyZajec, String nazwaZmienianejKlasy)
    	{
    		
    	}
    	
		// main 
    	public static final void main(String[] args) throws InterruptedException 
    	{ 
    		javax.swing.SwingUtilities.invokeLater(new Runnable()
    		{
    		public void run()
    			{
    			  
    				PlanLekcji gm = new PlanLekcji(); 
    				gm.wczytajDane("Michal Klich", null, null, null, null, null);
    				 
    			} 
    		});
    	}
    		 
//    	public static boolean czyNauczycielMaJakiesKlasy(String[] arr) {
//     
//    		if (arr == null) {
//    		  System.out.println("Nauczyciel nie posiada ¿adnych klas."); 
//    		}
//    		return arr;
//    	}
    	
    		public void wczytajDane(String folderName, String firstName, String lastName, String[] listOfAllClass, String[] listClassIteach, String KlasaWychowankowie)
    		{  
    		   
    			createGui(folderName, firstName, lastName, listOfAllClass, listClassIteach, KlasaWychowankowie);
    		}
    		
 
			 
    


    
    	private void wysrodkujOkno(JFrame frame) {
    		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		 int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    		 int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
    		 frame.setLocation(x, y);
    	}
    
    	//uruchamia klase 'zarzadzanieKlasami.java'
    	
    } // end: TwojeKlasy.java
