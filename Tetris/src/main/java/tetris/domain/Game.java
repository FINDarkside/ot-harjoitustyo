package tetris.domain;

import java.util.ArrayList;

public class Game {

    // Speed the block group drops at (blocks per second)
    private static final float VERTICAL_DROP_VELOCITY = 4;

    private GameBoard board;
    private boolean paused;
    private Tetromino activeTetromino;
    private TetrominoPool tetrominoPool = new TetrominoPool();

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
        activeTetromino.setY(activeTetromino.getY() - VERTICAL_DROP_VELOCITY * dt);
        if (board.collidesWithStaticBlocks(activeTetromino)) {
            System.out.println("COLLIDES");
            activeTetromino.setY((float) Math.floor(activeTetromino.getY()) + 1);
            board.addBlockGroup(activeTetromino);
            board.clearFullRows();
            setNextTetromino();
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
    public boolean moveFallingBlockGroups() {
        boolean blocksFalling = false;
        for (Tetromino group : board.getBlockGroups()) {
            if (this.moveBlockGroupIfFalling(group)) {
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
    private boolean moveBlockGroupIfFalling(Tetromino group) {
        boolean[][] usedCells = board.getUsedCells();
        for (Block block : group.getBlocks()) {
            usedCells[group.getBlockCellY(block)][group.getBlockCellX(block)] = false;
        }
        for (Block block : group.getBlocks()) {
            int x = group.getBlockCellX(block);
            int y = group.getBlockCellY(block);
            if (y <= 0 || usedCells[y][x]) {
                if (group.getY() != Math.floor(group.getY())) {
                    group.setY((float) Math.floor(group.getY()) + 1);
                }
                return false;
            }
        }
        group.setY(group.getY() - 1);
        return true;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Tetromino getActiveBlockGroup() {
        return activeTetromino;
    }

}
