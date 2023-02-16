package application;

import javafx.application.Application;
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
        new StageHandler().startTheApplication(stage);
    }
}

