package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.DetailsTextSection.ExpandedData;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.play.utils.UrlSpanUtils.Listener;
import java.util.List;

public abstract class TextModule extends FinskyModule<Data> implements OnClickListener, Listener {
    private ExpandedData mExpandedData;

    protected static class Data extends ModuleData {
        int backend;
        Integer backgroundFillColor;
        CharSequence body;
        String bodyHeader;
        CharSequence callout;
        int calloutGravity;
        Badge creatorBadge;
        int docType;
        boolean hideWhatsNewInCollapsed;
        List<Pair<Image, String>> iconDescriptionPairs;
        boolean restrictCalloutMaxLines;
        CharSequence translatedBody;
        CharSequence whatsNew;

        protected Data() {
        }
    }

    protected abstract Data getData(Document document, boolean z);

    protected ExpandedData getExpandedData(Document detailsDoc, boolean hasDetailsLoaded, Data moduleData) {
        if (!hasDetailsLoaded) {
            return null;
        }
        ExpandedData data = new ExpandedData();
        data.backend = detailsDoc.getBackend();
        data.title = detailsDoc.getTitle();
        data.callout = moduleData.callout;
        data.calloutGravity = moduleData.calloutGravity;
        data.bodyHeader = moduleData.bodyHeader;
        data.body = moduleData.body;
        data.translatedBody = moduleData.translatedBody;
        data.whatsNewHeader = this.mContext.getResources().getString(R.string.details_whats_new).toUpperCase();
        data.whatsNew = moduleData.whatsNew;
        boolean z = (moduleData.hideWhatsNewInCollapsed || TextUtils.isEmpty(moduleData.whatsNew)) ? false : true;
        data.promoteWhatsNew = z;
        return data;
    }

    public int getLayoutResId() {
        return R.layout.text_module;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null;
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null) {
            this.mModuleData = getData(detailsDoc, hasDetailsLoaded);
        }
        if (this.mExpandedData == null && this.mModuleData != null) {
            this.mExpandedData = getExpandedData(detailsDoc, hasDetailsLoaded, (Data) this.mModuleData);
        }
    }

    public void bindView(View view) {
        ((TextModuleLayout) view).bind(((Data) this.mModuleData).backend, ((Data) this.mModuleData).docType, ((Data) this.mModuleData).callout, ((Data) this.mModuleData).calloutGravity, ((Data) this.mModuleData).restrictCalloutMaxLines, ((Data) this.mModuleData).bodyHeader, ((Data) this.mModuleData).body, ((Data) this.mModuleData).translatedBody, ((Data) this.mModuleData).whatsNew, ((Data) this.mModuleData).hideWhatsNewInCollapsed, ((Data) this.mModuleData).creatorBadge, ((Data) this.mModuleData).iconDescriptionPairs, ((Data) this.mModuleData).backgroundFillColor, this, this, this.mBitmapLoader);
    }

    public void onClick(View view) {
        if (this.mExpandedData != null) {
            this.mNavigationManager.goToExpandedDescription(this.mExpandedData, this.mDfeToc);
        }
    }

    public void onClick(View view, String url) {
        Context context = view.getContext();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.setPackage(context.getPackageName());
        if (context.getPackageManager().resolveActivity(intent, 65536) != null) {
            this.mNavigationManager.handleDeepLink(url, null);
            return;
        }
        intent.setPackage(null);
        context.startActivity(intent);
    }
}
