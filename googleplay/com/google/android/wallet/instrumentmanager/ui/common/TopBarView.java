package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.config.G.images;

public class TopBarView extends LinearLayout {
    FifeNetworkImageView mTitleImageView;
    TextView mTitleTextView;

    public TopBarView(Context context) {
        super(context);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyle, int defResStyle) {
        super(context, attrs, defStyle, defResStyle);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        View separator = findViewById(R.id.title_separator);
        this.mTitleTextView = (TextView) findViewById(R.id.title);
        this.mTitleImageView = (FifeNetworkImageView) findViewById(R.id.title_icon);
        TypedArray a = getContext().obtainStyledAttributes(new int[]{R.attr.imTitleSeparatorBackground});
        WalletUiUtils.setViewBackgroundOrHide(separator, a.peekValue(0));
        a.recycle();
    }

    public void setTitle(CharSequence title, String titleImageFifeUrl, String titleImageContentDescription) {
        if (TextUtils.isEmpty(title)) {
            this.mTitleTextView.setVisibility(8);
        } else {
            this.mTitleTextView.setText(title);
            this.mTitleTextView.setVisibility(0);
        }
        if (TextUtils.isEmpty(titleImageFifeUrl)) {
            this.mTitleImageView.setVisibility(8);
            return;
        }
        this.mTitleImageView.setVisibility(0);
        this.mTitleImageView.setFadeIn(true);
        this.mTitleImageView.setFifeImageUrl(titleImageFifeUrl, PaymentUtils.getImageLoader(getContext().getApplicationContext()), ((Boolean) images.useWebPForFife.get()).booleanValue());
        this.mTitleImageView.setContentDescription(titleImageContentDescription);
    }

    public CharSequence getTitle() {
        return this.mTitleTextView.getText();
    }
}
