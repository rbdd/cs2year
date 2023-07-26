import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.table.*;
import java.io.*;

class Sport {
	private String _sportName;
	private int _numberOfPlayers;
	public Sport(String name, int playerCount) {
	   _sportName = name;
	   _numberOfPlayers = playerCount;
	}
	public String getSportName() {
		return _sportName;
	}
	public int getNumberOfPlayers() {
		return _numberOfPlayers;
	}
	public String toString() {
		return String.format("%s--%d", _sportName, _numberOfPlayers);
	}
}

class SportTableDemo extends JFrame  {
  JTable table;
  SportTableModel myModel;

  public SportTableDemo() {
    myModel = new SportTableModel();
    table = new JTable(myModel);

    JPanel input_panel = new JPanel();

    getContentPane().add(input_panel, BorderLayout.SOUTH);
     JScrollPane scrollPane = new JScrollPane(table);
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  class SportTableModel extends AbstractTableModel{
    private ArrayList<Sport> s = new ArrayList<Sport>();
    public SportTableModel(){}
    public int getColumnCount(){return 2;}
    public int getRowCount(){return s.size();}
    public String getColumnName(int column){if (column == 0) return "Sport Name"; return "Number of Players";}
    public Object getValueAt(int rowIndex, int columnIndex){if (columnIndex == 0) return s.get(rowIndex).getSportName(); return s.get(rowIndex).getNumberOfPlayers();}
    public void addRow(Sport sport){s.add(sport); fireTableRowsInserted(s.size()-1, s.size()-1);;}
    public void removeRow(int index){s.remove(index); fireTableRowsDeleted(index, index);}
}
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new SportTableDemo();
      }
    });
  }
}