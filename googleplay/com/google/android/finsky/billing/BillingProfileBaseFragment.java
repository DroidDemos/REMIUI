package com.google.android.finsky.billing;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.giftcard.RedeemCodeResult;
import com.google.android.finsky.billing.instrumentmanager.InstrumentManagerActivity;
import com.google.android.finsky.billing.lightpurchase.billingprofile.BillingProfileSidecar;
import com.google.android.finsky.billing.lightpurchase.billingprofile.Dcb2ProvisioningSidecar;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.AddCreditCardActivity;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.AddDcb2Activity;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.AddDcb3Activity;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.AddGenericInstrumentActivity;
import com.google.android.finsky.billing.storedvalue.StoredValueTopUpActivity;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.BillingProfileProtos.BillingProfile;
import com.google.android.finsky.protos.BillingProfileProtos.BillingProfileOption;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.protos.CommonDevice.GenericInstrument;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.CommonDevice.TopupInfo;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentMetadata;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;
import java.util.Map;

public abstract class BillingProfileBaseFragment extends Fragment implements Listener, PlayStoreUiElementNode {
    protected Account mAccount;
    private final BillingProfileListener mBillingProfileListener;
    protected BillingProfileSidecar mBillingProfileSidecar;
    private final CarrierBillingProvisioningListener mCarrierBillingProvisioningListener;
    private Dcb2ProvisioningSidecar mDcb2ProvisioningSidecar;
    protected View mErrorIndicator;
    protected FinskyEventLog mEventLog;
    private int mLastBillingProfileStateInstance;
    private int mLastCarrierBillingStateInstance;
    protected BillingProfile mProfile;
    private boolean mProfileDirty;
    protected View mProfileView;
    private ProgressDialogFragment mProgressFragment;
    protected View mProgressIndicator;
    protected String mPurchaseContextToken;
    private String mStoredValueInstrumentId;

    protected static class ActionEntry {
        public final OnClickListener action;
        public final String displayTitle;
        public final Image iconImage;

        public ActionEntry(BillingProfileOption option, OnClickListener action) {
            this(option.displayTitle, option.iconImage, action);
        }

        public ActionEntry(String displayTitle, Image iconImage, OnClickListener action) {
            this.displayTitle = displayTitle;
            this.iconImage = iconImage;
            this.action = action;
        }
    }

    private class BillingProfileListener implements SidecarFragment.Listener {
        private BillingProfileListener() {
        }

        public void onStateChange(SidecarFragment fragment) {
            if (fragment.getStateInstance() != BillingProfileBaseFragment.this.mLastBillingProfileStateInstance) {
                BillingProfileBaseFragment.this.mLastBillingProfileStateInstance = fragment.getStateInstance();
                BillingProfileBaseFragment.this.mProgressIndicator.setVisibility(8);
                if (BillingProfileBaseFragment.this.mErrorIndicator != null) {
                    BillingProfileBaseFragment.this.mErrorIndicator.setVisibility(8);
                }
                BillingProfileBaseFragment.this.mProfileView.setVisibility(8);
                switch (fragment.getState()) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        BillingProfileBaseFragment.this.requestBillingProfile();
                        return;
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        BillingProfileBaseFragment.this.showLoading();
                        return;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        BillingProfileBaseFragment.this.handleSuccess(BillingProfileBaseFragment.this.mBillingProfileSidecar.getBillingProfile());
                        return;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        BillingProfileBaseFragment.this.handleError();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private class CarrierBillingProvisioningListener implements SidecarFragment.Listener {
        private CarrierBillingProvisioningListener() {
        }

        public void onStateChange(SidecarFragment fragment) {
            if (fragment.getStateInstance() != BillingProfileBaseFragment.this.mLastCarrierBillingStateInstance) {
                BillingProfileBaseFragment.this.mLastCarrierBillingStateInstance = fragment.getStateInstance();
                switch (fragment.getState()) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        hideProgressDialog();
                        return;
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        if (BillingProfileBaseFragment.this.mProgressFragment == null) {
                            BillingProfileBaseFragment.this.mProgressFragment = ProgressDialogFragment.newInstance(BillingProfileBaseFragment.this.getString(com.android.vending.R.string.contacting_carrier, BillingProfileBaseFragment.this.mDcb2ProvisioningSidecar.getCarrierName()));
                            BillingProfileBaseFragment.this.mProgressFragment.show(BillingProfileBaseFragment.this.getFragmentManager(), "BillingProfileFragment.carrierBillingProgressFragment");
                            return;
                        }
                        return;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        hideProgressDialog();
                        if (BillingProfileBaseFragment.this.mDcb2ProvisioningSidecar.isProvisioned()) {
                            BillingProfileBaseFragment.this.addDcb2();
                        } else {
                            BillingProfileBaseFragment.this.onDcbError(BillingProfileBaseFragment.this.getString(com.android.vending.R.string.not_provisioned_error, BillingProfileBaseFragment.this.mDcb2ProvisioningSidecar.getCarrierName()));
                        }
                        removeFragment();
                        return;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        hideProgressDialog();
                        BillingProfileBaseFragment.this.onDcbError(BillingProfileBaseFragment.this.getString(com.android.vending.R.string.carrier_network_unavailable_error));
                        removeFragment();
                        return;
                    default:
                        return;
                }
            }
        }

        private void hideProgressDialog() {
            if (BillingProfileBaseFragment.this.mProgressFragment != null) {
                BillingProfileBaseFragment.this.mProgressFragment.dismiss();
                BillingProfileBaseFragment.this.mProgressFragment = null;
            }
        }

        private void removeFragment() {
            BillingProfileBaseFragment.this.mDcb2ProvisioningSidecar.setListener(null);
            BillingProfileBaseFragment.this.getFragmentManager().beginTransaction().remove(BillingProfileBaseFragment.this.mDcb2ProvisioningSidecar).commit();
        }
    }

    protected abstract byte[] getBackgroundEventServerLogsCookie();

    protected abstract int getBillingProfileRequestEnum();

    protected abstract int getCreditCardEventType();

    protected abstract int getDcbEventType();

    protected abstract int getGenericInstrumentEventType();

    protected abstract int getRedeemEventType();

    protected abstract int getTopupEventType();

    protected abstract void logLoading();

    protected abstract void logScreen();

    protected abstract void onFatalError(String str);

    protected abstract void onInstrumentCreated(String str, String str2);

    protected abstract void onPromoCodeRedeemed(RedeemCodeResult redeemCodeResult);

    protected abstract void onStoredValueAdded(String str, String str2);

    protected abstract void redeemCheckoutCode();

    protected abstract void renderActions(List<ActionEntry> list);

    protected abstract void renderInstruments(Instrument[] instrumentArr);

    public BillingProfileBaseFragment() {
        this.mBillingProfileListener = new BillingProfileListener();
        this.mCarrierBillingProvisioningListener = new CarrierBillingProvisioningListener();
        this.mLastBillingProfileStateInstance = -1;
        this.mLastCarrierBillingStateInstance = -1;
        this.mProfileDirty = true;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccount = (Account) getArguments().getParcelable("BillingProfileFragment.account");
        this.mPurchaseContextToken = getArguments().getString("BillingProfileFragment.purchaseContextToken");
        if (savedInstanceState != null) {
            this.mProfile = (BillingProfile) ParcelableProto.getProtoFromBundle(savedInstanceState, "BillingProfileFragment.profile");
            if (this.mProfile != null) {
                this.mProfileDirty = true;
            }
            this.mLastBillingProfileStateInstance = savedInstanceState.getInt("BillingProfileFragment.lastBillingProfileStateInstance", -1);
            this.mLastCarrierBillingStateInstance = savedInstanceState.getInt("BillingProfileFragment.lastCarrierBillingStateInstance", -1);
            this.mStoredValueInstrumentId = savedInstanceState.getString("PurchaseFlowBillingProfileFragment.storedValueInstrumentId");
        }
        this.mEventLog = FinskyApp.get().getEventLogger(this.mAccount);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("BillingProfileFragment.profile", ParcelableProto.forProto(this.mProfile));
        outState.putInt("BillingProfileFragment.lastBillingProfileStateInstance", this.mLastBillingProfileStateInstance);
        outState.putInt("BillingProfileFragment.lastCarrierBillingStateInstance", this.mLastCarrierBillingStateInstance);
        outState.putString("PurchaseFlowBillingProfileFragment.storedValueInstrumentId", this.mStoredValueInstrumentId);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.mProfileView == null) {
            throw new IllegalStateException("mProfileView not set up.");
        } else if (this.mProgressIndicator == null) {
            throw new IllegalStateException("mProgressIndicator not set up.");
        } else {
            renderProfile();
        }
    }

    public void onStart() {
        super.onStart();
        createBillingProfileSidecar();
        this.mDcb2ProvisioningSidecar = (Dcb2ProvisioningSidecar) getFragmentManager().findFragmentByTag("BillingProfileFragment.carrierBillingSidecar");
        this.mProgressFragment = (ProgressDialogFragment) getFragmentManager().findFragmentByTag("BillingProfileFragment.carrierBillingProgressFragment");
    }

    protected void createBillingProfileSidecar() {
        this.mBillingProfileSidecar = (BillingProfileSidecar) getFragmentManager().findFragmentByTag("BillingProfileFragment.billingProfileSidecar");
        if (this.mBillingProfileSidecar == null) {
            this.mBillingProfileSidecar = BillingProfileSidecar.newInstance(this.mAccount, this.mPurchaseContextToken);
            getFragmentManager().beginTransaction().add(this.mBillingProfileSidecar, "BillingProfileFragment.billingProfileSidecar").commit();
        }
    }

    public void onResume() {
        super.onResume();
        CarrierBillingUtils.initializeStorageAndParams(getActivity(), false, new Runnable() {
            public void run() {
                if (BillingProfileBaseFragment.this.isResumed()) {
                    BillingProfileBaseFragment.this.renderProfile();
                    BillingProfileBaseFragment.this.mBillingProfileSidecar.setListener(BillingProfileBaseFragment.this.mBillingProfileListener);
                }
            }
        });
        if (this.mDcb2ProvisioningSidecar != null) {
            this.mDcb2ProvisioningSidecar.setListener(this.mCarrierBillingProvisioningListener);
        }
    }

    public void onPause() {
        this.mBillingProfileSidecar.setListener(null);
        if (this.mDcb2ProvisioningSidecar != null) {
            this.mDcb2ProvisioningSidecar.setListener(null);
        }
        super.onPause();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mProfileDirty = true;
        this.mLastBillingProfileStateInstance = -1;
        this.mLastCarrierBillingStateInstance = -1;
    }

    private void showLoading() {
        logLoading();
        this.mProfileView.setVisibility(8);
        this.mProgressIndicator.setVisibility(0);
        if (this.mErrorIndicator != null) {
            this.mErrorIndicator.setVisibility(8);
        }
    }

    private void handleError() {
        switch (this.mBillingProfileSidecar.getSubstate()) {
            case R.styleable.WalletImFormEditText_required /*4*/:
                onFatalError(this.mBillingProfileSidecar.getErrorMessageHtml());
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                onFatalError(ErrorStrings.get(getActivity(), this.mBillingProfileSidecar.getVolleyError()));
                return;
            default:
                FinskyLog.e("Don't know how to handle error substate %d, cancel.", Integer.valueOf(this.mBillingProfileSidecar.getSubstate()));
                onFatalError(getString(com.android.vending.R.string.error));
                return;
        }
    }

    private void onDcbError(String errorMessageHtml) {
        renderProfile();
        Builder builder = new Builder();
        builder.setMessageHtml(errorMessageHtml).setPositiveId(com.android.vending.R.string.ok).setCallback(this, 6, null);
        builder.build().show(getFragmentManager(), "BillingProfileFragment.errorDialog");
    }

    private void handleSuccess(BillingProfile billingProfile) {
        this.mProfile = billingProfile;
        this.mProfileDirty = true;
        renderProfile();
    }

    protected void renderProfile() {
        if (this.mProfileDirty) {
            this.mProfileDirty = false;
            if (this.mProfile != null && shouldRender(this.mProfile.instrument)) {
                for (Instrument instrument : this.mProfile.instrument) {
                    if (instrument.instrumentFamily == 7 && instrument.storedValue != null && instrument.storedValue.type == 33) {
                        this.mStoredValueInstrumentId = instrument.externalInstrumentId;
                    }
                }
                renderInstruments(this.mProfile.instrument);
                List<ActionEntry> actionEntries = Lists.newArrayList(this.mProfile.billingProfileOption.length);
                for (BillingProfileOption option : this.mProfile.billingProfileOption) {
                    ActionEntry actionEntry = billingProfileOptionToActionEntry(option, this.mProfile.paymentsIntegratorCommonToken);
                    if (actionEntry != null) {
                        actionEntries.add(actionEntry);
                    }
                }
                renderActions(actionEntries);
                this.mProgressIndicator.setVisibility(8);
                if (this.mErrorIndicator != null) {
                    this.mErrorIndicator.setVisibility(8);
                }
                this.mProfileView.setVisibility(0);
                this.mProfileView.requestFocus();
                logScreen();
            }
        }
    }

    private ActionEntry billingProfileOptionToActionEntry(final BillingProfileOption option, final byte[] paymentsIntegratorCommonToken) {
        switch (option.type) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return new ActionEntry(option, new OnClickListener() {
                    public void onClick(View view) {
                        BillingProfileBaseFragment.this.mEventLog.logClickEvent(810, null, BillingProfileBaseFragment.this);
                        BillingProfileBaseFragment.this.addCreditCard();
                    }
                });
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return isDcb3SetupOption(option) ? getDcb3Action(option) : getDcb2Action(option);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return new ActionEntry(option, new OnClickListener() {
                    public void onClick(View view) {
                        BillingProfileBaseFragment.this.mEventLog.logClickEvent(812, null, BillingProfileBaseFragment.this);
                        BillingProfileBaseFragment.this.redeemCheckoutCode();
                    }
                });
            case R.styleable.WalletImFormEditText_required /*4*/:
                return new ActionEntry(option, new OnClickListener() {
                    public void onClick(View view) {
                        BillingProfileBaseFragment.this.mEventLog.logClickEvent(813, null, BillingProfileBaseFragment.this);
                        BillingProfileBaseFragment.this.topup(option.topupInfo);
                    }
                });
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                final GenericInstrument instrument = option.genericInstrument;
                DeviceCreateInstrumentMetadata metadata = instrument.createInstrumentMetadata;
                if (supportsGenericInstruments() && "PAYPAL".equals(metadata.instrumentType) && metadata.flowType == 1) {
                    return new ActionEntry(option, new OnClickListener() {
                        public void onClick(View view) {
                            BillingProfileBaseFragment.this.mEventLog.logClickEvent(814, null, BillingProfileBaseFragment.this);
                            BillingProfileBaseFragment.this.addGenericInstrument(instrument);
                        }
                    });
                }
                FinskyLog.w("Unsupported FlowType: flowType=%d, instrumentType=%s, title=%s", Integer.valueOf(metadata.flowType), metadata.instrumentType, option.displayTitle);
                return null;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return new ActionEntry(option, new OnClickListener() {
                    public void onClick(View view) {
                        int clickType;
                        int requestCode;
                        if ("CREDIT_CARD".equals(option.typeName)) {
                            clickType = 810;
                            requestCode = 8;
                        } else if ("PAYPAL".equals(option.typeName)) {
                            clickType = 814;
                            requestCode = 9;
                        } else {
                            FinskyLog.wtf("Unexpected typeName=%s", option.typeName);
                            clickType = -1;
                            requestCode = 7;
                        }
                        BillingProfileBaseFragment.this.mEventLog.logClickEvent(clickType, null, BillingProfileBaseFragment.this);
                        BillingProfileBaseFragment.this.addInstrumentManager(paymentsIntegratorCommonToken, option.paymentsIntegratorInstrumentToken, requestCode);
                    }
                });
            default:
                FinskyLog.w("Skipping unknown option: type=%d, displayTitle=%s", Integer.valueOf(option.type), option.displayTitle);
                return null;
        }
    }

    protected boolean supportsGenericInstruments() {
        return true;
    }

    protected ActionEntry getDcb2Action(BillingProfileOption option) {
        return new ActionEntry(option, new OnClickListener() {
            public void onClick(View view) {
                BillingProfileBaseFragment.this.mEventLog.logClickEvent(811, null, BillingProfileBaseFragment.this);
                BillingProfileBaseFragment.this.ensureProvisionedAndAddDcb2();
            }
        });
    }

    private ActionEntry getDcb3Action(final BillingProfileOption option) {
        return new ActionEntry(option, new OnClickListener() {
            public void onClick(View view) {
                BillingProfileBaseFragment.this.mEventLog.logClickEvent(811, null, BillingProfileBaseFragment.this);
                BillingProfileBaseFragment.this.addDcb3(option.carrierBillingInstrumentStatus);
            }
        });
    }

    private void addGenericInstrument(GenericInstrument instrument) {
        startActivityForResult(AddGenericInstrumentActivity.createIntent(this.mAccount.name, instrument), 3);
    }

    private boolean isDcb3SetupOption(BillingProfileOption option) {
        return option.carrierBillingInstrumentStatus != null && option.carrierBillingInstrumentStatus.apiVersion == 3;
    }

    protected void addCreditCard() {
        startActivityForResult(AddCreditCardActivity.createIntent(this.mAccount.name), 1);
    }

    protected void addInstrumentManager(byte[] commonToken, byte[] actionToken, int requestCode) {
        startActivityForResult(InstrumentManagerActivity.createIntent(this.mAccount.name, commonToken, actionToken), requestCode);
    }

    protected void addDcb3(CarrierBillingInstrumentStatus instrumentStatus) {
        startActivityForResult(AddDcb3Activity.createIntent(this.mAccount.name, instrumentStatus), 2);
    }

    private void ensureProvisionedAndAddDcb2() {
        if (CarrierBillingUtils.isProvisioned(BillingLocator.getCarrierBillingStorage())) {
            addDcb2();
            return;
        }
        this.mDcb2ProvisioningSidecar = (Dcb2ProvisioningSidecar) getFragmentManager().findFragmentByTag("BillingProfileFragment.carrierBillingSidecar");
        if (this.mDcb2ProvisioningSidecar != null) {
            FinskyLog.wtf("Not expected to have a carrier billing fragment.", new Object[0]);
            return;
        }
        this.mDcb2ProvisioningSidecar = new Dcb2ProvisioningSidecar();
        this.mDcb2ProvisioningSidecar.setListener(this.mCarrierBillingProvisioningListener);
        getFragmentManager().beginTransaction().add(this.mDcb2ProvisioningSidecar, "BillingProfileFragment.carrierBillingSidecar").commit();
    }

    private void addDcb2() {
        startActivityForResult(AddDcb2Activity.createIntent(this.mAccount.name), 2);
    }

    protected void topup(TopupInfo topupInfo) {
        startActivityForResult(StoredValueTopUpActivity.createIntent(this.mAccount.name, topupInfo), 5);
    }

    protected boolean shouldRender(Instrument[] instruments) {
        return true;
    }

    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        int backgroundEventType;
        switch (requestCode) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                backgroundEventType = getCreditCardEventType();
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                backgroundEventType = getDcbEventType();
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                backgroundEventType = getGenericInstrumentEventType();
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                backgroundEventType = getRedeemEventType();
                break;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                backgroundEventType = getTopupEventType();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                backgroundEventType = getCreditCardEventType();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                backgroundEventType = getGenericInstrumentEventType();
                break;
            default:
                FinskyLog.wtf("Unexpected requestCode=%d", Integer.valueOf(requestCode));
                backgroundEventType = -1;
                break;
        }
        byte[] serverLogsCookie = getBackgroundEventServerLogsCookie();
        switch (requestCode) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (resultCode == -1 && data.hasExtra("instrument_id")) {
                    this.mEventLog.logBackgroundEvent(backgroundEventType, serverLogsCookie);
                    onInstrumentCreated(data.getStringExtra("instrument_id"), data.getStringExtra("redeemed_offer_message_html"));
                    break;
                }
            case R.styleable.WalletImFormEditText_required /*4*/:
                if (resultCode == -1) {
                    RedeemCodeResult redeemCodeResult = (RedeemCodeResult) data.getParcelableExtra("RedeemCodeBaseActivity.redeem_code_result");
                    if (redeemCodeResult != null) {
                        this.mEventLog.logBackgroundEvent(backgroundEventType, serverLogsCookie);
                        String storedValueInstrumentId = redeemCodeResult.getStoredValueInstrumentId();
                        if (!TextUtils.isEmpty(storedValueInstrumentId)) {
                            this.mStoredValueInstrumentId = storedValueInstrumentId;
                            onStoredValueAdded(this.mStoredValueInstrumentId, redeemCodeResult.getRedeemedOfferHtml());
                            break;
                        }
                        onPromoCodeRedeemed(redeemCodeResult);
                        break;
                    }
                }
                break;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                if (resultCode == -1) {
                    this.mEventLog.logBackgroundEvent(backgroundEventType, serverLogsCookie);
                    onStoredValueAdded(this.mStoredValueInstrumentId, null);
                    break;
                }
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                if (resultCode == -1) {
                    String instrumentId = data.getStringExtra("instrument_id");
                    if (!TextUtils.isEmpty(instrumentId)) {
                        this.mEventLog.logBackgroundEvent(backgroundEventType, serverLogsCookie);
                        onInstrumentCreated(instrumentId, null);
                        break;
                    }
                }
                break;
        }
        if (!getActivity().isFinishing()) {
            logScreen();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void requestBillingProfile() {
        Map<String, String> extraPostParams = Maps.newHashMap();
        CarrierBillingUtils.addPrepareOrBillingProfileParams(true, true, extraPostParams);
        extraPostParams.put("bpif", String.valueOf(getBillingProfileRequestEnum()));
        this.mBillingProfileSidecar.start(extraPostParams);
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyLog.wtf("Not using tree impressions.", new Object[0]);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode != 6) {
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        if (requestCode != 6) {
        }
    }
}
