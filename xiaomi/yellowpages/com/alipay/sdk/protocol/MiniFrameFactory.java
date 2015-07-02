package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.data.Envelope;
import com.alipay.sdk.data.Request;
import com.alipay.sdk.data.Response;
import com.alipay.sdk.exception.AppErrorException;
import com.alipay.sdk.exception.FailOperatingException;
import com.alipay.sdk.tid.TidInfo;
import com.alipay.sdk.util.LogUtils;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

public class MiniFrameFactory {

    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[MiniStatus.values().length];
            try {
                a[MiniStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[MiniStatus.NOT_POP_TYPE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[MiniStatus.POP_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[MiniStatus.TID_REFRESH.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public MiniWindowFrame a(FrameData frameData) {
        Request a = frameData.a();
        Response b = frameData.b();
        JSONObject d = frameData.d();
        String str = "\u7a0b\u5e8f\u53d1\u751f\u9519\u8bef";
        MiniWindowFrame miniWindowFrame;
        if (d.has(MiniDefine.d)) {
            miniWindowFrame = new MiniWindowFrame(a, b);
            miniWindowFrame.a(frameData.d());
            return miniWindowFrame;
        } else if (d.has(MiniDefine.b)) {
            switch (AnonymousClass1.a[MiniStatus.a(d.optString(MiniDefine.b)).ordinal()]) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                case WindowData.d /*3*/:
                    miniWindowFrame = new MiniWindowFrame(a, b);
                    miniWindowFrame.a(d);
                    return miniWindowFrame;
                case Base64.CRLF /*4*/:
                    TidInfo.e();
                    return null;
                default:
                    CharSequence optString = d.optString(MiniDefine.c);
                    if (!TextUtils.isEmpty(optString)) {
                        CharSequence charSequence = optString;
                    }
                    throw new FailOperatingException(str);
            }
        } else {
            throw new FailOperatingException(str);
        }
    }

    public void b(FrameData frameData) {
        Response b = frameData.b();
        JSONObject d = frameData.d();
        if (d.has(MiniDefine.d)) {
            frameData.a(d.optJSONObject(MiniDefine.d).optString(MiniDefine.E));
        }
        Envelope f = frameData.a().f();
        Envelope a = frameData.b().a();
        if (TextUtils.isEmpty(a.d())) {
            a.d(f.d());
        }
        if (TextUtils.isEmpty(a.e())) {
            a.e(f.e());
        }
        if (TextUtils.isEmpty(a.c())) {
            a.c(f.c());
        }
        if (TextUtils.isEmpty(a.b())) {
            a.b(f.b());
        }
        String str = "session";
        JSONObject optJSONObject = d.optJSONObject("reflected_data");
        if (optJSONObject != null) {
            LogUtils.d("session = " + optJSONObject.optString(str, ConfigConstant.WIRELESS_FILENAME));
            frameData.b().a(optJSONObject);
        } else if (d.has(str)) {
            optJSONObject = new JSONObject();
            try {
                optJSONObject.put(str, d.optString(str));
                CharSequence a2 = TidInfo.d().a();
                if (!TextUtils.isEmpty(a2)) {
                    optJSONObject.put(MiniDefine.ak, a2);
                }
                b.a(optJSONObject);
            } catch (JSONException e) {
                throw new AppErrorException(getClass(), "can not put reflected values");
            }
        }
        b.b(d.optString("end_code", Profile.devicever));
        b.e(d.optString("user_id", ConfigConstant.WIRELESS_FILENAME));
        str = d.optString(GlobalDefine.g);
        try {
            str = URLDecoder.decode(d.optString(GlobalDefine.g), "UTF-8");
        } catch (Object e2) {
            LogUtils.a(e2);
        }
        b.c(str);
        b.d(d.optString(GlobalDefine.h, ConfigConstant.WIRELESS_FILENAME));
    }
}
