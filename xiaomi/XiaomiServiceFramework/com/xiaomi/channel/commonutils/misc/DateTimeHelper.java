package com.xiaomi.channel.commonutils.misc;

import android.text.TextUtils;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.transport.TTransportException;
import org.xml.sax.SAXException;

public class DateTimeHelper {
    private static final String LOG_TAG = "common/DateTimeHelper";
    public static final TimeZone sBeijingTimeZone;
    public static final long sDayInMilliseconds = 86400000;
    public static final long sDayInMinutes = 1440;
    public static final long sHourInMilliseconds = 3600000;
    public static final long sHourInMinutes = 60;
    public static final long sMinuteInMilliseconds = 60000;

    static {
        sBeijingTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
    }

    public static final long getCurrentTiemstamp() {
        return Calendar.getInstance(sBeijingTimeZone).getTimeInMillis();
    }

    public static final long getTodayStartTimestamp() {
        return getTodayStartTimestamp(getCurrentTiemstamp());
    }

    public static final long getTodayStartTimestamp(long timestamp) {
        return timestamp - (timestamp % sDayInMilliseconds);
    }

    public static final long getTomorrowStartTimestamp(long timestamp) {
        return (timestamp - (timestamp % sDayInMilliseconds)) + sDayInMilliseconds;
    }

    public static final long getElapsedMinutesFromToday() {
        return getElapsedMinutesFromToday(getCurrentTiemstamp());
    }

    public static final long getElapsedMinutesFromToday(long timestamp) {
        return (timestamp - getTodayStartTimestamp(timestamp)) / sMinuteInMilliseconds;
    }

    public static final long getElapsedMinutesFromHour() {
        return getElapsedMinutesFromHour(getCurrentTiemstamp());
    }

    public static final long getElapsedMinutesFromHour(long timestamp) {
        return getElapsedMinutesFromToday(timestamp) % sHourInMinutes;
    }

    public static long parseDate(String date) throws SAXException {
        long j = -1;
        if (!TextUtils.isEmpty(date)) {
            GregorianCalendar gc = new GregorianCalendar();
            try {
                gc.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date));
                gc.setTimeZone(sBeijingTimeZone);
                j = gc.getTimeInMillis();
            } catch (ParseException e) {
                Log.e(LOG_TAG, "Failed to parse date", e);
            }
        }
        return j;
    }

    public static String getWeekday(Date date) {
        switch (date.getDay()) {
            case TTransportException.UNKNOWN /*0*/:
                return "\u5468\u65e5";
            case TTransportException.NOT_OPEN /*1*/:
                return "\u5468\u4e00";
            case TTransportException.ALREADY_OPEN /*2*/:
                return "\u5468\u4e8c";
            case TTransportException.TIMED_OUT /*3*/:
                return "\u5468\u4e09";
            case TTransportException.END_OF_FILE /*4*/:
                return "\u5468\u56db";
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return "\u5468\u4e94";
            default:
                return "\u5468\u516d";
        }
    }

    public static String getCurrentString(String format) {
        return new SimpleDateFormat(format).format(new Date(System.currentTimeMillis()));
    }
}
