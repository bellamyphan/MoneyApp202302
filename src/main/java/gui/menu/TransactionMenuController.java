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

public class TransactionMenuController {
    @FXML
    public void addATransactionOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SystemConfiguration.addATransactionViewPath));
        Scene scene = new Scene(loader.load());
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        new StageHandler().setStageMaximized(stage);
        stage.show();
    }

    @FXML
    public void modifyATransactionOnAction() {
    }

    @FXML
    public void deleteATransactionOnAction() {
    }

    @FXML
    public void markATransactionRepeatable() {
    }

    @FXML
    public void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        new StageHandler().goToMainMenu(actionEvent);
    }
}
