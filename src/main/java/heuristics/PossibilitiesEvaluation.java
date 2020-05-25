package heuristics;

import engine.Board;

import static constances.Constances.*;

public class PossibilitiesEvaluation extends Evaluation {


    public PossibilitiesEvaluation(char maximizingSymbol, char minimizingSymbol) {
        super(maximizingSymbol, minimizingSymbol);

    }


    @Override
    public int evaluate(Board board) {

        return  series(maximizingSymbol, board) - (series(minimizingSymbol, board) * 2);

    }


    private int series(char symbol, Board boardObj) {

        char[][] board = boardObj.getBoard();
        int points = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (symbol != board[i][j] && symbol != BLANK_SYMBOL) {
                    continue;
                }

                points = points + checkPoints(board, symbol, i, j);


            }
        }
        return points;

    }

    private int checkPoints(char[][] board, char symbol, int i, int j) {

        int points = 0;

        if (j + 3 < board[0].length) {

            if ((board[i][j + 1] == symbol || board[i][j + 1] == BLANK_SYMBOL) &&
                    (board[i][j + 2] == symbol || board[i][j + 2] == BLANK_SYMBOL) &&
                    (board[i][j + 3] == symbol || board[i][j + 3] == BLANK_SYMBOL)) {

                int series = 0;
                if (board[i][j] == symbol) {
                    series += 1;
                }
                if (board[i][j + 1] == symbol) {
                    series += 1;
                }
                if (board[i][j + 2] == symbol) {
                    series += 1;
                }
                if (board[i][j + 3] == symbol) {
                    series += 1;
                }

                points = points + rowValue(series);
            }


        }


        if (i + 3 < board.length) {

            if ((board[i + 1][j] == symbol || board[i + 1][j] == BLANK_SYMBOL) &&
                    (board[i + 2][j] == symbol || board[i + 2][j] == BLANK_SYMBOL) &&
                    (board[i + 3][j] == symbol || board[i + 3][j] == BLANK_SYMBOL)) {

                int series = 0;
                if (board[i][j] == symbol) {
                    series += 1;
                }
                if (board[i + 1][j] == symbol) {
                    series += 1;
                }
                if (board[i + 2][j] == symbol) {
                    series += 1;
                }
                if (board[i + 3][j] == symbol) {
                    series += 1;
                }

                points = points + rowValue(series);
            }


            if (j + 3 < board[0].length){

                if ((board[i + 1][j + 1] == symbol || board[i + 1][j + 1] == BLANK_SYMBOL) &&
                        (board[i + 2][j + 2] == symbol || board[i + 2][j + 2] == BLANK_SYMBOL) &&
                        (board[i + 3][j + 3] == symbol || board[i + 3][j + 3] == BLANK_SYMBOL)) {

                    int series = 0;
                    if (board[i][j] == symbol) {
                        series += 1;
                    }
                    if (board[i + 1][j + 1] == symbol) {
                        series += 1;
                    }
                    if (board[i + 2][j + 2] == symbol) {
                        series += 1;
                    }
                    if (board[i + 3][j + 3] == symbol) {
                        series += 1;
                    }

                    points = points + rowValue(series);
                }

            }



            if (j - 3 >= 0) {

                if ((board[i + 1][j - 1] == symbol || board[i + 1][j - 1] == BLANK_SYMBOL) &&
                        (board[i + 2][j - 2] == symbol || board[i + 2][j - 2] == BLANK_SYMBOL) &&
                        (board[i + 3][j - 3] == symbol || board[i + 3][j - 3] == BLANK_SYMBOL)) {

                    int series = 0;
                    if (board[i][j] == symbol) {
                        series += 1;
                    }
                    if (board[i + 1][j - 1] == symbol) {
                        series += 1;
                    }
                    if (board[i + 2][j - 2] == symbol) {
                        series += 1;
                    }
                    if (board[i + 3][j - 3] == symbol) {
                        series += 1;
                    }

                    points = points + rowValue(series);
                }

            }

        }

        return points;

    }

    private int rowValue(int row) {
        if (row == 0) {
            return 0;
        } else if (row == 1) {
            return SERIES_1_WEIGHT;
        } else if (row == 2) {
            return SERIES_2_WEIGHT;
        } else if (row == 3) {
            return SERIES_3_WEIGHT;
        } else {
            return SERIES_4_WEIGHT;
        }
    }

}
