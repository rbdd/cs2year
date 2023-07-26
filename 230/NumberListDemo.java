import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class NumberListDemo extends JFrame  {
  private JList<String> numberList;
  DefaultListModel<String> listModel;
  JTextField numberTextField;
  JButton addButton, removeButton;

  public NumberListDemo() {
    listModel = new DefaultListModel<String>();
    listModel.addElement("One");
    listModel.addElement("Two");
    numberList = new JList<String>(listModel);
    JPanel input_panel = new JPanel();
    input_panel.add(new JLabel("Enter:"));
    numberTextField = new JTextField("Three");
    addButton = new JButton("Add");
    removeButton = new JButton("Remove");
    addButton.addActionListener(new AddListener());
    removeButton.addActionListener(new RemoveListener());
    input_panel.add(numberTextField);
    input_panel.add(addButton);
    input_panel.add(removeButton);
    getContentPane().add(input_panel, BorderLayout.SOUTH);
    getContentPane().add(new JScrollPane(numberList), BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setVisible(true);
  }
    class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String text = numberTextField.getText();
            listModel.addElement(text);
        }
    }
    class RemoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int index = numberList.getSelectedIndex();
            if (index > -1) listModel.removeElementAt(index);
        }
    }
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { new NumberListDemo();}
    });
  }
}
