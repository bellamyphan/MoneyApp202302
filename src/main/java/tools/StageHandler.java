package tools;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StageHandler {
    public void setStageMaximized(Stage stage) {
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
