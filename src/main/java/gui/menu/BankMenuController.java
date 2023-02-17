package gui.menu;

import application.SystemConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class BankMenuController {
    @FXML
    private void showAllBanksOnAction() {
    }

    @FXML
    private void showAllBankBalancesOnAction() {
    }

    @FXML
    private void addABankOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToView(actionEvent, SystemConfiguration.addABankViewPath);
    }

    @FXML
    private void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToView(actionEvent, SystemConfiguration.mainMenuViewPath);
    }
}
