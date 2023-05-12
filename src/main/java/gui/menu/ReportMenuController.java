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
    private Button inputYearButton, startMonthEndMonthButton, confirmButton,
            quickButton1, quickButton2, quickButton3;
    @FXML
    private Label startDateLabel;
    @FXML
    private TextField startDateTextField;

    private ReportType reportType;
    private final TransactionHandler transactionHandler;

    public ReportMenuController() {
        transactionHandler = new TransactionHandler(new TransactionReaderDao().getTransactions());
    }

    @FXML
    private void initialize() {
        loadQuickButtons();
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
    private void quickButton1OnAction(ActionEvent actionEvent) throws IOException {
        inputYearButton.setDisable(true);
        startMonthEndMonthButton.setDisable(true);
        reportType = ReportType.QUICK_BUTTON_1;
        confirmButtonOnAction(actionEvent);
    }

    @FXML
    private void quickButton2OnAction(ActionEvent actionEvent) throws IOException {
        inputYearButton.setDisable(true);
        startMonthEndMonthButton.setDisable(true);
        reportType = ReportType.QUICK_BUTTON_2;
        confirmButtonOnAction(actionEvent);
    }

    @FXML
    private void quickButton3OnAction(ActionEvent actionEvent) throws IOException {
        inputYearButton.setDisable(true);
        startMonthEndMonthButton.setDisable(true);
        reportType = ReportType.QUICK_BUTTON_3;
        confirmButtonOnAction(actionEvent);
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
        } else if (reportType == ReportType.QUICK_BUTTON_1) {
            Date firstDate = DateHandler.getFirstDayOfThisMonth(quickButton1.getText());
            Date lastDate = DateHandler.getLastDayOfThisMonth(quickButton1.getText());
            List<TransactionObject> filteredTransactions = transactionHandler.getTransactionsFilterByTime(
                    firstDate, lastDate);
            ReportList reportList = ReportHandler.getTypeReports(filteredTransactions);
            confirmButton.getScene().getWindow().setUserData(reportList);
            StageHandler.goToView(actionEvent, SystemConfiguration.typeReportViewPath);
        } else if (reportType == ReportType.QUICK_BUTTON_2) {
            Date firstDate = DateHandler.getFirstDayOfThisMonth(quickButton2.getText());
            Date lastDate = DateHandler.getLastDayOfThisMonth(quickButton2.getText());
            List<TransactionObject> filteredTransactions = transactionHandler.getTransactionsFilterByTime(
                    firstDate, lastDate);
            ReportList reportList = ReportHandler.getTypeReports(filteredTransactions);
            confirmButton.getScene().getWindow().setUserData(reportList);
            StageHandler.goToView(actionEvent, SystemConfiguration.typeReportViewPath);
        } else if (reportType == ReportType.QUICK_BUTTON_3) {
            Date firstDate = DateHandler.getFirstDayOfThisMonth(quickButton3.getText());
            Date lastDate = DateHandler.getLastDayOfThisMonth(quickButton3.getText());
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

    private void loadQuickButtons() {
        // Get current month
        TransactionObject latestTransaction = transactionHandler.getLatestTransactionExcludingFutureTransactions();
        Date minusZeroMonthDate = latestTransaction.getDate();
        // Get previous month
        Date minusOneMonthDate = DateHandler.getMinusOneMonth(DateHandler.getYearMonthString(minusZeroMonthDate));
        // Get previous previous month
        Date minusTwoMonthDate = DateHandler.getMinusOneMonth(DateHandler.getYearMonthString(minusOneMonthDate));
        // Set up quick buttons' names
        quickButton1.setText(DateHandler.getYearMonthString(minusZeroMonthDate));
        quickButton2.setText(DateHandler.getYearMonthString(minusOneMonthDate));
        quickButton3.setText(DateHandler.getYearMonthString(minusTwoMonthDate));
    }
}
