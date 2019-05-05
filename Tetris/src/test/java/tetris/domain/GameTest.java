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
        game = new Game(height, width);
    }

    @Test
    public void downInputDropsActiveTetromino() {
        Tetromino activeTetromino = game.getActiveTetromino();
        game.inputDown();
        game.update(1f);
        assertEquals("activeTetromino should change after down input", false, activeTetromino != game.getActiveTetromino());
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

}
