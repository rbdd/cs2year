import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.table.*;
import java.io.*;

class PhoneManufacturer {
   private String name;
   private int establishmentYear;
   public PhoneManufacturer(String name, int establishmentYear) {
    this.name = name;
    this.establishmentYear = establishmentYear;
   }
   public String getName() {return name;}
   public int getEstablishmentYear() {return establishmentYear;}
   public String toString() { return String.format("%s(%d)", name, establishmentYear);}
}

class PhoneManufacturerTableDemo extends JFrame  {
  JTable table;
  ManufacturerTableModel myModel;   

  public PhoneManufacturerTableDemo() {
    myModel = new ManufacturerTableModel();
    table = new JTable(myModel); 

    JPanel input_panel = new JPanel();  

    getContentPane().add(input_panel, BorderLayout.SOUTH);
     JScrollPane scrollPane = new JScrollPane(table);
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
  class ManufacturerTableModel extends AbstractTableModel{
    private ArrayList<Object[]> oa = new ArrayList<Object[]>(); 
    public int getColumnCount(){return 2;}
    public int getRowCount(){return oa.size();}
    public String getColumnName(int column){if (column == 0) return "name"; return "establishmentYear";}
    public Object getValueAt(int rowIndex, int columnIndex){return (oa.get(rowIndex))[columnIndex];}
    public void addRow(PhoneManufacturer company){Object[] a = {company.getName(), company.getEstablishmentYear()}; oa.add(a); fireTableRowsInserted(oa.size(), oa.size());}
    public void removeRow(int index){oa.remove(index); fireTableRowsDeleted(index, index);}

}
  //ADD ManufacturerTableModel class
  //Make appropriate changes to the code for testing your new table model.
  //One way to check this would be to make sure it displays PhoneManufacturer contents on the JTable view.
  //Fix any compile time errors in this file in the process.

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new PhoneManufacturerTableDemo();
      }
    });
  }
}