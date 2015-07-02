package com.google.android.finsky.layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.DocumentV2.PlusOneData;
import com.google.android.finsky.protos.PlusOne.PlusOneResponse;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.MyPeoplePageHelper;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.WishlistHelper;
import com.google.android.finsky.utils.WishlistHelper.WishlistStatusListener;
import java.text.NumberFormat;

public class DetailsSecondaryActionsSection extends LinearLayout implements OnClickListener, PlayStoreUiElementNode, WishlistStatusListener {
    private static final NumberFormat sCountFormatter;
    String mDetailsUrl;
    DfeApi mDfeApi;
    Document mDoc;
    PlayStoreUiElementNode mParentNode;
    private PlayStoreUiElement mPlayStoreUiElement;
    private View mPlusOneButton;
    private TextView mPlusOneIcon;
    private boolean mPlusOneSet;
    private long mPlusOneTotal;
    private View mShareButton;
    private boolean mShowWishlist;
    private View mWishlistButton;
    private ImageView mWishlistButtonIcon;

    static {
        sCountFormatter = NumberFormat.getIntegerInstance();
    }

    public DetailsSecondaryActionsSection(Context context) {
        this(context, null);
    }

    public DetailsSecondaryActionsSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(1820);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mWishlistButton = findViewById(R.id.wishlist_button);
        this.mWishlistButtonIcon = (ImageView) findViewById(R.id.wishlist_button_icon);
        this.mShareButton = findViewById(R.id.share_button);
        this.mPlusOneButton = findViewById(R.id.plus_one_button);
        this.mPlusOneIcon = (TextView) findViewById(R.id.plus_one_button_icon);
    }

    public void bind(Document doc, boolean hasDetailsLoaded, String detailsUrl, Bundle savedInstanceState, DfeApi dfeApi, PlayStoreUiElementNode parentNode) {
        if (hasDetailsLoaded) {
            setVisibility(0);
            this.mDoc = doc;
            this.mParentNode = parentNode;
            this.mDfeApi = dfeApi;
            this.mDetailsUrl = detailsUrl;
            this.mShowWishlist = !WishlistHelper.shouldHideWishlistAction(doc, dfeApi);
            if (this.mShowWishlist) {
                this.mWishlistButton.setVisibility(0);
                rebindWishlistButton(WishlistHelper.isInWishlist(doc, FinskyApp.get().getCurrentAccount()));
                this.mWishlistButton.setOnClickListener(this);
            } else {
                this.mWishlistButton.setVisibility(8);
            }
            this.mShareButton.setOnClickListener(this);
            if (hasDetailsLoaded && doc.hasPlusOneData()) {
                this.mPlusOneButton.setVisibility(0);
                PlusOneData plusOneData = doc.getPlusOneData();
                this.mPlusOneSet = savedInstanceState.getBoolean("DetailsSecondaryActionsSection.plusOneSet", plusOneData.setByUser);
                this.mPlusOneTotal = savedInstanceState.getLong("DetailsSecondaryActionsSection.plusOneCount", plusOneData.total);
                this.mPlusOneButton.setOnClickListener(this);
                rebindPlusOneButton();
                return;
            }
            this.mPlusOneButton.setVisibility(8);
            return;
        }
        setVisibility(8);
    }

    public void onSavedInstanceState(Bundle bundle) {
        bundle.putBoolean("DetailsSecondaryActionsSection.plusOneSet", this.mPlusOneSet);
        bundle.putLong("DetailsSecondaryActionsSection.plusOneCount", this.mPlusOneTotal);
    }

    public void onClick(View view) {
        boolean z = true;
        if (view == this.mWishlistButton) {
            int playStoreUiElementType;
            if (WishlistHelper.isInWishlist(this.mDoc, FinskyApp.get().getCurrentAccount())) {
                playStoreUiElementType = 205;
            } else {
                playStoreUiElementType = 204;
            }
            WishlistHelper.processWishlistClick(this, this.mDoc, this.mDfeApi);
            FinskyApp.get().getEventLogger().logClickEvent(playStoreUiElementType, null, this);
        }
        if (view == this.mShareButton) {
            Context context = getContext();
            context.startActivity(Intent.createChooser(IntentUtils.buildShareIntent(context, this.mDoc), context.getString(R.string.share_dialog_title, new Object[]{this.mDoc.getTitle()})));
            FinskyApp.get().getEventLogger().logClickEvent(202, null, this);
        }
        if (view == this.mPlusOneButton) {
            if (this.mPlusOneSet) {
                this.mPlusOneTotal--;
            } else {
                this.mPlusOneTotal++;
            }
            if (this.mPlusOneSet) {
                z = false;
            }
            this.mPlusOneSet = z;
            this.mDfeApi.setPlusOne(this.mDoc.getDocId(), this.mPlusOneSet, new Listener<PlusOneResponse>() {
                public void onResponse(PlusOneResponse plusOneResponse) {
                    DetailsSecondaryActionsSection.this.mDfeApi.invalidateDetailsCache(DetailsSecondaryActionsSection.this.mDetailsUrl, true);
                    MyPeoplePageHelper.onMutationOccurred(DetailsSecondaryActionsSection.this.mDfeApi);
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                }
            });
            rebindPlusOneButton();
            sendPlusOneToggleAccessibilityEvent(this.mPlusOneSet);
            FinskyApp.get().getEventLogger().logClickEvent(208, null, this);
        }
    }

    private void rebindWishlistButton(boolean isInWishlist) {
        if (isInWishlist) {
            this.mWishlistButtonIcon.setImageResource(CorpusResourceUtils.getWishlistOnDrawable(this.mDoc.getBackend()));
            this.mWishlistButton.setContentDescription(getContext().getString(R.string.content_description_wishlist_remove));
            return;
        }
        this.mWishlistButtonIcon.setImageResource(R.drawable.ic_menu_wish_off);
        this.mWishlistButton.setContentDescription(getContext().getString(R.string.content_description_wishlist_add));
    }

    private void rebindPlusOneButton() {
        Resources resources = getResources();
        long displayCount = this.mPlusOneTotal == 0 ? 1 : this.mPlusOneTotal;
        this.mPlusOneIcon.setText(resources.getString(R.string.details_secondary_action_plus_one_icon_text, new Object[]{sCountFormatter.format(displayCount)}));
        if (this.mPlusOneSet) {
            this.mPlusOneIcon.setBackgroundResource(R.drawable.plus_one_icon_on);
            this.mPlusOneIcon.setTextColor(resources.getColor(R.color.white));
            this.mPlusOneButton.setContentDescription(resources.getString(R.string.content_description_details_secondary_action_plus_one_set, new Object[]{Long.valueOf(this.mPlusOneTotal)}));
            return;
        }
        this.mPlusOneIcon.setBackgroundResource(R.drawable.plus_one_icon_off);
        this.mPlusOneIcon.setTextColor(resources.getColor(R.color.play_fg_primary));
        this.mPlusOneButton.setContentDescription(resources.getString(R.string.content_description_details_secondary_action_plus_one, new Object[]{Long.valueOf(this.mPlusOneTotal)}));
    }

    public void onWishlistStatusChanged(String docId, boolean isInWishlist, boolean isCommitted) {
        if (this.mShowWishlist && docId.equals(this.mDoc.getDocId())) {
            rebindWishlistButton(isInWishlist);
        }
    }

    private void sendPlusOneToggleAccessibilityEvent(boolean setByUser) {
        int eventTextId;
        if (setByUser) {
            eventTextId = R.string.accessibility_event_plus_one_toggle_on;
        } else {
            eventTextId = R.string.accessibility_event_plus_one_toggle_off;
        }
        Context context = getContext();
        UiUtils.sendAccessibilityEventWithText(context, context.getString(eventTextId), this);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        WishlistHelper.addWishlistStatusListener(this);
    }

    public void onDetachedFromWindow() {
        WishlistHelper.removeWishlistStatusListener(this);
        super.onDetachedFromWindow();
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
