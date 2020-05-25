package utils;

import controllers.GuiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import players.Player;

public class Printer {

    public void printBoard(char[][] board) {

        for (char[] chars : board) {
            System.out.print('|');
            for (char aChar : chars) {
                System.out.print(aChar);
                System.out.print('|');
            }
            System.out.println();
        }
        System.out.println(" 0 1 2 3 4 5 6 ");

        System.out.println();
        System.out.println();
    }

    public void printWinner(Player winner) {


        if (winner == null) {
            System.out.println("Game result: Draw");

        } else {
            System.out.println("Game result: " + winner.getName() + " win!");
        }
        System.out.println();
    }


}
