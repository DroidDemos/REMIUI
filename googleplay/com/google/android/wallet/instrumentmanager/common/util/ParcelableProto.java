package com.google.android.wallet.instrumentmanager.common.util;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;

public class ParcelableProto<T extends MessageNano> implements Parcelable {
    public static Creator<ParcelableProto<MessageNano>> CREATOR;
    private final T mPayload;
    private byte[] mSerialized;

    private ParcelableProto(T payload) {
        this.mSerialized = null;
        this.mPayload = payload;
    }

    public static <T extends MessageNano> ParcelableProto<T> forProto(T payload) {
        return new ParcelableProto(payload);
    }

    public static <T extends MessageNano> ArrayList<ParcelableProto<T>> forProtoArray(T[] payloads) {
        if (payloads == null) {
            return null;
        }
        ArrayList<ParcelableProto<T>> parcelableProtos = new ArrayList(payloads.length);
        for (T proto : payloads) {
            parcelableProtos.add(forProto(proto));
        }
        return parcelableProtos;
    }

    public static <T extends MessageNano> T getProtoFromBundle(Bundle b, String key) {
        ParcelableProto<T> wrapper = (ParcelableProto) b.getParcelable(key);
        return wrapper != null ? wrapper.getPayload() : null;
    }

    public static <T extends MessageNano> ArrayList<T> getProtoArrayListFromBundle(Bundle b, String key) {
        ArrayList<ParcelableProto<T>> wrapperArrayList = b.getParcelableArrayList(key);
        if (wrapperArrayList == null) {
            return null;
        }
        int length = wrapperArrayList.size();
        ArrayList<T> protosArrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            protosArrayList.add(((ParcelableProto) wrapperArrayList.get(i)).getPayload());
        }
        return protosArrayList;
    }

    public T getPayload() {
        return this.mPayload;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (this.mPayload == null) {
            dest.writeInt(-1);
            return;
        }
        if (this.mSerialized == null) {
            this.mSerialized = MessageNano.toByteArray(this.mPayload);
        }
        dest.writeInt(this.mSerialized.length);
        dest.writeByteArray(this.mSerialized);
        dest.writeString(this.mPayload.getClass().getName());
    }

    static {
        CREATOR = new Creator<ParcelableProto<MessageNano>>() {
            public ParcelableProto<MessageNano> createFromParcel(Parcel source) {
                int size = source.readInt();
                if (size == -1) {
                    return new ParcelableProto(null);
                }
                byte[] payload = new byte[size];
                source.readByteArray(payload);
                String className = source.readString();
                try {
                    MessageNano proto = (MessageNano) Class.forName(className).getConstructor((Class[]) null).newInstance((Object[]) null);
                    proto.mergeFrom(CodedInputByteBufferNano.newInstance(payload));
                    return new ParcelableProto(proto);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Exception when unmarshalling: " + className, e);
                }
            }

            public ParcelableProto<MessageNano>[] newArray(int size) {
                return new ParcelableProto[size];
            }
        };
    }
}
