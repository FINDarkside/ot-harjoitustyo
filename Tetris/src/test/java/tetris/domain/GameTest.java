package tetris.domain;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {

    public GameTest() {
    }

    private static int width = 10;
    private static int height = 20;
    private Game game;

    @Before
    public void setUp() {
        game = new Game(height, width, new Random("test".hashCode()));
    }

    @Test
    public void downInputDropsActiveTetromino() {
        Tetromino activeTetromino = game.getActiveTetromino();
        game.inputDown();
        game.update(1f);
        assertEquals("activeTetromino should change after down input", false, activeTetromino != game.getActiveTetromino());
    }

    @Test
    public void downInputDropsActiveTetrominoInCorrectPosition() {
        TetrominoFactory factory = new TetrominoFactory();
        for (int i = 0; i < 2; i++) {
            Tetromino t = factory.create(TetrominoType.I, "#333");
            t.setX(width / 2);
            t.setY(i + 1);
            game.getBoard().addTetromino(t);
        }
        Tetromino activeTetromino = game.getActiveTetromino();
        game.inputDown();
        game.update(1f);
        assertEquals(2, activeTetromino.getMinY(), 0f);
    }

    @Test
    public void leftInputShouldMoveActiveTetrominoLeft() {
        float x = game.getActiveTetromino().getMinX();
        game.inputLeft();
        game.update(1f);
        assertEquals(x - 1, game.getActiveTetromino().getMinX(), 0f);
    }

    @Test
    public void rightInputShouldMoveActiveTetrominoRight() {
        float x = game.getActiveTetromino().getMaxX();
        game.inputRight();
        game.update(1f);
        assertEquals(x + 1, game.getActiveTetromino().getMaxX(), 0f);
    }

    @Test
    public void rotateInputRotatesTetromino() {
        float x = game.getActiveTetromino().getMaxX();
        Block b = game.getActiveTetromino().getBlocks().get(0);
        int origX = b.getRelativeX();
        int origY = b.getRelativeY();
        game.inputRotate();
        game.update(1f);
        assertEquals(true, b.getRelativeX() != origX || b.getRelativeY() != origY);
    }

    @Test
    public void floatingStaticBlocksFall() {
        TetrominoFactory factory = new TetrominoFactory();
        Tetromino t = factory.create(TetrominoType.T, "#333");
        game.getBoard().addTetromino(t);
        t.setX(2);
        t.setY(height / 2);
        for (int i = 0; i < height * 5; i++) {
            game.update(0.2f);
        }
        boolean[][] usedCells = game.getBoard().getUsedCells();
        assertEquals(true, usedCells[0][2]);
    }

    @Test
    public void gameEndsIfBlocksFillBoard() {
        TetrominoFactory factory = new TetrominoFactory();
        for (int i = 0; i < height; i++) {
            Tetromino t = factory.create(TetrominoType.I, "#333");
            t.setX(width / 2);
            t.setY(i + 1);
            game.getBoard().addTetromino(t);
        }
        game.update(0.2f);
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void predictDropLocationWorks() {
        game.update(0.2f);
        int y = game.predictDropLocation();
        Tetromino t = game.getActiveTetromino();
        game.inputDown();
        game.update(0.2f);
        assertEquals(y, t.getY(), 0f);
    }

    @Test
    public void predictDropLocationWorks2() {
        game.update(0.2f);
        Tetromino t = game.getActiveTetromino();
        TetrominoFactory factory = new TetrominoFactory();
        Tetromino newT = factory.create(TetrominoType.I, "#333");
        newT.setX(width / 2);
        newT.setY(1);
        game.getBoard().addTetromino(newT);
        int y = game.predictDropLocation();
        game.inputDown();
        game.update(0.2f);
        assertEquals(y, t.getY(), 0f);
    }

    @Test
    public void activeTetrominoDoesNotFallIfGameIsPaused() {
        Tetromino t = game.getActiveTetromino();
        float y = t.getY();
        game.setPaused(true);
        game.update(1);
        assertEquals(y, t.getY(), 0f);
    }

}
