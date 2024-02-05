

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;


/**
 * The test class SudokuCellTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SudokuCellTest
{
    SudokuCell cell = null;
    
    SudokuCellFilter filter = null;
    /**
     * Default constructor for test class SudokuCellTest
     */
    public SudokuCellTest()
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
        cell = new SudokuCell(1, 0, 0, new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                
            }

        });
    }
    
    @Test
    public void verifyConstructorFunctionality() {
        assertNotNull(cell);
    }

    @Test
    public void verifySetCellTest() {
    
        cell.setCellText("5");
        
        String value = cell.getCellText();
        
        assertEquals("5", value);
    }
    
    @Test
    public void verifyCellRowTest() {
    
        int row = cell.getRow();
        
        assertEquals(0, row);
    }
    
    @Test
    public void verifyCellColumnTest() {
    
        int col = cell.getColumn();
        
        assertEquals(0, col);
    }
    
    @Test
    public void verifyCellFillableTest() {
    
        cell.setFillable(false);
        
        Boolean isFillable = cell.isFillable();
        
        assertEquals(false, isFillable);
    }
    
    @Test
     public void verifyCellFilterTest() {
         
         DocumentFilter filter = new SudokuCellFilter();
         
        ((AbstractDocument) cell.getDocument()).setDocumentFilter(filter);
    
        cell.setCellText("50");
        
        String value = cell.getCellText();
        
        // you cannot type strings based on the filter
        assertEquals("", value);
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
