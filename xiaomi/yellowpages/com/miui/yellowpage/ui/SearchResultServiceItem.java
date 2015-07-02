package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.SearchResultDataEntry;
import com.miui.yellowpage.base.model.SearchResultServiceDataEntry;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

public class SearchResultServiceItem extends du {
    private SearchResultServiceDataEntry DQ;
    private TextView bR;

    public SearchResultServiceItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.bR = (TextView) findViewById(R.id.description);
    }

    public void a(SearchResultDataEntry searchResultDataEntry) {
        if (searchResultDataEntry != null) {
            this.DQ = (SearchResultServiceDataEntry) searchResultDataEntry;
            this.bQ.setText(this.DQ.getTitle());
            CharSequence description = this.DQ.getDescription();
            if (TextUtils.isEmpty(description)) {
                this.bR.setVisibility(8);
            } else {
                this.bR.setText(description);
                this.bR.setVisibility(0);
            }
            YellowPageImgLoader.loadThumbnailByName(this.mContext, this.uW, kD, searchResultDataEntry.getIcon(), R.drawable.yellowpage_default_icon);
        }
    }
}
