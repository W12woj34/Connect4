package heuristics;

import engine.Board;

public class BoardWeightEvaluation extends Evaluation {


    private int[][] evaluationTable = {{3, 4, 5, 7, 5, 4, 3},
            {4, 6, 8, 10, 8, 6, 4},
            {5, 8, 11, 13, 11, 8, 5},
            {5, 8, 11, 13, 11, 8, 5},
            {4, 6, 8, 10, 8, 6, 4},
            {3, 4, 5, 7, 5, 4, 3}};

    public BoardWeightEvaluation(char maximizingSymbol, char minimizingSymbol) {
        super(maximizingSymbol, minimizingSymbol);

    }

    @Override
    public int evaluate(Board board) {

        if (winner(maximizingSymbol, board)) {
            return Integer.MAX_VALUE - 1;
        } else if (winner(minimizingSymbol, board)) {
            return Integer.MIN_VALUE + 1;
        }


        return points(maximizingSymbol, board.getBoard()) - (points(maximizingSymbol, board.getBoard()) * 2);


    }

    private int points(char symbol, char[][] board) {

        int value = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == symbol){
                    value += evaluationTable[i][j];
                }
            }

        return value;

    }

    private boolean winner(char symbol, Board boardObj) {

        char[][] board = boardObj.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (symbol != board[i][j]) {
                    continue;
                }


                if (j + 3 < board[0].length &&
                        symbol == board[i][j + 1] &&
                        symbol == board[i][j + 2] &&
                        symbol == board[i][j + 3]) {
                    return true;
                }

                if (boardObj.checkIfWin(board, symbol, i, j)) {
                    return true;
                }
            }
        }

        return false;

    }

}
