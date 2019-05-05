package tetris.domain;

import java.util.*;

public class GameBoard {

    private final int width;
    private final int height;
    private List<Tetromino> tetrominoes = new ArrayList<>();

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
        for (Tetromino tetromino : tetrominoes) {
            for (Block block : tetromino.getBlocks()) {
                int x = tetromino.getBlockCellX(block);
                int y = tetromino.getBlockCellY(block);
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

    public List<Tetromino> getTetrominoes() {
        return tetrominoes;
    }

    /**
     * Adds tetromino to the board.
     *
     * @param tetromino to add
     */
    public void addTetromino(Tetromino tetromino) {
        this.tetrominoes.add(tetromino);
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
        for (int j = tetrominoes.size() - 1; j >= 0; j--) {
            Tetromino tetromino = tetrominoes.get(j);
            Tetromino newTetromino = tetromino.removeBlockOnRow(y);
            if (newTetromino != null) {
                this.tetrominoes.add(newTetromino);
            }
            if (tetromino.getBlocks().isEmpty()) {
                tetrominoes.remove(j);
            }
        }
    }

    /**
     * Checks if the given tetromino collides with static blocks.
     *
     * @param tetromino tetromino to be checked against
     * @return value indicating whether the given tetromino collides with static
     * blocks.
     */
    public boolean collidesWithStaticBlocks(Tetromino tetromino) {
        boolean[][] usedCells = getUsedCells();
        for (Block block : tetromino.getBlocks()) {
            int minX = (int) Math.floor(tetromino.getX() + block.getRelativeX());
            int minY = (int) Math.floor(tetromino.getY() + block.getRelativeY());
            int maxX = (int) Math.ceil(tetromino.getX() + block.getRelativeX());
            int maxY = (int) Math.ceil(tetromino.getY() + block.getRelativeY());
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
