import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PressMeDemo extends JFrame  {
  JTextField textField1;
  JButton pressButton;
  String[] messages = new String[]{"YOU RANG!", "NOT NOW!", "MAYBE LATER!"};
  int count;
  public PressMeDemo() {
    textField1 = new JTextField(10);
    pressButton = new JButton("PRESS ME");
    pressButton.addActionListener( new MyListener());
    JPanel p = new JPanel();
    p.add(textField1);
    p.add(pressButton);
    getContentPane().add(p);
    setSize(180, 200);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public class MyListener implements ActionListener{
    private int i = 1;
    public void actionPerformed(ActionEvent e){
        if (i == 1){textField1.setText("YOU RANG!"); i++;}
        else if (i == 2){textField1.setText("NOT NOW!"); i++;}
        else if (i == 3){textField1.setText("MAYBE LATER!"); i-=2;}
    }
    }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { new PressMeDemo(); }
    });
  }
}