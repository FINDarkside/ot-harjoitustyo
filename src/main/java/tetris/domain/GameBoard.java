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
}
