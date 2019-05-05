package tetris.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void startGameClicked(ActionEvent event) {
        MainApp.instance.getPaneManager().openGameView();
    }
    
    @FXML
    private void openScoreBoardClicked(ActionEvent event) {
        MainApp.instance.getPaneManager().openHighscoreView();
    }

    @FXML
    private void loadGameClicked(ActionEvent event) {
        MainApp.instance.getPaneManager().openLoadSaveView();
    }

}
