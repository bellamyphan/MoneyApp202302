package gui.bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import objects.bank.BankType;
import objects.bank.BankTypeHandler;
import tools.DateHandler;

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
    private void confirmOnAction(/*ActionEvent actionEvent*/) {
//        BankObject bankObject = new BankObject(bankNameTextField.getText(), websiteTextField.getText(),
//                accountNameTextField.getText(), new DateHandler().getJavaUtilDate(openDatePicker.getValue().toString()),
//                new DateHandler().getJavaUtilDate(closeDatePicker.getValue().toString()),
//                accountTypeComboBox.getValue(), Double.parseDouble(interestRateTextField.getText()));
//        new BankWriterDao().addABankToDatabase(bankObject);
    }
}
