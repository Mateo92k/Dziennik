 
package Dziennik;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import Dziennik.GuiMain.MyComboBoxRenderer;

public class MainFrame  {

	private String frameTitle = "E-Dziennik";
	
	public JMenuBar createJMenuBar() 
	{
		JMenuBar mainMenuBar;
		JMenu menuPlik, menuWidok, menuNowy, menuPrzejdz, menuNarzedzia, menuPomoc;
		JMenuItem NowyMenuItem, OtworzBazeMenuItem, ZapiszMenuItem, Odswie¿MenuItem, DrukujMenuItem, WylogujMenuItem, WyjdzMenuItem ;
		
		mainMenuBar = new JMenuBar();
		menuPlik = new JMenu("Plik");
		mainMenuBar.add(menuPlik);
		
		menuNowy = new JMenu("Nowy");
		menuPlik.add(menuNowy);
		
		NowyMenuItem = new JMenuItem("Utwórz plik danych");
		menuNowy.add(NowyMenuItem);
		
		ZapiszMenuItem = new JMenuItem("Zapisz");
		menuPlik.add(ZapiszMenuItem);
		menuPlik.addSeparator();

		Odswie¿MenuItem = new JMenuItem("Odswie¿");
		menuPlik.add(Odswie¿MenuItem);
		menuPlik.addSeparator();

		DrukujMenuItem = new JMenuItem("Drukuj..");
		menuPlik.add(DrukujMenuItem);
		menuPlik.addSeparator();

		WylogujMenuItem = new JMenuItem("Wyloguj");
		menuPlik.add(WylogujMenuItem);
		
		WyjdzMenuItem = new JMenuItem("Zapisz i WyjdŸ");
		menuPlik.add(WyjdzMenuItem);
		
		menuNarzedzia = new JMenu("Narzedzia");
		mainMenuBar.add(menuNarzedzia);
		
		menuWidok = new JMenu("Widok");
		mainMenuBar.add(menuWidok);
		
		menuPomoc = new JMenu("Pomoc");
		mainMenuBar.add(menuPomoc);
		return mainMenuBar;
	} 
	
	private static void createGUI() {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		// Create and set up the window.
		JFrame frame = new JFrame("Dziennik Elektroniczny");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame mainframe = new MainFrame();
		frame.setJMenuBar(mainframe.createJMenuBar());
		frame.setSize(1362, 724);
		wysrodkujOkno(frame);
		//createMasterHead(frame);
		frame.setVisible(true);
		
		JPanel masterHead = new JPanel();
		masterHead.setLayout(new BoxLayout(masterHead, BoxLayout.X_AXIS));
		masterHead.setBackground(Color.WHITE);
		
		// Tworzenie Combox w Panelu masterHead
		String[] options = {" 6 A ", " 4 B " ,  " 1 A", " 1 B", " 2 A"};
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
    	frame.add(masterHead,BorderLayout.EAST); 
		
    	
	}// end of creat GUI
	
 


	private static void createMasterHead(JFrame frame) {
		// Utworzenie Panelu 

	}



	public static final void main(String[] args) throws InterruptedException {
    	
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				createGUI();
			}
		});
	}
	private static void wysrodkujOkno(JFrame frame) {
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		 int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		 int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		 frame.setLocation(x, y);	
		
	}
	
	String[] options = {" 6 A ", " 4 B " ,  " 1 A", " 1 B", " 2 A"};
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
}
