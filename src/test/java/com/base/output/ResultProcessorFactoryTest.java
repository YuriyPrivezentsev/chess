package com.base.output;

import org.junit.Test;

import java.io.File;

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
        File file = new File(factory.getCsvFileName());
        boolean performTest = true;
        if(file.exists()){
            performTest = file.delete();
        }
        if (performTest) {
            ResultProcessor resultProcessor = factory.buildResultProcessor(ResultProcessorFactory.ResultProcessorType.CSV);
            assertEquals(CsvResultProcessor.class, resultProcessor.getClass());
            cleanup();
        }
    }

    private void cleanup() {
        File resultFile = new File(factory.getCsvFileName());
        if(!resultFile.delete()){
            resultFile.deleteOnExit();
        }
    }

}
