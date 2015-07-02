package com.google.android.play.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import com.google.android.wallet.instrumentmanager.R;

public class PlayTouchDelegate extends TouchDelegate {
    private Rect mPlayBounds;
    private boolean mPlayDelegateTargeted;
    private View mPlayDelegateView;
    private int mPlaySlop;
    private Rect mPlaySlopBounds;

    public PlayTouchDelegate(Rect bounds, View delegateView) {
        super(bounds, delegateView);
        this.mPlayBounds = bounds;
        this.mPlaySlop = ViewConfiguration.get(delegateView.getContext()).getScaledTouchSlop();
        this.mPlaySlopBounds = new Rect(bounds);
        this.mPlaySlopBounds.inset(-this.mPlaySlop, -this.mPlaySlop);
        this.mPlayDelegateView = delegateView;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        boolean sendToDelegate = false;
        boolean hit = true;
        switch (event.getAction()) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                if (this.mPlayBounds.contains(x, y)) {
                    this.mPlayDelegateTargeted = true;
                    sendToDelegate = true;
                    break;
                }
                break;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                sendToDelegate = this.mPlayDelegateTargeted;
                if (sendToDelegate && !this.mPlaySlopBounds.contains(x, y)) {
                    hit = false;
                }
                if (event.getAction() == 1) {
                    this.mPlayDelegateTargeted = false;
                    break;
                }
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                sendToDelegate = this.mPlayDelegateTargeted;
                this.mPlayDelegateTargeted = false;
                break;
        }
        if (!sendToDelegate) {
            return false;
        }
        View delegateView = this.mPlayDelegateView;
        if (hit) {
            event.setLocation((float) (delegateView.getWidth() / 2), (float) (delegateView.getHeight() / 2));
        } else {
            int slop = this.mPlaySlop;
            event.setLocation((float) (-(slop * 2)), (float) (-(slop * 2)));
        }
        return delegateView.dispatchTouchEvent(event);
    }
}
