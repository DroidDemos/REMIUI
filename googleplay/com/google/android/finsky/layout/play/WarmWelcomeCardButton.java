package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;

public class WarmWelcomeCardButton extends LinearLayout implements Recyclable {
    private FifeImageView mIcon;
    private TextView mText;

    public WarmWelcomeCardButton(Context context) {
        this(context, null);
    }

    public WarmWelcomeCardButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (FifeImageView) findViewById(R.id.icon);
        this.mText = (TextView) findViewById(R.id.text);
    }

    public void configure(String text, Image icon, BitmapLoader bitmapLoader) {
        this.mText.setText(text);
        if (icon != null) {
            this.mIcon.setImage(icon.imageUrl, icon.supportsFifeUrlOptions, bitmapLoader);
            this.mIcon.setVisibility(0);
            return;
        }
        this.mIcon.setVisibility(8);
    }

    public void alignStart() {
        setGravity(8388627);
    }

    public void alignCenter() {
        setGravity(17);
    }

    public void onRecycle() {
        this.mIcon.clearImage();
    }
}
