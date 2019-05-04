package tetris.ui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import tetris.MainApp;

public class PaneManager {

    private Scene scene;

    public PaneManager(Scene scene) throws Exception {
        this.scene = scene;
    }

    public void openMenu() {
        try {
            scene.setRoot(FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")));
        } catch (Exception ex) {
            // TODO: Show error message?? 
        }
    }

    public void openGameView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/GameView.fxml"));
            scene.setRoot((Pane) loader.load());
            GameViewController controller = (GameViewController) loader.getController();
            controller.setupListeners();
        } catch (Exception ex) {

        }
    }

    public void openScoreSubmitView(int score) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/ScoreSubmitView.fxml"));
        scene.setRoot((Pane) loader.load());
        ScoreSubmitViewController controller = (ScoreSubmitViewController) loader.getController();
        controller.setScore(score);
    }

    public void openHighscoreView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/HighscoreView.fxml"));
            scene.setRoot((Pane) loader.load());
            HighscoreViewController controller = (HighscoreViewController) loader.getController();
            controller.setScores(MainApp.instance.getHighscoreDao().getAll());
        } catch (Exception ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }
    }

}
