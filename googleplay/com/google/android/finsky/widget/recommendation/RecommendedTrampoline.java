package com.google.android.finsky.widget.recommendation;

import com.android.vending.R;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.WidgetTrampolineActivity;

public class RecommendedTrampoline extends WidgetTrampolineActivity {
    protected boolean enableMultiCorpus() {
        return true;
    }

    protected Class<? extends BaseWidgetProvider> getWidgetClass() {
        return RecommendedWidgetProvider.class;
    }

    protected String getCorpusName(int backend) {
        if (backend == 0) {
            return getString(R.string.my_apps_tab_library);
        }
        return super.getCorpusName(backend);
    }

    protected int getDialogTitle() {
        return R.string.widget_recommended;
    }
}
