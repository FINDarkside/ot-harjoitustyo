/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.util.Arrays;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {

    public GameBoardTest() {
    }

    private static int n = 10;
    private GameBoard gameBoard;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(n, n);
        Block[][] arr = gameBoard.getBoard();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j <= i) {
                    arr[i][j] = new Block("#000");
                }
            }
        }
    }

    @Test
    public void isRowFullReturnsFalseWhenRowIsEmpty() {
        for (int i = 0; i < n - 1; i++) {
            assertEquals(gameBoard.isRowFull(i), false);
        }
    }

    @Test
    public void isRowFullReturnsTrueWhenRowIsFull() {
        assertEquals(gameBoard.isRowFull(n - 1), true);
    }

    @Test
    public void clearRowClearsTargetRow() {
        gameBoard.clearRow(n - 1);
        for (int i = 0; i < n; i++) {
            assertEquals(gameBoard.getBlock(i, n - 1), null);
        }
    }

    @Test
    public void clearRowMovesAboveRowsDown() {
        gameBoard.clearRow(0);
        assertEquals(Arrays.stream(gameBoard.getBoard()[0]).anyMatch(i -> i != null), true);
    }

    @Test
    public void boardCanBeClearedWithClearRow() {
        for (int i = 0; i < n; i++) {
            gameBoard.clearRow(0);
        }
        assertEquals(Arrays.stream(gameBoard.getBoard())
                .allMatch(
                        row -> Arrays.stream(row).allMatch(i -> i == null)
                ), true);
    }
}
