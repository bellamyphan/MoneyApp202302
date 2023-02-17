package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class MainController {
    @FXML
    private void startMoneyAppOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.mainMenuViewPath);
    }

    @FXML
    private void exitThisApplicationOnAction() {
        System.exit(0);
    }
}
