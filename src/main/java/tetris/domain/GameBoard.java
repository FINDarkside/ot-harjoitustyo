package tetris.domain;

public class GameBoard {

    private final int width;
    private final int height;
    private Block[][] board;

    /**
     * Creates new GameBoard.
     *
     * @param width Width of game board in blocks
     * @param height Height of game board in blocks
     */
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        board = new Block[height][width];
    }

    public Block[][] getBoard() {
        return board;
    }

    /**
     * Returns the block at given coordinates, or null if the block does not
     * exist.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the block at given coordinates, or null if the block does not
     */
    public Block getBlock(int x, int y) {
        return board[y][x];
    }

    /**
     * Checks if the row on {@code y} is full horizontally.
     *
     * @param y Target row, 0 is the bottom row
     * @return true if target row is full
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
     * move one unit down.
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

    /**
     * Checks if the given block group collides with static blocks.
     *
     * @param group Group to be checked against
     * @return value indicating whether the given block group collides with
     * static blocks.
     */
    public boolean collidesWithStaticBlocks(BlockGroup group) {
        for (BlockGroupItem groupItem : group.getBlocks()) {
            int minX = (int) (group.getX() + groupItem.getRelativeX());
            int minY = (int) (group.getY() + groupItem.getRelativeY());
            int maxX = minX + (int) Math.ceil(group.getX() + groupItem.getRelativeX() + 1) - 1;
            int maxY = minY + (int) Math.ceil(group.getY() + groupItem.getRelativeY() + 1) - 1;

            for (int y = minY; y < maxY; y++) {
                for (int x = minX; x < maxX; x++) {
                    if (board[y][x] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Places block group on the board with other static blocks. Ceils y and
     * floors x coordinate if they are not integers already.
     *
     * @param group Group to place on the board
     */
    public void placeBlockGroup(BlockGroup group) {
        for (BlockGroupItem groupItem : group.getBlocks()) {
            int x = (int) Math.floor(group.getX() + groupItem.getRelativeX());
            int y = (int) Math.ceil(group.getY() + groupItem.getRelativeY());
            if (board[y][x] != null) {
                throw new RuntimeException("Board at " + x + " : " + y + " is already occupied");
            }
            board[y][x] = groupItem.getBlock();
        }
    }

}
