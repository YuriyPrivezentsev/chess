package com.base.logic;

import com.base.board.BoardFactory;
import com.base.board.FigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import com.base.figures.Figure;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

/**
 * The board and figure traversal class which looks for solutions recursively placing figures iteratively. However,
 * we still need to save some status in the stack-like collection, thus it is call semi-recursive.
 *
 * @author Yuriy Privezentsev
 * @since 10/20/2015
 */
public class SemiRecursiveProcessor extends AbstractProcessor {
    private Deque<Figure> processedFigures;
    private Deque<Collection<Position>> processedPositions;

    public SemiRecursiveProcessor(int width, int height, Deque<Figure> figures) {
        super(width, height, figures);
        processedFigures = new ArrayDeque<>(figures.size());
        processedPositions = new ArrayDeque<>(figures.size());
    }

    @Override
    public void process() {
        long startTime = System.currentTimeMillis();
        if (isTrivialCase()) {
            return;
        }

        FigureBoard figureBoard = boardFactory.getFigureBoard(BoardFactory.FigureBoardType.TREE);
        FreeCellsBoard freeCellsBoard = boardFactory.getFreeCellsBoard();
        CalculationState state = new CalculationState(figures.pop(), freeCellsBoard.getFirstFreeCell());

        while (state.getFreeCell() != null) {
            if (figures.isEmpty()) {
                state = processLastFigure(state, figureBoard, freeCellsBoard);
            } else {
                state = processNextState(state, figureBoard, freeCellsBoard);
            }
        }

        long time = System.currentTimeMillis() - startTime;
        getResultProcessor().addSummary(time);
    }

    private CalculationState processNextState(final CalculationState state, FigureBoard figureBoard, FreeCellsBoard freeCellsBoard) {
        state.getFigure().setPosition(state.getFreeCell());
        Collection<Position> coverage = state.getFigure().placeOnBoard(figureBoard);
        CalculationState newState;
        if (!coverage.isEmpty()) {
            newState = placeFigure(state, coverage, figureBoard, freeCellsBoard);
        } else {
            newState = state.getNextState(freeCellsBoard);
        }
        return getNextValidState(newState, figureBoard, freeCellsBoard);
    }

    private CalculationState placeFigure(final CalculationState state, Collection<Position> coverage, FigureBoard figureBoard, FreeCellsBoard freeCellsBoard) {
        Collection<Position> actualCoverage = freeCellsBoard.occupyCells(coverage);
        CalculationState newState;
        if (figures.size() <= freeCellsBoard.getFreeCellsCount()) {
            figureBoard.addFigure(state.getFigure());
            processedPositions.push(actualCoverage);
            processedFigures.push(state.getFigure());
            Figure figure = figures.pop();
            newState = new CalculationState(figure, getNextFreeCell(figure, freeCellsBoard));
        } else {
            freeCellsBoard.freeCells(actualCoverage);
            newState = state.getNextState(freeCellsBoard);
        }
        return newState;
    }

    private CalculationState getNextValidState(final CalculationState state, FigureBoard figureBoard, FreeCellsBoard freeCellsBoard) {
        Position freeCell = state.getFreeCell();
        Figure figure = state.getFigure();
        while (freeCell == null && !processedFigures.isEmpty()) {
            figure = popFigure(figure, figureBoard);
            freeCell = popPosition(figure, freeCellsBoard);
        }
        return new CalculationState(figure, freeCell);
    }

    private CalculationState processLastFigure(final CalculationState state, FigureBoard figureBoard, FreeCellsBoard freeCellsBoard) {
        placeLastFigure(state, figureBoard, freeCellsBoard);
        CalculationState newState;
        if (!processedFigures.isEmpty()) {
            Figure figure = popFigure(state.getFigure(), figureBoard);
            newState = new CalculationState(figure, popPosition(figure, freeCellsBoard));
        } else {
            newState = new CalculationState(state.getFigure(), null);
        }
        return newState;
    }

    private Position getNextFreeCell(Figure figure, FreeCellsBoard freeCellsBoard) {
        Position freeCell;
        if (figure.isSameType(processedFigures.peek())) {
            freeCell = freeCellsBoard.getNextFreeCell(processedFigures.peek());
        } else {
            freeCell = freeCellsBoard.getFirstFreeCell();
        }
        return freeCell;
    }

    private Position popPosition(Figure figure, FreeCellsBoard freeCellsBoard) {
        Position freeCell;
        freeCellsBoard.freeCells(processedPositions.pop());
        freeCell = freeCellsBoard.getNextFreeCell(figure);
        return freeCell;
    }

    private Figure popFigure(Figure figure, FigureBoard figureBoard) {
        figures.push(figure);
        figure = processedFigures.pop();
        figureBoard.removeFigure(figure);
        return figure;
    }

    private void placeLastFigure(final CalculationState state, FigureBoard figureBoard, FreeCellsBoard freeCellsBoard) {
        Position freeCell = state.getFreeCell();
        Figure figure = state.getFigure();
        while (freeCell != null) {
            figure.setPosition(freeCell);
            Collection<Position> coverage = figure.placeOnBoard(figureBoard);
            if (!coverage.isEmpty()) {
                figureBoard.addFigure(figure);
                getResultProcessor().addResult(figureBoard);
                figureBoard.removeFigure(figure);
            }
            freeCell = freeCellsBoard.getNextFreeCell(figure);
        }
    }

    private final class CalculationState {
        private final Figure figure;
        private final Position freeCell;

        public CalculationState(Figure figure, Position freeCell) {
            this.figure = figure;
            this.freeCell = freeCell;
        }

        public Figure getFigure() {
            return figure;
        }

        public Position getFreeCell() {
            return freeCell;
        }

        public CalculationState getNextState(FreeCellsBoard freeCellsBoard) {
            return new CalculationState(getFigure(), freeCellsBoard.getNextFreeCell(getFigure()));
        }
    }
}
