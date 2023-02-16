package gui.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import objects.Type;
import objects.TypeHandler;

public class AddATransactionController {
    @FXML
    private ComboBox<Type> typeComboBox;

    @FXML
    public void initialize() {
        ObservableList<Type> observableList = FXCollections.observableArrayList();
        observableList.addAll(new TypeHandler().getTypeList());
        typeComboBox.setItems(observableList);
        typeComboBox.setEditable(true);
    }

    @FXML
    public void idOnMouseClicked(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        TextField textField = (TextField) node;
        textField.clear();
        System.out.println("Mouse clicked");
    }

    @FXML
    public void idOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().compareTo(KeyCode.ENTER) == 0) {
            System.out.println("Enter key pressed");
        }
    }
}
