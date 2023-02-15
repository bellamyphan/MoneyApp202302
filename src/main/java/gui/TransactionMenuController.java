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
    private Button onExitThisMenuButton;

    @FXML
    public void onExitThisMenuClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/main-menu-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) onExitThisMenuButton.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }
}
