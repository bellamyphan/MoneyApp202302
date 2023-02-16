package gui.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import objects.Type;
import objects.TypeHandler;

public class AddATransactionController {
    @FXML
    private ComboBox<Type> typeComboBox;

    @FXML
    public void initialize() {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList(new TypeHandler().getTypeList());
        typeComboBox.setItems(typeObservableList);
    }
}
