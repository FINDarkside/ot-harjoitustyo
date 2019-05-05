package tetris.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import tetris.domain.GameSaveData;

public class LoadSaveViewController implements Initializable {

    @FXML
    private VBox saveList;

    private List<GameSaveData> saves;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setSaves(List<GameSaveData> saves) {
        this.saves = saves;
        render();
    }

    private void render() {
        saveList.getChildren().clear();
        for (GameSaveData saveData : saves) {
            saveList.getChildren().add(createSaveRow(saveData));
        }
    }

    private HBox createSaveRow(GameSaveData save) {
        HBox box = new HBox(5);
        box.alignmentProperty().set(Pos.CENTER_LEFT);
        box.setPrefHeight(35);
        box.getChildren().add(createLabel(115, save.getName()));
        box.getChildren().add(createLabel(63, "" + save.getGame().getScore()));
        box.getChildren().add(createLabel(92, save.getDate()));
        box.getChildren().add(createButton("Load", (ActionEvent event) -> {
            MainApp.instance.getPaneManager().openGameView(save.getGame());
        }));
        box.getChildren().add(createButton("Delete", (ActionEvent event) -> {
            try {
                MainApp.instance.getGameSaveDao().delete(save.getId());
                this.saves.remove(save);
                this.render();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
                alert.setHeaderText("Failed to save game");
                alert.show();
            }
        }));
        return box;
    }

    private Label createLabel(double width, String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font: 16 Arial;");
        //label.setPrefHeight(25);
        label.setPrefWidth(width);
        return label;
    }

    private Button createButton(String text, EventHandler<ActionEvent> onClick) {
        Button button = new Button(text);
        button.setStyle("-fx-font: 14 Arial;");
        button.setPrefWidth(66);
        button.onActionProperty().set(onClick);
        return button;
    }

    @FXML
    private void menuClicked(ActionEvent event) {
        MainApp.instance.getPaneManager().openMenu();
    }

}
