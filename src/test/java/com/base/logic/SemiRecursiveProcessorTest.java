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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Unit test for @link{SemiRecursiveProcessor}
 *
 * Unlike most of the tests on the project this is a white-box test using PrivilegedAccessor framework to invoke private
 * methods of the tested class. The testing of private methods is justified by the fact that tested class has a little of
 * public API but still incorporates complex logic.
 *
 * @author Yuriy Privezentsev
 * @since 11/24/2015
 */
public class SemiRecursiveProcessorTest {
    private static ProcessorBuilder processorBuilder;
    private SemiRecursiveProcessor processor;

    @Mock
    private ResultProcessor resultProcessor;

    @BeforeClass
    public static void setUp() throws Exception {
        processorBuilder = new ProcessorBuilder();
        processorBuilder.setResultProcessorFactory(new ResultProcessorFactory());
        processorBuilder.setProcessorType(ProcessorBuilder.ProcessorType.SEMI_RECURSIVE);
        processorBuilder.setResultProcessorType(ResultProcessorFactory.ResultProcessorType.GENERIC);
        processorBuilder.setFigureBoardType(BoardFactory.FigureBoardType.ARRAY);
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        processor = (SemiRecursiveProcessor) processorBuilder.fromString("2x2,1xQ");
        processor.setResultProcessor(resultProcessor);
    }

    @Test
    public void testPlaceLastFigure() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        BoardFactory boardFactory = new BoardFactory(2, 2);
        FigureBoard figureBoard = boardFactory.getFigureBoard();
        FreeCellsBoard freeCellsBoard = boardFactory.getFreeCellsBoard();
        Figure figure = new King();
        Position position = new Position(1, 0, freeCellsBoard);
        Object calculationState = getCalculationState(processor, figure, position);

        String placeFigure = "placeLastFigure(" +
                calculationState.getClass().getName() + "," +
                FigureBoard.class.getName() + "," +
                FreeCellsBoard.class.getName() + ")";
        PA.invokeMethod(processor, placeFigure, calculationState, figureBoard, freeCellsBoard);

        verify(resultProcessor, times(2)).addResult(any(FigureBoard.class));
    }

    private Object getCalculationState(SemiRecursiveProcessor processor, Figure figure, Position position)
            throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<?> calculationStateClass =
                Arrays.stream(processor.getClass().getDeclaredClasses())
                        .filter(aClass -> aClass.getName().contains("CalculationState")).findFirst().get();

        Class[] argumentTypes = {SemiRecursiveProcessor.class, Figure.class, Position.class};
        return PA.instantiate(calculationStateClass, argumentTypes,processor, figure, position);
    }

}
