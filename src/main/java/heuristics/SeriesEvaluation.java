package heuristics;

import engine.Board;

import static constances.Constances.*;

public class SeriesEvaluation extends Evaluation {


    public SeriesEvaluation(char maximizingSymbol, char minimizingSymbol) {
        super(maximizingSymbol, minimizingSymbol);

    }


    @Override
    public int evaluate(Board board) {

        return (series(4, maximizingSymbol, board) * SERIES_4_WEIGHT) +
                (series(3, maximizingSymbol, board) * SERIES_3_WEIGHT) +
                (series(2, maximizingSymbol, board) * SERIES_2_WEIGHT) +
                (series(1, maximizingSymbol, board) * SERIES_1_WEIGHT) -
                (series(4, minimizingSymbol, board) * SERIES_4_WEIGHT * 2) -
                (series(3, maximizingSymbol, board) * SERIES_3_WEIGHT * 2) -
                (series(2, maximizingSymbol, board) * SERIES_2_WEIGHT * 2) -
                (series(1, maximizingSymbol, board) * SERIES_1_WEIGHT * 2);

    }

    private int series(int series, char symbol, Board boardObj) {

        char[][] board = boardObj.getBoard();
        int value = 0;
        int row;


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (symbol != board[i][j]) {
                    continue;
                }


                if (j + series - 1 < board[0].length) {
                    row = 1;
                    for (int k = 1; k < series; k++) {
                        if (symbol != board[i][j + k]) {
                            break;
                        }
                        row++;
                    }
                    if (row == series) {
                        value++;
                    }

                }


                if (i + series - 1 < board.length) {
                    row = 1;
                    for (int k = 1; k < series; k++) {
                        if (symbol != board[i + k][j]) {
                            break;
                        }
                        row++;
                    }
                    if (row == series) {
                        value++;
                    }

                    if (j + series - 1 < board[0].length) {
                        row = 1;
                        for (int k = 1; k < series; k++) {
                            if (symbol != board[i + k][j + k]) {
                                break;
                            }
                            row++;
                        }
                        if (row == series) {
                            value++;
                        }

                    }


                    if (j - series - 1 >= 0) {
                        row = 1;
                        for (int k = 1; k < series; k++) {
                            if (symbol != board[i + k][j - k]) {
                                break;
                            }
                            row++;
                        }
                        if (row == series) {
                            value++;
                        }
                    }
                }


            }
        }
        return value;
    }

}
