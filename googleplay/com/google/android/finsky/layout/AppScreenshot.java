package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.vending.R;

public class AppScreenshot extends FrameLayout {
    private ImageView mScreenshot;

    public AppScreenshot(Context context) {
        super(context);
    }

    public AppScreenshot(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mScreenshot = (ImageView) findViewById(R.id.screenshot);
    }

    public void setScreenshotDrawable(Drawable screenshotDrawable) {
        this.mScreenshot.setImageDrawable(screenshotDrawable);
    }

    public boolean hasScreenshotDrawable() {
        return this.mScreenshot.getDrawable() != null;
    }
}
