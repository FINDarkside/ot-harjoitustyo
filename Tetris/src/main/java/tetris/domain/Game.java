package tetris.domain;

import java.util.ArrayList;

public class Game {

    // Speed the block group drops at (blocks per second)
    private static final float VERTICAL_DROP_VELOCITY = 4;

    private GameBoard board;
    private boolean paused;
    private Tetromino activeTetromino;
    private TetrominoPool tetrominoPool = new TetrominoPool();

    private boolean inputLeft, inputRight, inputRotate, inputDown;

    /**
     * Creates new game with given width and height.
     *
     * @param height Height of the game board in blocks
     * @param width Width of the game board in blocks
     */
    public Game(int height, int width) {
        this.board = new GameBoard(width, height);
        setNextTetromino();
    }

    /**
     * Creates and sets new active block group.
     */
    public void setNextTetromino() {
        activeTetromino = tetrominoPool.getNext();
        activeTetromino.setX((float) Math.floor(board.getWidth() / 2));
        activeTetromino.setY(board.getHeight());
    }

    /**
     * Updates game state.
     *
     * @param dt Time since last update (in milliseconds)
     */
    public void update(float dt) {
        if (paused) {
            return;
        }
        if (moveFallingBlockGroups(dt)) {
            return;
        }
        activeTetromino.setY(activeTetromino.getY() - VERTICAL_DROP_VELOCITY * dt);
        handleInput();
        if (board.collidesWithStaticBlocks(activeTetromino) || activeTetromino.getMinY() < 0) {
            activeTetromino.setY((float) Math.floor(activeTetromino.getY()) + 1);
            board.addBlockGroup(activeTetromino);
            board.clearFullRows();
            setNextTetromino();
        }
    }

    private void handleInput() {
        if (inputLeft) {
            if (activeTetromino.getMinX() - 1 >= 0) {
                activeTetromino.setX(activeTetromino.getX() - 1);
            }
            inputLeft = false;
        }
        if (inputRight) {
            if (activeTetromino.getMaxX() + 1 < board.getWidth()) {
                activeTetromino.setX(activeTetromino.getX() + 1);
            }
            inputRight = false;
        }
        if (inputRotate) {
            activeTetromino.rotateClockwise();
            inputRotate = false;
        }
        if (inputDown) {
            while (!board.collidesWithStaticBlocks(activeTetromino) && activeTetromino.getMinY() > 0) {
                activeTetromino.setY(activeTetromino.getY() - 1);
            }
            inputDown = false;
        }
    }

    /**
     * Moves all block groups that are falling. Block group is considered
     * falling, if none of its blocks have another block directly below that's
     * not falling and is not from the same group.
     *
     * @return true if there was at least one falling block group, otherwise
     * false
     */
    public boolean moveFallingBlockGroups(float dt) {
        boolean blocksFalling = false;
        for (Tetromino group : board.getBlockGroups()) {
            if (this.moveBlockGroupIfFalling(group, dt)) {
                blocksFalling = true;
            }
        }
        return blocksFalling;
    }

    /**
     * Moves the given block group down if it's falling. Block group is
     * considered falling, if none of its blocks have another block directly
     * below that's not falling and is not from the same group.
     *
     * @param group Group to move down if it's falling
     * @return true if the block group was falling, otherwise false
     */
    private boolean moveBlockGroupIfFalling(Tetromino group, float dt) {
        boolean[][] usedCells = board.getUsedCells();
        for (Block block : group.getBlocks()) {
            int x = group.getBlockCellX(block);
            int y = group.getBlockCellY(block);
            if (x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight()) {
                usedCells[y][x] = false;
            }
        }
        for (Block block : group.getBlocks()) {
            int x = group.getBlockCellX(block);
            int y = group.getBlockCellY(block);
            if (y <= 0 || usedCells[y - 1][x]) {
                if (group.getY() != Math.floor(group.getY())) {
                    group.setY((float) Math.floor(group.getY()) + 1);
                }
                return false;
            }
        }
        group.setY(group.getY() - VERTICAL_DROP_VELOCITY * 2 * dt);
        return true;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Tetromino getActiveBlockGroup() {
        return activeTetromino;
    }

    public void inputLeft() {
        inputLeft = true;
    }

    public void inputRight() {
        inputRight = true;
    }

    public void inputRotate() {
        inputRotate = true;
    }

    public void inputDown() {
        inputDown = true;
    }

}
