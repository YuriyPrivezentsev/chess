package com.base.output;

/**
 * Construct the result processor
 *
 * @author Yuriy Privezentsev
 * @since 11/22/2015
 */
public class ResultProcessorFactory {
    public enum ResultProcessorType{
        GENERIC {
            @Override
            public ResultProcessor buildResultProcessor(String... args) {
                return new GenericResultProcessor();
            }
        },
        CSV {
            @Override
            public ResultProcessor buildResultProcessor(String... args) {
                return new CsvResultProcessor(args[0]);
            }
        };
        public abstract ResultProcessor buildResultProcessor(String... args);
    }
    private String csvFileName = "result.csv";

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public ResultProcessor buildResultProcessor(ResultProcessorType type){
        return type.buildResultProcessor(csvFileName);
    }
}
