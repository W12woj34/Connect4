package heuristics;

import engine.Board;

public abstract class Evaluation {

    char maximizingSymbol;
    char minimizingSymbol;

    public Evaluation(char maximizingSymbol, char minimizingSymbol) {
        this.maximizingSymbol = maximizingSymbol;
        this.minimizingSymbol = minimizingSymbol;
    }

    public abstract int evaluate(Board board);


}
