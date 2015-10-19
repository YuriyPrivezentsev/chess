package com.base.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Writes result to csv file.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class CsvResultProcessor implements ResultProcessor {

    public static final String CSV_DELIMITER = ",";
    private PrintWriter writer;
    private final File outputFile;

    public CsvResultProcessor(File outputFile) throws FileNotFoundException {
        this.outputFile = outputFile;
        writer = new PrintWriter(this.outputFile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processResult(String result) {
        result = result.replace(" ", CSV_DELIMITER);
        writer.println(result);
        writer.flush();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void processSummary(String summary) {
        summary = summary.replace("=", CSV_DELIMITER);
        writer.println();
        writer.println(summary);
        writer.flush();
        writer.close();
    }
}
