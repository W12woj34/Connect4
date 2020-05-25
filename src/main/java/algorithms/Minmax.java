package algorithms;

import engine.Board;
import heuristics.Evaluation;

import java.util.List;

public class Minmax extends Algorithm {



    public Minmax(Evaluation evaluation, int depth, char maximizingPlayerSymbol, char minimizingPlayerSymbol) {
        super(evaluation, depth, maximizingPlayerSymbol, minimizingPlayerSymbol);
    }

    @Override
    public int chooseColumn(Board board, boolean isMaximizing) {
        return minmax(board, depth, isMaximizing).getColumn();
    }

    private Move minmax(Board board, int depth, boolean maximizingPlayer) {

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
            int newValue = minmax(copy, depth - 1, !maximizingPlayer).getValue();

            if (maximizingPlayer && value < newValue || !maximizingPlayer && value > newValue) {
                chosenColumn = i;
                value = newValue;
            }

        }

        return new Move(chosenColumn, value);

    }



}
