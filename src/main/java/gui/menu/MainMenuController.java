package gui.menu;

import application.SystemConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.StageHandler;

import java.io.IOException;

public class MainMenuController {
    @FXML
    public void transactionMenuOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToView(actionEvent, SystemConfiguration.transactionMenuViewPath);
    }

    @FXML
    public void bankMenuOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToView(actionEvent, SystemConfiguration.bankMenuViewPath);
    }

    @FXML
    public void reportMenuOnAction() {
    }

    @FXML
    public void exportMenuOnAction() {
    }

    @FXML
    public void investmentAndSavingMenuOnAction() {
    }

    @FXML
    public void netWorthMenuOnAction() {
    }

    @FXML
    public void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToView(actionEvent, SystemConfiguration.applicationViewPath);
    }
}
