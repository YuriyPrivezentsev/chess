package com.base.output;

import com.base.board.FigureBoard;

/**
 * Processing string representation of the results.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public interface ResultProcessor {
    /**
     * Deliver string representation of the result to user.
     * @param result - string representation of the result board
     */
    void processResult(FigureBoard result);

    /**
     * Deliver summary to user.
     * @param summary - string representation of summary.
     */
    void processSummary(String summary);

    /**
     * Open the processor stream. For the case of streaming processor.
     */
    void open();

    /**
     * Close the processor stream. For the case of streaming processor.
     */
    void close();
}
