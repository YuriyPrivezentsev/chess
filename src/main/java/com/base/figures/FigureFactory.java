package com.base.figures;

import com.base.logic.ProcessorBuilder;

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

    public Collection<Figure> createSetOfFigures(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, ProcessorBuilder.MULTIPLIER_DELIMITER);
        String figure;
        int quantity;

        if (startsWithNumber(input)) {
            quantity = getQuantity(tokenizer);
            figure = tokenizer.nextToken();
        } else if (endsWithNumber(input)) {
            figure = tokenizer.nextToken();
            quantity = getQuantity(tokenizer);
        } else {
            throw new IllegalArgumentException("The figure parameter is in illegal format: " + input
                    + ". Must be either <figure notation>x<quantity> or <quantity>x<figure notation>");
        }

        if (tokenizer.hasMoreTokens()) {
            throw new IllegalArgumentException("Unrecognized figure set format: " + input);
        }

        Collection<Figure> result = new ArrayList<>(quantity);
        while (quantity > 0) {
            result.add(getFigure(figure));
            quantity--;
        }

        return result;
    }

    private int getQuantity(StringTokenizer tokenizer) {
        int result = Integer.parseInt(tokenizer.nextToken());
        if (result <= 0) {
            throw new IllegalArgumentException("Figure quantity cannot be of a non-positive value: " + result);
        }
        return result;
    }

    private boolean startsWithNumber(String input) {
        byte start = (byte) input.charAt(0);
        return start > 47 && start < 58;
    }

    private boolean endsWithNumber(String input) {
        byte start = (byte) input.charAt(input.length() - 1);
        return start > 47 && start < 58;
    }

    public Figure getFigure(String name) {
        Figure.Type type = Figure.Type.fromBoardSymbol(name);
        switch (type) {
            case KING:
                return new King();
            case QUEEN:
                return new Queen();
            case ROOK:
                return new Rook();
            case BISHOP:
                return new Bishop();
            case KNIGHT:
                return new Knight();
        }
        throw new IllegalArgumentException("There is no figure with such notation: " + name);
    }
}
