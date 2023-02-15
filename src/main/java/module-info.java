module application {
    requires javafx.controls;
    requires javafx.fxml;

    opens application to javafx.fxml;
    exports application;
    opens gui to javafx.fxml;
    exports gui;
}