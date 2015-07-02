package com.google.android.finsky.billing.lightpurchase.purchasesteps;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.Purchase.ApplicableVoucher;
import com.google.android.finsky.protos.Purchase.ClientCart;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;

public class CartDetailsStep extends StepFragment<PurchaseFragment> implements OnClickListener, PlayStoreUiElementNode {
    private int mBackend;
    private ClientCart mCart;
    private boolean mContinueButtonConfirmsPurchase;
    private boolean mContinueButtonShowsInstrumentManager;
    private FinskyEventLog mEventLog;
    private boolean mExpanded;
    private View mHeader;
    private TextView mPaymentSettingsView;
    private TextView mPriceView;
    private View mPurchaseDetailsView;
    private TextView mRedeemView;
    private View mSelectedVoucherView;
    private final PlayStoreUiElement mUiElement;

    public CartDetailsStep() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(710);
    }

    public static CartDetailsStep newInstance(int backend, ClientCart cart, boolean continueToInstrumentManager) {
        Bundle args = new Bundle();
        CartDetailsStep result = new CartDetailsStep();
        args.putInt("CartDetailsStep.backend", backend);
        args.putParcelable("CartDetailsStep.cart", ParcelableProto.forProto(cart));
        args.putBoolean("CartDetailsStep.continueToInstrumentManager", continueToInstrumentManager);
        result.setArguments(args);
        result.mCart = cart;
        return result;
    }

    public String getContinueButtonLabel(Resources resources) {
        return this.mCart.buttonText;
    }

    public boolean allowContinueButtonIcon() {
        return this.mContinueButtonConfirmsPurchase;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.mBackend = args.getInt("CartDetailsStep.backend");
        this.mCart = (ClientCart) ParcelableProto.getProtoFromBundle(args, "CartDetailsStep.cart");
        this.mContinueButtonShowsInstrumentManager = args.getBoolean("CartDetailsStep.continueToInstrumentManager");
        if (savedInstanceState != null) {
            this.mExpanded = savedInstanceState.getBoolean("CartDetailsStep.expanded");
        }
        this.mEventLog = FinskyApp.get().getEventLogger(((PurchaseFragment) getMultiStepFragment()).getAccount());
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("CartDetailsStep.expanded", this.mExpanded);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ColorStateList corpusColor = CorpusResourceUtils.getSecondaryTextColor(getActivity(), this.mBackend);
        int highlightColor = corpusColor.getDefaultColor();
        View mainView = inflater.inflate(R.layout.light_purchase_cart, container, false);
        TextView titleView = (TextView) mainView.findViewById(R.id.item_title);
        this.mPriceView = (TextView) mainView.findViewById(R.id.item_price);
        TextView instrumentView = (TextView) mainView.findViewById(R.id.instrument);
        TextView instrumentSetupView = (TextView) mainView.findViewById(R.id.instrument_setup_message);
        TextView accountView = (TextView) mainView.findViewById(R.id.account);
        accountView.setVisibility(0);
        accountView.setText(((PurchaseFragment) getMultiStepFragment()).getAccount().name);
        this.mPurchaseDetailsView = mainView.findViewById(R.id.purchase_details);
        TextView footerView = (TextView) mainView.findViewById(R.id.footer);
        this.mPaymentSettingsView = (TextView) mainView.findViewById(R.id.payment_settings);
        this.mPaymentSettingsView.setOnClickListener(this);
        this.mPaymentSettingsView.setTextColor(highlightColor);
        View redeemSeparator = mainView.findViewById(R.id.redeem_separator);
        this.mRedeemView = (TextView) mainView.findViewById(R.id.redeem);
        this.mRedeemView.setOnClickListener(this);
        this.mRedeemView.setTextColor(highlightColor);
        this.mHeader = mainView.findViewById(R.id.header);
        titleView.setText(this.mCart.title);
        this.mPriceView.setText(this.mCart.formattedPrice);
        this.mPriceView.setTextColor(corpusColor);
        TextView priceBylineView = (TextView) mainView.findViewById(R.id.price_byline);
        if (this.mCart.hasPriceByline) {
            priceBylineView.setText(this.mCart.priceByline);
            priceBylineView.setTextColor(corpusColor);
            priceBylineView.setVisibility(0);
        } else {
            priceBylineView.setVisibility(8);
        }
        Instrument instrument = this.mCart.instrument;
        if (instrument != null) {
            instrumentView.setText(instrument.displayTitle);
            instrumentView.setVisibility(0);
            if (instrument.disabledInfo.length > 0) {
                TextView instrumentErrorView = (TextView) mainView.findViewById(R.id.instrument_error_message);
                instrumentErrorView.setText(Html.fromHtml(instrument.disabledInfo[0].disabledMessageHtml));
                instrumentErrorView.setMovementMethod(LinkMovementMethod.getInstance());
                instrumentErrorView.setVisibility(0);
            } else {
                populateContainerWithTextViews(inflater, (ViewGroup) mainView.findViewById(R.id.extended_detail_messages), R.layout.light_purchase_cart_extended_detail_message, this.mCart.extendedDetailHtml, highlightColor);
                accountView.setText(((PurchaseFragment) getMultiStepFragment()).getAccount().name);
                this.mContinueButtonConfirmsPurchase = !this.mContinueButtonShowsInstrumentManager;
            }
        } else {
            instrumentView.setVisibility(8);
            instrumentSetupView.setText(BillingUtils.parseHtmlAndColorizeEm(this.mCart.addInstrumentPromptHtml, highlightColor));
            instrumentSetupView.setMovementMethod(LinkMovementMethod.getInstance());
            instrumentSetupView.setVisibility(0);
        }
        populateContainerWithTextViews(inflater, (ViewGroup) mainView.findViewById(R.id.detail_messages), R.layout.light_purchase_cart_detail_message, this.mCart.detailHtml, highlightColor);
        if (this.mCart.hasFooterHtml) {
            footerView.setVisibility(0);
            footerView.setText(BillingUtils.parseHtmlAndColorizeEm(this.mCart.footerHtml, highlightColor));
            footerView.setMovementMethod(LinkMovementMethod.getInstance());
            footerView.setLinkTextColor(footerView.getTextColors());
        } else {
            footerView.setVisibility(8);
        }
        if (this.mCart.applicableVouchers != null && this.mCart.applicableVouchers.length > 0) {
            populateVoucherViews(inflater, mainView, highlightColor);
        }
        boolean isExpandable = true;
        if (FinskyApp.get().getExperiments(((PurchaseFragment) getMultiStepFragment()).getAccount().name).isEnabled("cl:billing.cart_details_old_expander")) {
            if (instrument == null || instrument.disabledInfo.length != 0) {
                isExpandable = false;
            } else {
                redeemSeparator.setVisibility(8);
                this.mRedeemView.setVisibility(8);
            }
        }
        if (isExpandable) {
            this.mHeader.setOnClickListener(this);
            updateExpansion();
        }
        return mainView;
    }

    private void populateVoucherViews(LayoutInflater inflater, View mainView, int highlightColor) {
        ViewGroup voucherContainer = (ViewGroup) mainView.findViewById(R.id.vouchers);
        voucherContainer.setVisibility(0);
        ApplicableVoucher selectedVoucher = null;
        for (ApplicableVoucher applicableVoucher : this.mCart.applicableVouchers) {
            View rowView = inflater.inflate(R.layout.light_purchase_cart_voucher_entry, voucherContainer, false);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            title.setText(applicableVoucher.doc.title);
            if (!applicableVoucher.applied) {
                final String newVoucherId = applicableVoucher.doc.docid;
                rowView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        CartDetailsStep.this.mEventLog.logClickEvent(718, null, CartDetailsStep.this);
                        ((PurchaseFragment) CartDetailsStep.this.getMultiStepFragment()).switchVoucher(newVoucherId);
                    }
                });
            } else if (selectedVoucher != null) {
                throw new IllegalArgumentException("Multiple applied vouchers is not supported");
            } else {
                selectedVoucher = applicableVoucher;
                title.setTypeface(title.getTypeface(), 1);
                rowView.findViewById(R.id.checkmark).setVisibility(0);
                rowView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        CartDetailsStep.this.mEventLog.logClickEvent(719, null, CartDetailsStep.this);
                        ((PurchaseFragment) CartDetailsStep.this.getMultiStepFragment()).switchVoucher(null);
                    }
                });
            }
            voucherContainer.addView(rowView);
        }
        TextView voucherHeader = (TextView) mainView.findViewById(R.id.voucher_header);
        voucherHeader.setVisibility(0);
        voucherHeader.setTextColor(highlightColor);
        voucherHeader.setText(selectedVoucher == null ? R.string.voucher_section_none_selected : R.string.voucher_section_has_selected);
        mainView.findViewById(R.id.voucher_separator).setVisibility(0);
        if (selectedVoucher != null) {
            Object[] objArr;
            this.mSelectedVoucherView = mainView.findViewById(R.id.selected_voucher_container);
            this.mSelectedVoucherView.setVisibility(0);
            this.mSelectedVoucherView.setOnClickListener(this);
            ((TextView) this.mSelectedVoucherView.findViewById(R.id.selected_voucher_title)).setText(selectedVoucher.doc.title);
            TextView discountPrice = (TextView) this.mSelectedVoucherView.findViewById(R.id.selected_voucher_discount_price);
            if (TextUtils.isEmpty(selectedVoucher.formattedDiscountAmount)) {
                discountPrice.setVisibility(8);
            } else {
                objArr = new Object[1];
                objArr[0] = selectedVoucher.formattedDiscountAmount;
                discountPrice.setText(getString(R.string.cart_discounted_price, objArr));
            }
            TextView originalPrice = (TextView) this.mSelectedVoucherView.findViewById(R.id.selected_voucher_original_price);
            if (TextUtils.isEmpty(this.mCart.formattedOriginalPrice)) {
                originalPrice.setVisibility(8);
                return;
            }
            objArr = new Object[1];
            objArr[0] = this.mCart.formattedOriginalPrice;
            originalPrice.setText(getString(R.string.cart_original_price, objArr));
        }
    }

    public void onResume() {
        super.onResume();
        Context context = this.mHeader.getContext();
        UiUtils.sendAccessibilityEventWithText(context, context.getString(R.string.purchase_flow_start_description, new Object[]{this.mCart.title, this.mCart.formattedPrice}), this.mHeader);
    }

    private void populateContainerWithTextViews(LayoutInflater inflater, ViewGroup container, int textViewLayoutId, String[] messagesHtml, int highlightColor) {
        int i = 0;
        for (String messageHtml : messagesHtml) {
            TextView detailHtmlView = (TextView) inflater.inflate(textViewLayoutId, container, false);
            detailHtmlView.setText(BillingUtils.parseHtmlAndColorizeEm(messageHtml, highlightColor));
            detailHtmlView.setMovementMethod(LinkMovementMethod.getInstance());
            detailHtmlView.setLinkTextColor(detailHtmlView.getTextColors());
            container.addView(detailHtmlView);
        }
        if (messagesHtml.length <= 0) {
            i = 8;
        }
        container.setVisibility(i);
    }

    private void updateExpansion() {
        int i;
        int i2 = 8;
        View view = this.mPurchaseDetailsView;
        if (this.mExpanded) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        this.mPriceView.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.mExpanded ? CorpusResourceUtils.getMenuExpanderMaximized(this.mBackend) : CorpusResourceUtils.getMenuExpanderMinimized(this.mBackend), 0);
        if (this.mSelectedVoucherView != null) {
            View view2 = this.mSelectedVoucherView;
            if (!this.mExpanded) {
                i2 = 0;
            }
            view2.setVisibility(i2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mPriceView.getText()).append(", ").append(getString(this.mExpanded ? R.string.content_description_toggle_collapse : R.string.content_description_toggle_expand));
        this.mPriceView.setContentDescription(sb.toString());
    }

    private void toggleExpansion() {
        this.mExpanded = !this.mExpanded;
        if (this.mExpanded) {
            this.mEventLog.logPathImpression(0, 715, this);
        }
        updateExpansion();
    }

    public void onClick(View view) {
        if (view == this.mHeader) {
            logClick(713);
            toggleExpansion();
        } else if (view == this.mPaymentSettingsView) {
            logClick(714);
            ((PurchaseFragment) getMultiStepFragment()).launchBillingProfile();
        } else if (view == this.mRedeemView) {
            logClick(716);
            ((PurchaseFragment) getMultiStepFragment()).launchRedeem();
        } else if (view == this.mSelectedVoucherView) {
            logClick(717);
            toggleExpansion();
        }
    }

    public void onContinueButtonClicked() {
        if (this.mContinueButtonShowsInstrumentManager) {
            logClick(712);
            ((PurchaseFragment) getMultiStepFragment()).switchToInstrumentManager();
        } else if (this.mContinueButtonConfirmsPurchase) {
            logClick(711);
            ((PurchaseFragment) getMultiStepFragment()).confirmPurchase();
        } else {
            logClick(712);
            ((PurchaseFragment) getMultiStepFragment()).launchBillingProfile();
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }
}
