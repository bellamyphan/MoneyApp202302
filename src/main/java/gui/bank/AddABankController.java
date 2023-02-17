package gui.bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import objects.bank.BankType;
import objects.bank.BankTypeHandler;

public class AddABankController {
    @FXML
    private TextField bankNameTextField, websiteTextField, accountNameTextField, interestRateTextField;
    @FXML
    private DatePicker openDatePicker;
    @FXML
    private Text feedbackText;
    @FXML
    private ComboBox<BankType> accountTypeComboBox;

    @FXML
    private void initialize() {
        ObservableList<BankType> bankTypeObservableList = FXCollections.observableArrayList();
        bankTypeObservableList.addAll(new BankTypeHandler().getBankTypeList());
        accountTypeComboBox.setItems(bankTypeObservableList);
    }

    @FXML
    private void reviewBankOnAction() {
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
    }
}
