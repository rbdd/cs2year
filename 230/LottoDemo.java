import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class LottoDemo extends JFrame  {
  JTextField textField1;
  JButton pressButton;
  Random rand = new Random(30);
  public LottoDemo() {
    textField1 = new JTextField(10);
    pressButton = new JButton("RANDOM LOTTO NUMBERS");
    pressButton.addActionListener( new MyListener());
    JPanel p = new JPanel();
    p.add(textField1);
    p.add(pressButton);
    getContentPane().add(p);
    setSize(400, 200);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public class MyListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        Random rand = new Random(30);
        for (int i == 0; i < 6; i++){System.out.print(rand.nextInt(40) + " ");}
    }

  }
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { new LottoDemo(); }
    });
  }
}