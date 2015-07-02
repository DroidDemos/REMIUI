package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.android.vending.R;

public class SetupWizardFopSeparator extends SeparatorLinearLayout {
    public SetupWizardFopSeparator(Context context) {
        this(context, null);
    }

    public SetupWizardFopSeparator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getSeparatorColor(Resources res) {
        return res.getColor(R.color.setup_wizard_divider_color);
    }
}
