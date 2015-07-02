package com.google.android.finsky.utils;

import android.content.Context;
import com.google.android.finsky.FinskyApp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class DateUtils {
    private static final DateFormat DEVICE_DISPLAY_FORMAT;
    private static final DateFormat DEVICE_DISPLAY_FORMAT_SHORT;
    private static final DateFormat ISO8601_DATE_FORMAT;
    private static final DateFormat UTC_DISPLAY_FORMAT_SHORT;

    static {
        ISO8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        Context ctx = FinskyApp.get();
        DEVICE_DISPLAY_FORMAT = android.text.format.DateFormat.getLongDateFormat(ctx);
        DEVICE_DISPLAY_FORMAT_SHORT = android.text.format.DateFormat.getDateFormat(ctx);
        UTC_DISPLAY_FORMAT_SHORT = android.text.format.DateFormat.getDateFormat(ctx);
        UTC_DISPLAY_FORMAT_SHORT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static synchronized String formatIso8601Date(String iso8601Date) throws ParseException {
        synchronized (DateUtils.class) {
            try {
                iso8601Date = DEVICE_DISPLAY_FORMAT.format(ISO8601_DATE_FORMAT.parse(iso8601Date));
            } catch (ParseException e) {
                if (!Pattern.matches("^\\d\\d\\d\\d$", iso8601Date)) {
                    throw e;
                }
            }
        }
        return iso8601Date;
    }

    public static Date parseDate(String dateString, String dateFormatString) {
        try {
            return new SimpleDateFormat(dateFormatString).parse(dateString);
        } catch (ParseException e) {
            FinskyLog.wtf("Cannot parse date %s", dateString);
            return null;
        }
    }

    public static synchronized String formatDate(Date date) {
        String format;
        synchronized (DateUtils.class) {
            format = DEVICE_DISPLAY_FORMAT.format(date);
        }
        return format;
    }

    public static String formatDate(Date date, String dateFormatString) {
        return new SimpleDateFormat(dateFormatString).format(date);
    }

    public static synchronized String formatShortDisplayDate(long epochMsec) {
        String format;
        synchronized (DateUtils.class) {
            format = DEVICE_DISPLAY_FORMAT_SHORT.format(new Date(epochMsec));
        }
        return format;
    }

    public static synchronized String formatShortDisplayDateUtc(long epochMsec) {
        String format;
        synchronized (DateUtils.class) {
            format = UTC_DISPLAY_FORMAT_SHORT.format(new Date(epochMsec));
        }
        return format;
    }
}
