package com.google.android.finsky.layout;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.layout.DetailsTextSection.DetailsExtraSecondary;

public class DetailsExpandedExtraSecondaryView extends LinearLayout {
    private TextView mDescription;
    private TextView mTitle;

    public DetailsExpandedExtraSecondaryView(Context context) {
        this(context, null);
    }

    public DetailsExpandedExtraSecondaryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitle = (TextView) findViewById(R.id.extra_title);
        this.mDescription = (TextView) findViewById(R.id.extra_description);
        this.mDescription.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void populate(DetailsExtraSecondary extraSecondary) {
        this.mTitle.setText(extraSecondary.title);
        if (TextUtils.isEmpty(extraSecondary.description)) {
            this.mDescription.setVisibility(8);
            return;
        }
        this.mDescription.setVisibility(0);
        this.mDescription.setText(Html.fromHtml(extraSecondary.description));
    }
}
