package tetris.domain;

import java.util.*;

public class GameBoard {

    private final int width;
    private final int height;
    private List<Tetromino> blockGroups = new ArrayList<>();

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
        for (Tetromino group : blockGroups) {
            for (Block block : group.getBlocks()) {
                int x = group.getBlockCellX(block);
                int y = group.getBlockCellY(block);
                if (y < height && y >= 0 && x >= 0 && x < width) {
                    usedCells[y][x] = true;
                }
            }
        }
        return usedCells;
    }

    /**
     * Calculates the occupied cells on the game board, excluding the tetromino
     * given as argument.
     *
     * @param tetromino tetromino to exlude from results
     * @return 2d boolean array indicating which cells are occupied
     */
    public boolean[][] getUsedCellsExcludingTetromino(Tetromino tetromino) {
        boolean[][] usedCells = getUsedCells();
        for (Block block : tetromino.getBlocks()) {
            int x = tetromino.getBlockCellX(block);
            int y = tetromino.getBlockCellY(block);
            if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
                usedCells[y][x] = false;
            }
        }
        return usedCells;
    }

    public List<Tetromino> getBlockGroups() {
        return blockGroups;
    }

    /**
     * Adds block group to the board.
     *
     * @param group group to add
     */
    public void addBlockGroup(Tetromino group) {
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
        for (int j = blockGroups.size() - 1; j >= 0; j--) {
            Tetromino tetromino = blockGroups.get(j);
            Tetromino newTetromino = tetromino.removeBlockOnRow(y);
            if (newTetromino != null) {
                this.blockGroups.add(newTetromino);
            }
            if (tetromino.getBlocks().isEmpty()) {
                blockGroups.remove(j);
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
    public boolean collidesWithStaticBlocks(Tetromino group) {
        boolean[][] usedCells = getUsedCells();
        for (Block block : group.getBlocks()) {
            int minX = (int) Math.floor(group.getX() + block.getRelativeX());
            int minY = (int) Math.floor(group.getY() + block.getRelativeY());
            int maxX = (int) Math.ceil(group.getX() + block.getRelativeX());
            int maxY = (int) Math.ceil(group.getY() + block.getRelativeY());
            for (int y = minY; y <= maxY; y++) {
                for (int x = minX; x <= maxX; x++) {
                    if (y >= 0 && y < height && x >= 0 && x < width && usedCells[y][x]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the given tetromino is withing board bounds.
     *
     * @param tetromino tetromino to be checked
     * @return value indicating whether given tetromino is within board bounds.
     */
    public boolean isOutOfBounds(Tetromino tetromino) {
        return tetromino.getMinX() < 0
                || tetromino.getMaxX() > width - 1
                || tetromino.getMinY() < 0
                || tetromino.getMaxY() > height - 1;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
