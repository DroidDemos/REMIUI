package com.xiaomi.push.log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;

public class MIPushDebugLog implements LoggerInterface {
    private LoggerInterface sPushLogFileInterface;
    private LoggerInterface sUserLogInterface;

    public MIPushDebugLog(LoggerInterface sUserLogInterface, LoggerInterface sPushLogFileInterface) {
        this.sUserLogInterface = null;
        this.sPushLogFileInterface = null;
        this.sUserLogInterface = sUserLogInterface;
        this.sPushLogFileInterface = sPushLogFileInterface;
    }

    public void setTag(String tag) {
    }

    public void log(String content) {
        if (this.sUserLogInterface != null) {
            this.sUserLogInterface.log(content);
        }
        if (this.sPushLogFileInterface != null) {
            this.sPushLogFileInterface.log(content);
        }
    }

    public void log(String content, Throwable t) {
        if (this.sUserLogInterface != null) {
            this.sUserLogInterface.log(content, t);
        }
        if (this.sPushLogFileInterface != null) {
            this.sPushLogFileInterface.log(content, t);
        }
    }
}
