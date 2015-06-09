/**
 * Dodatek do logowania: poki user nie poda hasla i loginu "button" disable
 */

package Dziennik;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import com.thoughtworks.xstream.io.path.Path;

public class Rejestrowanie {	
public String[] args = {};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rejestrowanie window = new Rejestrowanie();
					window.frame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public  void runClassLogowanie(String imieUzytkownika, String nazwiskoUzytkownika) {
		 
 		Logowanie.runClassLogowanie(imieUzytkownika, nazwiskoUzytkownika);
		
	}
	
	public void runGuiMain(String str) {
	    GuiMain gm = new GuiMain();
	    gm.loadDataAndRunApp(str); 
	}
 
	public void pobierzKatalogDomowy(String imieUzytkownika, String nazwiskoUzytkownika, String nazwaKlasy, JFrame frame2) 
	{
		String nazwaKl = nazwaKlasy;
		String i = imieUzytkownika;
		String n = nazwiskoUzytkownika;
		File[] paths; 
		
		// zwroc sciezki dyskow dla folderow i katalogow
		paths = File.listRoots();
	
		//dla kazdego elementu z tablicy plikow która zawiera sciezke dysku
		for(File path:paths)
		{ 
		    // drukuje sciezke dysku 
		   
		    
		    //przypisz pierwszy dysk do katalogu domowego
		    katalogDomowy = path.toString();
		    
		    //przerwij petle by nie nadpisac kataloguDomowego kolejnymi nazwami dyskow z tablicy. 
		    break;
		    
		} 
		 sprawdzPoprawnoscFolderowInstalacyjnych(katalogDomowy, i, n, nazwaKl, frame2);
		 frame.dispose();
		 frame2.disable();
	}
	
	
	
	public void sprawdzPoprawnoscFolderowInstalacyjnych(String nazwaDysku, String imieUzytkownika, String nazwiskoUzytkownika, String nazwaKlasy, JFrame frame2) 
	{
		//usun ostatni znak '/' z pobranej sciezki i zamien na '//'
		String sciezkaDoProgramu = katalogDomowy;
		String imieInazwisko = imieUzytkownika + " " + nazwiskoUzytkownika;
		pobierzNazweDysku(nazwaDysku);
		String nk = nazwaKlasy;
		
		System.out.println("Nazwa montowanego dysku " + nazwaDysku);
		
		//sprawdz czy na dysku istniej¹ ju¿ pliki wymagane do dzia³ania programu, innymi s³owy: czy jakiœ u¿ytkownik rejestrowa³ siê ju¿ z tego komputera
		String strFfolderInstalacyjny = sciezkaDoProgramu + "//DziennikElektroniczny//";
		File fSciezkaDoProgramu = new File(strFfolderInstalacyjny );
		stworzFolderInstalacyjnyJesliPotrzeba(fSciezkaDoProgramu);
		stworzKatalogDoPrzechowywaniaKontUzytkownikow(fSciezkaDoProgramu);
		stworzFolderUzytkownika(fSciezkaDoProgramu, imieUzytkownika, nazwiskoUzytkownika, nk, frame2); 
		 
		 

		 
	}
	public void stworzIDodajFolderDlawprowadzonejKlasy(File fSciezkaDoProgramu, String imieInazwisko, String nazwaKlasy)
	{
		File fKlasyWDataNowaKlasa = new File("c:\\DziennikElektroniczny\\data\\klasyInfo\\" + txtNazwaKlasy.getText());
		File file = new File("c:\\DziennikElektroniczny\\users\\" + imieInazwisko + "\\klasyInfo\\" + nazwaKlasy + "\\studenci.txt.txt");
		File filePrzedmioty = new File("c:\\DziennikElektroniczny\\users\\" + imieInazwisko + "\\klasyInfo\\" + nazwaKlasy + "\\przedmioty.txt.txt");
		if (!fKlasyWDataNowaKlasa.exists()) 
		  { 
			  stworzFolder(fKlasyWDataNowaKlasa); 
			  System.out.println("Stworzono" + fKlasyWDataNowaKlasa); 
		  }
		 
		 stworzPlikStudenciTxt(file, imieInazwisko, nazwaKlasy);
		 
		 
		 
	}
	
	void stworzPlikTxtZDanymiUczniow(File file, String imieInazwisko, String nazwaKLasy)
	{
		File fStudenciTxt = file;
		if (!fStudenciTxt.exists()) 
		  { 
			try { 
			      if (file.createNewFile()){
			        System.out.println("File is created!");
			      }else{
			        System.out.println("File already exists.");
			      }
		 
		    	} catch (IOException e) 
			{
			      e.printStackTrace();
			}
			  System.out.println("InfoLog nr2 " + fStudenciTxt); 
		  }
		
		if(fStudenciTxt.exists())
		{
			System.out.println(fStudenciTxt + ":  Istnieje");
		}
		
		
	}
	
	public void stworzFolderKlasywData(File fSciezkaDoProgramu)
	{
		File fKlasyWData = new File("c:\\DziennikElektroniczny\\data\\klasyInfo\\");
		if (!fKlasyWData.exists()) 
		  { 
			  stworzFolder(fKlasyWData); 
			  System.out.println("Stworzono" + fKlasyWData); 
		  }
	}
	
	public void stworzFolderData(File fSciezkaDoProgramu)
	{ 
		File fKatalogDanychAplikacji = new File("c:\\DziennikElektroniczny\\data");
		if (!fKatalogDanychAplikacji.exists()) 
		  { 
			  stworzFolder(fKatalogDanychAplikacji); 
			  System.out.println("Stworzono" + fKatalogDanychAplikacji); 
		  }
	}
	
	public void stworzPlikStudenciTxt(File fSciezkaDoProgramu, String imieUzytkownika, String nazwiskoUzytkownika)
	{
		String strStudenci ="C://DziennikElektroniczny/users//" + imieUzytkownika + " " + nazwiskoUzytkownika + "//klasyInfo//" + txtNazwaKlasy.getText() + "//studenci.txt";  
		 File fStudenci = new File(strStudenci);
		 FileWriter fileWriter;
		 if(!fStudenci.exists() && !fStudenci.isDirectory())
		 {
			  System.out.println("Brak pliku studenci.txt");
			 
				if (!fStudenci.exists()) 
				  { 
					try { 
					      if (fStudenci.createNewFile()){
					        System.out.println("File is created!");
					      }else{
					        System.out.println("File already exists.");
					      }
				 
				    	} catch (IOException e) {
					      e.printStackTrace();
					}
					  System.out.println("InfoLog nr2 " + fStudenci); 
				  }
			  
		 }
		 
		 //fOCeny
			String strOceny ="C://DziennikElektroniczny/users//" + imieUzytkownika + " " + nazwiskoUzytkownika + "//klasyInfo//" + txtNazwaKlasy.getText() + "//Obecnosci//";  
			 File fOceny = new File(strOceny);
			 FileWriter fileWriter1;
			 if(!fOceny.exists() && !fOceny.isDirectory())
			 {
				  System.out.println("Brak pliku fOceny(.txt)");
				 
					if (!fOceny.exists()) 
					  { 
						try { 
						      if (fOceny.createNewFile()){
						        System.out.println("Plik fOceny stworzony");
						      }else{
						        System.out.println("Plik fOceny istnieje");
						      }
					 
					    	} catch (IOException e) {
						      e.printStackTrace();
						}
						  System.out.println("InfoLog nr2 " + fStudenci); 
					  }
				  
			 }
		 
		 String strPrzedmioty ="C://DziennikElektroniczny/users//" + imieUzytkownika + " " + nazwiskoUzytkownika + "//klasyInfo//" + txtNazwaKlasy.getText() + "//przedmioty.txt";  
		 File fPrzedmioty = new File(strPrzedmioty);
		 if(!fPrzedmioty.exists() && !fPrzedmioty.isDirectory())
		 {
			  System.out.println("Brak pliku przedmioty.txt");
			 
				if (!fPrzedmioty.exists()) 
				  { 
					try { 
					      if (fPrzedmioty.createNewFile()){
					        System.out.println("File fPrzedmioty created!");
					      }else{
					        System.out.println("File already exists.");
					      }
				 
				    	} catch (IOException e) {
					      e.printStackTrace();
					}
					  System.out.println("InfoLog nr2 " + fStudenci); 
					  
				  }
				
		
			  
		 }

		else System.out.println("Folder do przechowywania informacjach o klasach które uczy uzytkownik ( " + fStudenci  + ") istnieje."); 
		 String in =  imieUzytkownika +  " " + nazwiskoUzytkownika;
		 AddStudent addstudent = new AddStudent();
		  addstudent.createGui(in, strStudenci);
		  /**
		   * Tworzenie metody tworzoccej folder dla ocen
		   */
			/**
			 * Tworzenie metody tworz¹cej folder dla ocen gdzie bed¹ zpaisywanie
			 */
					String strOceny1 ="C://DziennikElektroniczny/users//" + imieUzytkownika + " " + nazwiskoUzytkownika + "//klasyInfo//" + txtNazwaKlasy.getText() + "//oceny.txt";  
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
							 
							    	} catch (IOException e) {
								      e.printStackTrace();
								}
								  System.out.println("InfoLog nr2 " + fOceny1); 
							  }
				  
			 }
	}
	
	public void stworzFolderWKlasyInfoZPodanaKlasa(File fSciezkaDoProgramu, String imieUzytkownika, String nazwiskoUzytkownika)
	{
		 
		File fDodaniePodanejKlasyDoKatalogu = new File(fSciezkaDoProgramu + "//users//" + imieUzytkownika + " " + nazwiskoUzytkownika + "//klasyInfo//" + txtNazwaKlasy.getText());
		String strDodaniePodanejKlasyDoKatalogu = (fSciezkaDoProgramu + "//users//" + imieUzytkownika + " " + nazwiskoUzytkownika + "//klasyInfo//" + txtNazwaKlasy.getText());
		stworzFolder(fDodaniePodanejKlasyDoKatalogu);  
	}
	
	//todo:odkomnentowac
//	public void przekieruj(JFrame frame)
//	{
//				JOptionPane.showMessageDialog(null, "Rejestracja przebieg³a pomyœlnie. Mo¿esz siê zalogowaæ.");	 
//				  frame.dispose();
//				 Logowanie.main(args);
//	}
	
	public void stworzFolderZListaKlasUzytkownika(File fSciezkaDoProgramu, String imieUzytkownika, String nazwiskoUzytkownika) 
	{
	     String strUczyKlasy ="C:/DziennikElektroniczny/users/" + imieUzytkownika + " " + nazwiskoUzytkownika + "/uczyKlasy.txt.txt";  
		 File fUczyKlasy = new File(strUczyKlasy);
	 
		//txtNazwaKlasy
		 
		 FileWriter fileWriter;
		 if(!fUczyKlasy.exists() && !fUczyKlasy.isDirectory())
		 {
			  
			 try 
			 {
				 		//tworzy plik txt
				 		fileWriter = new FileWriter(fUczyKlasy);
						PrintWriter printWriter = new PrintWriter(fileWriter);
						
						//Dodaj tekst do pliku nie usuwaj¹c zawartoœci obecnej w pliku
						try(PrintWriter output = new PrintWriter(new FileWriter(fUczyKlasy,false))) 
					{ 
						//zapisywanie cos do pliku
					    output.printf("%s\r\n", txtNazwaKlasy.getText());
					     
					}  
						
		 
				}
		 	catch (IOException e2) 
			{
				e2.printStackTrace();
			}
			  
		}
		
		else System.out.println("Folder do przechowywania informacjach o klasach które uczy uzytkownik ( " + fUczyKlasy  + ") istnieje."); 
	}
	
	public void stworzFolderKlasyInfo(File fSciezkaDoProgramu, String imieUzytkownika, String nazwiskoUzytkownika) 
	{
		String strKlasyInfo = fSciezkaDoProgramu + "//users//" + imieUzytkownika + " " + nazwiskoUzytkownika + "//klasyInfo"; 
		File fKlasyInfo = new File(strKlasyInfo);
		if(!fKlasyInfo.exists())
		{
			stworzFolder(fKlasyInfo); 
			System.out.println("Utworzono folder do przechowywania informacjach o klas istnieje uzytkownikow" + fKlasyInfo); 
		}
		else System.out.println("Folder do przechowywania informacjach o klas istnieje uzytkownikow ( " + fKlasyInfo  + ") istnieje."); 
	}
	
	public void stworzFolderUzytkownika(File fSciezkaDoProgramu, String imieUzytkownika, String nazwiskoUzytkownika, String nk, JFrame frame2) 
	{
		String strNazwaFolderuUzytkownika = fSciezkaDoProgramu + "//users//" + imieUzytkownika + " " + nazwiskoUzytkownika; 
		File fNazwaFolderuUzytkownika = new File(strNazwaFolderuUzytkownika);
		if(!fNazwaFolderuUzytkownika.exists())
		{
			
			stworzFolder(fNazwaFolderuUzytkownika); 
			stworzFolderKlasyInfo(fSciezkaDoProgramu, imieUzytkownika, nazwiskoUzytkownika);
			stworzFolderZListaKlasUzytkownika(fSciezkaDoProgramu, imieUzytkownika, nazwiskoUzytkownika);
			stworzFolderWKlasyInfoZPodanaKlasa(fSciezkaDoProgramu, imieUzytkownika, nazwiskoUzytkownika);
			stworzPlikStudenciTxt(fSciezkaDoProgramu, imieUzytkownika, nazwiskoUzytkownika);
			  // tworzenie ..\\data\\klasa\\
			stworzFolderData(fSciezkaDoProgramu);
			stworzFolderKlasywData(fSciezkaDoProgramu);
			stworzIDodajFolderDlawprowadzonejKlasy(fSciezkaDoProgramu,  imieUzytkownika,  nazwiskoUzytkownika);  
			
			
			System.out.println("Zakonczono metode ( sprawdzPoprawnoscFolderowInstalacyjnych ) "); 
			JOptionPane.showMessageDialog(null, "Konto stworzone. Proszê wprowadziæ przynajmniej jednego ucznia aby kontynuowaæ.");	 
  		
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "U¿ytkownik " + imieUzytkownika + " " + nazwiskoUzytkownika + " ju¿ istnieje!. Zaloguj siê..");
			frame.dispose();
			runClassLogowanie(imieUzytkownika, nazwiskoUzytkownika);
		}
	}
	
	public void stworzKatalogDoPrzechowywaniaKontUzytkownikow(File fSciezkaDoProgramu) 
	{
		String strFolderDoPrzechowywaniaUzytkownikow = fSciezkaDoProgramu + "//users//";
		File fFolderDoPrzechowywaniaUzytkownikow = new File(strFolderDoPrzechowywaniaUzytkownikow);
		if(!fFolderDoPrzechowywaniaUzytkownikow.exists())
		{
			stworzFolder(fFolderDoPrzechowywaniaUzytkownikow);
		}
		else
		{
			System.out.println("Folder do przechowywania danych uzytkownikow ( " + fFolderDoPrzechowywaniaUzytkownikow  + ") istnieje.");
		}
		
		
		
	}
	
	public void stworzFolder(File fSciezkaDoProgramu) 
	{
		if(!fSciezkaDoProgramu.exists())
		{
			fSciezkaDoProgramu.mkdir();
		} 
	}
	
	public void stworzFolderInstalacyjnyJesliPotrzeba(File fSciezkaDoProgramu) 
	{
		if(fSciezkaDoProgramu.exists())
		{
			System.out.println("Sciezka do programu ( " + fSciezkaDoProgramu  + ") istnieje.");
		}
		else stworzFolderInstalacyjny(fSciezkaDoProgramu) ;
	}
	
	//przyklad: tworzenie folderu o sciezce c:/DziennikElektroniczny/
	public void stworzFolderInstalacyjny(File fSciezkaDoProgramu) 
	{
		fSciezkaDoProgramu.mkdir();
		System.out.println("Stworzono sciezkie (" + fSciezkaDoProgramu + ").");
	}
	
	public String pobierzNazweDysku(String nazwaDysku) {
		 if (nazwaDysku.length() > 0 && nazwaDysku.charAt(nazwaDysku.length()-1)==((char) 92)) {
			 nazwaDysku = nazwaDysku.substring(0, nazwaDysku.length()-1);
		    }
		 System.out.println("Nazwa dysku bez ukosnika: " + nazwaDysku);
		    return nazwaDysku;
		}
	
	private JFrame frame;
	public JButton bZaloguj;
	public JTextField txtUserName, txtPassword, txtNazwaKlasy;
	String katalogDomowy;
	
	
	/**
	 * Create the application.
	 */
	public Rejestrowanie() {
		initialize();
		//nazwa KLASY.nazwaMETODY z sqlConnection.java
		//connection = sqlConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("E-Dziennik - Rejestracja");
		frame.setBounds(150, 300, 450, 300);
		frame.setSize(450,400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setLayout(null);
  
		JTextField txtImie = new JTextField(20);
		txtImie.setBounds(134, 100, 187, 31);
		panel.add(txtImie);

		JTextField txtNazwisko = new JTextField(20);
		txtNazwisko.setBounds(134, 135, 187, 31);
		panel.add(txtNazwisko);
		
		JLabel passwordLabel = new JLabel("Haslo");
		passwordLabel.setBounds(92, 170, 62, 14);
		panel.add(passwordLabel);

		JTextField passwordText = new JTextField(20);
		passwordText.setBounds(134, 170, 187, 31);
		panel.add(passwordText);

//		JButton btnChooseFolder = new JButton("Wybierz folder instalacyjny");
//		//btnChooseFolder.setFont(new Font("Sylfaen", Font.CENTER_BASELINE, 18));
//		btnChooseFolder.setForeground(SystemColor.text);
//		btnChooseFolder.setBounds(74, 269, 270, 25);
//		
//		btnChooseFolder.setEnabled(false);
//		panel.add(btnChooseFolder);
//		//Sluchacz zdarzen przycisku "Zaloguj".
//		
//		btnChooseFolder.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//
//				JFileChooser f = new JFileChooser();
//		        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
//		        f.showSaveDialog(null); 
//		        //System.out.println(f.getCurrentDirectory());
//		        System.out.println(f.getSelectedFile());
//		        String katalogDomowy = f.getSelectedFile().toString();
//		        System.out.println("Katalog domowy to: " + katalogDomowy);
//		        
//		        
//				//String imieINazwisko = txtPersonalia.getText();
// 				//runGuiMain(imieINazwisko);
//			}
//		});
	 
		
		JButton btnRegister = new JButton("Za³ó¿ konto");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String imieUzytkownika =  txtImie.getText();
				String nazwiskoUzytkownika =  txtNazwisko.getText();
				String NazwaKlasy =  txtNazwaKlasy.getText();
				if (!txtImie.getText().isEmpty() && !txtNazwisko.getText().isEmpty() && !txtNazwaKlasy.getText().isEmpty())
					{ 
									System.out.println("Pola: imie uzytk. i nazwisko s¹ poprawne.");
									pobierzKatalogDomowy(imieUzytkownika, nazwiskoUzytkownika, NazwaKlasy, frame); 	  
									 
					}
				else JOptionPane.showMessageDialog(null, "Wype³nij wszystkie pola.");	 
				  
			}
		});
		btnRegister.setBounds(72, 302, 270, 31);
		btnRegister.setFont(new Font("Sylfaen", Font.CENTER_BASELINE, 17));
		btnRegister.setForeground(SystemColor.text);
		btnRegister.setEnabled(true);
		btnRegister.setBackground(new Color(102, 102, 255));
		
		JButton btnZalogujSie = new JButton("Masz ju¿ konto? Zaloguj siê");
		btnZalogujSie.setBounds(72, 342, 270, 25);
		btnZalogujSie.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		btnZalogujSie.setForeground(SystemColor.text);
		btnZalogujSie.setEnabled(true);
		btnZalogujSie.setBackground(new Color(102, 102, 255));
		btnZalogujSie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				 Logowanie.main(args);
				 
				
			}
		});
		panel.add(btnZalogujSie);
		panel.add(btnRegister);
		
		frame.getContentPane().add(panel);
		
		JLabel lblImie = new JLabel("Imie");
		lblImie.setBounds(88, 100, 92, 14);
		panel.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(68, 135, 92, 14);
		panel.add(lblNazwisko);
		
		JLabel lblNazwaKlasy = new JLabel("Podaj klasê któr¹ uczysz");
		lblNazwaKlasy.setBounds(70, 220,  187, 32);
		panel.add(lblNazwaKlasy);
		
		txtNazwaKlasy = new JTextField();
		txtNazwaKlasy.setBounds(228, 220, 92, 24);
		 panel.add(txtNazwaKlasy);
		
		JLabel lblDziennikElektroniczny = new JLabel("               Dziennik Elektroniczny ");
		lblDziennikElektroniczny.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDziennikElektroniczny.setBounds(0, 0, 444, 48);
		panel.add(lblDziennikElektroniczny);
		
		JButton btnDodajUczniow = new JButton("Dodaj uczniów");
		btnDodajUczniow.setBounds(195, 250, 122, 24);
		btnDodajUczniow.setEnabled(false);
		btnDodajUczniow.setVisible(false);
		panel.add(btnDodajUczniow);
		
		JButton btnDodajKlase = new JButton("Dodaj klase");
		btnDodajKlase.setBounds(70, 250, 115, 24);
		btnDodajKlase.addActionListener(new ActionListener() 
		{
			String imieUzytkownika =  txtImie.getText();
			String nazwiskoUzytkownika =  txtNazwisko.getText();
			public void actionPerformed(ActionEvent e)
			{
			
				if(!txtImie.getText().isEmpty() && !txtNazwisko.getText().isEmpty() && !txtNazwaKlasy.getText().isEmpty())
				{ 
					frame.dispose();
					String imieUzytkownika =  txtImie.getText();
					AddNewClass adc = new AddNewClass(); 
				}
				else JOptionPane.showMessageDialog(null, "Pola: imie, nazwisko oraz nazwa klasy musz¹ byæ poprawnie wype³nione przed t¹ operacj¹.");	 
			}
			
		});
		btnDodajKlase.setEnabled(false);
		btnDodajKlase.setVisible(false);
		panel.add(btnDodajKlase);
		
//		JLabel label = new JLabel("");
//		label.setIcon(new ImageIcon(Rejestrowanie.class.getResource("/res/Login.png")));
//		label.setBounds(193, 103, 71, 73);
//		panel.add(label);
		
		JLabel lblZalogujSiBy = new JLabel("Zarejestruj si\u0119 by korzysta\u0107 z dzeinnika.");
		lblZalogujSiBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZalogujSiBy.setBounds(94, 59, 283, 23);
		panel.add(lblZalogujSiBy);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(69, 93, 276, 241);
		editorPane.setEnabled(false);
		panel.add(editorPane);


		
	}

	 
}
