package com.google.android.finsky.billing.lightpurchase.purchasesteps;

import android.content.res.Resources;
import android.os.Bundle;
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
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.config.PurchaseAuth;
import com.google.android.play.layout.PlayActionButton;

public class SuccessStepWithAuthChoices extends StepFragment<PurchaseFragment> implements OnClickListener {
    private String mAccountName;
    private int mBackend;
    private PlayActionButton mEveryTimeButton;
    private PlayActionButton mSessionButton;
    private View mStepFragmentView;
    private final PlayStoreUiElement mUiElement;
    private boolean mUsedPinBasedAuth;

    public SuccessStepWithAuthChoices() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(1250);
    }

    public static SuccessStepWithAuthChoices newInstance(String accountName, int backend, boolean usedPinBasedAuth) {
        Bundle args = new Bundle();
        SuccessStepWithAuthChoices stepFragment = new SuccessStepWithAuthChoices();
        args.putString("authAccount", accountName);
        args.putInt("SuccessStepWithAuthChoices.backend", backend);
        args.putBoolean("SuccessStepWithAuthChoices.usedPinBasedAuth", usedPinBasedAuth);
        stepFragment.setArguments(args);
        return stepFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.mAccountName = args.getString("authAccount");
        this.mBackend = args.getInt("SuccessStepWithAuthChoices.backend");
        this.mUsedPinBasedAuth = args.getBoolean("SuccessStepWithAuthChoices.usedPinBasedAuth", false);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mStepFragmentView = inflater.inflate(R.layout.light_purchase_success_step_with_auth_choices, container, false);
        TextView textView = (TextView) this.mStepFragmentView.findViewById(R.id.description);
        textView.setText(this.mUsedPinBasedAuth ? R.string.payment_success_via_pin_auth_choices_description : R.string.payment_success_auth_choices_description);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mEveryTimeButton = (PlayActionButton) this.mStepFragmentView.findViewById(R.id.choice_everytime_button);
        this.mSessionButton = (PlayActionButton) this.mStepFragmentView.findViewById(R.id.choice_session_button);
        this.mEveryTimeButton.configure(this.mBackend, (int) R.string.purchase_auth_choice_everytime, (OnClickListener) this);
        this.mSessionButton.configure(this.mBackend, (int) R.string.purchase_auth_choice_session, (OnClickListener) this);
        return this.mStepFragmentView;
    }

    public void onClick(View view) {
        if (view == this.mEveryTimeButton) {
            logClick(1251);
            setPurchaseAuth(2);
        } else if (view == this.mSessionButton) {
            logClick(1252);
            setPurchaseAuth(1);
        }
        authChoiceSelected();
    }

    private void setPurchaseAuth(int purchaseAuth) {
        PurchaseAuth.setAndLogPurchaseAuth(this.mAccountName, purchaseAuth, null, FinskyApp.get().getEventLogger(this.mAccountName), "success-step-with-choices");
    }

    public String getContinueButtonLabel(Resources resources) {
        return null;
    }

    public void onContinueButtonClicked() {
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    public boolean allowButtonBar() {
        return false;
    }

    private void authChoiceSelected() {
        PurchaseFragment purchaseFragment = (PurchaseFragment) getMultiStepFragment();
        if (purchaseFragment != null) {
            purchaseFragment.confirmAuthChoiceSelected();
        }
    }
}
