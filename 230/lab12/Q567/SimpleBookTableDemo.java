package Q567;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.table.*;

class SimpleBookTableDemo extends JFrame  {
  JTable table;
  JButton addButton, removeButton, increaseButton;
  JTextField nameTextField, authorTextField, pagesTextField;
  DefaultTableModel tableModel;

  public SimpleBookTableDemo() {
    String[] columnNames = new String[]{"Book Name", "Author", "Number of pages"};
    tableModel = new DefaultTableModel(new Object[][] {
	    { "Aotearoa: The New Zealand Story", "Gavin Bishop", 64 }, { "The Noisy Book", "Soledad Bravi" , 116}, { "The Very Hungry Caterpillar", "Eric Carle", 6 }}, columnNames);
    table = new JTable(tableModel );
    nameTextField = new JTextField("I Am Jellyfish", 10);
	authorTextField = new JTextField("Ruth Paul", 10);
	pagesTextField = new JTextField("32");
    JPanel input_panel = new JPanel();
    addButton = new JButton("Add");
    removeButton = new JButton("Remove");
    increaseButton = new JButton("Increase");
    addButton.addActionListener(new AddListener());
    removeButton.addActionListener(new RemoveListener());
    increaseButton.addActionListener(new IncreaseListener());
    input_panel.add(new JLabel("Enter a name"));
    input_panel.add(nameTextField);
    input_panel.add(authorTextField);
    input_panel.add(pagesTextField);
    input_panel.add(addButton);
    input_panel.add(removeButton);
    input_panel.add(increaseButton);
    getContentPane().add(input_panel, BorderLayout.SOUTH);
    getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setVisible(true);
  }

  class AddListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        String name = nameTextField.getText();
        String author = authorTextField.getText();
        String pages = pagesTextField.getText();
        Object[] oa = {name, author, pages};
        tableModel.addRow(oa);
    }
    }
    class IncreaseListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++){
            int p= (int) tableModel.getValueAt(i, 2);
            p++;
            tableModel.setValueAt(p,i, 2);
        }
      }
  }
  class RemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        int index = table.getSelectedRow();
        if (index != -1) tableModel.removeRow(index);
    }
  }
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new SimpleBookTableDemo();
      }
    });
  }
}