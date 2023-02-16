package gui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class TransactionMenuController {
    @FXML
    public void addATransactionOnAction() {
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
        new StageHandler().goToMainMenu(actionEvent);
    }
}
