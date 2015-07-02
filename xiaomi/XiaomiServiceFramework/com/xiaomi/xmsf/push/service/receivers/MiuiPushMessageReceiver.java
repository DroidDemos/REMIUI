package com.xiaomi.xmsf.push.service.receivers;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.xmsf.push.service.BusinessApp;
import com.xiaomi.xmsf.push.service.Constants;
import com.xiaomi.xmsf.push.service.MyLog;
import java.util.List;

public class MiuiPushMessageReceiver extends PushMessageReceiver {
    public void onCommandResult(Context context, MiPushCommandMessage cmdMessage) {
        if (cmdMessage.getResultCode() == 0) {
            String cmd = cmdMessage.getCommand();
            List<String> args = cmdMessage.getCommandArguments();
            if (args.size() > 0 && MiPushClient.COMMAND_REGISTER.equals(cmd)) {
                BusinessApp.getInstance(context).onPushRegisterSuccess((String) args.get(0));
                return;
            }
            return;
        }
        MyLog.e(cmdMessage.toString());
    }

    public void onReceiveMessage(Context context, MiPushMessage message) {
        if (PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI.equals((String) message.getExtra().get(Constants.EXTRA_KEY_ADS))) {
            BusinessApp.getInstance(context).handlePushMessage(message);
            return;
        }
        String pkg = (String) message.getExtra().get(Constants.EXTRA_KEY_PKGNAME);
        if (pkg != null && !pkg.trim().isEmpty()) {
            Intent intent = new Intent();
            intent.setPackage(pkg);
            intent.putExtras(message.toBundle());
            if (message.isNotified()) {
                intent.setAction(Constants.INTENT_ACTION_MIPUSH_MIUI_CLICK_MESSAGE);
                context.startService(intent);
                return;
            }
            intent.setAction(Constants.INTENT_ACTION_MIPUSH_MIUI_RECEIVE_MESSAGE);
            context.sendBroadcast(intent);
        }
    }
}
