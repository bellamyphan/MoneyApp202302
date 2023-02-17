package gui.menu;

import application.SystemConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class TransactionMenuController {
    @FXML
    private void addATransactionOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.addATransactionViewPath);
    }

    @FXML
    private void modifyATransactionOnAction() {
    }

    @FXML
    private void deleteATransactionOnAction() {
    }

    @FXML
    private void markATransactionRepeatable() {
    }

    @FXML
    private void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.mainMenuViewPath);
    }
}
