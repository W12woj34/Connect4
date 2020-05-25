package algorithms;

import engine.Board;
import heuristics.Evaluation;

import java.util.List;

public class AlphaBeta extends Algorithm {



    public AlphaBeta(Evaluation evaluation, int depth, char maximizingPlayerSymbol, char minimizingPlayerSymbol) {
        super(evaluation, depth, maximizingPlayerSymbol, minimizingPlayerSymbol);

    }

    @Override
    public int chooseColumn(Board board, boolean isMaximizing) {
        return alphaBeta(board, depth, isMaximizing, Integer.MIN_VALUE, Integer.MAX_VALUE).getColumn();
    }

    private Move alphaBeta(Board board, int depth, boolean maximizingPlayer, int alpha, int beta) {

        if (depth == 0 || board.checkForWinner() || board.isFull()) {
            return new Move(null, evaluation.evaluate(board));
        }
        int value;
        Integer chosenColumn = null;
        char symbol;
        if (maximizingPlayer) {
            value = Integer.MIN_VALUE;
            symbol = maximizingPlayerSymbol;
        } else {
            value = Integer.MAX_VALUE;
            symbol = minimizingPlayerSymbol;
        }

        List<Integer> possibleColumns = board.possibleColumns();
        for (int i : possibleColumns) {
            Board copy = new Board(board);

            copy.insertSymbol(i, symbol);
            int newValue = alphaBeta(copy, depth - 1, !maximizingPlayer, alpha, beta).getValue();

            if (maximizingPlayer && value < newValue || !maximizingPlayer && value > newValue) {
                chosenColumn = i;
                value = newValue;
            }
            if (maximizingPlayer) {
                alpha = Math.max(alpha, value);
            } else {
                beta = Math.min(beta, value);
            }
            if(alpha >= beta){
                break;
            }

        }

        return new Move(chosenColumn, value);

    }



}
