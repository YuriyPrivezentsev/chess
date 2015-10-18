package com.base;

import com.base.figures.Figure;
import com.base.figures.FigureFactory;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * The main logic class
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class Processor {

    public static final String PARAMETER_DELIMITER = ",";
    public static final String MULTIPLIER_DELIMITER = "x";

    public Processor(String input) {
        input = input.replace(" ","");
        StringTokenizer tokenizer = new StringTokenizer(input, PARAMETER_DELIMITER);

        String size = tokenizer.nextToken();
        StringTokenizer sizeTokenizer = new StringTokenizer(size, MULTIPLIER_DELIMITER);
        int width = getInteger(sizeTokenizer);
        int height = getInteger(sizeTokenizer);

        Queue<Figure> figures = getFigures(tokenizer);
    }

    private Queue<Figure> getFigures(StringTokenizer tokenizer) {
        Queue<Figure> result = new PriorityQueue<>();
        while (tokenizer.hasMoreTokens()){
            result.addAll(FigureFactory.createSetOfFigures(tokenizer.nextToken()));
        }
        return null;
    }

    private int getInteger(StringTokenizer tokenizer) {
        int result = Integer.parseInt(tokenizer.nextToken());
        if(result <= 0){
            throw new IllegalArgumentException("Board size cannot be of a non-positive value: " + result);
        }
        return result;
    }


}
