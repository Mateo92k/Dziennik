package Dziennik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javax.swing.JPopupMenu.Separator;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ZarzadzanieKlasami implements ActionListener {

	private JScrollPane scrollPanePanel;
	private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj;
	private static JButton  dodajKlase, usunKlase, edytujKlase;
	private static JLabel dostepneKlasy, lblSeparator,lblSeparator2 ;
	int liczbaKlas = 3;
	JButton[] btnKlas = new JButton[liczbaKlas];
	private JScrollPane scrollPane1, scrollPane2;
	Font fontTitle = new Font("Serif", Font.BOLD, 26);
    String[] options = {" 6 A ", " 4 B " ,  " 1 A", " 1 B", " 2 A"};
	final String zalogowanoJako = "Zalogowano jako: ";
	
	public void runClassLogowanie() {
	    Logowanie lg = new Logowanie();
	    lg.runClassLogowanie();		    
	}
	
	/**
	 * Tworzy okno aplikacji
	 */
	void createAndShowTopLevelGUI(String str) 
	{
		
		JFrame frame = new JFrame("Dziennik Elektroniczny - Zarz¹dzaj klasami");
		frame.setSize(1360, 715);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(1360, 715));
		wysrodkujOkno(frame);
	
		/**
		 * Ustawienia menuBar + info o zalogowanym.
		 */
		JMenuBar topMenu = new JMenuBar();
		topMenu.add(Box.createHorizontalGlue()); // ustawienie startu menu od prawej.
		topMenu.setBackground(new Color(226,247,165));
		final String userName = str;
		JMenu menutopMain = new JMenu(zalogowanoJako + userName ); 
				
		menutopMain.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/key.png")));
		menutopMain.setFocusable(true); 
		
		JMenuItem menuitemWyloguj = new JMenuItem("Wyloguj");
		menuitemWyloguj.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logout.png")));
		menuitemWyloguj.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				JOptionPane.showMessageDialog(null, "Wylogowano pomyœlnie.");
				//Logowanie.runClassLogowanie();
			}
		});
  		menutopMain.add(menuitemWyloguj);
		topMenu.add(menutopMain);
		frame.setJMenuBar(topMenu);
 
		topMenu.add(menutopMain);
		frame.setJMenuBar(topMenu);
  		
		/**
		 * Pasek z modu³ami
		 */
		JPanel mastHead = new JPanel();
		mastHead.setLayout(new BoxLayout(mastHead, BoxLayout.X_AXIS)); // !! SKOPIOWAC to do panelu lewego - ZMIEN w kodzie po lewej "x-axis"na "Y-AXIS" aby zobaczyc o co chodzi.
		mastHead.setBackground(Color.WHITE);
		
		 /**
		  * Ustawienia combobox w pasku z modu³ami
		  */
		        final JComboBox comboBox = new JComboBox(options);
		        comboBox.setRenderer(  new MyComboBoxRenderer("Lista klas i przedmiotów"));
		        comboBox.setPreferredSize(new Dimension(240, 30));
		        comboBox.setSelectedIndex(-1); //By default it selects first item, we don't want any selection
				comboBox.setBackground(Color.WHITE);
		        JButton clearSelectionButton = new JButton();
		        clearSelectionButton.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/homepage.png")));
		        clearSelectionButton.setBackground(Color.WHITE);
		        clearSelectionButton.setPreferredSize(new Dimension(48, 48));
		        clearSelectionButton.addActionListener(new ActionListener()
		        {
		            public void actionPerformed(ActionEvent e)
		            {
		            	frame.dispose();
		            	runGuiMain(str);
		                //comboBox.setSelectedIndex(-1);
		            }
		        });
		        
		mastHead.add(comboBox);
		mastHead.add(clearSelectionButton);   
			
 
		
		/*
		 * Tworzenie Menu na górze z modu³ami:
		 */
		
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
		
		bModulObecnosci = new JButton("Dziennik Obecnosci");
		bModulObecnosci.setBackground( Color.WHITE) ;
		bModulObecnosci.setPreferredSize(new Dimension(240, 30));
		bModulObecnosci.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/listaObecnosciIconSmall.png")));
		
		bModulOcen = new JButton("Oceny");
		bModulOcen.setBackground( Color.WHITE) ;
		bModulOcen.setPreferredSize(new Dimension(240, 30));
		bModulOcen.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/teacher.png")));

		bSzukaj = new JButton("Wyszukiwarka");
		bSzukaj.setBackground(Color.WHITE);
		bSzukaj.setPreferredSize(new Dimension(240, 30));
		bSzukaj.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/search48.png")));	
		
		bWyloguj = new JButton("Wyloguj");
		bWyloguj.setBackground(new Color(255, 255, 255));
		bWyloguj.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logout.png")));
		bWyloguj.setSize(30, 30);
		bWyloguj.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				JOptionPane.showMessageDialog(null, "Wylogowano pomyœlnie.");
				//Logowanie.runClassLogowanie();
			}
		});
		
		
		/**
		* Dodanie obiektów do panelu
		*/
		
		mastHead.add(lblSeparator);
		mastHead.add(bModulKlas);
		mastHead.add(bModulObecnosci);
		mastHead.add(bModulOcen);
		mastHead.add(bSzukaj);
		mastHead.add(Box.createHorizontalGlue());
		mastHead.add(Box.createRigidArea(new Dimension(15, 10))); 
		mastHead.add(bWyloguj);
		// ---koniec: pasek menu modu³ów
			 
		/**
		 * Tworzenie pionowego panelu po lewej
		 */
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		JPanel pLewy = new JPanel();
		pLewy.setBackground(new Color(255,254,218));	
		pLewy.setLayout(null);  
		pLewy.setPreferredSize(new Dimension(225, 225));
		
		JScrollPane ss = new JScrollPane(); 
		ss.getViewport().setBackground(Color.WHITE);
		ss.setBounds(0, 1, 224, 520);
		ss.setBackground(Color.white);
		
		JLabel lblAkcja = new JLabel("Akcja");
		lblAkcja.setBounds(10, 0, 120, 30);
		lblAkcja.setBackground(Color.red);
		pLewy.add(lblAkcja);
		
		JButton b1 = new JButton("Nowa klasa");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	 
				runAddClass();
				
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
		
		JRadioButton rb1 = new JRadioButton("Tabela");
		rb1.setBounds(30, 330, 150, 24);
		rb1.setBackground(Color.WHITE);
		rb1.setSelected(true);
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
	 
	 	
	 	/**
		*Tworzenie panelu centralnego
	 	*/
	 	JPanel pCentral = new JPanel();
		//pCentral.setBackground(new Color(255,254,218));	
		//pCentral.setBackground(Color.green);
		pCentral.setLayout(null);  
		pCentral.setPreferredSize(new Dimension(1127, 520));
		
	
//	 	JScrollPane ssC = new JScrollPane(); 
//		ssC.getViewport().setBackground(Color.orange);
//		ssC.setBounds(1, 1, 1127, 520);
	 	
	 	/**
	 	 * Tworzenie tabeli
	 	 */
		String[] columnNames = {"Imie", "Last Name", "Sport", "# of Years", "Vegetarian"};
		Object[][] data = {{"First Name", "Last Name", "Sport", "# of Years", "No"}};
		JTable table= new JTable(data, columnNames);
		
		//Dodanie tabeli do kontenera ScrollPane
		JScrollPane tableContainer = new JScrollPane(table);
		tableContainer.setBackground(Color.BLUE);
		tableContainer.setBounds(1, 1, 500, 500);
		pCentral.add(tableContainer);
		
		//table.setPreferredScrollableViewportSize(new Dimension(300, 70));
		//table.setBounds(125, 125, 137, 120);
		 
	 	//---end : panel centralny
		
		
		/**
		 * Dodanie paneli do okna:
		 */	
		pLewy.add(ss);
		 
		frame.add(mastHead, BorderLayout.NORTH);
		frame.add(pLewy, BorderLayout.WEST);
		frame.add(pCentral, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	    
		} // END: "Create and show GUI"
	    
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
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }

/**
 * uruchamia glowne okno aplikacji
 */
public static final void main(String[] args) 
{
	// Schedule a job for the event-dispatching thread:
	// creating and showing this application's GUI.
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() 
		{
			 
			new ZarzadzanieKlasami().createAndShowTopLevelGUI(null);
		}
	});
}



/**
 * wywoluje g³owne okno aplikacji gdy ?
 */
public void UruchomKlase(String str)
{   
	new ZarzadzanieKlasami().createAndShowTopLevelGUI(str);
}


private void wysrodkujOkno(JFrame frame) {
	 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	 int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	 int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	 frame.setLocation(x, y);	
	
}

public void runAddClass() {
	 AddClass ad = new  AddClass(null);
    ad.runAddClass( );	
     
}

public void runGuiMain(String str) {
    GuiMain gm = new GuiMain();
    gm.UruchomKlase(str);		    
} 

@Override

public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}