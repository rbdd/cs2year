import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.table.*;
import java.io.*;

class Country {
   private String name;
   private int numberOfStates;
   public Country(String name, int n) {
    this.name = name;
    numberOfStates = n;
   }
   public String getName() {return name;}
   public int getNumberOfStates() {return numberOfStates;}
   public String toString() { return String.format("%s(%d)", name, numberOfStates);}
}

class CountryTableDemo extends JFrame  {
  JTable table;
  CountryTableModel myModel;   

  public CountryTableDemo() {
    myModel = new CountryTableModel();
    table = new JTable(myModel); 

    JPanel input_panel = new JPanel();  

    getContentPane().add(input_panel, BorderLayout.SOUTH);
     JScrollPane scrollPane = new JScrollPane(table);
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

class CountryTableModel extends AbstractTableModel{
    private ArrayList<Country> countries;
    public CountryTableModel(){countries = new ArrayList<Country>();}
    public int getColumnCount(){return 2;}
    public int getRowCount(){return countries.size();}
    public String getColumnName(int column){if (column == 0) return "Name"; return "Number of States";}
    public Object getValueAt(int rowIndex, int columnIndex){if (columnIndex == 0) return countries.get(rowIndex).getName(); return countries.get(rowIndex).getNumberOfStates();}
    public void addRow(Country country){countries.add(country);}
    public void removeRow(int index){countries.remove(index);}
}
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new CountryTableDemo();
      }
    });
  }
}
