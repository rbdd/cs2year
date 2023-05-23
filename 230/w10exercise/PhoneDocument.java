import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
class PhoneDocument extends PlainDocument{
    @Override
    public void insertString(int offSet, String str, AttributeSet a) throws BadLocationException {
        String s = str;
        if (str.matches("[0-9]")){
            if (offSet==2 || offSet == 5) s = "-" + str;
            if (offSet<12) super.insertString(offSet, s, a);
        }
        else System.out.printf("%s not a digit at offset %d\n", str, offSet);
    }
}    