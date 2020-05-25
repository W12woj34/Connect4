import algorithms.Algorithm;
import algorithms.AlphaBeta;
import algorithms.Minmax;
import engine.Engine;
import heuristics.BoardWeightEvaluation;
import heuristics.Evaluation;
import heuristics.SeriesEvaluation;
import heuristics.PossibilitiesEvaluation;
import javafx.scene.paint.Color;
import players.ComputerPlayer;
import players.Player;
import players.UserPlayer;
import utils.DataWriter;
import utils.ResultData;

import java.util.ArrayList;
import java.util.List;

import static constances.Constances.*;

public class Main {

    //additional main for research purposes
    public static void main(String[] args) {

        List<ResultData> results = new ArrayList<>();

        char firstPlayer = PLAYER1_SYMBOL;
        char secondPlayer = PLAYER2_SYMBOL;
        Color firstPlayerColor = PLAYER1_COLOR;
        Color secondPlayerColor = PLAYER2_COLOR;
        int player1Depth = 1;
        int player2Depth = 1;

        Evaluation evaluation1 = new SeriesEvaluation(firstPlayer, secondPlayer);
        Evaluation evaluation2 = new PossibilitiesEvaluation(firstPlayer, secondPlayer);
        Evaluation evaluation3 = new BoardWeightEvaluation(firstPlayer, secondPlayer);

        /*
        Algorithm algorithm1AB_E4 = new AlphaBeta(evaluation3, 7, firstPlayer, secondPlayer);
        Algorithm algorithm2AB_E4 = new AlphaBeta(evaluation3, 7, firstPlayer, secondPlayer);
        Player userPlayer = new UserPlayer(secondPlayer, secondPlayerColor, true, null);
        Player computer2MM_E4 = new ComputerPlayer(secondPlayer, secondPlayerColor, false, algorithm2AB_E4, null);

        Engine engine0 = new Engine();
        engine0.startGame(userPlayer, computer2MM_E4);

        */

        Engine engine = new Engine();

        for (int j = 0; j < 4; j++) {

            for (int k = 0; k < 4; k++) {


                Algorithm algorithm1MM_E1 = new Minmax(evaluation1, player1Depth, firstPlayer, secondPlayer);
                Algorithm algorithm2MM_E1 = new Minmax(evaluation1, player2Depth, firstPlayer, secondPlayer);
                Algorithm algorithm1AB_E1 = new AlphaBeta(evaluation1, player1Depth, firstPlayer, secondPlayer);
                Algorithm algorithm2AB_E1 = new AlphaBeta(evaluation1, player2Depth, firstPlayer, secondPlayer);

                Player computer1MM_E1 = new ComputerPlayer(firstPlayer, firstPlayerColor, true, algorithm1MM_E1, null);
                Player computer2MM_E1 = new ComputerPlayer(secondPlayer, secondPlayerColor, false, algorithm2MM_E1, null);
                Player computer1AB_E1 = new ComputerPlayer(firstPlayer, firstPlayerColor, true, algorithm1AB_E1, null);
                Player computer2AB_E1 = new ComputerPlayer(secondPlayer, secondPlayerColor, false, algorithm2AB_E1, null);


                Algorithm algorithm1MM_E2 = new Minmax(evaluation2, player1Depth, firstPlayer, secondPlayer);
                Algorithm algorithm2MM_E2 = new Minmax(evaluation2, player2Depth, firstPlayer, secondPlayer);
                Algorithm algorithm1AB_E2 = new AlphaBeta(evaluation2, player1Depth, firstPlayer, secondPlayer);
                Algorithm algorithm2AB_E2 = new AlphaBeta(evaluation2, player2Depth, firstPlayer, secondPlayer);

                Player computer1MM_E2 = new ComputerPlayer(firstPlayer, firstPlayerColor, true, algorithm1MM_E2, null);
                Player computer2MM_E2 = new ComputerPlayer(secondPlayer, secondPlayerColor, false, algorithm2MM_E2, null);
                Player computer1AB_E2 = new ComputerPlayer(firstPlayer, firstPlayerColor, true, algorithm1AB_E2, null);
                Player computer2AB_E2 = new ComputerPlayer(secondPlayer, secondPlayerColor, false, algorithm2AB_E2, null);

                Algorithm algorithm1MM_E3 = new Minmax(evaluation3, player1Depth, firstPlayer, secondPlayer);
                Algorithm algorithm2MM_E3 = new Minmax(evaluation3, player2Depth, firstPlayer, secondPlayer);
                Algorithm algorithm1AB_E3 = new AlphaBeta(evaluation3, player1Depth, firstPlayer, secondPlayer);
                Algorithm algorithm2AB_E3 = new AlphaBeta(evaluation3, player2Depth, firstPlayer, secondPlayer);

                Player computer1MM_E3 = new ComputerPlayer(firstPlayer, firstPlayerColor, true, algorithm1MM_E3, null);
                Player computer2MM_E3 = new ComputerPlayer(secondPlayer, secondPlayerColor, false, algorithm2MM_E3, null);
                Player computer1AB_E3 = new ComputerPlayer(firstPlayer, firstPlayerColor, true, algorithm1AB_E3, null);
                Player computer2AB_E3 = new ComputerPlayer(secondPlayer, secondPlayerColor, false, algorithm2AB_E3, null);


                List<ResultData> results1 = new ArrayList<>();
                List<ResultData> results2 = new ArrayList<>();
                List<ResultData> results3 = new ArrayList<>();
                List<ResultData> results4 = new ArrayList<>();
                List<ResultData> results5 = new ArrayList<>();
                List<ResultData> results6 = new ArrayList<>();
                List<ResultData> results7 = new ArrayList<>();
                List<ResultData> results8 = new ArrayList<>();
                List<ResultData> results9 = new ArrayList<>();
                List<ResultData> results10 = new ArrayList<>();
                List<ResultData> results11 = new ArrayList<>();
                List<ResultData> results12 = new ArrayList<>();
                List<ResultData> results13 = new ArrayList<>();
                List<ResultData> results14 = new ArrayList<>();
                List<ResultData> results15 = new ArrayList<>();
                List<ResultData> results16 = new ArrayList<>();
                List<ResultData> results17 = new ArrayList<>();
                List<ResultData> results18 = new ArrayList<>();


                for (int i = 0; i < 50; i++) {
                    System.out.println(i + " " + j + " " + k);
                    System.out.println(1);
                    results1.add(engine.startGame(computer1MM_E1, computer2MM_E1));
                    results2.add(engine.startGame(computer1MM_E1, computer2AB_E1));
                    results3.add(engine.startGame(computer1AB_E1, computer2MM_E1));
                    results4.add(engine.startGame(computer1AB_E1, computer2AB_E1));

                    System.out.println(2);
                    results5.add(engine.startGame(computer1MM_E2, computer2MM_E2));
                    results6.add(engine.startGame(computer1MM_E2, computer2AB_E2));
                    results7.add(engine.startGame(computer1AB_E2, computer2MM_E2));
                    results8.add(engine.startGame(computer1AB_E2, computer2AB_E2));

                    System.out.println(3);
                    results9.add(engine.startGame(computer1MM_E3, computer2MM_E3));
                    results10.add(engine.startGame(computer1MM_E3, computer2AB_E3));
                    results11.add(engine.startGame(computer1AB_E3, computer2MM_E3));
                    results12.add(engine.startGame(computer1AB_E3, computer2AB_E3));

                    System.out.println(4);
                    results13.add(engine.startGame(computer1AB_E1, computer2AB_E2));
                    results14.add(engine.startGame(computer1AB_E1, computer2AB_E3));
                    results15.add(engine.startGame(computer1AB_E2, computer2AB_E1));
                    results16.add(engine.startGame(computer1AB_E2, computer2AB_E3));
                    results17.add(engine.startGame(computer1AB_E3, computer2AB_E1));
                    results18.add(engine.startGame(computer1AB_E3, computer2AB_E2));


                }

                DataWriter.writeSolution(results1);
                DataWriter.writeSolution(results2);
                DataWriter.writeSolution(results3);
                DataWriter.writeSolution(results4);
                DataWriter.writeSolution(results5);
                DataWriter.writeSolution(results6);
                DataWriter.writeSolution(results7);
                DataWriter.writeSolution(results8);
                DataWriter.writeSolution(results9);
                DataWriter.writeSolution(results10);
                DataWriter.writeSolution(results11);
                DataWriter.writeSolution(results12);
                DataWriter.writeSolution(results13);
                DataWriter.writeSolution(results14);
                DataWriter.writeSolution(results15);
                DataWriter.writeSolution(results16);
                DataWriter.writeSolution(results17);
                DataWriter.writeSolution(results18);

                player2Depth += 2;

            }

            player1Depth += 2;
            player2Depth = 1;
        }


    }
}
