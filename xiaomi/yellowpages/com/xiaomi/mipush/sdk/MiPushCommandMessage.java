package com.xiaomi.mipush.sdk;

import java.util.List;

public class MiPushCommandMessage implements a {
    private static final long serialVersionUID = 1;
    private String category;
    private String command;
    private List commandArguments;
    private String reason;
    private long resultCode;

    public List aB() {
        return this.commandArguments;
    }

    public long aC() {
        return this.resultCode;
    }

    public void c(List list) {
        this.commandArguments = list;
    }

    public void f(long j) {
        this.resultCode = j;
    }

    public String getCategory() {
        return this.category;
    }

    public String getCommand() {
        return this.command;
    }

    public String getReason() {
        return this.reason;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String toString() {
        return "command={" + this.command + "}, resultCode={" + this.resultCode + "}, reason={" + this.reason + "}, category={" + this.category + "}, commandArguments={" + this.commandArguments + "}";
    }
}
