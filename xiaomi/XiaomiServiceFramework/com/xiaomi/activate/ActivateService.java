package com.xiaomi.activate;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.activate.CloudServiceFailureException;
import com.xiaomi.accountsdk.activate.IActivateService;
import com.xiaomi.accountsdk.activate.IActivateService.Stub;
import com.xiaomi.accountsdk.activate.IActivateServiceResponse;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.activate.ActivateHelper.NoPhoneInfoException;
import com.xiaomi.activate.ActivateHelper.PhoneServiceStateListener;
import com.xiaomi.activate.ActivateHelper.VKeyExpiredException;
import com.xiaomi.activate.ActivateInfo.AutoActivateState;
import com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.xmsf.R;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class ActivateService extends Service {
    private static final String ACTION_INTERNAL_ACTIVATE_SMS_SENT = "com.xiaomi.action.ACTIVATE_SMS_SENT";
    public static final String ACTION_INTERNAL_NOTIFY_SIM_STATE_CHANGED = "com.xiaomi.action.INTERNAL_NOTIFY_SIM_STATE_CHANGED";
    private static final int AUTO_ACTIVATION_INTERVAL = 86400000;
    private static final boolean DEBUG = true;
    private static final int DUAL_MODE_PHONE_ACTIVATE_DELAY = 10000;
    private static final int MSG_ARG_UNUSED = -1;
    private static final int MSG_HANDLE_SIM_STATE_CHANGE = 1;
    private static final int MSG_SHOW_TOAST = 8;
    private static final int MSG_START_ACTIVATE_TASK = 2;
    private static final int MSG_START_VERIFY_SIM_TOKEN_TASK = 3;
    private static final String PREF_ACTIVATE_HOST = "activate_host_%s";
    public static final String PREF_AUTO_ACTIVATE_FAIL_POP_UP_SHOWN = "auto_activate_fail_popup_shown";
    private static final String PREF_LAST_AUTO_UPLINK_SIM_ID = "last_auto_uplink_sim_id_";
    private static final String PREF_PSEUDO_SIM_ID = "pseudo_sim_id_%s";
    private static final String TAG = "ActivateService";
    private static final boolean TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
    private static final int WAIT_SMS_TIMELIMIT_AUTO = 300000;
    private static final int WAIT_SMS_TIMELIMIT_DOWNLINK = 60000;
    private static volatile ActivateService sInstance;
    private ActivateInfo[] mActivateInfos;
    private ActivateProgressController mApc;
    private final Handler mHandler;
    private volatile PhoneServiceStateListener[] mPhoneServiceStateListener;
    private IActivateService mService;
    private final List<RemoteObj> mSessions;
    private int mSimCount;
    private volatile SimTaskSet[] mSimTaskSets;
    private SysInterface mSysInterface;
    private ThreadPoolExecutor mTaskExecutor;
    private HandlerThread mWorkThread;

    private static class ActivateArgs {
        int activateMethod;
        boolean enableServices;
        String phoneOrCode;
        int simIndex;
        String xiaomiAccountPassword;

        private ActivateArgs() {
        }
    }

    private abstract class ActivatePhoneTaskBase extends AsyncTask<Void, Void, Integer> {
        protected final ActivateInfo mActivateInfo;
        protected ActivateProgressReporter mApr;
        protected boolean mNotify;
        protected final int mSimIndex;
        protected final String mXiaomiAccountPassword;

        protected abstract String getName();

        protected abstract boolean getPhone() throws InterruptedException, VKeyExpiredException, NoPhoneInfoException, CloudServiceFailureException;

        protected abstract int getVKeys() throws InterruptedException;

        protected ActivatePhoneTaskBase(ActivateInfo info, int simIndex, String xiaomiAccountPassword) {
            this.mNotify = ActivateService.DEBUG;
            this.mApr = new ActivateProgressReporter();
            this.mSimIndex = simIndex;
            this.mXiaomiAccountPassword = xiaomiAccountPassword;
            this.mActivateInfo = info;
            if (this.mActivateInfo == null) {
                throw new IllegalStateException("ActivatePhoneTaskBase: activate info for SIM " + this.mSimIndex + " is null");
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.mApr.init(ActivateService.this, this.mSimIndex);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Integer doInBackground(java.lang.Void... r19) {
            /*
            r18 = this;
            r0 = r18;
            r2 = r0.mActivateInfo;
            r13 = 0;
            r11 = 1;
            r9 = 2;
            r10 = 3;
            r12 = 4;
            r8 = 5;
            r17 = 0;
            r15 = 0;
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r3 = r0.mSimIndex;	 Catch:{ InterruptedException -> 0x004b }
            r1.startNewActivation(r2, r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_start";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ InterruptedException -> 0x004b }
            com.xiaomi.activate.ActivateHelper.querySmsGateway(r1);	 Catch:{ InterruptedException -> 0x004b }
        L_0x0036:
            r1 = 5;
            r0 = r17;
            if (r0 == r1) goto L_0x04fa;
        L_0x003b:
            r1 = java.lang.Thread.currentThread();	 Catch:{ InterruptedException -> 0x004b }
            r1 = r1.isInterrupted();	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x0071;
        L_0x0045:
            r1 = new java.lang.InterruptedException;	 Catch:{ InterruptedException -> 0x004b }
            r1.<init>();	 Catch:{ InterruptedException -> 0x004b }
            throw r1;	 Catch:{ InterruptedException -> 0x004b }
        L_0x004b:
            r14 = move-exception;
            r1 = "ActivateService";
            r3 = "ActivatePhoneTaskBase: Activation interrupted";
            android.util.Log.w(r1, r3, r14);	 Catch:{ all -> 0x0549 }
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ all -> 0x0549 }
            r1 = r1.mApc;	 Catch:{ all -> 0x0549 }
            r0 = r18;
            r3 = r0.mSimIndex;	 Catch:{ all -> 0x0549 }
            r4 = -1;
            r5 = 0;
            r6 = 0;
            r1.reportActivateStatus(r3, r4, r5, r6);	 Catch:{ all -> 0x0549 }
            r1 = 2;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x0549 }
            switch(r17) {
                case 1: goto L_0x0597;
                case 2: goto L_0x05a2;
                case 3: goto L_0x05ad;
                case 4: goto L_0x05b8;
                default: goto L_0x006d;
            };
        L_0x006d:
            switch(r17) {
                case 1: goto L_0x05c3;
                case 2: goto L_0x05c3;
                case 3: goto L_0x05d5;
                case 4: goto L_0x05d5;
                default: goto L_0x0070;
            };
        L_0x0070:
            return r1;
        L_0x0071:
            r1 = "ActivateService";
            r3 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x004b }
            r3.<init>();	 Catch:{ InterruptedException -> 0x004b }
            r4 = "Activation of SIM ";
            r3 = r3.append(r4);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r4 = r0.mSimIndex;	 Catch:{ InterruptedException -> 0x004b }
            r3 = r3.append(r4);	 Catch:{ InterruptedException -> 0x004b }
            r4 = " on stage ";
            r3 = r3.append(r4);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r17;
            r3 = r3.append(r0);	 Catch:{ InterruptedException -> 0x004b }
            r3 = r3.toString();	 Catch:{ InterruptedException -> 0x004b }
            android.util.Log.v(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 0: goto L_0x00d6;
                case 1: goto L_0x011b;
                case 2: goto L_0x017d;
                case 3: goto L_0x02af;
                case 4: goto L_0x0380;
                default: goto L_0x009c;
            };	 Catch:{ InterruptedException -> 0x004b }
        L_0x009c:
            r1 = "ActivateService";
            r3 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x004b }
            r3.<init>();	 Catch:{ InterruptedException -> 0x004b }
            r4 = "ActivatePhoneTaskBase: Unexpected stage ";
            r3 = r3.append(r4);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r17;
            r3 = r3.append(r0);	 Catch:{ InterruptedException -> 0x004b }
            r3 = r3.toString();	 Catch:{ InterruptedException -> 0x004b }
            android.util.Log.w(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x081a;
                case 2: goto L_0x0825;
                case 3: goto L_0x0830;
                case 4: goto L_0x083b;
                default: goto L_0x00be;
            };
        L_0x00be:
            switch(r17) {
                case 1: goto L_0x00c2;
                case 2: goto L_0x00c2;
                case 3: goto L_0x0846;
                case 4: goto L_0x0846;
                default: goto L_0x00c1;
            };
        L_0x00c1:
            goto L_0x0070;
        L_0x00c2:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x00cc:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
        L_0x00d2:
            r3.setDiagnosisInfoCategory(r4);
            goto L_0x0070;
        L_0x00d6:
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r3 = r0.mSimIndex;	 Catch:{ InterruptedException -> 0x004b }
            r1 = com.xiaomi.activate.ActivateHelper.getActivateHost(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            r2.host = r1;	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ InterruptedException -> 0x004b }
            r3 = r2.hashedDeviceId;	 Catch:{ InterruptedException -> 0x004b }
            r4 = r2.host;	 Catch:{ InterruptedException -> 0x004b }
            r1.saveActivateHostPref(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1 = r2.simUserId;	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x0103;
        L_0x00f3:
            r1 = r2.simPassToken;	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x0103;
        L_0x00f7:
            r1 = r2.phone;	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x0103;
        L_0x00fb:
            r1 = r2.vkey1;	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x0103;
        L_0x00ff:
            r17 = 4;
            goto L_0x0036;
        L_0x0103:
            r1 = r2.phone;	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x010f;
        L_0x0107:
            r1 = r2.vkey1;	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x010f;
        L_0x010b:
            r17 = 3;
            goto L_0x0036;
        L_0x010f:
            r1 = r2.vkey1;	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x0117;
        L_0x0113:
            r17 = 2;
            goto L_0x0036;
        L_0x0117:
            r17 = 1;
            goto L_0x0036;
        L_0x011b:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_get_vkey_start";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r16 = r18.getVKeys();	 Catch:{ InterruptedException -> 0x004b }
            if (r16 == 0) goto L_0x0171;
        L_0x0138:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_get_vkey_fail";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r4 = java.lang.Integer.valueOf(r16);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1 = java.lang.Integer.valueOf(r16);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x05e0;
                case 2: goto L_0x05eb;
                case 3: goto L_0x05f6;
                case 4: goto L_0x0601;
                default: goto L_0x015a;
            };
        L_0x015a:
            switch(r17) {
                case 1: goto L_0x015f;
                case 2: goto L_0x015f;
                case 3: goto L_0x060c;
                case 4: goto L_0x060c;
                default: goto L_0x015d;
            };
        L_0x015d:
            goto L_0x0070;
        L_0x015f:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x0169:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x0171:
            r1 = com.xiaomi.activate.ActivateDatabaseHelper.getInstance();	 Catch:{ InterruptedException -> 0x004b }
            r1.updateActivateInfo(r2);	 Catch:{ InterruptedException -> 0x004b }
            r17 = 2;
            r15 = 1;
            goto L_0x0036;
        L_0x017d:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_get_phone_start";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r1 = r18.getPhone();	 Catch:{ VKeyExpiredException -> 0x01c4, NoPhoneInfoException -> 0x0228, CloudServiceFailureException -> 0x026f }
            if (r1 != 0) goto L_0x01b9;
        L_0x019a:
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ VKeyExpiredException -> 0x01c4, NoPhoneInfoException -> 0x0228, CloudServiceFailureException -> 0x026f }
            switch(r17) {
                case 1: goto L_0x06ae;
                case 2: goto L_0x06b9;
                case 3: goto L_0x06c4;
                case 4: goto L_0x06cf;
                default: goto L_0x01a2;
            };
        L_0x01a2:
            switch(r17) {
                case 1: goto L_0x01a7;
                case 2: goto L_0x01a7;
                case 3: goto L_0x06da;
                case 4: goto L_0x06da;
                default: goto L_0x01a5;
            };
        L_0x01a5:
            goto L_0x0070;
        L_0x01a7:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x01b1:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x01b9:
            r1 = com.xiaomi.activate.ActivateDatabaseHelper.getInstance();	 Catch:{ VKeyExpiredException -> 0x01c4, NoPhoneInfoException -> 0x0228, CloudServiceFailureException -> 0x026f }
            r1.updateActivateInfo(r2);	 Catch:{ VKeyExpiredException -> 0x01c4, NoPhoneInfoException -> 0x0228, CloudServiceFailureException -> 0x026f }
            r17 = 3;
            goto L_0x0036;
        L_0x01c4:
            r14 = move-exception;
            if (r15 == 0) goto L_0x020d;
        L_0x01c7:
            r1 = "ActivateService";
            r3 = "ActivatePhoneTaskBase: vkey expired just after acquisition!";
            android.util.Log.e(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_get_vkey_unexpected_vkey_expiration";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "vkey_expired";
            r1.setLiveReportDetails(r3);	 Catch:{ InterruptedException -> 0x004b }
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x0613;
                case 2: goto L_0x061e;
                case 3: goto L_0x0629;
                case 4: goto L_0x0634;
                default: goto L_0x01f6;
            };
        L_0x01f6:
            switch(r17) {
                case 1: goto L_0x01fb;
                case 2: goto L_0x01fb;
                case 3: goto L_0x063f;
                case 4: goto L_0x063f;
                default: goto L_0x01f9;
            };
        L_0x01f9:
            goto L_0x0070;
        L_0x01fb:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x0205:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x020d:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_get_vkey_expiration";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r17 = 1;
            goto L_0x0036;
        L_0x0228:
            r14 = move-exception;
            r1 = "ActivateService";
            r3 = "ActivatePhoneTaskBase: vkey not exist on servers!";
            android.util.Log.e(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_get_vkey_not_exist";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "no_phone_on_server";
            r1.setLiveReportDetails(r3);	 Catch:{ InterruptedException -> 0x004b }
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x0646;
                case 2: goto L_0x0651;
                case 3: goto L_0x065c;
                case 4: goto L_0x0667;
                default: goto L_0x0258;
            };
        L_0x0258:
            switch(r17) {
                case 1: goto L_0x025d;
                case 2: goto L_0x025d;
                case 3: goto L_0x0672;
                case 4: goto L_0x0672;
                default: goto L_0x025b;
            };
        L_0x025b:
            goto L_0x0070;
        L_0x025d:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x0267:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x026f:
            r14 = move-exception;
            r1 = "ActivateService";
            r3 = "ActivatePhoneTaskBase: Cloud Service error, %s ";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r14.getMessage();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            android.util.Log.e(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = r14.getMessage();	 Catch:{ InterruptedException -> 0x004b }
            r1.setLiveReportDetails(r3);	 Catch:{ InterruptedException -> 0x004b }
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x067a;
                case 2: goto L_0x0685;
                case 3: goto L_0x0690;
                case 4: goto L_0x069b;
                default: goto L_0x0298;
            };
        L_0x0298:
            switch(r17) {
                case 1: goto L_0x029d;
                case 2: goto L_0x029d;
                case 3: goto L_0x06a6;
                case 4: goto L_0x06a6;
                default: goto L_0x029b;
            };
        L_0x029b:
            goto L_0x0070;
        L_0x029d:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x02a7:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x02af:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ VKeyExpiredException -> 0x031c }
            r3 = "v6_%s_create_user_start";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ VKeyExpiredException -> 0x031c }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ VKeyExpiredException -> 0x031c }
            r4[r5] = r6;	 Catch:{ VKeyExpiredException -> 0x031c }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ VKeyExpiredException -> 0x031c }
            r1.trackEvent(r3);	 Catch:{ VKeyExpiredException -> 0x031c }
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ VKeyExpiredException -> 0x031c }
            r3 = 0;
            r0 = r18;
            r4 = r0.mSimIndex;	 Catch:{ VKeyExpiredException -> 0x031c }
            r5 = 0;
            r6 = 0;
            r0 = r18;
            r7 = r0.mApr;	 Catch:{ VKeyExpiredException -> 0x031c }
            r1 = r1.createOrUpdateUser(r2, r3, r4, r5, r6, r7);	 Catch:{ VKeyExpiredException -> 0x031c }
            if (r1 != 0) goto L_0x0311;
        L_0x02db:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ VKeyExpiredException -> 0x031c }
            r3 = "v6_%s_create_user_fail";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ VKeyExpiredException -> 0x031c }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ VKeyExpiredException -> 0x031c }
            r4[r5] = r6;	 Catch:{ VKeyExpiredException -> 0x031c }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ VKeyExpiredException -> 0x031c }
            r1.trackEvent(r3);	 Catch:{ VKeyExpiredException -> 0x031c }
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ VKeyExpiredException -> 0x031c }
            switch(r17) {
                case 1: goto L_0x0716;
                case 2: goto L_0x0721;
                case 3: goto L_0x072c;
                case 4: goto L_0x0737;
                default: goto L_0x02fa;
            };
        L_0x02fa:
            switch(r17) {
                case 1: goto L_0x02ff;
                case 2: goto L_0x02ff;
                case 3: goto L_0x0742;
                case 4: goto L_0x0742;
                default: goto L_0x02fd;
            };
        L_0x02fd:
            goto L_0x0070;
        L_0x02ff:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x0309:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x0311:
            r1 = com.xiaomi.activate.ActivateDatabaseHelper.getInstance();	 Catch:{ VKeyExpiredException -> 0x031c }
            r1.updateActivateInfo(r2);	 Catch:{ VKeyExpiredException -> 0x031c }
            r17 = 4;
            goto L_0x0036;
        L_0x031c:
            r14 = move-exception;
            if (r15 == 0) goto L_0x0365;
        L_0x031f:
            r1 = "ActivateService";
            r3 = "ActivatePhoneTaskBase: vkey expired just after acquisition!";
            android.util.Log.e(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_create_account_unexpected_vkey_expiration";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "vkey_expired";
            r1.setLiveReportDetails(r3);	 Catch:{ InterruptedException -> 0x004b }
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x06e2;
                case 2: goto L_0x06ed;
                case 3: goto L_0x06f8;
                case 4: goto L_0x0703;
                default: goto L_0x034e;
            };
        L_0x034e:
            switch(r17) {
                case 1: goto L_0x0353;
                case 2: goto L_0x0353;
                case 3: goto L_0x070e;
                case 4: goto L_0x070e;
                default: goto L_0x0351;
            };
        L_0x0351:
            goto L_0x0070;
        L_0x0353:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x035d:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x0365:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_create_account_vkey_expiration";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r17 = 1;
            goto L_0x0036;
        L_0x0380:
            r0 = r18;
            r1 = r0.mXiaomiAccountPassword;	 Catch:{ InterruptedException -> 0x004b }
            if (r1 == 0) goto L_0x04f6;
        L_0x0386:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r3 = "v6_%s_create_xiaomi_account_start";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r4[r5] = r6;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1.trackEvent(r3);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1 = com.xiaomi.activate.ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            if (r1 != 0) goto L_0x03fc;
        L_0x03a3:
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r0 = r18;
            r3 = r0.mSimIndex;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r4 = r2.phone;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1 = r1.doesAccountExist(r3, r4);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            if (r1 == 0) goto L_0x03fc;
        L_0x03b3:
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1 = r1.mApc;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r0 = r18;
            r3 = r0.mSimIndex;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r4 = -1;
            r5 = 0;
            r6 = 0;
            r1.reportActivateStatus(r3, r4, r5, r6);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r3 = "v6_%s_create_xiaomi_account_exist";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r4[r5] = r6;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1.trackEvent(r3);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1 = 10;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            switch(r17) {
                case 1: goto L_0x07b2;
                case 2: goto L_0x07bd;
                case 3: goto L_0x07c8;
                case 4: goto L_0x07d3;
                default: goto L_0x03e5;
            };
        L_0x03e5:
            switch(r17) {
                case 1: goto L_0x03ea;
                case 2: goto L_0x03ea;
                case 3: goto L_0x07de;
                case 4: goto L_0x07de;
                default: goto L_0x03e8;
            };
        L_0x03e8:
            goto L_0x0070;
        L_0x03ea:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x03f4:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x03fc:
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r3 = 1;
            r0 = r18;
            r4 = r0.mSimIndex;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r5 = 0;
            r0 = r18;
            r6 = r0.mXiaomiAccountPassword;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r0 = r18;
            r7 = r0.mApr;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1 = r1.createOrUpdateUser(r2, r3, r4, r5, r6, r7);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            if (r1 != 0) goto L_0x04f6;
        L_0x0414:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r3 = "v6_%s_create_xiaomi_account_fail";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r4[r5] = r6;	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1.trackEvent(r3);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            r1 = 9;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ VKeyExpiredException -> 0x044b, CloudServiceFailureException -> 0x04af }
            switch(r17) {
                case 1: goto L_0x07e6;
                case 2: goto L_0x07f1;
                case 3: goto L_0x07fc;
                case 4: goto L_0x0807;
                default: goto L_0x0434;
            };
        L_0x0434:
            switch(r17) {
                case 1: goto L_0x0439;
                case 2: goto L_0x0439;
                case 3: goto L_0x0812;
                case 4: goto L_0x0812;
                default: goto L_0x0437;
            };
        L_0x0437:
            goto L_0x0070;
        L_0x0439:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x0443:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x044b:
            r14 = move-exception;
            if (r15 == 0) goto L_0x0494;
        L_0x044e:
            r1 = "ActivateService";
            r3 = "ActivatePhoneTaskBase: vkey expired just after acquisition!";
            android.util.Log.e(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_create_account_unexpected_vkey_expiration";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "vkey_expired";
            r1.setLiveReportDetails(r3);	 Catch:{ InterruptedException -> 0x004b }
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x074a;
                case 2: goto L_0x0755;
                case 3: goto L_0x0760;
                case 4: goto L_0x076b;
                default: goto L_0x047d;
            };
        L_0x047d:
            switch(r17) {
                case 1: goto L_0x0482;
                case 2: goto L_0x0482;
                case 3: goto L_0x0776;
                case 4: goto L_0x0776;
                default: goto L_0x0480;
            };
        L_0x0480:
            goto L_0x0070;
        L_0x0482:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x048c:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x0494:
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_create_account_vkey_expiration";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r17 = 1;
            goto L_0x0036;
        L_0x04af:
            r14 = move-exception;
            r1 = "ActivateService";
            r3 = "ActivatePhoneTaskBase: cloud service failure!";
            android.util.Log.e(r1, r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_create_account_fail";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "vkey_expired";
            r1.setLiveReportDetails(r3);	 Catch:{ InterruptedException -> 0x004b }
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x077e;
                case 2: goto L_0x0789;
                case 3: goto L_0x0794;
                case 4: goto L_0x079f;
                default: goto L_0x04df;
            };
        L_0x04df:
            switch(r17) {
                case 1: goto L_0x04e4;
                case 2: goto L_0x04e4;
                case 3: goto L_0x07aa;
                case 4: goto L_0x07aa;
                default: goto L_0x04e2;
            };
        L_0x04e2:
            goto L_0x0070;
        L_0x04e4:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x04ee:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x04f6:
            r17 = 5;
            goto L_0x0036;
        L_0x04fa:
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ InterruptedException -> 0x004b }
            r1 = r1.mApc;	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r3 = r0.mSimIndex;	 Catch:{ InterruptedException -> 0x004b }
            r4 = -1;
            r5 = 0;
            r6 = 0;
            r1.reportActivateStatus(r3, r4, r5, r6);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = r0.mApr;	 Catch:{ InterruptedException -> 0x004b }
            r3 = "v6_%s_success";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x004b }
            r5 = 0;
            r6 = r18.getName();	 Catch:{ InterruptedException -> 0x004b }
            r4[r5] = r6;	 Catch:{ InterruptedException -> 0x004b }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ InterruptedException -> 0x004b }
            r1.trackEvent(r3);	 Catch:{ InterruptedException -> 0x004b }
            r0 = r18;
            r1 = com.xiaomi.activate.ActivateService.this;	 Catch:{ InterruptedException -> 0x004b }
            com.xiaomi.activate.ActivateHelper.cancelNotifyUnactivatedSimCards(r1);	 Catch:{ InterruptedException -> 0x004b }
            r1 = 0;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ InterruptedException -> 0x004b }
            switch(r17) {
                case 1: goto L_0x084e;
                case 2: goto L_0x0859;
                case 3: goto L_0x0864;
                case 4: goto L_0x086f;
                default: goto L_0x0532;
            };
        L_0x0532:
            switch(r17) {
                case 1: goto L_0x0537;
                case 2: goto L_0x0537;
                case 3: goto L_0x087a;
                case 4: goto L_0x087a;
                default: goto L_0x0535;
            };
        L_0x0535:
            goto L_0x0070;
        L_0x0537:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x0541:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x0549:
            r1 = move-exception;
            switch(r17) {
                case 1: goto L_0x0551;
                case 2: goto L_0x055b;
                case 3: goto L_0x0565;
                case 4: goto L_0x056f;
                default: goto L_0x054d;
            };
        L_0x054d:
            switch(r17) {
                case 1: goto L_0x0579;
                case 2: goto L_0x0579;
                case 3: goto L_0x058d;
                case 4: goto L_0x058d;
                default: goto L_0x0550;
            };
        L_0x0550:
            throw r1;
        L_0x0551:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x054d;
        L_0x055b:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x054d;
        L_0x0565:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x054d;
        L_0x056f:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x054d;
        L_0x0579:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0550;
        L_0x0583:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            r3.setDiagnosisInfoCategory(r4);
            goto L_0x0550;
        L_0x058d:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            r3.setDiagnosisInfoCategory(r4);
            goto L_0x0550;
        L_0x0597:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x006d;
        L_0x05a2:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x006d;
        L_0x05ad:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x006d;
        L_0x05b8:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x006d;
        L_0x05c3:
            r0 = r18;
            r3 = r0.mApr;
            r3 = r3.hasDiagnosisInfoCategory();
            if (r3 != 0) goto L_0x0070;
        L_0x05cd:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_GET_PHONE;
            goto L_0x00d2;
        L_0x05d5:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
        L_0x05db:
            r3.setDiagnosisInfoCategory(r4);
            goto L_0x0070;
        L_0x05e0:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x015a;
        L_0x05eb:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x015a;
        L_0x05f6:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x015a;
        L_0x0601:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x015a;
        L_0x060c:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x0613:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x01f6;
        L_0x061e:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x01f6;
        L_0x0629:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x01f6;
        L_0x0634:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x01f6;
        L_0x063f:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x0646:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0258;
        L_0x0651:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0258;
        L_0x065c:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0258;
        L_0x0667:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0258;
        L_0x0672:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x067a:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0298;
        L_0x0685:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0298;
        L_0x0690:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0298;
        L_0x069b:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0298;
        L_0x06a6:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x06ae:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x01a2;
        L_0x06b9:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x01a2;
        L_0x06c4:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x01a2;
        L_0x06cf:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x01a2;
        L_0x06da:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x06e2:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x034e;
        L_0x06ed:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x034e;
        L_0x06f8:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x034e;
        L_0x0703:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x034e;
        L_0x070e:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x0716:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x02fa;
        L_0x0721:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x02fa;
        L_0x072c:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x02fa;
        L_0x0737:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x02fa;
        L_0x0742:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x074a:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x047d;
        L_0x0755:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x047d;
        L_0x0760:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x047d;
        L_0x076b:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x047d;
        L_0x0776:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x077e:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x04df;
        L_0x0789:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x04df;
        L_0x0794:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x04df;
        L_0x079f:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x04df;
        L_0x07aa:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x07b2:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x03e5;
        L_0x07bd:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x03e5;
        L_0x07c8:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x03e5;
        L_0x07d3:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x03e5;
        L_0x07de:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x07e6:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0434;
        L_0x07f1:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0434;
        L_0x07fc:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0434;
        L_0x0807:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0434;
        L_0x0812:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x081a:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x00be;
        L_0x0825:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x00be;
        L_0x0830:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x00be;
        L_0x083b:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x00be;
        L_0x0846:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
        L_0x084e:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_vkey_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0532;
        L_0x0859:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_get_phone_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0532;
        L_0x0864:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_mx_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0532;
        L_0x086f:
            r0 = r18;
            r3 = r0.mApr;
            r4 = "v6_create_xiaomi_account_fail";
            r3.setLiveReportErrorReason(r4);
            goto L_0x0532;
        L_0x087a:
            r0 = r18;
            r3 = r0.mApr;
            r4 = com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category.FAIL_ACTIVATE_OTHER;
            goto L_0x05db;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.activate.ActivateService.ActivatePhoneTaskBase.doInBackground(java.lang.Void[]):java.lang.Integer");
        }

        protected void onFinish(int result) {
            Bundle data;
            if (result == 0) {
                data = ActivateService.this.makeActivateInfoBundle(this.mActivateInfo, this.mSimIndex);
                ActivateService.this.notifyActivateStatusChanged(this.mSimIndex, ActivateService.MSG_START_VERIFY_SIM_TOKEN_TASK);
            } else {
                data = null;
                ActivateService.this.notifyActivateStatusChanged(this.mSimIndex, ActivateService.MSG_HANDLE_SIM_STATE_CHANGE);
            }
            if (this.mNotify) {
                ActivateService.this.notifyResult(EVENT.EVENT_ACTIVATE_PHONE, result, data);
            }
            this.mApr.destroy(ActivateService.this, this.mSimIndex);
        }

        protected void onCancelled(Integer result) {
            onFinish(result.intValue());
        }

        protected void onPostExecute(Integer result) {
            onFinish(result.intValue());
        }

        public void setNotify(boolean notify) {
            this.mNotify = notify;
        }
    }

    private class AutoActivatePhoneTask extends ActivatePhoneTaskBase {
        private String mGatewayNumber;
        private String mSimOperatorPattern;

        public AutoActivatePhoneTask(ActivateInfo info, int simIndex, boolean enableServices, String xiaomiAccountPassword) {
            super(info, simIndex, xiaomiAccountPassword);
        }

        protected String getName() {
            return "auto_activate";
        }

        protected int getVKeys() throws InterruptedException {
            int result = ActivateService.this.autoGetVKey(this.mActivateInfo, this.mSimIndex, this.mApr);
            if (result == 0 || !ActivateService.this.mSysInterface.allowAutoUplinkActivation()) {
                return result;
            }
            Log.i(ActivateService.TAG, "AutoActivatePhoneTask: check previous auto-uplink activation for find device.");
            if (this.mActivateInfo.hashedSimId.equals(PrefUtils.getString(ActivateService.this, ActivateService.PREF_LAST_AUTO_UPLINK_SIM_ID + this.mSimIndex))) {
                return result;
            }
            Log.i(ActivateService.TAG, "AutoActivatePhoneTask: do auto-uplink activation");
            PrefUtils.saveString(ActivateService.this, ActivateService.PREF_LAST_AUTO_UPLINK_SIM_ID + this.mSimIndex, this.mActivateInfo.hashedSimId);
            ActivateService.this.mApc.reportActivateStatus(this.mSimIndex, R.string.activate_status_desc_getting_gateway, ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
            Pair<String, String> gatewayPair = ActivateHelper.getCurrentSmsGateway(ActivateService.this, this.mSimIndex);
            if (gatewayPair == null) {
                Log.w(ActivateService.TAG, "AutoActivatePhoneTask: failed to get gw");
                ActivateService.this.mApc.reportActivateStatus(this.mSimIndex, R.string.activate_status_desc_failed_getting_gateway, ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
                return ActivateService.MSG_HANDLE_SIM_STATE_CHANGE;
            }
            this.mSimOperatorPattern = (String) gatewayPair.first;
            this.mGatewayNumber = (String) gatewayPair.second;
            this.mApr.setSimOperatorPattern(this.mSimOperatorPattern);
            this.mApr.setGateway(this.mGatewayNumber);
            return ActivateService.this.sendActivationSms(this.mActivateInfo, this.mSimIndex, this.mSimOperatorPattern, this.mGatewayNumber, this.mApr);
        }

        protected boolean getPhone() throws InterruptedException, VKeyExpiredException, NoPhoneInfoException, CloudServiceFailureException {
            return ActivateService.this.verifyPhone(this.mActivateInfo, this.mSimIndex, this.mSimOperatorPattern, this.mGatewayNumber, this.mApr);
        }

        protected void onPreExecute() {
            this.mActivateInfo.autoActivateState = AutoActivateState.RUNNING;
            super.onPreExecute();
        }

        protected void onFinish(int result) {
            super.onFinish(result);
            if (result == ActivateService.MSG_HANDLE_SIM_STATE_CHANGE) {
                this.mActivateInfo.autoActivateState = AutoActivateState.FAILED;
            } else {
                this.mActivateInfo.autoActivateState = AutoActivateState.NONE;
            }
            ActivateService.this.popupAutoActivateFailIfNeed();
        }
    }

    private class DeadRecipientImpl implements DeathRecipient {
        private RemoteObj obj;

        private DeadRecipientImpl() {
        }

        public void binderDied() {
            Log.w(ActivateService.TAG, "binder died, remote obj:" + this.obj);
            synchronized (ActivateService.this.mSessions) {
                ActivateService.this.mSessions.remove(this.obj);
            }
        }

        public void setObj(RemoteObj obj) {
            this.obj = obj;
        }
    }

    private class DownlinkActivatePhoneTask extends ActivatePhoneTaskBase {
        private final String mPhone;

        protected String getName() {
            return "downlink_activate";
        }

        public DownlinkActivatePhoneTask(ActivateInfo info, int simIndex, String phone, boolean enableServices, String xiaomiAccountPassword) {
            super(info, simIndex, xiaomiAccountPassword);
            this.mPhone = phone;
        }

        protected void onPreExecute() {
            this.mActivateInfo.autoActivateState = AutoActivateState.NONE;
            if (TextUtils.isEmpty(this.mPhone) && TextUtils.isEmpty(this.mActivateInfo.inputPhone)) {
                throw new IllegalStateException("input phone number is null");
            }
            if (!(TextUtils.isEmpty(this.mPhone) || TextUtils.equals(this.mActivateInfo.inputPhone, this.mPhone))) {
                this.mActivateInfo.inputPhone = this.mPhone;
                this.mActivateInfo.vkey1 = null;
                this.mActivateInfo.vkey2 = null;
            }
            super.onPreExecute();
        }

        protected int getVKeys() throws InterruptedException {
            return ActivateService.this.sendVKeyToPhone(this.mActivateInfo, this.mSimIndex, this.mApr);
        }

        protected boolean getPhone() throws InterruptedException, VKeyExpiredException, NoPhoneInfoException, CloudServiceFailureException {
            return ActivateService.this.verifyPhone(this.mActivateInfo, this.mSimIndex, null, null, this.mApr);
        }
    }

    public enum EVENT {
        EVENT_ACTIVATE_PHONE,
        EVENT_GET_ACTIVATE_STATUS
    }

    private class HandleSimStateChangeTask extends AsyncTask<Void, Void, Void> {
        private final boolean mInserted;
        private String mSimId;
        private final int mSimIndex;

        public HandleSimStateChangeTask(int simIndex, boolean inserted) {
            this.mSimIndex = simIndex;
            this.mInserted = inserted;
        }

        protected Void doInBackground(Void... params) {
            if (this.mInserted) {
                this.mSimId = ActivateService.this.mSysInterface.blockingGetSimId(this.mSimIndex);
            } else {
                this.mSimId = null;
            }
            return null;
        }

        protected void onPostExecute(Void dummy) {
            ActivateInfo info = ActivateService.this.mActivateInfos[this.mSimIndex];
            boolean wasInserted;
            if (info != null) {
                wasInserted = ActivateService.DEBUG;
            } else {
                wasInserted = ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
            }
            if (this.mInserted && wasInserted) {
                if (!TextUtils.isEmpty(this.mSimId)) {
                    String hashedSimId = CloudCoder.hashDeviceInfo(this.mSimId);
                    if (!(info == null || hashedSimId.equals(info.hashedSimId))) {
                        ActivateService.this.mSimTaskSets[this.mSimIndex].cancel();
                        Log.i(ActivateService.TAG, "mHandler: simId changed. Report it to server.");
                        AnalyticsTracker analytics = AnalyticsTracker.getInstance();
                        analytics.startSession(ActivateService.this);
                        AnalyticsHelper.trackEvent(analytics, AnalyticsHelper.ACTIVATE_SIM_ID_CHANGE);
                        analytics.endSession();
                    }
                }
                Log.i(ActivateService.TAG, "mHandler: Insert state is unchanged, bail.");
            } else if (this.mInserted && !wasInserted) {
                if (!(TextUtils.isEmpty(this.mSimId) || CloudCoder.hashDeviceInfo(this.mSimId).equals(PrefUtils.getString(ActivateService.this, ActivateService.PREF_LAST_AUTO_UPLINK_SIM_ID + this.mSimIndex)))) {
                    Log.i(ActivateService.TAG, "mHandler: clear previous auto-uplink activation record.");
                    PrefUtils.saveString(ActivateService.this, ActivateService.PREF_LAST_AUTO_UPLINK_SIM_ID + this.mSimIndex, null);
                }
                int delay = 0;
                if (this.mSimIndex == 0 && ActivateService.this.mSysInterface.isCdmaDevice() && ActivateService.this.mSysInterface.getPhoneType(this.mSimIndex) == ActivateService.MSG_HANDLE_SIM_STATE_CHANGE) {
                    delay = ActivateService.DUAL_MODE_PHONE_ACTIVATE_DELAY;
                }
                ActivateService.this.mSimTaskSets[this.mSimIndex].cancel();
                InitActivateInfoTask task = new InitActivateInfoTask(this.mSimIndex, delay);
                ActivateService.this.mSimTaskSets[this.mSimIndex].initActivationInfoTask = task;
                Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
                Void[] voidArr = new Void[ActivateService.MSG_HANDLE_SIM_STATE_CHANGE];
                voidArr[0] = (Void) null;
                task.executeOnExecutor(executor, voidArr);
            } else if (!this.mInserted && wasInserted) {
                ActivateService.this.mSimTaskSets[this.mSimIndex].cancel();
                ActivateService.this.mActivateInfos[this.mSimIndex] = null;
                ActivateService.this.notifyActivateStatusChanged(this.mSimIndex, ActivateService.MSG_HANDLE_SIM_STATE_CHANGE);
                ActivateService.this.notifySimStateChanged(this.mSimIndex, ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT);
            }
        }
    }

    private class InitActivateInfoTask extends AsyncTask<Void, Void, Void> {
        private final int mDelay;
        private final int mSimIndex;

        public InitActivateInfoTask(int simIndex, int delay) {
            this.mSimIndex = simIndex;
            this.mDelay = delay;
        }

        protected Void doInBackground(Void... params) {
            if (this.mDelay > 0) {
                try {
                    Thread.sleep((long) this.mDelay);
                } catch (InterruptedException e) {
                    Log.i(ActivateService.TAG, "InitActivateInfoTask: Interrupted during delay");
                    return null;
                }
            }
            Log.v(ActivateService.TAG, "InitActivateInfoTask: Getting device ID");
            String hashedDeviceId = ActivateHelper.safeGetHashedDeviceId();
            if (hashedDeviceId == null) {
                Log.e(ActivateService.TAG, "InitActivateInfoTask: Error when get device ID");
                return null;
            }
            Log.v(ActivateService.TAG, "InitActivateInfoTask: Getting SIM ID for SIM " + this.mSimIndex);
            String simId = ActivateService.this.mSysInterface.blockingGetSimId(this.mSimIndex);
            if (simId == null) {
                Log.e(ActivateService.TAG, "InitActivateInfoTask: Error when getting SIM ID for SIM " + this.mSimIndex);
                return null;
            }
            String hashedSimId = CloudCoder.hashDeviceInfo(simId);
            Log.v(ActivateService.TAG, "InitActivateInfoTask: hashedDeviceId=" + hashedDeviceId + " hashedSimId=" + hashedSimId);
            ActivateInfo info = new ActivateInfo();
            info.hashedDeviceId = hashedDeviceId;
            info.hashedSimId = hashedSimId;
            info.pseudoSimId = ActivateService.this.ensurePseudoSimId(hashedSimId);
            info.insertedToServer = ActivateService.DEBUG;
            if (ActivateDatabaseHelper.getInstance().readActivateInfo(hashedSimId, info)) {
                Log.v(ActivateService.TAG, "InitActivateInfoTask: Loaded activate info entry from db.");
            } else {
                Log.v(ActivateService.TAG, "InitActivateInfoTask: Found no activate info entry in db.");
                PrefUtils.saveBoolean(ActivateService.this, ActivateService.PREF_AUTO_ACTIVATE_FAIL_POP_UP_SHOWN, ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT);
                ActivateDatabaseHelper.getInstance().updateActivateInfo(info);
            }
            info.host = ActivateService.this.loadActivateHostPref(hashedSimId);
            if (info.host == null && info.isActivated()) {
                info.host = "ac.account.xiaomi.com";
                ActivateService.this.saveActivateHostPref(hashedSimId, info.host);
            }
            ActivateService.this.notifySimStateChanged(this.mSimIndex, ActivateService.DEBUG);
            if (info.isActivated()) {
                ActivateService.this.mActivateInfos[this.mSimIndex] = info;
                ActivateService.this.notifyActivateStatusChanged(this.mSimIndex, ActivateService.MSG_START_ACTIVATE_TASK);
                ActivateService.this.mHandler.obtainMessage(ActivateService.MSG_START_VERIFY_SIM_TOKEN_TASK, this.mSimIndex, ActivateService.MSG_ARG_UNUSED).sendToTarget();
            } else {
                Log.i(ActivateService.TAG, "InitActivateInfoTask: SIM " + this.mSimIndex + " not activated.");
                if (info.vkey1 != null || (ActivateService.this.mSysInterface.allowAutoActivation() && info.nextAutoActivationTime <= System.currentTimeMillis())) {
                    if (info.vkey1 == null) {
                        Log.i(ActivateService.TAG, "InitActivateInfoTask: Start auto-activation");
                    } else {
                        Log.i(ActivateService.TAG, "InitActivateInfoTask: Continue previoius activation");
                    }
                    ActivateArgs args = new ActivateArgs();
                    args.simIndex = this.mSimIndex;
                    args.activateMethod = ActivateService.MSG_HANDLE_SIM_STATE_CHANGE;
                    args.enableServices = ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
                    args.phoneOrCode = null;
                    args.xiaomiAccountPassword = null;
                    ActivateService.this.mActivateInfos[this.mSimIndex] = info;
                    ActivateService.this.mHandler.obtainMessage(ActivateService.MSG_START_ACTIVATE_TASK, args).sendToTarget();
                } else {
                    Log.i(ActivateService.TAG, "Skip auto-activating");
                    info.autoActivateState = AutoActivateState.FAILED;
                    ActivateService.this.mActivateInfos[this.mSimIndex] = info;
                    ActivateService.this.notifyActivateStatusChanged(this.mSimIndex, ActivateService.MSG_HANDLE_SIM_STATE_CHANGE);
                    ActivateService.this.popupAutoActivateFailIfNeed();
                }
            }
            return null;
        }
    }

    private static final class RemoteObj {
        public final DeadRecipientImpl deadRecipient;
        public final EVENT event;
        public final IActivateServiceResponse response;

        private RemoteObj(EVENT event, IActivateServiceResponse response, DeadRecipientImpl deadRecipient) {
            this.event = event;
            this.response = response;
            this.deadRecipient = deadRecipient;
        }
    }

    private class ServiceImpl extends Stub {
        private ServiceImpl() {
        }

        public void startActivateSim(int simIndex, int activateMethod, String phoneOrCode, boolean enableServices, String xiaomiAccountPassword, IActivateServiceResponse response) throws RemoteException {
            synchronized (ActivateService.this.mSessions) {
                Log.v(ActivateService.TAG, "startActivateSim on thread " + Thread.currentThread().getId());
                ActivateInfo info = ActivateService.this.mActivateInfos[simIndex];
                if (info == null) {
                    Log.w(ActivateService.TAG, "startActivateSim: sim " + simIndex + " is absent.");
                    if (response != null) {
                        response.onError(ActivateService.MSG_START_VERIFY_SIM_TOKEN_TASK, "sim " + simIndex + " is absent");
                    }
                    return;
                }
                synchronized (info) {
                    if (info.isActivated() && xiaomiAccountPassword == null) {
                        Log.v(ActivateService.TAG, "startActivateSim: sim " + simIndex + " is already activated. return result directly");
                        Bundle bundle = ActivateService.this.makeActivateInfoBundle(info, simIndex);
                        if (response != null) {
                            response.onResult(bundle);
                        }
                    } else {
                        Log.v(ActivateService.TAG, "startActivateSim: sim " + simIndex + " is unactivated. send MSG_START_ACTIVATE_TASK message.");
                        if (response != null) {
                            ActivateService.this.addRemoteObj(EVENT.EVENT_ACTIVATE_PHONE, response);
                        }
                        ActivateArgs args = new ActivateArgs();
                        args.simIndex = simIndex;
                        args.activateMethod = activateMethod;
                        args.phoneOrCode = phoneOrCode;
                        args.enableServices = enableServices;
                        args.xiaomiAccountPassword = xiaomiAccountPassword;
                        ActivateService.this.mHandler.obtainMessage(ActivateService.MSG_START_ACTIVATE_TASK, args).sendToTarget();
                    }
                }
            }
        }

        public void getSimAuthToken(int simIndex, String serviceId, IActivateServiceResponse response) throws RemoteException {
            ActivateInfo info = ActivateService.this.mActivateInfos[simIndex];
            if (info == null) {
                Log.w(ActivateService.TAG, "getSimAuthToken: sim " + simIndex + " is absent.");
                response.onError(ActivateService.MSG_START_VERIFY_SIM_TOKEN_TASK, "sim " + simIndex + " is absent");
                return;
            }
            synchronized (info) {
                String userId = info.simUserId;
                String passToken = info.simPassToken;
            }
            if (userId == null || passToken == null) {
                response.onError(4, "SIM unactivated");
            } else {
                ActivateService.this.mTaskExecutor.execute(new UserLoginRunnable(userId, passToken, serviceId, response));
            }
        }

        public void getActivateInfo(int simIndex, IActivateServiceResponse response) throws RemoteException {
            synchronized (ActivateService.this.mSessions) {
                response.onResult(ActivateService.this.makeActivateInfoBundle(ActivateService.this.mActivateInfos[simIndex], simIndex));
            }
        }

        public void hasLocalGateway(int simIndex, IActivateServiceResponse response) throws RemoteException {
            synchronized (ActivateService.this.mSessions) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("supported", ActivateHelper.hasLocalGateway(ActivateService.this, simIndex));
                response.onResult(bundle);
            }
        }
    }

    private static class SimTaskSet {
        public volatile ActivatePhoneTaskBase activationTask;
        public volatile InitActivateInfoTask initActivationInfoTask;

        private SimTaskSet() {
        }

        public void cancel() {
            if (this.initActivationInfoTask != null) {
                this.initActivationInfoTask.cancel(ActivateService.DEBUG);
            }
            if (this.activationTask != null) {
                this.activationTask.cancel(ActivateService.DEBUG);
            }
        }
    }

    private class UplinkActivatePhoneTask extends ActivatePhoneTaskBase {
        private String mGatewayNumber;
        private String mSimOperatorPattern;

        protected String getName() {
            return "uplink_activate";
        }

        public UplinkActivatePhoneTask(ActivateInfo info, int simIndex, boolean enableServices, String xiaomiAccountPassword) {
            super(info, simIndex, xiaomiAccountPassword);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.mActivateInfo.autoActivateState = AutoActivateState.NONE;
        }

        protected int getVKeys() throws InterruptedException {
            Log.v(ActivateService.TAG, "UplinkActivatePhoneTask: getting smsGateway for SIM " + this.mSimIndex);
            ActivateService.this.mApc.reportActivateStatus(this.mSimIndex, R.string.activate_status_desc_getting_gateway, ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
            Pair<String, String> gatewayPair = ActivateHelper.getCurrentSmsGateway(ActivateService.this, this.mSimIndex);
            if (gatewayPair == null) {
                Log.w(ActivateService.TAG, "UplinkActivatePhoneTask: failed to get gw");
                ActivateService.this.mApc.reportActivateStatus(this.mSimIndex, R.string.activate_status_desc_failed_getting_gateway, ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
                return ActivateService.MSG_HANDLE_SIM_STATE_CHANGE;
            }
            this.mSimOperatorPattern = (String) gatewayPair.first;
            this.mGatewayNumber = (String) gatewayPair.second;
            this.mApr.setSimOperatorPattern(this.mSimOperatorPattern);
            this.mApr.setGateway(this.mGatewayNumber);
            return ActivateService.this.sendActivationSms(this.mActivateInfo, this.mSimIndex, this.mSimOperatorPattern, this.mGatewayNumber, this.mApr);
        }

        protected boolean getPhone() throws InterruptedException, VKeyExpiredException, NoPhoneInfoException, CloudServiceFailureException {
            return ActivateService.this.verifyPhone(this.mActivateInfo, this.mSimIndex, this.mSimOperatorPattern, this.mGatewayNumber, this.mApr);
        }
    }

    private class UserLoginRunnable implements Runnable {
        private final String mPassToken;
        private final IActivateServiceResponse mResponse;
        private final String mSid;
        private final String mUserId;

        private UserLoginRunnable(String userId, String passToken, String sid, IActivateServiceResponse response) {
            this.mUserId = userId;
            this.mPassToken = passToken;
            this.mSid = sid;
            this.mResponse = response;
        }

        public void run() {
            int code = 0;
            AccountInfo info = null;
            try {
                info = XMPassport.loginByPassToken(this.mUserId, this.mSid, ActivateHelper.safeGetHashedDeviceId(), this.mPassToken);
            } catch (IOException e) {
                code = 6;
                Log.e(ActivateService.TAG, "UserLoginRunnable error", e);
            } catch (InvalidCredentialException e2) {
                code = 7;
                Log.e(ActivateService.TAG, "UserLoginRunnable error", e2);
            } catch (InvalidUserNameException e3) {
                code = 7;
                Log.e(ActivateService.TAG, "UserLoginRunnable error", e3);
            } catch (InvalidResponseException e4) {
                code = ActivateService.MSG_HANDLE_SIM_STATE_CHANGE;
                Log.e(ActivateService.TAG, "UserLoginRunnable error", e4);
            } catch (AccessDeniedException e5) {
                code = ActivateService.MSG_HANDLE_SIM_STATE_CHANGE;
                Log.e(ActivateService.TAG, "UserLoginRunnable error", e5);
            } catch (AuthenticationFailureException e6) {
                code = ActivateService.MSG_HANDLE_SIM_STATE_CHANGE;
                Log.e(ActivateService.TAG, "UserLoginRunnable error", e6);
            }
            if (info != null) {
                Bundle bundle = new Bundle();
                bundle.putString("user_token", info.getServiceToken());
                bundle.putString("user_security", info.getSecurity());
                try {
                    this.mResponse.onResult(bundle);
                    return;
                } catch (RemoteException e7) {
                    return;
                }
            }
            try {
                this.mResponse.onError(code, null);
            } catch (RemoteException e8) {
            }
        }
    }

    private class VerifySimTokenTask extends AsyncTask<Void, Void, Void> {
        private final int mSimIndex;

        public VerifySimTokenTask(int simIndex) {
            this.mSimIndex = simIndex;
        }

        protected Void doInBackground(Void... params) {
            ActivateInfo info = ActivateService.this.mActivateInfos[this.mSimIndex];
            if (info != null && info.isActivated()) {
                try {
                    Log.v(ActivateService.TAG, "Start verifying passtoken for SIM " + this.mSimIndex);
                    AccountInfo accountInfo = ActivateHelper.verifyAndRenewPassToken(info.simUserId, info.simPassToken);
                    if (accountInfo == null) {
                        Log.e(ActivateService.TAG, "Invalid passtoken for SIM " + this.mSimIndex + ". Clear.");
                        synchronized (info) {
                            info.nextAutoActivationTime = 0;
                            info.simPassToken = null;
                        }
                        ActivateDatabaseHelper.getInstance().updateActivateInfo(info);
                        ActivateArgs args = new ActivateArgs();
                        args.simIndex = this.mSimIndex;
                        args.activateMethod = ActivateService.MSG_HANDLE_SIM_STATE_CHANGE;
                        args.enableServices = ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
                        args.phoneOrCode = null;
                        args.xiaomiAccountPassword = null;
                        ActivateService.this.mActivateInfos[this.mSimIndex] = info;
                        ActivateService.this.mHandler.obtainMessage(ActivateService.MSG_START_ACTIVATE_TASK, args).sendToTarget();
                    } else {
                        info.simPassToken = accountInfo.getPassToken();
                        Log.d(ActivateService.TAG, "Renewing passtoken for SIM " + this.mSimIndex);
                        ActivateDatabaseHelper.getInstance().updateActivateInfo(info);
                        ActivateService.this.notifyActivateStatusChanged(this.mSimIndex, ActivateService.MSG_START_VERIFY_SIM_TOKEN_TASK);
                        if (!info.insertedToServer) {
                            Log.d(ActivateService.TAG, "Current activate info for SIM " + this.mSimIndex + " isn't inserted to server. Do it now.");
                            if (ActivateHelper.insertActivateInfoToServer(accountInfo.getUserId(), accountInfo.getServiceToken(), "passportapi", UUID.randomUUID().toString().substring(0, 15), accountInfo.getSecurity(), info.hashedDeviceId, info.hashedSimId, info.pseudoSimId, info.phone, info.host)) {
                                synchronized (info) {
                                    info.insertedToServer = ActivateService.DEBUG;
                                }
                                ActivateDatabaseHelper.getInstance().updateActivateInfo(info);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    Log.e(ActivateService.TAG, "failed to verify/renew sim pass token", e);
                } catch (CloudServiceFailureException e2) {
                    Log.e(ActivateService.TAG, "failed to verify/renew sim pass token", e2);
                }
            }
            return null;
        }
    }

    public ActivateService() {
        this.mSessions = new LinkedList();
        this.mActivateInfos = new ActivateInfo[MSG_START_ACTIVATE_TASK];
        this.mTaskExecutor = new ThreadPoolExecutor(MSG_HANDLE_SIM_STATE_CHANGE, 5, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                boolean inserted = ActivateService.DEBUG;
                switch (msg.what) {
                    case ActivateService.MSG_HANDLE_SIM_STATE_CHANGE /*1*/:
                        int simIndex = msg.arg1;
                        if (msg.arg2 == 0) {
                            inserted = ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
                        }
                        Log.v(ActivateService.TAG, "mHandler handles MSG_HANDLE_SIM_STATE_CHANGE for sim " + simIndex + " inserted=" + inserted);
                        if (simIndex == ActivateService.MSG_ARG_UNUSED) {
                            throw new IllegalArgumentException("simIndex should not be null");
                        }
                        new HandleSimStateChangeTask(simIndex, inserted).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        return;
                    case ActivateService.MSG_START_ACTIVATE_TASK /*2*/:
                        ActivateArgs args = msg.obj;
                        Log.v(ActivateService.TAG, "mHandler starts activate SIM, simIndex=" + args.simIndex + " activateMethod=" + args.activateMethod + " enableServices=" + args.enableServices);
                        ActivatePhoneTaskBase task = ActivateService.this.mSimTaskSets[args.simIndex].activationTask;
                        if ((task instanceof AutoActivatePhoneTask) && args.activateMethod != ActivateService.MSG_HANDLE_SIM_STATE_CHANGE) {
                            Log.v(ActivateService.TAG, "mHandler: Cancelling ongoing auto activate task first due to activation strategy...");
                            task.setNotify(ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT);
                            task.cancel(ActivateService.DEBUG);
                            task = null;
                        }
                        ActivateInfo info = ActivateService.this.mActivateInfos[args.simIndex];
                        if (ActivateService.isTaskDone(task)) {
                            switch (args.activateMethod) {
                                case ActivateService.MSG_HANDLE_SIM_STATE_CHANGE /*1*/:
                                    task = new AutoActivatePhoneTask(info, args.simIndex, args.enableServices, args.xiaomiAccountPassword);
                                    ActivateService.this.mApc.setQuiet(args.simIndex, ActivateService.DEBUG);
                                    break;
                                case ActivateService.MSG_START_ACTIVATE_TASK /*2*/:
                                    task = new UplinkActivatePhoneTask(info, args.simIndex, args.enableServices, args.xiaomiAccountPassword);
                                    ActivateService.this.mApc.setQuiet(args.simIndex, ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT);
                                    ActivateHelper.cancelNotifyUnactivatedSimCards(ActivateService.this);
                                    break;
                                case ActivateService.MSG_START_VERIFY_SIM_TOKEN_TASK /*3*/:
                                    task = new DownlinkActivatePhoneTask(info, args.simIndex, args.phoneOrCode, args.enableServices, args.xiaomiAccountPassword);
                                    ActivateService.this.mApc.setQuiet(args.simIndex, ActivateService.TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT);
                                    ActivateHelper.cancelNotifyUnactivatedSimCards(ActivateService.this);
                                    break;
                                default:
                                    Log.e(ActivateService.TAG, "mHandler: Invalid activateMethod " + args.activateMethod);
                                    return;
                            }
                            if (task != null) {
                                ActivateService.this.mSimTaskSets[args.simIndex].activationTask = task;
                                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                                Log.d(ActivateService.TAG, "mHandler: activate phone task started for sim " + args.simIndex + ", type is " + task.getClass().getSimpleName());
                                return;
                            }
                            return;
                        }
                        Log.d(ActivateService.TAG, "mHandler: activate phone task is running for sim " + args.simIndex + ", type is " + task.getClass().getSimpleName());
                        return;
                    case ActivateService.MSG_START_VERIFY_SIM_TOKEN_TASK /*3*/:
                        new VerifySimTokenTask(msg.arg1).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        return;
                    case ActivateService.MSG_SHOW_TOAST /*8*/:
                        Toast.makeText(ActivateService.this, msg.arg1, 0).show();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    static {
        TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT = new File("/data/system/override_existing_account").exists();
    }

    public void onCreate() {
        super.onCreate();
        if (hasTelephonyFeature(getApplicationContext())) {
            Log.i(TAG, "Creating ActivateService");
            sInstance = this;
            this.mApc = new ActivateProgressController(this);
            this.mSysInterface = ActivateExternal.getSysInteface();
            this.mSimCount = this.mSysInterface.getSimCount();
            this.mSimTaskSets = new SimTaskSet[this.mSimCount];
            this.mPhoneServiceStateListener = new PhoneServiceStateListener[MSG_START_ACTIVATE_TASK];
            for (int i = 0; i < this.mSimCount; i += MSG_HANDLE_SIM_STATE_CHANGE) {
                this.mSimTaskSets[i] = new SimTaskSet();
                if (this.mSysInterface.isSimInserted(i)) {
                    this.mHandler.obtainMessage(MSG_HANDLE_SIM_STATE_CHANGE, i, MSG_HANDLE_SIM_STATE_CHANGE).sendToTarget();
                } else {
                    Log.v(TAG, "Sim " + i + " is not inserted when creating ActivateService.");
                }
                this.mPhoneServiceStateListener[i] = new PhoneServiceStateListener();
                this.mSysInterface.listen(this.mPhoneServiceStateListener[i], MSG_HANDLE_SIM_STATE_CHANGE, i);
            }
            this.mTaskExecutor.setRejectedExecutionHandler(new DiscardPolicy());
            this.mWorkThread = new HandlerThread("WorkHandlerThread");
            this.mWorkThread.start();
            return;
        }
        Log.w(TAG, "Has no telephony feature, ActivateService will be stopped right away after created.");
        stopSelf();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        int i = 0;
        String action = intent.getAction();
        if (ACTION_INTERNAL_NOTIFY_SIM_STATE_CHANGED.equals(action)) {
            Log.v(TAG, "onStartCommand ACTION_INTERNAL_NOTIFY_SIM_STATE_CHANGED");
            int simIndex = intent.getIntExtra("extra_sim_index", MSG_ARG_UNUSED);
            boolean inserted = intent.getBooleanExtra("extra_sim_inserted", TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT);
            this.mHandler.removeMessages(MSG_HANDLE_SIM_STATE_CHANGE);
            Handler handler = this.mHandler;
            if (inserted) {
                i = MSG_HANDLE_SIM_STATE_CHANGE;
            }
            handler.obtainMessage(MSG_HANDLE_SIM_STATE_CHANGE, simIndex, i).sendToTarget();
        } else if ("com.xiaomi.action.ACTION_SET_ACTIVATE_PROGRESS_CALLBACK".equals(action)) {
            Log.v(TAG, "onStartCommand ACTION_SET_ACTIVATE_PROGRESS_CALLBACK");
            this.mApc.setActivateStatusCallback(intent.getIntExtra("extra_sim_index", MSG_ARG_UNUSED), (PendingIntent) intent.getParcelableExtra("EXTRA_CALLBACK"));
        }
        return MSG_START_ACTIVATE_TASK;
    }

    public void onDestroy() {
        if (hasTelephonyFeature(getApplicationContext())) {
            this.mHandler.removeMessages(MSG_HANDLE_SIM_STATE_CHANGE);
            this.mHandler.removeMessages(MSG_START_ACTIVATE_TASK);
            this.mWorkThread.quit();
            SimTaskSet[] arr$ = this.mSimTaskSets;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$ += MSG_HANDLE_SIM_STATE_CHANGE) {
                arr$[i$].cancel();
            }
            this.mTaskExecutor.shutdownNow();
            this.mApc = null;
            if (sInstance == this) {
                sInstance = null;
            }
        }
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        if (this.mService == null) {
            this.mService = new ServiceImpl();
        }
        return this.mService.asBinder();
    }

    public static int getActivateStatus(int simIndex) {
        if (sInstance == null || sInstance.mActivateInfos == null) {
            return 0;
        }
        ActivateInfo info = sInstance.mActivateInfos[simIndex];
        if (info != null) {
            return sInstance.getActivateStatus(info, simIndex);
        }
        return 0;
    }

    private boolean doesAccountExist(int simIndex, String address) throws CloudServiceFailureException, InterruptedException {
        Log.d(TAG, "doesAccountExist: simIndex = " + simIndex);
        this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_checking_xiaomi_account, TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
        try {
            return ActivateHelper.doesAccountExist("PH", address);
        } catch (CloudServiceFailureException e) {
            this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_checking_xiaomi_account_server_error, DEBUG, ActivateHelper.getActivatePopupPendingIntent(this, simIndex, 7, null));
            throw e;
        }
    }

    private boolean createOrUpdateUser(ActivateInfo info, boolean createXiaomiUser, int simIndex, boolean useRandomPassword, String password, ActivateProgressReporter apr) throws VKeyExpiredException, InterruptedException {
        String type = createXiaomiUser ? "PH" : "MXPH";
        Log.d(TAG, "createOrUpdateUser: createOrUpdateUser of type " + type + " for sim " + simIndex + " with useRandomPassword=" + useRandomPassword);
        this.mApc.reportActivateStatus(simIndex, createXiaomiUser ? R.string.activate_status_desc_registering_xiaomi_account : R.string.activate_status_desc_registering_sim_account, TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
        if (info.phone == null) {
            throw new IllegalStateException("createOrUpdateUser: phone is null");
        }
        if (useRandomPassword) {
            password = ActivateHelper.generateRandomHex(16);
        }
        try {
            Pair<String, String> result = ActivateHelper.createOrUpdateUser(simIndex, type, ActivateHelper.MICLOUD_SID, info.traceId, info.hashedDeviceId, info.hashedSimId, info.pseudoSimId, info.vkey1, info.vkey2, info.phone, password, apr.mDiagnosisInfo, info.host);
            String userId = (String) result.first;
            String passToken = (String) result.second;
            if (userId == null) {
                return TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
            }
            if (createXiaomiUser) {
                synchronized (info) {
                    info.xiaomiUserId = userId;
                    info.xiaomiPassToken = passToken;
                }
            } else {
                synchronized (info) {
                    info.simUserId = userId;
                    info.simPassToken = passToken;
                }
            }
            return DEBUG;
        } catch (CloudServiceFailureException e) {
            this.mApc.reportActivateStatus(simIndex, createXiaomiUser ? R.string.activate_status_desc_registering_xiaomi_account_server_error : R.string.activate_status_desc_registering_sim_account_server_error, DEBUG, ActivateHelper.getActivatePopupPendingIntent(this, simIndex, 7, null));
            return TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
        }
    }

    protected void startNewActivation(ActivateInfo info, int simIndex) {
        if (info.hashedDeviceId == null) {
            throw new IllegalStateException("assignTraceId: activateInfo is not initialized for SIM " + simIndex);
        }
        synchronized (info) {
            info.traceId = UUID.randomUUID().toString().substring(0, 15);
            Log.v(TAG, "new traceId " + info.traceId + " is generated for activating sim card " + simIndex + ", with pseudoSimId=" + info.pseudoSimId);
        }
        notifyActivateStatusChanged(simIndex, MSG_START_ACTIVATE_TASK);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int autoGetVKey(com.xiaomi.activate.ActivateInfo r16, int r17, com.xiaomi.activate.ActivateProgressReporter r18) throws java.lang.InterruptedException {
        /*
        r15 = this;
        r1 = "ActivateService";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "autoGetVKey: simIndex=";
        r2 = r2.append(r3);
        r0 = r17;
        r2 = r2.append(r0);
        r2 = r2.toString();
        android.util.Log.v(r1, r2);
        r11 = new com.xiaomi.activate.ActivateHelper$ActivationSmsReceiver;
        r11.<init>();
        r11.register(r15);
        r1 = r15.mApc;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = 2131165198; // 0x7f07000e float:1.7944606E38 double:1.05293551E-314;
        r3 = 0;
        r4 = 0;
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = "micloud";
        r0 = r16;
        r2 = r0.traceId;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r16;
        r3 = r0.hashedDeviceId;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r16;
        r4 = r0.hashedSimId;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r16;
        r5 = r0.pseudoSimId;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r7 = r15.mPhoneServiceStateListener;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r6 = r7[r17];	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r16;
        r7 = r0.host;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r12 = com.xiaomi.activate.ActivateHelper.getPhoneBySimIdDevId(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = java.lang.System.currentTimeMillis();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r1 = r1 + r3;
        r0 = r16;
        r0.nextAutoActivationTime = r1;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        if (r12 == 0) goto L_0x0060;
    L_0x005a:
        r1 = r12.isEmpty();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        if (r1 == 0) goto L_0x00d4;
    L_0x0060:
        r1 = "ActivateService";
        r2 = new java.lang.StringBuilder;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2.<init>();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = "No saved number on server for sim ";
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r17;
        r2 = r2.append(r0);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = " for auto activation.";
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = r2.toString();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        android.util.Log.v(r1, r2);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = r15.mSysInterface;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r17;
        r6 = r1.getLine1Number(r0);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = android.text.TextUtils.isEmpty(r6);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        if (r1 != 0) goto L_0x0163;
    L_0x008e:
        r1 = "ActivateService";
        r2 = new java.lang.StringBuilder;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2.<init>();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = "Try activating by line 1 number: ";
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = mask(r6);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = r2.toString();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        android.util.Log.v(r1, r2);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = r15.mApc;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = 2131165205; // 0x7f070015 float:1.794462E38 double:1.0529355134E-314;
        r3 = 0;
        r4 = 0;
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = "micloud";
        r0 = r16;
        r2 = r0.traceId;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r16;
        r3 = r0.hashedDeviceId;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r16;
        r4 = r0.hashedSimId;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r16;
        r5 = r0.pseudoSimId;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r7 = r15.mPhoneServiceStateListener;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r7 = r7[r17];	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r16;
        r8 = r0.host;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r12 = com.xiaomi.activate.ActivateHelper.sendVKeyToPhone(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
    L_0x00d4:
        r1 = r15.mApc;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = 2131165202; // 0x7f070012 float:1.7944614E38 double:1.052935512E-314;
        r3 = 0;
        r4 = 0;
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = "ActivateService";
        r2 = new java.lang.StringBuilder;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2.<init>();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = "autoGetVKey: Found ";
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = r12.size();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = " entries for current sim card. Wait for incoming activation sms";
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = r2.toString();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        android.util.Log.i(r1, r2);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = "v6_%s_wait_for_sms";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = 0;
        r4 = "auto_activate";
        r2[r3] = r4;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r18;
        r0.trackTimedEvent(r1);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
    L_0x0115:
        r1 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r13 = r11.getResult(r1);	 Catch:{ all -> 0x01bd }
        if (r13 != 0) goto L_0x0191;
    L_0x011e:
        r1 = "ActivateService";
        r2 = "autoGetVKey: Didn't get activation sms in time limit. Bail.";
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x01bd }
        r1 = r15.mApc;	 Catch:{ all -> 0x01bd }
        r2 = 2131165204; // 0x7f070014 float:1.7944619E38 double:1.052935513E-314;
        r3 = 1;
        r4 = 5;
        r5 = 0;
        r0 = r17;
        r4 = com.xiaomi.activate.ActivateHelper.getActivatePopupPendingIntent(r15, r0, r4, r5);	 Catch:{ all -> 0x01bd }
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x01bd }
        r1 = "v6_%s_wait_for_sms_fail";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x01bd }
        r3 = 0;
        r4 = "auto_activate";
        r2[r3] = r4;	 Catch:{ all -> 0x01bd }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ all -> 0x01bd }
        r0 = r18;
        r0.trackEvent(r1);	 Catch:{ all -> 0x01bd }
        r1 = 1;
        r2 = "v6_%s_wait_for_sms";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r4 = 0;
        r5 = "auto_activate";
        r3[r4] = r5;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = java.lang.String.format(r2, r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r18;
        r0.endTimedEvent(r2);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
    L_0x015f:
        r15.unregisterReceiver(r11);
        return r1;
    L_0x0163:
        r1 = r15.mApc;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = 2131165199; // 0x7f07000f float:1.7944608E38 double:1.0529355104E-314;
        r3 = 1;
        r4 = 0;
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = "ActivateService";
        r2 = new java.lang.StringBuilder;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2.<init>();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = "No line 1 number for sim ";
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r17;
        r2 = r2.append(r0);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r3 = " for auto activation. Bail.";
        r2 = r2.append(r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = r2.toString();	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        android.util.Log.w(r1, r2);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r1 = 1;
        goto L_0x015f;
    L_0x0191:
        r10 = r13.first;	 Catch:{ all -> 0x01bd }
        r10 = (java.lang.String) r10;	 Catch:{ all -> 0x01bd }
        r14 = r12.get(r10);	 Catch:{ all -> 0x01bd }
        r14 = (java.lang.String) r14;	 Catch:{ all -> 0x01bd }
        if (r14 != 0) goto L_0x01e9;
    L_0x019d:
        r1 = "ActivateService";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bd }
        r2.<init>();	 Catch:{ all -> 0x01bd }
        r3 = "autoGetVKey: Received activation sms (msgId=";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01bd }
        r2 = r2.append(r10);	 Catch:{ all -> 0x01bd }
        r3 = ") didn't match server results. Wait for another one.";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01bd }
        r2 = r2.toString();	 Catch:{ all -> 0x01bd }
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x01bd }
        goto L_0x0115;
    L_0x01bd:
        r1 = move-exception;
        r2 = "v6_%s_wait_for_sms";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r4 = 0;
        r5 = "auto_activate";
        r3[r4] = r5;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = java.lang.String.format(r2, r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r18;
        r0.endTimedEvent(r2);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        throw r1;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
    L_0x01d2:
        r9 = move-exception;
        r1 = r15.mApc;	 Catch:{ all -> 0x0267 }
        r2 = 2131165200; // 0x7f070010 float:1.794461E38 double:1.052935511E-314;
        r3 = 1;
        r4 = 7;
        r5 = 0;
        r0 = r17;
        r4 = com.xiaomi.activate.ActivateHelper.getActivatePopupPendingIntent(r15, r0, r4, r5);	 Catch:{ all -> 0x0267 }
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x0267 }
        r1 = 1;
        goto L_0x015f;
    L_0x01e9:
        r1 = "ActivateService";
        r2 = "autoGetVKey: Received activation sms matched server results.";
        android.util.Log.i(r1, r2);	 Catch:{ all -> 0x01bd }
        r1 = r15.mApc;	 Catch:{ all -> 0x01bd }
        r2 = 2131165203; // 0x7f070013 float:1.7944616E38 double:1.0529355124E-314;
        r3 = 0;
        r4 = 0;
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x01bd }
        monitor-enter(r16);	 Catch:{ all -> 0x01bd }
        r1 = r13.second;	 Catch:{ all -> 0x024a }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x024a }
        r0 = r16;
        r0.vkey1 = r1;	 Catch:{ all -> 0x024a }
        r0 = r16;
        r0.vkey2 = r14;	 Catch:{ all -> 0x024a }
        monitor-exit(r16);	 Catch:{ all -> 0x024a }
        r1 = "v6_%s_wait_for_sms_success";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x01bd }
        r3 = 0;
        r4 = "auto_activate";
        r2[r3] = r4;	 Catch:{ all -> 0x01bd }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ all -> 0x01bd }
        r0 = r18;
        r0.trackEvent(r1);	 Catch:{ all -> 0x01bd }
        r1 = 0;
        r2 = "v6_%s_wait_for_sms";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r4 = 0;
        r5 = "auto_activate";
        r3[r4] = r5;	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r2 = java.lang.String.format(r2, r3);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        r0 = r18;
        r0.endTimedEvent(r2);	 Catch:{ CloudServiceFailureException -> 0x01d2, DownlinkSmsFailedException -> 0x0233, PhoneErrorException -> 0x024d }
        goto L_0x015f;
    L_0x0233:
        r9 = move-exception;
        r1 = r15.mApc;	 Catch:{ all -> 0x0267 }
        r2 = 2131165201; // 0x7f070011 float:1.7944612E38 double:1.0529355114E-314;
        r3 = 1;
        r4 = 7;
        r5 = 0;
        r0 = r17;
        r4 = com.xiaomi.activate.ActivateHelper.getActivatePopupPendingIntent(r15, r0, r4, r5);	 Catch:{ all -> 0x0267 }
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x0267 }
        r1 = 1;
        goto L_0x015f;
    L_0x024a:
        r1 = move-exception;
        monitor-exit(r16);	 Catch:{ all -> 0x024a }
        throw r1;	 Catch:{ all -> 0x01bd }
    L_0x024d:
        r9 = move-exception;
        r1 = r15.mApc;	 Catch:{ all -> 0x0267 }
        r2 = 2131165207; // 0x7f070017 float:1.7944625E38 double:1.0529355144E-314;
        r3 = 1;
        r4 = 6;
        r0 = r16;
        r5 = r0.inputPhone;	 Catch:{ all -> 0x0267 }
        r0 = r17;
        r4 = com.xiaomi.activate.ActivateHelper.getActivatePopupPendingIntent(r15, r0, r4, r5);	 Catch:{ all -> 0x0267 }
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x0267 }
        r1 = 1;
        goto L_0x015f;
    L_0x0267:
        r1 = move-exception;
        r15.unregisterReceiver(r11);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.activate.ActivateService.autoGetVKey(com.xiaomi.activate.ActivateInfo, int, com.xiaomi.activate.ActivateProgressReporter):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int sendVKeyToPhone(com.xiaomi.activate.ActivateInfo r16, int r17, com.xiaomi.activate.ActivateProgressReporter r18) throws java.lang.InterruptedException {
        /*
        r15 = this;
        r1 = "ActivateService";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "sendVKeyToPhone: simIndex=";
        r2 = r2.append(r3);
        r0 = r17;
        r2 = r2.append(r0);
        r2 = r2.toString();
        android.util.Log.v(r1, r2);
        r11 = new com.xiaomi.activate.ActivateHelper$ActivationSmsReceiver;
        r11.<init>();
        r11.register(r15);
        r1 = r15.mApc;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r2 = 2131165205; // 0x7f070015 float:1.794462E38 double:1.0529355134E-314;
        r3 = 0;
        r4 = 0;
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r1 = "micloud";
        r0 = r16;
        r2 = r0.traceId;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r16;
        r3 = r0.hashedDeviceId;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r16;
        r4 = r0.hashedSimId;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r16;
        r5 = r0.pseudoSimId;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r16;
        r6 = r0.inputPhone;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r7 = r15.mPhoneServiceStateListener;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r7 = r7[r17];	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r16;
        r8 = r0.host;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r12 = com.xiaomi.activate.ActivateHelper.sendVKeyToPhone(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r1 = r15.mApc;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r2 = 2131165202; // 0x7f070012 float:1.7944614E38 double:1.052935512E-314;
        r3 = 0;
        r4 = 0;
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r1 = "ActivateService";
        r2 = "sendVKeyToPhone: Wait for incoming activation sms";
        android.util.Log.i(r1, r2);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r1 = "v6_%s_wait_for_sms";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r3 = 0;
        r4 = "downlink_activate";
        r2[r3] = r4;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r18;
        r0.trackTimedEvent(r1);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
    L_0x0076:
        r1 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r13 = r11.getResult(r1);	 Catch:{ all -> 0x00f2 }
        if (r13 != 0) goto L_0x00c7;
    L_0x007f:
        r1 = "ActivateService";
        r2 = "sendVKeyToPhone: Didn't get activation sms in time limit. Bail.";
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x00f2 }
        r1 = r15.mApc;	 Catch:{ all -> 0x00f2 }
        r2 = 2131165204; // 0x7f070014 float:1.7944619E38 double:1.052935513E-314;
        r3 = 1;
        r4 = 5;
        r0 = r16;
        r5 = r0.inputPhone;	 Catch:{ all -> 0x00f2 }
        r0 = r17;
        r4 = com.xiaomi.activate.ActivateHelper.getActivatePopupPendingIntent(r15, r0, r4, r5);	 Catch:{ all -> 0x00f2 }
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x00f2 }
        r1 = "v6_%s_wait_for_sms_fail";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00f2 }
        r3 = 0;
        r4 = "downlink_activate";
        r2[r3] = r4;	 Catch:{ all -> 0x00f2 }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ all -> 0x00f2 }
        r0 = r18;
        r0.trackEvent(r1);	 Catch:{ all -> 0x00f2 }
        r1 = 1;
        r2 = "v6_%s_wait_for_sms";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r4 = 0;
        r5 = "downlink_activate";
        r3[r4] = r5;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r2 = java.lang.String.format(r2, r3);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r18;
        r0.endTimedEvent(r2);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
    L_0x00c3:
        r15.unregisterReceiver(r11);
        return r1;
    L_0x00c7:
        r10 = r13.first;	 Catch:{ all -> 0x00f2 }
        r10 = (java.lang.String) r10;	 Catch:{ all -> 0x00f2 }
        r14 = r12.get(r10);	 Catch:{ all -> 0x00f2 }
        r14 = (java.lang.String) r14;	 Catch:{ all -> 0x00f2 }
        if (r14 != 0) goto L_0x0120;
    L_0x00d3:
        r1 = "ActivateService";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f2 }
        r2.<init>();	 Catch:{ all -> 0x00f2 }
        r3 = "sendVKeyToPhone: Received activation sms (msgId=";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00f2 }
        r2 = r2.append(r10);	 Catch:{ all -> 0x00f2 }
        r3 = ") didn't match server results. Wait for another one.";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00f2 }
        r2 = r2.toString();	 Catch:{ all -> 0x00f2 }
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x00f2 }
        goto L_0x0076;
    L_0x00f2:
        r1 = move-exception;
        r2 = "v6_%s_wait_for_sms";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r4 = 0;
        r5 = "downlink_activate";
        r3[r4] = r5;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r2 = java.lang.String.format(r2, r3);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r18;
        r0.endTimedEvent(r2);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        throw r1;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
    L_0x0107:
        r9 = move-exception;
        r1 = r15.mApc;	 Catch:{ all -> 0x0188 }
        r2 = 2131165206; // 0x7f070016 float:1.7944623E38 double:1.052935514E-314;
        r3 = 1;
        r4 = 7;
        r0 = r16;
        r5 = r0.inputPhone;	 Catch:{ all -> 0x0188 }
        r0 = r17;
        r4 = com.xiaomi.activate.ActivateHelper.getActivatePopupPendingIntent(r15, r0, r4, r5);	 Catch:{ all -> 0x0188 }
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x0188 }
        r1 = 1;
        goto L_0x00c3;
    L_0x0120:
        r1 = "ActivateService";
        r2 = "sendVKeyToPhone: Received activation sms matched server results.";
        android.util.Log.i(r1, r2);	 Catch:{ all -> 0x00f2 }
        r1 = r15.mApc;	 Catch:{ all -> 0x00f2 }
        r2 = 2131165203; // 0x7f070013 float:1.7944616E38 double:1.0529355124E-314;
        r3 = 0;
        r4 = 0;
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x00f2 }
        monitor-enter(r16);	 Catch:{ all -> 0x00f2 }
        r1 = r13.second;	 Catch:{ all -> 0x0185 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0185 }
        r0 = r16;
        r0.vkey1 = r1;	 Catch:{ all -> 0x0185 }
        r0 = r16;
        r0.vkey2 = r14;	 Catch:{ all -> 0x0185 }
        monitor-exit(r16);	 Catch:{ all -> 0x0185 }
        r1 = "v6_%s_wait_for_sms_success";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00f2 }
        r3 = 0;
        r4 = "downlink_activate";
        r2[r3] = r4;	 Catch:{ all -> 0x00f2 }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ all -> 0x00f2 }
        r0 = r18;
        r0.trackEvent(r1);	 Catch:{ all -> 0x00f2 }
        r1 = 0;
        r2 = "v6_%s_wait_for_sms";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r4 = 0;
        r5 = "downlink_activate";
        r3[r4] = r5;	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r2 = java.lang.String.format(r2, r3);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        r0 = r18;
        r0.endTimedEvent(r2);	 Catch:{ CloudServiceFailureException -> 0x0107, PhoneErrorException -> 0x016a }
        goto L_0x00c3;
    L_0x016a:
        r9 = move-exception;
        r1 = r15.mApc;	 Catch:{ all -> 0x0188 }
        r2 = 2131165207; // 0x7f070017 float:1.7944625E38 double:1.0529355144E-314;
        r3 = 1;
        r4 = 6;
        r0 = r16;
        r5 = r0.inputPhone;	 Catch:{ all -> 0x0188 }
        r0 = r17;
        r4 = com.xiaomi.activate.ActivateHelper.getActivatePopupPendingIntent(r15, r0, r4, r5);	 Catch:{ all -> 0x0188 }
        r0 = r17;
        r1.reportActivateStatus(r0, r2, r3, r4);	 Catch:{ all -> 0x0188 }
        r1 = 11;
        goto L_0x00c3;
    L_0x0185:
        r1 = move-exception;
        monitor-exit(r16);	 Catch:{ all -> 0x0185 }
        throw r1;	 Catch:{ all -> 0x00f2 }
    L_0x0188:
        r1 = move-exception;
        r15.unregisterReceiver(r11);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.activate.ActivateService.sendVKeyToPhone(com.xiaomi.activate.ActivateInfo, int, com.xiaomi.activate.ActivateProgressReporter):int");
    }

    protected int sendActivationSms(ActivateInfo info, int simIndex, String simOperatorPattern, String gatewayNumber, ActivateProgressReporter apr) throws InterruptedException {
        String vkey1 = null;
        String vkey2 = null;
        IntentFilter filter = new IntentFilter(ACTION_INTERNAL_ACTIVATE_SMS_SENT);
        int tryCount = 0;
        boolean smsSent = TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
        loop0:
        while (!Thread.currentThread().isInterrupted() && tryCount < MSG_START_VERIFY_SIM_TOKEN_TASK) {
            tryCount += MSG_HANDLE_SIM_STATE_CHANGE;
            vkey1 = ActivateHelper.makeVKey1();
            vkey2 = ActivateHelper.makeVKey2();
            CountDownLatch countDownLatch = new CountDownLatch(MSG_HANDLE_SIM_STATE_CHANGE);
            SmsBroadcastReceiver receiver = new SmsBroadcastReceiver();
            receiver.setCountDownLatch(countDownLatch);
            receiver.reset();
            Intent intent = new Intent(ACTION_INTERNAL_ACTIVATE_SMS_SENT);
            intent.setPackage(getPackageName());
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 1073741824);
            registerReceiver(receiver, filter);
            apr.trackEvent(AnalyticsHelper.ACTIVATE_SEND_SMS_START, gatewayNumber);
            try {
                this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_prepare_sending_sms, TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
                this.mPhoneServiceStateListener[simIndex].waitForService();
                String smsBody = ActivateHelper.sendActivateSms(simIndex, pi, vkey1, vkey2, gatewayNumber);
                apr.mDiagnosisInfo.setHashedContent(CloudCoder.hashDeviceInfo(smsBody));
                this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_sending_sms, TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
                Log.i(TAG, "sendActivationSms: activate sms sent, waiting for radio response");
                countDownLatch.await();
                int smsResult = receiver.getResult();
                if (smsResult == MSG_ARG_UNUSED) {
                    smsSent = DEBUG;
                    break loop0;
                }
                Log.w(TAG, "sendActivationSms: failed to send activate sms, result:" + smsResult + ", count: " + tryCount);
                ActivateHelper.nextSmsGateway(this, simOperatorPattern);
                apr.trackEvent(AnalyticsHelper.ACTIVATE_SEND_SMS_FAIL, gatewayNumber);
            } finally {
                unregisterReceiver(receiver);
            }
        }
        if (smsSent) {
            synchronized (info) {
                info.vkey1 = vkey1;
                info.vkey2 = vkey2;
            }
            this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_sms_sent, TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
            Log.i(TAG, "sendActivationSms: activate sms for sim " + simIndex + " is sent");
            return 0;
        }
        Log.w(TAG, "sendActivationSms: failed to send sms for sim " + simIndex + " after retry");
        this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_sending_sms_fail, DEBUG, ActivateHelper.getActivatePopupPendingIntent(this, simIndex, MSG_START_VERIFY_SIM_TOKEN_TASK, null));
        apr.setDiagnosisInfoCategory(Category.FAIL_SEND_SMS);
        return MSG_HANDLE_SIM_STATE_CHANGE;
    }

    protected boolean verifyPhone(ActivateInfo info, int simIndex, String simOperatorPattern, String gatewayNumber, ActivateProgressReporter apr) throws InterruptedException, VKeyExpiredException, NoPhoneInfoException, CloudServiceFailureException {
        Log.v(TAG, "verifyPhone: simIndex=" + simIndex + " simOperatorPattern=" + simOperatorPattern);
        if (simOperatorPattern != null) {
            try {
                apr.trackEvent(AnalyticsHelper.ACTIVATE_WAIT_FOR_SERVER_INFO_START);
            } catch (CloudServiceFailureException e) {
                this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_acquire_phone_number_server_error, DEBUG, ActivateHelper.getActivatePopupPendingIntent(this, simIndex, 7, null));
                throw e;
            } catch (VKeyExpiredException e2) {
                synchronized (info) {
                }
                info.vkey1 = null;
                info.vkey2 = null;
                throw e2;
            } catch (NoPhoneInfoException e3) {
                apr.trackEvent(AnalyticsHelper.ACTIVATE_WAIT_FOR_SERVER_INFO_FAIL);
                this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_acquire_phone_number_server_no_info, DEBUG, ActivateHelper.getActivatePopupPendingIntent(this, simIndex, 4, null));
                ActivateHelper.nextSmsGateway(this, simOperatorPattern);
                synchronized (info) {
                }
                info.vkey1 = null;
                info.vkey2 = null;
                throw e3;
            }
        }
        this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_acquire_phone_number, TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT, null);
        String phone = ActivateHelper.verifyPhone(simIndex, ActivateHelper.MICLOUD_SID, info.traceId, info.hashedDeviceId, info.hashedSimId, info.pseudoSimId, info.vkey1, info.vkey2, simOperatorPattern, gatewayNumber, apr.mDiagnosisInfo, info.host);
        if (phone == null) {
            this.mApc.reportActivateStatus(simIndex, R.string.activate_status_desc_acquire_phone_number_server_no_info, DEBUG, ActivateHelper.getActivatePopupPendingIntent(this, simIndex, 4, null));
            return TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
        }
        synchronized (info) {
            info.phone = phone;
        }
        return DEBUG;
    }

    private static String mask(String phone) {
        return ActivateHelper.mask(phone);
    }

    private int getActivateFeatureIndex() {
        if (this.mSysInterface.isMmsSyncEnabled()) {
            return MSG_START_ACTIVATE_TASK;
        }
        if (this.mSysInterface.isCalllogSyncEnabled()) {
            return MSG_HANDLE_SIM_STATE_CHANGE;
        }
        if (this.mSysInterface.isCloudMessagingEnabled()) {
            return 0;
        }
        return MSG_ARG_UNUSED;
    }

    protected void notifyResult(EVENT event, int result) {
        notifyResult(event, result, null);
    }

    protected void notifyResult(EVENT event, int errorCode, Bundle data) {
        Iterator<RemoteObj> iterator;
        RemoteObj ro;
        if (errorCode == 0) {
            synchronized (this.mSessions) {
                iterator = this.mSessions.iterator();
                while (iterator.hasNext()) {
                    ro = (RemoteObj) iterator.next();
                    if (ro.event == event) {
                        try {
                            ro.response.onResult(data);
                            ro.response.asBinder().unlinkToDeath(ro.deadRecipient, 0);
                        } catch (RemoteException e) {
                        }
                        iterator.remove();
                    }
                }
            }
            return;
        }
        synchronized (this.mSessions) {
            iterator = this.mSessions.iterator();
            while (iterator.hasNext()) {
                ro = (RemoteObj) iterator.next();
                if (ro.event == event) {
                    try {
                        ro.response.onError(errorCode, "error code " + errorCode);
                        ro.response.asBinder().unlinkToDeath(ro.deadRecipient, 0);
                    } catch (RemoteException e2) {
                    }
                    iterator.remove();
                }
            }
        }
    }

    private void addRemoteObj(EVENT event, IActivateServiceResponse response) throws RemoteException {
        DeadRecipientImpl deadRecipient = new DeadRecipientImpl();
        response.asBinder().linkToDeath(deadRecipient, 0);
        RemoteObj ro = new RemoteObj(event, response, deadRecipient);
        deadRecipient.setObj(ro);
        this.mSessions.add(ro);
    }

    private void notifyActivateStatusChanged(int simIndex, int activateStatus) {
        Log.v(TAG, "Broadcasting ACTION_ACTIVATE_STATUS_CHANGED, simIndex=" + simIndex + " status=" + activateStatus);
        Intent intent = new Intent("com.xiaomi.action.ACTIVATE_STATUS_CHANGED");
        intent.putExtra("extra_sim_index", simIndex);
        intent.putExtra("extra_sim_inserted", activateStatus);
        sendBroadcast(intent);
    }

    private void notifySimStateChanged(int simIndex, boolean inserted) {
        Log.v(TAG, "Broadcasting ACTION_MICLOUD_SIM_STATE_CHANGED, simIndex=" + simIndex + " inserted=" + inserted);
        sendBroadcast(new Intent("com.xiaomi.action.MICLOUD_SIM_STATE_CHANGED").putExtra("extra_sim_index", simIndex).putExtra("extra_sim_inserted", inserted));
    }

    private static boolean isTaskDone(AsyncTask t) {
        return (t == null || Status.FINISHED.equals(t.getStatus())) ? DEBUG : TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
    }

    private Bundle makeActivateInfoBundle(ActivateInfo info, int simIndex) {
        Bundle bundle = new Bundle();
        bundle.putInt("sim_index", simIndex);
        if (info == null) {
            bundle.putBoolean("sim_inserted", TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT);
        } else {
            synchronized (info) {
                bundle.putBoolean("sim_inserted", DEBUG);
                bundle.putString("activate_hased_device_id", info.hashedDeviceId);
                bundle.putString("activate_hashed_sim_id", info.hashedSimId);
                bundle.putString("activate_phone", info.phone);
                bundle.putString("activate_sim_user_id", info.simUserId);
                bundle.putString("activate_sim_pass_token", info.simPassToken);
                bundle.putString("activate_xiaomi_user_id", info.xiaomiUserId);
                bundle.putString("activate_xiaomi_pass_token", info.xiaomiPassToken);
                bundle.putString("activate_host", info.host);
                bundle.putInt("activate_status", getActivateStatus(info, simIndex));
            }
        }
        return bundle;
    }

    private int getActivateStatus(ActivateInfo info, int simIndex) {
        if (info.isActivated()) {
            return MSG_START_VERIFY_SIM_TOKEN_TASK;
        }
        if (this.mSimTaskSets[simIndex].activationTask == null || (this.mSimTaskSets[simIndex].activationTask instanceof AutoActivatePhoneTask) || this.mSimTaskSets[simIndex].activationTask.getStatus() != Status.RUNNING) {
            return MSG_HANDLE_SIM_STATE_CHANGE;
        }
        return MSG_START_ACTIVATE_TASK;
    }

    private boolean isDeviceProvisioned() {
        if (Secure.getInt(getContentResolver(), "device_provisioned", 0) != 0) {
            return DEBUG;
        }
        return TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
    }

    private void popupAutoActivateFailIfNeed() {
        if (isDeviceProvisioned()) {
            boolean shouldPopup = TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT;
            for (int i = 0; i < this.mSimCount; i += MSG_HANDLE_SIM_STATE_CHANGE) {
                ActivateInfo info = this.mActivateInfos[i];
                if (info != null) {
                    if (info.autoActivateState == AutoActivateState.RUNNING) {
                        Log.v(TAG, "popupAutoActivateFailIfNeed: SIM " + i + " is auto-activating. Bail.");
                        return;
                    } else if (info.autoActivateState == AutoActivateState.FAILED) {
                        shouldPopup = DEBUG;
                        Log.v(TAG, "popupAutoActivateFailIfNeed: SIM " + i + " is failed.");
                    }
                }
            }
            if (shouldPopup) {
                int featureIndex = getActivateFeatureIndex();
                if (featureIndex == MSG_ARG_UNUSED) {
                    Log.v(TAG, "popupAutoActivateFailIfNeed: No feature needs activation. Bail.");
                    return;
                } else if (PrefUtils.getBoolean(this, PREF_AUTO_ACTIVATE_FAIL_POP_UP_SHOWN, TEST_FLAG_OVERRIDE_EXISTING_ACCOUNT)) {
                    Log.v(TAG, "popupAutoActivateFailIfNeed: Pop up activation.");
                    ActivateHelper.notifyUnactivatedSimCards(this);
                    return;
                } else {
                    Log.v(TAG, "popupAutoActivateFailIfNeed: Nofity activation.");
                    PrefUtils.saveBoolean(this, PREF_AUTO_ACTIVATE_FAIL_POP_UP_SHOWN, DEBUG);
                    Intent intent = new Intent(this, ActivatePopupActivity.class);
                    intent.putExtra("extra_sim_index", MSG_ARG_UNUSED);
                    intent.putExtra("extra_activate_feature_index", featureIndex);
                    intent.putExtra("extra_activate_reason", MSG_HANDLE_SIM_STATE_CHANGE);
                    intent.putExtra("extra_activate_source", "Xmsf_AutoActivateFail");
                    intent.setFlags(268435456);
                    startActivity(intent);
                    return;
                }
            }
            Log.v(TAG, "popupAutoActivateFailIfNeed: No failed SIM card. Bail.");
        }
    }

    private String loadActivateHostPref(String hashSimId) {
        String str = PREF_ACTIVATE_HOST;
        Object[] objArr = new Object[MSG_HANDLE_SIM_STATE_CHANGE];
        objArr[0] = hashSimId;
        return PrefUtils.getString(this, String.format(str, objArr));
    }

    private void saveActivateHostPref(String hashSimId, String host) {
        String str = PREF_ACTIVATE_HOST;
        Object[] objArr = new Object[MSG_HANDLE_SIM_STATE_CHANGE];
        objArr[0] = hashSimId;
        PrefUtils.saveString(this, String.format(str, objArr), host);
    }

    private String ensurePseudoSimId(String hashSimId) {
        String str = PREF_PSEUDO_SIM_ID;
        Object[] objArr = new Object[MSG_HANDLE_SIM_STATE_CHANGE];
        objArr[0] = hashSimId;
        String key = String.format(str, objArr);
        String pseudoSimId = PrefUtils.getString(this, key);
        if (pseudoSimId != null) {
            return pseudoSimId;
        }
        pseudoSimId = CloudCoder.generatePseudoDeviceId();
        PrefUtils.saveString(this, key, pseudoSimId);
        return pseudoSimId;
    }

    private static boolean hasTelephonyFeature(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }
}
