/**
 * A run time exception for the Sudoku game.
 * @author
 * @version
 */
@SuppressWarnings("serial")
public class SudokuException extends RuntimeException {
	/**
	 * Constructs a new exception instance
	 * 
	 * @param msg the error message
	 */
	public SudokuException(String msg) {
		super(msg);
	}
}
