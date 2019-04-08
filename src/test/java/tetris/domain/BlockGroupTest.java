package tetris.domain;

import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class BlockGroupTest {

    @Test
    public void oneBlockRotationWorks() {
        int[][] expectedResult = new int[][]{
            new int[]{0, -1},
            new int[]{-1, -1},
            new int[]{-1, 0},
            new int[]{0, 0}
        };
        BlockGroupItem[] groupBlocks = new BlockGroupItem[]{
            new BlockGroupItem(new Block("#000"), 0, 0)
        };
        BlockGroup group = new BlockGroup(groupBlocks, 0, 0);
        for (int i = 0; i < expectedResult.length; i++) {
            group.rotateClockwise();
            assertEquals(expectedResult[i][0], group.getBlocks()[0].getRelativeX());
            assertEquals(expectedResult[i][1], group.getBlocks()[0].getRelativeY());
        }
    }

    @Test
    public void rotatingMultipleBlocksWork() {
        int[][] expectedResult = new int[][]{
            new int[]{0, -4, 1 - 3, 2 - 4, 3 - 3},
            new int[]{-4, -1, -3, -2, -4, -6, -3, -4},
            new int[]{-1, 3, -2, 2, -3, 3, -4, 2},
            new int[]{3, 0, 2, 1, 3, 2, 2, 4}
        };
        BlockGroupItem[] groupBlocks = new BlockGroupItem[]{
            new BlockGroupItem(new Block("#000"), 3, 0),
            new BlockGroupItem(new Block("#000"), 2, 1),
            new BlockGroupItem(new Block("#000"), 3, 2),
            new BlockGroupItem(new Block("#000"), 2, 3)
        };
        BlockGroup group = new BlockGroup(groupBlocks, 0, 0);
        for (int i = 0; i < expectedResult.length; i++) {
            group.rotateClockwise();
            for (int j = 0; j < expectedResult[i].length; j += 2) {
                assertEquals(expectedResult[i][j], group.getBlocks()[j / 2].getRelativeX());
                assertEquals(expectedResult[i][j + 1], group.getBlocks()[j / 2].getRelativeY());
            }
        }
    }

}
