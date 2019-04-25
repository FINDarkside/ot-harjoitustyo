package tetris.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import tetris.domain.*;

public class GameViewController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private float pixelsPerCell = 25;
    private int borderWidth = 2;
    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        this.game = new Game(20, 10);
        System.out.println("NEW GAME");
        canvas.setWidth(10 * pixelsPerCell);
        canvas.setHeight(20 * pixelsPerCell);
        startGameLoop();
    }

    public void setupListeners() {
        Scene scene = canvas.getScene();
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    game.inputRotate();
                    break;
                case DOWN:
                    game.inputDown();
                    break;
                case LEFT:
                    game.inputLeft();
                    break;
                case RIGHT:
                    game.inputRight();
                    break;
            }
        });
    }

    private void startGameLoop() {
        GameViewController controller = this;
        AnimationTimer animator = new AnimationTimer() {
            private long lastTime;

            @Override
            public void handle(long now) {
                float dt = (float) ((now - lastTime) / 1000000) / 1000;
                dt = Math.min(1f / 30, dt);
                lastTime = now;
                game.update(dt);
                controller.render();
            }
        };
        animator.start();
    }

    public void render() {
        float height = (float) canvas.getHeight();
        float width = (float) canvas.getWidth();
        gc.clearRect(0, 0, width, height);

        for (Tetromino group : game.getBoard().getBlockGroups()) {
            drawBlockGroup(group);
        }
        drawBlockGroup(game.getActiveTetromino());
        gc.setFill(Color.BLACK);
        gc.fillRect(1, 0, width, 1);
        gc.fillRect(0, height - 1, width, height - 2);
        gc.fillRect(0, 0, 1, height);
        gc.fillRect(width - 1, 0, width, height);
    }

    private void drawBlockGroup(Tetromino group) {
        float height = (float) canvas.getHeight();
        float width = (float) canvas.getWidth();
        for (Block block : group.getBlocks()) {
            float x = (group.getX() + block.getRelativeX()) * pixelsPerCell;
            float y = height - (group.getY() + block.getRelativeY()) * pixelsPerCell;
            drawBlock(block, x, y);
        }
    }

    private void drawBlock(Block block, float x, float y) {
        Color color = Color.web(block.getColor());
        gc.setFill(color.darker());
        gc.fillRect(x, y - pixelsPerCell, pixelsPerCell, pixelsPerCell);
        gc.setFill(color);
        gc.fillRect(x + borderWidth, y - pixelsPerCell + borderWidth, pixelsPerCell - borderWidth * 2, pixelsPerCell - 2 * borderWidth);
    }

}
