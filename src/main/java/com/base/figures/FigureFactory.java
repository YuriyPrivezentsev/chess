package com.base.figures;

import com.base.logic.ProcessorBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.StringTokenizer;

/**
 * Construct the figure from the input string
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class FigureFactory {
    private final static EnumMap<Figure.Type,FigureFactoryMethod> BUILDERS = new EnumMap<>(Figure.Type.class);
    static{
        BUILDERS.put(Figure.Type.KING, King::new);
        BUILDERS.put(Figure.Type.QUEEN, Queen::new);
        BUILDERS.put(Figure.Type.ROOK, Rook::new);
        BUILDERS.put(Figure.Type.BISHOP, Bishop::new);
        BUILDERS.put(Figure.Type.KNIGHT, Knight::new);
    }

    /**
     * Construct the collection of figures form the short notation
     *
     * @param input - string in form of figureSymbolXquantity or quantityXfigureSymbol
     * @return - collection of figure entities according to input string.
     */
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

    /**
     * Construct a figure from the symbol.
     *
     * @param name - figure chessboard notation symbol, K for king, Q for Queen, etc.
     */
    public Figure getFigure(String name) {
        Figure.Type type = Figure.Type.fromBoardSymbol(name);
        return BUILDERS.get(type).buildFigure();
    }

    private interface FigureFactoryMethod {
        Figure buildFigure();
    }
}