package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.utils.CorpusResourceUtils;

public class PlayCardViewRateOverlay extends View {
    private final TextPaint mRatingLabelPaint;
    private final Rect mRatingLabelRect;
    private String mRatingLabelText;
    private final TextPaint mRatingStarsPaint;
    private final Rect mRatingStarsRect;
    private String mRatingStarsText;
    private final int mTopMargin;

    public PlayCardViewRateOverlay(Context context) {
        this(context, null);
    }

    public PlayCardViewRateOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = context.getResources();
        this.mRatingStarsPaint = new TextPaint();
        this.mRatingStarsPaint.setAntiAlias(true);
        this.mRatingStarsPaint.setTextSize((float) resources.getDimensionPixelSize(R.dimen.rate_card_stars_size));
        if (VERSION.SDK_INT >= 16) {
            this.mRatingStarsPaint.setTypeface(Typeface.create("sans-serif-light", 0));
        }
        this.mRatingLabelPaint = new TextPaint();
        this.mRatingLabelPaint.setAntiAlias(true);
        this.mRatingLabelPaint.setTextSize((float) resources.getDimensionPixelSize(R.dimen.rate_card_label_size));
        this.mRatingLabelPaint.setTypeface(Typeface.create("sans-serif", 0));
        this.mRatingStarsRect = new Rect();
        this.mRatingLabelRect = new Rect();
        this.mTopMargin = resources.getDimensionPixelSize(R.dimen.rate_card_overlay_top_margin);
        setWillNotDraw(false);
    }

    public void configure(int rating, int backendId) {
        this.mRatingStarsText = Integer.toString(rating);
        this.mRatingLabelText = getResources().getQuantityString(R.plurals.stars_without_numbers, rating);
        int ratingTextsColor = CorpusResourceUtils.getPrimaryColor(getContext(), backendId);
        this.mRatingStarsPaint.setColor(ratingTextsColor);
        this.mRatingLabelPaint.setColor(ratingTextsColor);
        this.mRatingStarsPaint.getTextBounds(this.mRatingStarsText, 0, this.mRatingStarsText.length(), this.mRatingStarsRect);
        this.mRatingLabelPaint.getTextBounds(this.mRatingLabelText, 0, this.mRatingLabelText.length(), this.mRatingLabelRect);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int ratingStarsTextHeight = this.mRatingStarsRect.height();
        int ratingTextsY = ((getPaddingTop() + this.mTopMargin) + ((height - (ratingStarsTextHeight + (this.mRatingLabelRect.height() * 2))) / 2)) + ratingStarsTextHeight;
        if (!TextUtils.isEmpty(this.mRatingStarsText)) {
            canvas.drawText(this.mRatingStarsText, (float) (((width - this.mRatingStarsRect.width()) / 2) - this.mRatingStarsRect.left), (float) ratingTextsY, this.mRatingStarsPaint);
        }
        if (!TextUtils.isEmpty(this.mRatingLabelText)) {
            int ratingLabelX = ((width - this.mRatingLabelRect.width()) / 2) - this.mRatingLabelRect.left;
            canvas.drawText(this.mRatingLabelText, (float) ratingLabelX, (float) (ratingTextsY + (this.mRatingLabelRect.height() * 2)), this.mRatingLabelPaint);
        }
    }
}
