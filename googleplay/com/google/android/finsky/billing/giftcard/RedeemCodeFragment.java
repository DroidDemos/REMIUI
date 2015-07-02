package com.google.android.finsky.billing.giftcard;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.billing.giftcard.steps.AddressChallengeStep;
import com.google.android.finsky.billing.giftcard.steps.ConfirmationStep;
import com.google.android.finsky.billing.giftcard.steps.InstrumentManagerRedeemStep;
import com.google.android.finsky.billing.giftcard.steps.RedeemScreenStep;
import com.google.android.finsky.billing.giftcard.steps.RedeemSuccessStep;
import com.google.android.finsky.billing.lightpurchase.CheckoutPurchaseSidecar;
import com.google.android.finsky.billing.lightpurchase.LightPurchaseFlowActivity;
import com.google.android.finsky.billing.lightpurchase.PurchaseActivity;
import com.google.android.finsky.billing.lightpurchase.PurchaseParams;
import com.google.android.finsky.billing.lightpurchase.multistep.MultiStepFragment;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.layout.IconButtonGroup;
import com.google.android.finsky.navigationmanager.ConsumptionUtils;
import com.google.android.finsky.protos.Acquisition.PostSuccessAction;
import com.google.android.finsky.protos.Acquisition.SuccessInfo;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.ParcelableProto;

public class RedeemCodeFragment extends MultiStepFragment implements com.google.android.finsky.activities.SimpleAlertDialog.Listener, com.google.android.finsky.fragments.SidecarFragment.Listener {
    private String mAccountName;
    private int mBillingUiMode;
    private boolean mCodeScreenSkipped;
    private Docid mDocid;
    private int mLastStateInstance;
    private RedeemCodeResult mRedeemCodeResult;
    private int mRedemptionContext;
    private RedeemCodeSidecar mSidecar;

    public interface Listener {
        void onFinished();
    }

    public static RedeemCodeFragment newInstance(String accountName, int redemptionContext, int backend, int mode, Docid docid, int offerType, String prefillCode, String partnerPayload) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putInt("RedeemCodeFragment.redemption_context", redemptionContext);
        args.putInt("RedeemCodeFragment.backend", backend);
        args.putInt("ui_mode", mode);
        args.putParcelable("RedeemCodeFragment.docid", ParcelableProto.forProto(docid));
        args.putInt("RedeemCodeFragment.offer_type", offerType);
        args.putString("RedeemCodeFragment.prefill_code", prefillCode);
        args.putString("RedeemCodeFragment.partner_payload", partnerPayload);
        RedeemCodeFragment result = new RedeemCodeFragment();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccountName = getArguments().getString("authAccount");
        this.mBillingUiMode = getArguments().getInt("ui_mode");
        this.mRedemptionContext = getArguments().getInt("RedeemCodeFragment.redemption_context");
        this.mDocid = (Docid) ParcelableProto.getProtoFromBundle(getArguments(), "RedeemCodeFragment.docid");
        if (this.mRedemptionContext == 1 && this.mDocid == null) {
            throw new IllegalStateException("Null docid in purchase context.");
        } else if (savedInstanceState != null) {
            this.mLastStateInstance = savedInstanceState.getInt("RedeemCodeFragment.last_state_instance");
            this.mRedeemCodeResult = (RedeemCodeResult) savedInstanceState.getParcelable("RedeemCodeFragment.redeem_code_result");
            this.mCodeScreenSkipped = savedInstanceState.getBoolean("RedeemCodeFragment.code_screen_skipped");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("RedeemCodeFragment.last_state_instance", this.mLastStateInstance);
        outState.putParcelable("RedeemCodeFragment.redeem_code_result", this.mRedeemCodeResult);
        outState.putBoolean("RedeemCodeFragment.code_screen_skipped", this.mCodeScreenSkipped);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(this.mBillingUiMode == 0 ? R.layout.light_purchase : R.layout.setup_wizard_redeem_code_fragment, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.mContinueButton instanceof IconButtonGroup) {
            ((IconButtonGroup) this.mContinueButton).setBackendForLabel(getArguments().getInt("RedeemCodeFragment.backend"));
        }
        Button cancelButton = (Button) this.mMainView.findViewById(R.id.cancel_button);
        if (cancelButton != null) {
            cancelButton.setText(R.string.cancel);
            cancelButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    RedeemCodeFragment.this.finish();
                }
            });
        }
    }

    public void onStart() {
        super.onStart();
        this.mSidecar = (RedeemCodeSidecar) getFragmentManager().findFragmentByTag("RedeemCodeFragment.sidecar");
        if (this.mSidecar == null) {
            Bundle arguments = getArguments();
            this.mSidecar = RedeemCodeSidecar.newInstance(this.mAccountName, this.mRedemptionContext, this.mDocid, arguments.getInt("RedeemCodeFragment.offer_type"), arguments.getString("RedeemCodeFragment.partner_payload"));
            getFragmentManager().beginTransaction().add(this.mSidecar, "RedeemCodeFragment.sidecar").commit();
        }
        this.mSidecar.setListener(this);
    }

    public void onStop() {
        this.mSidecar.setListener(null);
        super.onStop();
    }

    public void redeem(String code) {
        this.mSidecar.redeem(code);
    }

    public void confirm() {
        this.mSidecar.respondWithConfirmation();
    }

    public void addressChallenge(Address address, String[] checkboxIds) {
        this.mSidecar.respondWithAddress(address, checkboxIds);
    }

    public void instrumentManagerFinished(boolean success) {
        if (success) {
            this.mSidecar.respondInstrumentManagerSucceeded();
        } else {
            finish();
        }
    }

    public void performSuccessActionAndFinish() {
        if (this.mRedeemCodeResult != null) {
            SuccessInfo successInfo = this.mSidecar.getSuccessInfo();
            if (successInfo.postSuccessAction != null) {
                if (this.mBillingUiMode == 1) {
                    FinskyLog.wtf("Cannot perform success action during Setup Wizard", new Object[0]);
                } else if (performSuccessAction(successInfo.postSuccessAction)) {
                    FinskyLog.d("Dialog shown, waiting for user input.", new Object[0]);
                    return;
                }
            }
        }
        finish();
    }

    private void finish() {
        Listener listener = getListener();
        if (listener == null) {
            FinskyLog.wtf("No listener.", new Object[0]);
        } else {
            listener.onFinished();
        }
    }

    private boolean performSuccessAction(PostSuccessAction postSuccessAction) {
        Account account = AccountHandler.findAccount(this.mAccountName, getActivity());
        if (postSuccessAction.installApp != null) {
            return installAppIfNecessary(account, new Document(postSuccessAction.installApp.doc));
        }
        if (postSuccessAction.openDoc != null) {
            return ConsumptionUtils.openItem(getActivity(), account, new Document(postSuccessAction.openDoc.doc), getFragmentManager(), this, 1);
        } else if (postSuccessAction.openHome != null) {
            startActivity(IntentUtils.createAggregatedHomeIntent(getActivity()));
            return false;
        } else if (postSuccessAction.purchaseFlow != null) {
            Document voucherDoc = this.mSidecar.getRedeemedItemDoc();
            Document purchaseDoc = new Document(postSuccessAction.purchaseFlow.purchaseDoc);
            if (purchaseDoc.getBackend() == 3) {
                throw new IllegalStateException("Apps are unsupported");
            }
            startActivity(PurchaseActivity.createIntent(this.mAccount, PurchaseParams.builder().setDocument(purchaseDoc).setOfferType(postSuccessAction.purchaseFlow.purchaseOfferType).setVoucherId(voucherDoc.getDocId()).build(), null, null));
            return false;
        } else if (postSuccessAction.openContainer != null) {
            Link link = postSuccessAction.openContainer.link;
            if (link != null) {
                this.mRedeemCodeResult = new RedeemCodeResult(this.mRedeemCodeResult.getRedeemedOfferHtml(), this.mRedeemCodeResult.getStoredValueInstrumentId(), this.mRedeemCodeResult.isInstallAppDeferred(), this.mRedeemCodeResult.getExtraPurchaseData(), link);
                return false;
            }
            FinskyLog.wtf("Unexpected missing link", new Object[0]);
            return false;
        } else {
            FinskyLog.w("Unsupported PostSuccessAction.", new Object[0]);
            return false;
        }
    }

    private boolean installAppIfNecessary(Account account, Document appToInstall) {
        if (this.mRedemptionContext == 1 && DocUtils.isInAppDocid(this.mDocid)) {
            return false;
        }
        String packageName = appToInstall.getAppDetails().packageName;
        if (isAppCurrentVersion(packageName, appToInstall.getAppDetails().versionCode)) {
            Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent == null) {
                intent = IntentUtils.createViewDocumentIntent(getActivity(), appToInstall);
            }
            startActivity(intent);
            return false;
        } else if (this.mRedemptionContext == 1 && this.mDocid.type == 1) {
            this.mRedeemCodeResult = new RedeemCodeResult(this.mRedeemCodeResult.getRedeemedOfferHtml(), this.mRedeemCodeResult.getStoredValueInstrumentId(), true, this.mRedeemCodeResult.getExtraPurchaseData(), this.mRedeemCodeResult.getLink());
            return false;
        } else {
            startActivityForResult(LightPurchaseFlowActivity.createIntent(account, appToInstall, 1, null, null, null), 2);
            return true;
        }
    }

    private static boolean isAppCurrentVersion(String packageName, int currentVersionCode) {
        PackageState packageState = FinskyApp.get().getPackageInfoRepository().get(packageName);
        return packageState != null && packageState.installedVersion >= currentVersionCode;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (resultCode == -1) {
                    startActivity(IntentUtils.createViewDocumentIntent(getActivity(), new Document(this.mSidecar.getSuccessInfo().postSuccessAction.installApp.doc)));
                    finish();
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    public RedeemCodeResult getRedeemCodeResult() {
        return this.mRedeemCodeResult;
    }

    public void onStateChange(SidecarFragment fragment) {
        if (fragment != this.mSidecar) {
            FinskyLog.wtf("Received state change for unknown fragment: %s", fragment);
        } else if (this.mSidecar.getStateInstance() <= this.mLastStateInstance) {
            FinskyLog.d("Already received state instance %d, ignore.", Integer.valueOf(this.mLastStateInstance));
        } else {
            if (FinskyLog.DEBUG) {
                FinskyLog.d("State changed: %d", Integer.valueOf(this.mSidecar.getState()));
            }
            this.mLastStateInstance = this.mSidecar.getStateInstance();
            switch (this.mSidecar.getState()) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    String prefillCode = getArguments().getString("RedeemCodeFragment.prefill_code");
                    if (TextUtils.isEmpty(prefillCode)) {
                        showStep(RedeemScreenStep.newInstance(this.mAccountName, prefillCode, null, this.mBillingUiMode));
                        return;
                    }
                    this.mCodeScreenSkipped = true;
                    redeem(prefillCode);
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    showLoading();
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    SuccessInfo successInfo = this.mSidecar.getSuccessInfo();
                    succeed(successInfo, this.mSidecar.getRedeemedOfferHtml(), this.mSidecar.getStoredValueInstrumentId());
                    if (successInfo.hasButtonLabel) {
                        showStep(RedeemSuccessStep.newInstance(successInfo, this.mBillingUiMode));
                        return;
                    } else {
                        performSuccessActionAndFinish();
                        return;
                    }
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    String errorMessageHtml;
                    if (this.mSidecar.getSubstate() != 1 || this.mSidecar.getVolleyError() == null) {
                        errorMessageHtml = this.mSidecar.getErrorHtml();
                    } else {
                        errorMessageHtml = ErrorStrings.get(getActivity(), this.mSidecar.getVolleyError());
                    }
                    FinskyLog.d("Redemption error: %s", errorMessageHtml);
                    if (this.mCurrentFragment instanceof RedeemScreenStep) {
                        hideLoading();
                        ((RedeemScreenStep) this.mCurrentFragment).showError(errorMessageHtml);
                        return;
                    }
                    this.mCodeScreenSkipped = false;
                    showStep(RedeemScreenStep.newInstance(this.mAccountName, this.mSidecar.getLastRedeemCode(), errorMessageHtml, this.mBillingUiMode));
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    showStep(AddressChallengeStep.newInstance(this.mAccountName, this.mSidecar.getAddressChallenge(), this.mBillingUiMode));
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    showStep(ConfirmationStep.newInstance(this.mSidecar.getUserConfirmationChallenge(), this.mBillingUiMode, this.mCodeScreenSkipped));
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    showStep(InstrumentManagerRedeemStep.newInstance(this.mAccountName, this.mSidecar.getInstrumentManagerTokens()));
                    return;
                default:
                    FinskyLog.wtf("Unknown sidecar state: %d", Integer.valueOf(this.mSidecar.getState()));
                    return;
            }
        }
    }

    public void hideCancelButton() {
        Button cancelButton = (Button) this.mMainView.findViewById(R.id.cancel_button);
        if (cancelButton != null) {
            cancelButton.setVisibility(8);
        }
    }

    private void succeed(SuccessInfo successInfo, String redeemedOfferHtml, String storedValueInstrumentId) {
        boolean isInstalled = true;
        Bundle extraPurchaseData = null;
        if (this.mRedemptionContext == 1) {
            extraPurchaseData = CheckoutPurchaseSidecar.extractExtraPurchaseData(successInfo.libraryUpdate, this.mDocid);
        }
        this.mRedeemCodeResult = new RedeemCodeResult(redeemedOfferHtml, storedValueInstrumentId, false, extraPurchaseData, null);
        if (this.mRedemptionContext == 3) {
            Document redeemedItem = this.mSidecar.getRedeemedItemDoc();
            Docid consumptionApp = this.mSidecar.getConsumptionAppDocid();
            if (redeemedItem != null && DocUtils.isInAppDocid(redeemedItem.getFullDocid()) && consumptionApp != null && consumptionApp.backend == 3) {
                String packageName = consumptionApp.backendDocid;
                if (FinskyApp.get().getPackageInfoRepository().get(packageName) == null) {
                    isInstalled = false;
                }
                if (isInstalled) {
                    Intent intent = new Intent("com.android.vending.billing.PURCHASES_UPDATED");
                    intent.setPackage(packageName);
                    getActivity().sendBroadcast(intent);
                }
            }
        }
    }

    private Listener getListener() {
        if (getTargetFragment() instanceof Listener) {
            return (Listener) getTargetFragment();
        }
        if (getActivity() instanceof Listener) {
            return (Listener) getActivity();
        }
        return null;
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            startActivity(IntentUtils.createViewDocumentUrlIntent(getActivity(), extraArguments.getString("dialog_details_url")));
            finish();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            finish();
        }
    }
}
