package com.google.android.finsky.billing;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo.InstrumentInfo;
import com.google.android.finsky.billing.giftcard.RedeemCodeResult;
import com.google.android.finsky.billing.lightpurchase.billingprofile.BillingProfileSidecar;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.RedeemCodeActivity;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.layout.ForegroundRelativeLayout;
import java.util.List;

public class PaymentMethodsFragment extends BillingProfileBaseFragment {
    private static final Uri EDIT_FOOTER_URI;
    private ViewGroup mActionsContainer;
    private View mActionsView;
    private ViewGroup mInstrumentsContainer;
    private View mInstrumentsSwitcher;
    private View mInstrumentsView;
    private ViewGroup mParentDataView;
    private int mScrollPosition;
    private TextView mTitleView;
    private final PlayStoreUiElement mUiElement;

    public PaymentMethodsFragment() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(2620);
        this.mScrollPosition = 0;
    }

    static {
        EDIT_FOOTER_URI = Uri.parse((String) G.editPaymentMethodUrl.get());
    }

    public static PaymentMethodsFragment newInstance(Account account) {
        Bundle args = new Bundle();
        args.putParcelable("BillingProfileFragment.account", account);
        PaymentMethodsFragment result = new PaymentMethodsFragment();
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
        ViewGroup mainView = (ViewGroup) inflater.inflate(R.layout.payment_methods_fragment, container, false);
        this.mProgressIndicator = mainView.findViewById(R.id.loading_indicator);
        this.mErrorIndicator = mainView.findViewById(R.id.error_indicator);
        this.mProfileView = mainView.findViewById(R.id.profile);
        this.mTitleView = (TextView) mainView.findViewById(R.id.title);
        this.mInstrumentsContainer = (ViewGroup) mainView.findViewById(R.id.instruments_container);
        this.mActionsContainer = (ViewGroup) mainView.findViewById(R.id.actions_container);
        this.mInstrumentsView = mainView.findViewById(R.id.instruments);
        this.mActionsView = mainView.findViewById(R.id.actions);
        this.mInstrumentsSwitcher = mainView.findViewById(R.id.instruments_switcher);
        this.mInstrumentsSwitcher.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PaymentMethodsFragment.this.mEventLog.logClickEvent(2625, null, PaymentMethodsFragment.this);
                PaymentMethodsFragment.this.switchToInstrumentsView(true);
            }
        });
        mainView.findViewById(R.id.actions_switcher).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PaymentMethodsFragment.this.mEventLog.logClickEvent(2624, null, PaymentMethodsFragment.this);
                PaymentMethodsFragment.this.switchToActionsView(true);
            }
        });
        View editFooter = mainView.findViewById(R.id.edit_footer);
        if (FinskyApp.get().getExperiments().isEnabled("cl:billing.hide_edit_payment_my_account")) {
            editFooter.setVisibility(8);
        } else {
            editFooter.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    PaymentMethodsFragment.this.mEventLog.logClickEvent(2623, null, PaymentMethodsFragment.this);
                    PaymentMethodsFragment.this.startActivity(new Intent("android.intent.action.VIEW", PaymentMethodsFragment.EDIT_FOOTER_URI));
                }
            });
        }
        return mainView;
    }

    private void switchToInstrumentsView(boolean clickAction) {
        this.mEventLog.logPathImpression(0, 2621, this);
        this.mTitleView.setText(R.string.payment_methods);
        this.mInstrumentsView.setVisibility(0);
        if (clickAction) {
            this.mInstrumentsView.startAnimation(PlayAnimationUtils.getFadeInAnimation(getActivity(), 250, null));
            this.mActionsView.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getActivity(), 250, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    PaymentMethodsFragment.this.mActionsView.setVisibility(8);
                }
            }));
            if (this.mParentDataView != null && this.mScrollPosition != 0) {
                this.mParentDataView.scrollTo(0, this.mScrollPosition);
                return;
            }
            return;
        }
        this.mActionsView.setVisibility(8);
    }

    private void switchToActionsView(boolean clickAction) {
        this.mEventLog.logPathImpression(0, 2622, this);
        this.mTitleView.setText(R.string.add_payment_method);
        this.mActionsView.setVisibility(0);
        if (clickAction) {
            this.mActionsView.startAnimation(PlayAnimationUtils.getFadeInAnimation(getActivity(), 250, null));
            this.mInstrumentsView.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getActivity(), 250, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    PaymentMethodsFragment.this.mInstrumentsView.setVisibility(8);
                }
            }));
            if (this.mParentDataView != null) {
                this.mScrollPosition = this.mParentDataView.getScrollY();
                this.mParentDataView.scrollTo(0, 0);
                return;
            }
            return;
        }
        this.mInstrumentsView.setVisibility(8);
    }

    public void onDetach() {
        if (!((MainActivity) getActivity()).isStateSaved() && (isRemoving() || getParentFragment().isRemoving())) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this.mBillingProfileSidecar).commit();
            this.mBillingProfileSidecar = null;
        }
        super.onDetach();
    }

    protected void createBillingProfileSidecar() {
        this.mBillingProfileSidecar = (BillingProfileSidecar) getActivity().getSupportFragmentManager().findFragmentByTag("PaymentMethodsFragment.sidecar");
        if (this.mBillingProfileSidecar == null) {
            this.mBillingProfileSidecar = BillingProfileSidecar.newInstance(this.mAccount, this.mPurchaseContextToken);
            getActivity().getSupportFragmentManager().beginTransaction().add(this.mBillingProfileSidecar, "PaymentMethodsFragment.sidecar").commit();
        }
    }

    public void startActivity(Intent intent) {
        getParentFragment().startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        getParentFragment().startActivityForResult(intent, requestCode);
    }

    private void addEntry(ViewGroup parent, ActionEntry actionEntry, boolean isDefault, String errorMessage) {
        ForegroundRelativeLayout rowView = (ForegroundRelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_methods_row, parent, false);
        FifeImageView imageView = (FifeImageView) rowView.findViewById(R.id.image_icon);
        Image image = actionEntry.iconImage;
        if (image != null) {
            imageView.setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
        } else {
            imageView.setVisibility(4);
        }
        ((TextView) rowView.findViewById(R.id.title)).setText(actionEntry.displayTitle);
        if (isDefault) {
            rowView.findViewById(R.id.checkmark).setVisibility(0);
        }
        TextView bylineView = (TextView) rowView.findViewById(R.id.byline);
        if (TextUtils.isEmpty(errorMessage)) {
            bylineView.setVisibility(8);
        } else {
            bylineView.setText(errorMessage);
        }
        OnClickListener listener = actionEntry.action;
        if (listener != null) {
            rowView.setOnClickListener(listener);
        } else {
            rowView.setForeground(getResources().getDrawable(R.drawable.focus_overlay));
        }
        parent.addView(rowView);
    }

    protected void renderActions(List<ActionEntry> actionEntries) {
        this.mActionsContainer.removeAllViews();
        for (ActionEntry actionEntry : actionEntries) {
            addEntry(this.mActionsContainer, actionEntry, false, null);
        }
    }

    protected void renderInstruments(Instrument[] instruments) {
        this.mInstrumentsContainer.removeAllViews();
        if (instruments.length == 0) {
            this.mInstrumentsSwitcher.setVisibility(8);
            switchToActionsView(false);
            return;
        }
        this.mInstrumentsSwitcher.setVisibility(0);
        switchToInstrumentsView(false);
        String defaultInstrumentId = this.mProfile.selectedExternalInstrumentId;
        for (Instrument instrument : instruments) {
            String error = instrument.disabledInfo.length > 0 ? instrument.disabledInfo[0].disabledMessageHtml : null;
            boolean isDefault = instrument.externalInstrumentId.equals(defaultInstrumentId);
            InstrumentInfo instrumentInfo = new InstrumentInfo();
            instrumentInfo.instrumentFamily = instrument.instrumentFamily;
            instrumentInfo.hasInstrumentFamily = true;
            instrumentInfo.isDefault = isDefault;
            instrumentInfo.hasIsDefault = true;
            new PlayStoreUiElementInfo().instrumentInfo = instrumentInfo;
            addEntry(this.mInstrumentsContainer, new ActionEntry(instrument.displayTitle, instrument.iconImage, null), isDefault, error);
        }
    }

    public void onRetry() {
        requestBillingProfile();
    }

    public void setParentDataView(ViewGroup view) {
        this.mParentDataView = view;
    }

    protected void onInstrumentCreated(String instrumentId, String redeemedOfferHtml) {
        requestBillingProfile();
    }

    protected void onStoredValueAdded(String existingStoredValueInstrumentId, String redeemedOfferHtml) {
        requestBillingProfile();
    }

    protected void onPromoCodeRedeemed(RedeemCodeResult redeemCodeResult) {
        requestBillingProfile();
    }

    protected void onFatalError(String errorMessageHtml) {
        this.mErrorIndicator.setVisibility(0);
        this.mProgressIndicator.setVisibility(8);
        this.mProfileView.setVisibility(8);
        TextView errorMessageView = (TextView) this.mErrorIndicator.findViewById(R.id.error_msg);
        errorMessageView.setMovementMethod(LinkMovementMethod.getInstance());
        errorMessageView.setText(Html.fromHtml(errorMessageHtml));
        this.mErrorIndicator.findViewById(R.id.retry_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PaymentMethodsFragment.this.requestBillingProfile();
            }
        });
    }

    protected int getCreditCardEventType() {
        return 380;
    }

    protected int getDcbEventType() {
        return 381;
    }

    protected int getRedeemEventType() {
        return 382;
    }

    protected int getTopupEventType() {
        return 383;
    }

    protected int getGenericInstrumentEventType() {
        return 384;
    }

    protected byte[] getBackgroundEventServerLogsCookie() {
        return null;
    }

    protected int getBillingProfileRequestEnum() {
        return 4;
    }

    protected void logLoading() {
        this.mEventLog.logPathImpression(0, 213, this);
    }

    protected void logScreen() {
        this.mEventLog.logPathImpression(0, this);
    }

    protected void redeemCheckoutCode() {
        startActivityForResult(RedeemCodeActivity.createIntent(this.mAccount.name, 5), 4);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return null;
    }
}
