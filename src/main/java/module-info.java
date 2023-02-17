module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;

    opens application to javafx.fxml;
    exports application;
    opens gui.bank to javafx.fxml;
    exports gui.bank;
    opens gui.menu to javafx.fxml;
    exports gui.menu;
    opens gui.transaction to javafx.fxml;
    exports gui.transaction;
}