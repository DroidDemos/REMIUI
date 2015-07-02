package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.YellowPageDataDetailEntry;

public class YellowPagePhonesListItem extends YellowPageListItem {
    public YellowPagePhonesListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(YellowPageDataDetailEntry yellowPageDataDetailEntry) {
        this.zK.setTextAppearance(getContext(), R.style.YellowPageListItemTextAppearancePrimary);
        this.zK.setSingleLine(true);
        this.zK.setText(yellowPageDataDetailEntry.getData());
        this.zL.setPadding(0, 0, 0, 0);
        this.zL.setVisibility(0);
        this.zL.setImageDrawable(yellowPageDataDetailEntry.dr());
        this.zL.setOnClickListener(new aw(this, yellowPageDataDetailEntry));
        if (TextUtils.isEmpty(yellowPageDataDetailEntry.getTypeString())) {
            this.zJ.setVisibility(8);
            return;
        }
        this.zJ.setText(yellowPageDataDetailEntry.getTypeString());
        this.zJ.setVisibility(0);
    }
}
