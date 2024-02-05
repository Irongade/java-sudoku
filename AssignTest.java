

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class AssignTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AssignTest
{
    Assign move = null;
    Sudoku game = null;
    /**
     * Default constructor for test class AssignTest
     */
    public AssignTest()
    {
        game = new Sudoku();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        move  = new Assign(game, "0", "0", "3");
    }
    
    
    @Test
    public void verifyConstructorFunctionality() {
        assertNotNull(move);
    }

    @Test
    public void verifyAssignValueTest() {
        
        String value = move.getNumber();
        
        assertNotEquals("5", value);
        assertEquals("3", value);
    }
    
    @Test
    public void verifyCellRowTest() {
    
        String row = move.getRow();
        
        assertEquals("0", row);
    }
    
    @Test
    public void verifyCellColumnTest() {
    
        String col = move.getCol();
        
        assertEquals("0", col);
    }
    
    @Test
    public void verifyAssignMoveToStringTest() {
    
        String val = move.toFileString();
        
        assertEquals("0 0 3", val);
    }


    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
