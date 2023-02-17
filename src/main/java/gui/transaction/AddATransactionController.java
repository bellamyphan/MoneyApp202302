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
import javafx.scene.text.Text;
import objects.bank.BankObject;
import objects.type.Type;
import objects.type.TypeHandler;
import tools.DateHandler;
import tools.StageHandler;
import tools.UsaStateHandler;

import java.io.IOException;

public class AddATransactionController {
    @FXML
    private Button confirmButton;
    @FXML
    private ComboBox<Type> typeComboBox;
    @FXML
    private ComboBox<String> stateComboBox, cityComboBox, isPendingComboBox;
    @FXML
    private ComboBox<BankObject> primaryBankComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idTextField, amountTextField, noteTextField, nameTextField;
    @FXML
    private Text feedBackText;

    private Type selectedType;
    private String selectedStateName;

    @FXML
    private void initialize() {
        initializeTypeComboBox("");
        initializeStateComboBox("");
        initializeIsPendingComboBox();
        DateHandler.formatDatePicker(datePicker);
    }

    @FXML
    private void typeComboBoxOnAction() {
        selectedType = new TypeHandler().getType(String.valueOf(typeComboBox.getValue()));
        if (selectedType == null) {
            initializeTypeComboBox(String.valueOf(typeComboBox.getValue()));
        } else {
            typeComboBox.setValue(selectedType);
        }
    }

    @FXML
    private void stateComboBoxOnAction() {
        selectedStateName = new UsaStateHandler().getStateName(stateComboBox.getValue());
        if (selectedStateName == null) {
            initializeStateComboBox(stateComboBox.getValue());
        } else {
            stateComboBox.setValue(selectedStateName);
        }
    }

    @FXML
    private void reviewOnAction() {
        // Generate the transaction id
        idTextField.setText("No data to generate the id");
        // Check all text fields
        if (selectedType == null) {
            feedBackText.setText("Select valid type");
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
        if (selectedStateName == null) {
            feedBackText.setText("Select valid state");
            return;
        }
        if (cityComboBox.getValue() == null) {
            feedBackText.setText("Enter the city");
            return;
        }
        if (primaryBankComboBox.getValue() == null) {
            feedBackText.setText("Select primary bank");
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

    private void initializeStateComboBox(String searchString) {
        ObservableList<String> stateObservableList = FXCollections.observableArrayList();
        stateObservableList.addAll(new UsaStateHandler().getStateNames(searchString));
        stateComboBox.setItems(stateObservableList);
    }

    private void initializeIsPendingComboBox() {
        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        stringObservableList.addAll("Yes", "No");
        isPendingComboBox.setItems(stringObservableList);
    }
}
