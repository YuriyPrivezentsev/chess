package com.base.client;

import com.base.board.BoardFactory;
import com.base.logic.Processor;
import com.base.logic.ProcessorBuilder;
import com.base.output.ResultProcessorFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Generic command-line client
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class CommandLineClient {
    public static final String BOARD_PROMPT = "Enter the parameters in form MxN,FxQ,FxQ,..." +
            "\r\n where M and N are desk dimensions, F is figure name of Q,K,N,R,B and Q is quantity" +
            "\r\n or type EXIT for termination.";
    private static final String PARAMETERS_INFO = "Current processor uses %s result board type, and %s processor type, and %s outpur.";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String CONFIGURATION_COMMAND = "CONFIGURATION";

    public static void main(String[] args) throws IOException {
        ResultProcessorFactory resultProcessorFactory = new ResultProcessorFactory();
        if (args.length == 1) {
            resultProcessorFactory.setCsvFileName(args[0]);
        }

        Pattern pattern = Pattern.compile("[1-9][0-9]*X[1-9][0-9]*[(,[Q,K,N,B,R]X[1-9][0-9]*),(,[1-9][0-9]*X[Q,K,N,B,R])]+");

        System.out.println(BOARD_PROMPT);
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().replace(" ", "").toUpperCase();
        ProcessorBuilder processorBuilder = new ProcessorBuilder();
        processorBuilder.setResultProcessorFactory(resultProcessorFactory);
        while (!input.equals(EXIT_COMMAND)) {
            if (input.equals(CONFIGURATION_COMMAND)) {
                System.out.println(String.format(PARAMETERS_INFO,
                        processorBuilder.getFigureBoardType(),
                        processorBuilder.getProcessorType(),
                        processorBuilder.getResultProcessorType()));
                processorBuilder = getNewProcessorBuilder(scanner);
                processorBuilder.setResultProcessorFactory(resultProcessorFactory);
            } else if (pattern.matcher(input).matches()) {
                calculate(input, processorBuilder);
            } else {
                System.out.println("Wrong input format!");
                System.out.println(BOARD_PROMPT);
            }
            System.out.print(">");
            input = scanner.next().replace(" ", "").toUpperCase();
        }
    }

    private static void calculate(String input, ProcessorBuilder processorBuilder) {
        Processor processor = processorBuilder.fromString(input);
        processor.setFigureBoardType(BoardFactory.FigureBoardType.ARRAY);
        processor.process();
    }

    private static ProcessorBuilder getNewProcessorBuilder(Scanner scanner) {
        System.out.println("Pick result board type");

        ProcessorBuilder processorBuilder = new ProcessorBuilder();

        processorBuilder.setFigureBoardType(new EnumExtractor<BoardFactory.FigureBoardType>() {
            @Override
            BoardFactory.FigureBoardType[] getValues() {
                return BoardFactory.FigureBoardType.values();
            }
        }.getType(scanner));

        processorBuilder.setProcessorType(new EnumExtractor<ProcessorBuilder.ProcessorType>() {
            @Override
            ProcessorBuilder.ProcessorType[] getValues() {
                return ProcessorBuilder.ProcessorType.values();
            }
        }.getType(scanner));

        processorBuilder.setResultProcessorType(new EnumExtractor<ResultProcessorFactory.ResultProcessorType>() {
            @Override
            ResultProcessorFactory.ResultProcessorType[] getValues() {
                return ResultProcessorFactory.ResultProcessorType.values();
            }
        }.getType(scanner));

        return processorBuilder;
    }


    private static abstract class EnumExtractor<T extends Enum>{
        private static final Pattern NUBER_PATTERN = Pattern.compile("[1-9][0-9]*");
        T getType(Scanner scanner) {
            Stream.of(getValues()).forEach(type -> System.out.println(type.ordinal() + ". " + type));
            String input = readInputNumber(scanner);
            return getValues()[Integer.parseInt(input)];
        }

        abstract T[] getValues();

        private String readInputNumber(Scanner scanner) {
            System.out.print(">");
            String input = scanner.nextLine();
            while ((NUBER_PATTERN.matcher(input).matches() && Integer.parseInt(input) > getValues().length)
                    || !NUBER_PATTERN.matcher(input).matches()) {
                System.out.println("Wrong number, take another peek");
                System.out.print(">");
                input = scanner.nextLine();
            }
            return input;
        }
    }
}
