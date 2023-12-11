package app.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class EventValidator {

    private EventValidator() {
    }


    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2023;


    /**
     * @param dateStr
     * @return
     */
    public static boolean isValidDate(final String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            int year = date.getYear();

            boolean isLeapYear = date.isLeapYear();
            int februaryMaxDays = isLeapYear ? 29 : 28;

            if (day < MIN_DAY || day > MAX_DAY
                    || month < MIN_MONTH || month > MAX_MONTH
                    || year < MIN_YEAR || year > MAX_YEAR) {
                return false;
            }

            if (month == 2 && (day > februaryMaxDays)) {
                return false;
            }

            return true;
        } catch (DateTimeException | NullPointerException e) {
            return false;
        }
    }
}
