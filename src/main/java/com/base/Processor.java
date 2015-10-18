package com.base;

import com.base.board.FigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import com.base.figures.Figure;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

/**
 * The main logic class
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class Processor {

    private final int width;
    private final int height;
    private final Deque<Figure> figures;
    private int resultCount = 0;

    public Processor(int width, int height, Deque<Figure> figures) {
        this.width = width;
        this.height = height;
        this.figures = figures;
    }

    /**
     * Calculate solutions.
     */
    public void process() {
        long startTime = System.currentTimeMillis();
        resultCount = 0;
        if(figures.isEmpty()
                || figures.size() > width*height){
            return;
        }

        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(width, height);
        FigureBoard figureBoard = new FigureBoard(width, height);
        placeFigure(freeCellsBoard, figureBoard, null);

        long time = System.currentTimeMillis() - startTime;
        addSummary(time);
    }

    private void addSummary(long time) {
        System.out.println("\r\n");
        System.out.println("resultCount = " + resultCount);
        System.out.println("time = " + time);
    }

    private void placeFigure(FreeCellsBoard freeCellsBoard, FigureBoard figureBoard, Figure previousProcessedFigure) {
        if(figures.size() > freeCellsBoard.getFreeCellsCount()) {
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
                    processResult(figureBoard);
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

    private void processResult(FigureBoard figureBoard) {
        System.out.println(figureBoard.toString());
        resultCount++;
    }
}
