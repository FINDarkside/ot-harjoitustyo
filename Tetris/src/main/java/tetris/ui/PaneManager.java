package tetris.ui;

import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class PaneManager {

    private Scene scene;
    private Pane menu;
    private Pane game;

    public PaneManager(Scene scene) throws Exception {
        this.scene = scene;
    }

    public void openMenu() throws IOException {
        scene.setRoot(FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")));
    }

    public void openGameView() throws IOException {
        scene.setRoot(FXMLLoader.load(getClass().getResource("/fxml/GameView.fxml")));
    }
}
