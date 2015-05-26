package Dziennik; 

import java.awt.BorderLayout; 
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

public class Obecnosci {

  public static void main(String[] a) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    String[] columnTitles = { "Imie", "Nazwisko", "Wybierz..", "", "" };
    Object[][] dataEntries = { { "Saravan", "Pantham", new Integer(50), "Wybierz..", new Boolean(false) },
        { "Imie1", "", "Nazwisko1", "Wybierz..", new Boolean(true) },
        { "Imie2", "", "Nazwisko2" , "Wybierz..", new Boolean(false) },
        { "Imie3", "", "Nazwisko3", "Wybierz..", new Boolean(true) }, };
    TableModel model = new EditableTableModel(columnTitles, dataEntries);
    JTable table = new JTable(model);
    table.setFont(new Font("Tahoma", Font.BOLD, 11));
    table.createDefaultColumnsFromModel();

    String[] statusObecnosci = { "Obecny", "Nieobecny", "Spozniony", "Usprawiedliwiony" };
    JComboBox comboBox = new JComboBox(statusObecnosci);
    table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
     
    
    JScrollPane scrollPane = new JScrollPane(table);
    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
    	groupLayout.createParallelGroup(Alignment.LEADING)
    		.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
    );
    groupLayout.setVerticalGroup(
    	groupLayout.createParallelGroup(Alignment.LEADING)
    		.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
    );
    frame.getContentPane().setLayout(groupLayout);
      
    frame.setSize(300, 200);
    frame.setVisible(true);
    System.out.println("3333333333333");
    
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