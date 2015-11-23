package com.base.logic;

import com.base.board.BoardFactory;
import com.base.figures.Figure;
import com.base.figures.FigureFactory;
import com.base.output.ResultProcessorFactory;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Build processor from different kind of input parameters.
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class ProcessorBuilder {
    public enum ProcessorType {
        RECURSIVE {
            @Override
            Processor buildProcessor(int width, int height, Deque<Figure> figures) {
                return new RecursiveProcessor(width, height, figures);
            }
        },
        SEMI_RECURSIVE {
            @Override
            Processor buildProcessor(int width, int height, Deque<Figure> figures) {
                return new SemiRecursiveProcessor(width, height, figures);
            }
        };

        abstract Processor buildProcessor(int width, int height, Deque<Figure> figures);
    }

    public static final String PARAMETER_DELIMITER = ",";
    public static final String MULTIPLIER_DELIMITER = "X";

    private final FigureFactory figureFactory = new FigureFactory();
    private ProcessorType processorType = ProcessorType.RECURSIVE;
    private BoardFactory.FigureBoardType figureBoardType = BoardFactory.FigureBoardType.TREE;
    private ResultProcessorFactory.ResultProcessorType resultProcessorType = ResultProcessorFactory.ResultProcessorType.GENERIC;
    private ResultProcessorFactory resultProcessorFactory = new ResultProcessorFactory();

    /**
     * Parse input string to processor parameters and create processor.
     *
     * @param input - processor parameters string in form
     *              board_widthXboard_height,figure_nameXcount.
     *              For example 3X3,NX2,RX1 stands for problem of 3?3 board containing 2 Kings and 1 Rook.
     */
    public Processor fromString(String input) {
        input = input.replace(" ", "").toUpperCase();
        StringTokenizer tokenizer = new StringTokenizer(input, PARAMETER_DELIMITER);

        String size = tokenizer.nextToken();
        StringTokenizer sizeTokenizer = new StringTokenizer(size, MULTIPLIER_DELIMITER);
        int width = getInteger(sizeTokenizer);
        int height = getInteger(sizeTokenizer);

        Deque<Figure> figures = getFigures(tokenizer);

        Processor solutionProcessor = processorType.buildProcessor(width, height, figures);
        solutionProcessor.setFigureBoardType(figureBoardType);
        solutionProcessor.setResultProcessor(resultProcessorFactory.buildResultProcessor(resultProcessorType));

        return solutionProcessor;
    }

    /**
     * Construct prioritized list of figures for the problem.
     *
     * @param tokenizer - tokens with figure types and quantities
     * @return - prioritized list of figures
     */
    public Deque<Figure> getFigures(StringTokenizer tokenizer) {
        LinkedList<Figure> result = new LinkedList<>();
        while (tokenizer.hasMoreTokens()) {
            result.addAll(figureFactory.createSetOfFigures(tokenizer.nextToken()));
        }
        Collections.sort(result);
        return result;
    }

    public ProcessorType getProcessorType() {
        return processorType;
    }

    public void setProcessorType(ProcessorType processorType) {
        this.processorType = processorType;
    }

    public BoardFactory.FigureBoardType getFigureBoardType() {
        return figureBoardType;
    }

    public void setFigureBoardType(BoardFactory.FigureBoardType figureBoardType) {
        this.figureBoardType = figureBoardType;
    }

    public ResultProcessorFactory.ResultProcessorType getResultProcessorType() {
        return resultProcessorType;
    }

    public void setResultProcessorType(ResultProcessorFactory.ResultProcessorType resultProcessorType) {
        this.resultProcessorType = resultProcessorType;
    }

    public ResultProcessorFactory getResultProcessorFactory() {
        return resultProcessorFactory;
    }

    public void setResultProcessorFactory(ResultProcessorFactory resultProcessorFactory) {
        this.resultProcessorFactory = resultProcessorFactory;
    }

    private int getInteger(StringTokenizer tokenizer) {
        int result = Integer.parseInt(tokenizer.nextToken());
        if (result <= 0) {
            throw new IllegalArgumentException("Board size cannot be of a non-positive value: " + result);
        }
        return result;
    }


}
