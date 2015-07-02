package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.ExpandableUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.layout.PlayActionButton;

public class SubscriptionView extends RelativeLayout {
    private PlayActionButton mCancelButton;
    private CancelListener mCancelListener;
    private SubscriptionDateInfo mDateInfo;
    private View mDescriptionCollapser;
    private View mDescriptionExpander;
    private Document mDocument;
    private int mExpansionState;
    private TextView mSubscriptionDescription;
    private TextView mSubscriptionPrice;
    private TextView mSubscriptionRenewal;
    private TextView mSubscriptionStatus;
    private TextView mSubscriptionTitle;

    public interface CancelListener {
        void onCancel(Document document, LibrarySubscriptionEntry librarySubscriptionEntry);
    }

    public static class SubscriptionDateInfo {
        boolean canCancel;
        CharSequence renewalDescription;
        int statusResourceId;
    }

    public SubscriptionView(Context context) {
        this(context, null);
    }

    public SubscriptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mExpansionState = -1;
        this.mDateInfo = new SubscriptionDateInfo();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSubscriptionTitle = (TextView) findViewById(R.id.subscription_title);
        this.mSubscriptionPrice = (TextView) findViewById(R.id.subscription_price);
        this.mSubscriptionRenewal = (TextView) findViewById(R.id.subscription_renewal);
        this.mSubscriptionStatus = (TextView) findViewById(R.id.subscription_status);
        this.mCancelButton = (PlayActionButton) findViewById(R.id.subscription_cancel_button);
        this.mSubscriptionDescription = (TextView) findViewById(R.id.subscription_description);
        this.mDescriptionExpander = findViewById(R.id.description_expander);
        this.mDescriptionCollapser = findViewById(R.id.description_collapser);
    }

    public static void fillSubscriptionDateInfo(SubscriptionDateInfo info, LibrarySubscriptionEntry subscriptionDetails, Resources res) {
        long now = System.currentTimeMillis();
        String validUntil = DateUtils.formatShortDisplayDate(subscriptionDetails.getValidUntilTimestampMs());
        if (subscriptionDetails.isAutoRenewing) {
            if (now < subscriptionDetails.trialUntilTimestampMs) {
                info.renewalDescription = Html.fromHtml(res.getString(R.string.subscription_charges_on, new Object[]{DateUtils.formatShortDisplayDate(subscriptionDetails.trialUntilTimestampMs)}));
                info.statusResourceId = R.string.subscription_trial;
            } else {
                if (now < subscriptionDetails.getValidUntilTimestampMs()) {
                    info.renewalDescription = Html.fromHtml(res.getString(R.string.subscription_renews_on, new Object[]{validUntil}));
                } else {
                    info.renewalDescription = null;
                }
                info.statusResourceId = R.string.subscription_renewing;
            }
            info.canCancel = true;
            return;
        }
        info.renewalDescription = Html.fromHtml(res.getString(R.string.subscription_expires_on, new Object[]{validUntil}));
        info.statusResourceId = R.string.subscription_canceled;
        info.canCancel = false;
    }

    public void configure(Document doc, LibrarySubscriptionEntry subscriptionDetails, CancelListener cancelListener, Bundle savedState, PlayStoreUiElementNode parentNode) {
        configure(doc, subscriptionDetails, cancelListener, doc.getBackend(), savedState, parentNode);
    }

    public void configure(final Document doc, final LibrarySubscriptionEntry subscriptionDetails, CancelListener cancelListener, int backend, Bundle savedState, final PlayStoreUiElementNode parentNode) {
        this.mDocument = doc;
        this.mCancelListener = cancelListener;
        this.mSubscriptionTitle.setText(this.mDocument.getTitle());
        Offer offer = doc.getOffer(1);
        if (offer == null || offer.subscriptionTerms == null) {
            this.mSubscriptionPrice.setVisibility(8);
            FinskyLog.wtf("Document for %s does not contain a subscription offer or terms.", doc.getDocId());
        } else {
            String priceWithPeriod = offer.subscriptionTerms.formattedPriceWithRecurrencePeriod;
            if (TextUtils.isEmpty(priceWithPeriod)) {
                this.mSubscriptionPrice.setVisibility(8);
                FinskyLog.wtf("Document for %s does not contain a formatted price.", doc.getDocId());
            } else {
                this.mSubscriptionPrice.setVisibility(0);
                this.mSubscriptionPrice.setText(priceWithPeriod);
            }
        }
        this.mSubscriptionStatus.setTextColor(CorpusResourceUtils.getSecondaryTextColor(getContext(), backend));
        fillSubscriptionDateInfo(this.mDateInfo, subscriptionDetails, getContext().getResources());
        if (TextUtils.isEmpty(this.mDateInfo.renewalDescription)) {
            this.mSubscriptionRenewal.setVisibility(8);
        } else {
            this.mSubscriptionRenewal.setVisibility(0);
            this.mSubscriptionRenewal.setText(this.mDateInfo.renewalDescription);
        }
        this.mSubscriptionStatus.setText(this.mDateInfo.statusResourceId);
        if (this.mDateInfo.canCancel) {
            this.mCancelButton.setVisibility(0);
            this.mCancelButton.configure(backend, (int) R.string.cancel_subscription, new OnClickListener() {
                public void onClick(View v) {
                    FinskyApp.get().getEventLogger().logClickEvent(227, null, new GenericUiElementNode(242, doc.getServerLogsCookie(), null, parentNode));
                    SubscriptionView.this.mCancelListener.onCancel(doc, subscriptionDetails);
                }
            });
            setNextFocusRightId(this.mCancelButton.getId());
            this.mCancelButton.setNextFocusLeftId(getId());
        } else {
            this.mCancelButton.setVisibility(8);
            setNextFocusRightId(-1);
        }
        if (TextUtils.isEmpty(doc.getDescription())) {
            hideDescription();
            return;
        }
        this.mSubscriptionDescription.setText(doc.getDescription());
        if (this.mExpansionState < 0) {
            this.mExpansionState = ExpandableUtils.getSavedExpansionState(savedState, doc.getDocId(), 1);
        }
        if (this.mExpansionState == 2) {
            expandDescription();
        } else {
            collapseDescription();
        }
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (SubscriptionView.this.mExpansionState == 2) {
                    SubscriptionView.this.collapseDescription();
                    SubscriptionView.this.mExpansionState = 1;
                    return;
                }
                SubscriptionView.this.expandDescription();
                SubscriptionView.this.mExpansionState = 2;
            }
        });
    }

    private void hideDescription() {
        this.mSubscriptionDescription.setVisibility(8);
        this.mDescriptionCollapser.setVisibility(8);
        this.mDescriptionExpander.setVisibility(8);
    }

    private void expandDescription() {
        this.mDescriptionExpander.setVisibility(8);
        this.mSubscriptionDescription.setVisibility(0);
        this.mDescriptionCollapser.setVisibility(0);
    }

    private void collapseDescription() {
        this.mDescriptionExpander.setVisibility(0);
        this.mSubscriptionDescription.setVisibility(8);
        this.mDescriptionCollapser.setVisibility(8);
    }

    public void saveInstanceState(Bundle bundle) {
        String docId = this.mDocument.getDocId();
        if (!TextUtils.isEmpty(docId)) {
            ExpandableUtils.saveExpansionState(bundle, docId, this.mExpansionState);
        }
    }
}
