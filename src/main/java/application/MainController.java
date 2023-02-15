package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tools.PrimaryScreen;

import java.io.IOException;

public class MainController {
    @FXML
    private Button onStartButtonClick;

    @FXML
    public void onStartButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu-view.fxml"));
        Scene scene = new Scene(loader.load(), PrimaryScreen.primaryScreenWidth, PrimaryScreen.primaryScreenHeight);
        Stage stage = (Stage) onStartButtonClick.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onExitButtonClick() {
        System.exit(0);
    }
}
