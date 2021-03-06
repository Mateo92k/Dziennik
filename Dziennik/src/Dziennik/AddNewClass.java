package Dziennik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class AddNewClass {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private JTextField nazwaKlasy;
	private JTextField rocznikOd;
	private JTextField rocznikDo;
	private JTextField iloscUczniow;
	public String[] args = {};
	tworzenieKatalogow tworzeniekatalogow = new tworzenieKatalogow();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//tworzenieKatalogow.stworzFolder("c://klasyInfo//przedmiot");
					 
					//AddNewClass window = new AddNewClass();
					//window.frame.setVisible(true);
					//window.initialize("Michal Klich");
					//window.initialize(folderName, firstName, lastName, listOfAllClass, listClassIteach, KlasaWychowankowie);
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
//	public AddNewClass(String imie, String nazwisko) {
//		String imieInazwisko = imie + " " + nazwisko;
//		initialize(imieInazwisko );
//	}

	/**
	 * Initialize the contents of the frame.
	 * @param n2 
	 * @param nazwiskoUzytkownika 
	 */
	public  void initialize(String imieInazwisko, String im,  String na,  String sciezkaFolderu, String[] listOfAllClass, String[] listClassIteach,  String KlasaWychowankowie) {
		String sciezkaFolderuUzytkownika = sciezkaFolderu;
		String i = im;
		String n = na;
		
		frame = new JFrame();
		frame.setBackground(UIManager.getColor("info"));
		frame.setBounds(100, 100, 454, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 11, 438, 38);
		frame.add(panel);
		panel.setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNowaKlasa = new JLabel("Nowa Klasa");
		lblNowaKlasa.setForeground(SystemColor.inactiveCaptionText);
		lblNowaKlasa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNowaKlasa.setBounds(10, 11, 102, 14);
		panel.add(lblNowaKlasa);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 49, 438, 300);
		frame.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(410, 236, -399, -6);
		panel_1.add(separator);
		
		JLabel lblNazwaKlasy = new JLabel("Nazwa Klasy");
		lblNazwaKlasy.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblNazwaKlasy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazwaKlasy.setForeground(SystemColor.inactiveCaptionText);
		lblNazwaKlasy.setBounds(51, 11, 103, 32);
		panel_1.add(lblNazwaKlasy);
		
		nazwaKlasy = new JTextField();
		nazwaKlasy.setBounds(170, 11, 160, 32);
		panel_1.add(nazwaKlasy);
		nazwaKlasy.setColumns(10);
		
		rocznikOd = new JTextField();
		rocznikOd.setBounds(170, 54, 75, 23);
		panel_1.add(rocznikOd);
		rocznikOd.setColumns(10);
		
		rocznikDo = new JTextField();
		rocznikDo.setColumns(10);
		rocznikDo.setBounds(255, 54, 75, 23);
		panel_1.add(rocznikDo);
		
		JLabel lblRocznik = new JLabel("Rocznik");
		lblRocznik.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblRocznik.setForeground(SystemColor.inactiveCaptionText);
		lblRocznik.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRocznik.setBounds(77, 54, 75, 23);
		panel_1.add(lblRocznik);
		
		JLabel lblIloscUczniw = new JLabel("Ilosc uczni\u00F3w");
		lblIloscUczniw.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblIloscUczniw.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIloscUczniw.setForeground(SystemColor.inactiveCaptionText);
		lblIloscUczniw.setBounds(51, 95, 101, 15);
		panel_1.add(lblIloscUczniw);
		
		iloscUczniow = new JTextField();
		iloscUczniow.setBounds(170, 92, 86, 20);
		panel_1.add(iloscUczniow);
		iloscUczniow.setColumns(10);
		
		JRadioButton radiobtnWychowawca = new JRadioButton("Jestem nauczycielem tej klasy");
		radiobtnWychowawca.setSelected(true);
		radiobtnWychowawca.setEnabled(false);
		radiobtnWychowawca.setFont(new Font("Verdana", Font.ITALIC, 11));
		radiobtnWychowawca.setForeground(SystemColor.inactiveCaptionText);
		radiobtnWychowawca.setBounds(170, 119, 264, 23);
		panel_1.add(radiobtnWychowawca);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1, 256, 437, 32);
		panel_1.add(panel_2);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.setBackground(new Color(0, 204, 102));
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				String nazwaFolderuNowejKlasy = nazwaKlasy.getText();
				//tworzy folder w klasyInfo dla nowo wprowadzonej klasy.
				
				File f = new File("c://DziennikElektroniczny//users//" + imieInazwisko + "//klasyInfo//" + nazwaKlasy.getText()); 
				File f2 = new File("c://DziennikElektroniczny//users//" + imieInazwisko + "//klasyInfo//" + nazwaKlasy.getText() + "//przedmioty.txt");
				File f3 = new File("c://DziennikElektroniczny//users//" + imieInazwisko + "//klasyInfo//" + nazwaKlasy.getText() + "//studenci.txt");
				File f4 = new File("c://DziennikElektroniczny//users//" + imieInazwisko + "//uczyKlasy.txt.txt"); 
		 
				String strOceny1 ="C://DziennikElektroniczny/users//" + imieInazwisko + "//klasyInfo//" + nazwaKlasy.getText() + "//oceny.txt";  
				 File fOceny1 = new File(strOceny1);
				 if(!fOceny1.exists() && !fOceny1.isDirectory())
				 {
					  System.out.println("Brak pliku fOceny.txt");
						if (!fOceny1.exists()) 
						  { 
							try { 
							      if (fOceny1.createNewFile()){
							        System.out.println("File fPrzedmioty created!");
							      }else{
							        System.out.println("File already exists.");
							      	}
						    		} catch (IOException e1) {
							      e1.printStackTrace();
						    		}
							  System.out.println("InfoLog nr2 " + fOceny1); 
						  }
				 }
				
				
				try {
					f.mkdirs();
					f2.createNewFile();
					f3.createNewFile();
					
					try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f4, true))))
					{
					    out.println(nazwaFolderuNowejKlasy);   
						JOptionPane.showMessageDialog(null, "Klasa dodana pomy�lnie. Zmiany b�d� widoczne po ponownym uruchomieniu programu."); 
					} 
					
					  frame.setVisible(false); 
				      frame.dispose();
					  zamknijOknoUruchomTwojeKlasy(sciezkaFolderuUzytkownika ,imieInazwisko,    i,     n,    listOfAllClass,   listClassIteach,    KlasaWychowankowie);
					
				} catch (IOException e1) { 
					e1.printStackTrace();
					frame.dispose();
				}
				
				frame.setVisible(false); 
				frame.dispose();
 
	 
				
				/**
				File fDodajTuNowaKlase = new File("C:/DziennikElektroniczny/users/" + imieInazwisko + "/uczyKlasy.txt.txt"); 
				if(fDodajTuNowaKlase.exists() && !fDodajTuNowaKlase.isDirectory() && radiobtnWychowawca.isSelected())
				{
					System.out.println(" ok folder istnieje");
					//dodaj tekst z txtField "nazwa klasy" jako now� klase(now� linijk�) do pliku
					try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fDodajTuNowaKlase, true))))
					{
					    out.println(nameOfClass);  
					    System.out.println("Udalo sie dodac klase(nowa linijke do pliku txt");
						JOptionPane.showMessageDialog(null, "Dodano nazwe klasy pomy�lnie."); 
						 frame.setVisible(false); 
						 
					} 
					
					catch (IOException e1) 
					{
						 System.out.println("Nie udalo sie dodac nowej linijki do pliku(brak odpowiednich plikow)");
					}
				//stworz folder gdzie beda info o klasach 
				File fKlasyInfo = new File("C:/DziennikElektroniczny/users/" + imieInazwisko + "/klasyInfo");  
				//stworz folder w ktorym bedzie konkretna klasa z plikiem txt
				File fNewClassInfo = new File("C:/DziennikElektroniczny/users/" + imieInazwisko + "/klasyInfo/"+nazwaKlasy.getText()+".txt.txt"); 
				//jesli pole z nazwa klasy nie jest puste 
				if(nazwaKlasy.getText()!=null)
				{
					//jesli folder z informacjami o klasach nie istnieje
					if(!fKlasyInfo.exists())
					{
						//stworz folder w ktorym bd przechowywane info o klasach
						fKlasyInfo.mkdir();
						System.out.println("Created: " + fKlasyInfo);
						//jesli powiodlo sie tworzenie i folder istnieje gdzie mozna przechowac informacje o konkretnej klasie
						if(fKlasyInfo.exists())
						{
							//stworz plik txt z informacjami o konkretnej klasie
							PrintWriter writer;
							try {
								writer = new PrintWriter(fNewClassInfo, "UTF-8");
								writer.println(nazwaKlasy.getText());
								writer.println(rocznikOd.getText());
								writer.println(rocznikDo.getText());
								writer.println(iloscUczniow.getText());
								writer.close();
								frame.dispose();
							} catch (FileNotFoundException e1) { 
								e1.printStackTrace();
							} catch (UnsupportedEncodingException e1) { 
								e1.printStackTrace();
							}
							
						}
					}
				}
				//dokonczyc tworzenie w przypadku gdy folder istnieje i tylko dopisuje kolejne nowe pliki txt
				TwojeKlasy tk = new TwojeKlasy();
				tk.uruchomKlase(imieInazwisko);
				frame.dispose();
				}
			
				else {
					System.out.println("Nastapil blad przy dodaniu nowej klasy.");
					}
				frame.setVisible(false); 
				*/
				
			}
			
		});
		btnZapisz.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnZapisz.setForeground(SystemColor.textHighlightText);
		panel_2.add(btnZapisz);
		
		JButton btnAnuluj = new JButton("Zamknij");
		btnAnuluj.setBackground(Color.white);
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		panel_2.add(btnAnuluj);
		
		JButton btnNewButton = new JButton(" ");
		btnNewButton.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton.setBounds(1, 253, 437, 2);
		panel_1.add(btnNewButton);
	}
	
	
	void zamknijOknoUruchomTwojeKlasy(String sciezkaFolderuUzytkownika, String imieInazwisko, String i, String n, String[] listOfAllClass, String[] listClassIteach, String klasaWychowankowie)
	{
		TwojeKlasy tk = new TwojeKlasy();
	 
		tk.createGui( sciezkaFolderuUzytkownika,  i,  n,  listOfAllClass, listClassIteach,  klasaWychowankowie);
	
	}
}
