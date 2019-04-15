package tetris.domain;

import java.util.ArrayDeque;

public class TetrominoPool {

    private ArrayDeque<Tetromino> pool = new ArrayDeque<>();
    TetrominoFactory factory = new TetrominoFactory();

    public TetrominoPool() {
        this.generate();
    }

    public Tetromino getNext() {
        if (pool.size() == 0) {
            this.generate();
        }
        return pool.pop();
    }

    private void generate() {
        this.pool.clear();
        for (TetrominoType type : TetrominoType.values()) {
            this.pool.add(factory.create(type, "#333"));
        }
    }
}
