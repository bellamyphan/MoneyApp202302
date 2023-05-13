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
import objects.location.LocationHandler;
import objects.location.LocationObject;
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
import tools.StringHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AddATransactionController {
    @FXML
    private Button reviewButton, confirmButton, listAllNamesButton, listAllLocationsButton;
    @FXML
    private ComboBox<Type> typeComboBox;
    @FXML
    private ComboBox<String> locationComboBox, isPendingComboBox, noteComboBox, nameComboBox;
    @FXML
    private ComboBox<BankObject> primaryBankComboBox, secondaryBankComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idTextField, amountTextField;
    @FXML
    private Text finalFeedbackText, bankFeedbackText, typeFeedbackText, amountFeedbackText;
    private final BankHandler bankHandler;
    private final TransactionHandler transactionHandler;
    private Type selectedType;
    private LocationObject selectedLocation;

    public AddATransactionController() {
        transactionHandler = new TransactionHandler(new TransactionReaderDao().getTransactions());
        bankHandler = new BankHandler(new BankReaderDao().getBanks());
    }

    @FXML
    private void initialize() {
        initializeTypeComboBox("");
        initializeIsPendingComboBox();
        initializeDatePicker();
        initializePrimaryBankComboBox();
        DateHandler.formatDatePicker(datePicker);
    }

    @FXML
    private void typeComboBoxOnAction() {
        // Test if selective type is valid, else return null
        selectedType = new TypeHandler().getType(String.valueOf(typeComboBox.getValue()));
        // Type is null, need to select again
        if (selectedType == null) {
            initializeTypeComboBox(String.valueOf(typeComboBox.getValue()));
        }
        // Valid type selected
        else {
            // Feedback to type combo box
            typeComboBox.setValue(selectedType);
            typeFeedbackText.setText(new TypeDescription(selectedType).getDescription());
            // Initialize note combo box
            initializeNoteComboBox();
            // Initialize name combo box
            listAllNamesButton.setDisable(false);
            List<TransactionObject> filteredTransactions = transactionHandler.getTransactionsFilterByType(selectedType);
            initializeNameComboBox(filteredTransactions);
        }
    }

    @FXML
    private void amountTextFieldOnKeyTyped() {
        // Check valid amount, only contain digits, dot, negative sign
        if (!StringHandler.containsDigitDotNegativeSign(amountTextField.getText())) {
            amountFeedbackText.setText("In valid input - Only accept digits, dot and negative sign");
        } else {
            amountFeedbackText.setText("");
        }
    }

    @FXML
    private void nameComboBoxOnAction() {
        listAllLocationsButton.setDisable(false);
        List<TransactionObject> filteredTransactions = transactionHandler.getTransactionsFilterByName(
                nameComboBox.getValue());
        initializeLocationComboBox(filteredTransactions);
    }

    @FXML
    private void listAllNamesButtonOnAction() {
        initializeNameComboBox(transactionHandler.getTransactions());
    }

    @FXML
    private void locationComboBoxOnAction() {
        selectedLocation = new LocationObject(locationComboBox.getValue());
        if (selectedLocation.isValid()) {
            locationComboBox.setValue(selectedLocation.toString());
        } else {
            locationComboBox.setValue("");
        }
    }

    @FXML
    private void listAllLocationsButtonOnAction() {
        initializeLocationComboBox(transactionHandler.getTransactions());
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
        if (selectedLocation == null || !selectedLocation.isValid()) {
            finalFeedbackText.setText("Enter the location");
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
        locationComboBox.setDisable(true);
        primaryBankComboBox.setDisable(true);
        secondaryBankComboBox.setDisable(true);
        isPendingComboBox.setDisable(true);
        listAllNamesButton.setDisable(true);
        listAllLocationsButton.setDisable(true);
        reviewButton.setDisable(true);
        confirmButton.setDisable(true);

        // Add transaction to the database
        TransactionObject newTransaction = new TransactionObject(Integer.parseInt(idTextField.getText()), null,
                typeComboBox.getValue(), DateHandler.getJavaUtilDateFromString(
                        datePicker.getValue().toString()),
                new AmountObject(new BigDecimal(amountTextField.getText())), noteComboBox.getValue(),
                nameComboBox.getValue(), new LocationObject(locationComboBox.getValue()),
                primaryBankComboBox.getValue(), secondaryBankComboBox.getValue(),
                BooleanHandler.getBooleanValueFromString(isPendingComboBox.getValue()));
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

    private void initializeLocationComboBox(List<TransactionObject> transactions) {
        ObservableList<String> locationObservableList = FXCollections.observableArrayList();
        List<String> locations = LocationHandler.getLocationStringList(transactions);
        Collections.reverse(locations);
        locationObservableList.addAll(locations);
        locationComboBox.setItems(locationObservableList);
    }

    private void initializePrimaryBankComboBox() {
        if (bankHandler.getBanks() != null && bankHandler.getBanks().size() > 0) {
            ObservableList<BankObject> bankObservableList = FXCollections.observableArrayList();
            bankObservableList.addAll(bankHandler.getBanks());
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
        List<TransactionObject> filteredTransactions = transactionHandler.getTransactionsFilterByType(selectedType);
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
        Date defaultDate;
        if (transactionHandler.getTransactions().size() > 0) {
            defaultDate = transactionHandler.getTransactions()
                    .get(transactionHandler.getTransactions().size() - 1).getDate();
        } else {
            defaultDate = new Date();
        }
        datePicker.setValue(LocalDate.parse(DateHandler.getDateString(defaultDate)));
    }
}
