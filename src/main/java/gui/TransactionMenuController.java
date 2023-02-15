package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tools.StageHandler;

import java.io.IOException;

public class TransactionMenuController {
    @FXML
    private Button exitThisMenuButton;

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
    public void exitThisMenuOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/main-menu-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) exitThisMenuButton.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }
}
