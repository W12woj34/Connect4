package players;

import controllers.GuiController;
import engine.Board;
import javafx.scene.paint.Color;
import utils.Input;

import static java.lang.Thread.sleep;

public class UserPlayer extends Player {

    Input input;

    public UserPlayer(char symbol, Color color, boolean isFirst, GuiController controller) {
        super(symbol, color, isFirst, controller);
        input = new Input();


    }


    @Override
    public void makeMove(Board board) {
        String fieldId;
        while (true) {
            controller.setChosenColumn(-1);
            while (controller.getChosenColumn() == -1) {
                try {
                    sleep(100);
               } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
              //  int column = input.readColumn();
                int column = controller.getChosenColumn();
                fieldId = board.insertSymbol(column, symbol);
                controller.setChosenColumn(-2);
               break;

            } catch (RuntimeException ignore) {
                System.out.println("ERROR");
            }


        }
        setField(fieldId, color);

    }

}
