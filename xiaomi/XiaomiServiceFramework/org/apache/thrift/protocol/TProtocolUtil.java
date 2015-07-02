package org.apache.thrift.protocol;

import com.xiaomi.push.service.PushConstants;
import com.xiaomi.xmsf.push.service.Constants;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;

public class TProtocolUtil {
    private static int maxSkipDepth;

    static {
        maxSkipDepth = Integer.MAX_VALUE;
    }

    public static void setMaxSkipDepth(int depth) {
        maxSkipDepth = depth;
    }

    public static void skip(TProtocol prot, byte type) throws TException {
        skip(prot, type, maxSkipDepth);
    }

    public static void skip(TProtocol prot, byte type, int maxDepth) throws TException {
        if (maxDepth <= 0) {
            throw new TException("Maximum skip depth exceeded");
        }
        int i;
        switch (type) {
            case TTransportException.ALREADY_OPEN /*2*/:
                prot.readBool();
                return;
            case TTransportException.TIMED_OUT /*3*/:
                prot.readByte();
                return;
            case TTransportException.END_OF_FILE /*4*/:
                prot.readDouble();
                return;
            case TApplicationException.INTERNAL_ERROR /*6*/:
                prot.readI16();
                return;
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                prot.readI32();
                return;
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                prot.readI64();
                return;
            case PushConstants.ERROR_RESET /*11*/:
                prot.readBinary();
                return;
            case PushConstants.ERROR_NO_CLIENT /*12*/:
                prot.readStructBegin();
                while (true) {
                    TField field = prot.readFieldBegin();
                    if (field.type == (byte) 0) {
                        prot.readStructEnd();
                        return;
                    } else {
                        skip(prot, field.type, maxDepth - 1);
                        prot.readFieldEnd();
                    }
                }
            case PushConstants.ERROR_SERVER_STREAM /*13*/:
                TMap map = prot.readMapBegin();
                for (i = 0; i < map.size; i++) {
                    skip(prot, map.keyType, maxDepth - 1);
                    skip(prot, map.valueType, maxDepth - 1);
                }
                prot.readMapEnd();
                return;
            case PushConstants.ERROR_THREAD_BLOCK /*14*/:
                TSet set = prot.readSetBegin();
                for (i = 0; i < set.size; i++) {
                    skip(prot, set.elemType, maxDepth - 1);
                }
                prot.readSetEnd();
                return;
            case PushConstants.ERROR_SERVICE_DESTROY /*15*/:
                TList list = prot.readListBegin();
                for (i = 0; i < list.size; i++) {
                    skip(prot, list.elemType, maxDepth - 1);
                }
                prot.readListEnd();
                return;
            default:
                return;
        }
    }
}
