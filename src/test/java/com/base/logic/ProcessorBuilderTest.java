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
    @Test
    public void testOrder() {
        String input = "QX1,KX1,NX1,BX1,RX1,1XN";
        ProcessorBuilder processorBuilder = new ProcessorBuilder();
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

}
