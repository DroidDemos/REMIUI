package com.xiaomi.e.a;

import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public enum a {
    Registration(1),
    UnRegistration(2),
    Subscription(3),
    UnSubscription(4),
    SendMessage(5),
    AckMessage(6),
    SetConfig(7),
    ReportFeedback(8),
    Notification(9),
    Command(10),
    MultiConnectionBroadcast(11),
    MultiConnectionResult(12),
    ConnectionKick(13);
    
    private final int n;

    private a(int i) {
        this.n = i;
    }

    public static a O(int i) {
        switch (i) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return Registration;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return UnRegistration;
            case WindowData.d /*3*/:
                return Subscription;
            case Base64.CRLF /*4*/:
                return UnSubscription;
            case WindowData.f /*5*/:
                return SendMessage;
            case WindowData.g /*6*/:
                return AckMessage;
            case WindowData.h /*7*/:
                return SetConfig;
            case Base64.URL_SAFE /*8*/:
                return ReportFeedback;
            case WindowData.j /*9*/:
                return Notification;
            case WindowData.k /*10*/:
                return Command;
            case 11:
                return MultiConnectionBroadcast;
            case 12:
                return MultiConnectionResult;
            case 13:
                return ConnectionKick;
            default:
                return null;
        }
    }

    public int a() {
        return this.n;
    }
}
