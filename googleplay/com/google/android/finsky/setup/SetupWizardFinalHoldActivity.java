package com.google.android.finsky.setup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreBackgroundActionEvent;
import com.google.android.finsky.config.G;
import com.google.android.finsky.services.RestoreService;
import com.google.android.finsky.services.SetupHoldListener;
import com.google.android.finsky.services.VpaService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.image.FifeImageView;

public class SetupWizardFinalHoldActivity extends Activity implements SetupHoldListener {
    public static final String SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_LANDSCAPE_URL;
    public static final String SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_TABLET_URL;
    public static final String SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_URL;
    private static final long SETUP_WIZARD_RESTORE_FINAL_HOLD_LONG_MS;
    private static final long SETUP_WIZARD_RESTORE_FINAL_HOLD_SHORT_MS;
    private RestoringTitleAnimator mAnimator;
    private Handler mHandler;
    private boolean mRegisteredWithRestoreService;
    private boolean mRegisteredWithVpaService;
    private long mWatchdogExpirationRealtimeMs;
    private String mWatchdogLastPackage;
    private Runnable mWatchdogRunnable;

    private static class RestoringTitleAnimator {
        private static final int[] ELLIPSES;
        private Runnable mCallback;
        private TextView mEllipseView;
        private int mPosition;
        private boolean mRunning;

        static {
            ELLIPSES = new int[]{R.string.setup_wizard_ellipse_single, R.string.setup_wizard_ellipse_double, R.string.setup_wizard_ellipse_triple};
        }

        public RestoringTitleAnimator(TextView ellipseView) {
            this.mPosition = 0;
            this.mRunning = false;
            this.mCallback = new Runnable() {
                public void run() {
                    RestoringTitleAnimator.this.nextStep();
                    if (RestoringTitleAnimator.this.mRunning) {
                        RestoringTitleAnimator.this.mEllipseView.postDelayed(this, 500);
                    }
                }
            };
            this.mEllipseView = ellipseView;
        }

        private void nextStep() {
            this.mPosition = (this.mPosition + 1) % (ELLIPSES.length + 1);
            if (this.mPosition == 0) {
                this.mEllipseView.setText("");
            } else {
                this.mEllipseView.setText(ELLIPSES[this.mPosition - 1]);
            }
            this.mEllipseView.invalidate();
        }

        public void start() {
            this.mRunning = true;
            this.mEllipseView.removeCallbacks(this.mCallback);
            this.mEllipseView.postDelayed(this.mCallback, 500);
        }

        public void stop() {
            this.mRunning = false;
            this.mEllipseView.removeCallbacks(this.mCallback);
        }
    }

    public SetupWizardFinalHoldActivity() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mWatchdogRunnable = new Runnable() {
            public void run() {
                FinskyLog.w("Watchdog fired, skipping hold.", new Object[0]);
                SetupWizardFinalHoldActivity.this.setResultAndFinish(1);
                PlayStoreBackgroundActionEvent event = FinskyEventLog.obtainPlayStoreBackgroundActionEvent();
                event.type = 126;
                event.hasType = true;
                if (SetupWizardFinalHoldActivity.this.mWatchdogLastPackage != null) {
                    event.document = SetupWizardFinalHoldActivity.this.mWatchdogLastPackage;
                    event.hasDocument = true;
                }
                FinskyApp.get().getEventLogger().logBackgroundEvent(event);
            }
        };
    }

    static {
        SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_URL = (String) G.setupWizardRestoreFinalHoldHeaderUrl.get();
        SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_LANDSCAPE_URL = (String) G.setupWizardRestoreFinalHoldHeaderLandscapeUrl.get();
        SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_TABLET_URL = (String) G.setupWizardRestoreFinalHoldHeaderTabletUrl.get();
        SETUP_WIZARD_RESTORE_FINAL_HOLD_SHORT_MS = ((Long) G.setupWizardRestoreFinalHoldShortMs.get()).longValue();
        SETUP_WIZARD_RESTORE_FINAL_HOLD_LONG_MS = ((Long) G.setupWizardRestoreFinalHoldLongMs.get()).longValue();
    }

    public static Intent createIntent() {
        return new Intent(FinskyApp.get(), SetupWizardFinalHoldActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        String imageUrl;
        super.onCreate(savedInstanceState);
        setContentView((ViewGroup) LayoutInflater.from(this).inflate(R.layout.setup_wizard_final_hold, null));
        final View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 5634);
                return true;
            }
        });
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 5634);
        FifeImageView headerImageView = (FifeImageView) findViewById(R.id.setup_wizard_restore_header);
        this.mAnimator = new RestoringTitleAnimator((TextView) findViewById(R.id.setup_wizard_title_ellipse));
        headerImageView.setScaleType(ScaleType.CENTER_CROP);
        if (getResources().getBoolean(R.bool.setup_wizard_use_tablet_graphic)) {
            imageUrl = SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_TABLET_URL;
        } else if (getResources().getConfiguration().orientation == 2) {
            imageUrl = SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_LANDSCAPE_URL;
        } else {
            imageUrl = SETUP_WIZARD_RESTORE_FINAL_HOLD_HEADER_URL;
        }
        headerImageView.setImage(imageUrl, true, FinskyApp.get().getBitmapLoader());
        if (savedInstanceState == null) {
            setWatchdog(SETUP_WIZARD_RESTORE_FINAL_HOLD_SHORT_MS, null);
            return;
        }
        this.mWatchdogExpirationRealtimeMs = savedInstanceState.getLong("watchdog_expiration_ms");
        this.mWatchdogLastPackage = savedInstanceState.getString("watchdog_package");
        if (!finishIfTimeout()) {
            setWatchdog(this.mWatchdogExpirationRealtimeMs - SystemClock.elapsedRealtime(), this.mWatchdogLastPackage);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacks(this.mWatchdogRunnable);
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("watchdog_expiration_ms", this.mWatchdogExpirationRealtimeMs);
        savedInstanceState.putString("watchdog_package", this.mWatchdogLastPackage);
    }

    protected void onResume() {
        super.onResume();
        finishIfTimeout();
        this.mAnimator.start();
        if (!registerListener()) {
            setResultAndFinish(-1);
        }
    }

    protected void onPause() {
        super.onPause();
        this.mAnimator.stop();
        unregisterListeners();
    }

    private void setResultAndFinish(int result) {
        this.mHandler.removeCallbacks(this.mWatchdogRunnable);
        setResult(result);
        FinskyLog.d("Calling finish from final hold.", new Object[0]);
        finish();
    }

    private boolean registerListener() {
        if (RestoreService.registerListener(this)) {
            this.mRegisteredWithRestoreService = true;
            return true;
        } else if (!VpaService.registerListener(this)) {
            return false;
        } else {
            this.mRegisteredWithVpaService = true;
            return true;
        }
    }

    private void unregisterListeners() {
        if (this.mRegisteredWithVpaService) {
            VpaService.registerListener(null);
            this.mRegisteredWithVpaService = false;
        }
        if (this.mRegisteredWithRestoreService) {
            RestoreService.registerListener(null);
            this.mRegisteredWithRestoreService = false;
        }
    }

    public void onStatusChange(int eventCode, String packageName, String title, boolean cancelable, String logString) {
        String str = "Final hold status change: listener=%s code=%d package=%s cancelable=%b (%s)";
        Object[] objArr = new Object[5];
        objArr[0] = this.mRegisteredWithVpaService ? "VPA" : "Restore";
        objArr[1] = Integer.valueOf(eventCode);
        objArr[2] = packageName;
        objArr[3] = Boolean.valueOf(cancelable);
        objArr[4] = logString;
        FinskyLog.d(str, objArr);
        switch (eventCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mHandler.post(new Runnable() {
                    public void run() {
                        SetupWizardFinalHoldActivity.this.unregisterListeners();
                        if (!SetupWizardFinalHoldActivity.this.registerListener()) {
                            SetupWizardFinalHoldActivity.this.setResultAndFinish(-1);
                        }
                    }
                });
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                setWatchdog(SETUP_WIZARD_RESTORE_FINAL_HOLD_SHORT_MS, null);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (this.mWatchdogLastPackage == null || !this.mWatchdogLastPackage.equals(packageName)) {
                    setWatchdog(SETUP_WIZARD_RESTORE_FINAL_HOLD_LONG_MS, packageName);
                    return;
                }
                return;
            default:
                FinskyLog.wtf("Unknown event code - finishing early", new Object[0]);
                setResultAndFinish(-1);
                return;
        }
    }

    private void setWatchdog(long delayMs, String packageName) {
        if (SETUP_WIZARD_RESTORE_FINAL_HOLD_SHORT_MS == -1) {
            FinskyLog.d("Watchdog disabled", new Object[0]);
            return;
        }
        FinskyLog.d("Set watchdog to %d sec (package %s)", Long.valueOf(delayMs / 1000), packageName);
        this.mWatchdogExpirationRealtimeMs = SystemClock.elapsedRealtime() + delayMs;
        this.mWatchdogLastPackage = packageName;
        this.mHandler.removeCallbacks(this.mWatchdogRunnable);
        this.mHandler.postDelayed(this.mWatchdogRunnable, delayMs);
    }

    private boolean finishIfTimeout() {
        if (SETUP_WIZARD_RESTORE_FINAL_HOLD_SHORT_MS == -1 || SystemClock.elapsedRealtime() <= this.mWatchdogExpirationRealtimeMs) {
            return false;
        }
        this.mWatchdogRunnable.run();
        return true;
    }
}
