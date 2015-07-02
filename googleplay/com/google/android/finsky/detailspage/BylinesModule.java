package com.google.android.finsky.detailspage;

import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.DetailsBylinesSection;
import com.google.android.finsky.layout.DetailsBylinesSection.DetailsBylineEntry;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import java.util.List;

public class BylinesModule extends FinskyModule<Data> {

    protected static class Data extends ModuleData {
        List<DetailsBylineEntry> bylineEntryList;
        String title;
        Badge titleBadge;

        protected Data() {
        }
    }

    public int getLayoutResId() {
        return R.layout.details_bylines_container;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null;
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null && hasDetailsLoaded) {
            List<DetailsBylineEntry> detailsBylineEntries = DetailsBylinesSection.getBylineEntries(detailsDoc, this.mContext, this.mParentNode);
            if (!detailsBylineEntries.isEmpty()) {
                this.mModuleData = new Data();
                ((Data) this.mModuleData).title = DetailsBylinesSection.getBylinesTitle(detailsDoc, this.mContext);
                ((Data) this.mModuleData).titleBadge = DetailsBylinesSection.getBylinesTitleBadge(detailsDoc);
                ((Data) this.mModuleData).bylineEntryList = detailsBylineEntries;
            }
        }
    }

    public void bindView(View view) {
        DetailsBylinesSection bylinesSection = (DetailsBylinesSection) view;
        if (!bylinesSection.hasBinded()) {
            bylinesSection.bind(true, this.mBitmapLoader, ((Data) this.mModuleData).title, ((Data) this.mModuleData).titleBadge, ((Data) this.mModuleData).bylineEntryList, this.mParentNode);
        }
    }
}
