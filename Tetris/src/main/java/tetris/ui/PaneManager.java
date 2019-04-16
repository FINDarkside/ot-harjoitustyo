package tetris.ui;

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
        this.menu = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        this.game = FXMLLoader.load(getClass().getResource("/fxml/GameView.fxml"));
    }
    
    public void openMenu() {
        scene.setRoot(menu);
    }
    
    public void openGameView() {
        scene.setRoot(game);
    }
}
