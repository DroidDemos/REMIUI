package com.google.android.finsky.billing;

import android.accounts.Account;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo.InstrumentInfo;
import com.google.android.finsky.billing.giftcard.RedeemCodeResult;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.RedeemCodeActivity;
import com.google.android.finsky.layout.SeparatorLinearLayout;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.play.image.FifeImageView;
import java.util.List;

public class BillingProfileFragment extends BillingProfileBaseFragment {
    private TextView mActionsHeader;
    private View mActionsHeaderSeparator;
    private ViewGroup mActionsView;
    private ViewGroup mExistingInstrumentsView;
    private final PlayStoreUiElement mUiElement;

    public interface Listener {
        void onCancel();

        void onInstrumentSelected(String str);

        void onPromoCodeRedeemed(RedeemCodeResult redeemCodeResult);
    }

    public BillingProfileFragment() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(800);
    }

    public static BillingProfileFragment newInstance(Account account, String purchaseContextToken, Docid docid, int offerType) {
        Bundle args = new Bundle();
        args.putParcelable("BillingProfileFragment.account", account);
        args.putString("BillingProfileFragment.purchaseContextToken", purchaseContextToken);
        args.putParcelable("BillingProfileFragment.docid", ParcelableProto.forProto(docid));
        args.putInt("BillingProfileFragment.offer_type", offerType);
        BillingProfileFragment result = new BillingProfileFragment();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.mEventLog.logPathImpression(0, this);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mainView = (ViewGroup) inflater.inflate(R.layout.billing_profile, container, false);
        this.mExistingInstrumentsView = (ViewGroup) mainView.findViewById(R.id.existing_instruments);
        this.mActionsView = (ViewGroup) mainView.findViewById(R.id.actions);
        this.mProgressIndicator = mainView.findViewById(R.id.loading_indicator);
        this.mProfileView = mainView.findViewById(R.id.profile);
        this.mActionsHeader = (TextView) mainView.findViewById(R.id.actions_header);
        this.mActionsHeader.setText(getString(R.string.billing_profile_other_options).toUpperCase());
        this.mActionsHeaderSeparator = mainView.findViewById(R.id.actions_header_separator);
        return mainView;
    }

    private void addEntry(ViewGroup parent, ActionEntry actionEntry, boolean isDefault, String errorMessage) {
        View rowView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (errorMessage == null) {
            rowView = inflater.inflate(R.layout.billing_profile_entry, parent, false);
            if (isDefault) {
                rowView.findViewById(R.id.checkmark).setVisibility(0);
            }
            rowView.setOnClickListener(actionEntry.action);
        } else {
            rowView = inflater.inflate(R.layout.billing_profile_disabled_entry, parent, false);
            ((TextView) rowView.findViewById(R.id.byline)).setText(errorMessage);
        }
        ((TextView) rowView.findViewById(R.id.title)).setText(actionEntry.displayTitle);
        FifeImageView imageView = (FifeImageView) rowView.findViewById(R.id.image_icon);
        Image image = actionEntry.iconImage;
        if (image == null) {
            imageView.setVisibility(8);
        } else {
            imageView.setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
        }
        parent.addView(rowView);
    }

    protected void renderActions(List<ActionEntry> actionEntries) {
        this.mActionsView.removeAllViews();
        for (ActionEntry actionEntry : actionEntries) {
            addEntry(this.mActionsView, actionEntry, false, null);
        }
        if (this.mActionsView.getChildCount() > 0) {
            ((SeparatorLinearLayout) this.mActionsView.getChildAt(this.mActionsView.getChildCount() - 1)).hideSeparator();
        }
    }

    protected void renderInstruments(Instrument[] instruments) {
        this.mExistingInstrumentsView.removeAllViews();
        if (instruments.length == 0) {
            this.mActionsHeader.setVisibility(8);
            this.mActionsHeaderSeparator.setVisibility(8);
        } else {
            this.mActionsHeader.setVisibility(0);
            this.mActionsHeaderSeparator.setVisibility(0);
        }
        String defaultInstrumentId = this.mProfile.selectedExternalInstrumentId;
        for (Instrument instrument : instruments) {
            String error = instrument.disabledInfo.length > 0 ? instrument.disabledInfo[0].disabledMessageHtml : null;
            final String instrumentId = instrument.externalInstrumentId;
            boolean isDefault = instrumentId.equals(defaultInstrumentId);
            InstrumentInfo instrumentInfo = new InstrumentInfo();
            instrumentInfo.instrumentFamily = instrument.instrumentFamily;
            instrumentInfo.hasInstrumentFamily = true;
            instrumentInfo.isDefault = isDefault;
            instrumentInfo.hasIsDefault = true;
            final PlayStoreUiElementInfo clientCookie = new PlayStoreUiElementInfo();
            clientCookie.instrumentInfo = instrumentInfo;
            addEntry(this.mExistingInstrumentsView, new ActionEntry(instrument.displayTitle, instrument.iconImage, new OnClickListener() {
                public void onClick(View view) {
                    BillingProfileFragment.this.mEventLog.logClickEventWithClientCookie(802, clientCookie, BillingProfileFragment.this);
                    BillingProfileFragment.this.notifyListenerOnInstrumentSelected(instrumentId);
                }
            }), isDefault, error);
        }
        if (this.mExistingInstrumentsView.getChildCount() > 0) {
            ((SeparatorLinearLayout) this.mExistingInstrumentsView.getChildAt(this.mExistingInstrumentsView.getChildCount() - 1)).hideSeparator();
        }
    }

    protected void onInstrumentCreated(String instrumentId, String redeemedOfferHtml) {
        notifyListenerOnInstrumentSelected(instrumentId);
    }

    protected void onStoredValueAdded(String existingStoredValueInstrumentId, String redeemedOfferHtml) {
        if (existingStoredValueInstrumentId != null) {
            notifyListenerOnInstrumentSelected(existingStoredValueInstrumentId);
        } else {
            this.mProfileView.post(new Runnable() {
                public void run() {
                    BillingProfileFragment.this.requestBillingProfile();
                }
            });
        }
    }

    protected void onPromoCodeRedeemed(RedeemCodeResult redeemCodeResult) {
        notifyListenerOnPromoCodeRedeemed(redeemCodeResult);
    }

    protected void onFatalError(String errorMessageHtml) {
        renderProfile();
        Builder builder = new Builder();
        builder.setMessageHtml(errorMessageHtml).setPositiveId(R.string.ok).setCallback(this, 10, null);
        builder.build().show(getFragmentManager(), "BillingProfileFragment.errorDialog");
    }

    protected void notifyListenerOnInstrumentSelected(String instrumentId) {
        Listener listener = getListener();
        if (listener != null) {
            listener.onInstrumentSelected(instrumentId);
        }
    }

    protected void notifyListenerOnPromoCodeRedeemed(RedeemCodeResult redeemCodeResult) {
        Listener listener = getListener();
        if (listener != null) {
            listener.onPromoCodeRedeemed(redeemCodeResult);
        }
    }

    private void notifyListenerOnCancel() {
        Listener listener = getListener();
        if (listener != null) {
            listener.onCancel();
        }
    }

    private Listener getListener() {
        if (getActivity() instanceof Listener) {
            return (Listener) getActivity();
        }
        FinskyLog.wtf("No listener registered.", new Object[0]);
        return null;
    }

    protected int getCreditCardEventType() {
        return 320;
    }

    protected int getDcbEventType() {
        return 321;
    }

    protected int getRedeemEventType() {
        return 322;
    }

    protected int getTopupEventType() {
        return 323;
    }

    protected int getGenericInstrumentEventType() {
        return 324;
    }

    protected byte[] getBackgroundEventServerLogsCookie() {
        return null;
    }

    protected int getBillingProfileRequestEnum() {
        return 1;
    }

    protected void logLoading() {
        this.mEventLog.logPathImpression(0, 213, this);
    }

    protected void logScreen() {
        this.mEventLog.logPathImpression(0, 801, this);
    }

    protected void redeemCheckoutCode() {
        Bundle arguments = getArguments();
        startActivityForResult(RedeemCodeActivity.createBuyFlowIntent(this.mAccount.name, 3, (Docid) ParcelableProto.getProtoFromBundle(arguments, "BillingProfileFragment.docid"), arguments.getInt("BillingProfileFragment.offer_type")), 4);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 10) {
            notifyListenerOnCancel();
        } else {
            super.onPositiveClick(requestCode, extraArguments);
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 10) {
            notifyListenerOnCancel();
        } else {
            super.onNegativeClick(requestCode, extraArguments);
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return null;
    }
}
