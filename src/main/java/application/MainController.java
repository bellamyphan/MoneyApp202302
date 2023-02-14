package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button onStartButtonClick;

    @FXML
    public void onStartButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu-view.fxml"));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(loader.load(), bounds.getWidth(), bounds.getHeight());
        Stage stage = (Stage) onStartButtonClick.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onExitButtonClick() {
        System.exit(0);
    }
}
