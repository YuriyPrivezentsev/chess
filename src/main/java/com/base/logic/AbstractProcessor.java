package com.base.logic;

import com.base.board.BoardFactory;
import com.base.board.FigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import com.base.figures.Figure;
import com.base.output.GenericResultProcessor;
import com.base.output.ResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Common processor functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/20/2015
 */
public abstract class AbstractProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(RecursiveProcessor.class);
    public static final String SUMMARY = "Summary";
    protected final BoardFactory boardFactory;
    protected final Deque<Figure> figures;
    protected int resultCount = 0;
    private Thread resultThread;
    private BoardFactory.FigureBoardType figureBoardType = BoardFactory.FigureBoardType.TREE;
    private ResultProcessor resultProcessor = new GenericResultProcessor();
    private BlockingQueue<String> resultQueue = new LinkedBlockingQueue<>();
    private volatile boolean running;

    public AbstractProcessor(int width, int height, Deque<Figure> figures) {
        this.boardFactory = new BoardFactory(width,height);
        this.figures = figures;
    }

    protected void startResultProcessingThread() {
        running = true;

        resultThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    String result;
                    try {
                        result = resultQueue.take();
                        if (result.startsWith(SUMMARY)) {
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

    @Override
    public BoardFactory.FigureBoardType getFigureBoardType() {
        return figureBoardType;
    }

    @Override
    public void setFigureBoardType(BoardFactory.FigureBoardType figureBoardType) {
        this.figureBoardType = figureBoardType;
    }

    @Override
    public ResultProcessor getResultProcessor() {
        return resultProcessor;
    }

    @Override
    public void setResultProcessor(ResultProcessor resultProcessor) {
        this.resultProcessor = resultProcessor;
    }

    protected boolean isTrivialCase() {
        if (figures.isEmpty()
                || figures.size() > boardFactory.getTotalCellCount()) {
            getResultProcessor().processSummary("There are no solutions.");
            return true;
        }
        return false;
    }

    protected void addSummary(long time) {
        String summary = SUMMARY + "\r\nResults count = " + resultCount + "\r\nTime = " + time + "ms.";
        LOG.debug(summary);
        resultQueue.offer(summary);
        try {
            resultThread.join();
        } catch (InterruptedException e) {
            LOG.error("Unable to gracefully shut down the result thread, may suffer data loss");
        }
    }


    protected void processResult(FigureBoard figureBoard) {
        String result = figureBoard.toString();
        resultQueue.offer(result);
        resultCount++;
    }

}
