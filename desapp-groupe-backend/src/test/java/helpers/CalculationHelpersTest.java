package helpers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by daniel on 02/05/16.
 */
public class CalculationHelpersTest {
    @Test
    public void test_sectionsOverlap()
    {
        assertTrue(CalculationHelpers.rangesOverlap(0,5,0,5));

        assertTrue(CalculationHelpers.rangesOverlap(1,5,0,5));
        assertTrue(CalculationHelpers.rangesOverlap(0,4,0,5));
        assertTrue(CalculationHelpers.rangesOverlap(0,4,0,5));
        assertTrue(CalculationHelpers.rangesOverlap(0,5,3,4));

        assertFalse(CalculationHelpers.rangesOverlap(1,2,2,3));
        assertFalse(CalculationHelpers.rangesOverlap(1,2,3,4));


    }



}
