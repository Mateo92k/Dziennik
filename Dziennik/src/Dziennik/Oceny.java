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
import java.awt.ScrollPane;
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
import java.util.ArrayList;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

public class Oceny {

	private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj;
	private static JLabel lblInformacje, lblRokSzkolny, lblAktualnyPrzedmiot, lblObecnychStudentow, lblSeparator,lblSeparator2, lblTime,lblDay, lblVersion;
	private static JButton welcomeBut[ ];
	
	String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
	String strDay = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
	final String zalogowanoJako = "Zalogowano jako: ";
	
	Object[][] ddata = null;
	String[] ccolumnNames = new String[2];
	
	public JMenuBar createJMenuBar( ) 
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

	private void createGui(String comboStatus, boolean panel2isEnable) {
		
		/**
		 * Ustawienia okna
		 */
		JFrame frame = new JFrame("Dziennik Elektroniczny ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Oceny app = new Oceny();
		frame.setJMenuBar(app.createJMenuBar( ));
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
		//Color blueColor = new Color( 31, 69, 252);
		
 		/**
 		 *	***********************************************************************  Create Panel 1	************************************************************************************************
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

		JLabel lblDaneZalogowanego = new JLabel("Michal Klich");
		lblDaneZalogowanego.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/usericonm.png")));
   
		JLabel lblMojProfil = new JLabel("Mój profil");
		lblMojProfil.setForeground(blueColor);
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

    	final JComboBox comboBox = new JComboBox(options);
    	 
		comboBox.setRenderer(  new MyComboBoxRenderer(comboStatus));
		comboBox.setPreferredSize(new Dimension(90, 30));
		comboBox.setSelectedIndex(-1); //By default it selects first item, we don't want any selection    
		comboBox.setBorder(null); 
		comboBox.setOpaque(false);
		comboBox.setBackground(Color.WHITE);
		
		
		JButton btnZamknijDziennik = new JButton("(Zamknij dziennik)");
		btnZamknijDziennik.setFont(btnZamknijDziennik.getFont().deriveFont(11.0f));
		//clearSelectionButton.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/homepage.png")));
		btnZamknijDziennik.setBackground(Color.WHITE);
		btnZamknijDziennik.setPreferredSize(new Dimension(108, 48));
	
		btnZamknijDziennik.setOpaque(false);
		btnZamknijDziennik.setBorder(null);
		btnZamknijDziennik.setContentAreaFilled(false);
		btnZamknijDziennik.setBorderPainted(false);
		btnZamknijDziennik.setForeground(blueColor);
		


		JLabel lblDziennik = new JLabel();
		 
		lblDziennik.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/dziennikm.png")));
		//Dodanie komponentów do panelu1
		p1.add(lblLogo); // dodanie logo z funkcj¹ maj¹ce funkcje powrotu do glownego okna kontekstowego
		p1.add(Box.createHorizontalGlue());	// tworzenie orientacji obiektów od prawej do lewej
		//p1.add(Box.createRigidArea(new Dimension(35,0)));
		p1.add(bSep4);
		p1.add(Box.createRigidArea(new Dimension(	3,0)));
		p1.add(lblDziennik);
		p1.add(Box.createRigidArea(new Dimension(1,0)));
		p1.add(comboBox);
		p1.add(btnZamknijDziennik);
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
		 
		/**
		 * Create Panel2
		 */
 		JPanel p2 = new JPanel();
 		p2.setVisible(false);
		p2.setBounds(1,61,1360,50);
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS)); // !! SKOPIOWAC to do panelu lewego - ZMIEN w kodzie po lewej "x-axis"na "Y-AXIS" aby zobaczyc o co chodzi.
		p2.setBackground(Color.GREEN);

		/**---------------------------------------------------------------------------------------------------------------------------------------------------------------------------COMBOx's
		-------------------------------------------------------------------------------------------------------------------------------------------------------------------------COMBOx's*/
		//start: cbx1
		
        final JComboBox cbx1 = new JComboBox(opt1);
	    cbx1.setRenderer(new MyComboBoxRenderer1("Lekcje"));
    	cbx1.setSelectedIndex(-1);  
  		cbx1.setEnabled(false);
    	cbx1.setBackground(Color.WHITE);
    	p2.add(cbx1);
		//end: cbx1

		//start: cbx2
		
        final JComboBox cbx2 = new JComboBox(opt2);
	    cbx2.setRenderer(new MyComboBoxRenderer2("Oceny"));
    	cbx2.setSelectedIndex(-1);  
    	cbx2.setBackground(Color.WHITE);
    	p2.add(cbx2);
		//end: cbx2
		        
		//start: cbx3
		
        final JComboBox cbx3 = new JComboBox(opt3);
	    cbx3.setRenderer(new MyComboBoxRenderer3("Obecnosci"));
    	cbx3.setSelectedIndex(-1);  
    	cbx3.setBackground(Color.WHITE);
    	p2.add(cbx3);
		//end: cbx3
		
		//start: cbx4
		final JComboBox cbx4 = new JComboBox(opt4);
	    cbx4.setRenderer(new MyComboBoxRenderer4("Uczniowie"));
    	cbx4.setSelectedIndex(-1);  
    	cbx4.setBackground(Color.WHITE);
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
 
		
 	
		

  
		/**
		 * ********************************************************************************Tworzenie panelu nawigacyjnego - #navPanel#
		 */
	 
	 	
 
			  	
		
 
		 
 
		JScrollPane spOpera = new JScrollPane( );
		spOpera.setBackground( Color.red);
		spOpera.setBounds(125, 115, 1250, 100);
		frame.add(spOpera);
	
		JScrollPane spTools = new JScrollPane( );
		spTools.setBackground( Color.green);
		spTools.setBounds(1, 115, 124, 555);
		frame.add(spTools);
		 
//		JScrollPane spCentral = new JScrollPane( );
//		spCentral.setBackground( Color.blue);
//		spCentral.setBounds(125 , 215, 1250, 350);
//		frame.add(spCentral);
		
		JScrollPane spInfo = new JScrollPane( );
		spInfo.setBackground( Color.blue);
		spInfo.setBounds(125 , 570, 1250, 100);
		frame.add(spInfo);
 
//tworzenie JTable
        String[] columnNames = {"Lp",
                "Imie",
                "Nazwisko",
                "Plusy",
                "Dodaj i nazwij rubrykê",
                "Srednia ",
                "Ocena koncowa"
                };

        		Object[][] data = {
        							{"1", "Imie1", "Nazwisko1", "x", "empty", "x", "y"},
                					{"1", "Imie1", "Nazwisko1", "x", "empty", "x", "y"},
                					{"1", "Imie1", "Nazwisko1", "x", "empty", "x", "y"},
                					{"1", "Imie1", "Nazwisko1", "x", "empty", "x", "y"},
                					{"1", "Imie1", "Nazwisko1", "x", "empty", "x", "y"},
        		};

     
        		
        		final JTable table = new JTable(data, columnNames);
        		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        		table.setFillsViewportHeight(true);

        		table.addMouseListener(new MouseAdapter()
        		{	
        			public void mouseClicked(MouseEvent evt)
        			{
        				  DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        				    Vector data = dtm.getDataVector();
        				    Vector row = (Vector) data.elementAt(1);

        				    int mColIndex = 0;
        				    ArrayList colData = new ArrayList(table.getRowCount());
        				    for (int i = 0; i < table.getRowCount(); i++) {
        				      row = (Vector) data.elementAt(i);
        				      colData.add(row.get(mColIndex));
        				    }

        				    // Append a new column with copied data
        				    dtm.addColumn("Col3", colData.toArray());
        			}
        		});
	
        		//Create the scroll pane and add the table to it.
        		JScrollPane scrollPane = new JScrollPane(table);
        		scrollPane.setBounds(125 , 415, 1250, 150);
        		//Add the scroll pane to this panel.
        		frame.add(scrollPane);
		
	 
		
		
		
		/**
		*Tworzenie panelu "cetralnego"
		*/
		JPanel pCenter = new JPanel();
		pCenter.setBackground(new Color(242,242,242));	
		pCenter.setLayout(null);  	
			
		

	 
	 

  
		comboBox.addActionListener(new ActionListener() {
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
		            }
		});
		
		
		//Dodanie paneli do okna
		frame.add(p1, BorderLayout.NORTH);
		frame.add(p2);  	 
		
		frame.add(pCenter, BorderLayout.CENTER);
		frame.setVisible(true);
		

		
	} // ---koniec metody: private void createAndShowTopLevelGUI(String str) 



	// main..
	public static final void main(String[] args) throws InterruptedException {
	

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				Oceny oceny = new Oceny();
				oceny.createGui(null, false);
			}
		});
	}
	
	//uruchom klase Oceny.java
	public void OcenyRUN(String choosenClass, boolean p2isEnable  ) 
	{
		 System.out.println(choosenClass);
		 new Oceny().createGui(choosenClass, p2isEnable  );
	}
 
 

	private void wysrodkujOkno(JFrame frame) {
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		 int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		 int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		 frame.setLocation(x, y);	
		
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

	        @Override
	        public Component getListCellRendererComponent(JList list, Object value,
	                int index, boolean isSelected, boolean hasFocus)
	        {
	            if (index == -1 && value == null) 
	            {
	            	setText(_title);
	            
	            	 
	            	
	            }
	            
	            else 
	            	{
	      
	            	}
	            return this;
	        }

		 
	    }
	//----------------END: combox 0 ---------------


								
//-----------------START: Combobox 1---------------------
String[] opt1 = {"Ostatni tydzieñ", "Nadchodz¹cy tydzieñ" ,  "Plan lekcji klasy"};
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
String[] opt2 = {"Semestr I", "Semestr II" ,  "Wydrukuj oceny na wywiadówké"};
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
String[] opt3 = {"Bie¿¹cy tydzieñ", "Poprzedni tydzieñ" ,  "Frekwencja Semestr I", "Frekwencja Semestr II"};
	class MyComboBoxRenderer3 extends JLabel implements ListCellRenderer
{
    private String _title;

    public MyComboBoxRenderer3(String title)
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



