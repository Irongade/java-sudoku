

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SudokuButtonTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SudokuButtonTest
{
    
    SudokuButton btn = null;
    /**
     * Default constructor for test class SudokuButtonTest
     */
    public SudokuButtonTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        btn = new SudokuButton("Button Test");
    }
    
    @Test
    public void verifyConstructorFunctionality() {
        assertNotNull(btn);
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
