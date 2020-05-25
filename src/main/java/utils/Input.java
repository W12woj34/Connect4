package utils;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Input {

    Scanner scanner = new Scanner(System.in);

    public int readColumn() {
        System.out.println("Write column");

        return Integer.parseInt(scanner.nextLine());
    }

}
