package com.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logic of work with the board.
 *
 * @author Yuriy Privezentsev
 * @since 10/12/2015
 */
public class BoardOperations {
    private static final Logger LOG = LoggerFactory.getLogger(BoardOperations.class);
    public static final int TAKEN = 1;

    public static void printBoard(int[][] board) {
        StringBuilder boardRepresentation = new StringBuilder();
        for (int i = 0; i < board.length; i++ ) {
            boardRepresentation.append("\r\n");
            for (int j = 0 ; j < board.length; j ++){
                boardRepresentation.append(board[i][j]).append(" ");
            }
        }
        LOG.info(boardRepresentation.toString());
    }
}
