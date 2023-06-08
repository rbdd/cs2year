package Q3;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

class MyDocument extends PlainDocument {
    
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str.matches("\\d+"))
            if (offset < 3)
                super.insertString(offset, str, attr);
    }
}