package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tools.PrimaryScreen;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button onExitMainMenuButton;

    @FXML
    public void onTransactionMenuClick() {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("application-view.fxml"));
        Scene scene = new Scene(loader.load(), PrimaryScreen.primaryScreenWidth, PrimaryScreen.primaryScreenHeight);
        Stage stage = (Stage) onExitMainMenuButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
