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
    public void testSmallBoardSemiRecursiveTree() {
        performTest("3x3,2xK,1xR", 4, BoardFactory.FigureBoardType.TREE, ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
    }
    @Test

    public void testSmallBoardSemiRecursiveArray() {
        performTest("3x3,2xK,1xR", 4, BoardFactory.FigureBoardType.ARRAY, ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
    }
    @Test
    public void testSmallBoardRecursiveTree() {
        performTest("3x3,2xK,1xR", 4, BoardFactory.FigureBoardType.TREE, ProcessorBuilder.ProcessorType.RECURSIVE);
    }
    @Test
    public void testSmallBoardRecursiveArray() {
        performTest("3x3,2xK,1xR", 4, BoardFactory.FigureBoardType.ARRAY, ProcessorBuilder.ProcessorType.RECURSIVE);
    }

    @Test
    public void testMediumBoardSemiRecursiveTree() {
        performTest("4x4,2xR,4xN", 8, BoardFactory.FigureBoardType.TREE, ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
    }
    @Test

    public void testMediumBoardSemiRecursiveArray() {
        performTest("4x4,2xR,4xN", 8, BoardFactory.FigureBoardType.ARRAY, ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
    }
    @Test
    public void testMediumBoardRecursiveTree() {
        performTest("4x4,2xR,4xN", 8, BoardFactory.FigureBoardType.TREE, ProcessorBuilder.ProcessorType.RECURSIVE);
    }
    @Test
    public void testMediumBoardRecursiveArray() {
        performTest("4x4,2xR,4xN", 8, BoardFactory.FigureBoardType.ARRAY, ProcessorBuilder.ProcessorType.RECURSIVE);
    }

    @Test
    public void testEightQueenSemiRecursiveTree() {
        performTest("8x8,8xQ", 92, BoardFactory.FigureBoardType.TREE, ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
    }
    @Test

    public void testEightQueenSemiRecursiveArray() {
        performTest("8x8,8xQ", 92, BoardFactory.FigureBoardType.ARRAY, ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
    }
    @Test
    public void testEightQueenRecursiveTree() {
        performTest("8x8,8xQ", 92, BoardFactory.FigureBoardType.TREE, ProcessorBuilder.ProcessorType.RECURSIVE);
    }
    @Test
    public void testEightQueenRecursiveArray() {
        performTest("8x8,8xQ", 92, BoardFactory.FigureBoardType.ARRAY, ProcessorBuilder.ProcessorType.RECURSIVE);
    }

    private void performTest(String input, int expectedResultCount, BoardFactory.FigureBoardType figureBoardType, ProcessorBuilder.ProcessorType processorType) {
        ProcessorBuilder processorBuilder = new ProcessorBuilder();
        processorBuilder.setProcessorType(processorType);
        TestSummaryProcessor testSummaryProcessor = new TestSummaryProcessor();
        Processor processor = processorBuilder.fromString(input);
        processor.setResultProcessor(testSummaryProcessor);
        processor.setFigureBoardType(figureBoardType);
        processor.process();
        assertEquals(expectedResultCount, testSummaryProcessor.getNumberOfSolutions());
    }
}
