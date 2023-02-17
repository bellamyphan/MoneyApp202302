package tools;

import application.SystemConfiguration;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHandler {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(SystemConfiguration.dateFormat);
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SystemConfiguration.dateFormat);

    public static void formatDatePicker(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    return dateTimeFormatter.format(localDate);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateTimeFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public static String getDateString(Date date) {
        if (date != null) {
            return simpleDateFormat.format(date);
        } else {
            return "";
        }
    }

    public static Date getJavaUtilDate(String dateString) {
        Date date;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
