
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.util.*;
import java.io.*;

/**
 * This is the GUI of the SudokuGame and handles all the presentational logic
 * @author 
 * @version 
 */
public class GUI extends JFrame implements Observer, ActionListener {

    private Sudoku thegame;//this is the game model
    private Stack<Assign> gameStack = null; // stack of game moves

    private SudokuCell[][] gameCells = null; // keeps record of all the sudoku cells

    private JPanel btnPanel = null; // this panel houses the buttons 
    private JPanel cellContainerPanel = null; // this panel houses the cell panel and is attached to the Jframe
    private JPanel cellPanel = null; // this panel houses the sudoku cells (jtextfield components)

    private SudokuButton    undoBtn = null;
    private SudokuButton    saveBtn = null;
    private SudokuButton    clearBtn = null;
    private SudokuButton    loadBtn = null;
    private SudokuButton    quitBtn  = null;

    private Boolean isGameCompleted = null;

    private static final int FRAME_HEIGHT = 400;
    private static final int FRAME_WIDTH = 700;

    private static final String GAMEFILE = "sudoku.txt";
    public static final String EMPTY_STATE = "-";

    public GUI() {
        gameStack = new Stack<Assign>();
        thegame = new Sudoku();
        isGameCompleted = false;
        
        int gameSize = thegame.getGameSize();
        Slot[][] slots = thegame.getMoves();
        gameCells = new SudokuCell[gameSize][gameSize];

        cellContainerPanel = new JPanel();
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(4, 2));
        cellPanel = new JPanel(new GridLayout(gameSize, gameSize));

        DocumentFilter documentFilterLimit = new SudokuCellFilter();

        // go through the rows and columns, then create the sudoku cells
        // then add filters to the cells and add the current jframe as an observer to all the slots 
        for (int r = 0; r<gameSize; r++) {
            for (int c = 0; c <gameSize; c++) {
                SudokuCell cell = new SudokuCell(1, r, c, this);
                
                ((AbstractDocument) cell.getDocument()).setDocumentFilter(documentFilterLimit);

                cell.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                        KeyEvent enterKeyEvent = new KeyEvent(
                            cell,
                            KeyEvent.KEY_PRESSED,
                            System.currentTimeMillis(),
                            0,
                            KeyEvent.VK_ENTER,
                            KeyEvent.CHAR_UNDEFINED
                        );
                        if (!cell.getCellText().contains("-") ) {
                            cell.dispatchEvent(enterKeyEvent);
                        }
                    }
                    
                });
                
                gameCells[r][c] = cell;
                cellPanel.add(cell);

                Slot slot = slots[r][c];
                slot.addObserver(this);

                String state = slot.getState();
                Boolean fillable = slot.getFillable();
                cell.setCellText(state);

                if(!fillable) {
                    cell.setFillable(fillable);
                    cell.setTextColor(Color.BLACK);
                }
            }
        }


        // Create the buttons.
        saveBtn = new SudokuButton("Save Game");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                saveGame();
            }
        });
        
        clearBtn = new SudokuButton("Clear Game");
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                clearGame();
            }
        });

        undoBtn = new SudokuButton("Undo Move");
        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                undoMove();
            }
        });

        quitBtn = new SudokuButton("Quit Game");
        quitBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent action) {
            quitGame();
        }
        });
        
        loadBtn = new SudokuButton("Load Game");
        loadBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent action) {
            loadGame();
        }
        });
        
        // add all panels to the btnPanel and cellContainerPanels
        // then add those to the window.
        btnPanel.add(loadBtn);
        btnPanel.add(saveBtn);
        btnPanel.add(undoBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(quitBtn);

        setLayout(new BorderLayout());
        cellContainerPanel.add(cellPanel);
        add(btnPanel, BorderLayout.NORTH);
        add(cellContainerPanel, BorderLayout.CENTER);

        // add mouselistener to make the game lose focus after inputing a value in the textfield.
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                requestFocusInWindow();
            }
        });

        setFocusable( true );
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    /**
     * saveGame 
     * This saves the current game to a file
     */
    public void saveGame() {
        try {
            PrintStream gameFileStream = new PrintStream(new File(GAMEFILE));
            for (Assign move : gameStack) {
                gameFileStream.println(move.convertAssignToString());
            }
            gameFileStream.close();
            JOptionPane.showMessageDialog(this, "Game saved successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);

        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Sorry!", JOptionPane.ERROR_MESSAGE);
            throw new SudokuException("Game file could not be created");
        }
    }
    

    /**
     * undoMove 
     * This undos the last move played by the player and updates the state accordingly
     */
    public void undoMove() {
        if (gameStack.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no more moves to undo!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Assign lastMove = gameStack.pop();
        thegame.makeMove(lastMove.getRow(), lastMove.getCol(), EMPTY_STATE);
    }

  /**
   * loadGame
   * Loads a saved game from file and updates the game state accordingly
   */
    public void loadGame() {
        try {
            Scanner gameFileScnr = new Scanner(new File(GAMEFILE));
            clearGame();
            while(gameFileScnr.hasNext()) {
                String gameMoveLine = gameFileScnr.nextLine();
                System.out.println(gameMoveLine);

                String[] gameMove = gameMoveLine.split(" ");

                String row = gameMove[0];
                String col = gameMove[1];
                String number = gameMove[2];

                System.out.println(row);
                System.out.println(col);
                System.out.println(number);

                thegame.makeMove(row, col, number);
                Assign savedMove = new Assign(thegame, row, col, number);
                gameStack.add(savedMove);
            }
            gameFileScnr.close();

            JOptionPane.showMessageDialog(this, "Game loaded successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);

        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Game not loaded!", "Sorry!", JOptionPane.ERROR_MESSAGE);
            // throw new SudokuException("Game file can't be created");
        }
    }

  /**
   * clearGame
   * Clears the current game state and undos the stack.
   */
    public void clearGame() {
        gameStack.clear();
        thegame.clear();
    }

    /**
     * Disposes of the current frame and terminates the Sudoku game.
     */
    private void quitGame() {
        gameStack.clear();
        dispose();
        System.exit(0);
    }

    /**
     * This method is called when an action event is dispatched
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof SudokuCell) {
            SudokuCell textField = (SudokuCell) e.getSource();

            int row = textField.getRow();
            int col = textField.getColumn();
            String number = textField.getCellText();

            // make move
            thegame.makeMove(Integer.toString(row), Integer.toString(col), number);
            // then add to stack
            gameStack.add(new Assign(thegame, Integer.toString(row), Integer.toString(col), number));

            if (thegame.checkWin()) {
                if (!isGameCompleted) {
                    isGameCompleted = true;
                    JOptionPane.showMessageDialog(this, "Game completed successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                isGameCompleted = false;
            }
        }
    }

    /**
     * This updates the GUI with respect to the Observable
     *
     * @param obv 
     * @param arguments 
     */
    @Override
    public void update(Observable obv, Object arguments) {
        try {
            Slot changedCell = (Slot) arguments;

            int row = changedCell.getRow();
            int col  = changedCell.getCol();
            String state = changedCell.getState();

            SudokuCell guiGameCell = gameCells[row][col];

            if (guiGameCell.isFillable()) {
                guiGameCell.setText(state);
            }

        } catch (SudokuException err) {
            System.out.println("An error occured");
        }
    }
    

     /**
     * The main method within the Java application. 
     * It's the core method of the program and calls all others.
     */
    public static void main(String args[]) {
        GUI thisGUI = new GUI();
    }
}
