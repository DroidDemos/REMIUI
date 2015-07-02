package com.google.android.finsky.layout.actionbar;

import com.google.android.finsky.activities.TextSectionTranslatable;

public interface ActionBarController {

    public interface ActionBarSearchModeListener {
        void onEnterActionBarSearchMode();

        void onExitActionBarSearchMode();
    }

    void disableActionBarOverlay();

    void enableActionBarOverlay();

    void enterActionBarSearchMode();

    void enterActionBarSectionExpandedMode(CharSequence charSequence, TextSectionTranslatable textSectionTranslatable);

    void exitActionBarSearchMode();

    void exitActionBarSectionExpandedMode();

    void setActionBarAlpha(int i, boolean z);

    void setActionBarSearchModeListener(ActionBarSearchModeListener actionBarSearchModeListener);
}
