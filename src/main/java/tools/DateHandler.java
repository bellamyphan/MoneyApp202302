package tools;

import application.SystemConfiguration;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern(SystemConfiguration.dateFormat);
    private static final SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat(SystemConfiguration.dateFormat);

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

    public static String getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static String getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return "" + '0' + month;
        } else {
            return "" + month;
        }
    }

    public static Date getFirstDayOfThisMonth(String yearMonthString) {
        return getJavaUtilDateFromString(yearMonthString);
    }

    public static Date getLastDayOfThisMonth(String yearMonthString) {
        Date inputDate = getJavaUtilDateFromString(yearMonthString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String newDateString = getDateString(inputDate).substring(0, 8) + maxDay;
        return getJavaUtilDateFromString(newDateString);
    }

    public static Date getJavaUtilDateFromString(String dateString) {
        if (dateString != null && dateString.length() == 6) {
            dateString = dateString.substring(0, 4) + '-' + dateString.substring(4) + "-01";
            Date date;
            try {
                date = simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return date;
        } else if (dateString != null && dateString.length() == 7) {
            Date date;
            try {
                date = simpleDateFormat.parse(dateString + "-01");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return date;
        } else if (dateString != null && dateString.length() == 10) {
            Date date;
            try {
                date = simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return date;
        } else {
            return null;
        }
    }
}
