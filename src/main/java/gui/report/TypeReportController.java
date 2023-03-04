package gui.report;

import application.SystemConfiguration;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import objects.report.ReportList;
import objects.report.ReportObject;
import tools.StageHandler;

import java.io.IOException;

public class TypeReportController {
    @FXML
    private VBox rootVBox, leftCenterVBox, rightCenterVBox;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            ReportList reportList = (ReportList) rootVBox.getScene().getWindow().getUserData();
            loadLeftCenterReportView(reportList);
            loadRightCenterReportView(reportList);
        });
    }

    @FXML
    private void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.mainMenuViewPath);
    }

    private void loadLeftCenterReportView(ReportList reportList) {
        for (ReportObject report : reportList.getReportList()) {
            HBox newHBox = new HBox();
            newHBox.setSpacing(20);
            Label label1 = new Label(report.getType().toString());
            Label label2 = new Label(report.getAmountObject().toString());
            newHBox.getChildren().addAll(label1, label2);
            leftCenterVBox.getChildren().add(newHBox);
        }
    }

    private void loadRightCenterReportView(ReportList reportList) {
        Label label1 = new Label("OVERALL BALANCE");
        Label label2 = new Label(reportList.getOverallBalance().toString());
        rightCenterVBox.getChildren().addAll(label1, label2);
    }
}
