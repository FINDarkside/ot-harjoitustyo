package tetris.domain;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TetrominoTest {

    @Test
    public void oneBlockRotationWorks() {
        int[][] expectedResult = new int[][]{
            new int[]{0, -1},
            new int[]{-1, -1},
            new int[]{-1, 0},
            new int[]{0, 0}
        };
        ArrayList<Block> blocks = new ArrayList();
        blocks.add(new Block("#00", 0, 0));

        Tetromino tetromino = new Tetromino(blocks, 0, 0);
        for (int i = 0; i < expectedResult.length; i++) {
            tetromino.rotateClockwise();
            assertEquals(expectedResult[i][0], tetromino.getBlocks().get(0).getRelativeX());
            assertEquals(expectedResult[i][1], tetromino.getBlocks().get(0).getRelativeY());
        }
    }

    @Test
    public void rotatingMultipleBlocksWork() {
        int[][] expectedResult = new int[][]{
            new int[]{0, -4, 1, -3, 2, -4, 3, -3},
            new int[]{-4, -1, -3, -2, -4, -3, -3, -4},
            new int[]{-1, 3, -2, 2, -3, 3, -4, 2},
            new int[]{3, 0, 2, 1, 3, 2, 2, 3}
        };
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(new Block("#000", 3, 0));
        blocks.add(new Block("#000", 2, 1));
        blocks.add(new Block("#000", 3, 2));
        blocks.add(new Block("#000", 2, 3));

        Tetromino tetromino = new Tetromino(blocks, 0, 0);
        for (int i = 0;
                i < expectedResult.length;
                i++) {
            tetromino.rotateClockwise();
            for (int j = 0; j < expectedResult[i].length; j += 2) {
                assertEquals(expectedResult[i][j], tetromino.getBlocks().get(j / 2).getRelativeX());
                assertEquals(expectedResult[i][j + 1], tetromino.getBlocks().get(j / 2).getRelativeY());
            }
        }
    }

}
