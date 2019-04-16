package tetris.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class TetrominoFactory {

    private HashMap<TetrominoType, int[][]> types = new HashMap<>();

    /**
     * Creates new TetrominoFactory.
     */
    public TetrominoFactory() {
        types.put(TetrominoType.I, new int[][]{new int[]{-2, -1, 0, 1}, new int[]{-1, -1, -1, -1}});
        types.put(TetrominoType.J, new int[][]{new int[]{-2, -2, -1, 0}, new int[]{0, -1, -1, -1}});
        types.put(TetrominoType.L, new int[][]{new int[]{-2, -1, 0, 0}, new int[]{-1, -1, -1, 0}});
        types.put(TetrominoType.O, new int[][]{new int[]{-1, -1, 0, 0}, new int[]{-1, 0, -1, 0}});
        types.put(TetrominoType.S, new int[][]{new int[]{-2, -1, -1, 0}, new int[]{-1, -1, 0, 0}});
        types.put(TetrominoType.T, new int[][]{new int[]{-2, -1, -1, 0}, new int[]{-1, -1, 0, -1}});
        types.put(TetrominoType.Z, new int[][]{new int[]{-2, -1, -1, 0}, new int[]{0, 0, -1, -1}});
    }

    /**
     * Creates new tetromino of the given type.
     *
     * @param type Type of tetromino
     * @param color Color of tetromino
     * @return new tetromino of given type and color
     */
    public Tetromino create(TetrominoType type, String color) {
        int[][] blockPositions = types.get(type);
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < blockPositions[0].length; i++) {
            blocks.add(new Block(color, blockPositions[0][i], blockPositions[1][i]));
        }
        return new Tetromino(blocks, 0, 0);
    }

}
