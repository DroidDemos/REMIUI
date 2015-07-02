package com.google.android.finsky.layout;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.layout.DetailsTextSection.DetailsExtraPrimary;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;

public class DetailsExpandedExtraPrimaryView extends RelativeLayout {
    private TextView mDescription;
    private FifeImageView mThumbnail;
    private TextView mTitle;

    public DetailsExpandedExtraPrimaryView(Context context) {
        this(context, null);
    }

    public DetailsExpandedExtraPrimaryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mThumbnail = (FifeImageView) findViewById(R.id.extra_thumbnail);
        this.mTitle = (TextView) findViewById(R.id.extra_title);
        this.mDescription = (TextView) findViewById(R.id.extra_description);
        this.mDescription.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void populate(DetailsExtraPrimary extraPrimary, BitmapLoader bitmapLoader, OnClickListener onClickListener) {
        if (extraPrimary.image == null) {
            this.mThumbnail.setVisibility(4);
        } else {
            this.mThumbnail.setVisibility(0);
            this.mThumbnail.setImage(extraPrimary.image.imageUrl, extraPrimary.image.supportsFifeUrlOptions, bitmapLoader);
        }
        this.mTitle.setText(extraPrimary.title);
        if (TextUtils.isEmpty(extraPrimary.description)) {
            this.mDescription.setVisibility(8);
        } else {
            this.mDescription.setVisibility(0);
            this.mDescription.setText(Html.fromHtml(extraPrimary.description));
        }
        if (onClickListener != null) {
            setOnClickListener(onClickListener);
            return;
        }
        setOnClickListener(null);
        setClickable(false);
    }
}
