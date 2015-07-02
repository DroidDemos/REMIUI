package com.xiaomi.xmpush.thrift;

import com.xiaomi.channel.commonutils.logger.MyLog;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;

public class XmPushThriftSerializeUtils {
    public static <T extends TBase<T, ?>> byte[] convertThriftObjectToBytes(T thriftObj) {
        byte[] bArr = null;
        if (thriftObj != null) {
            try {
                bArr = new TSerializer(new Factory()).serialize(thriftObj);
            } catch (TException e) {
                MyLog.e("convertThriftObjectToBytes catch TException.", e);
            }
        }
        return bArr;
    }

    public static <T extends TBase<T, ?>> void convertByteArrayToThriftObject(T thriftObj, byte[] bytes) throws TException {
        if (bytes == null) {
            throw new TException("the message byte is empty.");
        }
        new TDeserializer().deserialize(thriftObj, bytes);
    }
}
