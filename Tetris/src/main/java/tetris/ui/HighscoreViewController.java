package tetris.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import tetris.MainApp;
import tetris.domain.Highscore;

public class HighscoreViewController implements Initializable {

    @FXML
    private VBox scoreContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private void menuClicked(ActionEvent event) {
        MainApp.instance.getPaneManager().openMenu();
    }

    public void setScores(List<Highscore> scores) {
        scoreContainer.getChildren().clear();
        for (int i = 0; i < scores.size(); i++) {
            scoreContainer.getChildren().add(createScoreRow(i + 1, scores.get(i)));
        }
    }

    private HBox createScoreRow(int num, Highscore highscore) {
        HBox hbox = new HBox();
        hbox.setPrefHeight(50);
        hbox.setPrefWidth(500);
        hbox.getChildren().add(createLabel("#" + num, 100));
        hbox.getChildren().add(createLabel(highscore.getNickname(), 200));
        hbox.getChildren().add(createLabel("" + highscore.getScore(), 100));
        hbox.getChildren().add(createLabel(highscore.getDate(), 100));
        return hbox;
    }

    private Label createLabel(String text, double width) {
        Label label = new Label(text);
        label.setPrefWidth(width);
        label.setPrefHeight(50);
        label.textAlignmentProperty().set(TextAlignment.CENTER);
        return label;
    }

}
