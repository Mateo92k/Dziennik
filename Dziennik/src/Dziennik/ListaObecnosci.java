package Dziennik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector; 

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import Dziennik.GuiMain.MyComboBoxRenderer;

public class ListaObecnosci 
{
 
	private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj;
	private static JLabel lblInformacje, lblRokSzkolny, lblAktualnyPrzedmiot, lblObecnychStudentow, lblSeparator,lblSeparator2, lblTime,lblDay, lblVersion;
  
	String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
	String strDay = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

	final String zalogowanoJako = "Zalogowano jako: ";
	public String imie, nazwisko, wychowankowie, nazwaFolderuUzytkownika, persona;
	public String[] uczeKlasy, wszystkieKlasy;
	public String[] args = {};
	
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
	
	public static void createGui(String fullname, String dzisiejszaData, String dlaPrzedmiotu ) 
	{ 
		String przedmiot = dlaPrzedmiotu;
		JFrame frame = new JFrame("Dziennik Elektroniczny - Lista obecnosci");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ListaObecnosci listaobecnosci = new ListaObecnosci();
		frame.setJMenuBar(listaobecnosci.createJMenuBar()); 
		frame.setResizable(false);
		frame.setVisible(true); 
		frame.pack(); 
		frame.setSize(1362, 724);
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
            	GuiMain gm = new GuiMain();
            	gm.loadDataAndRunApp(fullname);
            	System.out.println("Powrót do g³ównego okna. "); 
            }
        });
		
		JLabel lblMojeKlasy = new JLabel("Moje Klasy"); 
		lblMojeKlasy.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
            	TwojeKlasy tk = new TwojeKlasy();
            	tk.wczytajDane(fullname, null, null, null, null, null);
            	System.out.println("Powrót do g³ównego okna. "); 
            }
        });
		lblMojeKlasy.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
		
		JLabel lblDaneZalogowanego = new JLabel(fullname);
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
 
		JButton bZapisz = new JButton("Zapisz");
		bZapisz.setBounds(340, 240, 840, 40);
		
		
		
		JPanel p2 = new JPanel();
 		p2.setVisible(false);
		p2.setBounds(1,61,1360,50);
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS)); // !! SKOPIOWAC to do panelu lewego - ZMIEN w kodzie po lewej "x-axis"na "Y-AXIS" aby zobaczyc o co chodzi.
		p2.setBackground(Color.darkGray);
		p2.add(bZapisz);
		
		JPanel pPrawyCaly = new JPanel();
		pPrawyCaly.setBackground(new Color(242,242,242));	
		pPrawyCaly.setLayout(null);  
		

		
		//pobranie info o studentach, zlistowanie i wodrêbnienie imion od nazwisk z jednej lini do dwóch osobnych tablic.
	try
	{
		List<String> list;
		list = Files.readAllLines(Paths.get("C:/dziennik/users/Michal Klich/studenci.txt" ), StandardCharsets.UTF_8);
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
		String[] columnTitles = { "Lp.", "Imie", "Nazwisko", "Wybierz",  }; 
	    Object[][] dataEntries = new Object[dl][4];
	    
	    for(int i = 0 ; i<imiona.length ; i++)
	    {
	    	dataEntries[i][0] = lp[i];
	        dataEntries[i][1] = imiona[i]; 
	        dataEntries[i][2] = nazwiska[i];
	        dataEntries[i][3] = "Kliknij by wybraæ";

	    }
	    
	    TableModel model = new EditableTableModel(columnTitles, dataEntries);
	    JTable table = new JTable(model);
	    table.setFont(new Font("Tahoma", Font.BOLD, 11));
	    table.createDefaultColumnsFromModel();
	    table.setRowHeight(  35);
	    table.getColumnModel().getColumn(0).setPreferredWidth(15);
	    String[] statusObecnosci = { "Obecny", "Nieobecny", "Spozniony", "Usprawiedliwiony" };
	    JComboBox comboBox = new JComboBox(statusObecnosci);
	    table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
	
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.LEFT );
	    table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer ); 
	    table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
	    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
	    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	    table.getTableHeader().setReorderingAllowed(false);
	    
	    //scrollPane z tabela
		JScrollPane scrollPaneTable = new JScrollPane(table);
		scrollPaneTable.getViewport().setBackground(Color.white);
		//scrollPaneTable.setBounds(400, 400, 450, 322);
		scrollPaneTable.setBackground(Color.red); 
		scrollPaneTable.setPreferredSize(new Dimension(600,340));
		
		 
		JLabel lblPrzedmiot = new JLabel("Lista obecnoœci " + dzisiejszaData );
		lblPrzedmiot.setFont(new Font("default", Font.ROMAN_BASELINE, 17));
		lblPrzedmiot.setBounds(10, 10, 400, 100);
		pPrawyCaly.add(lblPrzedmiot);
		
		JLabel lblNazwaPrzedmiotu = new JLabel("Przedmiot: " + przedmiot  );
		lblNazwaPrzedmiotu.setFont(new Font("default", Font.BOLD, 17));
		lblNazwaPrzedmiotu.setBounds(520,55, 400, 100);
		pPrawyCaly.add(lblNazwaPrzedmiotu);
		
		JButton btnPoprzednieZajecia = new JButton("<< Lista poprzednich zajêæ" ); 
		btnPoprzednieZajecia.setBounds(250, 50, 350, 20); 
		btnPoprzednieZajecia.setOpaque(false);
		btnPoprzednieZajecia.setBorder(null);
		btnPoprzednieZajecia.setContentAreaFilled(false);
		btnPoprzednieZajecia.setBorderPainted(false);
		btnPoprzednieZajecia.setForeground(new Color(69,55,215));
		btnPoprzednieZajecia.setFont(new Font("default", Font.ITALIC, 16));
		pPrawyCaly.add(btnPoprzednieZajecia);
		
		JButton btnWybranaData = new JButton("25/05/2015" ); 
		btnWybranaData.setBounds(400, 50, 350, 20);
		btnWybranaData.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
		btnWybranaData.setOpaque(false);
		btnWybranaData.setBorder(null);
		btnWybranaData.setContentAreaFilled(false);
		btnWybranaData.setBorderPainted(false);
		btnWybranaData.setForeground(new Color(114,114,114));
		btnWybranaData.setFont(new Font("default", Font.HANGING_BASELINE, 16));
		pPrawyCaly.add(btnWybranaData);
		
		JButton btnPrzyszleZajecia = new JButton("Lista przysz³ych zajêæ >>" ); 
		btnPrzyszleZajecia.setBounds(570, 50, 350, 20);
		btnPrzyszleZajecia.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
		btnPrzyszleZajecia.setOpaque(false);
		btnPrzyszleZajecia.setBorder(null);
		btnPrzyszleZajecia.setContentAreaFilled(false);
		btnPrzyszleZajecia.setBorderPainted(false);
		btnPrzyszleZajecia.setForeground(new Color(69,55,215));
		btnPrzyszleZajecia.setFont(new Font("default", Font.ITALIC, 16));
		pPrawyCaly.add(btnPrzyszleZajecia);
		
		JLabel lblProwadzacy = new JLabel("Nauczyciel: " + fullname );
		lblProwadzacy.setBounds(10, 85, 200, 100);
		pPrawyCaly.add(lblProwadzacy);
		
		JLabel lblTematLekcji = new JLabel("Temat lekcji: ");
		lblTematLekcji.setBounds(390, 85, 700, 100);
		pPrawyCaly.add(lblTematLekcji);
	
		JTextField txtTematLekcji = new JTextField("Wprowadz temat lekcji");
		txtTematLekcji.setBounds(470, 120, 350, 30);
		txtTematLekcji.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTematLekcji.setText("");  
			}
		});
		
		pPrawyCaly.add(txtTematLekcji);
		
		JTextArea notatnik = new JTextArea();
		JScrollPane scrollPaneBlue = new JScrollPane(notatnik); 
		scrollPaneBlue.getViewport().setBackground(Color.blue);
		scrollPaneBlue.setBounds(615, 525, 400, 122); 
		
		JButton btnZapiszZmiany = new JButton("Zapisz"); 
		btnZapiszZmiany.setForeground(new Color(76,80,248)); 
		btnZapiszZmiany.setFont(new Font("default", Font.ROMAN_BASELINE, 12));
		btnZapiszZmiany.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
		btnZapiszZmiany.setBounds(820, 181, 150, 30);
		btnZapiszZmiany.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png"))); 
		btnZapiszZmiany.setOpaque(false);
		btnZapiszZmiany.setBorder(null);
		btnZapiszZmiany.setContentAreaFilled(false);
		btnZapiszZmiany.setBorderPainted(false);
		
		frame.add(btnZapiszZmiany);
		frame.add(p1, BorderLayout.NORTH);
		frame.add(p2);
		//frame.add(bZapisz);
		//frame.add(scrollPaneBlue);
		frame.add(scrollPaneTable, BorderLayout.SOUTH); 
		frame.add(pPrawyCaly);
	}catch (IOException e) 
		{ 
			System.out.println("Zaladowanie listy studentow i pzrekonwertowanie do tablicy nie powiodlo sie.");
		}
	}
 
	 
	public static final void main(String[] args) throws InterruptedException 
	{ 
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
		public void run()
			{
			  
				ListaObecnosci lo = new ListaObecnosci(); 
				lo.createGui("Michal Klich", "26/06/2016", "Polski"); 
			} 
		});
	}
	
	public static void wysrodkujOkno(JFrame frame) {
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		 int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		 int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		 frame.setLocation(x, y);
	}
}
	class EditableTableModel extends AbstractTableModel {
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
