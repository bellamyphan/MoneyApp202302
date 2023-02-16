package gui.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import objects.Type;
import objects.TypeHandler;

public class AddATransactionController {
    @FXML
    private ComboBox<Type> typeComboBox;

    @FXML
    public void initialize() {
        initializeTypeComboBox("");
    }

    @FXML
    public void searchTypeOnKeyTyped(KeyEvent keyEvent) {
        TextField textField = (TextField) keyEvent.getSource();
        initializeTypeComboBox(textField.getText());
    }

    private void initializeTypeComboBox(String searchType) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        typeObservableList.addAll(new TypeHandler().getTypeList(searchType));
        typeComboBox.setItems(typeObservableList);
    }
}
