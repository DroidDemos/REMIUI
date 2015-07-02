package com.google.android.finsky.layout;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.layout.DetailsTextSection.DetailsExtraCredits;

public class DetailsExpandedExtraCreditsView extends LinearLayout {
    private TextView mCredit;
    private TextView mNames;

    public DetailsExpandedExtraCreditsView(Context context) {
        this(context, null);
    }

    public DetailsExpandedExtraCreditsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCredit = (TextView) findViewById(R.id.extra_credit);
        this.mNames = (TextView) findViewById(R.id.extra_names);
    }

    public void populate(DetailsExtraCredits extraCredits) {
        this.mCredit.setText(extraCredits.credit);
        if (TextUtils.isEmpty(extraCredits.names)) {
            this.mNames.setVisibility(8);
            return;
        }
        this.mNames.setVisibility(0);
        this.mNames.setText(Html.fromHtml(extraCredits.names));
    }
}
