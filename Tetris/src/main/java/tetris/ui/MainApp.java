package tetris.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tetris.dao.*;

public class MainApp extends Application {

    public static MainApp instance;
    private static Scene scene;
    private PaneManager paneManager;
    private HighscoreDao highscoreDao;
    private GameSaveDao gameSaveDao;

    public MainApp() {
        instance = this;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        paneManager = new PaneManager(scene);
        try {
            String currentPath = new File(MainApp.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            highscoreDao = new DbHighscoreDao(DriverManager.getConnection("jdbc:sqlite:" + Paths.get(currentPath, "tetris.db")));
            gameSaveDao = new DbGameSaveDao(DriverManager.getConnection("jdbc:sqlite:" + Paths.get(currentPath, "tetris.db")));
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error when initializing db connections , closing program.\n" + ex.toString());
            alert.showAndWait();
            Platform.exit();
            System.exit(0);
        }
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

    public GameSaveDao getGameSaveDao() {
        return gameSaveDao;
    }

}
