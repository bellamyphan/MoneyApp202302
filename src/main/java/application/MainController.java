package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.StageHandler;

import java.io.IOException;

public class MainController {
    @FXML
    public void startMoneyAppOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SystemConfiguration.mainMenuViewPath));
        Scene scene = new Scene(loader.load());
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }

    @FXML
    public void exitThisApplicationOnAction() {
        System.exit(0);
    }
}
