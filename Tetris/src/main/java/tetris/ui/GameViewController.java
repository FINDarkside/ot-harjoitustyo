package tetris.ui;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import tetris.MainApp;
import tetris.domain.*;

public class GameViewController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private Canvas nextTetrominoCanvas;
    @FXML
    private Label scoreLabel;
    @FXML
    private Button pauseButton;

    private float pixelsPerCell = 25;
    private int borderWidth = 2;
    private Game game;

    private float canvasHeight;
    private float canvasWidth;

    private AnimationTimer animator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void init(Game newGame) {
        this.game = newGame;

        Scene scene = canvas.getScene();
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    game.inputRotate();
                    event.consume();
                    break;
                case DOWN:
                    game.inputDown();
                    event.consume();
                    break;
                case LEFT:
                    game.inputLeft();
                    event.consume();
                    break;
                case RIGHT:
                    game.inputRight();
                    event.consume();
                    break;
            }
        });

        canvasHeight = 20 * pixelsPerCell;
        canvasWidth = 10 * pixelsPerCell;
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
        startGameLoop();
    }

    private void startGameLoop() {
        GameViewController controller = this;
        animator = new AnimationTimer() {
            private long lastTime;

            @Override
            public void handle(long now) {
                try {
                    float dt = (float) ((now - lastTime) / 1000000) / 1000;
                    dt = Math.min(1f / 30, dt);
                    lastTime = now;
                    game.update(dt);
                    controller.render();
                    scoreLabel.setText("" + game.getScore());
                    if (game.isGameOver()) {
                        animator.stop();
                        gameEnd();
                    }
                } catch (Exception ex) {
                    animator.stop();
                    MainApp.instance.getPaneManager().openMenu();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error in game loop. Returning back to main menu.\n" + ex.toString());
                    alert.show();
                }

            }
        };
        animator.start();
    }

    private void gameEnd() throws IOException {
        System.out.println("Game end");
        MainApp.instance.getPaneManager().openScoreSubmitView(game.getScore());
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
        renderNextTetromino();
    }

    private void drawBlockGroup(Tetromino group, GraphicsContext gc) {
        float height = (float) gc.getCanvas().getHeight();
        float width = (float) gc.getCanvas().getWidth();
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
        float height = (float) gc.getCanvas().getHeight();

        HashSet<Point2D> usedCells = new HashSet<>();
        for (Block block : tetromino.getBlocks()) {
            usedCells.add(new Point2D(block.getRelativeX(), block.getRelativeY()));
        }
        for (Block block : tetromino.getBlocks()) {
            double x = Math.floor((tetromino.getX() + block.getRelativeX()) * pixelsPerCell);
            double y = Math.floor(height - (tetromino.getY() + block.getRelativeY()) * pixelsPerCell);
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

    private void renderNextTetromino() {
        GraphicsContext gc = nextTetrominoCanvas.getGraphicsContext2D();
        float height = (float) nextTetrominoCanvas.getHeight();
        float width = (float) nextTetrominoCanvas.getWidth();
        gc.clearRect(0, 0, width, height);

        Tetromino nextTetromino = game.getTetrominoPool().peekNext();
        float minX = nextTetromino.getMinX() * pixelsPerCell;
        float maxX = nextTetromino.getMaxX() * pixelsPerCell;
        float minY = nextTetromino.getMinY() * pixelsPerCell;
        float maxY = nextTetromino.getMaxY() * pixelsPerCell;

        float centerX = (minX + maxX + pixelsPerCell) / 2;
        float centerY = (minY + maxY + pixelsPerCell) / 2;
        nextTetromino.setX(nextTetromino.getX() + (width / 2 - centerX) / pixelsPerCell);
        nextTetromino.setY(nextTetromino.getY() + (height / 2 - centerY) / pixelsPerCell);

        drawBlockGroup(nextTetromino, gc);
    }

    @FXML
    private void pauseClicked(ActionEvent event) {
        game.setPaused(!game.isPaused());
        if (game.isPaused()) {
            pauseButton.setText("Continue");
        } else {
            pauseButton.setText("Pause");
        }
    }

    @FXML
    private void menuClicked(ActionEvent event) {
        animator.stop();
        MainApp.instance.getPaneManager().openMenu();
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        animator.stop();
        TextInputDialog td = new TextInputDialog("");
        td.setTitle("Save name");
        td.setHeaderText("Set save name");
        td.setContentText("Name");
        Optional<String> result = td.showAndWait();
        if (!result.isPresent()) {
            animator.start();
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        GameSaveData saveData = new GameSaveData(game, result.get(), dateFormat.format(date));
        try {
            MainApp.instance.getGameSaveDao().save(saveData);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save game.\n" + ex.toString());
            alert.showAndWait();
            animator.start();
            return;
        }
        MainApp.instance.getPaneManager().openMenu();
    }
}
