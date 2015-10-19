package com.base.logic;

import com.base.output.ResultProcessor;

/**
 * Interface for board and figure traversal and looking for solutions.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public interface Processor {
    /**
     * Calculate all possible solutions.
     */
    void process();

    ResultProcessor getResultProcessor();

    void setResultProcessor(ResultProcessor resultProcessor);
}
