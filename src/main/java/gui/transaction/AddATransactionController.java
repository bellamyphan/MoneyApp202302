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
import objects.amount.AmountObject;
import objects.bank.BankHandler;
import objects.bank.BankObject;
import objects.location.LocationObject;
import objects.location.UsaCityHandler;
import objects.transaction.TransactionObject;
import objects.type.Type;
import objects.type.TypeHandler;
import tools.DateHandler;
import tools.StageHandler;
import objects.location.UsaStateHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class AddATransactionController {
    @FXML
    private Button confirmButton;
    @FXML
    private ComboBox<Type> typeComboBox;
    @FXML
    private ComboBox<String> stateComboBox, cityComboBox, isPendingComboBox;
    @FXML
    private ComboBox<BankObject> primaryBankComboBox, secondaryBankComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idTextField, amountTextField, noteTextField, nameTextField;
    @FXML
    private Text feedBackText;

    private final UsaStateHandler stateHandler;
    private Type selectedType;
    private String selectedStateName;
    private String selectedCityName;

    public AddATransactionController() {
        stateHandler = new UsaStateHandler();
    }

    @FXML
    private void initialize() {
        initializeTypeComboBox("");
        initializeStateComboBox("");
        initializeIsPendingComboBox();
        initializeDatePicker();
        initializePrimaryBankComboBox();
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
        String comboBoxValue = stateComboBox.getValue();
        selectedStateName = stateHandler.isValidStateName(comboBoxValue) ?
                stateHandler.getState(comboBoxValue).stateName() : null;
        if (selectedStateName == null) {
            initializeStateComboBox(comboBoxValue);
        } else {
            stateComboBox.setValue(selectedStateName);
            cityComboBox.setDisable(false);
            initializeCityComboBox(stateHandler.getState(selectedStateName).stateCode(), "");
        }
    }

    @FXML
    private void cityComboBoxOnAction() {
        String comboBoxValue = cityComboBox.getValue();
        UsaCityHandler cityHandler = new UsaCityHandler(selectedStateName);
        selectedCityName = cityHandler.getCityName(comboBoxValue);
        if (selectedCityName == null) {
            initializeCityComboBox(stateHandler.getState(selectedStateName).stateCode(), comboBoxValue);
        } else {
            cityComboBox.setValue(selectedCityName);
        }
    }

    @FXML
    private void primaryBankComboBoxOnAction() {
        secondaryBankComboBox.setDisable(false);
        initializeSecondaryBankComboBox();
    }

    @FXML
    private void reviewButtonOnAction() {
        // Generate the transaction id
        idTextField.setText("No data to generate the id");
        // Check all text fields
        if (selectedType == null) {
            feedBackText.setText("Select valid type");
            confirmButton.setVisible(false);
            return;
        }
        if (datePicker.getValue() == null) {
            feedBackText.setText("Enter the date");
            confirmButton.setVisible(false);
            return;
        }
        if (amountTextField.getText().length() == 0) {
            feedBackText.setText("Enter the amount");
            confirmButton.setVisible(false);
            return;
        }
        if (noteTextField.getText().length() == 0) {
            feedBackText.setText("Enter the note");
            confirmButton.setVisible(false);
            return;
        }
        if (nameTextField.getText().length() == 0) {
            feedBackText.setText("Enter the name");
            confirmButton.setVisible(false);
            return;
        }
        if (selectedStateName == null) {
            feedBackText.setText("Select valid state");
            confirmButton.setVisible(false);
            return;
        }
        if (selectedCityName == null) {
            feedBackText.setText("Select valid city");
            confirmButton.setVisible(false);
            return;
        }
        if (primaryBankComboBox.getValue() == null) {
            feedBackText.setText("Select primary bank");
            confirmButton.setVisible(false);
            return;
        }
        if (isPendingComboBox.getValue() == null) {
            feedBackText.setText("Enter pending status");
            confirmButton.setVisible(false);
            return;
        }
        feedBackText.setText("");
        confirmButton.setVisible(true);
    }

    @FXML
    private void confirmButtonOnAction() {
        typeComboBox.setDisable(true);
        datePicker.setDisable(true);
        amountTextField.setDisable(true);
        noteTextField.setDisable(true);
        nameTextField.setDisable(true);
        stateComboBox.setDisable(true);
        cityComboBox.setDisable(true);
        primaryBankComboBox.setDisable(true);
        secondaryBankComboBox.setDisable(true);
        isPendingComboBox.setDisable(true);

        TransactionObject newTransaction = new TransactionObject(0, null, typeComboBox.getValue(),
                DateHandler.getJavaUtilDate(datePicker.getValue().toString()),
                new AmountObject(new BigDecimal(amountTextField.getText())), noteTextField.getText(),
                nameTextField.getText(), new LocationObject(cityComboBox.getValue(),
                stateHandler.getState(stateComboBox.getValue())), primaryBankComboBox.getValue(),
                secondaryBankComboBox.getValue(), Boolean.parseBoolean(isPendingComboBox.getValue()));

        System.out.println(newTransaction);
    }

    @FXML
    private void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.transactionMenuViewPath);
    }

    private void initializeTypeComboBox(String searchType) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        typeObservableList.addAll(new TypeHandler().getTypeList(searchType));
        typeComboBox.setItems(typeObservableList);
    }

    private void initializeStateComboBox(String stateNameSearch) {
        ObservableList<String> stateObservableList = FXCollections.observableArrayList();
        stateObservableList.addAll(stateHandler.getStateNames(stateNameSearch));
        stateComboBox.setItems(stateObservableList);
    }

    private void initializeCityComboBox(String stateCode, String cityNameSearch) {
        ObservableList<String> cityObservableList = FXCollections.observableArrayList();
        cityObservableList.addAll(new UsaCityHandler(stateCode).getCityNames(cityNameSearch));
        cityComboBox.setItems(cityObservableList);
    }

    private void initializePrimaryBankComboBox() {
        ObservableList<BankObject> bankObservableList = FXCollections.observableArrayList();
        bankObservableList.addAll(new BankHandler().getBanks());
        primaryBankComboBox.setItems(bankObservableList);
    }

    private void initializeSecondaryBankComboBox() {
        ObservableList<BankObject> bankObservableList = FXCollections.observableArrayList();
        bankObservableList.addAll(new BankHandler().getBanksExclude(primaryBankComboBox.getValue()));
        secondaryBankComboBox.setItems(bankObservableList);
    }

    private void initializeIsPendingComboBox() {
        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        stringObservableList.addAll("Yes", "No");
        isPendingComboBox.setItems(stringObservableList);
    }

    private void initializeDatePicker() {
        datePicker.setValue(LocalDate.parse(DateHandler.getDateString(new Date())));
    }
}
