package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import com.android.vending.R;
import com.google.android.play.layout.PlayActionButton;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardViewSingleWithButton extends PlayCardViewBase {
    protected PlayActionButton mActionButton;

    public PlayCardViewSingleWithButton(Context context) {
        this(context, null);
    }

    public PlayCardViewSingleWithButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getCardType() {
        return 14;
    }

    public PlayActionButton getActionButton() {
        return this.mActionButton;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mActionButton = (PlayActionButton) findViewById(R.id.li_primary_action_button);
    }

    public void bindLoading() {
        super.bindLoading();
        if (this.mActionButton != null) {
            this.mActionButton.setVisibility(8);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureThumbnailSpanningHeight(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
