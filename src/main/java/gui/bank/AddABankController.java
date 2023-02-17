package gui.bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import objects.bank.BankType;
import objects.bank.BankTypeHandler;

public class AddABankController {
    @FXML
    private ComboBox<BankType> accountTypeComboBox;

    @FXML
    private void initialize() {
        ObservableList<BankType> bankTypeObservableList = FXCollections.observableArrayList();
        bankTypeObservableList.addAll(new BankTypeHandler().getBankTypeList());
        accountTypeComboBox.setItems(bankTypeObservableList);
    }
}
