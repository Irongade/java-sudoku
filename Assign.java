/**
 * Assign
 * This class handles the creation of all moves in the game
 * @author Lauren Scott
 * @version Student Sample Code
 */
public class Assign {
    private String col, row;//The row and column being assigned
    private String number; //the number played by the user
    private Sudoku game;//The game
    // Slot[][] moves;//2D Array to store the game's moves

    /**
     * Constructor for Assign class.
     * This gets the total number of moves and calls methods to determine the row that will be filled, and to set the state of the slot being assigned.
     * @param game - the game
     * @param col - the column the user has selected
     * @param player - a Boolean value that determines whether it is a player/computer move
     */
    public Assign(Sudoku game, String row, String col, String number){
        this.game = game;
        this.col = col;
        this.row = row;
        this.number = number;
        // assignMove(number);
    }
    
    /**
     * assignMove
     * This method assigns the move to the game
     * @param player a Boolean value to determine whether it is a computer/player move
     */
    // public void assignMove(String number) {
    //     moves[row][col].setState(number);
    // }

    /**
     * getRow
     * This method returns the current row value for this move. It allows another element of the program to access this move's current row.
     * @return the row value
     */
    public String getRow() {
        return row;
    }

    /**
     * getRow
     * This method returns the current column value for this move. It allows another element of the program to access this move's current column.
     * @return the row value
     */
    public String getCol() {
        return col;
    }

    /**
     * getRow
     * This method returns the current value for this move. It allows another element of the program to access this move's current value.
     * @return the row value
     */
    public String getNumber() {
        return number;
    }

  /**
   * String representation of the move to be saved
   * 
   * @return the String representation of the assign class
   */
    public String convertAssignToString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(row + " " + col + " " + number);
        return buffer.toString();
      }

}//End of class Assign
