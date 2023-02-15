package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class BankMenuController {
    @FXML
    public void showAllBanksOnAction() {
    }

    @FXML
    public void showAllBankBalancesOnAction() {
    }

    @FXML
    public void addABankOnAction() {
    }

    @FXML
    public void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToMainMenu(actionEvent);
    }
}
