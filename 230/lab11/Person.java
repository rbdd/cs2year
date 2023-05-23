import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import javafx.scene.shape.Path;

import java.util.*;

abstract class Person {
  protected String name;
  protected Person parent = null;

  public Person(String name) {
    this.name = name;
  }

  public Person getParent() {
    return parent;
  }

  public void setParent(Person parent) {
    this.parent = parent;
  }

  public String toString() {
    return name;
  }

  public Person[] getPath() {
    return getPathToRoot(this, 0);
  }

  protected Person[] getPathToRoot(Person aNode, int depth) {
    Person[] retNodes;
    if (aNode == null) {
      if (depth == 0)
        return null;
      else
        retNodes = new Person[depth];
    } else {
      depth++;
      retNodes = getPathToRoot(aNode.getParent(), depth);
      retNodes[retNodes.length - depth] = aNode;
    }
    return retNodes;
  }
}

class Member extends Person {
  public Member(String name) {
    super(name);
  }
}

class ProjectLeader extends Person {
  private ArrayList<Person> list = new ArrayList<Person>();
  private String projectName;

  public ProjectLeader(String projectName, String name) {
    super(name);
    this.projectName = projectName;
  }

  public void add(Person p) {
    p.setParent(this);
    list.add(p);
  }

  public void remove(Person p) {
    p.setParent(null);
    list.remove(p);
  }

  public Person get(int index) {
    return list.get(index);
  }

  public int getSize() {
    return list.size();
  }

  public int indexOf(Person p) {
    return list.indexOf(p);
  }
}

class ProjectLeaderTreeDemo extends JFrame {
  JButton addButton, removeButton;
  JTextField nameTextField;
  JLabel resultLabel;
  JTree tree;
  PersonTreeModel treeModel;
  ProjectLeader root;

  public ProjectLeaderTreeDemo() {
    root = new ProjectLeader("P1", "Dick");
    ProjectLeader t2 = new ProjectLeader("P2", "David");
    t2.add(new Member("Mary"));
    root.add(new Member("Emma"));
    ProjectLeader t3 = new ProjectLeader("P3", "Paul");
    t3.add(new Member("Happy"));
    t3.add(new Member("Anna"));
    t2.add(t3);
    root.add(t2);
    root.add(new Member("Rock"));
    treeModel = new PersonTreeModel(root);
    tree = new JTree(treeModel);
    addButton = new JButton("Add");
    removeButton = new JButton("Remove");
    nameTextField = new JTextField("Amy", 20);
    resultLabel = new JLabel("-");
    addButton.addActionListener(new AddListener());
    removeButton.addActionListener(new RemoveListener());

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(nameTextField);
    buttonsPanel.add(addButton);
    buttonsPanel.add(removeButton);
    buttonsPanel.add(resultLabel);
    getContentPane().add(buttonsPanel, BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 400);
    setVisible(true);
  }

  class RemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (tree.getLastSelectedPathComponent() == null) {
        
      }
    }
  }

  class AddListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Object o = tree.getLastSelectedPathComponent();
        if (o == null) resultLabel.setText("ERROR: No selection!");
        else if (!(o instanceof ProjectLeader)) resultLabel.setText("ERROR: Not a project leader!");
        else {
            ProjectLeader pm = (ProjectLeader) o;
            Member p = new Member(nameTextField.getText());
            treeModel.insertNodeInto(p, pm, pm.getSize());
            tree.expandPath(null);
            resultLabel.setText("A new member has been added.");
        }
    }
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new ProjectLeaderTreeDemo();
      }
    });
  }
}