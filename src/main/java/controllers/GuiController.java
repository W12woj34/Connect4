package controllers;

import algorithms.Algorithm;
import algorithms.AlphaBeta;
import algorithms.Minmax;
import engine.Engine;
import heuristics.BoardWeightEvaluation;
import heuristics.Evaluation;
import heuristics.SeriesEvaluation;
import heuristics.PossibilitiesEvaluation;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import players.ComputerPlayer;
import players.Player;
import players.UserPlayer;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import static constances.Constances.*;

public class GuiController implements Initializable {

    @FXML
    private Button buttonStart;
    @FXML
    private Rectangle button1Column, button2Column, button3Column, button4Column, button5Column, button6Column, button7Column;
    @FXML
    private ChoiceBox<String> choiceBoxPlayer1, choiceBoxPlayer2, choiceBox1Eval, choiceBox2Eval, choiceBox1Depth, choiceBox2Depth;
    @FXML
    private Circle field00, field01, field02, field03, field04, field05, field06,
            field10, field11, field12, field13, field14, field15, field16,
            field20, field21, field22, field23, field24, field25, field26,
            field30, field31, field32, field33, field34, field35, field36,
            field40, field41, field42, field43, field44, field45, field46,
            field50, field51, field52, field53, field54, field55, field56;
    @FXML
    private Label label1Eval, label2Eval, label1Depth, label2Depth, labelResult;

    private Engine engine = new Engine();
    private int chosenColumn = -2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBox1Eval.setVisible(false);
        choiceBox2Eval.setVisible(false);
        choiceBox1Depth.setVisible(false);
        choiceBox2Depth.setVisible(false);
        label1Eval.setVisible(false);
        label2Eval.setVisible(false);
        label1Depth.setVisible(false);
        label2Depth.setVisible(false);


        choiceBoxPlayer2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Human")) {
                label2Eval.setVisible(false);
                label2Depth.setVisible(false);
                choiceBox2Eval.setVisible(false);
                choiceBox2Depth.setVisible(false);
            } else {
                label2Eval.setVisible(true);
                label2Depth.setVisible(true);
                choiceBox2Eval.setVisible(true);
                choiceBox2Depth.setVisible(true);
            }

        });

        choiceBoxPlayer1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Human")) {
                label1Eval.setVisible(false);
                label1Depth.setVisible(false);
                choiceBox1Eval.setVisible(false);
                choiceBox1Depth.setVisible(false);
            } else {
                label1Eval.setVisible(true);
                label1Depth.setVisible(true);
                choiceBox1Eval.setVisible(true);
                choiceBox1Depth.setVisible(true);
            }

        });
    }

    @FXML
    private void startButtonAction() throws NoSuchFieldException, IllegalAccessException {


        buttonStart.setDisable(true);
        labelResult.setText("Game Started");

        for (int i = 0; i < BOARD_ROW_NUMBER; i++) {
            for (int j = 0; j < BOARD_COLUMN_NUMBER; j++) {
                setFieldColor(String.valueOf(i) + j, Color.WHITE);
            }
        }

        char firstPlayerSymbol = PLAYER1_SYMBOL;
        char secondPlayerSymbol = PLAYER2_SYMBOL;
        Color firstPlayerColor = PLAYER1_COLOR;
        Color secondPlayerColor = PLAYER2_COLOR;

        Player player1;
        Player player2;
        int player1Depth = Integer.parseInt(choiceBox1Depth.getValue());
        int player2Depth = Integer.parseInt(choiceBox2Depth.getValue());
        Evaluation evaluation1 = new SeriesEvaluation(firstPlayerSymbol, secondPlayerSymbol);
        Evaluation evaluation2 = new PossibilitiesEvaluation(firstPlayerSymbol, secondPlayerSymbol);
        Evaluation evaluation3 = new BoardWeightEvaluation(firstPlayerSymbol, secondPlayerSymbol);

        if (choiceBoxPlayer1.getValue().equals("Human")) {
            player1 = new UserPlayer(firstPlayerSymbol, firstPlayerColor, true, this);
        } else {
            Evaluation evaluation;
            if (choiceBox1Eval.getValue().equals("Series")) {
                evaluation = evaluation1;
            } else if (choiceBox1Eval.getValue().equals("Possibilities")) {
                evaluation = evaluation2;
            } else {
                evaluation = evaluation3;
            }

            Algorithm algorithm1;
            if (choiceBoxPlayer1.getValue().equals("Minmax")) {
                algorithm1 = new Minmax(evaluation, player1Depth, firstPlayerSymbol, secondPlayerSymbol);
            } else {
                algorithm1 = new AlphaBeta(evaluation, player1Depth, firstPlayerSymbol, secondPlayerSymbol);
            }

            player1 = new ComputerPlayer(firstPlayerSymbol, firstPlayerColor, true, algorithm1, this);
        }

        if (choiceBoxPlayer2.getValue().equals("Human")) {
            player2 = new UserPlayer(secondPlayerSymbol, secondPlayerColor, false, this);
        } else {
            Evaluation evaluation;
            if (choiceBox2Eval.getValue().equals("Series")) {
                evaluation = evaluation1;
            } else if (choiceBox2Eval.getValue().equals("Possibilities")) {
                evaluation = evaluation2;
            } else {
                evaluation = evaluation3;
            }

            Algorithm algorithm2;
            if (choiceBoxPlayer2.getValue().equals("Minmax")) {
                algorithm2 = new Minmax(evaluation, player2Depth, firstPlayerSymbol, secondPlayerSymbol);
            } else {
                algorithm2 = new AlphaBeta(evaluation, player2Depth, firstPlayerSymbol, secondPlayerSymbol);
            }
            player2 = new ComputerPlayer(secondPlayerSymbol, secondPlayerColor, false, algorithm2, this);
            labelResult.setText(evaluation.getClass().toString());
        }


        Service<Void> service = new Service<>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        String winner = engine.startGame(player1, player2).getWinner();
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                buttonStart.setDisable(false);
                                if (winner.equals("Draw")) {
                                    setResultLabel("Game result: Draw");
                                } else {
                                    setResultLabel("Game result: " + winner + " win!");
                                }

                            } finally {
                                latch.countDown();
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        service.start();

    }

    public void setFieldColor(String fieldCord, Color color) throws NoSuchFieldException, IllegalAccessException {
        Circle field = (Circle) getClass().getDeclaredField("field" + fieldCord).get(this);
        field.setFill(color);
    }

    public void setResultLabel(String text) {
        labelResult.setText(text);
    }


    public int getChosenColumn() {
        return chosenColumn;
    }

    public void setChosenColumn(int chosenColumn) {
        this.chosenColumn = chosenColumn;
    }

    @FXML
    private void column1Action() {
        if (chosenColumn == -1) {
            chosenColumn = 0;
        }
    }

    @FXML
    private void column2Action() {
        if (chosenColumn == -1) {
            chosenColumn = 1;
        }
    }

    @FXML
    private void column3Action() {
        if (chosenColumn == -1) {
            chosenColumn = 2;
        }
    }

    @FXML
    private void column4Action() {
        if (chosenColumn == -1) {
            chosenColumn = 3;
        }
    }

    @FXML
    private void column5Action() {
        if (chosenColumn == -1) {
            chosenColumn = 4;
        }
    }

    @FXML
    private void column6Action() {
        if (chosenColumn == -1) {
            chosenColumn = 5;
        }
    }

    @FXML
    private void column7Action() {
        if (chosenColumn == -1) {
            chosenColumn = 6;
        }
    }

    @FXML
    private void enteredColumn1() {
        button1Column.setFill(Color.valueOf("#fffff030"));
    }

    @FXML
    private void exitedColumn1() {
        button1Column.setFill(Color.valueOf("#ffffff00"));
    }

    @FXML
    private void enteredColumn2() {
        button2Column.setFill(Color.valueOf("#fffff030"));
    }

    @FXML
    private void exitedColumn2() {
        button2Column.setFill(Color.valueOf("#ffffff00"));
    }

    @FXML
    private void enteredColumn3() {
        button3Column.setFill(Color.valueOf("#fffff030"));
    }

    @FXML
    private void exitedColumn3() {
        button3Column.setFill(Color.valueOf("#ffffff00"));
    }

    @FXML
    private void enteredColumn4() {
        button4Column.setFill(Color.valueOf("#fffff030"));
    }

    @FXML
    private void exitedColumn4() {
        button4Column.setFill(Color.valueOf("#ffffff00"));
    }

    @FXML
    private void enteredColumn5() {
        button5Column.setFill(Color.valueOf("#fffff030"));
    }

    @FXML
    private void exitedColumn5() {
        button5Column.setFill(Color.valueOf("#ffffff00"));
    }

    @FXML
    private void enteredColumn6() {
        button6Column.setFill(Color.valueOf("#fffff030"));
    }

    @FXML
    private void exitedColumn6() {
        button6Column.setFill(Color.valueOf("#ffffff00"));
    }

    @FXML
    private void enteredColumn7() {
        button7Column.setFill(Color.valueOf("#fffff030"));
    }

    @FXML
    private void exitedColumn7() {
        button7Column.setFill(Color.valueOf("#ffffff00"));
    }

}
