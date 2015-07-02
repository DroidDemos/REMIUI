package com.google.android.finsky.detailspage;

import android.content.Intent;
import android.view.View;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.detailspage.SecondaryActionsModuleLayout.SecondaryActionsClickListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.DocumentV2.PlusOneData;
import com.google.android.finsky.protos.PlusOne.PlusOneResponse;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.MyPeoplePageHelper;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.WishlistHelper;
import com.google.android.finsky.utils.WishlistHelper.WishlistStatusListener;

public class SecondaryActionsModule extends FinskyModule<Data> implements SecondaryActionsClickListener, PlayStoreUiElementNode, WishlistStatusListener {
    private PlayStoreUiElement mPlayStoreUiElement;

    protected static class Data extends ModuleData {
        public Document doc;
        public boolean isInWishlist;
        public boolean isPlusOned;
        public long plusOneCount;
        public boolean showPlusOne;
        public boolean showWishlist;

        protected Data() {
        }
    }

    public SecondaryActionsModule() {
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(1820);
    }

    public int getLayoutResId() {
        return R.layout.secondary_actions_module;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null;
    }

    public void onRestoreModuleData(ModuleData data) {
        super.onRestoreModuleData(data);
        if (this.mModuleData != null) {
            WishlistHelper.addWishlistStatusListener(this);
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (hasDetailsLoaded && this.mModuleData == null) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).doc = detailsDoc;
            ((Data) this.mModuleData).showWishlist = !WishlistHelper.shouldHideWishlistAction(detailsDoc, this.mDfeApi);
            if (((Data) this.mModuleData).showWishlist) {
                ((Data) this.mModuleData).isInWishlist = WishlistHelper.isInWishlist(detailsDoc, FinskyApp.get().getCurrentAccount());
            }
            ((Data) this.mModuleData).showPlusOne = socialDetailsDoc.hasPlusOneData();
            if (((Data) this.mModuleData).showPlusOne) {
                PlusOneData plusOneData = socialDetailsDoc.getPlusOneData();
                ((Data) this.mModuleData).isPlusOned = plusOneData.setByUser;
                ((Data) this.mModuleData).plusOneCount = plusOneData.total;
            }
            WishlistHelper.addWishlistStatusListener(this);
        }
    }

    public void bindView(View view) {
        ((SecondaryActionsModuleLayout) view).bind(((Data) this.mModuleData).showWishlist, ((Data) this.mModuleData).isInWishlist, ((Data) this.mModuleData).doc.getBackend(), true, ((Data) this.mModuleData).showPlusOne, ((Data) this.mModuleData).plusOneCount, ((Data) this.mModuleData).isPlusOned, this);
    }

    public void onDestroyModule() {
        WishlistHelper.removeWishlistStatusListener(this);
    }

    public void onWishlistStatusChanged(String docId, boolean isInWishlist, boolean isCommitted) {
        if (this.mModuleData != null && ((Data) this.mModuleData).doc.getDocId().equals(docId)) {
            ((Data) this.mModuleData).isInWishlist = isInWishlist;
            this.mFinskyModuleController.refreshModule(this, false);
        }
    }

    public void onWishlistClick(View view) {
        int playStoreUiElementType;
        if (((Data) this.mModuleData).isInWishlist) {
            playStoreUiElementType = 205;
        } else {
            playStoreUiElementType = 204;
        }
        FinskyApp.get().getEventLogger().logClickEvent(playStoreUiElementType, null, this);
        WishlistHelper.processWishlistClick(view, ((Data) this.mModuleData).doc, this.mDfeApi);
        FinskyApp.get().getEventLogger().logClickEvent(playStoreUiElementType, null, this);
    }

    public void onShareClick(View view) {
        Intent intent = IntentUtils.buildShareIntent(this.mContext, ((Data) this.mModuleData).doc);
        this.mContext.startActivity(Intent.createChooser(intent, this.mContext.getString(R.string.share_dialog_title, new Object[]{((Data) this.mModuleData).doc.getTitle()})));
        FinskyApp.get().getEventLogger().logClickEvent(202, null, this);
    }

    public void onPlusOneClick(View view) {
        Data data;
        boolean z;
        if (((Data) this.mModuleData).isPlusOned) {
            data = (Data) this.mModuleData;
            data.plusOneCount--;
        } else {
            data = (Data) this.mModuleData;
            data.plusOneCount++;
        }
        data = (Data) this.mModuleData;
        if (((Data) this.mModuleData).isPlusOned) {
            z = false;
        } else {
            z = true;
        }
        data.isPlusOned = z;
        this.mSocialDfeApi.setPlusOne(((Data) this.mModuleData).doc.getDocId(), ((Data) this.mModuleData).isPlusOned, new Listener<PlusOneResponse>() {
            public void onResponse(PlusOneResponse plusOneResponse) {
                SecondaryActionsModule.this.mSocialDfeApi.invalidateDetailsCache(SecondaryActionsModule.this.mDetailsUrl, true);
                MyPeoplePageHelper.onMutationOccurred(SecondaryActionsModule.this.mSocialDfeApi);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        sendPlusOneToggleAccessibilityEvent(((Data) this.mModuleData).isPlusOned, view);
        FinskyApp.get().getEventLogger().logClickEvent(208, null, this);
        this.mFinskyModuleController.refreshModule(this, false);
    }

    private void sendPlusOneToggleAccessibilityEvent(boolean setByUser, View view) {
        int eventTextId;
        if (setByUser) {
            eventTextId = R.string.accessibility_event_plus_one_toggle_on;
        } else {
            eventTextId = R.string.accessibility_event_plus_one_toggle_off;
        }
        UiUtils.sendAccessibilityEventWithText(this.mContext, this.mContext.getString(eventTextId), view);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
