package tetris.domain;

import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class BlockGroupTest {

    private BlockGroup group;

    @Before
    public void setUp() {

    }

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

}
