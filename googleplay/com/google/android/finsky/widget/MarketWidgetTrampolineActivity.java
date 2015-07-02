package com.google.android.finsky.widget;

import com.android.vending.MarketWidgetProvider;
import com.google.android.finsky.config.G;

public class MarketWidgetTrampolineActivity extends WidgetTrampolineActivity {
    protected boolean shouldAllowConfiguration() {
        return ((Boolean) G.enableCorpusWidgets.get()).booleanValue();
    }

    protected Class<? extends BaseWidgetProvider> getWidgetClass() {
        return MarketWidgetProvider.class;
    }

    protected boolean enableMultiCorpus() {
        return false;
    }
}
