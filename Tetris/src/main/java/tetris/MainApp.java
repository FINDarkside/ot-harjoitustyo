package tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tetris.dao.HighscoreDao;
import tetris.dao.DbHighscoreDao;
import tetris.ui.PaneManager;

public class MainApp extends Application {

    public static MainApp instance;
    private static Scene scene;
    private PaneManager paneManager;
    private HighscoreDao highscoreDao;

    public MainApp() {
        instance = this;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        this.scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        paneManager = new PaneManager(scene);
        highscoreDao = new DbHighscoreDao("jdbc:sqlite::memory:");
        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public PaneManager getPaneManager() {
        return paneManager;
    }

    public HighscoreDao getHighscoreDao() {
        return highscoreDao;
    }

}
