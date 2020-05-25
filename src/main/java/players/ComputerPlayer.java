package players;

import algorithms.Algorithm;
import controllers.GuiController;
import engine.Board;
import javafx.scene.paint.Color;

import java.util.Random;

public class ComputerPlayer extends Player {

    Algorithm algorithm;
    Random random;

    public ComputerPlayer(char symbol, Color color, boolean isFirst, Algorithm algorithm, GuiController controller) {
        super(symbol, color, isFirst, controller);
        this.algorithm = algorithm;
        this.random = new Random();
    }

    @Override
    public void makeMove(Board board) {

        String fieldId;
        if (board.isEmpty()) {
            fieldId = board.insertSymbol(random.nextInt(7), symbol);
        } else {
            fieldId = board.insertSymbol(algorithm.chooseColumn(board, isFirst), symbol);
        }

        setField(fieldId, color);

    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
}
