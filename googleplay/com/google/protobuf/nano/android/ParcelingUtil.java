package com.google.protobuf.nano.android;

import android.os.Parcel;
import android.util.Log;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;

final class ParcelingUtil {
    static <T extends MessageNano> T createFromParcel(Parcel in) {
        String className = in.readString();
        byte[] data = in.createByteArray();
        T proto = null;
        try {
            proto = (MessageNano) Class.forName(className).newInstance();
            MessageNano.mergeFrom(proto, data);
            return proto;
        } catch (ClassNotFoundException e) {
            Log.e("ParcelingUtil", "Exception trying to create proto from parcel", e);
            return proto;
        } catch (IllegalAccessException e2) {
            Log.e("ParcelingUtil", "Exception trying to create proto from parcel", e2);
            return proto;
        } catch (InstantiationException e3) {
            Log.e("ParcelingUtil", "Exception trying to create proto from parcel", e3);
            return proto;
        } catch (InvalidProtocolBufferNanoException e4) {
            Log.e("ParcelingUtil", "Exception trying to create proto from parcel", e4);
            return proto;
        }
    }
}
