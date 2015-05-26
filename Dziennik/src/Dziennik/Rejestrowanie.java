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
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.thoughtworks.xstream.io.path.Path;

public class Rejestrowanie {	
	
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
	
	public  void runClassLogowanie() {
		 
 		Logowanie.runClassLogowanie();
		
	}
	
	public void runGuiMain(String str) {
	    GuiMain gm = new GuiMain();
	    gm.createDirectoryIfNeeded(str); 
	}
 
 
	//myConn bedzie uzyte do polaczenia z baza danych
	//nazwanie polaczenia dla tej klasy z java.sql class
	//Connection connection = null;
	
	private JFrame frame;
	public JButton bZaloguj;
	public JTextField txtUserName, txtPassword;
	
	
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
		JLabel userLabel = new JLabel("Nazwa uzytkownika( imie ):");
		userLabel.setBounds(57, 5, 130, 14);
		userLabel.setLayout(null);
		userLabel.setBounds(500, 500, 100, 100);
		panel.add(userLabel);

		
		JTextField txtPersonalia = new JTextField(20);
		txtPersonalia.setBounds(134, 185, 187, 31);
		panel.add(txtPersonalia);

		JLabel passwordLabel = new JLabel("Haslo");
		passwordLabel.setBounds(92, 235, 62, 14);
		panel.add(passwordLabel);

		JTextField passwordText = new JTextField(20);
		passwordText.setBounds(134, 227, 187, 31);
		panel.add(passwordText);

		JButton loginButton = new JButton("Zarejestruj i zaloguj");
		loginButton.setFont(new Font("Sylfaen", Font.BOLD, 18));
		loginButton.setForeground(SystemColor.text);
		loginButton.setBounds(74, 269, 270, 31);
		panel.add(loginButton);
		//Sluchacz zdarzen przycisku "Zaloguj".
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{

				JFileChooser f = new JFileChooser();
		        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		        f.showSaveDialog(null);

		        System.out.println(f.getCurrentDirectory());
		        System.out.println(f.getSelectedFile());
		        String homeFolderUser = f.getSelectedFile().toString();
		        System.out.println(homeFolderUser);
		        
		        
				String imieINazwisko = txtPersonalia.getText();
 				runGuiMain(imieINazwisko);
			}
		});
		
		JButton btnRegister = new JButton("Zarejestruj sie");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRegister.setBounds(134, 338, 187, 23);
		btnRegister.setEnabled(true);
		loginButton.setBackground(new Color(102, 102, 255));
		btnRegister.setBackground(Color.WHITE);
		panel.add(btnRegister);
		
		frame.getContentPane().add(panel);
		
		JLabel lblLogin = new JLabel("Imie i nazwisko");
		lblLogin.setBounds(38, 193, 92, 14);
		panel.add(lblLogin);
		
		JLabel lblDziennikElektroniczny = new JLabel("               Dziennik Elektroniczny ");
		lblDziennikElektroniczny.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDziennikElektroniczny.setBounds(0, 0, 444, 48);
		panel.add(lblDziennikElektroniczny);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Rejestrowanie.class.getResource("/res/Login.png")));
		label.setBounds(193, 103, 71, 73);
		panel.add(label);
		
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
