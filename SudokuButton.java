import javax.swing.*;

/**
 * A button class for Sudoku game user interface.
 * This class is a single button in the JFrame.
 *  @author 
 *  @version 
 */
public class SudokuButton extends JButton {
    
    /**
     * Constructor for creating a Single Sudokun button with a specified name.
     * 
     * @param name The button label.
     */
    public SudokuButton(String name) {
        super(name);
        super.setSize(BTN_WIDTH, BTN_HEIGHT);
    }
    
    private static final int BTN_WIDTH = 200;
    private static final int BTN_HEIGHT = 60;
}
