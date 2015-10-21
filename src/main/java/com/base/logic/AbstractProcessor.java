package com.base.logic;

import com.base.board.BoardFactory;
import com.base.board.FigureBoard;
import com.base.figures.Figure;
import com.base.output.GenericResultProcessor;
import com.base.output.ResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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
    private BlockingQueue<FigureBoard> resultQueue = new LinkedBlockingQueue<>();
    private volatile boolean running;

    public AbstractProcessor(int width, int height, Deque<Figure> figures) {
        this.boardFactory = new BoardFactory(width, height);
        this.figures = figures;
    }

    protected void startResultProcessingThread() {
        running = true;

        resultThread = new Thread(new Runnable() {
            @Override
            public void run() {
                resultProcessor.open();
                while (running || !resultQueue.isEmpty()) {
                    FigureBoard result;
                    try {
                        result = resultQueue.poll(100, TimeUnit.MILLISECONDS);
                        resultProcessor.processResult(result);
                        if(LOG.isDebugEnabled()){
                            LOG.debug(result.toString());
                        }
                    } catch (InterruptedException e) {
                        LOG.error("Unable to save the result", e);
                    }
                }
                resultProcessor.close();
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
        running = false;
        LOG.debug(summary);
        try {
            resultThread.join();
        } catch (InterruptedException e) {
            LOG.error("Unable to gracefully shut down the result thread, may suffer data loss");
        }
        resultProcessor.open();
        resultProcessor.processSummary(summary);
        resultProcessor.close();
    }


    protected void processResult(FigureBoard figureBoard) {
        resultQueue.offer(figureBoard.deepCopy());
        resultCount++;
    }

}
