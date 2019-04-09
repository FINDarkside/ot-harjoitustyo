package tetris.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tetris.domain.*;

public class GameViewController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private float pixelsPerCell = 15;
    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
    }

    public void setGame(Game game) {
        this.game = game;
        float height = (float) canvas.getHeight();
        float width = (float) canvas.getWidth();
    }

    public void render() {
        float height = (float) canvas.getHeight();
        float width = (float) canvas.getWidth();
        gc.clearRect(0, 0, width, height);

        for (BlockGroup group : game.getBoard().getBlockGroups()) {
            drawBlockGroup(group);
        }
        drawBlockGroup(game.getActiveBlockGroup());
        gc.setFill(Color.BLACK);
        gc.fillRect(1, 0, width, 1);
        gc.fillRect(0, height - 1, width, height - 2);
        gc.fillRect(0, 0, 1, height);
        gc.fillRect(width - 1, 0, width, height);
    }

    private void drawBlockGroup(BlockGroup group) {
        float height = (float) canvas.getHeight();
        float width = (float) canvas.getWidth();
        for (Block block : group.getBlocks()) {
            gc.setFill(Color.BLUE);
            float x = (group.getX() + block.getRelativeX()) * pixelsPerCell;
            float y = height - (group.getY() + block.getRelativeY()) * pixelsPerCell;
            gc.fillRect(x, y - pixelsPerCell, pixelsPerCell, pixelsPerCell);
        }
    }

}
