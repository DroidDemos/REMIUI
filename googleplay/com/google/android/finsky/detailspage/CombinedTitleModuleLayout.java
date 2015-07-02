package com.google.android.finsky.detailspage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoBottomDivider;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoTopDivider;
import com.google.android.finsky.layout.DiscoveryBar;
import com.google.android.finsky.layout.WarningMessageSection;

public class CombinedTitleModuleLayout extends LinearLayout implements NoBottomDivider, NoTopDivider {
    private DiscoveryBar mDiscoveryBar;
    private WarningMessageSection mWarningMessageSection;

    public CombinedTitleModuleLayout(Context context) {
        super(context);
    }

    public CombinedTitleModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDiscoveryBar = (DiscoveryBar) findViewById(R.id.discovery_bar);
        this.mWarningMessageSection = (WarningMessageSection) findViewById(R.id.warning_message_panel);
    }

    public View getTitleModuleLayout() {
        return this;
    }

    public DiscoveryBar getDiscoveryBarModuleLayout() {
        return this.mDiscoveryBar;
    }

    public WarningMessageSection getWarningMessageModuleLayout() {
        return this.mWarningMessageSection;
    }
}
