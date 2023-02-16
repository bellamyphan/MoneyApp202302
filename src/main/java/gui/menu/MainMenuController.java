package gui.menu;

import application.SystemConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.StageHandler;

import java.io.IOException;

public class MainMenuController {
    @FXML
    public void transactionMenuOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SystemConfiguration.transactionMenuViewPath));
        Scene scene = new Scene(loader.load());
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }

    @FXML
    public void bankMenuOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SystemConfiguration.bankMenuViewPath));
        Scene scene = new Scene(loader.load());
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }

    @FXML
    public void reportMenuOnAction() {
    }

    @FXML
    public void exportMenuOnAction() {
    }

    @FXML
    public void investmentAndSavingMenuOnAction() {
    }

    @FXML
    public void netWorthMenuOnAction() {
    }

    @FXML
    public void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SystemConfiguration.applicationViewPath));
        Scene scene = new Scene(loader.load());
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }
}
