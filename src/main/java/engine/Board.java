package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static constances.Constances.*;

public class Board {

    char[][] board;

    public Board() {

        board = new char[BOARD_ROW_NUMBER][BOARD_COLUMN_NUMBER];
        for (char[] chars : board) {
            Arrays.fill(chars, BLANK_SYMBOL);
        }
    }

    public Board(Board board) {

        this.board = new char[BOARD_ROW_NUMBER][BOARD_COLUMN_NUMBER];
        for (int i = 0; i < board.board.length; i++) {
            System.arraycopy(board.board[i], 0, this.board[i], 0, board.board[i].length);
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public List<Integer> possibleColumns() {

        List<Integer> possibleColumns = new ArrayList<>();

        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == BLANK_SYMBOL) {
                possibleColumns.add(i);
            }
        }
        return possibleColumns;

    }

    public boolean checkForWinner() {


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char symbol = board[i][j];
                if (symbol == BLANK_SYMBOL) {
                    continue;
                }

                if (checkIfWin(board, symbol, i, j)) {
                    return true;
                }


            }
        }
        return false;

    }

    public boolean checkIfWin(char[][] board, char symbol, int i, int j) {


        if (j + 3 < board[0].length &&
                symbol == board[i][j + 1] &&
                symbol == board[i][j + 2] &&
                symbol == board[i][j + 3]) {
            return true;
        }


        if (i + 3 < board.length) {

            if (symbol == board[i + 1][j] &&
                    symbol == board[i + 2][j] &&
                    symbol == board[i + 3][j]) {
                return true;
            }


            if (j + 3 < board[0].length &&
                    symbol == board[i + 1][j + 1] &&
                    symbol == board[i + 2][j + 2] &&
                    symbol == board[i + 3][j + 3]) {
                return true;
            }


            if (j - 3 >= 0 &&
                    symbol == board[i + 1][j - 1] &&
                    symbol == board[i + 2][j - 2] &&
                    symbol == board[i + 3][j - 3]) {
                return true;
            }
        }

        return false;

    }

    public boolean isFull() {

        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == BLANK_SYMBOL) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmpty() {

        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar != BLANK_SYMBOL) {
                    return false;
                }
            }
        }
        return true;
    }

    public String insertSymbol(int column, char symbol) {

        for (int i = 0; i < board.length; i++) {

            if (i == board.length - 1 && board[i][column] == BLANK_SYMBOL) {
                board[i][column] = symbol;
                return String.valueOf(i) + column;
            }

            if (board[i][column] != BLANK_SYMBOL) {

                i -= 1;

                if (i >= 0) {
                    board[i][column] = symbol;
                    return String.valueOf(i) + column;
                } else {
                    throw new RuntimeException();
                }

            }
        }
        throw new RuntimeException();

    }

}
