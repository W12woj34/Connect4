package players;

import controllers.GuiController;
import engine.Board;
import javafx.scene.paint.Color;

public abstract class Player {

    protected String name;
    protected char symbol;
    protected Color color;
    protected boolean isFirst;
    protected GuiController controller;

    public Player(char symbol, Color color, boolean isFirst, GuiController controller) {
        this.symbol = symbol;
        this.color = color;
        this.isFirst = isFirst;
        this.controller = controller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public abstract void makeMove(Board board);

    protected void setField(String lastMoveCord, Color color) {

        try {
            controller.setFieldColor(lastMoveCord, color);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
