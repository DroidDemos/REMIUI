package com.alipay.sdk.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.data.FrameUtils;
import com.alipay.sdk.data.InteractionData;
import com.alipay.sdk.data.MspConfig;
import com.alipay.sdk.data.Request;
import com.alipay.sdk.exception.AppErrorException;
import com.alipay.sdk.exception.FailOperatingException;
import com.alipay.sdk.exception.NetErrorException;
import com.alipay.sdk.exception.UnZipException;
import com.alipay.sdk.net.RequestWrapper;
import com.alipay.sdk.protocol.ActionType;
import com.alipay.sdk.protocol.ElementAction;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.util.ActionUtil;
import com.alipay.sdk.util.FileDownloader;
import com.alipay.sdk.util.FileDownloader.IDownloadProgress;
import com.alipay.sdk.util.LogUtils;
import com.alipay.sdk.util.PayHelper;
import com.alipay.sdk.util.Utils;
import com.alipay.sdk.widget.Loading;
import com.alipay.sdk.widget.SystemDefaultDialog;
import org.json.JSONObject;

public class PayTask {
    static final Object a;
    private Activity b;
    private String c;
    private PayHelper d;
    private Dialog e;
    private FileDownloader f;
    private Handler g;
    private String h;
    private boolean i;
    private String j;
    private Runnable k;
    private BroadcastReceiver l;

    static {
        a = PayTask.class;
    }

    public PayTask(Activity activity) {
        this.k = new Runnable(this) {
            final /* synthetic */ PayTask a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.f != null) {
                    this.a.f.c();
                }
                SystemDefaultDialog.a(this.a.b, "\u63d0\u793a", "\u4e0b\u8f7d\u5b89\u88c5\u5305\u5931\u8d25\uff0c\u662f\u5426\u91cd\u8bd5\uff1f", "\u91cd\u8bd5", new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a.c();
                    }
                }, "\u53d6\u6d88", new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        synchronized (PayTask.a) {
                            ResultStatus a = ResultStatus.a(ResultStatus.CANCELED);
                            Result.a(Result.a(a.a(), a.b(), ConfigConstant.WIRELESS_FILENAME));
                            try {
                                PayTask.a.notify();
                            } catch (Object e) {
                                LogUtils.a(e);
                            }
                        }
                    }
                });
            }
        };
        this.l = new BroadcastReceiver(this) {
            final /* synthetic */ PayTask a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("android.intent.action.PACKAGE_ADDED")) {
                    this.a.g.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.a.e != null) {
                                this.a.a.e.dismiss();
                            }
                            this.a.a.i = true;
                            this.a.a.b.unregisterReceiver(this.a.a.l);
                        }
                    });
                }
            }
        };
        this.b = activity;
        this.g = new Handler(this.b.getMainLooper());
        GlobalContext.a().a(this.b, MspConfig.b());
    }

    public String pay(String str) {
        this.d = new PayHelper(this.b);
        this.c = str;
        if (str.contains("paymethod=\"expressGateway\"")) {
            return b();
        }
        if (Utils.b(this.b)) {
            return a();
        }
        return b();
    }

    public String getVersion() {
        return GlobalConstants.e;
    }

    public boolean checkAccountIfExist() {
        boolean z = false;
        try {
            z = new RequestWrapper().a(this.b, FrameUtils.a(), true).d().optBoolean("hasAccount", false);
        } catch (Exception e) {
        }
        return z;
    }

    private String a() {
        if (this.c.contains("bizcontext=")) {
            return this.d.b(this.c);
        }
        return this.d.b(this.c + "&bizcontext=\"{\"appkey\":\"2014052600006128\"}\"");
    }

    private String b() {
        int i = 0;
        this.j = this.b.getCacheDir().getAbsolutePath() + "/temp.apk";
        Request a = FrameUtils.a(new InteractionData(), this.c, new JSONObject(), true);
        RequestWrapper requestWrapper = new RequestWrapper(new InteractionData());
        ResultStatus resultStatus = null;
        try {
            ActionType[] a2 = ActionType.a(ElementAction.a(requestWrapper.a(this.b, a, false).d().optJSONObject(MiniDefine.d), MiniDefine.g));
            for (ActionType actionType : a2) {
                if (actionType == ActionType.Update) {
                    ActionUtil.b(actionType.e());
                }
            }
            int length = a2.length;
            while (i < length) {
                ActionType actionType2 = a2[i];
                if (actionType2 == ActionType.WapPay) {
                    return b(actionType2);
                }
                if (actionType2 == ActionType.MspApp) {
                    return this.d.a(this.c);
                }
                if (actionType2 == ActionType.Confirm) {
                    return a(actionType2);
                }
                i++;
            }
        } catch (NetErrorException e) {
            resultStatus = ResultStatus.a(ResultStatus.NETWORK_ERROR);
        } catch (FailOperatingException e2) {
        } catch (AppErrorException e3) {
        } catch (UnZipException e4) {
        }
        if (resultStatus == null) {
            resultStatus = ResultStatus.a(ResultStatus.FAILED);
        }
        return Result.a(resultStatus.a(), resultStatus.b(), ConfigConstant.WIRELESS_FILENAME);
    }

    private String a(ActionType actionType) {
        ActionType[] actionTypeArr;
        ActionType[] actionTypeArr2;
        String[] a = ActionUtil.a(actionType.e());
        if (a.length <= 4 || TextUtils.isEmpty(a[4])) {
            actionTypeArr = null;
        } else {
            actionTypeArr = ActionType.a(ElementAction.a(a[4], actionType));
        }
        if (a.length <= 5 || TextUtils.isEmpty(a[5])) {
            actionTypeArr2 = null;
        } else {
            actionTypeArr2 = ActionType.a(ElementAction.a(a[5], actionType));
        }
        a(a[0], a[1], a[2], actionTypeArr, a[3], actionTypeArr2);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.i) {
            return a();
        }
        String a2 = Result.a();
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        ResultStatus a3 = ResultStatus.a(ResultStatus.CANCELED);
        return Result.a(a3.a(), a3.b(), ConfigConstant.WIRELESS_FILENAME);
    }

    private void a(String str, String str2, String str3, ActionType[] actionTypeArr, String str4, ActionType[] actionTypeArr2) {
        final ActionType[] actionTypeArr3 = actionTypeArr;
        final ActionType[] actionTypeArr4 = actionTypeArr2;
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final String str8 = str4;
        this.b.runOnUiThread(new Runnable(this) {
            final /* synthetic */ PayTask g;

            public void run() {
                OnClickListener anonymousClass1;
                OnClickListener anonymousClass2;
                if (actionTypeArr3 != null) {
                    anonymousClass1 = new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.g.a(actionTypeArr3);
                        }
                    };
                } else {
                    anonymousClass1 = null;
                }
                if (actionTypeArr4 != null) {
                    anonymousClass2 = new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.g.a(actionTypeArr4);
                        }
                    };
                } else {
                    anonymousClass2 = null;
                }
                SystemDefaultDialog.a(this.g.b, str5, str6, str7, anonymousClass1, str8, anonymousClass2);
            }
        });
    }

    private String b(ActionType actionType) {
        String str = ConfigConstant.WIRELESS_FILENAME;
        String[] a = ActionUtil.a(actionType.e());
        Intent intent = new Intent(this.b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", a[0]);
        if (a.length == 2) {
            bundle.putString("cookie", a[1]);
        }
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (Object e) {
                LogUtils.a(e);
            }
        }
        str = Result.a();
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        ResultStatus a2 = ResultStatus.a(ResultStatus.CANCELED);
        return Result.a(a2.a(), a2.b(), ConfigConstant.WIRELESS_FILENAME);
    }

    private void a(ActionType[] actionTypeArr) {
        for (ActionType actionType : actionTypeArr) {
            if (actionType == ActionType.DownLoad) {
                this.h = ActionUtil.a(actionType.e())[0];
                c();
            }
            if (actionType == ActionType.Exit) {
                synchronized (a) {
                    ResultStatus a = ResultStatus.a(ResultStatus.CANCELED);
                    Result.a(Result.a(a.a(), a.b(), ConfigConstant.WIRELESS_FILENAME));
                    try {
                        a.notify();
                    } catch (Object e) {
                        LogUtils.a(e);
                    }
                }
            }
        }
    }

    private void c() {
        final Loading loading = new Loading(this.b);
        loading.a("\u6b63\u5728\u4e0b\u8f7d\u4e2d", true, new OnCancelListener(this) {
            final /* synthetic */ PayTask b;

            public void onCancel(DialogInterface dialogInterface) {
                loading.c();
                this.b.f.c();
                this.b.b.unregisterReceiver(this.b.l);
                this.b.g.removeCallbacks(this.b.k);
                synchronized (PayTask.a) {
                    ResultStatus a = ResultStatus.a(ResultStatus.CANCELED);
                    Result.a(Result.a(a.a(), a.b(), ConfigConstant.WIRELESS_FILENAME));
                    try {
                        PayTask.a.notify();
                    } catch (Object e) {
                        LogUtils.a(e);
                    }
                }
            }
        });
        this.f = new FileDownloader();
        this.f.a(this.h);
        this.f.b(this.j);
        this.f.a(new IDownloadProgress(this) {
            final /* synthetic */ PayTask b;

            public void a() {
                loading.c();
                this.b.g.removeCallbacks(this.b.k);
                this.b.d();
            }

            public void a(float f) {
            }

            public void b() {
                loading.c();
                this.b.g.post(this.b.k);
            }
        });
        this.f.b();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.b.registerReceiver(this.l, intentFilter);
        this.g.postDelayed(this.k, 180000);
    }

    private void d() {
        this.g.post(new Runnable(this) {
            final /* synthetic */ PayTask a;

            {
                this.a = r1;
            }

            public void run() {
                if (Utils.c(this.a.b, this.a.j)) {
                    Utils.a(this.a.b, this.a.j);
                    this.a.e = SystemDefaultDialog.a(this.a.b, "\u63d0\u793a", "\u662f\u5426\u53d6\u6d88\u5b89\u88c5\uff1f", "\u91cd\u65b0\u5b89\u88c5", new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.d();
                        }
                    }, "\u53d6\u6d88", new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.b.unregisterReceiver(this.a.a.l);
                            this.a.a.i = false;
                            ResultStatus a = ResultStatus.a(ResultStatus.CANCELED);
                            Result.a(Result.a(a.a(), a.b(), ConfigConstant.WIRELESS_FILENAME));
                            synchronized (PayTask.a) {
                                try {
                                    PayTask.a.notify();
                                } catch (Object e) {
                                    LogUtils.a(e);
                                }
                            }
                        }
                    });
                    return;
                }
                synchronized (PayTask.a) {
                    ResultStatus a = ResultStatus.a(ResultStatus.FAILED);
                    Result.a(Result.a(a.a(), a.b(), ConfigConstant.WIRELESS_FILENAME));
                    try {
                        PayTask.a.notify();
                    } catch (Object e) {
                        LogUtils.a(e);
                    }
                }
            }
        });
    }
}
