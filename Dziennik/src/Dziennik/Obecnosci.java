package Dziennik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import Dziennik.GuiMain.MyComboBoxRenderer;
 

public class Obecnosci extends  JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int lp[];
	String[] imie;
	String[] nazwisko;
	String[] rubrykiOcen;
	double[] sredniaOcen;
	int[] ocenaProponowana;
	int[] ocenaKoncowa;
	private static JLabel lblSeparator;
	String wybranyDziennik ;
	JButton btnZapisz, btnEksportuj;
	JRadioButton[] radiobtn;
	JCheckBox checkObecny;
		private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj, bZapiszTabele;
    	private static JLabel lblInformacje, lblRokSzkolny, lblAktualnyPrzedmiot, lblObecnychStudentow, lblSeparator2, lblTime,lblDay, lblVersion;
      
    	String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    	String strDay = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
  
    	final String zalogowanoJako = "Zalogowano jako: ";
		public String   wychowankowie, imieInazwisko, persona, userName, imieUzytkownika, nazwiskoUzytkownika;
		public String[] uczeKlasy, wszystkieKlasy;
		public String[] args = {};
		//zmienna przechowujaca przedmioty tej klasy, w konstruktorze dopisac by pobieral te przedmioty z plku
		public String[] przedmioty = {"Polski", "Geografia", "Matematyka"};
		JRadioButton rb1;
		JScrollPane scrollPaneTable;
		public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
            public void run() { 
            Obecnosci dziennikocen = new Obecnosci();
            
            // (String iN, String imieUser, String nazwiskoUser, String[] arKlasyWszystkie, String[] arUczeKLasy, String strUczeKlase, String strWybranyDziennik) 
            dziennikocen.createGui("fooImie fooNazwisko", "fooImie", "fooNazwisko", null, null, "uczeKlaseFoo", "uczeKlaseFoo");
            	
            }
        });
	}
	
	public void pobierzInfo(String nazwaZalogowanego, String tab)
	{
		System.out.println(tab);
	}
	
	public JMenuBar createJMenuBar()  
    	{
    		JMenuBar mainMenuBar;
    		JMenu menuPlik, menuWidok, menuNowy, menuNarzedzia, menuPomoc;
    		JMenuItem NowyMenuItem, ZapiszMenuItem, Odswie¿MenuItem, DrukujMenuItem, WylogujMenuItem, WyjdzMenuItem ;
    		
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
	
	public void createGui(String iN, String imieUser, String nazwiskoUser, String[] arKlasyWszystkie, String[] arUczeKLasy, String strWychowankowie, String strWybrDziennik) 
    { 
			imieInazwisko = iN; 
			imieUzytkownika = imieUser;
			nazwiskoUzytkownika = nazwiskoUser;
    		wszystkieKlasy = arKlasyWszystkie;
    		uczeKlasy = arUczeKLasy;
    		wychowankowie = strWychowankowie;
    		  wybranyDziennik = strWybrDziennik;
    		persona = imieUser + " " + nazwiskoUser; 
    		userName = iN;
     
    		JFrame frame = new JFrame("Dziennik Elektroniczny - Dziennik ocen"); 
    		GuiMain app = new GuiMain();
    		frame.setJMenuBar(app.createJMenuBar()); 
     		frame.setResizable(true);
     		   
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
    		/**
     	    * Create Panel1
     		*/
    		JPanel p1 = new JPanel();
    		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS)); // ustawienie ikon w lini poziomej
    		p1.setBackground(Color.WHITE);
   
    		JLabel lblLogo = new JLabel();
    		lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		lblLogo.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logo4m.png")));
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
                	
                	//todo: nieprzejrzana metoda, wiec zakomentowana
//                	TwojeKlasy tk = new TwojeKlasy();
//					tk.wczytajDane(iN, imieUzytkownika, nazwiskoUzytkownika, arKlasyWszystkie, arUczeKLasy, strWychowankowie, wybranyDziennik);
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
    				Logowanie.runClassLogowanie(null, null);
    			}
    		});
    		
    		//tworzenie podkreslenia
    		

	    	
			 
    		JButton btnZamknijDziennik = new JButton("Zamknij okno i powróæ.");
    		btnZamknijDziennik.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		btnZamknijDziennik.setFont(btnZamknijDziennik.getFont().deriveFont(11.0f));
    		//clearSelectionButton.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/homepage.png")));
    		btnZamknijDziennik.setBackground(Color.WHITE);
    		btnZamknijDziennik.setPreferredSize(new Dimension(138, 48));
    	
    		btnZamknijDziennik.setOpaque(false);
    		btnZamknijDziennik.setBorder(null);
    		btnZamknijDziennik.setContentAreaFilled(false);
    		btnZamknijDziennik.setBorderPainted(false);
    		btnZamknijDziennik.setForeground(blueColor);
    		btnZamknijDziennik.setVisible(true);
       		 btnZamknijDziennik.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				{
    					GuiMain gm = new GuiMain();
    					gm.loadDataAndRunApp(persona);
    					
    				}
    			}
    		});
    
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
    		p2.setVisible(true);
    		p2.setBounds(1, 61, 1360, 50);
    		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));  
    		
    		JPanel p3 = new JPanel();
    		p3.setVisible(true);
    		p3.setBounds(1, 61, 1360, 50);
    		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS)); 
    		
//    		JButton[] btnDlaKlasy = null;
//    		for(int i = 0 ; i < uczeKlasy.length; i++)
//    		{
//    			btnDlaKlasy[i] = new JButton("s");
//    			p3.add(btnDlaKlasy[i]);
//    		}
    		
//    		int iloscRadioBtn;
//    		System.out.println("uczeklasy wynosi:" +  arUczeKLasy.length);
//    		 
//    		for(  iloscRadioBtn = 0; iloscRadioBtn < arUczeKLasy.length; iloscRadioBtn ++)
//    		{ 
//    			radiobtn[iloscRadioBtn] = new JRadioButton("wq");
//    			p3.add(radiobtn[iloscRadioBtn]); 
//    		}
//    		
//    		JRadioButton rdbtn = new JRadioButton("eee");
//    		p3.add(rdbtn);
    		
//    		radiobtn = new JRadioButton();
//    	    JRadioButton male = new JRadioButton("male");
//    	     JRadioButton female = new JRadioButton("Female");
//    	     ButtonGroup bG = new ButtonGroup();
//    	     bG.add(male);
//    	     bG.add(female);
    		
    		
    		// start: cbx1 
    		final JComboBox cbx1 = new JComboBox(opt1);
    		cbx1.setRenderer(new MyComboBoxRenderer1("Lekcje"));
    		cbx1.setSelectedIndex(-1);
    		cbx1.setEnabled(false);
    		cbx1.setBackground(Color.WHITE);
    		cbx1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				{

    					// Object selected = cbx2.getSelectedItem();
    					if (cbx1.getSelectedIndex() == 0) {
    						System.out.println("Wybrano: Lista uczniow");
    						try {
    							//PlanLekcji pl = new PlanLekcji();
    							//pl.wczytajDane(imieInazwisko, imieUzytkownika,
    							//		nazwiskoUzytkownika, listOfAllClass,
    							//		listClassIteach, KlasaWychowankowie);
    							//frame.dispose();
    						} catch (Exception ex) {
    							System.out.println("Blad przy wyborze cbx1" + ex);
    						}
    					}

    				}
    			}
    		});
    		p2.add(cbx1);
    		// end: cbx1

    		// start: cbx2

    		final JComboBox cbx2 = new JComboBox(opt2);
    		cbx2.setRenderer(new MyComboBoxRenderer2("Oceny"));
    		cbx2.setSelectedIndex(-1);
    		cbx2.setBackground(Color.WHITE);
    		cbx2.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				{
    					// Object selected = cbx2.getSelectedItem();
    					if (cbx2.getSelectedIndex() == 0) {
//    						System.out.println("Uruchomiono: Oceny semestr I");
//    						String wybranaKlasa = comboBox.getSelectedItem().toString();
//     
//    						DziennikOcen dziennikOcen = new DziennikOcen();
//    						dziennikOcen.createGui(imieInazwisko, imieUzytkownika,
//    								nazwiskoUzytkownika, listOfAllClass,
//    								listClassIteach, KlasaWychowankowie, wybranaKlasa  );
//    						System.out.println("Wybrana klasa to: " + imieInazwisko);
//    						System.out.println("Wybrana klasa to: " + imieUzytkownika);
//    						System.out.println("Wybrana klasa to: " + nazwiskoUzytkownika);
//    						System.out.println("Wybrana klasa to: " + listOfAllClass);
//    						System.out.println("Wybrana klasa to: " + listClassIteach[]);
//    						System.out.println("Wybrana klasa to: " + KlasaWychowankowie);
//    						System.out.println("Wybrana klasa to: " + wybranaKlasa);
//    						
    						
    					}

    					if (cbx2.getSelectedIndex() == 1) {
    						System.out.println("Wybrano: sem II");
    					}
    					if (cbx2.getSelectedIndex() == 2) {
    						System.out.println("Wybrano: oceny na wywiadowke");
    					}
    					// String choosenClass = (String)
    					// comboBox.getSelectedItem();
    					// frame.dispose();
    					// OcenyRUN( choosenClass, true) ;
    				}
    			}
    		});
    		p2.add(cbx2);
    		// end: cbx2

    		// start: cbx3

    		final JComboBox cbx3 = new JComboBox(opt3);
    		cbx3.setRenderer(new MyComboBoxRenderer3("Obecnosci"));
    		cbx3.setSelectedIndex(-1);
    		cbx3.setBackground(Color.WHITE);
    		cbx3.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				{
    					if (cbx3.getSelectedIndex() == 0) {
    						String przedmiot = opt3[0];
    						//ListaObecnosci listaObecnosci = new ListaObecnosci();
    						ListaObecnosci.createGui(imieInazwisko, strDay, przedmiot);
    					}

    					if (cbx3.getSelectedIndex() == 1) {
    						// cos ..
    					}
    					if (cbx3.getSelectedIndex() == 2) {
    						// cos ..
    					}
    					// String choosenClass = (String)
    					// comboBox.getSelectedItem();
    					// frame.dispose();
    					// OcenyRUN( choosenClass, true) ;
    				}
    			}
    		});
    		p2.add(cbx3);

    		// end: cbx3

    		// start: cbx4
    		final JComboBox cbx4 = new JComboBox(opt4);
    		cbx4.setRenderer(new MyComboBoxRenderer4("Uczniowie"));
    		cbx4.setSelectedIndex(-1);
    		cbx4.setBackground(Color.WHITE);
    		cbx4.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				{

    					// Object selected = cbx2.getSelectedItem();
    					if (cbx4.getSelectedIndex() == 0) {
    						System.out.println("Wybrano: Lista uczniow");
    						try {
    							ListaUczniow.main(args);
    						} catch (Exception ex) {
    							System.out.println("Blad przy wyborze cbx2" + ex);
    						}
    					}

    					if (cbx4.getSelectedIndex() == 1) {
    						frame.setVisible(false);
    						AddStudent.main(args);
    						System.out.println("Wybrano: Dodaj ucznia");
    					}

    					// String choosenClass = (String)
    					// comboBox.getSelectedItem();
    					// frame.dispose();
    					// OcenyRUN( choosenClass, true) ;
    				}
    			}
    		});
    		p2.add(cbx4);
    		// end: cbx4

    		// start: cbx5
    		final JComboBox cbx5 = new JComboBox(opt5);
    		cbx5.setRenderer(new MyComboBoxRenderer5("Zachowanie i uwagi"));
    		cbx5.setSelectedIndex(-1);
    		cbx5.setBackground(Color.WHITE);
    		p2.add(cbx5);
    		// end: cbx5

    		// start: cbx6
    		final JComboBox cbx6 = new JComboBox(opt6);
    		cbx6.setRenderer(new MyComboBoxRenderer6("Raporty i zestawienia"));
    		cbx6.setSelectedIndex(-1);
    		cbx6.setBackground(Color.WHITE);
    		p2.add(cbx6);
    		// end: cbx6

    		// start: cbx7
    		final JComboBox cbx7 = new JComboBox(opt7);
    		cbx7.setRenderer(new MyComboBoxRenderer6("Zarz¹dzaj"));
    		cbx7.setSelectedIndex(-1);
    		cbx7.setBackground(Color.WHITE);
    		p2.add(cbx7);
    		// end: cbx7

    		// start: cbx8
    		final JComboBox cbx8 = new JComboBox(opt8);
    		cbx8.setRenderer(new MyComboBoxRenderer6("Wyszukaj"));
    		cbx8.setSelectedIndex(-1);
    		cbx8.setBackground(Color.WHITE);
    		p2.add(cbx8);
    		// end: cbx8

    		cbx1.setEnabled(false);
    		cbx2.setEnabled(false);
    		cbx3.setEnabled(false);
    		cbx4.setEnabled(false);
    		cbx5.setEnabled(false);
    		cbx6.setEnabled(false);
    		cbx7.setEnabled(false);

    		lblSeparator = new JLabel();
    		lblSeparator.setIcon(new ImageIcon(Wyszukiwanie.class
    				.getResource("/res/sep1.png")));
    		lblSeparator.setBackground(Color.WHITE);
    		lblSeparator.setPreferredSize(new Dimension(10, 48));

    		lblSeparator2 = new JLabel();
    		lblSeparator2.setPreferredSize(new Dimension(10, 48));
    		lblSeparator2.setIcon(new ImageIcon(Wyszukiwanie.class
    				.getResource("/res/sep1.png")));
    		lblSeparator2.setBackground(Color.WHITE);

    		bModulKlas = new JButton("Zarz¹dzaj klas¹");
    		bModulKlas.setBackground(Color.WHITE);
    		bModulKlas.setPreferredSize(new Dimension(240, 30));
    		bModulKlas.setIcon(new ImageIcon(Wyszukiwanie.class
    				.getResource("/res/klasIcon.png")));
    		bModulKlas.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				//ZarzadzanieKlasami(imieInazwisko);
    				//frame.setVisible(false);
    			}
    		});
    		
    		List<String> list;
    	try
    	{  
		
		//TODO: zostala zmieniona ponizsza linijka  z list = Files.readAllLines(Paths.get("C:/dziennik/users/Michal Klich/studenci.txt" ), StandardCharsets.UTF_8); na:
		// i nie dziala bo brak pliku txt studenci. stworzyc wyjatek
		//File fStudenci = new File("C:/dziennik/users/Michal Klich/studenci.txt" );
    		System.out.println("Wyswietl mi tu tá persone " + persona);
		list = Files.readAllLines(Paths.get("C://DziennikElektroniczny//users/" + persona + "//klasyInfo//"+ wybranyDziennik + "/studenci.txt" ), StandardCharsets.UTF_8);
		System.out.println("Komunikat testowy 1");
		String[] personaliaUczniow = list.toArray(new String[list.size()]); 
		int dl = personaliaUczniow.length;
		String[] imiona = new String[dl];
		String[] nazwiska = new String[dl];
		 
		for(int i = 0;i<dl;i++)
		{
			String string = personaliaUczniow[i];
			String[] parts = string.split(" ");
			imiona[i] = parts[0];   
			nazwiska[i] = parts[1];  
		}
		
		int[] lp = new int[nazwiska.length];
		for(int i = 0; i<nazwiska.length; i++)
		{
			 lp[i] = i+1; 
		}
    	
		//tworzenie nazw nag³owków tabeli, i 2wymiarowej tablicy pobieraj¹cej personalia studentów i umieszcza ich w tabeli DataEntries
		String[] columnTitles = { "Lp.", "Imie", "Nazwisko", "Obecny", "Nieobecny", "Usprawiedliwiony", "Zwolniony"  }; 
	    Object[][] dataEntries = new Object[dl][7];
	    
	    Student sss = new Student();
	    JCheckBox cb1 = new JCheckBox( );
	    JCheckBox cb2 = new JCheckBox( );
	    JCheckBox cb3 = new JCheckBox( );
	    JCheckBox cb4 = new JCheckBox( );
	    JCheckBox cb5 = new JCheckBox( );
	    JCheckBox cb6 = new JCheckBox( );
	    JCheckBox cb7 = new JCheckBox( );
	    
	    
	    for(int i = 0 ; i<imiona.length ; i++)
	    {
	    	dataEntries[i][0] = lp[i] + ".";
	        dataEntries[i][1] = sss.getStudentFirstName(i, imieInazwisko, wybranyDziennik);
	        dataEntries[i][2] = nazwiska[i];
	        dataEntries[i][3] = "";
	        dataEntries[i][4] = "";
	        dataEntries[i][5] = "";
	        dataEntries[i][6] = ""; 
	    }
	    
	    
	    TableModel model = new EditableTableModel(columnTitles, dataEntries);
	    JTable table = new JTable(model);
	    table.setFont(new Font("Tahoma", Font.BOLD, 11));
	    table.createDefaultColumnsFromModel();
	    table.setRowHeight( 35);
  
	    //  String[] statusObecnosci = { "Obecny", "Nieobecny", "Spozniony", "Usprawiedliwiony" };
	    //  @SuppressWarnings("rawtypes")
		 //JComboBox comboBox1 = new JComboBox(statusObecnosci);
	
	    table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cb1));
	    table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cb2));
	    table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(cb3));
	    table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cb4));
	    table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cb5));
	    table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cb6));
	    table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cb7));
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.LEFT );
//	    table.getColumnModel().getColumn(8).setCellRenderer( centerRenderer ); 
//	    table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer ); 
//	    table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
//	    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
//	    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	    table.getTableHeader().setReorderingAllowed(false);
		
	    table.getColumnModel().getColumn(0).setPreferredWidth(30);
	    table.getColumnModel().getColumn(1).setPreferredWidth(300);
	    table.getColumnModel().getColumn(2).setPreferredWidth(300);
	    table.getColumnModel().getColumn(3).setPreferredWidth(135);
	    table.getColumnModel().getColumn(4).setPreferredWidth(170);
	    table.getColumnModel().getColumn(5).setPreferredWidth(200);
	    table.getColumnModel().getColumn(6).setPreferredWidth(90);
	 
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	    
		//scrollPane z tabela
		  scrollPaneTable = new JScrollPane(table);
		scrollPaneTable.getViewport().setBackground(Color.white);
		//scrollPaneTable.setBounds(400, 400, 450, 322);
		scrollPaneTable.setBackground(Color.red); 
		scrollPaneTable.setPreferredSize(new Dimension(600,340));
	     
		
		
		JPanel pInformacyjny = new JPanel();
		pInformacyjny.setLayout(new BorderLayout());
		
		JPanel pNarzedzi = new JPanel();
		pNarzedzi.setLayout(new BoxLayout(pNarzedzi, BoxLayout.Y_AXIS)); 
	 
		
		btnZapisz = new JButton("Zapisz");
		pNarzedzi.add(btnZapisz ); 
		pNarzedzi.add(Box.createRigidArea(new Dimension(5,5)));
		
		btnEksportuj = new JButton("Eksportuj");
		pNarzedzi.add(btnEksportuj);

		
		
		
		//tworzenie mojego layout'u: (3 rzedy, 1 kolumna)
		//TODO: zmieniæ na 'new GridLayout(3,1) i stwrzyæ trzeci panel z nawigacj¹. póki co new GridLayout (2,1).
	GridLayout experimentLayoutThreeOne = new GridLayout(0,1);
	FlowLayout flowLayout = new FlowLayout();
	
	
	GridLayout layoutZespolone = new GridLayout(1,2);
	
	//tworzenie panelu z trzema pierwszymi panelami 
	final JPanel panelDodajacyDoNorth = new JPanel();
	panelDodajacyDoNorth.setLayout(experimentLayoutThreeOne);
	
	JPanel pNawigacji = new JPanel();
	FlowLayout flowL = new FlowLayout(); 
	pNawigacji.setLayout(flowL);
	
	JPanel pWybierzKlase = new JPanel();
	pWybierzKlase.setLayout(flowL);
	
	
	JLabel lbl1 = new JLabel("Jesteœ tu: Strona domowa  =>");
	JLabel lbl2 = new JLabel(" Dziennik Obecnoœci: " + wybranyDziennik);
	
	pNawigacji.add(lbl1, BorderLayout.SOUTH); 
	pNawigacji.add(lbl2, BorderLayout.SOUTH); 
	//tworzenie panelu ktory zawiera panel narzedzi i panel tresci
	JPanel pZespolone = new JPanel();
	pZespolone.setLayout(layoutZespolone);
	
	
	JPanel pNarzedzia = new JPanel();
	pNarzedzia.setLayout(new BoxLayout(pNarzedzia, BoxLayout.Y_AXIS));
	
	
	JButton fakeButton = new JButton("Zapisz");
	pNarzedzia.add(fakeButton);
	
	
	//todo: przejrzeæ
	Border emptyBorder = BorderFactory.createEmptyBorder();
	JPanel pLewy = new JPanel();
	pLewy.setBackground(new Color(255, 255, 255));	
	pLewy.setLayout(null);  
	pLewy.setPreferredSize(new Dimension(225, 225));
	
	JScrollPane ss = new JScrollPane(); 
	ss.getViewport().setBackground(Color.WHITE);
	ss.setBounds(0, 1, 224, 520);
	ss.setBackground(Color.white);
	
	JLabel lblAkcja = new JLabel("Zarz¹dzaj");
	lblAkcja.setBounds(10, 0, 120, 30);
	lblAkcja.setBackground(Color.white);
	pLewy.add(lblAkcja);
	
	JButton b1 = new JButton("Nowa ocena");
	b1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {	 
			//runAddClass();
			
		}
	});
	b1.setBorder(emptyBorder);
	b1.setBounds(5, 30, 150, 24);
	b1.setBackground(Color.white);
	b1.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/addClassM.png")));
	pLewy.add(b1);
	
	JButton b2 = new JButton("Nowy uczeñ");
	b2.setBorder(emptyBorder);
	b2.setBounds(5, 60, 150, 24);
	b2.setBackground(Color.white);
	b2.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/addStudentM.png")));
	pLewy.add(b2);
	
	JButton b3 = new JButton("Usuñ ucznia");
	b3.setBorder(emptyBorder);
	b3.setBounds(5, 120, 150, 24);
	b3.setBackground(Color.white);
	b3.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/deleteStudentM.png")));
	pLewy.add(b3);
	
	JButton b4 = new JButton("Usuñ klase");
	b4.setBorder(emptyBorder);
	b4.setBounds(5, 150, 150, 24);
	b4.setBackground(Color.white);
	b4.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/deteleclassm.png")));
	pLewy.add(b4);
	
	JButton b5 = new JButton("Edytuj ucznia");
	b5.setBorder(emptyBorder);
	b5.setBounds(5, 210, 150, 24);
	b5.setBackground(Color.white);
	b5.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/editstudentm.png")));
	pLewy.add(b5);
	
	JButton b6 = new JButton("Edytuj klase");
	b6.setBorder(emptyBorder);
	b6.setBounds(5, 240, 150, 24);
	b6.setBackground(Color.white);
	b6.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/editclasm.png")));
	pLewy.add(b6);
	
	JLabel sep = new JLabel( );
	sep.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/sep.png")));
	sep.setBounds(5, 240, 244, 100);
	pLewy.add(sep);
	
	JLabel lblWidok = new JLabel("Widok");
	lblWidok.setBounds(10, 300, 120, 30);
	lblWidok.setBackground(Color.BLACK);
	pLewy.add(lblWidok);
	
	bZapiszTabele = new JButton("bZapiszTabele");
	bZapiszTabele.setBorder(emptyBorder);
	bZapiszTabele.setBounds(250, 410, 150, 30);
	bZapiszTabele.setBackground(Color.white);
	bZapiszTabele.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/save.png")));
	bZapiszTabele.addActionListener(this);
	pLewy.add(bZapiszTabele);
	
	rb1 = new JRadioButton("Tabela");
	rb1.setBounds(30, 330, 150, 24);
	rb1.setBackground(Color.WHITE);
	rb1.setSelected(true);
	rb1.addActionListener(this);
	pLewy.add(rb1);
	
	JRadioButton rb2 = new JRadioButton("Adres");
	rb2.setBounds(30, 360, 150, 24);
	rb2.setBackground(Color.WHITE);
	rb2.setSelected(true);
	pLewy.add(rb2);
	
	JRadioButton rb3 = new JRadioButton("Pesel");
	rb3.setBounds(30, 390, 150, 24);
	rb3.setBackground(Color.WHITE);
	rb3.setSelected(true);
	pLewy.add(rb3);
	/**
	 *  end: lewy panel
	 */
	
	
		//pZespolone.add(pLewy);
	
		JPanel pData = new JPanel();
		pData.setLayout(layoutZespolone);
		pData.add(scrollPaneTable);
	
		pZespolone.add(pData);
	
 
		FlowLayout pInfoLayout = new FlowLayout();
		
		JPanel pInfo = new JPanel();
		//pInfo.setLayout(pInfoLayout);
		pInfo.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton button;
		button = new JButton("Komunikat: Stworzono przycisk wyswietlajacy informacje o dzia³aniach u¿ytkownika w programie.");
		button.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;      //make this component tall
		c.ipadx = 1330;
		c.weightx = 0.0;
		c.gridwidth = 0;
		c.gridx = 0;
		c.gridy = 1;
		 
		button.setOpaque(true); 
		button.setContentAreaFilled(true);
		button.setBorderPainted(false);
		
		pInfo.add(button, c);
		
		//ustawianie preferowanych rozmiarow
		//panelDodajacyDoNorth.setPreferredSize(new Dimension(1360, 200));
	 
	  	/**
	  	 * Zapisywanie ocen
	  	 */
		JButton btnZapiszOceny = new JButton("Zapisz obecnoœci");
		btnZapiszOceny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	 
			  
				String strOceny ="C://DziennikElektroniczny/users//" + imieUzytkownika + " " + nazwiskoUzytkownika + "//klasyInfo//" + wybranyDziennik + "//oceny.txt";  
			    File fOceny = new File(strOceny);
				PrintWriter os; 
				 
				PrintWriter writer;
				try {
					writer = new PrintWriter(fOceny);
					writer.print("");
					writer.close();
				} catch (FileNotFoundException e2) 
				{
					e2.printStackTrace();
				}
				
				
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fOceny, true))))
				{
				
					for (int row = 0; row < table.getRowCount(); row++) {
				    for (int col = 0; col < table.getColumnCount(); col++) {
				    	out.println(table.getColumnName(col));
				    	out.print(": ");
				    	out.println(table.getValueAt(row, col));
				    	
				    }
					
					}
					JOptionPane.showMessageDialog(null, "Pomyœlnie dodano wartoœæ z tabeli do oceny.txt"); 
				} 
						  catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
			}
		 
		});
		
		p3.add(btnZapiszOceny);
		
		
		/**
		 * ODczytaj przedmioty klasy któr¹ uzytkownik wybra³
		 */
		String strPrzedmioty = "C:/DziennikElektroniczny/users/" + imieUzytkownika + " " + nazwiskoUzytkownika +"/klasyInfo/" + wybranyDziennik +  "/przedmioty.txt";
		File fPrzedmioty = new File(strPrzedmioty); 
		 JComboBox comboBox;
		int liczbaPrzedmiotow;
		BufferedReader in = new BufferedReader(new FileReader( strPrzedmioty));
		String[] przedmiotyKlasy;
		Scanner fileIn;
		List<String> list1;
		
		if (fPrzedmioty.exists() && !fPrzedmioty.isDirectory()) 
		{
			if (fPrzedmioty.length() != 0)
				try {

					// oblicz ile uzytkownik ma klas(czyli ile wierszy jest w
					// pliku uczyKlasy.txt)
					liczbaPrzedmiotow = 0;
					// BufferedReader in = new BufferedReader(new
					// FileReader(fKlasy.toString()));
					
					while (in.readLine() != null)
						liczbaPrzedmiotow++;
					// in.close();
					System.out.println("Plik ma lini: " + liczbaPrzedmiotow);

					// stworz zmienna do przechowywania klas
				    przedmiotyKlasy = new String[liczbaPrzedmiotow];
					System.out.println("Stworzono zmienna 'string uczyKlasy'");

					// Dodaj klasy z pliku txt do zmiennej tablicowej String
					  fileIn = new Scanner(new File(strPrzedmioty));
					System.out.println("Stworzono zmienna 'Scanner fileIn '");

					 list1 = new ArrayList<String>();

					System.out.println("Stworzono zmienna 'List<String> lines ' i 'line'");

					// stara petla:
					while (fileIn.hasNext()) {
						list1.add(fileIn.next());
					}
					System.out.println("INFO: Lista przedmiotow w tej klasie:"
							+ Arrays.toString(list1.toArray()));
					fileIn.close();

					try {
						przedmiotyKlasy = list1.toArray(new String[list1.size()]);
						System.out.println("INFO: Przekonwertowano listê przedmiotów do tablicy.");
					} catch (Exception e3) {
						System.out.println("Nie udalo sie przekonwertowaæ");
					}
					System.out.println("przekonwertowano liste do tablicy'");

					for (int i = 0; i < przedmioty.length; i++) {
						System.out.println("INFO: Zawartoœæ 'uczyKlasy[]': " + przedmiotyKlasy[i]);
					}

					System.out.println("Zamknieto strumien I/o");
					
					comboBox = new JComboBox(przedmiotyKlasy);
					comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
					comboBox.setRenderer(new MyComboBoxRenderer("Wybierz klasê której obecnoœæi chcesz zobaczyæ"));
					comboBox.setPreferredSize(new Dimension(190, 30));
					comboBox.setSelectedIndex(-1); // By default it selects first item, we
															// don't want any selection
					comboBox.setBorder(null);
					comboBox.setOpaque(false);
					comboBox.setBackground(Color.WHITE);
					
					pNawigacji.add(comboBox);
					
				} catch (FileNotFoundException e) 
			{
					System.out .print("Plik z list¹ klas nie jest pusty i b³¹d przy odczytywaniu go. "+ e);
			}
			finally{
				
			
				 
			}
			
			//end if 1
		} // end if 0
		
		panelDodajacyDoNorth.add(p1); // dodanie p1 ktory jest boxlayoutem 
		panelDodajacyDoNorth.add(p2);
		panelDodajacyDoNorth.add(pNawigacji);
		panelDodajacyDoNorth.add(p3);
				
		frame.add(panelDodajacyDoNorth, BorderLayout.NORTH);
		frame.add(pZespolone, BorderLayout.CENTER);
		frame.add(pInfo, BorderLayout.SOUTH);
		
		 
		
		//frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
        frame.setVisible(true);
        frame.setSize(1360, 750);
		
		//Add komponentów to experiment with Grid Layout
	
	    
	    
    	}
		catch (Exception ex) {
			System.out.println(ex);
			System.out.println("persona:" + persona);
		}
	
    }
	

	
	public void zapiszTabele(ActionEvent e) 
	{
		System.out.println(persona);
		System.out.println(userName);
	}
	
	class EditableTableModel extends AbstractTableModel 
	{
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		String[] columnTitles;

		  Object[][] dataEntries;

		  int rowCount;

		  public EditableTableModel(String[] columnTitles, Object[][] dataEntries) {
		    this.columnTitles = columnTitles;
		    this.dataEntries = dataEntries;
		  }

		  public int getRowCount() {
		    return dataEntries.length;
		  }

		  public int getColumnCount() {
		    return columnTitles.length;
		  }

		  public Object getValueAt(int row, int column) {
		    return dataEntries[row][column];
		  }

		  public String getColumnName(int column) {
		    return columnTitles[column];
		  }

		  public Class getColumnClass(int column) {
		    return getValueAt(0, column).getClass();
		  }

		  public boolean isCellEditable(int row, int column) {
		    return true;
		  }

		  public void setValueAt(Object value, int row, int column) {
		    dataEntries[row][column] = value;
		  }
		  
}
	
	String getDziennikOcenInfo()
	{
		return lp + ": " + imie + "nazwisko: " + nazwisko;
	}
	
	class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer(String title)
		{
			_title = title;  
		}

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null) {
				setText(_title);
			}

			else {
				setText(value.toString());
			}
			return this;
		}

	}

	// ----------------END: combox 0 ---------------

	// -----------------START: Combobox 1---------------------
	String[] opt1 = { "Wyswietl plan lekcji" };

	class MyComboBoxRenderer1 extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer1(String title) {
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}

	// -----------------KONIEC: Combobox 1---------------------

	// -----------------START: Combobox 2---------------------
	String[] opt2 = { "Semestr I", "Semestr II", "Wydrukuj oceny na wywiadówke" };

	class MyComboBoxRenderer2 extends JLabel implements ListCellRenderer {

		private String _title;

		public MyComboBoxRenderer2(String title) {
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}

	// -----------------KONIEC: Combobox 2---------------------

	// -----------------START: Combobox 3---------------------
	// String[] opt3 = {"Bie¿¹cy tydzieñ", "Poprzedni tydzieñ" ,
	// "Frekwencja Semestr I", "Frekwencja Semestr II", "Polski", "Matematyka",
	// "Geografia", przedmioty[0]};
	String[] opt3 = { "Polski", "Matematyka", "Geografia", przedmioty[0] };

	class MyComboBoxRenderer3 extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer3(String title) {
			_title = title;
		}

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}

	// -----------------KONIEC: Combobox 3---------------------

	// -----------------START: Combobox 4---------------------
	String[] opt4 = { "Lista uczniów", "Dodaj ucznia" }; // opcjonalnie dodaæ
															// tutaj wszystkich
															// uczniów by byli
															// wyœwietleni od
															// razu w comboxie

	class MyComboBoxRenderer4 extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer4(String title) {
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}

	// -----------------KONIEC: Combobox 4---------------------

	// -----------------START: Combobox 5---------------------
	String[] opt5 = { "Dziennik uwag", "Dodaj now¹ uwagê",
			"Podgl¹d ocen z zachowania, semestr I",
			"Podgl¹d ocen z zachowania, semestr II" }; // opcjonalnie dodaæ
														// tutaj wszystkich
														// uczniów by byli
														// wyœwietleni od razu w
														// comboxie

	class MyComboBoxRenderer5 extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer5(String title) {
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}

	// -----------------KONIEC: Combobox 5---------------------

	// -----------------START: Combobox 6---------------------
	String[] opt6 = { "Statystyki klasy", "Podsumowanie ocen semestr I",
			"Podsumowanie ocen semestr I", "Podsumowanie ocen semestr II",
			"Zestawienie frekwencji" }; // opcjonalnie dodaæ tutaj wszystkich
										// uczniów by byli wyœwietleni od razu w
										// comboxie

	class MyComboBoxRenderer6 extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer6(String title) {
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}

	// -----------------KONIEC: Combobox 6---------------------

	// -----------------START: Combobox 6---------------------
	String[] opt7 = { "Zarz¹dzaj klas¹", "Zarz¹dzaj ocenami",
			"Zarz¹dzaj obecnoœciami", "Odwo³aj lekcje", "Zaplanuj wywiadówkê",
			"Zaplanuj wycieczkê" }; // opcjonalnie dodaæ tutaj wszystkich
									// uczniów by byli wyœwietleni od razu w
									// comboxie

	class MyComboBoxRenderer7 extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer7(String title) {
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}

	// -----------------KONIEC: Combobox 6---------------------

	// -----------------START: Combobox 6---------------------
	String[] opt8 = { "Wyszukaj ucznia", "Wyszukiwanie zaawansowane" }; // opcjonalnie
																		// dodaæ
																		// tutaj
																		// wszystkich
																		// uczniów
																		// by
																		// byli
																		// wyœwietleni
																		// od
																		// razu
																		// w
																		// comboxie

	class MyComboBoxRenderer8 extends JLabel implements ListCellRenderer {
		private String _title;

		public MyComboBoxRenderer8(String title) {
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			if (index == -1 && value == null)
				setText(_title);
			else
				setText(value.toString());
			return this;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
