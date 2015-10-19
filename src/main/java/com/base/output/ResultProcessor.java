package com.base.output;

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
    void processResult(String result);

    /**
     * Deliver summary to user;
     * @param summary - string representation of summary.
     */
    void processSummary(String summary);
}
