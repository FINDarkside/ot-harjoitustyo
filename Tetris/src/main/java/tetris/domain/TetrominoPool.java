package tetris.domain;

import java.util.ArrayList;
import java.util.Collections;

public class TetrominoPool {

    private ArrayList<Tetromino> pool = new ArrayList<>();
    TetrominoFactory factory = new TetrominoFactory();

    public TetrominoPool() {
        this.generate();
    }

    public Tetromino getNext() {
        if (pool.isEmpty()) {
            this.generate();
        }
        return pool.remove(pool.size() - 1);
    }

    private void generate() {
        this.pool.clear();
        for (TetrominoType type : TetrominoType.values()) {
            this.pool.add(factory.create(type, "#333"));
        }
        Collections.shuffle(pool);
    }
}
