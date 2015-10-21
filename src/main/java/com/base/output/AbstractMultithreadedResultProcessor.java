package com.base.output;

import com.base.board.FigureBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Result processor which delegates heavy weight operations to another thread.
 *
 * @author Yuriy Privezentsev
 * @since 10/21/2015
 */
public abstract class AbstractMultithreadedResultProcessor implements ResultProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMultithreadedResultProcessor.class);
    private Thread resultThread;
    private BlockingQueue<FigureBoard> resultQueue = new LinkedBlockingQueue<>();
    private volatile boolean running;
    private int resultCount = 0;

    public AbstractMultithreadedResultProcessor() {
        startResultProcessingThread();
    }

    protected void startResultProcessingThread() {
        running = true;

        resultThread = new Thread(new Runnable() {
            @Override
            public void run() {
                open();
                while (running || !resultQueue.isEmpty()) {
                    FigureBoard result;
                    try {
                        result = resultQueue.poll(100, TimeUnit.MILLISECONDS);
                        processResult(result);
                        if(LOG.isDebugEnabled()){
                            LOG.debug(result.toString());
                        }
                    } catch (InterruptedException e) {
                        LOG.error("Unable to save the result", e);
                    }
                }
                close();
            }
        });
        resultThread.start();
    }
    public void addResult(FigureBoard result){
        resultQueue.offer(result.deepCopy());
        resultCount++;
    }

    public void addSummary(long time) {
        String summary = String.format(SUMMARY, resultCount,time);
        running = false;
        LOG.debug(summary);
        try {
            resultThread.join();
        } catch (InterruptedException e) {
            LOG.error("Unable to gracefully shut down the result thread, may suffer data loss");
        }
        open();
        processSummary(summary);
        close();
    }

    protected abstract void processResult(FigureBoard result);

    protected abstract void processSummary(String summary);

    protected abstract void open();

    protected abstract void close();

}
