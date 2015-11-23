package com.base.output;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for @link{ResultProcessorFactory}
 *
 * @author Yuriy Privezentsev
 * @since 11/22/2015
 */
public class ResultProcessorFactoryTest {
    ResultProcessorFactory factory = new ResultProcessorFactory();

    @Test
    public void testBuildGeneric() {
        ResultProcessor resultProcessor = factory.buildResultProcessor(ResultProcessorFactory.ResultProcessorType.GENERIC);
        assertEquals(GenericResultProcessor.class, resultProcessor.getClass());
    }

    @Test
    public void testBuildCsv() {
        ResultProcessor resultProcessor = factory.buildResultProcessor(ResultProcessorFactory.ResultProcessorType.CSV);
        assertEquals(CsvResultProcessor.class, resultProcessor.getClass());
    }

}
