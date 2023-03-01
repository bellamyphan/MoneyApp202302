package gui.transaction;

import application.SystemConfiguration;
import dao.bank.BankReaderDao;
import dao.transaction.TransactionReaderDao;
import dao.transaction.TransactionWriterDao;
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
import objects.name.NameHandler;
import objects.note.NoteHandler;
import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;
import objects.type.Type;
import objects.type.TypeDescription;
import objects.type.TypeHandler;
import tools.BooleanHandler;
import tools.DateHandler;
import tools.StageHandler;
import objects.location.UsaStateHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AddATransactionController {
    @FXML
    private Button confirmButton, typeFilterButton;
    @FXML
    private ComboBox<Type> typeComboBox;
    @FXML
    private ComboBox<String> stateComboBox, cityComboBox, isPendingComboBox, noteComboBox, nameComboBox;
    @FXML
    private ComboBox<BankObject> primaryBankComboBox, secondaryBankComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idTextField, amountTextField;
    @FXML
    private Text finalFeedbackText, bankFeedbackText, typeFeedbackText;

    private final List<TransactionObject> transactions;
    private final List<BankObject> banks;
    private final BankHandler bankHandler;
    private final TransactionHandler transactionHandler;
    private final UsaStateHandler stateHandler;
    private Type selectedType;
    private String selectedStateName;
    private String selectedCityName;

    public AddATransactionController() {
        stateHandler = new UsaStateHandler();
        transactions = new TransactionReaderDao().getTransactions();
        transactionHandler = new TransactionHandler(transactions);
        banks = new BankReaderDao().getBanks();
        bankHandler = new BankHandler(banks);
    }

    @FXML
    private void initialize() {
        initializeTypeComboBox("");
        initializeStateComboBox("");
        initializeIsPendingComboBox();
        initializeDatePicker();
        initializePrimaryBankComboBox();
        initializeNameComboBox(transactions);
        DateHandler.formatDatePicker(datePicker);
    }

    @FXML
    private void typeComboBoxOnAction() {
        selectedType = new TypeHandler().getType(String.valueOf(typeComboBox.getValue()));
        if (selectedType == null) {
            initializeTypeComboBox(String.valueOf(typeComboBox.getValue()));
        } else {
            typeComboBox.setValue(selectedType);
            typeFeedbackText.setText(new TypeDescription(selectedType).getDescription());
            initializeNoteComboBox();
            typeFilterButton.setDisable(false);
        }
    }

    @FXML
    private void typeFilterButtonOnAction() {
        List<TransactionObject> filteredTransactions = transactionHandler.getTransactions(selectedType);
        initializeNameComboBox(filteredTransactions);
    }

    @FXML
    private void stateComboBoxOnAction() {
        String comboBoxValue = stateComboBox.getValue();
        selectedStateName = stateHandler.isValidStateName(comboBoxValue) ?
                stateHandler.getStateUsingStateName(comboBoxValue).stateName() : null;
        if (selectedStateName == null) {
            initializeStateComboBox(comboBoxValue);
        } else {
            stateComboBox.setValue(selectedStateName);
            cityComboBox.setDisable(false);
            initializeCityComboBox(stateHandler.getStateUsingStateName(selectedStateName).stateCode(), "");
        }
    }

    @FXML
    private void cityComboBoxOnAction() {
        String comboBoxValue = cityComboBox.getValue();
        UsaCityHandler cityHandler = new UsaCityHandler(stateHandler.getStateUsingStateName(selectedStateName).stateCode());
        selectedCityName = cityHandler.getCityName(comboBoxValue);
        if (selectedCityName == null) {
            initializeCityComboBox(stateHandler.getStateUsingStateName(selectedStateName).stateCode(), comboBoxValue);
        } else {
            cityComboBox.setValue(selectedCityName);
            stateComboBox.setDisable(true);
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
        idTextField.setText(transactionHandler.getAutoGeneratedId().toString());
        // Check all text fields
        if (selectedType == null) {
            finalFeedbackText.setText("Select valid type");
            confirmButton.setVisible(false);
            return;
        }
        if (datePicker.getValue() == null) {
            finalFeedbackText.setText("Enter the date");
            confirmButton.setVisible(false);
            return;
        }
        if (amountTextField.getText().length() == 0) {
            finalFeedbackText.setText("Enter the amount");
            confirmButton.setVisible(false);
            return;
        }
        if (noteComboBox.getValue().length() == 0) {
            finalFeedbackText.setText("Enter the note");
            confirmButton.setVisible(false);
            return;
        }
        if (nameComboBox.getValue().length() == 0) {
            finalFeedbackText.setText("Enter the name");
            confirmButton.setVisible(false);
            return;
        }
        if (selectedStateName == null) {
            finalFeedbackText.setText("Select valid state");
            confirmButton.setVisible(false);
            return;
        }
        if (selectedCityName == null) {
            finalFeedbackText.setText("Select valid city");
            confirmButton.setVisible(false);
            return;
        }
        if (primaryBankComboBox.getValue() == null) {
            finalFeedbackText.setText("Select primary bank");
            confirmButton.setVisible(false);
            return;
        }
        if (isPendingComboBox.getValue() == null) {
            finalFeedbackText.setText("Enter pending status");
            confirmButton.setVisible(false);
            return;
        }
        finalFeedbackText.setText("");
        confirmButton.setVisible(true);
    }

    @FXML
    private void confirmButtonOnAction() {
        // Finalize all input fields
        typeComboBox.setDisable(true);
        datePicker.setDisable(true);
        amountTextField.setDisable(true);
        noteComboBox.setDisable(true);
        nameComboBox.setDisable(true);
        cityComboBox.setDisable(true);
        primaryBankComboBox.setDisable(true);
        secondaryBankComboBox.setDisable(true);
        isPendingComboBox.setDisable(true);
        // Add transaction to the database
        TransactionObject newTransaction = new TransactionObject(Integer.parseInt(idTextField.getText()), null,
                typeComboBox.getValue(), DateHandler.getJavaUtilDate(datePicker.getValue().toString()),
                new AmountObject(new BigDecimal(amountTextField.getText())), noteComboBox.getValue(),
                nameComboBox.getValue(), new LocationObject(cityComboBox.getValue(),
                stateHandler.getStateUsingStateName(stateComboBox.getValue())), primaryBankComboBox.getValue(),
                secondaryBankComboBox.getValue(), BooleanHandler.getBooleanValueFromString(isPendingComboBox.getValue()));
        new TransactionWriterDao().addATransactionToDatabase(newTransaction);
        // Feedback
        finalFeedbackText.setText("Transaction added successfully");
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
        if (banks != null && banks.size() > 0) {
            ObservableList<BankObject> bankObservableList = FXCollections.observableArrayList();
            bankObservableList.addAll(banks);
            primaryBankComboBox.setItems(bankObservableList);
        } else {
            bankFeedbackText.setText("No banks on record");
        }
    }

    private void initializeSecondaryBankComboBox() {
        ObservableList<BankObject> bankObservableList = FXCollections.observableArrayList();
        bankObservableList.addAll(bankHandler.getBanksExclude(primaryBankComboBox.getValue()));
        secondaryBankComboBox.setItems(bankObservableList);
    }

    private void initializeIsPendingComboBox() {
        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        stringObservableList.addAll("Yes", "No");
        isPendingComboBox.setItems(stringObservableList);
    }

    private void initializeNoteComboBox() {
        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        List<TransactionObject> filteredTransactions = transactionHandler.getTransactions(selectedType);
        List<String> notes = NoteHandler.getNoteStringList(filteredTransactions);
        Collections.reverse(notes);
        stringObservableList.addAll(notes);
        noteComboBox.setItems(stringObservableList);
    }

    private void initializeNameComboBox(List<TransactionObject> transactions) {
        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        List<String> names = NameHandler.getNameStringList(transactions);
        Collections.reverse(names);
        stringObservableList.addAll(names);
        nameComboBox.setItems(stringObservableList);
    }

    private void initializeDatePicker() {
        datePicker.setValue(LocalDate.parse(DateHandler.getDateString(new Date())));
    }
}
