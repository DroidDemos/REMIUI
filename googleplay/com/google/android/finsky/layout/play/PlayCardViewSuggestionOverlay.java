package com.google.android.finsky.layout.play;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.android.vending.R;

public class PlayCardViewSuggestionOverlay extends View {
    private final Drawable mOverlayDrawable;

    public PlayCardViewSuggestionOverlay(Context context) {
        this(context, null);
    }

    public PlayCardViewSuggestionOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOverlayDrawable = context.getResources().getDrawable(R.drawable.ic_hidden_cards);
        setBackgroundResource(R.drawable.card_suggestion_overlay);
        setWillNotDraw(false);
    }

    protected void onDraw(Canvas canvas) {
        float scaleY = 1.0f;
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int overlayIntrinsicWidth = this.mOverlayDrawable.getIntrinsicWidth();
        int overlayIntrinsicHeight = this.mOverlayDrawable.getIntrinsicHeight();
        float scaleX = overlayIntrinsicWidth <= width ? 1.0f : ((float) width) / ((float) overlayIntrinsicWidth);
        if (overlayIntrinsicHeight > height) {
            scaleY = ((float) height) / ((float) overlayIntrinsicHeight);
        }
        float scale = Math.min(scaleX, scaleY);
        int overlayWidth = (int) (((float) overlayIntrinsicWidth) * scale);
        int overlayHeight = (int) (((float) overlayIntrinsicHeight) * scale);
        int overlayX = (width - overlayWidth) / 2;
        int overlayY = (height - overlayHeight) / 2;
        this.mOverlayDrawable.setBounds(overlayX, overlayY, overlayX + overlayWidth, overlayY + overlayHeight);
        this.mOverlayDrawable.draw(canvas);
    }
}
