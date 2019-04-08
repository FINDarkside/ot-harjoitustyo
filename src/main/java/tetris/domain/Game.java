package tetris.domain;

public class Game {

    // Speed the block group drops at (blocks per second)
    private static final float VERTICAL_DROP_VELOCITY = 2;

    private GameBoard board;
    private boolean paused;
    private BlockGroup blockGroup;

    /**
     * Creates new game with given width and height.
     *
     * @param height Height of the game board in blocks
     * @param width Width of the game board in blocks
     */
    public Game(int height, int width) {
        this.board = new GameBoard(width, height);
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

        blockGroup.setY(blockGroup.getY() - VERTICAL_DROP_VELOCITY * dt);
    }
}
