package com.base.board;

/**
 * Factory to provide different implementations of the boards.
 *
 * @author Yuriy Privezentsev
 * @since 10/19/2015
 */
public class BoardFactory {
    public enum FigureBoardType {
        ARRAY,
        TREE
    }

    public enum FreeCellsBoardType{
        TREE
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
        switch (implementation){
            case ARRAY:
                return new ArrayFigureBoard(width,height);
            case TREE:
                return new TreeFigureBoard(width,height);
            default:
                throw new IllegalArgumentException("Unsupported FigureBoard type " + implementation);
        }
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
        switch (implementation){
            case TREE:
                return new FreeCellsBoard(width,height);
            default:
                throw new IllegalArgumentException("Unsupported FreeCellsBoard type " + implementation);
        }
    }

    public int getTotalCellCount(){
        return width * height;
    }
}
