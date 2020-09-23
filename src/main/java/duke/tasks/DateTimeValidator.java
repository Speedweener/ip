package duke.tasks;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class DateTimeValidator {

    private static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");

    /** Returns the correct character index between date and time if valid, else -1 */
    public static boolean isValid(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, f);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static LocalDateTime stringToDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, f);
    }

    public static String dateTimeExport(LocalDateTime dateTime) {
        return (dateTime.format(f));
    }


    public static String dateTimeToString (LocalDateTime dateTime) {
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
