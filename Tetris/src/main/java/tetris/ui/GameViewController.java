package tetris.ui;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import tetris.domain.*;

public class GameViewController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private Canvas nextTetrominoCanvas;
    @FXML
    private Label scoreLabel;

    private float pixelsPerCell = 25;
    private int borderWidth = 2;
    private Game game;

    private float canvasHeight;
    private float canvasWidth;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.game = new Game(20, 10);
        System.out.println("NEW GAME");
        canvasHeight = 20 * pixelsPerCell;
        canvasWidth = 10 * pixelsPerCell;
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
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
                scoreLabel.setText("" + game.getScore());
            }
        };
        animator.start();
    }

    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        for (Tetromino group : game.getBoard().getBlockGroups()) {
            drawBlockGroup(group, gc);
        }
        drawBlockGroup(game.getActiveTetromino(), gc);
        gc.setFill(Color.BLACK);
        gc.fillRect(1, 0, canvasWidth, 1);
        gc.fillRect(0, canvasHeight - 1, canvasWidth, canvasHeight - 2);
        gc.fillRect(0, 0, 1, canvasHeight);
        gc.fillRect(canvasWidth - 1, 0, canvasWidth, canvasHeight);
    }

    private void drawBlockGroup(Tetromino group, GraphicsContext gc) {
        float height = (float) canvas.getHeight();
        float width = (float) canvas.getWidth();
        for (Block block : group.getBlocks()) {
            double x = Math.floor((group.getX() + block.getRelativeX()) * pixelsPerCell);
            double y = Math.floor(height - (group.getY() + block.getRelativeY()) * pixelsPerCell);

            Color color = Color.web(block.getColor());
            gc.setFill(color);
            gc.fillRect(x, y - pixelsPerCell, pixelsPerCell, pixelsPerCell);
        }
        drawTetrominoBorders(group, gc);
    }

    private void drawTetrominoBorders(Tetromino tetromino, GraphicsContext gc) {
        HashSet<Point2D> usedCells = new HashSet<>();
        for (Block block : tetromino.getBlocks()) {
            usedCells.add(new Point2D(block.getRelativeX(), block.getRelativeY()));
        }
        for (Block block : tetromino.getBlocks()) {
            double x = Math.floor((tetromino.getX() + block.getRelativeX()) * pixelsPerCell);
            double y = Math.floor(canvasHeight - (tetromino.getY() + block.getRelativeY()) * pixelsPerCell);
            Color color = Color.web(block.getColor()).darker();
            gc.setFill(color);
            if (!usedCells.contains(new Point2D(block.getRelativeX() - 1, block.getRelativeY()))) {
                gc.fillRect(x, y - pixelsPerCell, borderWidth, pixelsPerCell);
            }
            if (!usedCells.contains(new Point2D(block.getRelativeX() + 1, block.getRelativeY()))) {
                gc.fillRect(x + pixelsPerCell - borderWidth, y - pixelsPerCell, borderWidth, pixelsPerCell);
            }
            if (!usedCells.contains(new Point2D(block.getRelativeX(), block.getRelativeY() - 1))) {
                gc.fillRect(x, y - borderWidth, pixelsPerCell, borderWidth);
            }
            if (!usedCells.contains(new Point2D(block.getRelativeX(), block.getRelativeY() + 1))) {
                gc.fillRect(x, y - pixelsPerCell, pixelsPerCell, borderWidth);
            }
        }
    }

}
