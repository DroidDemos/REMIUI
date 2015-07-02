package com.google.android.play.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.play.R;
import com.google.android.play.utils.PlayCorpusUtils;

public class PlayCardLabelView extends View {
    private boolean mCanShowStrikeText;
    private final int mDefaultStrikeTextColor;
    private Drawable mIcon;
    private final int mIconGap;
    private String mStrikeText;
    private final TextPaint mStrikeTextPaint;
    private int mStrikeTextWidth;
    private String mText;
    private final int mTextBaseline;
    private final int mTextHeight;
    private final TextPaint mTextPaint;
    private final int mTextSize;
    private final int mTextsGap;

    public PlayCardLabelView(Context context) {
        this(context, null);
    }

    public PlayCardLabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mIconGap = res.getDimensionPixelSize(R.dimen.play_card_label_icon_gap);
        this.mTextsGap = res.getDimensionPixelSize(R.dimen.play_card_label_texts_gap);
        this.mTextSize = res.getDimensionPixelSize(R.dimen.play_medium_size);
        this.mTextPaint = new TextPaint(1);
        this.mTextPaint.density = res.getDisplayMetrics().density;
        this.mTextPaint.setTextSize((float) this.mTextSize);
        this.mTextPaint.setFakeBoldText(false);
        this.mStrikeTextPaint = new TextPaint(1);
        this.mStrikeTextPaint.density = res.getDisplayMetrics().density;
        this.mStrikeTextPaint.setTextSize((float) this.mTextSize);
        this.mDefaultStrikeTextColor = res.getColor(R.color.play_fg_secondary);
        this.mStrikeTextPaint.setColor(this.mDefaultStrikeTextColor);
        this.mStrikeTextPaint.setStrikeThruText(true);
        this.mStrikeTextPaint.setFakeBoldText(false);
        FontMetrics fm = this.mTextPaint.getFontMetrics();
        this.mTextHeight = (int) (Math.abs(fm.top) + Math.abs(fm.bottom));
        this.mTextBaseline = (int) Math.abs(fm.top);
        setWillNotDraw(false);
    }

    public void setText(int stringId, int backend) {
        setText(getResources().getString(stringId), backend);
    }

    public void setText(String text, int backend) {
        setText(text, null, backend, text);
    }

    public void setText(String text, String strikeText, int backend, String contentDescription) {
        setText(text, PlayCorpusUtils.getPrimaryTextColor(getContext(), backend).getDefaultColor(), strikeText, this.mDefaultStrikeTextColor, contentDescription);
    }

    public void setText(String text, int textColor, String strikeText, int strikeTextColor, String contentDescription) {
        String str = null;
        this.mText = text != null ? text.toUpperCase() : null;
        if (strikeText != null) {
            str = strikeText.toUpperCase();
        }
        this.mStrikeText = str;
        this.mTextPaint.setColor(textColor);
        this.mStrikeTextPaint.setColor(strikeTextColor);
        setContentDescription(contentDescription);
        invalidate();
        requestLayout();
    }

    public String getText() {
        return this.mText;
    }

    public String getStrikeText() {
        return this.mStrikeText;
    }

    public void setIcon(int iconResourceId) {
        this.mIcon = getResources().getDrawable(iconResourceId);
        this.mIcon.setBounds(0, 0, this.mIcon.getIntrinsicWidth(), this.mIcon.getIntrinsicHeight());
        invalidate();
        requestLayout();
    }

    public void clearIcon() {
        this.mIcon = null;
        invalidate();
        requestLayout();
    }

    @ExportedProperty(category = "layout")
    public int getBaseline() {
        return getPaddingTop() + this.mTextBaseline;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        boolean result = super.dispatchPopulateAccessibilityEvent(event);
        if (event.getEventType() != 8) {
            return result;
        }
        event.getText().add(getContentDescription());
        return true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean forceZeroWidth;
        boolean hasText;
        if (MeasureSpec.getMode(widthMeasureSpec) == 1073741824 && MeasureSpec.getSize(widthMeasureSpec) == 0) {
            forceZeroWidth = true;
        } else {
            forceZeroWidth = false;
        }
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        this.mStrikeTextWidth = 0;
        this.mCanShowStrikeText = false;
        if (TextUtils.isEmpty(this.mText)) {
            hasText = false;
        } else {
            hasText = true;
        }
        if (!forceZeroWidth) {
            if (this.mIcon != null) {
                width = this.mIcon.getIntrinsicWidth();
                if (hasText) {
                    width += this.mIconGap;
                }
            }
            if (hasText) {
                width += (int) this.mTextPaint.measureText(this.mText);
            }
            if (!TextUtils.isEmpty(this.mStrikeText)) {
                int i;
                this.mStrikeTextWidth = (int) this.mStrikeTextPaint.measureText(this.mStrikeText);
                int i2 = this.mStrikeTextWidth;
                if (hasText) {
                    i = this.mTextsGap;
                } else {
                    i = 0;
                }
                int requiredWidth = i2 + i;
                if (availableWidth <= 0 || width + requiredWidth > availableWidth) {
                    this.mCanShowStrikeText = false;
                } else {
                    width += requiredWidth;
                    this.mCanShowStrikeText = true;
                }
            }
            width += getPaddingLeft() + getPaddingRight();
        }
        setMeasuredDimension(width, (this.mIcon == null ? this.mTextHeight : Math.max(this.mTextHeight, this.mIcon.getIntrinsicHeight())) + (getPaddingTop() + getPaddingBottom()));
    }

    protected void onDraw(Canvas canvas) {
        canvas.save();
        int x = getPaddingLeft();
        int y = getPaddingTop();
        boolean hasText = !TextUtils.isEmpty(this.mText);
        if (this.mIcon != null) {
            int iconY = y + ((((getHeight() - getPaddingTop()) - getPaddingBottom()) - this.mIcon.getIntrinsicHeight()) / 2);
            canvas.translate((float) x, (float) iconY);
            this.mIcon.draw(canvas);
            canvas.translate((float) (-x), (float) (-iconY));
            x += this.mIcon.getIntrinsicWidth() + this.mIconGap;
        }
        if (this.mCanShowStrikeText) {
            canvas.drawText(this.mStrikeText, (float) x, (float) (this.mTextBaseline + y), this.mStrikeTextPaint);
            x += this.mStrikeTextWidth + this.mTextsGap;
        }
        if (hasText) {
            canvas.drawText(this.mText, (float) x, (float) (this.mTextBaseline + y), this.mTextPaint);
        }
        canvas.restore();
    }
}
