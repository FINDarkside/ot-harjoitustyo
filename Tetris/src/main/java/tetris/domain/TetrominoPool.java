package tetris.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TetrominoPool {

    private Random r = new Random();
    private ArrayList<Tetromino> pool = new ArrayList<>();
    TetrominoFactory factory = new TetrominoFactory();

    /**
     * Creates new TetrominoPool.
     * @param r Random instance to use
     */
    public TetrominoPool(Random r) {
        this.r = r;
        this.generate();
    }

    /**
     * Returns and removes next tetromino from the pool.
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
     * Returns next tetromino from the pool.
     *
     * @return next tetromino from the pool
     */
    public Tetromino peekNext() {
        if (pool.isEmpty()) {
            this.generate();
        }
        return pool.get(pool.size() - 1);
    }

    /**
     * Generates tetromino pool and shuffles it.
     */
    private void generate() {
        Random r = new Random();
        this.pool.clear();
        for (TetrominoType type : TetrominoType.values()) {
            String color = "#" + String.format("%02x", r.nextInt(255))
                    + String.format("%02x", r.nextInt(255))
                    + String.format("%02x", r.nextInt(255));
            this.pool.add(factory.create(type, color));
        }
        Collections.shuffle(pool);
    }
}
