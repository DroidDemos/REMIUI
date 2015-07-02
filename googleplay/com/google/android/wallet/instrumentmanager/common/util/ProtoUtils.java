package com.google.android.wallet.instrumentmanager.common.util;

import android.util.Log;
import com.google.android.wallet.instrumentmanager.config.G;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class ProtoUtils {
    private static final boolean PROTO_DEBUG;

    static {
        PROTO_DEBUG = Log.isLoggable("ImProto", 2);
    }

    public static <T extends MessageNano> T parseFrom(byte[] buffer, Class<T> clazz) {
        try {
            return MessageNano.mergeFrom((MessageNano) clazz.newInstance(), buffer);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to parse a known parcelable proto " + clazz.getName());
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to parse a known parcelable proto " + clazz.getName());
        } catch (IOException e3) {
            throw new RuntimeException("Failed to parse a known parcelable proto " + clazz.getName());
        }
    }

    public static <T extends MessageNano> T copyFrom(T messageNano) {
        return parseFrom(MessageNano.toByteArray(messageNano), messageNano.getClass());
    }

    public static void log(MessageNano proto, String logSummary) {
        if (!PROTO_DEBUG) {
            return;
        }
        if (((Boolean) G.allowPiiLogging.get()).booleanValue()) {
            synchronized (ProtoUtils.class) {
                Log.v("ImProto", logSummary);
                for (String logLine : proto.toString().split("\n")) {
                    Log.v("ImProto", "| " + logLine);
                }
            }
            return;
        }
        Log.v("ImProto", "allowPiiLogging needs to be enabled for proto logging");
    }

    public static void logResponse(MessageNano response, String url) {
        log(response, "Response for " + url);
    }

    private ProtoUtils() {
    }
}
