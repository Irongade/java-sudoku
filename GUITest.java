

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class GUITest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GUITest
{
    GUI thisGui = null;
    /**
     * Default constructor for test class GUITest
     */
    public GUITest()
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
        thisGui = new GUI();
    }
    
    @Test
    public void verifyConstructorFunctionality() {
        assertNotNull(thisGui);
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
