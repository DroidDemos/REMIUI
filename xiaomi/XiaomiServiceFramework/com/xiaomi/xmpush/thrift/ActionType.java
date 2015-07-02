package com.xiaomi.xmpush.thrift;

import com.xiaomi.push.service.PushConstants;
import com.xiaomi.xmsf.push.service.Constants;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TEnum;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.transport.TTransportException;

public enum ActionType implements TEnum {
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
    
    private final int value;

    private ActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ActionType findByValue(int value) {
        switch (value) {
            case TTransportException.NOT_OPEN /*1*/:
                return Registration;
            case TTransportException.ALREADY_OPEN /*2*/:
                return UnRegistration;
            case TTransportException.TIMED_OUT /*3*/:
                return Subscription;
            case TTransportException.END_OF_FILE /*4*/:
                return UnSubscription;
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return SendMessage;
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return AckMessage;
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return SetConfig;
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return ReportFeedback;
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return Notification;
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return Command;
            case PushConstants.ERROR_RESET /*11*/:
                return MultiConnectionBroadcast;
            case PushConstants.ERROR_NO_CLIENT /*12*/:
                return MultiConnectionResult;
            case PushConstants.ERROR_SERVER_STREAM /*13*/:
                return ConnectionKick;
            default:
                return null;
        }
    }
}
