module application {
    requires javafx.controls;
    requires javafx.fxml;

    opens application to javafx.fxml;
    exports application;
    opens gui.menu to javafx.fxml;
    exports gui.menu;
    opens gui.transaction to javafx.fxml;
    exports gui.transaction;
    opens gui.bank to javafx.fxml;
    exports gui.bank;
}