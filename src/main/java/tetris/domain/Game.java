package tetris.domain;

public class Game {

    // Speed the block group drops at (blocks per second)
    private static final float VERTICAL_DROP_VELOCITY = 2;
    
    private GameBoard board;
    private boolean paused;
    private BlockGroup blockGroup;
    
    public Game(int height, int width) {
        this.board = new GameBoard(width, height);
    }
    
    public void update(float dt) {
        if (paused) {
            return;
        }
        
        blockGroup.setY(blockGroup.getY() - VERTICAL_DROP_VELOCITY * dt);
        
    }
}
