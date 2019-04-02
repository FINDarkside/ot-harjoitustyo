package tetris.domain;

import javafx.scene.paint.Color;

public class Block {

    private String color;

    public Block(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
