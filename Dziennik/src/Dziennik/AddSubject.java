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
	 
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSubject as = new AddSubject();
					as.initialize(null, tablica);
					
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

	public void runit(String[] tablica)
	{
		initialize("Michal KLIch", tablica, null);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(String persona, String[] tablica, String[] listClassIteach) {
		System.out.println("Dl" +tablica.length);
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
		
		JComboBox ComboBox = new JComboBox(listClassIteach );
		ComboBox.setBounds(225, 93, 122, 26);  
		frame.getContentPane().add(ComboBox);
		
		btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(123, 228, 89, 23);
		btnDodaj.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(ComboBox.getSelectedItem()!=null)
				{	
					String cbxWybranaKlasa =  ComboBox.getSelectedItem().toString();
					String nazwaPrzedmiotu = txtPrzedmiot.getText();
					//jesli katalog z informacjami o klasie nie istnieje,stworz go
					File fAboutClass = new File("c:\\dziennik\\users\\"+ persona + "\\AboutClass");
					if(!fAboutClass.exists())
					{
						//stworz folder z informacjami o danej klasie
						fAboutClass.mkdir(); 
					}
					//czy istnieje juz ten przedmiot w tej klasie
					File fKonkretnaKlasa = new File("c:\\dziennik\\users\\"+ persona + "\\AboutClass\\" + cbxWybranaKlasa);
					if(!fKonkretnaKlasa.exists())
					{
						fKonkretnaKlasa.mkdir();
					}
					
					File fPrzedmioty = new File("c:/dziennik/users/"+ persona + "/AboutClass/" + cbxWybranaKlasa + "/Przedmioty/");
					if(!fPrzedmioty.exists())
					{
						fPrzedmioty.mkdir();
					}
					
					File fKonkretnyPrzedmiot = new File("c:/dziennik/users/"+ persona + "/AboutClass/" + cbxWybranaKlasa + "/Przedmioty/" + txtPrzedmiot.getText()+".txt.txt");
					if(!fKonkretnyPrzedmiot.exists())
					{
						PrintWriter writer;
						try {
							writer = new PrintWriter(fKonkretnyPrzedmiot, "UTF-8");
							writer.println(nazwaPrzedmiotu );
						 
							writer.close();
						} catch (FileNotFoundException e1) { 
							e1.printStackTrace();
						} catch (UnsupportedEncodingException e1) { 
							e1.printStackTrace();
						}
						
					}
					
					if(fAboutClass.exists() && fKonkretnaKlasa.exists() && fKonkretnyPrzedmiot.exists() && !fKonkretnyPrzedmiot.isDirectory())
					{
						//stworz plik z konkretn klas¹ 
						System.out.println("wokr");
					}
				}
			}
		});
		
		
		frame.getContentPane().add(btnDodaj);
	}
}
