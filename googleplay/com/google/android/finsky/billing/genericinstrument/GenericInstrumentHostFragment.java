package com.google.android.finsky.billing.genericinstrument;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.billing.ProgressSpinnerFragment;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.BuyInstruments.CreateInstrumentRequest;
import com.google.android.finsky.protos.CommonDevice.GenericInstrument;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentFlowState;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;

public class GenericInstrumentHostFragment extends Fragment implements com.google.android.finsky.fragments.SidecarFragment.Listener {
    private int mLastStateInstance;
    private ProgressSpinnerFragment mProgressSpinnerFragment;
    private GenericInstrumentSidecar mSidecar;

    public interface Listener {
        void onFinished(String str);
    }

    public static Fragment newInstance(String accountName, GenericInstrument genericInstrument) {
        GenericInstrumentHostFragment result = new GenericInstrumentHostFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putParcelable("GenericInstrumentFlowFragment.generic_instrument", ParcelableProto.forProto(genericInstrument));
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mLastStateInstance = savedInstanceState.getInt("GenericInstrumentFlowFragment.last_state_instance");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("GenericInstrumentFlowFragment.last_state_instance", this.mLastStateInstance);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.billing_generic_instrument_container, container, false);
    }

    public void onStart() {
        super.onStart();
        this.mSidecar = (GenericInstrumentSidecar) getFragmentManager().findFragmentByTag("generic_instrument_sidecar");
        if (this.mSidecar == null) {
            Bundle args = getArguments();
            this.mSidecar = GenericInstrumentSidecar.newInstance(args.getString("authAccount"), (GenericInstrument) ParcelableProto.getProtoFromBundle(args, "GenericInstrumentFlowFragment.generic_instrument"));
            getFragmentManager().beginTransaction().add(this.mSidecar, "generic_instrument_sidecar").commit();
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
                this.mSidecar.requestInitialInstrumentFlowState();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                handleRunning();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                handleSuccess(this.mSidecar.getInstrumentId());
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                handleError(this.mSidecar.getErrorHtml());
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                handleFlowState(this.mSidecar.getInstrumentFlowState());
                return;
            default:
                FinskyLog.wtf("Unexpected state: " + this.mSidecar.getState(), new Object[0]);
                return;
        }
    }

    private void handleRunning() {
        if (this.mProgressSpinnerFragment == null) {
            this.mProgressSpinnerFragment = new ProgressSpinnerFragment();
        }
        showFragment(this.mProgressSpinnerFragment);
    }

    private void handleSuccess(String instrumentId) {
        getListener().onFinished(instrumentId);
    }

    private void handleError(String errorHtml) {
        boolean isTerminalError = true;
        String accountName = getArguments().getString("authAccount");
        if (this.mSidecar.getSubstate() != 1) {
            isTerminalError = false;
        }
        showFragment(GenericInstrumentErrorFragment.newInstance(accountName, errorHtml, isTerminalError));
    }

    private void handleFlowState(DeviceCreateInstrumentFlowState flowState) {
        if (flowState.usernamePasswordForm != null) {
            showFragment(UsernamePasswordFormFragment.newInstance(getArguments().getString("authAccount"), flowState));
            return;
        }
        throw new IllegalStateException("Unsupported form");
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.setTransition(4099);
        transaction.commit();
    }

    private Listener getListener() {
        return (Listener) getActivity();
    }

    public void createInstrument(CreateInstrumentRequest request) {
        this.mSidecar.createInstrument(request);
    }

    public void acknowledgeTerminalError() {
        getListener().onFinished(null);
    }

    public void retryAfterError() {
        this.mSidecar.retryAfterError();
    }
}
