package tetris;

import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tetris.domain.Game;
import tetris.ui.GameViewController;
import tetris.ui.PaneManager;

public class MainApp extends Application {

    public static PaneManager paneManager;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        this.scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        paneManager = new PaneManager(scene);

        stage.setTitle("Tetris");
        stage.setScene(scene);
        paneManager.openMenu();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void startGame() throws InterruptedException, IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/GameView.fxml"));
        scene.setRoot((Pane) loader.load());
        GameViewController controller = (GameViewController) loader.getController();

        Game game = new Game(30, 15);
        controller.setGame(game);

        long lastTime = System.nanoTime();
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                float dt = (float) (now - lastTime) / 1000000000;
                dt = 0.00001f;
                game.update(0.1f);
                controller.render();
            }
        };
        animator.start();

    }
}
