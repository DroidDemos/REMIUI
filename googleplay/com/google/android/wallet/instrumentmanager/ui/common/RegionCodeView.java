package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.common.address.AddressUtils;
import com.google.android.wallet.instrumentmanager.ui.common.RegionCodeSelector.OnRegionCodeSelectedListener;

public class RegionCodeView extends FrameLayout implements OnRegionCodeSelectedListener {
    private OnRegionCodeSelectedListener mOnRegionCodeSelectedListener;
    private boolean mReadOnlyMode;
    private boolean mRegionCodesSet;
    private int mSelectedRegionCode;
    public RegionCodeSelectorSpinner mSpinner;
    public TextView mTextView;

    public RegionCodeView(Context context) {
        super(context);
    }

    public RegionCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionCodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public RegionCodeView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSpinner = (RegionCodeSelectorSpinner) findViewById(R.id.region_code_spinner);
        this.mTextView = (TextView) findViewById(R.id.region_code_text);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mSpinner.setEnabled(enabled);
        this.mTextView.setEnabled(enabled);
    }

    public void setRegionCodes(int[] regionCodes) {
        if (regionCodes.length == 1) {
            this.mSpinner.setRegionCodeSelectedListener(null);
            this.mSpinner.setVisibility(8);
            this.mTextView.setText(AddressUtils.getDisplayCountryForDefaultLocale(regionCodes[0]));
            onRegionCodeSelected(regionCodes[0], getId());
            this.mTextView.setVisibility(0);
            this.mReadOnlyMode = true;
        } else {
            this.mSpinner.setRegionCodeSelectedListener(this);
            this.mSpinner.setRegionCodes(regionCodes);
            this.mSpinner.setVisibility(0);
            this.mTextView.setVisibility(8);
            this.mReadOnlyMode = false;
        }
        this.mRegionCodesSet = true;
    }

    public void setSelectedRegionCode(int regionCode) {
        if (!this.mRegionCodesSet) {
            throw new IllegalStateException("setRegionCodes() must be called before setSelectedRegionCode()");
        } else if (!this.mReadOnlyMode) {
            this.mSpinner.setSelectedRegionCode(regionCode);
        }
    }

    public void setRegionCodeSelectedListener(OnRegionCodeSelectedListener listener) {
        this.mOnRegionCodeSelectedListener = listener;
    }

    public int getSelectedRegionCode() {
        if (this.mReadOnlyMode) {
            return this.mSelectedRegionCode;
        }
        return this.mSpinner.getSelectedRegionCode();
    }

    public void onRegionCodeSelected(int regionCode, int senderId) {
        this.mSelectedRegionCode = regionCode;
        if (this.mOnRegionCodeSelectedListener != null) {
            this.mOnRegionCodeSelectedListener.onRegionCodeSelected(regionCode, getId());
        }
    }
}
