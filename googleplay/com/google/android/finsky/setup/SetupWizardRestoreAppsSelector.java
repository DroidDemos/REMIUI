package com.google.android.finsky.setup;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.layout.SeparatorLinearLayout;

public class SetupWizardRestoreAppsSelector extends SeparatorLinearLayout {
    private TextView mMainText;
    private TextView mSecondaryText;

    public SetupWizardRestoreAppsSelector(Context context) {
        super(context);
    }

    public SetupWizardRestoreAppsSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMainText = (TextView) findViewById(R.id.sw_menu_selector_main_text);
        this.mSecondaryText = (TextView) findViewById(R.id.sw_menu_selector_secondary_text);
        syncContentDescription();
    }

    public void setTexts(String mainText) {
        setTexts(mainText, "");
    }

    public void setTexts(String mainText, String secondaryText) {
        this.mMainText.setText(mainText);
        this.mSecondaryText.setText(secondaryText);
        syncContentDescription();
    }

    private void syncContentDescription() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.mMainText.getText())) {
            sb.append(this.mMainText.getText()).append(", ");
        }
        if (!TextUtils.isEmpty(this.mSecondaryText.getText())) {
            sb.append(this.mSecondaryText.getText()).append(", ");
        }
        sb.append(getResources().getString(R.string.content_description_toggle_selector));
        setContentDescription(sb.toString());
    }
}
