package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.ShopInformation;

public class ShopInformationListItem extends LinearLayout {
    private TextView ga;

    public ShopInformationListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.ga = (TextView) findViewById(R.id.info);
    }

    public void a(ShopInformation shopInformation) {
        this.ga.setText(getContext().getResources().getString(R.string.shop_recommendation_format, new Object[]{shopInformation.getTitle(), shopInformation.getContent()}));
        if (shopInformation.getIntent() != null) {
            setOnClickListener(new dt(this, shopInformation));
        } else {
            setClickable(false);
        }
    }
}
