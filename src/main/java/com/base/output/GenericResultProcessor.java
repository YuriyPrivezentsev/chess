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
    /**
     * {@inheritDoc}
     * @param result
     */
    @Override
    public void processResult(FigureBoard result) {
        if (LOG.isTraceEnabled()) {
            LOG.trace(result.toString() + "\r\n");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processSummary(String summary) {
        LOG.info(summary);
    }

    /**
     * Empty method stub in this implementation.
     */
    @Override
    public void open() {

    }

    /**
     * Empty method stub in this implementation.
     */
    @Override
    public void close() {

    }
}
