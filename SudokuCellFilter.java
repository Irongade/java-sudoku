
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * A Document filter for the SudokuCell (JTextfield)
 *  @author 
 *  @version 
 */
public class SudokuCellFilter extends DocumentFilter {
   public static final int TEXT_LENGTH_LIMIT = 1;

    /**
     * This method is called before a string is inserted in the textfield
     */
   @Override
   public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
           throws BadLocationException {
            if (fb.getDocument().getLength() + string.length() <= TEXT_LENGTH_LIMIT && (string.matches("[0-9]") || string.equals("-"))) {
                super.insertString(fb, offset, string, attr);
            } else {
            }
   }

   /**
     * This method is called before a string is replaced in the textfield
     */
   @Override
   public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
           throws BadLocationException {
       int documentLength = fb.getDocument().getLength();
        if (documentLength - length + text.length() <= TEXT_LENGTH_LIMIT && (text.matches("[0-9]") || text.equals("-"))) {
            super.replace(fb, offset, length, text, attrs);
        }
        else  {
        }
   }

   /**
     * This method is called before a string is removed in the textfield
     */
   @Override
   public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
       super.remove(fb, offset, length);
   }
}
