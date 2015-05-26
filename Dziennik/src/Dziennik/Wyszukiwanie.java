// To jest gotowy kod pomieszany, ma byc tu tylko wyszukiwanie w glownym pasku.

package Dziennik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Wyszukiwanie {
	protected static final String[] args = null;
	/**
	 * Tworzy glowneOknoAplikacji
	 */
	private static JButton bWyszukaj, bModulKlas, bModulOcen, bModulObecnosci,
			bHome, bWyloguj, bSzukaj;
	private static JLabel lblUser_Name ;
	//String statusBarNazwaUser, statusBarNazwaKlasy;
	static String statusBarZalogowano = "Zalogowano jako: ";	 
	static String statusBarNazwaUser = "Michal Klich ";
	static String statusBarKlasa = "Zarzadzasz klasa: ";
	static String statusBarNazwa = " 3 a";
	
	private static JTextField search_Container;
	

	
	private static void createAndShowTopLevelGUI() {
 
		// Ustawienia okna programu
		JFrame frame = new JFrame("Frame");
		frame.setSize(1362, 724);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/program.png"));

		/** 
		 *  Inicjalizacja kolorow
		 	
		Map<String, Color> colors = new HashMap<String, Color>();
		colors.put("blue", Color.BLUE);
		colors.put("red", Color.RED);
		colors.put("green", Color.GREEN);
		 UZYTKOWANIE:
		String a = "blue";
		jLabel3.setForeground(colors.get(a.toLowerCase()));
		
		* end ---> Inicjalizacja kolorow 
		* */
		
		
		
		/**
		 * Tworzenie menuBar
		 * ! wyswietla w prawym górnym roku nazwe zalogowanego uzytkownika. 
		 */
	
		JMenuBar topMenu = new JMenuBar();
		topMenu.add(Box.createHorizontalGlue()); // ustawienie startu menu od prawej.

		JMenu menutopMain = new JMenu(    statusBarZalogowano + statusBarNazwaUser   ); //  +" / "+ statusBarKlasa + statusBarNazwa 
		//menutopMain.setEnabled(false);		
		menutopMain.setIcon(new ImageIcon(GuiMain.class.getResource("/res/key.png")));
		
		//JMenuItem menuItem = new JMenuItem("aaa");
		JMenuItem menuitemWyloguj = new JMenuItem("Wyloguj");
		menuitemWyloguj.setIcon(new ImageIcon(GuiMain.class.getResource("/res/logout.png")));
		
		menutopMain.add(menuitemWyloguj);
	
		JMenu menutopMain2 = new JMenu("");
		//menutopMain2.setEnabled(false);
		topMenu.add(menutopMain2);
		
		//ImageIcon icon = new ImageIcon("res/key.png");
		//menutopMain2 = (JMenu) new JMenuItem(icon);
		
		topMenu.add(menutopMain);
		 //topMenu.add(menuItem);
		frame.setJMenuBar(topMenu);

		
		/**
		 * Panel NAWIGACJI(GORNY)  wyswietlajacy moduly 
		 * Kod zawiera ustawienia dotyczace tego panelu.
		 */
		// Tworzy BoxLayout do panelu z modulami-umiejscowiony na samej gorze.(pod menuBar)
		JPanel mastHead = new JPanel();
		mastHead.setLayout(new BoxLayout(mastHead, BoxLayout.X_AXIS)); // !! SKOPIOWAC to do panelu lewego - ZMIEN w kodzie po lewej "x-axis"na "Y-AXIS" aby zobaczyc o co chodzi.
		mastHead.setBackground(new Color(214, 238, 223));
		
		
		//Tworzenie obiektów do panelu NAWIGACJI.
		bHome = new JButton("Wstecz ");
		//bHome.setEnabled(false);
		bHome.setPreferredSize(new Dimension(162, 30));
		bHome.setIcon(new ImageIcon(GuiMain.class.getResource("/res/dziennik.png")));
		bHome.setBackground(Color.WHITE);
		bHome.setMargin(new Insets(0, 0, 0, 0));
		//bHome.setPreferredSize(new Dimension(140, 40));
		 
		bSzukaj = new JButton("Szukaj");
		bSzukaj.setForeground(Color.WHITE);
		bSzukaj.setIcon(new ImageIcon(GuiMain.class
				.getResource("/res/search.png")));
		bSzukaj.setBackground(Color.black);
		
		 
		search_Container = new JTextField("Wprowadz tekst tutaj..");
		
		bModulKlas = new JButton("Zarz¹dzaj klas¹");
		bModulKlas.setBackground( Color.WHITE) ;
		bModulKlas.setPreferredSize(new Dimension(240, 30));
		bModulKlas.setIcon(new ImageIcon(GuiMain.class.getResource("/res/klasIcon.png")));
		
		bModulObecnosci = new JButton("Dziennik Obecnosci");
		bModulObecnosci.setBackground( Color.WHITE) ;
		bModulObecnosci.setPreferredSize(new Dimension(240, 30));
		bModulObecnosci.setIcon(new ImageIcon(GuiMain.class.getResource("/res/listaObecnosciIconSmall.png")));
		
		bModulOcen = new JButton("Oceny");
		bModulOcen.setBackground( Color.WHITE) ;
		bModulOcen.setPreferredSize(new Dimension(240, 30));
		bModulOcen.setIcon(new ImageIcon(GuiMain.class.getResource("/res/teacher.png")));
		 
		
		bWyloguj = new JButton("");
		bWyloguj.setBackground(new Color(255, 255, 255));
		bWyloguj.setIcon(new ImageIcon(GuiMain.class.getResource("/res/logout.png")));
		bWyloguj.setSize(30, 30);
		
		//KONTENER POZWALAJACY ZARZADZAC KOLEJNOSCIA OBIEKTOW w PANELU NAWIGACJI(GORNYM)
		 mastHead.add(bHome);
		//mastHead.add(bModulKlas);
		//mastHead.add(bModulObecnosci);
		//mastHead.add(bModulOcen);
		mastHead.add(search_Container);
		mastHead.add(bSzukaj);

		
		//mastHead.add(lblUser_Name);
		
		mastHead.add(Box.createHorizontalGlue());
		mastHead.add(Box.createRigidArea(new Dimension(15, 10))); 
		mastHead.add(bWyloguj);
		// END of creating BoxLayout and JPanel

		// ActionListener dla search_Container (czysci pole tekstowe po
		// kliknieciu myszka w polu wyszukiwania)
		search_Container.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				search_Container.setText("");  
			}
		});
		
		/**
		 * Panel "lewostronny". 
		 * Aktywowany po wybraniu ktoregokolwiek modulu.
		 * ! Wyswietla dodatkowe narzedzia dla odpowiednich modulow . 
		 */
		// Create JPanel (umiejscowiony po lewej)
		JPanel panelEtykiet = new JPanel();
		panelEtykiet.setBackground(Color.red);
 



		
		
		// start gwn
		 //Tworzenie panela lewego pionowego
		 JPanel panelNawigacji = new JPanel(new GridBagLayout());
		 panelNawigacji.setBackground(Color.darkGray);
		
		 bModulKlas = new JButton("Zarz¹dzaj klas¹");
		 bModulObecnosci = new JButton("Modul Obecnosci");
		 bModulOcen = new JButton("Modul Ocen");
		 bWyszukaj = new JButton("Wyszukaj");
		// END: Tworzenie panela lewego pionowego

		 //Tworzenie GridBag
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.insets = new Insets(15, 15, 15, 15);
		
		 gbc.gridx = 0;
		 gbc.gridy = 1;
		 panelNawigacji.add(bModulKlas, gbc);
		 gbc.gridx = 0;
		 gbc.gridy = 2;
		 panelNawigacji.add(bModulObecnosci, gbc);
		 gbc.gridx = 0;
		 gbc.gridy = 3;
		 panelNawigacji.add(bModulOcen, gbc);
		 gbc.gridx = 0;
		 gbc.gridy = 4;
		 panelNawigacji.add(bWyszukaj, gbc);
		// koniec  gown
		
		 bModulKlas.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				//jeœli po polaczeniu  
				//AddStudent.main(args);
				
			}
		});
		
		
		
		
		// Dodanie JPaneli do frame
		//frame.add(panelEtykiet, BorderLayout.WEST);
		frame.add(mastHead, BorderLayout.NORTH);
		frame.add(panelNawigacji, BorderLayout.WEST);
		 
		// Wyswietlenie okna
		frame.setVisible(true);
	}

	/**
	 * 'main' wywolujaca metode 'createAndShowGUI'
	 */
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowTopLevelGUI();
			}
		});
	}
}