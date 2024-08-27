package mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenu{

    @FXML
    void launchContinueGame(ActionEvent event) {
        System.out.print("Game Continued");
    }

    @FXML
    void launchNewGame(ActionEvent event) {
        System.out.print("New Game Started");
    }

    @FXML
    void setDifficulty(ActionEvent event) {
        System.out.print("Set Difficulty");
    }

    @FXML
    void setStage(ActionEvent event) {
        System.out.print("Set Stage Level");
    }

    @FXML
    void showHighScore(ActionEvent event) {
        System.out.print("Show High Score");
    }
}
