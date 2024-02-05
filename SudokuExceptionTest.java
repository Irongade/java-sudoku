

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SudokuExceptionTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SudokuExceptionTest
{
    SudokuException err = null;
    /**
     * Default constructor for test class SudokuExceptionTest
     */
    public SudokuExceptionTest()
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
        err = new SudokuException("Error");
    }
    
    @Test
    public void verifyConstructorFunctionality() {
        assertNotNull(err);
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
