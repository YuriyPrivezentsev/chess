package com.base.board;

/**
 * Factory to provide different implementations of the boards.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class BoardFactory {
    public enum FigureBoardType {
        ARRAY {
            @Override
            FigureBoard buildBoard(int width, int height) {
                return new ArrayFigureBoard(width, height);
            }
        },
        TREE {
            @Override
            FigureBoard buildBoard(int width, int height) {
                return new TreeFigureBoard(width, height);
            }
        };

        abstract FigureBoard buildBoard(int width, int height);
    }

    public enum FreeCellsBoardType{
        TREE {
            @Override
            FreeCellsBoard buildBoard(int width, int height) {
                return new FreeCellsBoard(width, height);
            }
        };
        abstract FreeCellsBoard buildBoard(int width, int height);
    }

    private final int width;
    private final int height;

    public BoardFactory(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Create new FigureBoard of default implementation
     * @return - new FigureBoard instance
     */
    public FigureBoard getFigureBoard(){
        return getFigureBoard(FigureBoardType.TREE);
    }

    /**
     * Create new FigureBoard according to desired implementation
     * @param implementation - implementation type
     * @return - new FigureBoard instance
     */
    public FigureBoard getFigureBoard(FigureBoardType implementation) {
        return implementation.buildBoard(width, height);
    }

    /**
     * Create new FreeCellsBoard of default implementation
     * @return - new FreeCellsBoard instance
     */
    public FreeCellsBoard getFreeCellsBoard(){
        return getFreeCellsBoard(FreeCellsBoardType.TREE);
    }

    /**
     * Create new FreeCellsBoard according to desired implementation
     * @param implementation - implementation type
     * @return - new FreeCellsBoard instance
     */
    public FreeCellsBoard getFreeCellsBoard(FreeCellsBoardType implementation) {
        return implementation.buildBoard(width, height);
    }

    public int getTotalCellCount(){
        return width * height;
    }
}
