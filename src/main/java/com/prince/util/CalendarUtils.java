package com.prince.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.annotation.Nonnull;

public class CalendarUtils {

    private CalendarUtils() {}

    public static final class DateFormatterThreadLocal extends ThreadLocal<DateFormat> {

        @Nonnull
        private final TimeZone timeZone;

        @Nonnull
        private final String datePattern;

        public DateFormatterThreadLocal(@Nonnull String timeZone, @Nonnull String datePattern) {
            this.timeZone = TimeZone.getTimeZone(timeZone);
            this.datePattern = datePattern;
        }

        @Override
        protected DateFormat initialValue() {
            DateFormat dateFormat = new SimpleDateFormat(datePattern);
            dateFormat.setTimeZone(timeZone);
            return dateFormat;
        }
    }
}
