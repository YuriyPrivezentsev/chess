package com.base.logic;

import com.base.board.BoardFactory;
import com.base.board.FigureBoard;
import com.base.figures.Figure;
import com.base.output.GenericResultProcessor;
import com.base.output.ResultProcessor;

import java.util.Deque;

/**
 * Common processor functionality implementation.
 *
 * @author Yuriy Privezentsev
 * @since 10/20/2015
 */
public abstract class AbstractProcessor implements Processor {
    protected final BoardFactory boardFactory;
    protected final Deque<Figure> figures;
    private BoardFactory.FigureBoardType figureBoardType = BoardFactory.FigureBoardType.TREE;
    private ResultProcessor resultProcessor = new GenericResultProcessor();

    public AbstractProcessor(int width, int height, Deque<Figure> figures) {
        this.boardFactory = new BoardFactory(width, height);
        this.figures = figures;
    }

    @Override
    public BoardFactory.FigureBoardType getFigureBoardType() {
        return figureBoardType;
    }

    @Override
    public void setFigureBoardType(BoardFactory.FigureBoardType figureBoardType) {
        this.figureBoardType = figureBoardType;
    }

    @Override
    public ResultProcessor getResultProcessor() {
        return resultProcessor;
    }

    @Override
    public void setResultProcessor(ResultProcessor resultProcessor) {
        this.resultProcessor = resultProcessor;
    }

    protected boolean isTrivialCase() {
        if (figures.isEmpty()
                || figures.size() > boardFactory.getTotalCellCount()) {
            getResultProcessor().addSummary(0);
            return true;
        }
        return false;
    }

}
