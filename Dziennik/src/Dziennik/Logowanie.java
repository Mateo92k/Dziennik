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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.*;

import javax.swing.*;

import java.awt.SystemColor;
import java.awt.Font;
import java.io.File;

public class Logowanie {	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logowanie window = new Logowanie("","");
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void uruchomGlowneOknoAplikacji(String iN) {
		String imieInazwisko = iN;
	    GuiMain gm = new GuiMain();
	    gm.UruchomKlase(imieInazwisko);		    
	}
	 
	Connection connection = null;
	
	private JFrame frame;
	public JButton bZaloguj;
	public JTextField txtUserName, txtPassword;
	String katalogDomowy ;
	String imieUsera, nazwiskoUsera;
	public String[] args = {};
 
	public Logowanie(String imieUzytkownika, String nazwiskoUzytkownika) {
		initialize(imieUzytkownika, nazwiskoUzytkownika);
		//nazwa KLASY.nazwaMETODY z sqlConnection.java
		connection = sqlConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param nazwiskoUzytkownika 
	 * @param imieUzytkownika 
	 */
	public void initialize(String imieUzytkownika, String nazwiskoUzytkownika) {
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
 
		JTextField loginNick = new JTextField(20);
		loginNick.setBounds(134, 185, 187, 31);
		loginNick.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	loginNick.setText("");
            }
        });
		panel.add(loginNick);

		JLabel passwordLabel = new JLabel("Haslo");
		passwordLabel.setFocusable(true);
		passwordLabel.setBounds(92, 235, 62, 14);
		panel.add(passwordLabel);

		JTextField passwordText = new JPasswordField(20);
		passwordText.setBounds(134, 227, 187, 31);
		panel.add(passwordText);

		JButton loginButton = new JButton("Zaloguj:");
		loginButton.setFont(new Font("Sylfaen", Font.BOLD, 18));
		loginButton.setForeground(SystemColor.text);
		loginButton.setBounds(134, 269, 187, 31);
		panel.add(loginButton);
		//Sluchacz zdarzen przycisku "Zaloguj".
		String temp = "";
		loginNick.setText("np. Jan Kowalski");
		loginButton.addActionListener(new ActionListener() 
		{  
			public void actionPerformed(ActionEvent e) { 
					String imieInazwisko = loginNick.getText(); 
					
					try
					{
						
					//tworzenie zapytania
					String query = "select * from teacher where login = ? and haslo = ?";
					 
					
					//klasa statement <obiekt tej klasy> = polaczenie.tejklasy(zapytanie)
					PreparedStatement pst = connection.prepareStatement(query);
				 
							
					//dwa argumenty: parametrIndex to jest indeks zapytania, w tym wypadku username ma indeks 0 a password indeks rowny 1
					pst.setString(1, loginNick.getText());
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
					  
					if((count == 1)) 
						{ 
						JOptionPane.showMessageDialog(null, "Zalogowano pomyœlnie");	 
						frame.setVisible(false);
						uruchomGlowneOknoAplikacji(imieInazwisko);
						}
					else if(count > 1) JOptionPane.showMessageDialog(null, "Blad struktury bazy danych!");
					else 
					{
						File[] paths; 
						paths = File.listRoots();
						for(File path:paths)
						{ 
						    katalogDomowy = path.toString();
						    break;
						} 
						if (katalogDomowy.length() > 0 && katalogDomowy.charAt(katalogDomowy.length()-1)==((char) 92)) {
							katalogDomowy = katalogDomowy.substring(0, katalogDomowy.length()-1);
						    }
						
						String[] splitStr = imieInazwisko.split("\\s+");
			    		imieUsera = splitStr[0];
			    		nazwiskoUsera = splitStr[1]; 
						File fNazwaFolderuUzytkownika = new File(katalogDomowy + "//DziennikElektroniczny//users//" + imieUsera + " " + nazwiskoUsera );
						System.out.println( imieUsera);
						System.out.println("Imie to: " + imieUsera + ". Nazwisko to: " + nazwiskoUsera);
						if(( fNazwaFolderuUzytkownika.exists()))
						{ 
						JOptionPane.showMessageDialog(null, "Zalogowano pomyœlnie"); 
						frame.setVisible(false);
						uruchomGlowneOknoAplikacji(imieInazwisko);
						System.out.println("persona " + imieInazwisko);
						}
						else JOptionPane.showMessageDialog(null, "Logowanie nie powiod³o siê.");	
					}
				}
				
				//zamkniecie polaczenia
				  
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ("Info log.: " + ex));
			 
				}
			
				
			}
			
		});
		
		JButton btnRegister = new JButton("Nie masz konta? Zarejestruj sie");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{ 
				//Rejestrowanie rej = new Rejestrowanie();
				Rejestrowanie.main(args);
				frame.dispose();
			}
		});
		btnRegister.setBounds(70, 338, 270, 23);
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
		
		JLabel lblZalogujSiBy = new JLabel("WprowadŸ imie i nazwisko oraz has³o.");
		lblZalogujSiBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZalogujSiBy.setBounds(94, 59, 283, 23);
		panel.add(lblZalogujSiBy);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(69, 93, 276, 241);
		editorPane.setEnabled(false);
		panel.add(editorPane); 
	}

	public static void runClassLogowanie(String imieUzytkownika, String nazwiskoUzytkownika) 
	{ 
		Logowanie w = new Logowanie(imieUzytkownika, nazwiskoUzytkownika );
		w.frame.setVisible(true); 
	}
}
