package com.xiaomi.push.service;

public interface PacketSendCallback {
    void onAnswer(String str, String str2, String str3, String str4);

    void onFail(PacketSendFailCause packetSendFailCause, String str);

    void onSent();
}
