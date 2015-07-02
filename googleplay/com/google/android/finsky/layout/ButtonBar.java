package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.android.vending.R;

public class ButtonBar extends LinearLayout implements OnClickListener {
    private static boolean SHOW_TOP_SEPARATOR;
    private ClickListener mClickListener;
    private Button mNegativeButton;
    private Button mPositiveButton;
    private Paint mTopSeparatorPaint;

    public interface ClickListener {
        void onNegativeButtonClick();

        void onPositiveButtonClick();
    }

    static {
        boolean z = VERSION.SDK_INT >= 11 && VERSION.SDK_INT < 21;
        SHOW_TOP_SEPARATOR = z;
    }

    public ButtonBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (SHOW_TOP_SEPARATOR) {
            this.mTopSeparatorPaint = new Paint();
            this.mTopSeparatorPaint.setColor(context.getResources().getColor(R.color.play_multi_primary));
            this.mTopSeparatorPaint.setStrokeWidth(1.0f);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mPositiveButton = (Button) findViewById(R.id.positive_button);
        this.mNegativeButton = (Button) findViewById(R.id.negative_button);
    }

    public void setPositiveButtonTitle(int titleResId) {
        this.mPositiveButton.setText(titleResId);
    }

    public void setPositiveButtonTitle(String title) {
        this.mPositiveButton.setText(title);
    }

    public void setNegativeButtonTitle(int titleResId) {
        this.mNegativeButton.setText(titleResId);
    }

    public void setNegativeButtonTitle(String title) {
        this.mNegativeButton.setText(title);
    }

    public void setPositiveButtonEnabled(boolean enabled) {
        this.mPositiveButton.setEnabled(enabled);
    }

    public void setPositiveButtonVisible(boolean visible) {
        this.mPositiveButton.setVisibility(visible ? 0 : 8);
    }

    public void setNegativeButtonVisible(boolean visible) {
        this.mNegativeButton.setVisibility(visible ? 0 : 8);
    }

    public void setClickListener(ClickListener listener) {
        this.mClickListener = listener;
        this.mPositiveButton.setOnClickListener(this);
        this.mNegativeButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.mClickListener == null) {
            return;
        }
        if (view == this.mPositiveButton) {
            this.mClickListener.onPositiveButtonClick();
        } else if (view == this.mNegativeButton) {
            this.mClickListener.onNegativeButtonClick();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mTopSeparatorPaint != null) {
            canvas.drawLine(0.0f, 0.0f, (float) getWidth(), 0.0f, this.mTopSeparatorPaint);
        }
    }
}
