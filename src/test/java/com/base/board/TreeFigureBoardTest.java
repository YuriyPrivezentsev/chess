package com.base.board;

/**
 * Unit test for @link{TreeFigureBoard}
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class TreeFigureBoardTest extends FigureBoardTest<TreeFigureBoard> {
    @Override
    protected TreeFigureBoard getFigureBoard(int width, int height) {
        return new TreeFigureBoard(width, height);
    }
}
