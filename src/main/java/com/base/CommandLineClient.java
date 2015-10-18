package com.base;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Generic command-line client
 *
 * @author Yuriy Privezentsev
 * @since 10/18/2015
 */
public class CommandLineClient {

    public static final String BOARD_PROMPT = "Enter the parameters in form MxN,FxQ,FxQ,..." +
            "\r\n where M and N are desk dimensions, F is figure name of Q,K,N,R,B and Qa if quantity" +
            "\r\n or type EXIT for termination.";

    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("[1-9][0-9]*X[1-9][0-9]*[(,[Q,K,N,B,R]X[1-9][0-9]*),(,[1-9][0-9]*X[Q,K,N,B,R])]+");
        System.out.println(BOARD_PROMPT);
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().replace(" ", "").toUpperCase();
        while (!input.equals("EXIT")) {
            if (pattern.matcher(input).matches()) {
                ProcessorBuilder processorBuilder = new ProcessorBuilder();
                Processor processor = processorBuilder.fromString(input);
                processor.process();
            } else {
                System.out.println("Wrong input format!");
                System.out.println(BOARD_PROMPT);
            }
            System.out.print(">");
            input = scanner.next().replace(" ", "").toUpperCase();
        }
    }
}
