package com.base.output;

import com.base.board.FigureBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prints results to log
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class GenericResultProcessor implements ResultProcessor{
    private static final Logger LOG = LoggerFactory.getLogger(GenericResultProcessor.class);
    private int resultCount = 0;
    /**
     * {@inheritDoc}
     */
    @Override
    public void addResult(FigureBoard result) {
        resultCount++;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void addSummary(long time) {
        String summary = String.format(SUMMARY, resultCount,time);
        LOG.info(summary);
    }
}
