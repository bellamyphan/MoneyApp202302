package tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class StageHandler {
    public void goToView(ActionEvent actionEvent, String viewPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
        Scene scene = new Scene(loader.load());
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        setStageMaximized(stage);
        stage.show();
    }

    public void goToView(Stage stage, String viewPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        setStageMaximized(stage);
        stage.show();
    }

    private void setStageMaximized(Stage stage) {
        String osName = System.getProperty("os.name");
        if (osName.compareTo("Windows 10") == 0 || osName.compareTo("Windows 11") == 0) {
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(screenBounds.getMinX());
            stage.setY(screenBounds.getMinY());
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());
        } else {
            stage.setMaximized(true);
        }
    }
}
