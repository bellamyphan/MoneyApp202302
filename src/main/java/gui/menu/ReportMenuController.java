package gui.menu;

import application.SystemConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tools.DateHandler;
import tools.StageHandler;

import java.io.IOException;
import java.util.Date;

public class ReportMenuController {

    @FXML
    private Button inputYearButton, startMonthEndMonthButton, confirmButton;
    @FXML
    private Label startDateLabel;
    @FXML
    private TextField startDateTextField;

    @FXML
    private void inputMonthButtonOnAction() {
        inputYearButton.setDisable(true);
        startMonthEndMonthButton.setDisable(true);
        startDateLabel.setDisable(false);
        startDateLabel.setText("Year month input");
        startDateTextField.setDisable(false);
    }

    @FXML
    private void startDateTextFieldOnAction() {
        String inputText = startDateTextField.getText();
        if (inputText.length() == 6) {
            inputText = inputText.substring(0, 4) + '-' + inputText.substring(4);
        }
        Date selectedDate = DateHandler.getJavaUtilDateFromYearMonthString(inputText);
        if (selectedDate != null) {
            confirmButton.setDisable(false);
            startDateTextField.setText(DateHandler.getYear(selectedDate) + '-' + DateHandler.getMonth(selectedDate));
        } else {
            startDateTextField.setText("");
        }
    }

    @FXML
    private void exitThisMenuOnAction(ActionEvent actionEvent) throws IOException {
        StageHandler.goToView(actionEvent, SystemConfiguration.mainMenuViewPath);
    }
}
