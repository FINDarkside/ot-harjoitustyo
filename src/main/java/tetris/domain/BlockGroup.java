package tetris.domain;

import java.util.*;

public class BlockGroup {

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
    public BlockGroup(List<Block> blocks, float x, float y) {
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

    public int getBlockX(Block b) {
        return (int) Math.floor(this.x) + b.getRelativeX();
    }

    public int getBlockY(Block b) {
        return (int) Math.floor(this.y) + b.getRelativeY();
    }

}
