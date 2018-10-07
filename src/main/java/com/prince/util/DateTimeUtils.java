package com.prince.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static final String UTC_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final DateTimeFormatter UTC_DATE_FORMATTER =
            DateTimeFormat.forPattern(UTC_DATE_PATTERN).withZoneUTC();

    private DateTimeUtils() {}
}
