package com.google.android.finsky.activities;

import android.os.Bundle;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.utils.ExpandableUtils;

public class DetailsTextViewBinder extends DetailsViewBinder {
    private int mExpansionState;
    private boolean mIsTranslated;

    public void saveInstanceState(Bundle bundle) {
        if (this.mLayout != null) {
            ExpandableUtils.saveExpansionState(bundle, Integer.toString(this.mLayout.getId()), this.mExpansionState);
            bundle.putBoolean("translation_state" + this.mLayout.getId(), this.mIsTranslated);
        }
    }

    public void onDestroyView() {
    }
}
