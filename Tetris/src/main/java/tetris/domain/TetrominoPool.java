package tetris.domain;

import java.util.ArrayList;
import java.util.Collections;

public class TetrominoPool {

    private ArrayList<Tetromino> pool = new ArrayList<>();
    TetrominoFactory factory = new TetrominoFactory();

    /**
     * Creates new TetrominoPool.
     */
    public TetrominoPool() {
        this.generate();
    }

    /**
     * Returns next tetromino from the pool.
     *
     * @return next tetromino from the pool
     */
    public Tetromino getNext() {
        if (pool.isEmpty()) {
            this.generate();
        }
        return pool.remove(pool.size() - 1);
    }

    /**
     * Generates tetromino pool and shuffles it.
     */
    private void generate() {
        this.pool.clear();
        for (TetrominoType type : TetrominoType.values()) {
            this.pool.add(factory.create(type, "#333"));
        }
        Collections.shuffle(pool);
    }
}
