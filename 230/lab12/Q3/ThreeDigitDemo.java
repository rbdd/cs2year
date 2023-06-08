package Q3;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class ThreeDigitDemo extends JFrame {
  JTextField textField;
  MyDocument  document;
  public ThreeDigitDemo() {
    textField = new JTextField(4);
    document = new MyDocument();
    textField.setDocument(document);
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter 3 letters: "));
    panel.add(textField);
    getContentPane().add(panel, BorderLayout.NORTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(200, 200);
    setVisible(true);
  }


public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { new ThreeDigitDemo();}
    });
  }
}