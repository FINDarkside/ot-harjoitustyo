package tetris.domain;

public class Block {

    private String color;
    private int relativeX;
    private int relativeY;

    /**
     * Creates new block.
     *
     * @param color Color of the block
     * @param relativeX relative x position to other blocks in the same tetromino
     * @param relativeY relative y position to other blocks in the same tetromino
     */
    public Block(String color, int relativeX, int relativeY) {
        this.color = color;
        this.relativeX = relativeX;
        this.relativeY = relativeY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRelativeX() {
        return relativeX;
    }

    public int getRelativeY() {
        return relativeY;
    }

    public void setRelativeX(int relativeX) {
        this.relativeX = relativeX;
    }

    public void setRelativeY(int relativeY) {
        this.relativeY = relativeY;
    }

}
