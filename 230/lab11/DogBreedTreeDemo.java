import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import javafx.scene.chart.NumberAxis.DefaultFormatter;


class DogBreedTreeDemo extends JFrame {
  JLabel resultLabel, valuLabel;
  JTree tree;
  DefaultTreeModel treeModel;
  DefaultMutableTreeNode root;
  JButton expandButton, collapseButton, checkButton;
  JTextField rowTextField;

  public DogBreedTreeDemo() {
    String[] group = new String[]{"Toy", "Sporting", "Herding", "Terrier"};
    String[][] breeds = new String[][]{ {"Pug", "Poddle", "Havanese"},
      {"Golden Retriever","Pointer","Vizsla","Barbet"}, {"Briard", "Belgian Sheepdog", "Australian Cattle Dog", "Canaan Dog"},
      {"West Highland White", "Cairn", "Cesky", "Kerry Blue"} };
	buildTree(group, breeds);
	tree = new JTree(treeModel);
	tree.addTreeSelectionListener(new SelectListener());
   	resultLabel = new JLabel("Select a node");
    JPanel buttonsPanel = new JPanel();
    JButton expandButton = new JButton("Expand");
    JButton collapseButton = new JButton("Collapse");
    JButton checkButton = new JButton("Check");
    expandButton.addActionListener(new ExpandListener());
    collapseButton.addActionListener(new CollapseListener());
    checkButton.addActionListener(new CheckListener());
    rowTextField = new JTextField("1", 5);
    buttonsPanel.add(new JLabel("Row index: "));
    buttonsPanel.add(rowTextField);
    buttonsPanel.add(expandButton);
    buttonsPanel.add(collapseButton);
    buttonsPanel.add(checkButton);
    buttonsPanel.add(resultLabel);
    getContentPane().add(buttonsPanel, BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setVisible(true);
  }
  public void buildTree(String[] groups, String[][] breeds){
    root = new DefaultMutableTreeNode("Dog Breeds");
    for (int index = 0; index < groups.length; index++){
      DefaultMutableTreeNode group = new DefaultMutableTreeNode(groups[index]);
      for (String breed:breeds[index]){
        DefaultMutableTreeNode b = new DefaultMutableTreeNode(breed);
        group.add(b);
      }
      root.add(group);
    }
    treeModel = new DefaultTreeModel(root);
  }
  public void buildTreeNodes(String[] groups, String[][] breeds) {

  }
  class ExpandListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String index = rowTextField.getText();
      tree.expandRow(Integer.parseInt(index));
    }
  }
  class CheckListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {

    }
  }
  class CollapseListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String index = rowTextField.getText();
      tree.collapseRow(Integer.parseInt(index));
    }
  }
  class SelectListener implements TreeSelectionListener {
    public void valueChanged(TreeSelectionEvent e) {
     DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
     valuLabel.setText(dmtn.getUserObject().toString());
     }
   }
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { new DogBreedTreeDemo();}
  	});
  }
}