package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.PrimaryScreen;

import java.io.IOException;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), PrimaryScreen.primaryScreenWidth, PrimaryScreen.primaryScreenHeight);
        stage.setTitle("Money App - Version 202302");
        stage.setScene(scene);
        stage.show();
    }
}

