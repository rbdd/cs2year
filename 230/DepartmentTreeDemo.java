import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.*;

class DepartmentTreeDemo extends JFrame {
    JTextField nameTextField, indexTextField;
    JButton addButton, removeButton;
    JTree tree;
    DefaultTreeModel treeModel;
    DefaultMutableTreeNode root;

  public DepartmentTreeDemo() {
    root = new DefaultMutableTreeNode("Head");
    root.add(new DefaultMutableTreeNode("Sales"));
    root.add(new DefaultMutableTreeNode("Financial"));
    treeModel = new DefaultTreeModel(root);
    tree = new JTree(treeModel);

    nameTextField = new JTextField("Name", 20);
    indexTextField = new JTextField("1", 5);
    addButton = new JButton("Add");
    removeButton = new JButton("Remove");
    //addButton.addActionListener(new AddListener());
    //removeButton.addActionListener(new RemoveListener());
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(new JLabel("Enter:"));
    buttonsPanel.add(nameTextField);
    buttonsPanel.add(addButton);
    buttonsPanel.add(new JLabel("Remove by index:"));
    buttonsPanel.add(indexTextField);
    buttonsPanel.add(removeButton);
    getContentPane().add(buttonsPanel, BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 200);
    setVisible(true);
    }

  //complete inner classes here
  class AddListener implements ActionListener{
    public void actionPerformed(ActionEvent e){treeModel.insertNodeInto(new DefaultMutableTreeNode(nameTextField.getText()), root, root.getChildCount());}
  }   
  class RemoveListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        String s = indexTextField.getText();
        int i = Integer.parseInt(s);
        if (i >= 0 && i < root.getChildCount()) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) root.getChildAt(i);
            treeModel.removeNodeFromParent(childNode);
        }
    }
  }   


  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { new DepartmentTreeDemo();}
    });
  }
}