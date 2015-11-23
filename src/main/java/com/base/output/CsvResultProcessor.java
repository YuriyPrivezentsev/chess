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
public class CsvResultProcessor extends AbstractMultithreadedResultProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(CsvResultProcessor.class);
    private static final String CSV_DELIMITER = ",";
    private BufferedWriter writer;
    private final File outputFile;

    public CsvResultProcessor(String outputFileName) {
        boolean success = true;
        this.outputFile = new File(outputFileName);
        if(outputFile.exists()){
            success = outputFile.delete();
        }
        try {
            success &= outputFile.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to create output file.  No result will be published.", e);
        }

        if(!success){
            throw new IllegalStateException("Unable to create output file.  No result will be published.");
        }
    }

    @Override
    protected void processResult(FigureBoard result) {
        try {
            writer.write(result.toString() + "\r\n");
            writer.flush();
        } catch (IOException e) {
            LOG.warn("Unable to write the result to csv", e);
        }
    }

    @Override
    protected void processSummary(String summary) {
        try {
            writer.write(summary.replace(" = ",CSV_DELIMITER));
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save summary.", e);
        }
    }

    @Override
    protected void open() {
        try {
            writer = new BufferedWriter(new FileWriter(this.outputFile, true));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to open file for writing.  No result will be published.", e);
        }
    }

    @Override
    protected void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to finalise file. No result will be published.", e);
        }
    }
}
