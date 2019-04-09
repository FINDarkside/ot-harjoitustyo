package tetris.domain;

import java.util.*;

public class GameBoard {

    private final int width;
    private final int height;
    private List<BlockGroup> blockGroups = new ArrayList<>();

    /**
     * Creates new GameBoard.
     *
     * @param width Width of game board in blocks
     * @param height Height of game board in blocks
     */
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the occupied cells on the game board. Bottom left is [0][0]
     *
     * @return 2d boolean array indicating which cells are occupied
     */
    public boolean[][] getUsedCells() {
        boolean[][] usedCells = new boolean[height][width];
        for (int i = 0; i < usedCells.length; i++) {
            Arrays.fill(usedCells[i], false);
        }
        for (BlockGroup group : blockGroups) {
            for (Block block : group.getBlocks()) {
                int x = group.getBlockCellX(block);
                int y = group.getBlockCellY(block);
                usedCells[y][x] = true;
            }
        }
        return usedCells;
    }

    public List<BlockGroup> getBlockGroups() {
        return blockGroups;
    }

    /**
     * Adds block group to the board.
     *
     * @param group group to add
     */
    public void addBlockGroup(BlockGroup group) {
        this.blockGroups.add(group);
    }

    /**
     * Checks if the row on {@code y} is full horizontally.
     *
     * @param y Target row, 0 is the bottom row
     * @return true if target row is full
     */
    public boolean isRowFull(int y) {
        for (boolean cellUsed : getUsedCells()[y]) {
            if (!cellUsed) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes all blocks from target row. All blocks above the target row will
     * move one unit down.
     *
     * @param y Row to clear, 0 is the bottom row
     */
    public void clearRow(int y) {
        for (BlockGroup group : blockGroups) {
            List<Block> blocks = group.getBlocks();
            for (int i = blocks.size() - 1; i >= 0; i--) {
                if (group.getBlockCellY(blocks.get(i)) == y) {
                    blocks.remove(i);
                }
            }
        }
    }

    /**
     * Checks if the given block group collides with static blocks.
     *
     * @param group Group to be checked against
     * @return value indicating whether the given block group collides with
     * static blocks.
     */
    public boolean collidesWithStaticBlocks(BlockGroup group) {
        boolean[][] usedCells = getUsedCells();
        for (Block block : group.getBlocks()) {
            int minX = (int) (group.getX() + block.getRelativeX());
            int minY = (int) (group.getY() + block.getRelativeY());
            int maxX = minX + (int) Math.ceil(group.getX() + block.getRelativeX() + 1) - 1;
            int maxY = minY + (int) Math.ceil(group.getY() + block.getRelativeY() + 1) - 1;

            for (int y = minY; y < maxY; y++) {
                for (int x = minX; x < maxX; x++) {
                    if (usedCells[y][x]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks the map for full rows, and removes them when found. If blocks are
     * left floating, they will be moved down until they hit another block.
     *
     * @return Amount of rows removed
     */
    public int clearFullRows() {
        int fullRows = 0;
        for (int i = 0; i < height; i++) {
            if (isRowFull(i)) {
                clearRow(i);
                fullRows++;
            }
        }
        return fullRows;
    }

}
