package com.alipay.sdk.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IAlixPay.Stub;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.app.Result;
import com.alipay.sdk.app.ResultStatus;
import com.alipay.sdk.cons.GlobalConstants;

public class PayHelper {
    private Activity a;
    private IAlixPay b;
    private Object c;
    private boolean d;
    private ServiceConnection e;
    private IRemoteServiceCallback f;

    public PayHelper(Activity activity) {
        this.d = false;
        this.e = new ServiceConnection(this) {
            final /* synthetic */ PayHelper a;

            {
                this.a = r1;
            }

            public void onServiceDisconnected(ComponentName componentName) {
                this.a.b = null;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (this.a.c) {
                    this.a.b = Stub.asInterface(iBinder);
                    this.a.c.notify();
                }
            }
        };
        this.f = new IRemoteServiceCallback.Stub(this) {
            final /* synthetic */ PayHelper a;

            {
                this.a = r1;
            }

            public boolean isHideLoadingScreen() {
                return false;
            }

            public void payEnd(boolean z, String str) {
            }

            public void startActivity(String str, String str2, int i, Bundle bundle) {
                Intent intent = new Intent("android.intent.action.MAIN", null);
                if (bundle == null) {
                    bundle = new Bundle();
                }
                try {
                    bundle.putInt("CallingPid", i);
                    intent.putExtras(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.setClassName(str, str2);
                this.a.a.startActivity(intent);
            }
        };
        this.a = activity;
        this.c = new Object();
    }

    public String a(String str) {
        CharSequence a = Utils.a(Utils.a(this.a, DeviceInfo.a));
        if (a == null || TextUtils.equals(a, GlobalConstants.f)) {
            Intent intent = new Intent();
            if (Utils.c(this.a)) {
                intent.setClassName(DeviceInfo.a, "com.alipay.android.app.MspService");
            } else {
                intent.setClassName(DeviceInfo.a, "com.alipay.android.app.AlixService");
            }
            intent.setAction("com.alipay.android.app.IAlixPay");
            return a(str, intent);
        }
        ResultStatus a2 = ResultStatus.a(ResultStatus.CANCELED);
        return Result.a(a2.a(), a2.b(), ConfigConstant.WIRELESS_FILENAME);
    }

    public String b(String str) {
        CharSequence a = Utils.a(Utils.a(this.a, DeviceInfo.b));
        if (a == null || TextUtils.equals(a, GlobalConstants.f)) {
            Intent intent = new Intent();
            intent.setClassName(DeviceInfo.b, "com.alipay.android.app.MspService");
            intent.setAction("com.eg.android.AlipayGphone.IAlixPay");
            return a(str, intent);
        }
        ResultStatus a2 = ResultStatus.a(ResultStatus.CANCELED);
        return Result.a(a2.a(), a2.b(), ConfigConstant.WIRELESS_FILENAME);
    }

    private String a(String str, Intent intent) {
        String str2;
        Exception e;
        if (this.d) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        this.d = true;
        if (this.b == null) {
            this.a.getApplicationContext().bindService(intent, this.e, 1);
        }
        try {
            synchronized (this.c) {
                if (this.b == null) {
                    this.c.wait(3000);
                }
            }
            if (this.b == null) {
                str2 = ConfigConstant.WIRELESS_FILENAME;
                try {
                    this.a.unbindService(this.e);
                } catch (Exception e2) {
                    this.b = null;
                }
                this.d = false;
                return str2;
            }
            this.b.registerCallback(this.f);
            str2 = this.b.Pay(str);
            try {
                this.b.unregisterCallback(this.f);
                this.b = null;
                try {
                    this.a.unbindService(this.e);
                } catch (Exception e3) {
                    this.b = null;
                }
                this.d = false;
                return str2;
            } catch (Exception e4) {
                e = e4;
                try {
                    e.printStackTrace();
                    return str2;
                } finally {
                    try {
                        this.a.unbindService(this.e);
                    } catch (Exception e5) {
                        this.b = null;
                    }
                    this.d = false;
                }
            }
        } catch (Exception e6) {
            e = e6;
            str2 = null;
            e.printStackTrace();
            return str2;
        }
    }
}
