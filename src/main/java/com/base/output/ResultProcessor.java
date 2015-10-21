package com.base.output;

import com.base.board.FigureBoard;

/**
 * Processing string representation of the results.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public interface ResultProcessor {
    String SUMMARY = "Summary\r\nResults count = %d\r\nTime = %d ms.";
    /**
     * Deliver string representation of the result to user.
     * @param result - string representation of the result board
     */
    void addResult(FigureBoard result);

    /**
     * Deliver summary to user.
     * @param time - time in milliseconds spent on calculation.
     */
    void addSummary(long time);
}
