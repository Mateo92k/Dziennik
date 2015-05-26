//Klasa która bêdzie list¹ obecnoœci

package Dziennik; 

import java.awt.BorderLayout;  
import java.util.List; 
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment; 
import java.awt.Font;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditableTable {
	
	private void createGUI()
	{ 

	    
	}
	
  public static void main(String[] a) {
    
    
    EditableTable etm = new EditableTable();
    etm.loadData("Michal Klich");
    //loadData("Michal Klich");
    //zaladowanie listy imion
     
  }

public static void loadData(String fullname) 
{
	List<String> list;
	try
	{
		list = Files.readAllLines(Paths.get("C:/dziennik/users/Michal Klich/studenci.txt" ), StandardCharsets.UTF_8);
		String[] personaliaUczniow = list.toArray(new String[list.size()]);
		int dl = personaliaUczniow.length;
		String[] imiona = new String[dl];
		String[] nazwiska = new String[dl];
		
		for(int i = 0;i<dl;i++)
		{
		String string = personaliaUczniow[i];
		String[] parts = string.split(" ");
		imiona[i] = parts[0];   
		nazwiska[i] = parts[1];  
		}
		
		
		System.out.println(imiona[3]);
		String[] columnTitles = { "Imie", "Nazwisko", "Status", "" }; 
	    Object[][] dataEntries = new Object[dl][4];
	    
	    String[] firstName = {"Imie1", "Imie2", "Imie3", "imie4"}; 
	    String[] secondName = {"s1", "s2", "s3", "s4"};
	     
	    
//	    	for(int i = 0 ; i<imiona.length; i++)  
//	    	{  
//	 		        dataEntries[i][0] = imiona[i]; 
//	 		        dataEntries[i][1] = nazwiska[i];
//	 		        dataEntries[i][2] = "Click to select";
//	 		        dataEntries[i][3] = new Boolean(false);  
//	    	}
	    
	    for(int i = 0 ; i<imiona.length ; i++)
	    {
	        dataEntries[i][0] = imiona[i]; 
	        dataEntries[i][1] = nazwiska[i];
	        dataEntries[i][2] = "Kliknij by wybraæ";
	        dataEntries[i][3] = new Boolean(false);
	    }
	    
	    
	    TableModel model = new EditableTableModel(columnTitles, dataEntries);
	    JTable table = new JTable(model);
	    table.setFont(new Font("Tahoma", Font.BOLD, 11));
	    table.createDefaultColumnsFromModel();

	    String[] statusObecnosci = { "Obecny", "Nieobecny", "Spozniony", "Usprawiedliwiony" };
	    JComboBox comboBox = new JComboBox(statusObecnosci);
	    table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));
	      
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		JScrollPane scrollPane = new JScrollPane(table);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE));
		groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE));
		
		frame.getContentPane().setLayout(groupLayout); 
		frame.setSize(400, 200);
		frame.setVisible(true); 
	} 
	
	catch (IOException e) 
	{ 
		System.out.println("Zaladowanie listy studentow i pzrekonwertowanie do tablicy nie powiodlo sie.");
	}
	 
	
	
}

 
}

class EditableTableModel extends AbstractTableModel {
  String[] columnTitles;

  Object[][] dataEntries;

  int rowCount;

  public EditableTableModel(String[] columnTitles, Object[][] dataEntries) {
    this.columnTitles = columnTitles;
    this.dataEntries = dataEntries;
  }

  public int getRowCount() {
    return dataEntries.length;
  }

  public int getColumnCount() {
    return columnTitles.length;
  }

  public Object getValueAt(int row, int column) {
    return dataEntries[row][column];
  }

  public String getColumnName(int column) {
    return columnTitles[column];
  }

  public Class getColumnClass(int column) {
    return getValueAt(0, column).getClass();
  }

  public boolean isCellEditable(int row, int column) {
    return true;
  }

  public void setValueAt(Object value, int row, int column) {
    dataEntries[row][column] = value;
  }
  
  
  
}