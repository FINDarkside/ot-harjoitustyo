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
                    gameBoard.addBlockGroup(new BlockGroup(l, j, i));
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
    public void clearRowMovesAboveRowsDown() {
        gameBoard.clearRow(0);
        gameBoard.moveFallingBlocks();
        boolean usedFound = false;
        for (boolean b : gameBoard.getUsedCells()[0]) {
            if (b) {
                usedFound = true;
            }
        }
        assertEquals("At lest 1 cell should be used", true, usedFound);
    }

    @Test
    public void boardCanBeClearedWithClearRow() {
        for (int i = 0; i < n; i++) {
            gameBoard.clearRow(0);
            gameBoard.moveFallingBlocks();
        }
        for (boolean[] ba : gameBoard.getUsedCells()) {
            for (boolean b : ba) {
                assertEquals("All cells should be unused after calling", false, b);
            }
        }
    }

    @Test
    public void moveFallingBlocksMovesBlocksDown() {
        while (gameBoard.moveFallingBlocks()) {
        };
        for (boolean b : gameBoard.getUsedCells()[0]) {
            assertEquals("All cells in bottom row should be usedF", true, b);
        }
    }

}
