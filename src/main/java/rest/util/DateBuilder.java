package rest.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateBuilder {
    public static ZonedDateTime getLocalDateFromDateAndTime (String dateStr, String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return ZonedDateTime.parse(dateStr + " "+ timeStr, formatter);
    }
}
