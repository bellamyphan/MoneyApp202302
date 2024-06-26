package application;

import java.util.Locale;

public class SystemConfiguration {
    // System information
    public static final String applicationTitle = "Money App - Version 2023 02";
    public static final String dateFormat = "yyyy-MM-dd";
    public static final Locale locale = Locale.US;

    // FXML view paths
    public static final String applicationViewPath = "/application/application-view.fxml";
    public static final String bankMenuViewPath = "/gui/menu/bank-menu-view.fxml";
    public static final String mainMenuViewPath = "/gui/menu/main-menu-view.fxml";
    public static final String transactionMenuViewPath = "/gui/menu/transaction-menu-view.fxml";
    public static final String reportMenuViewPath = "/gui/menu/report-menu-view.fxml";
    public static final String addATransactionViewPath = "/gui/transaction/add-a-transaction-view.fxml";
    public static final String addABankViewPath = "/gui/bank/add-a-bank-view.fxml";
    public static final String typeReportViewPath = "/gui/report/type-report-view.fxml";

    // Data paths
    public static final String bankDataPath = "data/banks.csv";
    public static final String transactionDataPath = "data/transactions.csv";
    public static final String usStatesPath = "data/usStates.csv";
    public static final String usCitiesPath = "data/usCities.csv";
}
