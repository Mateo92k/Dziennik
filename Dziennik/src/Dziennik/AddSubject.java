package Dziennik;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListCellRenderer;

import Dziennik.GuiMain.MyComboBoxRenderer;

import javax.swing.SwingConstants;

public class AddSubject {

	private JFrame frame;
	private JTextField txtPrzedmiot;
	private JComboBox comboBox;
	private JLabel lblPrzypiszDoKlasy;
	private JButton btnDodaj;
	private JButton btnAnuluj;
	String strSciezkaDoKataloguDomowegoUzytkownika, imie, nazwisko, wychowankowie;
	String[] wszystkieKlasy,uczeKlasy;
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSubject as = new AddSubject();
					as.initialize(null, null, null, null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
//	public AddSubject() {
//		initialize(null, null);
//	}

//	public void runit(String[] tablica)
//	{
//		initialize(String strSciezkaDomowa, String imieUzytkownika, String nazwiskoUzytkownika, String[] wszystkieKlasy, String[] uczyKlasy, String wychowankowie);
//	}
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	public void initialize(String sciezkaFolderuUzytkownika, String i,  String n,String[] listClassIteach) 
	{
		System.out.println("imieUzytkownika:" + i);
		System.out.println("nazwiskoUzytkownika:" + n);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNazwaPrzedmiotu = new JLabel("Nazwa przedmiotu");
		lblNazwaPrzedmiotu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazwaPrzedmiotu.setBounds(93, 51, 122, 26);
		frame.getContentPane().add(lblNazwaPrzedmiotu);
		
		txtPrzedmiot = new JTextField();
		txtPrzedmiot.setBounds(225, 50, 104, 29);
		frame.getContentPane().add(txtPrzedmiot);
		txtPrzedmiot.setColumns(10);
		 
		lblPrzypiszDoKlasy = new JLabel("Dodaj przedmiot do klasy:");
		lblPrzypiszDoKlasy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrzypiszDoKlasy.setBounds(68, 93, 149, 26);
		frame.getContentPane().add(lblPrzypiszDoKlasy);
		
	 
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setBounds(225, 228, 89, 23);
		frame.getContentPane().add(btnAnuluj);
		
		String[] listClassIteach2 = {"chuj", "kurwa"};
		
		@SuppressWarnings("unchecked")
		JComboBox ComboBox = new JComboBox(listClassIteach );
		ComboBox.setBounds(225, 93, 122, 26);  
		frame.getContentPane().add(ComboBox);
		
		btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(123, 228, 89, 23);
		frame.getContentPane().add(btnDodaj);
		btnDodaj.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//sprawdzanie poprawnosci przeslanych klas
//				for (int i = 0 ; i<listClassIteach.length ; i++)
//                	System.out.println("Sprawdz poprawnoœæ!: listClassIteach: " + listClassIteach[i]);
//				
				//todo: ponizej nie dziala 
  			   System.out.println("nazwa przedmiotu nowego: " + txtPrzedmiot.getText());
				int indexCombo = ComboBox.getSelectedIndex();   
  				int k = 0;
				for (  k = 0; k<listClassIteach.length;k++)
				{
					if(indexCombo==k)
					System.out.println("Dodano do klasy:" + listClassIteach[indexCombo]); 
					break;
				}
				
				String wybranyDziennik = "PKiSI";
				String strPrzedmioty ="C://DziennikElektroniczny/users//" + i + " " + n + "//klasyInfo//" + wybranyDziennik + "//przedmioty.txt";  
			    File fPrzedmioty = new File(strPrzedmioty);
				PrintWriter os; 
				
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fPrzedmioty, true))))
				{ 
				    	out.println(txtPrzedmiot.getText() ); 
				    	JOptionPane.showMessageDialog(null, "Pomyœlnie dodano wartoœæ z txtPrzedmiot do przedmioty.txt");  
				    	frame.dispose();
				   
//				    	TwojeKlasy tk = new TwojeKlasy();
//						tk.createGui( sciezkaFolderuUzytkownika,  i,  n,  listOfAllClass, listClassIteach,  KlasaWychowankowie);
				} 
				
				catch (IOException e1) 
				{
					e1.printStackTrace();
				} 
			
			}
		});
		
		
		
	}
	
 
 
}

//				String sciezkaDoZapisuPrzedmiotu = sciezkaFolderuUzytkownika + "//klasyInfo//" + listClassIteach[indexCombo] + "przedmioty.txt.txt";
//				
// 
//				 System.out.println("Czy sciezka zawiera nazwe klasy do ktorej chcesz dodac przedmiot?" + listClassIteach[indexCombo]);
//				 String strPrzypiszKlasie = listClassIteach[indexCombo];
//			     System.out.println("Czy sciezka przedmioty txt.: + " + sciezkaFolderuUzytkownika);
//			     String strSciezkaPrzedmioty = sciezkaFolderuUzytkownika + "//klasyInfo//" + strPrzypiszKlasie + "//przedmioty.txt.txt";
//			     File fPrzedmioty = new File(strSciezkaPrzedmioty);
//			     
//			     if(fPrzedmioty.exists())
//			     {
//			    	 System.out.println("Wszystko dziala");
//			     }
//			     else   System.out.println("B³¹d.");
				 //String nazwaPrzediotuDoZapisania = txtPrzedmiot.getText();
//				 //String sciezkaDoTxtPrzedmioty = sciezkaFolderuUzytkownika + "//klasyInfo//" + 
//				 dodajPrzedmiot(strSciezkaKataloguDomowegoUzytkownika, cbxWybranaKlasa, nazwaPrzediotuDoZapisania);
//				 try{
//						System.out.println("cbxWybranaKlasa to(powinno byc: nazwajakiejsklasy : "+ cbxWybranaKlasa);
//					}catch(Exception stringtocombo)
//					{ 
//						System.out.println("blad:" + stringtocombo); 
//					} 