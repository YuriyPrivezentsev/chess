package com.base.logic;

import com.base.board.BoardFactory;
import com.base.board.FigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import com.base.figures.Figure;
import com.base.figures.King;
import com.base.figures.Rook;
import com.base.output.ResultProcessor;
import com.base.output.ResultProcessorFactory;
import junit.extensions.PA;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit test for @link{RecursiveProcessor}
 * <p>
 * Unlike most of the tests on the project this is a white-box test using PrivilegedAccessor framework to invoke private
 * methods of the tested class. The testing of private methods is justified by the fact that tested class has a little of
 * public API but still incorporates complex logic.
 *
 * @author Yuriy Privezentsev
 * @since 11/30/2015
 */
@SuppressWarnings("unchecked")
public class RecursiveProcessorTest {
    private static final String PLACE_FIGURE_SIGNATURE = "placeFigure(" +
            Figure.class.getName() + "," +
            Collection.class.getName() + "," +
            FigureBoard.class.getName() + "," +
            FreeCellsBoard.class.getName() + ")";
    private static ProcessorBuilder processorBuilder;

    private RecursiveProcessor processor;
    private FreeCellsBoard freeCellsBoard;
    private FigureBoard figureBoard;

    @Mock
    private ResultProcessor resultProcessor;

    @BeforeClass
    public static void setUp() throws Exception {
        processorBuilder = new ProcessorBuilder();
        processorBuilder.setResultProcessorFactory(new ResultProcessorFactory());
        processorBuilder.setProcessorType(ProcessorBuilder.ProcessorType.RECURSIVE);
        processorBuilder.setResultProcessorType(ResultProcessorFactory.ResultProcessorType.GENERIC);
        processorBuilder.setFigureBoardType(BoardFactory.FigureBoardType.ARRAY);
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        processor = (RecursiveProcessor) processorBuilder.fromString("3x3,2xK,1xR");
        processor.setResultProcessor(resultProcessor);
        BoardFactory boardFactory = new BoardFactory(3, 3);
        freeCellsBoard = boardFactory.getFreeCellsBoard();
        figureBoard = boardFactory.getFigureBoard(BoardFactory.FigureBoardType.ARRAY);
    }

    @Test
    public void testPlaceFigure() {
        Figure figure = new King();

        Position position = new Position(2, 0, freeCellsBoard);
        figure.setPosition(position);
        Collection<Position> coverage = figure.placeOnBoard(figureBoard);

        Deque<Figure> figures = (Deque<Figure>) PA.getValue(processor, "figures");
        figures.clear();

        PA.invokeMethod(processor, PLACE_FIGURE_SIGNATURE, figure, coverage, figureBoard, freeCellsBoard);

        verify(resultProcessor, times(1)).addResult(any(FigureBoard.class));
    }

    @Test
    public void testPlaceFigureReturn() {
        Deque<Figure> figures = (Deque<Figure>) PA.getValue(processor, "figures");
        Figure rook = figures.pop();
        Position rookPosition = new Position(0, 0, freeCellsBoard);
        rook.setPosition(rookPosition);
        freeCellsBoard.occupyCells(rook.placeOnBoard(figureBoard));
        figureBoard.addFigure(rook);

        Figure king = figures.pop();
        Position kingPosition = new Position(1, 2, figureBoard);
        king.setPosition(kingPosition);
        Collection<Position> kingCoverage = king.placeOnBoard(figureBoard);

        Collection<Position> realCoverage = (Collection<Position>) PA.invokeMethod(processor, PLACE_FIGURE_SIGNATURE, king, kingCoverage, figureBoard, freeCellsBoard);

        assertEquals(4, realCoverage.size());

        ArrayList<Position> resultPositions = new ArrayList<>(4);
        resultPositions.addAll(realCoverage);
        for(int line = 1, i=0; line <= 2; line++){
            for (int column = 1; column <= 2; column++, i++){
                Position position = resultPositions.get(i);
                assertEquals(line, position.getLine());
                assertEquals(column, position.getColumn());
            }
        }

        assertEquals(1, resultPositions.get(0).getLine());
        assertEquals(1, resultPositions.get(0).getLine());
    }


    @Test
    public void testTryCellUnsuccessful() {
        Position kingPosition = new Position(1, 1, freeCellsBoard);
        int expectedResultCount = 0;
        runTryCellTest(kingPosition, expectedResultCount);
    }

    @Test
    public void testTryCellSuccessful() {
        Position kingPosition = new Position(1, 2, freeCellsBoard);
        int expectedResultCount = 1;
        runTryCellTest(kingPosition, expectedResultCount);
    }


    private void runTryCellTest(Position kingPosition, int expectedResultCount) {
        Figure rook = new Rook();
        Position rookPosition = new Position(0, 0, freeCellsBoard);
        rook.setPosition(rookPosition);
        figureBoard.addFigure(rook);
        freeCellsBoard.occupyCells(rook.placeOnBoard(figureBoard));

        Figure king = new King();

        Deque<Figure> figures = (Deque<Figure>) PA.getValue(processor, "figures");
        figures.clear();

        String signature = "tryCell(" +
                Figure.class.getName() + "," +
                Position.class.getName() + "," +
                FigureBoard.class.getName() + "," +
                FreeCellsBoard.class.getName() + ")";


        PA.invokeMethod(processor, signature, king, kingPosition, figureBoard, freeCellsBoard);

        verify(resultProcessor, times(expectedResultCount)).addResult(any(FigureBoard.class));
    }

}
