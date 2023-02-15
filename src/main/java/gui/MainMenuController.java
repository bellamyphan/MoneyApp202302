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
    private Button onExitMainMenuButton;
    @FXML
    private Button onTransactionMenuButton;

    @FXML
    public void onTransactionMenuClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/transaction-menu-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) onTransactionMenuButton.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }

    @FXML
    public void onBankMenuClick() {
    }

    @FXML
    public void onReportMenuClick() {
    }

    @FXML
    public void onExportMenuClick() {
    }

    @FXML
    public void onInvestmentAndSavingMenuClick() {
    }

    @FXML
    public void onNetWorthMenuClick() {
    }

    @FXML
    public void onExitMainMenuClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/application-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) onExitMainMenuButton.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }
}
