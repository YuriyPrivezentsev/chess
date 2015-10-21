package com.base.board;

import com.base.figures.Figure;

/**
 * The common logic implementation for the result board.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public abstract class AbstractFigureBoard extends AbstractBoard implements FigureBoard {
    protected static final String OVERLAPPING_POSITIONS_MESSAGE = "Trying to place a %s  on top of the %s at position %s";
    protected static final String WRONG_FIGURE_MESSAGE = "Trying to delete the wrong figure at position %s deleting %s  were was %s";
    protected static final String POSITION_SEPARATOR = ",";

    public AbstractFigureBoard(int width, int height) {
        super(width, height);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addFigure(Figure figure) {
        Figure previousFigure = tryToInsert(figure);
        if(previousFigure != null){
            throw new IllegalStateException(String.format(AbstractFigureBoard.OVERLAPPING_POSITIONS_MESSAGE,
                    figure.getName(),
                    previousFigure.getName(),
                    figure.getPosition()));
        }
    }

    protected abstract Figure tryToInsert(Figure figure);

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFigure(Figure deleteFigure) {
        Figure removed = tryToRemove(deleteFigure);
        if (!deleteFigure.equals(removed)) {
            throw new IllegalArgumentException(String.format(AbstractFigureBoard.WRONG_FIGURE_MESSAGE,
                    deleteFigure.getPosition(),
                    deleteFigure.getName(),
                    removed.getName()));
        }
    }

    protected abstract Figure tryToRemove(Figure figure);

}
