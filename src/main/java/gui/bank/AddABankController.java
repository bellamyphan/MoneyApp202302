package gui.bank;

import application.SystemConfiguration;
import dao.bank.BankWriterDao;
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
import objects.bank.BankType;
import objects.bank.BankTypeHandler;
import tools.DateHandler;
import tools.StageHandler;
import tools.StringHandler;

import java.io.IOException;
import java.math.BigDecimal;

public class AddABankController {
    @FXML
    private Button confirmButton, reviewButton;
    @FXML
    private TextField bankNameTextField, websiteTextField, accountNameTextField, interestRateTextField;
    @FXML
    private DatePicker openDatePicker, closeDatePicker;
    @FXML
    private Text feedbackText;
    @FXML
    private ComboBox<BankType> accountTypeComboBox;

    @FXML
    private void initialize() {
        // Load bank type combo box
        ObservableList<BankType> bankTypeObservableList = FXCollections.observableArrayList();
        bankTypeObservableList.addAll(new BankTypeHandler().getBankTypeList());
        accountTypeComboBox.setItems(bankTypeObservableList);
        // Format date picker
        DateHandler.formatDatePicker(openDatePicker);
        DateHandler.formatDatePicker(closeDatePicker);
    }

    @FXML
    private void reviewOnAction() {
        if (bankNameTextField.getText().length() == 0) {
            feedbackText.setText("Enter bank name");
            return;
        }
        if (websiteTextField.getText().length() == 0) {
            feedbackText.setText("Enter bank website");
            return;
        }
        if (accountNameTextField.getText().length() == 0) {
            feedbackText.setText("Enter account name");
            return;
        }
        if (openDatePicker.getValue() == null) {
            feedbackText.setText("Select open date");
            return;
        }
        if (accountTypeComboBox.getValue() == null) {
            feedbackText.setText("Select account type");
            return;
        }
        if (interestRateTextField.getText().length() == 0) {
            interestRateTextField.setText("00.0000%");
        }
        feedbackText.setText("Click 'Confirm' to finish");
        confirmButton.setVisible(true);
    }

    @FXML
    private void confirmOnAction() {
        // Finalized all input fields
        bankNameTextField.setEditable(false);
        websiteTextField.setEditable(false);
        accountNameTextField.setEditable(false);
        openDatePicker.setDisable(true);
        closeDatePicker.setDisable(true);
        accountTypeComboBox.setDisable(true);
        interestRateTextField.setEditable(false);
        reviewButton.setDisable(true);
        confirmButton.setDisable(true);
        feedbackText.setText("Bank added successfully");
        // Add the bank to the database
        BankObject bankObject = new BankObject(bankNameTextField.getText(), websiteTextField.getText(),
                accountNameTextField.getText(), DateHandler.getJavaUtilDate(openDatePicker.getValue().toString()),
                closeDatePicker.getValue() != null ?
                        DateHandler.getJavaUtilDate(closeDatePicker.getValue().toString()) : null,
                accountTypeComboBox.getValue(),
                new BigDecimal(StringHandler.getNumberString(interestRateTextField.getText())));
        new BankWriterDao().addABankToDatabase(bankObject);
    }

    @FXML
    private void goBackOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.bankMenuViewPath);
    }
}
