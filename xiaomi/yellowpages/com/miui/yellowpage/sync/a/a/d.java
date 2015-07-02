package com.miui.yellowpage.sync.a.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.base.exception.ClientException;
import com.miui.yellowpage.base.exception.NetworkUnavailableException;
import com.miui.yellowpage.base.exception.ServerException;
import com.miui.yellowpage.base.exception.ServiceUnavailableException;
import com.miui.yellowpage.base.provider.InternalYellowPageContract.BuiltinVersion;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.sync.a.a;
import com.miui.yellowpage.sync.action.PresetDataUpdateAction;
import com.miui.yellowpage.sync.action.UpdateAction;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.concurrent.TimeUnit;

/* compiled from: PullTask */
public abstract class d extends a {
    protected long mVersion;

    protected abstract boolean e(Context context, String str);

    protected abstract JSONRequest m(Context context);

    public void j(long j) {
        this.mVersion = j;
    }

    protected String n(Context context) {
        throw new RuntimeException("must override");
    }

    protected String bJ() {
        return "update_time_for_pull_task_" + getClass().getSimpleName();
    }

    public boolean A(Context context) {
        boolean z = false;
        long D = D(context);
        boolean z2 = this.mVersion > D;
        Log.d("PullTask", "shouldPull.version: server:" + this.mVersion + ", local:" + D);
        long B = B(context);
        long currentTimeMillis = System.currentTimeMillis();
        boolean z3;
        if (Math.abs(B - currentTimeMillis) > bK()) {
            z3 = true;
        } else {
            z3 = false;
        }
        Log.d("PullTask", "shouldPull.maxTimeSpan:" + bK());
        Log.d("PullTask", "shouldPull.updateTime: last:" + B + ", current:" + currentTimeMillis);
        if (z2 || r3) {
            z = true;
        }
        Log.d("PullTask", "shouldPull: " + z);
        return z;
    }

    protected long bK() {
        return TimeUnit.DAYS.toMillis(14);
    }

    public long B(Context context) {
        String bJ = bJ();
        Log.d("PullTask", "check update time, pref key:" + bJ);
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(bJ, 0);
    }

    public void d(Context context, long j) {
        String bJ = bJ();
        Log.d("PullTask", "set update time, pref key:" + bJ + ", time:" + j);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(bJ, j).commit();
    }

    public void a(Context context, UpdateAction updateAction) {
        PresetDataUpdateAction presetDataUpdateAction = (PresetDataUpdateAction) updateAction;
        a(context, presetDataUpdateAction.getData(), presetDataUpdateAction.iz());
    }

    public void a(Context context, String str, long j) {
        Log.d("PullTask", "try to update " + getClass().getSimpleName().toLowerCase());
        j(j);
        if (A(context)) {
            if (TextUtils.isEmpty(str)) {
                str = C(context);
            }
            boolean e = e(context, str);
            d(context, System.currentTimeMillis());
            while (e) {
                e = e(context, C(context));
                d(context, System.currentTimeMillis());
            }
            return;
        }
        Log.d("PullTask", "no need to update");
    }

    public String C(Context context) {
        JSONRequest m = m(context);
        if (m == null) {
            return null;
        }
        int status = m.getStatus();
        Log.d("PullTask", "request status:" + status);
        switch (status) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return m.requestData();
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                throw new NetworkUnavailableException();
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                throw new ServiceUnavailableException();
            case WindowData.d /*3*/:
                throw new ClientException();
            case Base64.CRLF /*4*/:
                throw new ServerException();
            default:
                throw new IllegalStateException();
        }
    }

    public long D(Context context) {
        Cursor query = context.getContentResolver().query(BuiltinVersion.CONTNET_URI, new String[]{"data"}, "mime_type=? AND data LIKE ?", new String[]{"version", n(context) + ":%"}, null);
        if (query == null) {
            return 0;
        }
        try {
            long longValue;
            if (query.moveToFirst()) {
                longValue = Long.valueOf(query.getString(0).split(":")[1]).longValue();
            } else {
                longValue = 0;
            }
            query.close();
            return longValue;
        } catch (Throwable th) {
            query.close();
        }
    }

    protected void e(Context context, long j) {
        context.getContentResolver().delete(BuiltinVersion.CONTNET_URI, "mime_type=? AND data LIKE ?", new String[]{"version", n(context) + ":%"});
        ContentValues contentValues = new ContentValues();
        contentValues.put("mime_type", "version");
        contentValues.put("data", n(context) + ":" + j);
        context.getContentResolver().insert(BuiltinVersion.CONTNET_URI, contentValues);
    }
}
