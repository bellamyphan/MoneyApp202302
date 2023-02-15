package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class MainController {
    @FXML
    public void startMoneyAppOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToMainMenu(actionEvent);
    }

    @FXML
    public void exitThisApplicationOnAction() {
        System.exit(0);
    }
}
