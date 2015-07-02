package com.xiaomi.d.c;

public class d {
    private String a;

    public d(String str) {
        this.a = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stream:error (").append(this.a).append(")");
        return stringBuilder.toString();
    }
}
