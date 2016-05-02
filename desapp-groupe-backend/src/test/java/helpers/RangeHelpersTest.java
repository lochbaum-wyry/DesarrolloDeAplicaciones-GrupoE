package helpers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by daniel on 02/05/16.
 */
public class RangeHelpersTest {
    @Test
    public void test_sectionsOverlap()
    {
        assertTrue(RangeHelpers.rangesOverlap(0,5,0,5));

        assertTrue(RangeHelpers.rangesOverlap(1,5,0,5));
        assertTrue(RangeHelpers.rangesOverlap(0,4,0,5));
        assertTrue(RangeHelpers.rangesOverlap(0,4,0,5));
        assertTrue(RangeHelpers.rangesOverlap(0,5,3,4));

        assertFalse(RangeHelpers.rangesOverlap(1,2,2,3));
        assertFalse(RangeHelpers.rangesOverlap(1,2,3,4));


    }



}
