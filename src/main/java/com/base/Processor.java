package com.base;

import com.base.board.FigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import com.base.figures.Figure;

import java.util.Collection;
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

    public Processor(int width, int height, Deque<Figure> figures) {
        this.width = width;
        this.height = height;
        this.figures = figures;
    }

    public void process() {
        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(width, height);
        FigureBoard figureBoard = new FigureBoard(width, height);
        placeFigure(freeCellsBoard, figureBoard, null);
    }

    private void placeFigure(FreeCellsBoard freeCellsBoard, FigureBoard figureBoard, Figure previusProcessedFigure) {
        Position freeCell;
        Figure figure = figures.poll();
        if (previusProcessedFigure == null || !figure.isSameType(previusProcessedFigure)) {
            freeCell = freeCellsBoard.getFirstFreeCell();
        } else {
            freeCell = freeCellsBoard.getNextFreeCell(previusProcessedFigure.getPosition());
        }

        while (freeCell != null) {
            figure.setPosition(freeCell);
            Collection<Position> figureCoverage = figure.placeOnBoard(figureBoard);
            if (!figureCoverage.isEmpty()) {
                figureBoard.addFigure(figure);
                if (figures.isEmpty()) {
                    processResult(figureBoard);
                } else {
                    placeFigure(freeCellsBoard, figureBoard, figure);
                }
                figureBoard.removeFigure(figure);
            }
            freeCell = freeCellsBoard.getNextFreeCell(freeCell);
        }
        figures.push(figure);
    }

    private void processResult(FigureBoard figureBoard) {
        System.out.println(figureBoard.toString());
    }
}
