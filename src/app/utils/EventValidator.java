package app.utils;

import app.Admin;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class EventValidator {

    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2023;

    private static EventValidator instance = null;

    private EventValidator() {
    }

    /**
     * Returns the instance of the EventValidator singleton class.
     *
     * @return The instance of the EventValidator class.
     */
    public static EventValidator getInstance() {
        if (instance == null) {
            instance = new EventValidator();
        }
        return instance;
    }


    /**
     * Validates a date string in the format "dd-MM-yyyy".
     *
     * @param dateStr the date string to validate
     * @return  true if the date string is valid, false otherwise
     */
    public boolean isValidDate(final String dateStr) {
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

            return month != 2 || (day <= februaryMaxDays);
        } catch (DateTimeException | NullPointerException e) {
            return false;
        }
    }
}
