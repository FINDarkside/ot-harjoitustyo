package tetris.domain;

public class GameBoard {

    private final int width;
    private final int height;
    private Block[][] board;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        board = new Block[height][width];
    }

    public Block[][] getBoard() {
        return board;
    }

    public Block getBlock(int x, int y) {
        return board[y][x];
    }

    /**
     * @param y Target row, 0 is the bottom row
     * @return Returns true if target row is full
     */
    public boolean isRowFull(int y) {
        if (y < 0 || y >= height) {
            return false;
        }
        for (Block b : board[y]) {
            if (b == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes all blocks from target row. All blocks above the target row will
     * move one unit down
     *
     * @param y Row to clear, 0 is the bottom row
     */
    public void clearRow(int y) {
        for (int x = 0; x < width; x++) {
            board[y][x] = null;
        }
        for (int i = y + 1; i < height; i++) {
            for (int x = 0; x < width; x++) {
                board[i - 1][x] = board[i][x];
                board[i][x] = null;
            }
        }
    }
}
