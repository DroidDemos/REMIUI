package com.google.android.finsky.detailspage;

import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;

public class FooterSpacerModule extends FinskyModule<Data> {

    protected static class Data extends ModuleData {
        boolean isShowing;

        protected Data() {
        }
    }

    public int getLayoutResId() {
        return R.layout.footer_spacer_module;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null && ((Data) this.mModuleData).isShowing;
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).isShowing = true;
        }
        if (hasDetailsLoaded && ((Data) this.mModuleData).isShowing) {
            ((Data) this.mModuleData).isShowing = false;
            this.mFinskyModuleController.removeModule(this);
        }
    }

    public void bindView(View view) {
        ((FooterSpacerModuleLayout) view).bind(this.mContext.getResources().getDisplayMetrics().heightPixels - this.mContext.getResources().getDimensionPixelSize(R.dimen.hero_image_height));
    }
}
