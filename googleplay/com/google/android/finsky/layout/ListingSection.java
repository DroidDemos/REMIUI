package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;

public class ListingSection extends RelativeLayout {
    private LinearLayout mDescriptionContainer;
    private ImageView mIcon;
    private LayoutInflater mInflater;
    private TextView mTitle;
    private View mTopSeparator;

    public ListingSection(Context context) {
        this(context, null);
    }

    public ListingSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mInflater = LayoutInflater.from(context);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitle = (TextView) findViewById(R.id.listing_title);
        this.mDescriptionContainer = (LinearLayout) findViewById(R.id.listing_description);
        this.mIcon = (ImageView) findViewById(R.id.listing_icon);
        this.mTopSeparator = findViewById(R.id.top_separator);
    }
}
