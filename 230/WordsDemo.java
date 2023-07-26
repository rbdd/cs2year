import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class WordsDemo extends JFrame {
  JComboBox<String> lengthComboBox;
  DefaultComboBoxModel<String> comboBoxModel;

  JList<String> lengthList;
  DefaultListModel<String> lengthListModel;

  JList<String> wordsList;
  DefaultListModel<String> listModel;
  String[][] words;

  public WordsDemo() {
    lengthListModel = new DefaultListModel<String>();
    String[] numbers = { "3", "4", "5", "6", "7"};
    for (String value: numbers) lengthListModel.addElement(value);
    lengthList = new JList<String>(lengthListModel);
    comboBoxModel = new DefaultComboBoxModel<String>(new String[] { "3", "4", "5", "6", "7"});
    lengthComboBox = new JComboBox<String>(comboBoxModel);
	words = new String[][]{{"cat","jar","ice","bed","nap","oat"},
	{"baby","fact","each","hack","jail","safe","tack"},
	{"eagle","rabit","yacht"},
	{"eagers","fabric","gabbed","habits"},{"cabbage","icefall","vaccine"}};
	listModel = new DefaultListModel<String>();
	for (String value : words[0])
	  listModel.addElement(value);
	wordsList = new JList<String>(listModel);
    lengthComboBox.addActionListener(new MyListener());

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(lengthComboBox);
    buttonsPanel.add(wordsList);
    getContentPane().add(buttonsPanel, BorderLayout.NORTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 200);
    setVisible(true);
  }
  class MyListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        int i = lengthComboBox.getSelectedIndex();
        listModel.clear();
        for (String value : words[i])
            listModel.addElement(value);
        wordsList = new JList<String>(listModel);
    }
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { new WordsDemo();}
    });
  }
}