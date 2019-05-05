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

    /**
     * Rotates the block group anticlockwise.
     */
    public void rotateAnticlockwise() {
        for (Block block : blocks) {
            int x = block.getRelativeX();
            int y = block.getRelativeY();
            block.setRelativeX(-y - 1);
            block.setRelativeY(x);
        }
    }

    /**
     * Removes all blocks in the given row.
     *
     * @param y row to clear
     * @return new tetromino if removing blocks split tetromino in half,
     * otherwise null
     */
    public Tetromino removeBlockOnRow(int y) {
        boolean blocksRemoved = false;
        for (int i = blocks.size() - 1; i >= 0; i--) {
            if (getBlockCellY(blocks.get(i)) == y) {
                blocks.remove(i);
                blocksRemoved = true;
            }
        }
        if (blocksRemoved) {
            return splitTetromino(y);
        }
        return null;
    }

    private Tetromino splitTetromino(int y) {
        ArrayList<Block> newTetrominoBlocks = new ArrayList<>();
        for (int i = blocks.size() - 1; i >= 0; i--) {
            if (getBlockCellY(blocks.get(i)) > y) {
                newTetrominoBlocks.add(blocks.remove(i));
            }
        }
        if (newTetrominoBlocks.isEmpty()) {
            return null;
        }
        return new Tetromino(newTetrominoBlocks, getX(), getY());
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

    /**
     * Returns the minimum y value of any block in the tetromino.
     *
     * @return minimum y value of any block in the tetromino
     */
    public float getMinY() {
        float minY = Float.MAX_VALUE;
        for (Block block : blocks) {
            minY = Math.min(minY, block.getRelativeY() + y);
        }
        return minY;
    }

    /**
     * Returns the maximum y value of any block in the tetromino.
     *
     * @return maximum y value of any block in the tetromino
     */
    public float getMaxY() {
        float maxY = -1;
        for (Block block : blocks) {
            maxY = Math.max(maxY, block.getRelativeY() + y);
        }
        return maxY;
    }

    /**
     * Returns the minimum x value of any block in the tetromino.
     *
     * @return minimum x value of any block in the tetromino
     */
    public float getMinX() {
        float minX = Float.MAX_VALUE;
        for (Block block : blocks) {
            minX = Math.min(minX, block.getRelativeX() + x);
        }
        return minX;
    }

    /**
     * Returns the maximum x value of any block in the tetromino.
     *
     * @return maximum x value of any block in the tetromino
     */
    public float getMaxX() {
        float maxX = 0;
        for (Block block : blocks) {
            maxX = Math.max(maxX, block.getRelativeX() + x);
        }
        return maxX;
    }

}
