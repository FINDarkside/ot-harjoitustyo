package tetris.domain;

public class Game {

    // Speed tetrominos fall at (blocks per second)
    private static final float VERTICAL_DROP_VELOCITY = 4;

    private GameBoard board;
    private boolean paused;
    private Tetromino activeTetromino;
    private TetrominoPool tetrominoPool = new TetrominoPool();
    private int score = 0;
    private boolean gameOver = false;

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
     * Creates and sets new active tetromino.
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
        if (paused || gameOver) {
            return;
        }
        if (moveFallingTetrominos(dt)) {
            return;
        }
        activeTetromino.setY(activeTetromino.getY() - VERTICAL_DROP_VELOCITY * dt);
        handleInput();
        if (board.collidesWithStaticBlocks(activeTetromino) || activeTetromino.getMinY() < 0) {
            activeTetromino.setY((float) Math.floor(activeTetromino.getY()) + 1);
            board.addTetromino(activeTetromino);
            if (activeTetromino.getMaxY() >= board.getHeight()) {
                gameOver = true;
                return;
            }
            setNextTetromino();
        }
        score += board.clearFullRows();
    }

    private void handleInput() {
        if (inputLeft) {
            moveLeft();
        }
        if (inputRight) {
            moveRight();
        }
        if (inputRotate) {
            rotateActiveTetromino();
            inputRotate = false;
        }
        if (inputDown) {
            while (!board.collidesWithStaticBlocks(activeTetromino) && activeTetromino.getMinY() > 0) {
                activeTetromino.setY(activeTetromino.getY() - 1);
            }
            inputDown = false;
        }
    }

    private void rotateActiveTetromino() {
        activeTetromino.rotateClockwise();
        if (board.collidesWithStaticBlocks(activeTetromino) || board.isOutOfBounds(activeTetromino)) {
            activeTetromino.rotateAnticlockwise();
        }
    }

    /**
     * Moves active tetromino left if possible.
     */
    private void moveLeft() {
        if (activeTetromino.getMinX() - 1 >= 0) {
            activeTetromino.setX(activeTetromino.getX() - 1);
        }
        if (board.collidesWithStaticBlocks(activeTetromino)) {
            activeTetromino.setX(activeTetromino.getX() + 1);
        }
        inputLeft = false;
    }

    /**
     * Moves active tetromino right if possible.
     */
    private void moveRight() {
        if (activeTetromino.getMaxX() + 1 < board.getWidth()) {
            activeTetromino.setX(activeTetromino.getX() + 1);
        }
        if (board.collidesWithStaticBlocks(activeTetromino)) {
            activeTetromino.setX(activeTetromino.getX() - 1);
        }
        inputRight = false;
    }

    /**
     * Moves all tetrominoes that are falling. Tetromino is considered falling,
     * if none of its blocks have another block directly below them, which is
     * not falling and is not from the same tetromino.
     *
     * @param dt delta time since last frame (in seconds)
     * @return true if there was at least one falling tetromino, otherwise false
     */
    public boolean moveFallingTetrominos(float dt) {
        boolean blocksFalling = false;
        for (Tetromino tetromino : board.getTetrominoes()) {
            if (this.moveTetrominoIfFalling(tetromino, dt)) {
                blocksFalling = true;
            }
        }
        return blocksFalling;
    }

    /**
     * Calculates the position which the active tetromino will end up in without
     * any input.
     *
     * @return y coordinate of the final position
     */
    public int predictDropLocation() {
        float originalY = activeTetromino.getY();
        while (!board.collidesWithStaticBlocks(activeTetromino) && activeTetromino.getMinY() > 0) {
            activeTetromino.setY(activeTetromino.getY() - 1);
        }
        int result = (int) Math.ceil(activeTetromino.getY());
        activeTetromino.setY(originalY);
        return result;
    }

    /**
     * Moves the given tetromino down if it's falling. Tetromino is considered
     * falling, if none of its blocks have another block directly below them,
     * which is not falling and is not from the same tetromino.
     *
     * @param tetromino Tetromino to move down if it's falling
     * @param dt delta time since last frame (in seconds)
     * @return true if tetromino was falling, otherwise false
     */
    private boolean moveTetrominoIfFalling(Tetromino tetromino, float dt) {
        boolean[][] usedCells = board.getUsedCellsExcludingTetromino(tetromino);
        for (Block block : tetromino.getBlocks()) {
            int x = tetromino.getBlockCellX(block);
            int y = tetromino.getBlockCellY(block);
            if (y <= 0 || usedCells[y - 1][x]) {
                if (tetromino.getY() != Math.floor(tetromino.getY())) {
                    tetromino.setY((float) Math.floor(tetromino.getY()) + 1);
                }
                return false;
            }
        }
        tetromino.setY(tetromino.getY() - VERTICAL_DROP_VELOCITY * 2 * dt);
        return true;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Tetromino getActiveTetromino() {
        return activeTetromino;
    }

    /**
     * Sets input left as pressed.
     */
    public void inputLeft() {
        inputLeft = true;
    }

    /**
     * Sets input right as pressed.
     */
    public void inputRight() {
        inputRight = true;
    }

    /**
     * Sets input rotate as pressed.
     */
    public void inputRotate() {
        inputRotate = true;
    }

    /**
     * Sets input down as pressed.
     */
    public void inputDown() {
        inputDown = true;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public TetrominoPool getTetrominoPool() {
        return tetrominoPool;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
