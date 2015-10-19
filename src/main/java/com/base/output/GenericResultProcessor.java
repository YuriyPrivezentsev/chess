package com.base.output;

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
     */
    @Override
    public void processResult(String result) {
        LOG.info(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processSummary(String summary) {
        LOG.info(summary);
    }
}
