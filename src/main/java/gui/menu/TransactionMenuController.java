package gui.menu;

import application.SystemConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class TransactionMenuController {
    @FXML
    public void addATransactionOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToView(actionEvent, SystemConfiguration.addATransactionViewPath);
    }

    @FXML
    public void modifyATransactionOnAction() {
    }

    @FXML
    public void deleteATransactionOnAction() {
    }

    @FXML
    public void markATransactionRepeatable() {
    }

    @FXML
    public void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToView(actionEvent, SystemConfiguration.mainMenuViewPath);
    }
}
