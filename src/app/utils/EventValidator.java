package app.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class EventValidator {

    private EventValidator() {}
    public static boolean isValidDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            int year = date.getYear();


            if (day <= 0 || day > 31 || month <= 0 || month > 12 || year < 1900 || year > 2023) {
                return false;
            }

            if (month == 2 && (day > 28 || (day > 29 && !date.isLeapYear()))) {
                return false;
            }

            return true;
        } catch (DateTimeException | NullPointerException e) {
            return false;
        }
    }
}