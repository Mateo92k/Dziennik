/**
 * Dodatek do logowania: poki user nie poda hasla i loginu "button" disable
 */

package Dziennik;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.*;

import javax.swing.*;

import java.awt.SystemColor;
import java.awt.Font;

public class Logowanie {	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logowanie window = new Logowanie();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void runGuiMain(String str) {
	    GuiMain gm = new GuiMain();
	    gm.UruchomKlase(str);		    
	}
	 
	Connection connection = null;
	
	private JFrame frame;
	public JButton bZaloguj;
	public JTextField txtUserName, txtPassword;
	
	/**
	 * Create the application.
	 */
	public Logowanie() {
		initialize();
		//nazwa KLASY.nazwaMETODY z sqlConnection.java
		connection = sqlConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("E-Dziennik - LOGIN");
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
		JLabel userLabel = new JLabel("Nazwa uzytkownika( imie ):");
		userLabel.setBounds(57, 5, 130, 14);
		userLabel.setLayout(null);
		userLabel.setBounds(500, 500, 100, 100);
		panel.add(userLabel);
 
		JTextField userText = new JTextField(20);
		userText.setBounds(134, 185, 187, 31);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Haslo");
		passwordLabel.setBounds(92, 235, 62, 14);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(134, 227, 187, 31);
		panel.add(passwordText);

		JButton loginButton = new JButton("Zaloguj:");
		loginButton.setFont(new Font("Sylfaen", Font.BOLD, 18));
		loginButton.setForeground(SystemColor.text);
		loginButton.setBounds(134, 269, 187, 31);
		panel.add(loginButton);
		//Sluchacz zdarzen przycisku "Zaloguj".
		String temp = "";
		userText.setText("Michal Klich");
		loginButton.addActionListener(new ActionListener() 
		{
			
			 
			
			public void actionPerformed(ActionEvent e) {
			 
					String personalia = userText.getText(); // get string from jtextfield
					
						try
					{

					//tworzenie zapytania
					String query = "select * from teacher where login = ? and haslo = ?";
					 
					
					//klasa statement <obiekt tej klasy> = polaczenie.tejklasy(zapytanie)
					PreparedStatement pst = connection.prepareStatement(query);
				 
							
					//dwa argumenty: parametrIndex to jest indeks zapytania, w tym wypadku username ma indeks 0 a password indeks rowny 1
					pst.setString(1, userText.getText());
					pst.setString(2, passwordText.getText());
					
					
					//teraz mamy wartosci i query, teraz czas na rezultat: // rs wykonuje moje zapytanie
					//obojetnie co wykonam pst.execute wykona zapytanie i zawartosc bedzie w rs.
					ResultSet rs = pst.executeQuery();
					
					//petla do execute tego query dopóki rezultat bedzie wysylany(czyli bedzie co pobierac)
					//tworze integer:
					int count = 0; 
					while(rs.next())
					{
						count += 1; 
					}
					
					if(count == 1) 
						{
							
						JOptionPane.showMessageDialog(null, "Zalogowano pomyœlnie");	 
						frame.setVisible(false);
						runGuiMain(personalia);
						}
					else if(count > 1) JOptionPane.showMessageDialog(null, "Error: Database structure.");
					else JOptionPane.showMessageDialog(null, "Podany login lub has³o s¹ nieprawid³owe.");
				}
				
				//zamkniecie polaczenia
				  
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ("Blad nr.X: " + ex));
				}
				finally{
					//zamkniecie polaczenia z baza "connec
					try{
						
					}catch(Exception ex)
					{ 
						
					}
				}
			}
			
		});
		
		JButton btnRegister = new JButton("Zarejestruj sie");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{ 
				
			}
		});
		btnRegister.setBounds(134, 338, 187, 23);
		btnRegister.setEnabled(true);
		loginButton.setBackground(new Color(102, 102, 255));
		btnRegister.setBackground(Color.WHITE);
		panel.add(btnRegister);
		
		frame.getContentPane().add(panel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(92, 193, 62, 14);
		panel.add(lblLogin);
		
		JLabel lblDziennikElektroniczny = new JLabel("               Dziennik Elektroniczny ");
		lblDziennikElektroniczny.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDziennikElektroniczny.setBounds(0, 0, 444, 48);
		panel.add(lblDziennikElektroniczny);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Logowanie.class.getResource("/res/Login.png")));
		label.setBounds(193, 103, 71, 73);
		panel.add(label);
		
		JLabel lblZalogujSiBy = new JLabel("Zaloguj si\u0119 by korzysta\u0107 z dzeinnika.");
		lblZalogujSiBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZalogujSiBy.setBounds(94, 59, 283, 23);
		panel.add(lblZalogujSiBy);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(69, 93, 276, 241);
		editorPane.setEnabled(false);
		panel.add(editorPane); 
	}

	public static void runClassLogowanie() 
	{
		
		Logowanie w = new Logowanie();
		w.frame.setVisible(true); 
	}
}
