package Dziennik; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddStudent {
	
	private static JButton  bAdd, bSaveAndExit, bExitWithoutSave;
	 
	public void createGui(String imieInazwisko, String sciezkaDoKlasy)
	{
		String iN = imieInazwisko;
		String sciezkaDoStudenciTxt = sciezkaDoKlasy;
		JFrame f = new JFrame("Dodawanie uczniów");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(740, 130);
		f.setVisible(true);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
	    f.setLocation(x, y);
	   
	    JPanel p = new JPanel();
		p.setLayout(null);
		
		JTextField txtImie = new JTextField("imie");
		txtImie.setFont(new Font("Verdana", Font.ITALIC, 11));
		txtImie.setForeground(SystemColor.inactiveCaptionText);
		txtImie.setHorizontalAlignment(SwingConstants.LEFT);
		txtImie.setBounds(10, 5, 150, 20);
		txtImie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtImie.setText("");
			}
		});
		p.add(txtImie);
		
		JTextField txtNazwisko = new JTextField("nazwisko");
		txtNazwisko.setFont(new Font("Verdana", Font.ITALIC, 11));
		txtNazwisko.setForeground(SystemColor.inactiveCaptionText);
		txtNazwisko.setHorizontalAlignment(SwingConstants.LEFT);
		txtNazwisko.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNazwisko.setText("");
			}
		});
		txtNazwisko.setBounds(160, 5, 260, 20);
		p.add(txtNazwisko);
		bAdd = new JButton("Dodaj"); 
		bAdd.setBounds(450, 5, 260, 20);
		bAdd.setBackground(new Color(0, 204, 102));
		bAdd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				f.dispose();
				f.setVisible(false);
				  String imieUcznia = txtImie.getText();
				  String nazwiskoUcznia = txtNazwisko.getText();
				  
				  sprawdzCzyIstniejeFolderZListaUczniow(iN, sciezkaDoKlasy);
				  dodajStudenta(imieUcznia, nazwiskoUcznia, sciezkaDoKlasy, f);
			}
		});
		
		p.add(bAdd);
		f.add(p);
		
	}
	
	void dodajStudenta(String imieUcznia, String nazwiskoUcznia, String sciezkaDoKlasy, JFrame f)
	{
		f.dispose();
		File fStudenci = new File(sciezkaDoKlasy); 
		System.out.println("TODO: " + sciezkaDoKlasy);
		String text = imieUcznia +  " "  + nazwiskoUcznia;
 		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fStudenci, true))))
		{
 			f.dispose();
		    out.println(text);  
		    System.out.println("Udalo sie dodac klase(nowa linijke do pliku txt");
			JOptionPane.showMessageDialog(null, "Rejestracja przebieg³a pomyœlnie. Mo¿esz siê zalogowaæ");
			Logowanie.runClassLogowanie(null, null);
			f.dispose();
			f.setVisible(false);
		} 
		
		catch (IOException e1) 
		{
			 System.out.println("Nie udalo sie dodac nowej linijki do pliku(brak odpowiednich plikow)");
		}  
 		finally{
 			f.dispose();
 		}
	}
		
	
	void sprawdzCzyIstniejeFolderZListaUczniow(String iN, String sciezkaDoStudenciTxt)
	{
		String imieInazwisko = iN;
		String PathstudenciTxt = sciezkaDoStudenciTxt;
		File file= new File(PathstudenciTxt);
		 
		if(!file.exists())
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
		}
		else System.out.println("Folder do przechowywania danych uzytkownikow ( " + file  + ") istnieje.");
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
            public void run() {
//            	 AddStudent as = new AddStudent();
//            	 as.createGui(null, null);
 				 
            	 //Komenda wyzej zakomentowana bo po wcisnieciu przycisku "dodaj klase" okno uruchamia sie dwa razy  
            }
        });
	}
}