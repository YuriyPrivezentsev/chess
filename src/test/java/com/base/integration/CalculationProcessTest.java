package com.base.integration;

import com.base.board.BoardFactory;
import com.base.logic.Processor;
import com.base.logic.ProcessorBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Technically it is integration test. Since it is a bit of over-engineering to include integration test frameworks into
 * the pet project we will handle it with unit test framework and maven profiles.
 * To run this test choose integration-tests maven profile.
 * To add this test to unit tests choose all-tests profile.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class CalculationProcessTest {
    @Test
    public void testSmallBoard() {
        performTest("3x3,2xK,1xR", 4);
    }

    @Test
    public void testMediumBoard() {
        performTest("4x4,2xR,4xN", 8);
    }

    @Test
    public void testEightQueen() {
        performTest("8x8,8xQ", 92);
    }

    private void performTest(String input, int expectedResultCount) {
        performTest(input, expectedResultCount, BoardFactory.FigureBoardType.TREE);
        performTest(input, expectedResultCount, BoardFactory.FigureBoardType.ARRAY);
    }

    private void performTest(String input, int expectedResultCount, BoardFactory.FigureBoardType figureBoardType) {
        ProcessorBuilder processorBuilder = new ProcessorBuilder();
        TestSummaryProcessor testSummaryProcessor = new TestSummaryProcessor();
        Processor processor = processorBuilder.fromString(input);
        processor.setResultProcessor(testSummaryProcessor);
        processor.setFigureBoardType(figureBoardType);
        processor.process();
        assertEquals(expectedResultCount, testSummaryProcessor.getNumberOfSolutions());
    }
}
