package com.google.android.finsky.layout;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.vending.R;

public class LayoutSwitcher {
    protected final View mContentLayout;
    protected int mDataLayoutId;
    private final int mErrorLayoutId;
    private final Handler mHandler;
    private final int mLoadingLayoutId;
    private int mMode;
    private volatile boolean mPendingLoad;
    private final RetryButtonListener mRetryListener;
    private final OnClickListener retryClickListener;

    public interface RetryButtonListener {
        void onRetry();
    }

    public LayoutSwitcher(View pageLayout, int dataLayoutId, int errorLayoutId, int loadingLayoutId, RetryButtonListener listener, int initialState) {
        this.mHandler = new Handler();
        this.retryClickListener = new OnClickListener() {
            public void onClick(View v) {
                LayoutSwitcher.this.switchToLoadingMode();
                LayoutSwitcher.this.mRetryListener.onRetry();
            }
        };
        this.mPendingLoad = false;
        this.mDataLayoutId = dataLayoutId;
        this.mErrorLayoutId = errorLayoutId;
        this.mLoadingLayoutId = loadingLayoutId;
        this.mContentLayout = pageLayout;
        this.mRetryListener = listener;
        this.mMode = initialState;
    }

    public LayoutSwitcher(View pageLayout, int dataLayoutId, RetryButtonListener listener) {
        this.mHandler = new Handler();
        this.retryClickListener = /* anonymous class already generated */;
        this.mPendingLoad = false;
        this.mDataLayoutId = dataLayoutId;
        this.mErrorLayoutId = R.id.error_indicator;
        this.mLoadingLayoutId = R.id.loading_indicator;
        this.mContentLayout = pageLayout;
        this.mRetryListener = listener;
        resetMode();
    }

    private void resetMode() {
        this.mMode = 3;
        setLoadingVisible(false);
        setErrorVisible(false, null);
        setDataVisible(false, false);
    }

    public boolean isDataMode() {
        return this.mMode == 2;
    }

    public void switchToBlankMode() {
        performSwitch(3, null);
    }

    public void switchToLoadingMode() {
        performSwitch(0, null);
    }

    public void switchToLoadingDelayed(int delayMillis) {
        this.mPendingLoad = true;
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (LayoutSwitcher.this.mPendingLoad) {
                    LayoutSwitcher.this.switchToLoadingMode();
                }
            }
        }, (long) delayMillis);
    }

    public void switchToDataMode() {
        performSwitch(2, null);
    }

    public void switchToDataMode(int layoutId) {
        this.mDataLayoutId = layoutId;
        switchToDataMode();
    }

    public void switchToErrorMode(String error) {
        performSwitch(1, error);
    }

    private void performSwitch(int newMode, String errorMessage) {
        this.mPendingLoad = false;
        if (this.mMode != newMode) {
            switch (this.mMode) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    setLoadingVisible(false);
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    setErrorVisible(false, null);
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    setDataVisible(false, newMode == 0);
                    break;
            }
            switch (newMode) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    setLoadingVisible(true);
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    setErrorVisible(true, errorMessage);
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    setDataVisible(true, false);
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    break;
                default:
                    throw new IllegalStateException("Invalid mode " + newMode + "should be LOADING_MODE, ERROR_MODE, DATA_MODE, or BLANK_MODE");
            }
            this.mMode = newMode;
        }
    }

    private void setLoadingVisible(boolean show) {
        this.mContentLayout.findViewById(this.mLoadingLayoutId).setVisibility(show ? 0 : 8);
    }

    private void setErrorVisible(boolean show, String errorMessage) {
        View errorIndicator = this.mContentLayout.findViewById(this.mErrorLayoutId);
        errorIndicator.setVisibility(show ? 0 : 8);
        if (show) {
            ((TextView) errorIndicator.findViewById(R.id.error_msg)).setText(errorMessage);
        }
        ((Button) errorIndicator.findViewById(R.id.retry_button)).setOnClickListener(show ? this.retryClickListener : null);
    }

    protected void setDataVisible(boolean show, boolean goingToLoadingMode) {
        if (this.mDataLayoutId > 0) {
            ViewGroup dataView = (ViewGroup) this.mContentLayout.findViewById(this.mDataLayoutId);
            if (dataView != null) {
                dataView.setVisibility(show ? 0 : 8);
            }
        }
    }
}
