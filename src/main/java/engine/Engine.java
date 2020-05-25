package engine;

import players.ComputerPlayer;
import players.Player;
import utils.Printer;
import utils.ResultData;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    Player player1;
    Player player2;
    Player currentPlayer;
    Board board;
    Printer printer = new Printer();
    List<Long> player1moveTimes;
    List<Long> player2moveTimes;


    public ResultData startGame(Player player1, Player player2) {

        ResultData result = initResultData(player1, player2);
        player1.setName("Player 1");
        player2.setName("Player 2");
        player1moveTimes = new ArrayList<>();
        player2moveTimes = new ArrayList<>();

        currentPlayer = player1;
        this.player1 = player1;
        this.player2 = player2;
        board = new Board();
        //printer.printBoard(board.getBoard());

        while (true) {
            long startTime = System.currentTimeMillis();
            currentPlayer.makeMove(board);
            long endTime = System.currentTimeMillis();
           // printer.printBoard(board.getBoard());
            if (board.checkForWinner()) {
                break;
            }
            if (board.isFull()) {
                currentPlayer = null;
                break;
            }

            switchPlayer(endTime - startTime);
        }

        addResultData(result);

        printer.printWinner(currentPlayer);
        if (currentPlayer == null) {
            result.setWinner("Draw");
            return result;
        }
        result.setWinner(currentPlayer.getName());
        return result;


    }

    private void addResultData(ResultData result) {

        result.setFirstPlayerAvgMoveTime(player1moveTimes.stream()
                .mapToDouble(a -> a)
                .average().orElse(0.0));
        result.setSecondPlayerAvgMoveTime(player2moveTimes.stream()
                .mapToDouble(a -> a)
                .average().orElse(0.0));
        if (currentPlayer == player1) {
            result.setWinnerPlayerMoveNumber(player1moveTimes.size());
        } else {
            result.setWinnerPlayerMoveNumber(player2moveTimes.size());
        }

    }

    private ResultData initResultData(Player player1, Player player2) {
        ResultData result = new ResultData();
        try {
            result.setFirstPlayerAlgorithm(((ComputerPlayer) player1).getAlgorithm().getClass().getName().replace("algorithms.", ""));
            result.setFirstPlayerDepth(((ComputerPlayer) player1).getAlgorithm().getDepth().toString());
            result.setFirstPlayerEvaluation(((ComputerPlayer) player1).getAlgorithm().getEvaluation().getClass().getName().replace("heuristics.", ""));
        } catch (ClassCastException e) {
            result.setFirstPlayerAlgorithm("Human");
            result.setFirstPlayerDepth("Human");
            result.setFirstPlayerEvaluation("Human");
        }

        try {
            result.setSecondPlayerAlgorithm(((ComputerPlayer) player2).getAlgorithm().getClass().getName().replace("algorithms.", ""));
            result.setSecondPlayerDepth(((ComputerPlayer) player2).getAlgorithm().getDepth().toString());
            result.setSecondPlayerEvaluation(((ComputerPlayer) player2).getAlgorithm().getEvaluation().getClass().getName().replace("heuristics.", ""));
        } catch (ClassCastException e) {
            result.setSecondPlayerAlgorithm("Human");
            result.setSecondPlayerDepth("Human");
            result.setSecondPlayerEvaluation("Human");
        }

        return result;
    }


    private void switchPlayer(long moveTime) {
        if (currentPlayer == player1) {
            currentPlayer = player2;
            player1moveTimes.add(moveTime);

        } else {
            currentPlayer = player1;
            player2moveTimes.add(moveTime);
        }
    }

}
