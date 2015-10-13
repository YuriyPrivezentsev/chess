package com.base;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * Unit test for @link{Position}
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class PositionTest {
    @Test
    public void testCompareLess() {
        Position position = new Position(1, 1);

        assertTrue(position.compareTo(new Position(0, 1)) > 0);
        assertTrue(position.compareTo(new Position(1, 0)) > 0);
        assertTrue(position.compareTo(new Position(0, 0)) > 0);
    }

    @Test
    public void testCompareSymmetrical() {
        Position position = new Position(1, 2);
        assertTrue(position.compareTo(new Position(2, 1)) < 0);
    }


}
