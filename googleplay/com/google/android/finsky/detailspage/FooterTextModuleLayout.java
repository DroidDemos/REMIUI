package com.google.android.finsky.detailspage;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoBottomDivider;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoTopDivider;

public class FooterTextModuleLayout extends TextView implements NoBottomDivider, NoTopDivider {
    private boolean mBinded;

    public FooterTextModuleLayout(Context context) {
        super(context, null);
    }

    public FooterTextModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(String footerHtml) {
        setText(Html.fromHtml(footerHtml));
        this.mBinded = true;
    }

    public boolean hasBinded() {
        return this.mBinded;
    }
}
