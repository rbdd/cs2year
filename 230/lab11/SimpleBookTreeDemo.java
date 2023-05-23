import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

class SimpleBookTreeDemo extends JFrame {
  JTextField titleTextField;
  JTree tree;
  DefaultTreeModel treeModel;
  DefaultMutableTreeNode root;
  JButton addButton, removeButton;

  public SimpleBookTreeDemo() {
    root = new DefaultMutableTreeNode("Books");
    String[] names = new String[] { "Aotearoa: The New Zealand Story", "The Noisy Book" };
    for (String c : names)
      root.add(new DefaultMutableTreeNode(c));
    treeModel = new DefaultTreeModel(root);
    tree = new JTree(treeModel);
    titleTextField = new JTextField("I Am Jellyfish", 8);
    JButton addButton = new JButton("Add");
    JButton removeButton = new JButton("Remove");
    addButton.addActionListener(new AddListener());
    removeButton.addActionListener(new RemoveListener());
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(new JLabel("Enter a title:"));
    buttonsPanel.add(titleTextField);
    buttonsPanel.add(addButton);
    buttonsPanel.add(removeButton);
    getContentPane().add(buttonsPanel, BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setVisible(true);
  }

  class AddListener implements ActionListener {
    public void actionPerformed(ActionEvent e) { // remove from root
        treeModel.insertNodeInto(new DefaultMutableTreeNode(titleTextField.getText()), root, root.getChildCount());
    }
  }

  class RemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode d = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (d != root) treeModel.removeNodeFromParent(d);
    }
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new SimpleBookTreeDemo();
      }
    });
  }
}