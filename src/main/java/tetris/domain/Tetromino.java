package tetris.domain;

import java.util.*;

public class Tetromino {

    private List<Block> blocks;
    private float x;
    private float y;

    /**
     * Creates new {@code BlockGroup}.
     *
     * @param blocks Array of {@code BlockGroupItem}
     * @param x x coordinate of the group
     * @param y y coordinate of the group
     */
    public Tetromino(List<Block> blocks, float x, float y) {
        this.blocks = blocks;
        this.x = x;
        this.y = y;
    }

    /**
     * Rotates the block group clockwise.
     */
    public void rotateClockwise() {
        for (Block block : blocks) {
            int x = block.getRelativeX();
            int y = block.getRelativeY();
            block.setRelativeX(y);
            block.setRelativeY(-x - 1);
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Returns the smallest x coordinate of a cell the given block occupies. For
     * example, if the absolute x position of the block is 2.5, this will return
     * 2.
     *
     * @param b target block to calculate coordinate from
     * @return smallest x coordinate of a cell the given block occupies
     */
    public int getBlockCellX(Block b) {
        return (int) Math.floor(this.x) + b.getRelativeX();
    }

    /**
     * Returns the largest y coordinate of a cell the given block occupies. For
     * example, if the absolute y position of the block is 2.5, this will return
     * 3.
     *
     * @param b target block to calculate coordinate from
     * @return smallest x coordinate of a cell the given block occupies
     */
    public int getBlockCellY(Block b) {
        return (int) Math.ceil(this.y) + b.getRelativeY();
    }

}
