package com.base;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * Unit test for @link{Position}
 *
 * @author Yuriy Privezentsev
 * @since 10/13/2015
 */
public class PositionTest {
    @Mock
    private Board board;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(board.getHeight()).thenReturn(2);
    }
    @Test
    public void testCompareLess() {
        Position position = new Position(1, 1, board);

        assertTrue(position.compareTo(new Position(0, 1, board)) > 0);
        assertTrue(position.compareTo(new Position(1, 0, board)) > 0);
        assertTrue(position.compareTo(new Position(0, 0, board)) > 0);
    }

    @Test
    public void testCompareSymmetrical() {
        Position position = new Position(1, 2, board);
        assertTrue(position.compareTo(new Position(2, 1, board)) < 0);
    }


}
