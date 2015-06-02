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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import Dziennik.GuiMain.MyComboBoxRenderer;
import Dziennik.GuiMain.MyComboBoxRenderer1;

public class DziennikOcen extends  JFrame implements ActionListener{
	
	int lp[];
	String[] imie;
	String[] nazwisko;
	String[] rubrykiOcen;
	double[] sredniaOcen;
	int[] ocenaProponowana;
	int[] ocenaKoncowa;
	
	JButton btnZapisz, btnEksportuj;
		private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj, bZapiszTabele;
    	private static JLabel lblInformacje, lblRokSzkolny, lblAktualnyPrzedmiot, lblObecnychStudentow, lblSeparator,lblSeparator2, lblTime,lblDay, lblVersion;
      
    	String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    	String strDay = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
  
    	final String zalogowanoJako = "Zalogowano jako: ";
		public String   wychowankowie, nazwaFolderuUzytkownika, persona, userName;
		public String[] uczeKlasy, wszystkieKlasy;
		public String[] args = {};
		//zmienna przechowujaca przedmioty tej klasy, w konstruktorze dopisac by pobieral te przedmioty z plku
		public String[] przedmioty = {"Polski", "Geografia", "Matematyka"};
		JRadioButton rb1;
		JScrollPane scrollPaneTable;
		private String[] labelString = {"WprowadŸ ocene", "Dane ucznia",  "Nazwa kolumny", "Obliczenia"};
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
            public void run() { 
            	 
            	 DziennikOcen dziennikocen = new DziennikOcen();
            	 //parametry po kolei: 1.String folderName, 2.imie, 3.nazwisko, String[] listOfAllClass, String[] listClassIteach, String KlasaWychowankowie) 
            	  dziennikocen.createGui("Michal Klich", "Michal Klich", "Michal Klich", null, null, null);
 				 
            	 //Komenda wyzej zakomentowana bo po wcisnieciu przycisku "dodaj klase" okno uruchamia sie dwa razy  
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
	
	public void createGui(String folderName, String firstName, String lastName, String[] listOfAllClass, String[] listClassIteach, String KlasaWychowankowie) 
    {
		
			nazwaFolderuUzytkownika = folderName; 
    		wszystkieKlasy = listOfAllClass;
    		uczeKlasy = listClassIteach;
    		wychowankowie = KlasaWychowankowie;
    		persona = imie + " " + nazwisko; 
    		  userName = folderName;
    		//frame settings..
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
    		

	    	
			 
    		JButton btnZamknijDziennik = new JButton("Powrót");
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
    		btnZamknijDziennik.setVisible(true);
       		  
    
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
     		p2.setVisible(false);
    		p2.setBounds(1,61,1360,50);
    		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS)); // !! SKOPIOWAC to do panelu lewego - ZMIEN w kodzie po lewej "x-axis"na "Y-AXIS" aby zobaczyc o co chodzi.
    		p2.setBackground(Color.GREEN);
 
    		System.out.println("imie: " + firstName + ", nazwisko " +lastName);
	try
	{  
		File fSprawdzContent;
		
		List<String> list;
		//TODO: zostala zmieniona ponizsza linijka  z list = Files.readAllLines(Paths.get("C:/dziennik/users/Michal Klich/studenci.txt" ), StandardCharsets.UTF_8); na:
		// i nie dziala bo brak pliku txt studenci. stworzyc wyjatek
		//File fStudenci = new File("C:/dziennik/users/Michal Klich/studenci.txt" );
		list = Files.readAllLines(Paths.get("C:/dziennik/users/" + imie + " " + nazwisko + "/studenci.txt" ), StandardCharsets.UTF_8);
		
		
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
		String[] columnTitles = { "Lp.", "Imie", "Nazwisko", "Aktyw.", "Odp. ustna", "Praca dom.", "Spr.", "Praca kl.", "Œr.", "Oc. koñcowa" }; 
	    Object[][] dataEntries = new Object[dl][10];
	    
	    Student sss = new Student();
	    
	    for(int i = 0 ; i<imiona.length ; i++)
	    {
	    	dataEntries[i][0] = lp[i] + ".";
	        dataEntries[i][1] = imiona[i]; 
	        dataEntries[i][2] = nazwiska[i];
	        dataEntries[i][3] = sss.getStudentFirstName(i);
	        dataEntries[i][4] = "";
	        dataEntries[i][5] = "";
	        dataEntries[i][6] = "";
	        dataEntries[i][7] = "";
	        dataEntries[i][8] = "";
	        dataEntries[i][9] = "";
	    }
	    
	    TableModel model = new EditableTableModel(columnTitles, dataEntries);
	    JTable table = new JTable(model);
	    table.setFont(new Font("Tahoma", Font.BOLD, 11));
	    table.createDefaultColumnsFromModel();
	    table.setRowHeight( 35);
 
	    
	    String[] statusObecnosci = { "Obecny", "Nieobecny", "Spozniony", "Usprawiedliwiony" };
	    JComboBox comboBox1 = new JComboBox(statusObecnosci);
	    table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox1));
	
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
	    table.getColumnModel().getColumn(7).setPreferredWidth(150);
	    table.getColumnModel().getColumn(8).setPreferredWidth(30);
	    table.getColumnModel().getColumn(9).setPreferredWidth(250); 
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	    
		//scrollPane z tabela
		  scrollPaneTable = new JScrollPane(table);
		scrollPaneTable.getViewport().setBackground(Color.white);
		//scrollPaneTable.setBounds(400, 400, 450, 322);
		scrollPaneTable.setBackground(Color.red); 
		scrollPaneTable.setPreferredSize(new Dimension(600,340));
	 
		
 
		
		JPanel pNawigacji = new JPanel();
		
		
 
		
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
	
	GridLayout layoutZespolone = new GridLayout(1,2);
	
	//tworzenie panelu z trzema pierwszymi panelami 
	final JPanel pCompsToExperiment = new JPanel();
	pCompsToExperiment.setLayout(experimentLayoutThreeOne);
	
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
		pCompsToExperiment.setPreferredSize(new Dimension(1360, 200));
	 
	  	
		//Add komponentów to experiment with Grid Layout
		pCompsToExperiment.add(p1); // dodanie p1 ktory jest boxlayoutem
		pCompsToExperiment.add(p2);
		
		
		frame.add(pCompsToExperiment, BorderLayout.NORTH);
		frame.add(pZespolone, BorderLayout.CENTER);
		frame.add(pInfo, BorderLayout.SOUTH);
		
		 
		
		//frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
        frame.setVisible(true);
        frame.setSize(1360, 750);
		}	
	catch (Exception ex) {
		
	}
	
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source == rb1 && !rb1.isSelected())
		{
			 scrollPaneTable.setVisible(false);
		}
		if(source == rb1 && rb1.isSelected()) scrollPaneTable.setVisible(true);
		
		else if(source == bZapiszTabele)
		{
			zapiszTabele(e);
		}
		 
		//else if(source == blueButton)
		//	...
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
	
	
}
