package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tools.StageHandler;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button exitThisMenuButton;
    @FXML
    private Button transactionMenuButton;

    @FXML
    public void transactionMenuOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/transaction-menu-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) transactionMenuButton.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }

    @FXML
    public void bankMenuOnAction() {
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
    public void exitThisMenuOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/application-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) exitThisMenuButton.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }
}
