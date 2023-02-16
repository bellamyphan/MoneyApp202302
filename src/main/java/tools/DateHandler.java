package tools;

import application.SystemConfiguration;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHandler {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(SystemConfiguration.dateFormat);

    public void formatDatePicker(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    return dateTimeFormatter.format(localDate);
                } else {
                    System.out.println("Null returned");
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateTimeFormatter);
                } else {
                    System.out.println("Null returned");
                    return null;
                }
            }
        });
    }
}
