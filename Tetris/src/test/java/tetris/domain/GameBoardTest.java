package tetris.domain;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GameBoardTest {

    public GameBoardTest() {
    }

    private static int n = 10;
    private GameBoard gameBoard;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j >= i) {
                    List<Block> l = new ArrayList<>();
                    l.add(new Block("#000", 0, 0));
                    gameBoard.addTetromino(new Tetromino(l, j, i));
                }
            }
        }
    }

    @Test
    public void isRowFullReturnsFalseWhenRowIsEmpty() {
        for (int i = 1; i < n; i++) {
            assertEquals(false, gameBoard.isRowFull(i));
        }
    }

    @Test
    public void isRowFullReturnsTrueWhenRowIsFull() {
        assertEquals(true, gameBoard.isRowFull(0));
    }

    @Test
    public void clearRowClearsTargetRow() {
        gameBoard.clearRow(n - 1);
        for (int i = 0; i < n; i++) {
            assertEquals(false, gameBoard.getUsedCells()[n - 1][i]);
        }
    }

    @Test
    public void boardCanBeClearedWithClearRow() {
        for (int i = 0; i < n; i++) {
            gameBoard.clearRow(i);
        }
        for (boolean[] ba : gameBoard.getUsedCells()) {
            for (boolean b : ba) {
                assertEquals("All cells should be unused after calling clearRow on all rows", false, b);
            }
        }
    }

    @Test
    public void clearFullRowsClearsOnlyFullRows() {
        gameBoard.clearFullRows();
        for (int i = 0; i < n; i++) {
            boolean usedCellsFound = false;
            for (boolean b : gameBoard.getUsedCells()[i]) {
                if (b) {
                    usedCellsFound = true;
                }
            }
            assertEquals(i != 0, usedCellsFound);
        }
    }

    @Test
    public void collidesWithStaticBlockTest1() {
        List<Block> l = new ArrayList<>();
        l.add(new Block("#000", 0, 0));
        Tetromino tetromino = new Tetromino(l, 0, n - 1);
        assertEquals(false, gameBoard.collidesWithStaticBlocks(tetromino));
    }

    @Test
    public void collidesWithStaticBlockTest2() {
        List<Block> l = new ArrayList<>();
        l.add(new Block("#000", 0, 0));
        l.add(new Block("#000", 1, 0));
        l.add(new Block("#000", 2, 0));
        Tetromino tetromino = new Tetromino(l, 7, n - 1);
        assertEquals(true, gameBoard.collidesWithStaticBlocks(tetromino));
    }

    @Test
    public void clearRowSplitsTetrominos() {
        gameBoard.getTetrominoes().clear();
        List<Block> l = new ArrayList<>();
        l.add(new Block("#000", 0, 4));
        l.add(new Block("#000", 0, 3));
        l.add(new Block("#000", 0, 2));
        Tetromino tetromino = new Tetromino(l, 0, 0);
        gameBoard.addTetromino(tetromino);
        gameBoard.clearRow(3);
        assertEquals(2, gameBoard.getTetrominoes().size());   
    }

}
