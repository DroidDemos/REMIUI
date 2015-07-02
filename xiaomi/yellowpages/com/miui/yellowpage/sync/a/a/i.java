package com.miui.yellowpage.sync.a.a;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.exception.ParseException;
import com.miui.yellowpage.base.exception.ServerException;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.utils.J;
import com.miui.yellowpage.utils.t;
import com.miui.yellowpage.utils.z;
import java.io.File;
import java.io.IOException;
import miui.util.CoderUtils;
import miui.util.Patcher;
import miui.yellowpage.YellowPageContract.AntispamNumber;
import org.json.JSONObject;

/* compiled from: Antispam */
public class i extends d {
    protected JSONRequest m(Context context) {
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getPatchInfoUrl());
        long D = D(context);
        jSONRequest.addParam("ver", String.valueOf(D));
        jSONRequest.addParam("m", D == 0 ? ConfigConstant.WIRELESS_FILENAME : J.at(context));
        jSONRequest.addParam("t", String.valueOf(1));
        return jSONRequest;
    }

    public boolean A(Context context) {
        return true;
    }

    public boolean e(Context context, String str) {
        JSONObject jSONObject = new JSONObject(str);
        Log.d("PullTask", "json:" + jSONObject);
        boolean z = jSONObject.getBoolean(GlobalDefine.g);
        int i = jSONObject.getInt(MiniDefine.i);
        jSONObject = jSONObject.optJSONObject("info");
        if (z && i == 1 && jSONObject != null) {
            c(context, jSONObject);
        } else {
            Log.d("PullTask", "no need to update or bad things happened");
        }
        return false;
    }

    private void c(Context context, JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject.getInt("serviceType") != 1) {
            throw new ServerException("service type mismatch");
        }
        t i = t.i(jSONObject);
        if (i == null) {
            throw new ServerException("null info");
        } else if (i.eE() && D(context) != i.eF()) {
            throw new ServerException("old version mismatch");
        } else if (z.gP()) {
            File file = new File(z.Cc, ".antispam.dat.tmp");
            int i2 = 0;
            while (i2 < 3 && !z) {
                try {
                    z = Files.downLoadFile(context, i.getUrl(), file.getAbsolutePath());
                    i2++;
                    if (z) {
                        a(context, i, file);
                    }
                } catch (Throwable th) {
                    Files.deleteFile(file);
                }
            }
            if (z) {
                ae(context);
                Files.deleteFile(file);
                return;
            }
            throw new IOException("failed to download file");
        } else {
            throw new IOException("failed to create download dir");
        }
    }

    private void ae(Context context) {
        context.getContentResolver().delete(AntispamNumber.CONTENT_URI, "type=?", new String[]{String.valueOf(5)});
    }

    private void a(Context context, t tVar, File file) {
        if (tVar.eE()) {
            b(context, tVar, file);
        } else {
            c(context, tVar, file);
        }
    }

    private void b(Context context, t tVar, File file) {
        if (TextUtils.equals(tVar.eI(), CoderUtils.encodeMD5(file))) {
            File file2 = new File(J.as(context).getAbsoluteFile() + ".new");
            if (new Patcher().applyPatch(J.as(context).getAbsolutePath(), file2.getAbsolutePath(), file.getAbsolutePath()) != 0) {
                Files.deleteFiles(new File[]{file2});
                throw new ParseException("failed to apply patch");
            } else if (!TextUtils.equals(CoderUtils.encodeMD5(file2), tVar.eH())) {
                Files.deleteFiles(new File[]{file2});
                throw new ParseException("digest mismatch");
            } else if (Files.copyFile(file2, J.as(context))) {
                e(context, tVar.eG());
                Log.d("PullTask", "finished patching");
                Files.deleteFiles(new File[]{file2});
                return;
            } else {
                throw new IOException("failed to copy file");
            }
        }
        throw new ParseException("digest mistmatch");
    }

    private void c(Context context, t tVar, File file) {
        if (!TextUtils.equals(CoderUtils.encodeMD5(file), tVar.eI())) {
            throw new ParseException("digest mistmatch");
        } else if (Files.copyFile(file, J.as(context))) {
            e(context, tVar.eG());
            Log.d("PullTask", "finished patching");
        } else {
            throw new IOException("failed to move");
        }
    }

    public long D(Context context) {
        return J.M(context);
    }

    protected void e(Context context, long j) {
        J.j(context, j);
    }
}
