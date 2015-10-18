package com.base.figures;

import com.base.Processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 * Construct the figure from the input string
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class FigureFactory {

    public static Collection<Figure> createSetOfFigures(String input){
        StringTokenizer tokenizer = new StringTokenizer(input, Processor.MULTIPLIER_DELIMITER);
        String figure;
        int quantity;

        if(startsWithNumber(input)){
            quantity = getQuantity(tokenizer);
            figure = tokenizer.nextToken();
        } else if (endsWithNumber(input)){
            figure = tokenizer.nextToken();
            quantity = getQuantity(tokenizer);
        } else {
            throw new IllegalArgumentException("The figure parameter is in illegal format: " + input
                    + ". Must be either <figure notation>x<quantity> or <quantity>x<figure notation>");
        }

        if(tokenizer.hasMoreTokens()){
            throw new IllegalArgumentException("Unrecognized figure set format: " + input);
        }

        Collection<Figure> result = new ArrayList<>(quantity);
        while(quantity > 0){
            result.add(getFigure(figure));
            quantity--;
        }

        return result;
    }

    private static int getQuantity(StringTokenizer tokenizer) {
        int result = Integer.parseInt(tokenizer.nextToken());
        if(result <= 0){
            throw new IllegalArgumentException("Figure quantity cannot be of a non-positive value: " + result);
        }
        return result;
    }

    private static boolean startsWithNumber(String input){
        byte start = (byte) input.charAt(0);
        return start > 47 && start < 58;
    }

    private static boolean endsWithNumber(String input){
        byte start = (byte) input.charAt(input.length() - 1);
        return start > 47 && start < 58;
    }

    public static Figure getFigure(String name) {
        switch (name){
            case King.NAME:
                return new King();
            case Queen.NAME:
                return new Queen();
            case Rook.NAME:
                return new Rook();
            case Bishop.NAME:
                return new Bishop();
            case Knight.NAME:
                return new Knight();
        }
        throw new IllegalArgumentException("There is no figure with such notation: " + name);
    }
}
