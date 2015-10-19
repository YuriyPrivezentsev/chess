package com.base;

import com.base.board.FigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import com.base.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The main logic class
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class Processor {
    private static final Logger LOG = LoggerFactory.getLogger(Processor.class);
    public static final String SUMMARY = "Summary";
    private final int width;
    private final int height;
    private final Deque<Figure> figures;
    private int resultCount = 0;
    private ResultProcessor resultProcessor;
    private Thread resultThread;
    private BlockingQueue<String> resultQueue = new LinkedBlockingQueue<>();
    private volatile boolean running;

    public Processor(int width, int height, Deque<Figure> figures) {
        this.width = width;
        this.height = height;
        this.figures = figures;
        this.resultProcessor = new GenericResultProcessor();
    }

    public Processor(int width, int height, Deque<Figure> figures, ResultProcessor resultProcessor) {
        this.width = width;
        this.height = height;
        this.figures = figures;
        this.resultProcessor = resultProcessor;
    }

    /**
     * Calculate solutions.
     */
    public void process() {
        long startTime = System.currentTimeMillis();
        resultCount = 0;
        if (figures.isEmpty()
                || figures.size() > width * height) {
            return;
        }

        startResultProcessingThread();

        FreeCellsBoard freeCellsBoard = new FreeCellsBoard(width, height);
        FigureBoard figureBoard = new FigureBoard(width, height);
        placeFigure(freeCellsBoard, figureBoard, null);

        long time = System.currentTimeMillis() - startTime;
        addSummary(time);
    }

    private void startResultProcessingThread() {
        running = true;

        resultThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    String result = null;
                    try {
                        result = resultQueue.take();
                        if(result.startsWith(SUMMARY)){
                            resultProcessor.processSummary(result);
                            running = false;
                        } else {
                            resultProcessor.processResult(result);
                        }
                        LOG.debug(result);
                    } catch (InterruptedException e) {
                        LOG.error("Unable to save the result", e);
                    }
                }
            }
        });

        resultThread.start();
    }

    public ResultProcessor getResultProcessor() {
        return resultProcessor;
    }

    public void setResultProcessor(ResultProcessor resultProcessor) {
        this.resultProcessor = resultProcessor;
    }

    private void addSummary(long time) {
        String summary = SUMMARY + "\r\nResults count = " + resultCount + "\r\nTime = " + time + "ms.";
        LOG.debug(summary);
        resultQueue.offer(summary);
        running = false;
        try {
            resultThread.join(1000);
        } catch (InterruptedException e) {
            LOG.error("Unable to gracefully shut down the result thread, may suffer data loss");
        }
        resultProcessor.processSummary(summary);
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
        String result = figureBoard.toString();
        resultQueue.offer(result);
        resultCount++;
    }
}
