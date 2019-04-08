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
                if (j <= i) {
                    List<Block> l = new ArrayList<>();
                    l.add(new Block("#000", 0, 0));
                    gameBoard.addBlockGroup(new BlockGroup(l, j, i));
                }
            }
        }
    }

    @Test
    public void isRowFullReturnsFalseWhenRowIsEmpty() {
        for (int i = 0; i < n - 1; i++) {
            assertEquals(false, gameBoard.isRowFull(i));
        }
    }

    @Test
    public void isRowFullReturnsTrueWhenRowIsFull() {
        assertEquals(true, gameBoard.isRowFull(n - 1));
    }

    @Test
    public void clearRowClearsTargetRow() {
        gameBoard.clearRow(n - 1);
        for (int i = 0; i < n; i++) {
            assertEquals(false, gameBoard.getUsedCells()[n - 1][i]);
        }
    }

}
