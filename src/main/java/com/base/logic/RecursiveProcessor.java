package com.base.logic;

import com.base.board.FigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import com.base.figures.Figure;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

/**
 * The board and figure traversal class which looks for solutions recursively placing figures on the boards.
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class RecursiveProcessor extends AbstractProcessor {

    public RecursiveProcessor(int width, int height, Deque<Figure> figures) {
        super(width, height, figures);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process() {
        long startTime = System.currentTimeMillis();
        if (isTrivialCase()){
            return;
        }

        FreeCellsBoard freeCellsBoard = boardFactory.getFreeCellsBoard();
        FigureBoard figureBoard = boardFactory.getFigureBoard(getFigureBoardType());
        placeFigure(freeCellsBoard, figureBoard, null);

        long time = System.currentTimeMillis() - startTime;
        getResultProcessor().addSummary(time);
    }

    private void placeFigure(FreeCellsBoard freeCellsBoard, FigureBoard figureBoard, Figure previousProcessedFigure) {
        if (figures.size() > freeCellsBoard.getFreeCellsCount()) {
            return;
        }

        Position freeCell;
        Figure figure = figures.poll();
        if (previousProcessedFigure == null
                || !figure.isSameType(previousProcessedFigure)) {
            freeCell = freeCellsBoard.getFirstFreeCell();
        } else {
            freeCell = freeCellsBoard.getNextFreeCell(previousProcessedFigure.getPosition());
        }

        while (freeCell != null) {
            figure.setPosition(freeCell);
            Collection<Position> figureCoverage = figure.placeOnBoard(figureBoard);
            if (!figureCoverage.isEmpty()) {
                figureBoard.addFigure(figure);
                Collection<Position> nonOverlappedCoverage = Collections.EMPTY_LIST;
                if (figures.isEmpty()) {
                    getResultProcessor().addResult(figureBoard);
                } else {
                    nonOverlappedCoverage = freeCellsBoard.occupyCells(figureCoverage);
                    placeFigure(freeCellsBoard, figureBoard, figure);
                }
                figureBoard.removeFigure(figure);
                freeCellsBoard.freeCells(nonOverlappedCoverage);
            }
            freeCell = freeCellsBoard.getNextFreeCell(freeCell);
        }
        figures.push(figure);
    }
}
