package com.base.board;

/**
 * Unit test for @link{ArrayFigureBoard}
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class ArrayFigureBoardTest extends FigureBoardTest<ArrayFigureBoard> {
    @Override
    protected ArrayFigureBoard getFigureBoard(int width, int height) {
        return new ArrayFigureBoard(width,height);
    }
}
