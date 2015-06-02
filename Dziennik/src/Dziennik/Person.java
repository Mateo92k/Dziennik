package Dziennik;

import java.awt.List;

import javax.swing.table.AbstractTableModel;

public class Person extends AbstractTableModel {
	 
    private List<Person> persons = null;
    private final static Object[] columnNames = {"", "Imiê", "Nazwisko", "P³eæ",
        "Ulica","Nr. d/m", "Miasto", "Kod pocztowy", "Województwo",
        "Tel. kom", "Tel. dom", "Email"};
     
    private final static int HIDDEN_IDX = 0;
    private final static int NAME_IDX = 1;
    private final static int SURNAME_IDX = 2;
    private final static int GENDER_IDX = 3;
    private final static int STREET_IDX = 4;
    private final static int APARTMENT_IDX = 5;
    private final static int CITY_IDX = 6;
    private final static int ZIPCODE_IDX = 7;
    private final static int PROVINCE_IDX = 8;
    private final static int CELL_IDX = 9;
    private final static int PHONE_IDX = 10;
    private final static int EMAIL_IDX = 11;
 
    public Person() {}
     
    @Override
    public int getRowCount() {
        if(persons==null) return 0;
        return persons.size();
    }
 
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
     
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
 
        if(persons == null) return null;
        Person p = (Person) persons.get(rowIndex);
        switch (columnIndex) {
            case HIDDEN_IDX:
                return p.getId();
            case NAME_IDX:
                return p.getName();
            case SURNAME_IDX:
                return p.getSurname();
            case GENDER_IDX:
                return p.getGender().toString();
            case STREET_IDX:
                return p.getStreet();
            case APARTMENT_IDX:
                return p.getApartment();
            case CITY_IDX:
                return p.getCity();
            case ZIPCODE_IDX:
                return p.getZipCode();
            case PROVINCE_IDX:
                return p.getProvince().toString();
            case CELL_IDX:
                return p.getCellNumber();
            case PHONE_IDX:
                return p.getPhoneNumber();
            case EMAIL_IDX:
                return p.getEmail();
            default:
                return p;
        }
    }
 
    @Override
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }
 
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
     
    public void setModelData(List<Person> persons) {
       this.persons =  persons;
    }
    public Person getPerson(int position) {
        return persons.get(position);
    }
  
}
