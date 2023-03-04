package gui.menu;

import application.SystemConfiguration;
import dao.transaction.TransactionReaderDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.report.ReportHandler;
import objects.report.ReportList;
import objects.report.ReportType;
import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;
import tools.DateHandler;
import tools.StageHandler;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ReportMenuController {
    @FXML
    private Button inputYearButton, startMonthEndMonthButton, confirmButton;
    @FXML
    private Label startDateLabel;
    @FXML
    private TextField startDateTextField;

    private ReportType reportType;
    private final TransactionHandler transactionHandler;

    public ReportMenuController() {
        List<TransactionObject> transactions = new TransactionReaderDao().getTransactions();
        transactionHandler = new TransactionHandler(transactions);
    }

    @FXML
    private void inputMonthButtonOnAction() {
        inputYearButton.setDisable(true);
        startMonthEndMonthButton.setDisable(true);
        startDateLabel.setDisable(false);
        startDateLabel.setText("Year month input");
        startDateTextField.setDisable(false);
        reportType = ReportType.INPUT_MONTH;
    }

    @FXML
    private void startDateTextFieldOnAction() {
        String inputText = startDateTextField.getText();
        if (inputText.length() == 6) {
            inputText = inputText.substring(0, 4) + '-' + inputText.substring(4);
        }
        Date selectedDate = DateHandler.getJavaUtilDateFromString(inputText);
        if (selectedDate != null) {
            confirmButton.setDisable(false);
            startDateTextField.setText(DateHandler.getYear(selectedDate) + '-' + DateHandler.getMonth(selectedDate));
        } else {
            confirmButton.setDisable(true);
            startDateTextField.setText("");
        }
    }

    @FXML
    private void confirmButtonOnAction(ActionEvent actionEvent) throws IOException {
        if (reportType == ReportType.INPUT_MONTH) {
            Date firstDate = DateHandler.getFirstDayOfThisMonth(startDateTextField.getText());
            Date lastDate = DateHandler.getLastDayOfThisMonth(startDateTextField.getText());
            List<TransactionObject> filteredTransactions = transactionHandler.getTransactionsFilterByTime(
                    firstDate, lastDate);
            ReportList reportList = ReportHandler.getTypeReports(filteredTransactions);
            confirmButton.getScene().getWindow().setUserData(reportList);
            StageHandler.goToView(actionEvent, SystemConfiguration.typeReportViewPath);
        }
    }

    @FXML
    private void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.mainMenuViewPath);
    }
}
