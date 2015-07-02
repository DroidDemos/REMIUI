package com.google.android.finsky.billing.lightpurchase;

import android.accounts.Account;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.RootUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.DocUtils.OfferFilter;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.VoucherUtils;
import java.util.Iterator;
import java.util.List;

public class OfferResolutionActivity extends FragmentActivity implements OnClickListener, ErrorListener, Listener, OnDataChangedListener, RootUiElementNode {
    private Account mAccount;
    private DfeDetails mDfeDetails;
    private DfeToc mDfeToc;
    private Document mDoc;
    private FinskyEventLog mEventLog;
    private OfferFilter mOfferFilter;
    private final PlayStoreUiElement mUiElement;

    public static class AvailableOffer {
        public final Document doc;
        public final Offer offer;

        private AvailableOffer(Document doc, Offer offer) {
            this.doc = doc;
            this.offer = offer;
        }
    }

    public OfferResolutionActivity() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(780);
    }

    public static Intent createIntent(DfeToc dfeToc, Account account, String docid, Document doc, OfferFilter offerFilter) {
        Intent intent = new Intent(FinskyApp.get(), OfferResolutionActivity.class);
        intent.putExtra("OfferResolutionActivity.dfeToc", dfeToc);
        intent.putExtra("OfferResolutionActivity.account", account);
        intent.putExtra("OfferResolutionActivity.docid", docid);
        intent.putExtra("OfferResolutionActivity.doc", doc);
        if (offerFilter != null) {
            intent.putExtra("OfferResolutionActivity.offerFilter", offerFilter.name());
        }
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_purchase_offer_resolution);
        Intent intent = getIntent();
        this.mDfeToc = (DfeToc) intent.getParcelableExtra("OfferResolutionActivity.dfeToc");
        this.mAccount = (Account) intent.getParcelableExtra("OfferResolutionActivity.account");
        ((TextView) findViewById(R.id.title)).setText(R.string.offer_resolution_dialog_title);
        this.mEventLog = FinskyApp.get().getEventLogger(this.mAccount);
        String docid = intent.getStringExtra("OfferResolutionActivity.docid");
        this.mDoc = (Document) intent.getParcelableExtra("OfferResolutionActivity.doc");
        if (intent.hasExtra("OfferResolutionActivity.offerFilter")) {
            this.mOfferFilter = OfferFilter.valueOf(intent.getStringExtra("OfferResolutionActivity.offerFilter"));
        }
        if (savedInstanceState == null) {
            this.mEventLog.logPathImpression(0, this);
        }
        if (this.mDoc == null) {
            showLoading();
            this.mDfeDetails = new DfeDetails(FinskyApp.get().getDfeApi(this.mAccount.name), DfeUtils.createDetailsUrlFromId(docid), false, VoucherUtils.getVoucherIds(FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount)));
            this.mDfeDetails.addDataChangedListener(this);
            this.mDfeDetails.addErrorListener(this);
            return;
        }
        updateFromDoc();
    }

    protected void onStart() {
        super.onStart();
        if (this.mDfeDetails != null) {
            this.mDfeDetails.addDataChangedListener(this);
            this.mDfeDetails.addErrorListener(this);
        }
    }

    protected void onStop() {
        if (this.mDfeDetails != null) {
            this.mDfeDetails.removeDataChangedListener(this);
            this.mDfeDetails.removeErrorListener(this);
        }
        super.onStop();
    }

    private void updateFromDoc() {
        hideLoading();
        byte[] serverLogsCookie = this.mDoc.getServerLogsCookie();
        FinskyEventLog.setServerLogCookie(this.mUiElement, serverLogsCookie);
        this.mEventLog.logPathImpression(0, 781, this);
        ViewGroup container = (ViewGroup) findViewById(R.id.offers);
        container.removeAllViews();
        List<AvailableOffer> availableOffers = getAvailableOffers();
        applyOfferFilter(availableOffers);
        if (availableOffers.isEmpty()) {
            showError(getString(R.string.item_unavailable_message));
            return;
        }
        int backend = this.mDoc.getBackend();
        AccountLibrary accountLibrary = FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount);
        if (VoucherUtils.hasApplicableVouchers(this.mDoc, accountLibrary)) {
            if (this.mDoc.hasApplicableVoucherDescription()) {
                findViewById(R.id.voucher_section).setVisibility(0);
                ((ImageView) findViewById(R.id.voucher_icon)).setImageResource(CorpusResourceUtils.getRewardDrawable(backend));
                ColorStateList secondaryTextColor = CorpusResourceUtils.getSecondaryTextColor(this, backend);
                TextView voucherMessage = (TextView) findViewById(R.id.voucher_message);
                voucherMessage.setText(this.mDoc.getApplicableVoucherDescription());
                voucherMessage.setTextColor(secondaryTextColor);
            }
        }
        int offerCount = availableOffers.size();
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i < offerCount; i++) {
            AvailableOffer availableOffer = (AvailableOffer) availableOffers.get(i);
            ViewGroup entry = (ViewGroup) layoutInflater.inflate(R.layout.light_purchase_offer_resolution_entry, container, false);
            TextView price = (TextView) entry.findViewById(R.id.price);
            TextView fullPrice = (TextView) entry.findViewById(R.id.full_price);
            TextView byline = (TextView) entry.findViewById(R.id.byline);
            ((TextView) entry.findViewById(R.id.title)).setText(availableOffer.offer.formattedName);
            price.setText(availableOffer.offer.formattedAmount);
            price.setTextColor(CorpusResourceUtils.getSecondaryTextColor(this, backend));
            boolean hideSalePrices = FinskyApp.get().getExperiments().isEnabled("cl:billing.hide_sale_prices");
            if (!DocUtils.hasDiscount(availableOffer.offer) || hideSalePrices) {
                fullPrice.setVisibility(8);
            } else {
                fullPrice.setVisibility(0);
                fullPrice.setText(availableOffer.offer.formattedFullAmount);
                fullPrice.setPaintFlags(fullPrice.getPaintFlags() | 16);
                Resources resources = getResources();
                Object[] objArr = new Object[1];
                objArr[0] = availableOffer.offer.formattedFullAmount;
                fullPrice.setContentDescription(resources.getString(R.string.content_description_full_price, objArr));
                resources = getResources();
                objArr = new Object[1];
                objArr[0] = availableOffer.offer.formattedAmount;
                price.setContentDescription(resources.getString(R.string.content_description_current_price, objArr));
            }
            if (TextUtils.isEmpty(availableOffer.offer.formattedDescription)) {
                byline.setVisibility(8);
            } else {
                byline.setText(availableOffer.offer.formattedDescription);
            }
            entry.setTag(availableOffer);
            entry.setOnClickListener(this);
            container.addView(entry);
            if (i < offerCount - 1) {
                container.addView(layoutInflater.inflate(R.layout.light_purchase_separator, container, false));
            }
        }
    }

    private List<AvailableOffer> getAvailableOffers() {
        List<AvailableOffer> result = Lists.newArrayList();
        if (this.mDoc.hasSubscriptions()) {
            DocUtils.getSubscriptions(this.mDoc, this.mDfeToc, FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount));
            for (Document subscription : this.mDoc.getSubscriptionsList()) {
                Offer subscriptionOffer = subscription.getOffer(1);
                if (subscriptionOffer == null) {
                    FinskyLog.w("Skipping subscription doc, no PURCHASE offer: %s", subscription.getDocId());
                } else {
                    result.add(new AvailableOffer(subscription, subscriptionOffer));
                }
            }
        } else {
            for (Offer offer : this.mDoc.getAvailableOffers()) {
                if (offer.offerType != 2) {
                    result.add(new AvailableOffer(this.mDoc, offer));
                }
            }
        }
        return result;
    }

    private void applyOfferFilter(List<AvailableOffer> availableOffers) {
        if (this.mOfferFilter != null) {
            Iterator<AvailableOffer> iterator = availableOffers.iterator();
            while (iterator.hasNext()) {
                if (!this.mOfferFilter.matches(((AvailableOffer) iterator.next()).offer.offerType)) {
                    iterator.remove();
                }
            }
        }
    }

    private void showLoading() {
        findViewById(R.id.contents).setVisibility(8);
        findViewById(R.id.loading_indicator).setVisibility(0);
        this.mEventLog.logPathImpression(0, 213, this);
    }

    private void hideLoading() {
        findViewById(R.id.contents).setVisibility(0);
        findViewById(R.id.loading_indicator).setVisibility(8);
    }

    public void onClick(View view) {
        AvailableOffer availableOffer = (AvailableOffer) view.getTag();
        Intent intent = new Intent();
        intent.putExtra("OfferResolutionActivity.document", availableOffer.doc);
        intent.putExtra("OfferResolutionActivity.offer", ParcelableProto.forProto(availableOffer.offer));
        this.mEventLog.logClickEvent(782, availableOffer.doc.getServerLogsCookie(), this);
        setResult(-1, intent);
        finish();
    }

    public void onDataChanged() {
        this.mDoc = this.mDfeDetails.getDocument();
        if (this.mDoc == null) {
            showError(getString(R.string.item_unavailable_message));
        } else if (LibraryUtils.isAvailable(this.mDoc, FinskyApp.get().getToc(), FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount))) {
            updateFromDoc();
        } else {
            showError(getString(DocUtils.getAvailabilityRestrictionResourceId(this.mDoc)));
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        showError(ErrorStrings.get(this, volleyError));
    }

    private void showError(String message) {
        Builder builder = new Builder();
        builder.setMessage(message).setPositiveId(R.string.ok);
        builder.build().show(getSupportFragmentManager(), "OfferResolutionActivity.errorDialog");
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        setResult(0);
        finish();
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }

    public static AvailableOffer extractAvailableOffer(Intent resultIntent) {
        return new AvailableOffer((Document) resultIntent.getParcelableExtra("OfferResolutionActivity.document"), (Offer) ParcelableProto.getProtoFromIntent(resultIntent, "OfferResolutionActivity.offer"));
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return null;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyLog.wtf("Not using tree impressions.", new Object[0]);
    }

    public void startNewImpression() {
        FinskyLog.wtf("Not using impression id's.", new Object[0]);
    }

    public void flushImpression() {
        FinskyLog.wtf("Not using tree impressions.", new Object[0]);
    }

    public void finish() {
        if (this.mEventLog != null) {
            this.mEventLog.logPathImpression(0, 603, this);
        }
        super.finish();
    }
}
