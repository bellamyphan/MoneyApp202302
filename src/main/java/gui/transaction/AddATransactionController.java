package gui.transaction;

import application.SystemConfiguration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import objects.type.Type;
import objects.type.TypeHandler;
import tools.DateHandler;
import tools.StageHandler;

import java.io.IOException;

public class AddATransactionController {
    @FXML
    private Button confirmButton;
    @FXML
    private ComboBox<Type> typeComboBox;
    @FXML
    private ComboBox<String> stateComboBox, cityComboBox, isPendingComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idTextField, searchTypeTextField, amountTextField, noteTextField, nameTextField,
            primaryBankTextField, secondaryBankTextField;
    @FXML
    private Text feedBackText;

    @FXML
    private void initialize() {
        initializeTypeComboBox("");
        initializeIsPendingComboBox();
        DateHandler.formatDatePicker(datePicker);
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
    private void reviewOnAction() {
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
        if (stateComboBox.getValue() == null) {
            feedBackText.setText("Enter the state");
            return;
        }
        if (cityComboBox.getValue() == null) {
            feedBackText.setText("Enter the city");
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
        if (isPendingComboBox.getValue() == null) {
            feedBackText.setText("Enter pending status");
            return;
        }
        feedBackText.setText("");
        confirmButton.setVisible(true);
    }

    @FXML
    private void goBackOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.transactionMenuViewPath);
    }

    private void initializeTypeComboBox(String searchType) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        typeObservableList.addAll(new TypeHandler().getTypeList(searchType));
        typeComboBox.setItems(typeObservableList);
    }

    private void initializeIsPendingComboBox() {
        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        stringObservableList.addAll("Yes", "No");
        isPendingComboBox.setItems(stringObservableList);
    }
}
