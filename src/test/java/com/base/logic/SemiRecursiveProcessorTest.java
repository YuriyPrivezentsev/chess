package com.base.logic;

import com.base.board.BoardFactory;
import com.base.board.FigureBoard;
import com.base.board.FreeCellsBoard;
import com.base.board.Position;
import com.base.figures.Figure;
import com.base.figures.King;
import com.base.output.ResultProcessor;
import com.base.output.ResultProcessorFactory;
import junit.extensions.PA;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit test for @link{SemiRecursiveProcessor}
 * <p>
 * Unlike most of the tests on the project this is a white-box test using PrivilegedAccessor framework to invoke private
 * methods of the tested class. The testing of private methods is justified by the fact that tested class has a little of
 * public API but still incorporates complex logic.
 *
 * @author Yuriy Privezentsev
 * @since 11/24/2015
 */
@SuppressWarnings("unchecked")
public class SemiRecursiveProcessorTest {
    private static ProcessorBuilder processorBuilder;

    private SemiRecursiveProcessor processor;
    private FigureBoard figureBoard;
    private FreeCellsBoard freeCellsBoard;

    @BeforeClass
    public static void setUp() throws Exception {
        processorBuilder = new ProcessorBuilder();
        processorBuilder.setResultProcessorFactory(new ResultProcessorFactory());
        processorBuilder.setProcessorType(ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
        processorBuilder.setResultProcessorType(ResultProcessorFactory.ResultProcessorType.GENERIC);
        processorBuilder.setFigureBoardType(BoardFactory.FigureBoardType.ARRAY);
    }

    @Before
    public void  init(){
        processor = (SemiRecursiveProcessor) processorBuilder.fromString("3x3,2xK,1xR");

        BoardFactory boardFactory = new BoardFactory(3, 3);
        figureBoard = boardFactory.getFigureBoard();
        freeCellsBoard = boardFactory.getFreeCellsBoard();
    }


    @Test
    public void testPlaceLastFigure() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Figure figure = new King();
        ResultProcessor resultProcessor = mock(ResultProcessor.class);
        processor.setResultProcessor(resultProcessor);
        Position position = new Position(2, 0, freeCellsBoard);
        Object calculationState = getCalculationState(processor, figure, position);

        String placeFigure = "placeLastFigure(" +
                calculationState.getClass().getName() + "," +
                FigureBoard.class.getName() + "," +
                FreeCellsBoard.class.getName() + ")";
        PA.invokeMethod(processor, placeFigure, calculationState, figureBoard, freeCellsBoard);

        verify(resultProcessor, times(3)).addResult(any(FigureBoard.class));
    }

    @Test
    public void testProcessNextState() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Deque<Figure> figures = (Deque<Figure>) PA.getValue(processor, "figures");

        Figure rook = figures.pop();
        rook.setPosition(new Position(0, 1, figureBoard));
        Collection<Position> positions = rook.placeOnBoard(figureBoard);
        figureBoard.addFigure(rook);
        freeCellsBoard.occupyCells(positions);

        Figure king = new King();
        Position position = new Position(2, 0, freeCellsBoard);
        Object calculationState = getCalculationState(processor, king, position);

        String signature = "processNextState(" +
                calculationState.getClass().getName() + "," +
                FigureBoard.class.getName() + "," +
                FreeCellsBoard.class.getName() + ")";
        Object resultState = PA.invokeMethod(processor, signature, calculationState, figureBoard, freeCellsBoard);

        Figure resultFigure = (Figure) PA.getValue(resultState, "figure");
        Position freeCell = (Position) PA.getValue(resultState, "freeCell");

        assertTrue(resultFigure.isSameType(new King()));
        assertEquals(2, freeCell.getLine());
        assertEquals(2, freeCell.getColumn());
    }

    @Test
    public void testGetNextValidState() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Deque<Figure> figures = (Deque<Figure>) PA.getValue(processor, "figures");
        Deque<Figure> processedFigures = (Deque<Figure>) PA.getValue(processor, "processedFigures");
        Deque<Collection<Position>> processedPositions = (Deque<Collection<Position>>) PA.getValue(processor, "processedPositions");

        Figure rook = figures.pop();
        rook.setPosition(new Position(0, 0, figureBoard));
        Collection<Position> rookPositions = rook.placeOnBoard(figureBoard);
        figureBoard.addFigure(rook);
        processedFigures.push(rook);
        Collection<Position> realRookCoverage = freeCellsBoard.occupyCells(rookPositions);
        processedPositions.push(realRookCoverage);

        Figure king = figures.pop();
        king.setPosition(new Position(1, 2, figureBoard));
        Collection<Position> kingPositions = king.placeOnBoard(figureBoard);
        figureBoard.addFigure(king);
        processedFigures.push(king);
        Collection<Position> realKingCoverage = freeCellsBoard.occupyCells(kingPositions);
        processedPositions.push(realKingCoverage);

        Figure secondKing = figures.pop();
        Object calculationState = getCalculationState(processor, secondKing, null);
        String signature = "getNextValidState(" +
                calculationState.getClass().getName() + "," +
                FigureBoard.class.getName() + "," +
                FreeCellsBoard.class.getName() + ")";
        Object resultState = PA.invokeMethod(processor, signature, calculationState, figureBoard, freeCellsBoard);

        Figure resultFigure = (Figure) PA.getValue(resultState, "figure");
        Position freeCell = (Position) PA.getValue(resultState, "freeCell");

        assertEquals(king, resultFigure);
        assertEquals(1, processedFigures.size());
        assertEquals(2, freeCell.getLine());
        assertEquals(1, freeCell.getColumn());
    }

    @Test
    public void testPlaceFigure() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Deque<Figure> figures = (Deque<Figure>) PA.getValue(processor, "figures");

        Figure rook = figures.pop();
        Position initialPosition = new Position(0, 0, figureBoard);
        rook.setPosition(initialPosition);
        Collection<Position> coverage = rook.placeOnBoard(figureBoard);

        Object calculationState = getCalculationState(processor, rook, initialPosition);
        String signature = "placeFigure(" +
                calculationState.getClass().getName() + "," +
                Collection.class.getName() + "," +
                FigureBoard.class.getName() + "," +
                FreeCellsBoard.class.getName() + ")";
        Object resultState = PA.invokeMethod(processor, signature, calculationState, coverage, figureBoard, freeCellsBoard);


        Figure resultFigure = (Figure) PA.getValue(resultState, "figure");
        Position freeCell = (Position) PA.getValue(resultState, "freeCell");

        assertEquals(Figure.Type.KING, resultFigure.getType());
        assertEquals(1, freeCell.getLine());
        assertEquals(1, freeCell.getColumn());

        Deque<Figure> processedFigures = (Deque<Figure>) PA.getValue(processor, "processedFigures");
        assertEquals(1, processedFigures.size());
        assertTrue(processedFigures.peek().isSameType(rook));

        Deque<Collection<Position>> processedPositions = (Deque<Collection<Position>>) PA.getValue(processor, "processedPositions");
        assertEquals(1, processedPositions.size());

        assertEquals(1, figures.size());
        assertEquals(Figure.Type.KING, figures.peek().getType());
    }

    private Object getCalculationState(SemiRecursiveProcessor processor, Figure figure, Position position)
            throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<?> calculationStateClass =
                Arrays.stream(processor.getClass().getDeclaredClasses())
                        .filter(aClass -> aClass.getName().contains("CalculationState")).findFirst().get();

        Class[] argumentTypes = {SemiRecursiveProcessor.class, Figure.class, Position.class};
        return PA.instantiate(calculationStateClass, argumentTypes, processor, figure, position);
    }

}
