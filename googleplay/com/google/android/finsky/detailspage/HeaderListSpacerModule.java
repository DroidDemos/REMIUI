package com.google.android.finsky.detailspage;

import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.HeroGraphicView;

public class HeaderListSpacerModule extends FinskyModule<Data> {

    protected class Data extends ModuleData {
        Document doc;
        int headerListSpacerHeight;

        protected Data() {
        }
    }

    public int getLayoutResId() {
        return R.layout.header_list_spacer_module;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null;
    }

    public void onRestoreModuleData(ModuleData moduleData) {
        super.onRestoreModuleData(moduleData);
        if (this.mModuleData != null) {
            ((Data) this.mModuleData).headerListSpacerHeight = HeroGraphicView.getDetailsHeroSpacerHeight(this.mContext, ((Data) this.mModuleData).doc, this.mUseWideLayout);
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).doc = detailsDoc;
            ((Data) this.mModuleData).headerListSpacerHeight = HeroGraphicView.getDetailsHeroSpacerHeight(this.mContext, ((Data) this.mModuleData).doc, this.mUseWideLayout);
        }
        if (hasDetailsLoaded) {
            ((Data) this.mModuleData).doc = detailsDoc;
            int newSpacerHeight = HeroGraphicView.getDetailsHeroSpacerHeight(this.mContext, ((Data) this.mModuleData).doc, this.mUseWideLayout);
            if (newSpacerHeight != ((Data) this.mModuleData).headerListSpacerHeight) {
                ((Data) this.mModuleData).headerListSpacerHeight = newSpacerHeight;
                this.mFinskyModuleController.refreshModule(this, true);
            }
        }
    }

    public void bindView(View view) {
        ((HeaderListSpacerModuleLayout) view).bind(((Data) this.mModuleData).headerListSpacerHeight);
    }
}
