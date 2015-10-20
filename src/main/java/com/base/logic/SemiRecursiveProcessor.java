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
public class SemiRecursiveProcessor extends AbstractProcessor{
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
        resultCount = 0;
        startResultProcessingThread();
        if (isTrivialCase()){
            return;
        }

        FigureBoard figureBoard = boardFactory.getFigureBoard(BoardFactory.FigureBoardType.TREE);
        FreeCellsBoard freeCellsBoard = boardFactory.getFreeCellsBoard();
        Position freeCell = freeCellsBoard.getFirstFreeCell();
        Figure figure = figures.pop();

        while (freeCell != null ){
            if (figures.isEmpty()){
                placeLastFigure(figure, freeCell, figureBoard, freeCellsBoard);
                if(!processedFigures.isEmpty()){
                    figure = popFigure(figure, figureBoard);
                    freeCell = popPosition(figure, freeCellsBoard);
                } else {
                    freeCell = null;
                }
            } else {
                figure.setPosition(freeCell);
                Collection<Position> coverage = figure.placeOnBoard(figureBoard);
                if(!coverage.isEmpty()){
                    figureBoard.addFigure(figure);
                    Collection<Position> actualCoverage = freeCellsBoard.occupyCells(coverage);
                    processedPositions.push(actualCoverage);
                    processedFigures.push(figure);
                    figure = figures.pop();
                    freeCell = getNextFreeCell(figure, freeCellsBoard);
                } else {
                    freeCell = freeCellsBoard.getNextFreeCell(figure);
                }
                while(freeCell == null && !processedFigures.isEmpty()) {
                    figure = popFigure(figure, figureBoard);
                    freeCell = popPosition(figure, freeCellsBoard);
                }
            }
        }

        long time = System.currentTimeMillis() - startTime;
        addSummary(time);
    }

    private Position getNextFreeCell(Figure figure, FreeCellsBoard freeCellsBoard) {
        Position freeCell;
        if(figure.isSameType(processedFigures.peek())){
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

    private void placeLastFigure(Figure figure, Position freeCell, FigureBoard figureBoard, FreeCellsBoard freeCellsBoard) {
        while (freeCell != null){
            figure.setPosition(freeCell);
            Collection<Position> coverage = figure.placeOnBoard(figureBoard);
            if(!coverage.isEmpty()){
                figureBoard.addFigure(figure);
                processResult(figureBoard);
                figureBoard.removeFigure(figure);
            }
            freeCell = freeCellsBoard.getNextFreeCell(figure);
        }
    }
}
