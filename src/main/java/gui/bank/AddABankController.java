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
            confirmButton.setVisible(false);
            return;
        }
        if (websiteTextField.getText().length() == 0) {
            websiteTextField.setPromptText("Warning - Empty link");
        }
        if (accountNameTextField.getText().length() == 0) {
            feedbackText.setText("Enter account name");
            confirmButton.setVisible(false);
            return;
        }
        if (openDatePicker.getValue() == null) {
            feedbackText.setText("Select open date");
            confirmButton.setVisible(false);
            return;
        }
        if (accountTypeComboBox.getValue() == null) {
            feedbackText.setText("Select account type");
            confirmButton.setVisible(false);
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
        bankNameTextField.setDisable(true);
        websiteTextField.setDisable(true);
        accountNameTextField.setDisable(true);
        openDatePicker.setDisable(true);
        closeDatePicker.setDisable(true);
        accountTypeComboBox.setDisable(true);
        interestRateTextField.setDisable(true);
        reviewButton.setDisable(true);
        confirmButton.setDisable(true);
        // Add the bank to the database
        BankObject bankObject = new BankObject(bankNameTextField.getText(), websiteTextField.getText(),
                accountNameTextField.getText(), DateHandler.getJavaUtilDateFromString(
                        openDatePicker.getValue().toString()),
                closeDatePicker.getValue() != null ?
                        DateHandler.getJavaUtilDateFromString(closeDatePicker.getValue().toString()) : null,
                accountTypeComboBox.getValue(),
                new BigDecimal(StringHandler.getNumberString(interestRateTextField.getText())));
        new BankWriterDao().addABankToDatabase(bankObject);
        // Feedback
        feedbackText.setText("Bank added successfully");
    }

    @FXML
    private void goBackOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.bankMenuViewPath);
    }
}
