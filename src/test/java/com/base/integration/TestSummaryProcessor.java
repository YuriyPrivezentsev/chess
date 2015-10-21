package com.base.integration;

import com.base.board.FigureBoard;
import com.base.output.ResultProcessor;

/**
 * Summary processor implementation for test.
 * The class extracts the number of solutions from the summary.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class TestSummaryProcessor implements ResultProcessor {
    private int numberOfSolutions = 0;

    @Override
    public void addResult(FigureBoard result) {
        numberOfSolutions++;
    }

    @Override
    public void addSummary(long time) {

    }

    public int getNumberOfSolutions() {
        return numberOfSolutions;
    }
}
