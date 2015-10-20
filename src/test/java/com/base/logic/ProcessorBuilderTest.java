package com.base.logic;

import com.base.figures.*;
import com.base.logic.ProcessorBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for @link{ProcessorBuilder}
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class ProcessorBuilderTest {
    ProcessorBuilder processorBuilder = new ProcessorBuilder();

    @Test
    public void testOrder() {
        String input = "QX1,KX1,NX1,BX1,RX1,1XN";
        StringTokenizer tokenizer = new StringTokenizer(input, ProcessorBuilder.PARAMETER_DELIMITER);
        Deque<Figure> figures = processorBuilder.getFigures(tokenizer);

        assertEquals(6, figures.size());
        List<Figure> orderedFigures = new ArrayList<>(6);
        orderedFigures.addAll(figures);
        assertEquals(Rook.class, orderedFigures.get(0).getClass());
        assertEquals(Bishop.class, orderedFigures.get(1).getClass());
        assertEquals(Queen.class, orderedFigures.get(2).getClass());
        assertEquals(Knight.class, orderedFigures.get(3).getClass());
        assertEquals(Knight.class, orderedFigures.get(4).getClass());
        assertEquals(King.class, orderedFigures.get(5).getClass());

    }

    @Test
    public void testRecursiveType() {
        String input = "4x4,2xQ";

        processorBuilder.setProcessorType(ProcessorBuilder.ProcessorType.RECURSIVE);
        Processor processor = processorBuilder.fromString(input);
        assertEquals(RecursiveProcessor.class,processor.getClass());
    }

    @Test
    public void testSemiRecursiveType() {
        String input = "4x4,2xQ";

        processorBuilder.setProcessorType(ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
        Processor processor = processorBuilder.fromString(input);
        assertEquals(SemiRecursiveProcessor.class,processor.getClass());
    }
}
