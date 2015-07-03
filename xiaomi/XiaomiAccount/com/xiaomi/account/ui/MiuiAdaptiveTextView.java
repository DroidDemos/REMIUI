package com.xiaomi.account.ui;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.xiaomi.account.R;

public class MiuiAdaptiveTextView extends TextView {
    private static final float STEP_OF_TEXT_SIZE = 1.0f;
    private int mCurrentWidth;
    private int mHeight;
    private final float mMaxTextSize;
    private final float mMinTextSize;
    private final int mRightMargin;

    public MiuiAdaptiveTextView(Context context) {
        super(context);
        this.mHeight = -1;
        this.mCurrentWidth = -1;
        this.mMaxTextSize = (float) context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_text_size);
        this.mMinTextSize = (float) context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_min_text_size);
        this.mRightMargin = context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_view_right_margin);
    }

    public MiuiAdaptiveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHeight = -1;
        this.mCurrentWidth = -1;
        this.mMaxTextSize = (float) context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_text_size);
        this.mMinTextSize = (float) context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_min_text_size);
        this.mRightMargin = context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_view_right_margin);
    }

    public MiuiAdaptiveTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mHeight = -1;
        this.mCurrentWidth = -1;
        this.mMaxTextSize = (float) context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_text_size);
        this.mMinTextSize = (float) context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_min_text_size);
        this.mRightMargin = context.getResources().getDimensionPixelSize(R.dimen.micloud_settings_user_id_view_right_margin);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.mHeight < 0) {
            int height = getHeight();
            if (height > 0) {
                this.mHeight = height;
                LayoutParams params = getLayoutParams();
                params.height = this.mHeight;
                setLayoutParams(params);
            }
        } else if (((View) getParent()).getWidth() != this.mCurrentWidth) {
            setText(getText());
        }
    }

    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (isLayoutRequested() || ((View) getParent()).getWidth() != this.mCurrentWidth) {
            adaptTextSize();
        }
    }

    private void adaptTextSize() {
        if (this.mHeight >= 0 && getParent() != null) {
            this.mCurrentWidth = ((View) getParent()).getWidth();
            int textAreaWidth = this.mCurrentWidth - this.mRightMargin;
            setTextSize(0, this.mMaxTextSize);
            String curText = getText().toString();
            float textSize = this.mMaxTextSize;
            TextPaint paint = getPaint();
            paint.setTextSize(textSize);
            int widthText = (int) (paint.measureText(curText) + 0.5f);
            while (widthText > textAreaWidth) {
                textSize -= STEP_OF_TEXT_SIZE;
                if (textSize < this.mMinTextSize) {
                    textSize = this.mMinTextSize;
                    break;
                } else {
                    paint.setTextSize(textSize);
                    widthText = (int) (paint.measureText(curText) + 0.5f);
                }
            }
            setTextSize(0, textSize);
        }
    }
}
