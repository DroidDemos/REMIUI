package com.google.android.finsky.detailspage;

import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.DetailsFlagItemSection;

public class FlagContentModule extends FinskyModule<Data> {

    protected static class Data extends ModuleData {
        Document detailsDoc;

        protected Data() {
        }
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null;
    }

    public int getLayoutResId() {
        return R.layout.details_flag_item;
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData != null || !hasDetailsLoaded) {
            return;
        }
        if (detailsDoc.getBackend() == 3 || detailsDoc.getBackend() == 2) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).detailsDoc = detailsDoc;
        }
    }

    public void bindView(View view) {
        DetailsFlagItemSection flagItemSection = (DetailsFlagItemSection) view;
        if (!flagItemSection.hasBinded()) {
            flagItemSection.bind(((Data) this.mModuleData).detailsDoc, this.mNavigationManager, true, this.mParentNode);
        }
    }
}
