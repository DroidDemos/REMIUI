package com.xiaomi.passport.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.passport.utils.PhoneNumUtil.CountryPhoneNumData;

public class AreaCodePickerListItem extends LinearLayout {
    private static final String TAG = "AreaCodePickerListItem";
    private TextView mAreaCodeView;
    private TextView mAreaView;
    private View mHeaderLayout;
    private TextView mSectionHeader;

    public AreaCodePickerListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAreaView = (TextView) findViewById(R.id.area);
        this.mAreaCodeView = (TextView) findViewById(R.id.area_code);
        this.mSectionHeader = (TextView) findViewById(R.id.section_header);
        this.mHeaderLayout = findViewById(R.id.section_header_layout);
    }

    public void bind(CountryPhoneNumData data, String sectionHeaderText) {
        this.mAreaView.setText(data.countryName);
        this.mAreaCodeView.setText(data.countryCode);
        if (TextUtils.isEmpty(sectionHeaderText)) {
            this.mHeaderLayout.setVisibility(8);
            return;
        }
        this.mSectionHeader.setText(sectionHeaderText);
        this.mHeaderLayout.setVisibility(0);
    }
}
