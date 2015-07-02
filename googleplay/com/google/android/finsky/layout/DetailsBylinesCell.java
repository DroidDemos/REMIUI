package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.layout.DetailsBylinesSection.DetailsBylineEntry;

public class DetailsBylinesCell extends RelativeLayout {
    private ImageView mThumbnail;
    private TextView mTitle;

    public DetailsBylinesCell(Context context) {
        this(context, null);
    }

    public DetailsBylinesCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mThumbnail = (ImageView) findViewById(R.id.byline_thumbnail);
        this.mTitle = (TextView) findViewById(R.id.byline_title);
    }

    public void populate(DetailsBylineEntry bylineEntry) {
        if (bylineEntry.iconResourceId < 0) {
            this.mThumbnail.setVisibility(4);
        } else {
            this.mThumbnail.setVisibility(0);
            this.mThumbnail.setImageResource(bylineEntry.iconResourceId);
        }
        if (bylineEntry.textResourceId > 0) {
            this.mTitle.setText(bylineEntry.textResourceId);
        } else {
            this.mTitle.setText(bylineEntry.text);
        }
        if (bylineEntry.onClickListener != null) {
            setOnClickListener(bylineEntry.onClickListener);
            return;
        }
        setOnClickListener(null);
        setClickable(false);
    }
}
