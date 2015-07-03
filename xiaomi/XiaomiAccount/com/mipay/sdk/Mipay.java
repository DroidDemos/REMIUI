package com.mipay.sdk;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.mipay.sdk.IMipayResponse.Stub;
import com.mipay.sdk.exception.AuthenticationFailureException;
import com.mipay.sdk.exception.MipayException;
import com.mipay.sdk.exception.OperationCancelledException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class Mipay {
    private static final String ACTION_MIPAY_COUNTER = "com.xiaomi.action.MIPAY_PAY_ORDER";
    private static final String ACTION_MIPAY_COUNTER_SERVICE = "com.xiaomi.action.MIPAY";
    private static final String ACTION_MIPAY_WALLET = "com.xiaomi.action.VIEW_MIPAY_WALLET";
    public static final int CAPABILITY = 0;
    public static final int ERROR_CODE_ACCOUNT_ERROR = 4;
    public static final int ERROR_CODE_CALL_TOO_FAST = 3;
    public static final int ERROR_CODE_CANCELED = 2;
    public static final int ERROR_CODE_DUPLICATE_PURCHASE = 9;
    public static final int ERROR_CODE_EXCEPTION = 1;
    public static final int ERROR_CODE_INVALID_CALLER = 8;
    public static final int ERROR_CODE_INVALID_DATA = 7;
    public static final int ERROR_CODE_NETWORK_ERROR = 5;
    public static final int ERROR_CODE_OK = 0;
    public static final int ERROR_CODE_SERVER_ERROR = 6;
    public static final String KEY_CODE = "code";
    public static final String KEY_EXTRA = "extra";
    public static final String KEY_FULL_RESULT = "fullResult";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_ORDER = "order";
    public static final String KEY_RESULT = "result";
    private static final String PACKAGE_MIPAY_COUNTER = "com.mipay.counter";
    private static final String PACKAGE_MIPAY_WALLET = "com.mipay.wallet";
    public static final int REQUEST_MIPAY = 20140424;
    private static final String TAG = "Mipay";
    public static final String XIAOMI_ACCOUNT_TYPE = "com.xiaomi";
    private final Context mContext;
    private final Handler mMainHandler;
    private boolean mUseWallet;

    private interface MipayFuture<V> {
        boolean cancel(boolean z);

        V getResult() throws IOException, OperationCancelledException, AuthenticationFailureException, MipayException;

        V getResult(long j, TimeUnit timeUnit) throws IOException, OperationCancelledException, AuthenticationFailureException, MipayException;

        boolean isCancelled();

        boolean isDone();
    }

    private abstract class PmsTask extends FutureTask<Bundle> implements MipayFuture<Bundle>, ServiceConnection {
        private final int HOST_MONITOR_HEART_INTERNAL;
        private Activity mActivity;
        private MipayCallback<Bundle> mCallback;
        private Runnable mHostActivityMonitor;
        private boolean mIsBound;
        private IMipayResponse mResponse;
        private IMipayService mService;

        class AnonymousClass2 implements Callable<Bundle> {
            final /* synthetic */ Mipay val$this$0;

            AnonymousClass2(Mipay mipay) {
                this.val$this$0 = mipay;
            }

            public Bundle call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        }

        class MipayResponseImpl extends Stub {
            MipayResponseImpl() {
            }

            public void onResult(Bundle bundle) throws RemoteException {
                Intent intent = (Intent) bundle.getParcelable(Mipay.KEY_INTENT);
                if (intent == null) {
                    PmsTask.this.set(bundle);
                } else if (PmsTask.this.mActivity != null) {
                    PmsTask.this.mActivity.startActivity(intent);
                } else {
                    PmsTask.this.setException(new MipayException(Mipay.ERROR_CODE_INVALID_DATA, "activity cannot be null"));
                }
            }

            public void onError(int code, String message, Bundle bundle) throws RemoteException {
                if (code == Mipay.ERROR_CODE_CANCELED) {
                    PmsTask.this.cancel(true);
                    PmsTask.this.unBind();
                    return;
                }
                PmsTask.this.setException(PmsTask.this.convertErrorCodeToException(code, message, bundle));
            }
        }

        protected abstract void doWork() throws RemoteException;

        protected PmsTask(Mipay mipay, Activity activity) {
            this(activity, null);
        }

        protected PmsTask(Activity activity, MipayCallback<Bundle> callback) {
            super(new AnonymousClass2(Mipay.this));
            this.mIsBound = false;
            this.HOST_MONITOR_HEART_INTERNAL = 5000;
            this.mHostActivityMonitor = new Runnable() {
                public void run() {
                    Activity host = PmsTask.this.mActivity;
                    if (!PmsTask.this.isDone() && host != null) {
                        if (host.isFinishing()) {
                            PmsTask.this.setException(new OperationCancelledException("Operation has been cancelled because host activity has finished."));
                        } else {
                            Mipay.this.mMainHandler.postDelayed(this, 5000);
                        }
                    }
                }
            };
            this.mActivity = activity;
            this.mCallback = callback;
            this.mResponse = new MipayResponseImpl();
        }

        protected IMipayResponse getResponse() {
            return this.mResponse;
        }

        protected IMipayService getService() {
            return this.mService;
        }

        public final MipayFuture<Bundle> start() {
            bind();
            return this;
        }

        protected void bind() {
            if (bindToMipayService()) {
                this.mIsBound = true;
                Log.d(Mipay.TAG, "service bound");
                return;
            }
            setException(new MipayException(Mipay.ERROR_CODE_EXCEPTION, "bind to service failed"));
        }

        protected void unBind() {
            if (this.mIsBound) {
                Mipay.this.mContext.unbindService(this);
                this.mIsBound = false;
                Log.d(Mipay.TAG, "service unbinded");
            }
        }

        protected boolean bindToMipayService() {
            Intent intent = new Intent(Mipay.ACTION_MIPAY_COUNTER_SERVICE);
            if (Mipay.this.mUseWallet) {
                intent.setPackage(Mipay.PACKAGE_MIPAY_WALLET);
            } else {
                intent.setPackage(Mipay.PACKAGE_MIPAY_COUNTER);
            }
            return Mipay.this.mContext.bindService(intent, this, Mipay.ERROR_CODE_EXCEPTION);
        }

        private Bundle internalGetResult(Long timeout, TimeUnit unit) throws IOException, OperationCancelledException, AuthenticationFailureException, MipayException {
            Bundle bundle;
            if (!isDone()) {
                ensureNotOnMainThread();
            }
            if (timeout == null) {
                try {
                    bundle = (Bundle) get();
                    cancel(true);
                } catch (CancellationException e) {
                    throw new OperationCancelledException("cancelled by user");
                } catch (TimeoutException e2) {
                    cancel(true);
                    throw new OperationCancelledException("cancelled by exception");
                } catch (InterruptedException e3) {
                    cancel(true);
                    throw new OperationCancelledException("cancelled by exception");
                } catch (ExecutionException e4) {
                    Throwable cause = e4.getCause();
                    if (cause instanceof IOException) {
                        throw ((IOException) cause);
                    } else if (cause instanceof MipayException) {
                        throw ((MipayException) cause);
                    } else if (cause instanceof AuthenticationFailureException) {
                        throw ((AuthenticationFailureException) cause);
                    } else if (cause instanceof OperationCancelledException) {
                        throw ((OperationCancelledException) cause);
                    } else if (cause instanceof RuntimeException) {
                        throw ((RuntimeException) cause);
                    } else if (cause instanceof Error) {
                        throw ((Error) cause);
                    } else {
                        throw new IllegalStateException(cause);
                    }
                } catch (Throwable th) {
                    cancel(true);
                }
            } else {
                bundle = (Bundle) get(timeout.longValue(), unit);
                cancel(true);
            }
            return bundle;
        }

        protected void set(Bundle bundle) {
            super.set(bundle);
            unBind();
        }

        protected void setException(Throwable t) {
            super.setException(t);
            unBind();
        }

        public Bundle getResult(long timeout, TimeUnit unit) throws IOException, OperationCancelledException, AuthenticationFailureException, MipayException {
            return internalGetResult(Long.valueOf(timeout), unit);
        }

        public Bundle getResult() throws IOException, OperationCancelledException, AuthenticationFailureException, MipayException {
            return internalGetResult(null, null);
        }

        protected void done() {
            if (this.mCallback != null) {
                Mipay.this.mMainHandler.post(new Runnable() {
                    public void run() {
                        PmsTask.this.mCallback.run(PmsTask.this);
                        PmsTask.this.mCallback = null;
                    }
                });
            }
            Mipay.this.mMainHandler.removeCallbacks(this.mHostActivityMonitor);
            this.mActivity = null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(Mipay.TAG, "service connected, component:" + name);
            this.mService = IMipayService.Stub.asInterface(service);
            try {
                doWork();
                Mipay.this.mMainHandler.postDelayed(this.mHostActivityMonitor, 5000);
            } catch (RemoteException e) {
                setException(e);
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            Log.e(Mipay.TAG, "service disconnected");
            if (!isDone()) {
                Log.e(Mipay.TAG, "task is not completed");
                setException(new MipayException(Mipay.ERROR_CODE_EXCEPTION, "active service exits unexpectedly"));
            }
            this.mService = null;
        }

        private Exception convertErrorCodeToException(int code, String message, Bundle bundle) {
            if (code == Mipay.ERROR_CODE_NETWORK_ERROR) {
                return new IOException(message);
            }
            if (code == Mipay.ERROR_CODE_ACCOUNT_ERROR) {
                return new AuthenticationFailureException(message);
            }
            if (TextUtils.isEmpty(message)) {
                message = "Unknown failure";
            }
            return new MipayException(code, message, bundle);
        }

        private void ensureNotOnMainThread() {
            Looper looper = Looper.myLooper();
            if (looper != null && looper == Mipay.this.mContext.getMainLooper()) {
                IllegalStateException exception = new IllegalStateException("calling this from your main thread can lead to deadlock");
                Log.e(Mipay.TAG, "calling this from your main thread can lead to deadlock and/or ANRs", exception);
                throw exception;
            }
        }
    }

    private interface MipayCallback<V> {
        void run(MipayFuture<V> mipayFuture);
    }

    private class MipayCallbackImpl implements MipayCallback<Bundle> {
        private Handler mCallback;
        private int mWhat;

        public MipayCallbackImpl(int what, Handler callback) {
            this.mWhat = what;
            this.mCallback = callback;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run(com.mipay.sdk.Mipay.MipayFuture<android.os.Bundle> r10) {
            /*
            r9 = this;
            r8 = 0;
            r3 = r9.mCallback;
            if (r3 != 0) goto L_0x0006;
        L_0x0005:
            return;
        L_0x0006:
            r0 = r10.getResult();	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r0 = (android.os.Bundle) r0;	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            if (r0 == 0) goto L_0x0026;
        L_0x000e:
            r3 = "result";
            r2 = r0.getString(r3);	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r3 = r9.mCallback;	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r4 = com.mipay.sdk.Mipay.this;	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r5 = r9.mWhat;	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r6 = 0;
            r7 = 0;
            r4 = r4.makeMessage(r5, r6, r7, r2);	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r3.sendMessage(r4);	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
        L_0x0023:
            r9.mCallback = r8;
            goto L_0x0005;
        L_0x0026:
            r3 = r9.mCallback;	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r4 = com.mipay.sdk.Mipay.this;	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r5 = r9.mWhat;	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r6 = 1;
            r7 = "error";
            r4 = r4.makeMessage(r5, r6, r7);	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            r3.sendMessage(r4);	 Catch:{ IOException -> 0x0037, OperationCancelledException -> 0x004d, AuthenticationFailureException -> 0x0063, MipayException -> 0x0079 }
            goto L_0x0023;
        L_0x0037:
            r1 = move-exception;
            r3 = r9.mCallback;	 Catch:{ all -> 0x0093 }
            r4 = com.mipay.sdk.Mipay.this;	 Catch:{ all -> 0x0093 }
            r5 = r9.mWhat;	 Catch:{ all -> 0x0093 }
            r6 = 5;
            r7 = r1.getMessage();	 Catch:{ all -> 0x0093 }
            r4 = r4.makeMessage(r5, r6, r7);	 Catch:{ all -> 0x0093 }
            r3.sendMessage(r4);	 Catch:{ all -> 0x0093 }
            r9.mCallback = r8;
            goto L_0x0005;
        L_0x004d:
            r1 = move-exception;
            r3 = r9.mCallback;	 Catch:{ all -> 0x0093 }
            r4 = com.mipay.sdk.Mipay.this;	 Catch:{ all -> 0x0093 }
            r5 = r9.mWhat;	 Catch:{ all -> 0x0093 }
            r6 = 2;
            r7 = r1.getMessage();	 Catch:{ all -> 0x0093 }
            r4 = r4.makeMessage(r5, r6, r7);	 Catch:{ all -> 0x0093 }
            r3.sendMessage(r4);	 Catch:{ all -> 0x0093 }
            r9.mCallback = r8;
            goto L_0x0005;
        L_0x0063:
            r1 = move-exception;
            r3 = r9.mCallback;	 Catch:{ all -> 0x0093 }
            r4 = com.mipay.sdk.Mipay.this;	 Catch:{ all -> 0x0093 }
            r5 = r9.mWhat;	 Catch:{ all -> 0x0093 }
            r6 = 4;
            r7 = r1.getMessage();	 Catch:{ all -> 0x0093 }
            r4 = r4.makeMessage(r5, r6, r7);	 Catch:{ all -> 0x0093 }
            r3.sendMessage(r4);	 Catch:{ all -> 0x0093 }
            r9.mCallback = r8;
            goto L_0x0005;
        L_0x0079:
            r1 = move-exception;
            r3 = r9.mCallback;	 Catch:{ all -> 0x0093 }
            r4 = com.mipay.sdk.Mipay.this;	 Catch:{ all -> 0x0093 }
            r5 = r9.mWhat;	 Catch:{ all -> 0x0093 }
            r6 = r1.getError();	 Catch:{ all -> 0x0093 }
            r7 = r1.getMessage();	 Catch:{ all -> 0x0093 }
            r4 = r4.makeMessage(r5, r6, r7);	 Catch:{ all -> 0x0093 }
            r3.sendMessage(r4);	 Catch:{ all -> 0x0093 }
            r9.mCallback = r8;
            goto L_0x0005;
        L_0x0093:
            r3 = move-exception;
            r9.mCallback = r8;
            throw r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mipay.sdk.Mipay.MipayCallbackImpl.run(com.mipay.sdk.Mipay$MipayFuture):void");
        }
    }

    private Mipay(Context context) {
        this.mContext = context.getApplicationContext();
        this.mMainHandler = new Handler(this.mContext.getMainLooper());
        this.mUseWallet = isMipayWalletInstalled(context);
    }

    public static Mipay get(Context context) {
        return new Mipay(context);
    }

    public static boolean isMipayInstalled(Context context) {
        if (isTablet()) {
            return false;
        }
        if (isMipayWalletInstalled(context) || isMipayCounterInstalled(context)) {
            return true;
        }
        return false;
    }

    private static boolean isTablet() {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return ((int) ((((float) Math.min(dm.widthPixels, dm.heightPixels)) / dm.density) + 0.5f)) >= 600;
    }

    private static boolean isMipayWalletInstalled(Context context) {
        try {
            context.getPackageManager().getPackageInfo(PACKAGE_MIPAY_WALLET, ERROR_CODE_OK);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private static boolean isMipayCounterInstalled(Context context) {
        try {
            context.getPackageManager().getPackageInfo(PACKAGE_MIPAY_COUNTER, ERROR_CODE_OK);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @Deprecated
    public void pay(Activity activity, String order, Bundle extra, int what, Handler callback) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(order)) {
            throw new InvalidParameterException("order cannot be empty");
        } else {
            internalPay(activity, order, extra, new MipayCallbackImpl(what, callback));
        }
    }

    public void pay(Activity activity, String order, Bundle extra) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(order)) {
            throw new InvalidParameterException("order cannot be empty");
        } else {
            Intent intent = new Intent(ACTION_MIPAY_COUNTER);
            intent.setPackage(PACKAGE_MIPAY_WALLET);
            intent.putExtra(KEY_ORDER, order);
            intent.putExtra(KEY_EXTRA, extra);
            activity.startActivityForResult(intent, REQUEST_MIPAY);
        }
    }

    public void pay(Fragment fragment, String order, Bundle extra) {
        if (fragment == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(order)) {
            throw new InvalidParameterException("order cannot be empty");
        } else {
            Intent intent = new Intent(ACTION_MIPAY_COUNTER);
            intent.setPackage(PACKAGE_MIPAY_WALLET);
            intent.putExtra(KEY_ORDER, order);
            intent.putExtra(KEY_EXTRA, extra);
            fragment.startActivityForResult(intent, REQUEST_MIPAY);
        }
    }

    public void showMipayCenter(Activity activity) {
        internalShowWallet(activity);
    }

    private Message makeMessage(int what, int code, String message) {
        return makeMessage(what, code, message, null);
    }

    private Message makeMessage(int what, int code, String message, String result) {
        JSONObject ret = new JSONObject();
        try {
            ret.put(KEY_CODE, code);
            if (!TextUtils.isEmpty(message)) {
                ret.put(KEY_MESSAGE, message);
            }
            if (!TextUtils.isEmpty(result)) {
                ret.put(KEY_RESULT, result);
            }
        } catch (JSONException e) {
        }
        Message msg = new Message();
        msg.what = what;
        msg.obj = ret.toString();
        return msg;
    }

    private MipayFuture<Bundle> internalPay(Activity activity, String order, Bundle extra, MipayCallback<Bundle> callback) {
        final Bundle bundle = extra;
        final String str = order;
        return new PmsTask(activity, callback) {
            protected void doWork() throws RemoteException {
                IMipayService service = getService();
                Bundle finalExtra = new Bundle();
                if (bundle != null) {
                    finalExtra.putAll(bundle);
                }
                service.pay(getResponse(), null, str, finalExtra);
            }
        }.start();
    }

    private void internalShowWallet(Activity activity) {
        if (this.mUseWallet) {
            Intent intent = new Intent(ACTION_MIPAY_WALLET);
            intent.setPackage(PACKAGE_MIPAY_WALLET);
            activity.startActivity(intent);
        }
    }
}
