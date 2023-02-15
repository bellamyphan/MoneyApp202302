package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.StageHandler;

import java.io.IOException;

public class MainApplication extends Application {

    public static void main(String[] args) {
        boolean productionTesting = false;
        if (productionTesting) {
            // Development code here...
            System.exit(0);
        } else {
            launch(args);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Money App - Version 202302");
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }
}

