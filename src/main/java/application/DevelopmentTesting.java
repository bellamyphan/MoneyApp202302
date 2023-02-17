package application;

import javafx.application.Application;
import javafx.stage.Stage;
import tools.StageHandler;

import java.io.IOException;

public class DevelopmentTesting extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        StageHandler.goToView(stage, SystemConfiguration.addATransactionViewPath);
    }
}
