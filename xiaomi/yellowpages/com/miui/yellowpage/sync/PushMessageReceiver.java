package com.miui.yellowpage.sync;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.sync.action.UpdateAction;
import com.miui.yellowpage.utils.u;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.b;
import com.xiaomi.mipush.sdk.d;
import com.xiaomi.mipush.sdk.q;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import miui.yellowpage.YellowPageContract.YellowPage;

public class PushMessageReceiver extends b {
    private static final String TAG = "YellowPagePushMessageReceiver";

    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        String command = miPushCommandMessage.getCommand();
        Log.d(TAG, "onCommandResult, command:" + command);
        if (!u.P(context)) {
            return;
        }
        if ("register".equals(command)) {
            if (miPushCommandMessage.aC() == ((long) d.SUCCESS)) {
                u.Q(context);
            } else {
                Log.e(TAG, "failed to register");
            }
        } else if ("set-alias".equals(command)) {
            command = (String) miPushCommandMessage.aB().get(0);
            if (miPushCommandMessage.aC() == ((long) d.SUCCESS)) {
                Log.d(TAG, "successfully subscribed alias");
            } else {
                Log.d(TAG, "failed to subscribe alias");
            }
        } else if (q.LA.equals(command)) {
            if (miPushCommandMessage.aC() == ((long) d.SUCCESS)) {
                command = (String) miPushCommandMessage.aB().get(0);
                Log.d(TAG, "successfully subscribed topic: " + command);
                if (u.bc(command) > 0) {
                    r2 = new ContentValues();
                    r2.put("subscribe_stats", Integer.valueOf(1));
                    context.getContentResolver().update(YellowPage.CONTENT_URI, r2, "yid=?", new String[]{String.valueOf(r0)});
                    return;
                }
                return;
            }
            Log.e(TAG, "failed to subscribe topic:" + miPushCommandMessage.aB());
        } else if (!q.LB.equals(command)) {
        } else {
            if (miPushCommandMessage.aC() == ((long) d.SUCCESS)) {
                command = (String) miPushCommandMessage.aB().get(0);
                Log.d(TAG, "successfully unsubscribed topic:" + command);
                if (u.bc(command) > 0) {
                    r2 = new ContentValues();
                    r2.put("subscribe_stats", Integer.valueOf(4));
                    context.getContentResolver().update(YellowPage.CONTENT_URI, r2, "yid=?", new String[]{String.valueOf(r0)});
                    context.getContentResolver().delete(YellowPage.CONTENT_URI, "yid=?", new String[]{String.valueOf(r0)});
                    return;
                }
                return;
            }
            Log.e(TAG, "failed to unsubscribe topic:" + miPushCommandMessage.aB());
        }
    }

    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
        String content = miPushMessage.getContent();
        String topic = miPushMessage.getTopic();
        int passThrough = miPushMessage.getPassThrough();
        Log.d(TAG, "onReceiveMessage: topic:" + topic + ", content:" + content + ", passthrough:" + passThrough);
        if (u.P(context) && !TextUtils.isEmpty(content)) {
            if (passThrough == 0) {
                try {
                    Intent parseUri = Intent.parseUri(content, 0);
                    if (parseUri != null) {
                        parseUri.addFlags(268435456);
                        context.startActivity(parseUri);
                        return;
                    }
                    return;
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    return;
                }
            }
            UpdateAction i = UpdateAction.i(content);
            if (i != null) {
                Bundle bundle = getBundle(topic, content);
                if (i.cH()) {
                    Account a = c.a(context, true);
                    if (a != null) {
                        ContentResolver.requestSync(a, "miui.yellowpage", bundle);
                        return;
                    }
                    return;
                }
                long randomDelay = getRandomDelay(i.cI());
                Log.d(TAG, "delay " + randomDelay + " milli secs");
                bundle.putLong("delay", randomDelay);
                Account a2 = c.a(context, true);
                if (a2 != null) {
                    ContentResolver.addPeriodicSync(a2, "miui.yellowpage", bundle, i.cI() ? randomDelay / 1000 : TimeUnit.HOURS.toSeconds(1));
                }
            }
        }
    }

    private Bundle getBundle(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("triggered_by_push", true);
        bundle.putString(MiniDefine.at, str2);
        bundle.putString("topic", str);
        bundle.putLong("timestamp", System.currentTimeMillis());
        return bundle;
    }

    private long getRandomDelay(boolean z) {
        Random random = new Random();
        if (z) {
            return (long) random.nextInt((int) TimeUnit.HOURS.toMillis(1));
        }
        return (long) random.nextInt((int) TimeUnit.HOURS.toMillis(48));
    }
}
