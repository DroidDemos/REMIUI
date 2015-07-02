package com.google.android.finsky.detailspage;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.detailspage.CardClusterModuleLayout.CardBinder;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.play.layout.PlayCardViewBase;

public abstract class DfeListCardClusterModule extends FinskyModule<Data> implements OnClickListener, OnDataChangedListener, CardBinder, PlayStoreUiElementNode {
    private PlayStoreUiElement mUiElementProto;

    protected static class Data extends ModuleData {
        int backend;
        Image creatorAvatarImage;
        Image creatorBackgroundImage;
        Badge creatorBadge;
        Document creatorDoc;
        DfeList dfeList;
        String headerString;
        String moreBrowseUrl;
        String moreString;
        String subheaderString;
        boolean supportsTwoRows;

        protected Data() {
        }
    }

    protected abstract String getDfeListUrl(Document document, boolean z);

    protected abstract String getHeaderString(Document document, boolean z);

    protected abstract String getMoreBrowseUrl(Document document, boolean z);

    public DfeListCardClusterModule() {
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(400);
    }

    protected boolean requiresFullDocument() {
        return false;
    }

    protected boolean showsCreatorDataInHeader(Document detailsDoc) {
        return false;
    }

    protected String getMoreString(Document detailsDoc, boolean hasBrowseUrl) {
        if (hasBrowseUrl) {
            return this.mContext.getResources().getString(R.string.more_results_no_count);
        }
        return null;
    }

    protected boolean supportsTwoRows(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return false;
    }

    public int getLayoutResId() {
        return ((Data) this.mModuleData).creatorDoc != null ? R.layout.creator_avatar_card_cluster_module : R.layout.card_cluster_module;
    }

    public boolean readyForDisplay() {
        return (this.mModuleData == null || ((Data) this.mModuleData).dfeList == null || !((Data) this.mModuleData).dfeList.isReady() || ((Data) this.mModuleData).dfeList.getCount() == 0) ? false : true;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null && ((Data) this.mModuleData).dfeList != null && !((Data) this.mModuleData).dfeList.isReady()) {
            ((Data) this.mModuleData).dfeList.addDataChangedListener(this);
            ((Data) this.mModuleData).dfeList.startLoadItems();
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        Badge badge = null;
        if ((!requiresFullDocument() || hasDetailsLoaded) && this.mModuleData == null) {
            String dfeListUrl = getDfeListUrl(detailsDoc, hasDetailsLoaded);
            if (!TextUtils.isEmpty(dfeListUrl)) {
                boolean z;
                this.mModuleData = new Data();
                ((Data) this.mModuleData).creatorDoc = showsCreatorDataInHeader(detailsDoc) ? detailsDoc.getCreatorDoc() : null;
                ((Data) this.mModuleData).backend = detailsDoc.getBackend();
                ((Data) this.mModuleData).moreBrowseUrl = getMoreBrowseUrl(detailsDoc, hasDetailsLoaded);
                ((Data) this.mModuleData).headerString = getHeaderString(detailsDoc, hasDetailsLoaded);
                ((Data) this.mModuleData).supportsTwoRows = supportsTwoRows(detailsDoc, hasDetailsLoaded);
                Data data = (Data) this.mModuleData;
                if (TextUtils.isEmpty(((Data) this.mModuleData).moreBrowseUrl)) {
                    z = false;
                } else {
                    z = true;
                }
                data.moreString = getMoreString(detailsDoc, z);
                if (((Data) this.mModuleData).creatorDoc != null) {
                    Image image;
                    data = (Data) this.mModuleData;
                    if (((Data) this.mModuleData).creatorDoc.hasImages(14)) {
                        image = (Image) ((Data) this.mModuleData).creatorDoc.getImages(14).get(0);
                    } else {
                        image = null;
                    }
                    data.creatorBackgroundImage = image;
                    data = (Data) this.mModuleData;
                    if (((Data) this.mModuleData).creatorDoc.hasImages(4)) {
                        image = (Image) ((Data) this.mModuleData).creatorDoc.getImages(4).get(0);
                    } else {
                        image = null;
                    }
                    data.creatorAvatarImage = image;
                    data = (Data) this.mModuleData;
                    if (((Data) this.mModuleData).creatorDoc.hasCreatorBadges()) {
                        badge = ((Data) this.mModuleData).creatorDoc.getFirstCreatorBadge();
                    }
                    data.creatorBadge = badge;
                }
                ((Data) this.mModuleData).dfeList = new DfeList(this.mDfeApi, dfeListUrl, false);
                ((Data) this.mModuleData).dfeList.addDataChangedListener(this);
                ((Data) this.mModuleData).dfeList.startLoadItems();
            }
        }
    }

    public void bindView(View view) {
        if (!readyForDisplay()) {
            FinskyLog.wtf("Module is not ready for bindView", new Object[0]);
        } else if (((Data) this.mModuleData).creatorDoc == null) {
            ((CardClusterModuleLayout) view).bind(this, ((Data) this.mModuleData).backend, ((Data) this.mModuleData).headerString, null, ((Data) this.mModuleData).moreString, ((Data) this.mModuleData).supportsTwoRows, this);
        } else {
            ((CreatorAvatarCardClusterModuleLayout) view).bind(this, this.mBitmapLoader, ((Data) this.mModuleData).backend, ((Data) this.mModuleData).headerString, ((Data) this.mModuleData).subheaderString, ((Data) this.mModuleData).moreString, ((Data) this.mModuleData).supportsTwoRows, ((Data) this.mModuleData).creatorAvatarImage, ((Data) this.mModuleData).creatorBackgroundImage, ((Data) this.mModuleData).creatorBadge, this, this.mNavigationManager.getClickListener(((Data) this.mModuleData).creatorDoc, this));
        }
    }

    public void onDestroyModule() {
        if (this.mModuleData != null && ((Data) this.mModuleData).dfeList != null) {
            ((Data) this.mModuleData).dfeList.removeDataChangedListener(this);
        }
    }

    public void onDataChanged() {
        if (((Data) this.mModuleData).dfeList.getCount() != 0) {
            this.mFinskyModuleController.refreshModule(this, false);
        }
    }

    public void bindCard(PlayCardViewBase card, int position) {
        if (readyForDisplay()) {
            DfeList dfeList = ((Data) this.mModuleData).dfeList;
            Document data = position < dfeList.getCount() ? (Document) dfeList.getItem(position) : null;
            if (data != null) {
                PlayCardUtils.bindCard(card, data, dfeList.getContainerDocument() != null ? dfeList.getContainerDocument().getDocId() : dfeList.getInitialUrl(), this.mBitmapLoader, this.mNavigationManager, this.mParentNode);
                return;
            } else {
                card.clearCardState();
                return;
            }
        }
        FinskyLog.wtf("Module is not ready for bindCard", new Object[0]);
    }

    public void onClick(View view) {
        if (readyForDisplay()) {
            this.mNavigationManager.goBrowse(((Data) this.mModuleData).moreBrowseUrl, null, ((Data) this.mModuleData).backend, this.mDfeToc, null);
        } else {
            FinskyLog.wtf("Module is not ready to handle click", new Object[0]);
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
