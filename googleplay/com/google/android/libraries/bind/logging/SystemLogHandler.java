package com.google.android.libraries.bind.logging;

import android.util.Log;

public class SystemLogHandler implements LogHandler {
    public void log(int logLevel, String tag, String message) {
        Log.println(logLevel, tag, message);
    }
}
