package gui.menu;

import application.SystemConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class ReportMenuController {
    @FXML
    private void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.mainMenuViewPath);
    }
}
