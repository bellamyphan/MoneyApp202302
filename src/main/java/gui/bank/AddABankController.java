package gui.bank;

import dao.bank.BankWriterDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import tools.StringHandler;

import java.math.BigDecimal;

public class AddABankController {
    @FXML
    private Button confirmButton;
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
        feedbackText.setText("");
        confirmButton.setVisible(true);
    }

    @FXML
    private void confirmOnAction() {
        BankObject bankObject;
        if (closeDatePicker.getValue() != null) {
             bankObject = new BankObject(bankNameTextField.getText(), websiteTextField.getText(),
                    accountNameTextField.getText(), DateHandler.getJavaUtilDate(openDatePicker.getValue().toString()),
                    DateHandler.getJavaUtilDate(closeDatePicker.getValue().toString()),
                    accountTypeComboBox.getValue(),
                    new BigDecimal(StringHandler.getNumberString(interestRateTextField.getText())));
        } else {
            bankObject = new BankObject(bankNameTextField.getText(), websiteTextField.getText(),
                    accountNameTextField.getText(), DateHandler.getJavaUtilDate(openDatePicker.getValue().toString()),
                    null, accountTypeComboBox.getValue(),
                    new BigDecimal(StringHandler.getNumberString(interestRateTextField.getText())));
        }
        new BankWriterDao().addABankToDatabase(bankObject);
    }
}
