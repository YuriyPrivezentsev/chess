package com.base.output;

import com.base.board.FigureBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes result to csv file.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class CsvResultProcessor implements ResultProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(CsvResultProcessor.class);
    public static final String CSV_DELIMITER = ",";
    private BufferedWriter writer;
    private final File outputFile;

    public CsvResultProcessor(File outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * {@inheritDoc}
     * @param result
     */
    @Override
    public void processResult(FigureBoard result) {
        try {
            writer.write(result.toString() + "\r\n");
            writer.flush();
        } catch (IOException e) {
            LOG.warn("Unable to write the result to csv",e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processSummary(String summary) {
        try {
            writer.write(summary);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save summary.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void open() {
        try {
            writer = new BufferedWriter(new FileWriter(this.outputFile, true));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to open file for writing.  No result will be published.", e);
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to finalise file. No result will be published.", e);
        }
    }
}
