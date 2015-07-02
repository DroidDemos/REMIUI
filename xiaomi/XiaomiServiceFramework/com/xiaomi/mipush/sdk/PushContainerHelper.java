package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.Target;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionCommandResult;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendFeedbackResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import com.xiaomi.xmpush.thrift.XmPushActionSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import com.xiaomi.xmsf.push.service.Constants;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.transport.TTransportException;

public class PushContainerHelper {
    private static final byte[] DEFAULT_IV;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$ActionType;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$ActionType = new int[ActionType.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.Registration.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.UnRegistration.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.Subscription.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.UnSubscription.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.SendMessage.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.AckMessage.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.SetConfig.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.ReportFeedback.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.Notification.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.Command.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    static {
        DEFAULT_IV = new byte[]{(byte) 100, (byte) 23, (byte) 84, (byte) 114, (byte) 72, (byte) 0, (byte) 4, (byte) 97, (byte) 73, (byte) 97, (byte) 2, (byte) 52, (byte) 84, (byte) 102, (byte) 18, (byte) 32};
    }

    protected static <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(Context context, T message, ActionType action) {
        return generateRequestContainer(context, message, action, !action.equals(ActionType.Registration));
    }

    protected static <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(Context context, T message, ActionType action, boolean encrypt) {
        byte[] msgbytes = XmPushThriftSerializeUtils.convertThriftObjectToBytes(message);
        if (msgbytes == null) {
            MyLog.warn("invoke convertThriftObjectToBytes method, return null.");
            return null;
        }
        XmPushActionContainer container = new XmPushActionContainer();
        if (encrypt) {
            byte[] keyBytes = Base64Coder.decode(AppInfoHolder.getInstance(context).getRegSecret());
            MyLog.v(Arrays.toString(keyBytes));
            try {
                msgbytes = MIPushEncrypt(keyBytes, msgbytes);
            } catch (Exception e) {
                MyLog.e("encryption error. ");
            }
        }
        Target target = new Target();
        target.channelId = 5;
        target.userId = "fakeid";
        container.setTarget(target);
        container.setPushAction(ByteBuffer.wrap(msgbytes));
        container.setAction(action);
        container.setIsRequest(true);
        container.setPackageName(context.getPackageName());
        container.setEncryptAction(encrypt);
        container.setAppid(AppInfoHolder.getInstance(context).getAppID());
        return container;
    }

    protected static TBase getResponseMessageBodyFromContainer(Context context, XmPushActionContainer container) throws TException {
        if (container.isEncryptAction()) {
            try {
                byte[] oriMsgBytes = MIPushDecrypt(Base64Coder.decode(AppInfoHolder.getInstance(context).getRegSecret()), container.getPushAction());
            } catch (Exception e) {
                throw new TException("the aes decrypt failed.", e);
            }
        }
        oriMsgBytes = container.getPushAction();
        TBase packet = createRespMessageFromAction(container.getAction());
        if (packet != null) {
            XmPushThriftSerializeUtils.convertByteArrayToThriftObject(packet, oriMsgBytes);
        }
        return packet;
    }

    private static TBase createRespMessageFromAction(ActionType act) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$ActionType[act.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return new XmPushActionRegistrationResult();
            case TTransportException.ALREADY_OPEN /*2*/:
                return new XmPushActionUnRegistrationResult();
            case TTransportException.TIMED_OUT /*3*/:
                return new XmPushActionSubscriptionResult();
            case TTransportException.END_OF_FILE /*4*/:
                return new XmPushActionUnSubscriptionResult();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return new XmPushActionSendMessage();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return new XmPushActionAckMessage();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return new XmPushActionCommandResult();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return new XmPushActionSendFeedbackResult();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return new XmPushActionNotification();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return new XmPushActionCommandResult();
            default:
                return null;
        }
    }

    private static Cipher createCipher(byte[] key, int mode) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(DEFAULT_IV);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, skeySpec, ivSpec);
        return cipher;
    }

    public static byte[] MIPushDecrypt(byte[] key, byte[] input) throws Exception {
        return createCipher(key, 2).doFinal(input);
    }

    public static byte[] MIPushEncrypt(byte[] key, byte[] input) throws Exception {
        return createCipher(key, 1).doFinal(input);
    }
}
