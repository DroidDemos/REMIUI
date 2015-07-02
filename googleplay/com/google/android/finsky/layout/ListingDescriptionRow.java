package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.play.image.FifeImageView;

public class ListingDescriptionRow extends LinearLayout {
    private FifeImageView mIcon;
    private TextView mText;

    public ListingDescriptionRow(Context context) {
        super(context);
    }

    public ListingDescriptionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mText = (TextView) findViewById(R.id.listing_description_text);
        this.mIcon = (FifeImageView) findViewById(R.id.listing_description_icon);
    }
}
