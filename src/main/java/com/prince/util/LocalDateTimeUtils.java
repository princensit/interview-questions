package com.prince.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalDateTimeUtils {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormat.forPattern(DATE_TIME_PATTERN);

    private LocalDateTimeUtils() {}
}
