package com.xiaomi.push.service.profile;

import android.util.Pair;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class MessageProfiling {
    private static ConcurrentHashMap<String, Long> sSendingMessages;
    private static Vector<Pair<String, Long>> sSentPerfDatas;

    static {
        sSentPerfDatas = new Vector();
        sSendingMessages = new ConcurrentHashMap();
    }

    public static void onSendingMessage(String msgId) {
        sSendingMessages.put(msgId, Long.valueOf(System.currentTimeMillis()));
    }

    public static void onReceiveSentAck(String msgId, String pktId) {
        if (sSendingMessages.containsKey(msgId)) {
            sSentPerfDatas.add(new Pair(pktId, Long.valueOf(System.currentTimeMillis() - ((Long) sSendingMessages.get(msgId)).longValue())));
            sSendingMessages.remove(msgId);
        }
    }

    public static String getPrefString() {
        StringBuilder sb = new StringBuilder();
        synchronized (sSentPerfDatas) {
            for (int i = 0; i < sSentPerfDatas.size(); i++) {
                Pair<String, Long> p = (Pair) sSentPerfDatas.elementAt(i);
                sb.append((String) p.first).append(":").append(p.second);
                if (i < sSentPerfDatas.size() - 1) {
                    sb.append(";");
                }
            }
            sSentPerfDatas.clear();
        }
        return sb.toString();
    }
}
