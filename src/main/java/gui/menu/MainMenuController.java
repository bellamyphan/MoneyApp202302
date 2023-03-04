package gui.menu;

import application.SystemConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private void transactionMenuOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.transactionMenuViewPath);
    }

    @FXML
    private void bankMenuOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.bankMenuViewPath);
    }

    @FXML
    private void reportMenuOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.reportMenuViewPath);
    }

    @FXML
    private void exportMenuOnAction() {
    }

    @FXML
    private void investmentAndSavingMenuOnAction() {
    }

    @FXML
    private void netWorthMenuOnAction() {
    }

    @FXML
    private void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.applicationViewPath);
    }
}
