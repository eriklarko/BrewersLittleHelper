package org.blh.beerxml;

import org.blh.core.unit.Unit;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimePrinter;

/**
 * Basic utility class handling null-safe toStrings and date parsing.
 * TODO: JodaTime is part of Java8, is it not? Remove JodaTime as a dependency.
 *
 * @author thinner
 * @since May 23, 2013 10:56:04 PM
 */
public final class Utils {
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String toStringOrNull(Unit<?> unit) {
        if (unit == null) {
            return null;
        }
        if (unit.value() == null) {
            return null;
        }

        return String.valueOf(unit.value());
    }

    public static String toStringOrNull(String s) {
        return s;
    }

    public static String toStringOrNull(Object e) {
        if (e == null) {
            return null;
        }
        return String.valueOf(e);
    }

    public static String toStringOrNull(boolean b) {
        return String.valueOf(b);
    }

    public static String toStringOrNull(int i) {
        return String.valueOf(i);
    }

    public static String toStringOrNull(double d) {
        return String.valueOf(d);
    }

    public static DateTimeFormatter getAwesomeFormatter() {
        DateTimeParser[] parsers = {
            DateTimeFormat.forPattern(DEFAULT_TIME_FORMAT).getParser(),
            DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
            DateTimeFormat.forPattern("yyyy-MM-dd HH").getParser(),
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").getParser(),
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser(),
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss Z").getParser(),
            DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").getParser(),

            DateTimeFormat.forPattern("d MMM yy").getParser()

        };

        DateTimePrinter printer = DateTimeFormat.forPattern(DEFAULT_TIME_FORMAT).getPrinter();
        return new DateTimeFormatterBuilder().append(printer, parsers).toFormatter();
    }

    private Utils() {
    }
}
