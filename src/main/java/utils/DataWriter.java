package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static constances.Constances.FILES_PATH;

public class DataWriter {

    public static void writeSolution(List<ResultData> data) {


        ArrayList<String> toWrite = new ArrayList<>();
        toWrite.add(data.get(0).getFirstPlayerAlgorithm());
        toWrite.add(data.get(0).getFirstPlayerDepth());
        toWrite.add(data.get(0).getFirstPlayerEvaluation());
        toWrite.add("" + data.stream().mapToDouble(ResultData::getFirstPlayerAvgMoveTime).average().orElse(-1.0));
        toWrite.add(data.get(0).getSecondPlayerAlgorithm());
        toWrite.add(data.get(0).getSecondPlayerDepth());
        toWrite.add(data.get(0).getSecondPlayerEvaluation());
        toWrite.add("" + data.stream().mapToDouble(ResultData::getSecondPlayerAvgMoveTime).average().orElse(-1.0));
        toWrite.add("" + data.stream().mapToDouble(ResultData::getWinnerPlayerMoveNumber).average().orElse(-1.0));
        toWrite.add("" + data.stream().map(ResultData::getWinner).filter(a -> a.equals("Player 1")).count());
        toWrite.add("" + data.stream().map(ResultData::getWinner).filter(a -> a.equals("Player 2")).count());
        toWrite.add("" + data.stream().map(ResultData::getWinner).filter(a -> a.equals("Draw")).count());

        StringBuilder stringData = new StringBuilder();
        for (String string : toWrite) {
            stringData.append(string).append(";");
        }
        try {
            File file = new File(FILES_PATH + "Results/Player1-_Algorithm-" + data.get(0).getFirstPlayerAlgorithm() +
                    "_Depth-" + data.get(0).getFirstPlayerDepth() +
                    "_Evaluation-" + data.get(0).getFirstPlayerEvaluation() +
                    "__Player2-_Algorithm-" + data.get(0).getSecondPlayerAlgorithm() +
                    "_Depth-" + data.get(0).getSecondPlayerDepth() +
                    "_Evaluation-" + data.get(0).getSecondPlayerEvaluation() +
                    "_Result.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(outputStream);

            writer.println(stringData);
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

/*
            toWrite.add("Algorytm pierwszego gracza: " + data.get(0).getFirstPlayerAlgorithm());
            toWrite.add("Głębokość przeszukiwania pierwszego gracza: " + data.get(0).getFirstPlayerDepth());
            toWrite.add("Metoda ewaluacji pierwszego gracza: " + data.get(0).getFirstPlayerEvaluation());
            toWrite.add("Sredni czas wykowania ruchu pierwszego gracza: " + data.stream().mapToDouble(ResultData::getFirstPlayerAvgMoveTime).average().orElse(-1.0));
            toWrite.add("Algorytm drugiego gracza: " + data.get(0).getSecondPlayerAlgorithm());
            toWrite.add("Głębokość przeszukiwania drugiego gracza: " + data.get(0).getSecondPlayerDepth());
            toWrite.add("Metoda ewaluacji drugiego gracza: " + data.get(0).getSecondPlayerEvaluation());
            toWrite.add("Sredni czas wykowania ruchu drugiego gracza: " + data.stream().mapToDouble(ResultData::getSecondPlayerAvgMoveTime).average().orElse(-1.0));
            toWrite.add("Srednia liczba ruchów gracza wygrywającego gracza: " + data.stream().mapToDouble(ResultData::getWinnerPlayerMoveNumber).average().orElse(-1.0));
            toWrite.add("Liczba Zwycięstw pierwszego gracza: " + data.stream().map(ResultData::getWinner).filter(a -> a.equals("Player 1")).count());
            toWrite.add("Liczba Zwycięstw drugiego gracza: " + data.stream().map(ResultData::getWinner).filter(a -> a.equals("Player 2")).count());
            toWrite.add("Liczba remisów: " + data.stream().map(ResultData::getWinner).filter(a -> a.equals("Draw")).count());

 */