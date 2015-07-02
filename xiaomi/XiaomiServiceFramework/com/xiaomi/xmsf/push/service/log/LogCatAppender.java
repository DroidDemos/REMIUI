package com.xiaomi.xmsf.push.service.log;

import android.util.Log;
import com.xiaomi.network.UploadHostStatHelper;
import com.xiaomi.push.service.XMPushService;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class LogCatAppender extends AppenderSkeleton {
    protected Layout tagLayout;

    public LogCatAppender(Layout messageLayout, Layout tagLayout) {
        this.tagLayout = tagLayout;
        setLayout(messageLayout);
    }

    public LogCatAppender(Layout messageLayout) {
        this(messageLayout, new PatternLayout("%c"));
    }

    public LogCatAppender() {
        this(new PatternLayout("%m%n"));
    }

    protected void append(LoggingEvent le) {
        switch (le.getLevel().toInt()) {
            case 5000:
                if (le.getThrowableInformation() != null) {
                    Log.v(getTagLayout().format(le), getLayout().format(le), le.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.v(getTagLayout().format(le), getLayout().format(le));
                    return;
                }
            case UploadHostStatHelper.UPLOAD_RATIO_MULTIPLE /*10000*/:
                if (le.getThrowableInformation() != null) {
                    Log.d(getTagLayout().format(le), getLayout().format(le), le.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.d(getTagLayout().format(le), getLayout().format(le));
                    return;
                }
            case 20000:
                if (le.getThrowableInformation() != null) {
                    Log.i(getTagLayout().format(le), getLayout().format(le), le.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.i(getTagLayout().format(le), getLayout().format(le));
                    return;
                }
            case XMPushService.TIMER_RESET_CONNECTION /*30000*/:
                if (le.getThrowableInformation() != null) {
                    Log.w(getTagLayout().format(le), getLayout().format(le), le.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.w(getTagLayout().format(le), getLayout().format(le));
                    return;
                }
            case 40000:
                if (le.getThrowableInformation() != null) {
                    Log.e(getTagLayout().format(le), getLayout().format(le), le.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.e(getTagLayout().format(le), getLayout().format(le));
                    return;
                }
            case 50000:
                if (le.getThrowableInformation() != null) {
                    Log.wtf(getTagLayout().format(le), getLayout().format(le), le.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.wtf(getTagLayout().format(le), getLayout().format(le));
                    return;
                }
            default:
                return;
        }
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return true;
    }

    public Layout getTagLayout() {
        return this.tagLayout;
    }

    public void setTagLayout(Layout tagLayout) {
        this.tagLayout = tagLayout;
    }
}
