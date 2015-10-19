package com.base.integration;

import com.base.output.ResultProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Summary processor implementation for test.
 * The class extracts the number of solutions from the summary.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class TestSummaryProcessor implements ResultProcessor{
    private int numberOfSolutions;

    @Override
    public void processResult(String result) {

    }

    @Override
    public void processSummary(String summary) {
        Pattern pattern = Pattern.compile("Results count = [0-9]+");
        Matcher matcher = pattern.matcher(summary);
        if (matcher.find()){
            String resultCount = matcher.group(0);
            resultCount = resultCount.replace("Results count = ", "").replace(" ","");
            numberOfSolutions = Integer.parseInt(resultCount);
        }
    }

    public int getNumberOfSolutions() {
        return numberOfSolutions;
    }
}
