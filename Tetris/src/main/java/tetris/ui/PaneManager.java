package tetris.ui;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import tetris.MainApp;

public class PaneManager {

    private Scene scene;

    public PaneManager(Scene scene) {
        this.scene = scene;
    }

    public void openMenu() {
        try {
            scene.setRoot(FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")));
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR, "Failed to open main menu. Program will close now.");
            alert.showAndWait();
            Platform.exit();
            System.exit(0);
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
            Alert alert = new Alert(AlertType.ERROR, "Failed to open game view");
            alert.showAndWait();
            MainApp.instance.getPaneManager().openMenu();
        }
    }

    public void openScoreSubmitView(int score) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/ScoreSubmitView.fxml"));
            scene.setRoot((Pane) loader.load());
            ScoreSubmitViewController controller = (ScoreSubmitViewController) loader.getController();
            controller.setScore(score);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR, "Failed to open score submit view");
            alert.showAndWait();
            MainApp.instance.getPaneManager().openMenu();
        }

    }

    public void openHighscoreView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/HighscoreView.fxml"));
            scene.setRoot((Pane) loader.load());
            HighscoreViewController controller = (HighscoreViewController) loader.getController();
            controller.setScores(MainApp.instance.getHighscoreDao().getAll());
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR, "Failed to open highscore view");
            alert.showAndWait();
            MainApp.instance.getPaneManager().openMenu();
        }
    }

}
