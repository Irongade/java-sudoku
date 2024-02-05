import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * A Jtextfield class for Sudoku game interface
 * This class is a single input textfield in the JFrame.
 * @author 
 * @version 
 */
public class SudokuCell extends JTextField {
    int row;
    int col;

    /**
     * Constructor for SudokuCell class.
     * 
     * @param columns The number of columns in the cell.
     * @param row The row number of the cell.
     * @param col The column number of the cell.
     * @param action The action listener for the cell.
     */
    public SudokuCell(int columns, int row, int col, ActionListener action) {
        super();
        this.setColumns(columns);
        this.row = row;
        this.col = col;
        this.addActionListener(action);
        this.setBorder(new LineBorder(new Color(102, 102, 153), 1));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setBorder(BorderFactory.createCompoundBorder(
        this.getBorder(), 
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        this.setForeground(Color.BLUE);
    }

    /**
     * This method sets the textfield to either be editable or not
     * 
     * @param fillable The fillable status.
     */
    public void setFillable(Boolean fillable) {
        this.setEditable(fillable);
    }

    /**
     * This method sets the cell's text
     * 
     * @param text The text to set.
     */
    public void setCellText(String text) {
        this.setText(text);
    }

    /**
     * This method gets the cell's text
     */
    public String getCellText() {
        return this.getText();
    }

     /**
     * This method gets the cell's fillable status
     */
    public Boolean isFillable() {
        return this.isEditable();
    }

     /**
     * This method gets the cell's row number
     */
    public int getRow() {
        return row;
    }

     /**
     * This method gets the cell's column number
     */
    public int getColumn() {
        return col;
    }

     /**
     * This method sets the cell's background color
     * 
     * @param color The color to set.
     */
    public void setBackgroundColour(Color color) {
        this.setBackground(color);
    }

    /**
     * This method sets the cell's text color
     * 
     * @param color The color to set.
     */
    public void setTextColor(Color color) {
        this.setForeground(color);
    }
}
