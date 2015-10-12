package com.base;

/**
 * Logic of work with the board.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class BoardOperations {
    public static final int MARKER = 1;

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++ ) {
            for (int j = 0 ; j < board.length; j ++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
