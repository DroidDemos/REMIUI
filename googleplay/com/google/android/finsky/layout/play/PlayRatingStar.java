package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.android.vending.R;
import com.google.android.finsky.utils.CorpusResourceUtils;

public class PlayRatingStar extends ImageView {
    private int mFilledFocusedResourceId;
    private int mFilledResourceId;
    private int mFocusedResourceId;
    private int mIndex;
    private boolean mIsFilled;
    private boolean mIsFocused;
    private int mNormalResourceId;
    private OnPressStateChangeListener mOnPressStateChangeListener;
    private boolean mWasPressed;

    public interface OnPressStateChangeListener {
        void onPressStateChanged(View view, boolean z);
    }

    public PlayRatingStar(Context context) {
        this(context, null);
    }

    public PlayRatingStar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void configure(int index, int backend) {
        this.mIndex = index;
        this.mNormalResourceId = R.drawable.btn_rating_star_normal;
        this.mFocusedResourceId = R.drawable.btn_rating_star_normal_focused;
        this.mFilledResourceId = CorpusResourceUtils.getRatingBarFilledResourceId(backend);
        this.mFilledFocusedResourceId = CorpusResourceUtils.getRatingBarFilledFocusedResourceId(backend);
        syncState();
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setOnPressStateChangeListener(OnPressStateChangeListener listener) {
        this.mOnPressStateChangeListener = listener;
    }

    public void setFocused(boolean isFocused) {
        this.mIsFocused = isFocused;
        syncState();
    }

    public void setFilled(boolean isFilled) {
        this.mIsFilled = isFilled;
        syncState();
    }

    public void refreshDrawableState() {
        super.refreshDrawableState();
        boolean isPressed = isPressed();
        if (this.mWasPressed != isPressed) {
            if (this.mOnPressStateChangeListener != null) {
                this.mOnPressStateChangeListener.onPressStateChanged(this, isPressed);
            }
            this.mWasPressed = isPressed;
        }
    }

    private void syncState() {
        if (this.mIsFilled) {
            setImageResource(this.mIsFocused ? this.mFilledFocusedResourceId : this.mFilledResourceId);
        } else {
            setImageResource(this.mIsFocused ? this.mFocusedResourceId : this.mNormalResourceId);
        }
    }
}
