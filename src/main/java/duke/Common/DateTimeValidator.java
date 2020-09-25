package duke.Common;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class DateTimeValidator {


    /** Chosen format for date and time. Date and time input must follow this format */
    private static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");

    /**
     * Returns true if input string follows the format "yyyyMMdd HHmm"
     * Else returns false
     */
    public static boolean isValid(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, f);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /** Returns LocalDateTime converted from input String according to the chosen format*/
    public static LocalDateTime stringToDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, f);
    }

    /** Returns String converted from LocalDateTime according to the chosen format*/
    public static String dateTimeExport(LocalDateTime dateTime) {
        return (dateTime.format(f));
    }


    /**
     * Returns String in a more readable format to be printed for the user
     * Format of returned String is <day><prefix> of <month> <year> <hour>:<minute><am/pm>
     */
    public static String dateTimeToPrint(LocalDateTime dateTime) {
        String prefix;
        switch (dateTime.getDayOfMonth()) {
        case 1:
        case 21:
        case 31: {
            prefix = "st";
            break;
        }
        case 2:
        case 22: {
            prefix = "nd";
            break;
        }
        case 3:
        case 23: {
            prefix = "rd";
            break;
        }
        default:
            prefix = "th";
            break;
        }
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        return (dateTime.getDayOfMonth() + prefix + " of " + dateTime.getMonth() + " " +
                dateTime.getYear() + " " + (hour>12 ? hour-12 : hour) + ":" +
                (minute<10 ? "0" + minute : minute ) + (hour>11 ? "pm" : "am" ) );
    }




}
