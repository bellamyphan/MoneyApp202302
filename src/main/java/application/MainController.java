package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tools.StageHandler;

import java.io.IOException;

public class MainController {
    @FXML
    private Button startMoneyAppButton;

    @FXML
    public void startMoneyAppOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SystemConfiguration.mainMenuViewPath));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) startMoneyAppButton.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }

    @FXML
    public void exitThisApplicationOnAction() {
        System.exit(0);
    }
}
