module application {
    requires javafx.controls;
    requires javafx.fxml;

    opens application to javafx.fxml;
    exports application;
    exports gui.menu;
    opens gui.menu to javafx.fxml;
}