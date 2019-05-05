package tetris.ui;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tetris.domain.Highscore;

public class ScoreSubmitViewController implements Initializable {

    @FXML
    private Label scoreLabel;
    @FXML
    private TextField nameField;

    private int score;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText(String.valueOf(score));
    }

    @FXML
    private void submitClicked(ActionEvent event) throws Exception {
        // TODO: Handle errors!
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        MainApp.instance.getHighscoreDao().save(new Highscore(score, nameField.getText(), dateFormat.format(date)));
        MainApp.instance.getPaneManager().openHighscoreView();
    }

    @FXML
    private void cancelClicked(ActionEvent event) {
        MainApp.instance.getPaneManager().openMenu();
    }
}
