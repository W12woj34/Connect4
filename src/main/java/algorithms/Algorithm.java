package algorithms;

import engine.Board;
import heuristics.Evaluation;

public abstract class Algorithm {

    protected Evaluation evaluation;
    protected int depth;
    protected char maximizingPlayerSymbol;
    protected char minimizingPlayerSymbol;

    public Algorithm(Evaluation evaluation, int depth, char maximizingPlayerSymbol, char minimizingPlayerSymbol) {
        this.evaluation = evaluation;
        this.depth = depth;
        this.maximizingPlayerSymbol = maximizingPlayerSymbol;
        this.minimizingPlayerSymbol = minimizingPlayerSymbol;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public Integer getDepth() {
        return depth;
    }

    public abstract int chooseColumn(Board board, boolean isMaximizing);

    protected static class Move {

        Integer column;
        int value;

        public Move(Integer column, int value) {
            this.column = column;
            this.value = value;
        }




        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
