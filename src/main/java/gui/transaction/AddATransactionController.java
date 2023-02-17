package gui.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import objects.type.Type;
import objects.type.TypeHandler;
import tools.DateHandler;

public class AddATransactionController {
    @FXML
    private ComboBox<Type> typeComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idTextField, searchTypeTextField, amountTextField, noteTextField, nameTextField,
            locationTextField, primaryBankTextField, secondaryBankTextField, isPendingTextField;
    @FXML
    private Text feedBackText;

    @FXML
    private void initialize() {
        initializeTypeComboBox("");
        new DateHandler().formatDatePicker(datePicker);
    }

    @FXML
    private void searchTypeOnKeyTyped(KeyEvent keyEvent) {
        TextField textField = (TextField) keyEvent.getSource();
        initializeTypeComboBox(textField.getText());
    }

    @FXML
    private void typeComboBoxOnAction() {
        searchTypeTextField.setText("Type selected");
        searchTypeTextField.setEditable(false);
    }

    @FXML
    private void reviewTransactionOnAction() {
        // Generate the transaction id
        idTextField.setText("No data to generate the id");

        // Check all text fields
        if (typeComboBox.getValue() == null) {
            feedBackText.setText("Select a type");
            return;
        }
        if (datePicker.getValue() == null) {
            feedBackText.setText("Enter the date");
            return;
        }
        if (amountTextField.getText().length() == 0) {
            feedBackText.setText("Enter the amount");
            return;
        }
        if (noteTextField.getText().length() == 0) {
            feedBackText.setText("Enter the note");
            return;
        }
        if (nameTextField.getText().length() == 0) {
            feedBackText.setText("Enter the name");
            return;
        }
        if (locationTextField.getText().length() == 0) {
            feedBackText.setText("Enter the location");
            return;
        }
        if (primaryBankTextField.getText().length() == 0) {
            feedBackText.setText("Enter primary bank");
            return;
        }
        if (secondaryBankTextField.getText().length() == 0) {
            feedBackText.setText("Enter secondary bank");
            return;
        }
        if (isPendingTextField.getText().length() == 0) {
            feedBackText.setText("Enter pending status");
            return;
        }
        feedBackText.setText("");
    }

    private void initializeTypeComboBox(String searchType) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        typeObservableList.addAll(new TypeHandler().getTypeList(searchType));
        typeComboBox.setItems(typeObservableList);
    }
}
