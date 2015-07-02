package com.google.android.finsky.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.android.vending.R;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.SetupWizardIllustration;
import com.google.android.finsky.setup.SetupWizardNavBar;

public class SetupWizardUtils {
    public static final String ILLUSTRATION_URL_PAYMENT;
    public static final String ILLUSTRATION_URL_PAYMENT_WIDE;
    public static final String ILLUSTRATION_URL_RESTORE;
    public static final String ILLUSTRATION_URL_RESTORE_WIDE;

    private static class FullscreenAdjustResizeWorkaround {
        private View mActivityView;
        private LayoutParams mFrameLayoutParams;
        private int mPreviousHeight;
        private int mStatusBarHeight;

        public static void forceSizeChanges(Activity activity) {
            FullscreenAdjustResizeWorkaround fullscreenAdjustResizeWorkaround = new FullscreenAdjustResizeWorkaround(activity);
        }

        private FullscreenAdjustResizeWorkaround(Activity activity) {
            this.mStatusBarHeight = 0;
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                this.mStatusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
            }
            this.mActivityView = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
            this.mActivityView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    FullscreenAdjustResizeWorkaround.this.resizeOrRestoreContentSize();
                }
            });
            this.mFrameLayoutParams = (LayoutParams) this.mActivityView.getLayoutParams();
        }

        private void resizeOrRestoreContentSize() {
            int visibleViewHeight = computeVisibleViewHeight();
            if (visibleViewHeight != this.mPreviousHeight) {
                int fullViewHeight = this.mActivityView.getRootView().getHeight();
                int heightDelta = fullViewHeight - visibleViewHeight;
                if (heightDelta > fullViewHeight / 4) {
                    this.mFrameLayoutParams.height = fullViewHeight - heightDelta;
                } else {
                    this.mFrameLayoutParams.height = fullViewHeight;
                }
                this.mActivityView.requestLayout();
                this.mPreviousHeight = visibleViewHeight;
            }
        }

        private int computeVisibleViewHeight() {
            Rect r = new Rect();
            this.mActivityView.getWindowVisibleDisplayFrame(r);
            return (r.bottom - r.top) + this.mStatusBarHeight;
        }
    }

    public static class SetupWizardParams implements Parcelable {
        public static final Creator<SetupWizardParams> CREATOR;
        private final String mCardHolderName;
        private final boolean mHasSetupCompleteScreen;
        private final boolean mIsLightTheme;
        private final boolean mOnInitialSetup;
        private final boolean mUseImmersiveMode;

        public SetupWizardParams(Intent intent) {
            this.mCardHolderName = intent.getStringExtra("cardholder_name");
            this.mOnInitialSetup = intent.getBooleanExtra("on_initial_setup", true);
            this.mHasSetupCompleteScreen = intent.getBooleanExtra("on_initial_setup", false);
            this.mUseImmersiveMode = intent.getBooleanExtra("useImmersiveMode", false);
            this.mIsLightTheme = shouldUseLightTheme(intent);
        }

        public SetupWizardParams(Parcel in) {
            boolean z;
            boolean z2 = true;
            this.mCardHolderName = in.readString();
            if (in.readByte() == (byte) 1) {
                z = true;
            } else {
                z = false;
            }
            this.mOnInitialSetup = z;
            if (in.readByte() == (byte) 1) {
                z = true;
            } else {
                z = false;
            }
            this.mHasSetupCompleteScreen = z;
            if (in.readByte() == (byte) 1) {
                z = true;
            } else {
                z = false;
            }
            this.mUseImmersiveMode = z;
            if (in.readByte() != (byte) 1) {
                z2 = false;
            }
            this.mIsLightTheme = z2;
        }

        public void writeToParcel(Parcel out, int flags) {
            int i;
            int i2 = 1;
            out.writeString(this.mCardHolderName);
            if (this.mOnInitialSetup) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeByte((byte) i);
            if (this.mHasSetupCompleteScreen) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeByte((byte) i);
            if (this.mUseImmersiveMode) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeByte((byte) i);
            if (!this.mIsLightTheme) {
                i2 = 0;
            }
            out.writeByte((byte) i2);
        }

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<SetupWizardParams>() {
                public SetupWizardParams createFromParcel(Parcel parcel) {
                    return new SetupWizardParams(parcel);
                }

                public SetupWizardParams[] newArray(int size) {
                    return new SetupWizardParams[size];
                }
            };
        }

        public String getCardholderName() {
            return this.mCardHolderName;
        }

        public boolean isInitialSetup() {
            return this.mOnInitialSetup;
        }

        public boolean hasSetupCompleteScreen() {
            return this.mHasSetupCompleteScreen;
        }

        public boolean useImmersiveMode() {
            return this.mUseImmersiveMode;
        }

        public boolean isLightTheme() {
            return this.mIsLightTheme;
        }

        private static boolean shouldUseLightTheme(Intent intent) {
            if (!SetupWizardUtils.shouldUseMaterialTheme()) {
                return false;
            }
            String theme = intent.getStringExtra("theme");
            if (TextUtils.isEmpty(theme) || theme.equalsIgnoreCase("material_light")) {
                return true;
            }
            return false;
        }
    }

    static {
        ILLUSTRATION_URL_PAYMENT = (String) G.setupWizardPaymentHeaderGraphicUrl.get();
        ILLUSTRATION_URL_PAYMENT_WIDE = (String) G.setupWizardPaymentHeaderWideGraphicUrl.get();
        ILLUSTRATION_URL_RESTORE = (String) G.setupWizardRestoreHeaderGraphicUrl.get();
        ILLUSTRATION_URL_RESTORE_WIDE = (String) G.setupWizardRestoreWideHeaderGraphicUrl.get();
    }

    public static boolean shouldUseMaterialTheme() {
        return VERSION.SDK_INT >= 21;
    }

    public static SetupWizardNavBar getNavBarIfPossible(Activity activity) {
        if (shouldUseMaterialTheme()) {
            return (SetupWizardNavBar) activity.getFragmentManager().findFragmentById(R.id.navigation_bar);
        }
        return null;
    }

    public static void animateSliding(Activity activity, boolean slideBackwards) {
        if (!shouldUseMaterialTheme()) {
            return;
        }
        if (slideBackwards) {
            activity.overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);
        } else {
            activity.overridePendingTransition(R.anim.slide_next_in, R.anim.slide_next_out);
        }
    }

    public static void configureBasicUi(Activity activity, SetupWizardParams params, int context, boolean backAllowed, boolean collapseHeaderIfPossible, boolean hasKeyboard) {
        boolean useMaterialTheme = shouldUseMaterialTheme();
        View decorView = activity.getWindow().getDecorView();
        int visibility = decorView.getSystemUiVisibility();
        if (!useMaterialTheme && params.isInitialSetup()) {
            visibility |= 37158912;
            backAllowed = false;
        }
        if (backAllowed) {
            visibility &= -4194305;
        } else {
            visibility |= 4194304;
        }
        decorView.setSystemUiVisibility(visibility);
        if (useMaterialTheme) {
            configureBasicMaterialUi(activity, params, context, backAllowed, collapseHeaderIfPossible, hasKeyboard);
        }
    }

    private static void configureBasicMaterialUi(Activity activity, SetupWizardParams params, int context, boolean backAllowed, boolean collapseHeaderIfPossible, boolean hasKeyboard) {
        String imageUrl;
        SetupWizardNavBar navigationBar = getNavBarIfPossible(activity);
        navigationBar.setUseImmersiveMode(params.useImmersiveMode());
        navigationBar.getBackButton().setEnabled(backAllowed);
        if (hasKeyboard && params.useImmersiveMode() && ((Boolean) G.setupWizardForceResizeForKeyboardInFullscreen.get()).booleanValue()) {
            FullscreenAdjustResizeWorkaround.forceSizeChanges(activity);
        }
        boolean useWideImage = activity.getResources().getBoolean(R.bool.setup_wizard_use_tablet_graphic);
        if (context == 0) {
            imageUrl = useWideImage ? ILLUSTRATION_URL_PAYMENT_WIDE : ILLUSTRATION_URL_PAYMENT;
        } else if (context == 1) {
            imageUrl = useWideImage ? ILLUSTRATION_URL_RESTORE_WIDE : ILLUSTRATION_URL_RESTORE;
        } else {
            throw new IllegalStateException("Unknown context: " + context);
        }
        ((SetupWizardIllustration) activity.findViewById(R.id.illustration)).configure(imageUrl, collapseHeaderIfPossible);
    }
}
