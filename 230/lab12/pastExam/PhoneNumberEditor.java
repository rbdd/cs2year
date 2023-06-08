import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class PhoneNumberEditor extends JPanel {

   JLabel label;
   JTextField text;
   static JComponent demo;

   public PhoneNumberEditor() {
      label = new JLabel("Write Java code");
      text = new JTextField(30);
      Document phoneDocument = new PhonePlainDocument();
      text.setDocument(phoneDocument);
      add(label);
      add(text);
   }

   private static void createAndShowGUI() {
      JFrame frame = new JFrame("TextFieldDemo");

      demo = new PhoneNumberEditor();

      frame.add(demo);
      frame.pack();
      frame.setVisible(true);
   }

   public static void main(String[ ] args) {
      createAndShowGUI();
   }
}
//Note: For marking purpose only, the answer preload extends MockPlainDocument
//in place of PlainDocument. Do not change that class declaration in the answer preload
//and simply copy your class body from here and paste inside the answer preload code.
class PhonePlainDocument extends PlainDocument{
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
        String s = str;
        if (str.matches("[0-9]")){
            if (offset == 2 || offset == 5) s = "-" + str;
            if (offset < 12)super.insertString(offset, s, attr);
        }
    }
}