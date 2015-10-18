package com.base;

import com.base.figures.Figure;
import com.base.figures.FigureFactory;

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
    public static final String PARAMETER_DELIMITER = ",";
    public static final String MULTIPLIER_DELIMITER = "X";

    private final FigureFactory figureFactory = new FigureFactory();

    public Processor fromString(String input) {
        input = input.replace(" ", "");
        StringTokenizer tokenizer = new StringTokenizer(input, PARAMETER_DELIMITER);

        String size = tokenizer.nextToken();
        StringTokenizer sizeTokenizer = new StringTokenizer(size, MULTIPLIER_DELIMITER);
        int width = getInteger(sizeTokenizer);
        int height = getInteger(sizeTokenizer);

        Deque<Figure> figures = getFigures(tokenizer);

        return new Processor(width, height, figures);
    }

    public Deque<Figure> getFigures(StringTokenizer tokenizer) {
        LinkedList<Figure> result = new LinkedList<>();
        while (tokenizer.hasMoreTokens()) {
            result.addAll(figureFactory.createSetOfFigures(tokenizer.nextToken()));
        }
        Collections.sort(result);
        return result;
    }

    private int getInteger(StringTokenizer tokenizer) {
        int result = Integer.parseInt(tokenizer.nextToken());
        if (result <= 0) {
            throw new IllegalArgumentException("Board size cannot be of a non-positive value: " + result);
        }
        return result;
    }


}
