package com.google.android.finsky.detailspage;

import android.text.TextUtils;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;

public class FooterTextModule extends FinskyModule<Data> {

    protected static class Data extends ModuleData {
        String footerHtml;

        protected Data() {
        }
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null;
    }

    public int getLayoutResId() {
        return R.layout.footer_text_module;
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null && hasDetailsLoaded) {
            String footerHtml = dfeDetails.getFooterHtml();
            if (!TextUtils.isEmpty(footerHtml)) {
                this.mModuleData = new Data();
                ((Data) this.mModuleData).footerHtml = footerHtml;
            }
        }
    }

    public void bindView(View view) {
        FooterTextModuleLayout footerTextModuleLayout = (FooterTextModuleLayout) view;
        if (!footerTextModuleLayout.hasBinded()) {
            footerTextModuleLayout.bind(((Data) this.mModuleData).footerHtml);
        }
    }
}
