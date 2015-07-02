package com.google.android.finsky.billing.lightpurchase.ageverification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.billing.ProgressSpinnerFragment;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.ChallengeProto.AgeChallenge;
import com.google.android.finsky.protos.ChallengeProto.Challenge;
import com.google.android.finsky.protos.ChallengeProto.ChallengeError;
import com.google.android.finsky.protos.ChallengeProto.SmsCodeChallenge;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Map;

public class AgeVerificationHostFragment extends Fragment implements com.google.android.finsky.billing.lightpurchase.ageverification.AgeChallengeFragment.Listener, com.google.android.finsky.billing.lightpurchase.ageverification.ChallengeErrorFragment.Listener, com.google.android.finsky.billing.lightpurchase.ageverification.SmsCodeFragment.Listener, com.google.android.finsky.fragments.SidecarFragment.Listener {
    private String mAccountName;
    private int mBackend;
    private String mDocidStr;
    private int mLastStateInstance;
    private ProgressSpinnerFragment mProgressSpinnerFragment;
    private AgeVerificationSidecar mSidecar;

    public interface Listener {
        void onFinished(boolean z);
    }

    public static Fragment newInstance(String accountName, int backend, String docidStr) {
        AgeVerificationHostFragment result = new AgeVerificationHostFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putInt("AgeVerificationHostFragment.backend", backend);
        args.putString("AgeVerificationHostFragment.docid_str", docidStr);
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccountName = getArguments().getString("authAccount");
        this.mBackend = getArguments().getInt("AgeVerificationHostFragment.backend");
        this.mDocidStr = getArguments().getString("AgeVerificationHostFragment.docid_str");
        if (savedInstanceState != null) {
            this.mLastStateInstance = savedInstanceState.getInt("AgeVerificationHostFragment.last_state_instance");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("AgeVerificationHostFragment.last_state_instance", this.mLastStateInstance);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.age_verification_host_fragment, container, false);
    }

    public void onStart() {
        super.onStart();
        this.mSidecar = (AgeVerificationSidecar) getFragmentManager().findFragmentByTag("AgeVerificationHostFragment.sidecar");
        if (this.mSidecar == null) {
            this.mSidecar = AgeVerificationSidecar.newInstance(this.mAccountName);
            getFragmentManager().beginTransaction().add(this.mSidecar, "AgeVerificationHostFragment.sidecar").commit();
        }
    }

    public void onResume() {
        super.onResume();
        this.mSidecar.setListener(this);
    }

    public void onPause() {
        super.onPause();
        this.mSidecar.setListener(null);
    }

    public void onStateChange(SidecarFragment fragment) {
        if (this.mSidecar.getStateInstance() <= this.mLastStateInstance) {
            FinskyLog.d("Already received state instance %d, ignore.", Integer.valueOf(this.mLastStateInstance));
            return;
        }
        this.mLastStateInstance = this.mSidecar.getStateInstance();
        switch (this.mSidecar.getState()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.mSidecar.checkDocumentMaturity(this.mDocidStr);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                showLoading();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                handleSuccess(true);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (this.mSidecar.getSubstate() == 1) {
                    launchChallengeErrorFragment(this.mSidecar.getChallengeError());
                    return;
                } else {
                    ErrorDialog.show(getFragmentManager(), null, this.mSidecar.getErrorHtml(), false);
                    return;
                }
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                this.mSidecar.requestAgeVerificationForm();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                launchAgeChallengeFragment(this.mSidecar.getAgeChallenge());
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                launchSmsCodeFragment(this.mSidecar.getSmsCodeChallenge());
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                handleSuccess(false);
                return;
            default:
                FinskyLog.wtf("Unexpected state: " + this.mSidecar.getState(), new Object[0]);
                return;
        }
    }

    private void showLoading() {
        if (this.mProgressSpinnerFragment == null) {
            this.mProgressSpinnerFragment = new ProgressSpinnerFragment();
        }
        showFragment(this.mProgressSpinnerFragment);
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.setTransition(4099);
        transaction.commit();
    }

    private void launchAgeChallengeFragment(AgeChallenge challenge) {
        showFragment(AgeChallengeFragment.newInstance(this.mAccountName, this.mBackend, challenge));
    }

    private void launchSmsCodeFragment(SmsCodeChallenge challenge) {
        showFragment(SmsCodeFragment.newInstance(this.mAccountName, this.mBackend, challenge));
    }

    private void launchChallengeErrorFragment(ChallengeError challengeError) {
        showFragment(ChallengeErrorFragment.newInstance(this.mAccountName, this.mBackend, challengeError));
    }

    private void handleSuccess(boolean isAgeVerified) {
        if (isAgeVerified) {
            FinskyApp.get().getClientMutationCache(this.mAccountName).setAgeVerificationRequired(false);
        }
        getListener().onFinished(true);
    }

    private void fail() {
        getListener().onFinished(false);
    }

    private Listener getListener() {
        return (Listener) getActivity();
    }

    public void onSubmit(String actionUrl, Map<String, String> postParams) {
        this.mSidecar.verifyAge(actionUrl, postParams);
    }

    public void onResendSmsCode(String actionUrl) {
        this.mSidecar.resendSmsCode(actionUrl);
    }

    public void onVerifySmsCode(String actionUrl, String codePostParam, String code) {
        this.mSidecar.verifySmsCode(actionUrl, codePostParam, code);
    }

    public void onFail() {
        fail();
    }

    public void onStartChallenge(Challenge challenge) {
        this.mSidecar.startChallenge(challenge);
    }
}
