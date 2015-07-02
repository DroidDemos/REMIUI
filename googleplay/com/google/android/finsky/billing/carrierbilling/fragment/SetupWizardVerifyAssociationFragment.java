package com.google.android.finsky.billing.carrierbilling.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyAssociationDialogFragment.VerifyAssociationListener;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.InstrumentActivity;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.setup.SetupWizardNavBar;

public class SetupWizardVerifyAssociationFragment extends LoggingFragment implements OnClickListener {
    private Button mCancelButton;
    private VerifyAssociationListener mListener;

    public static SetupWizardVerifyAssociationFragment newInstance(String accountName) {
        SetupWizardVerifyAssociationFragment fragment = new SetupWizardVerifyAssociationFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setup_wizard_task_progress_view, container, false);
        ((TextView) v.findViewById(R.id.verbose_message)).setText(getResources().getString(R.string.associating_device_message));
        SetupWizardNavBar setupWizardNavBar = ((InstrumentActivity) getActivity()).getSetupWizardNavBar();
        if (setupWizardNavBar != null) {
            setupWizardNavBar.getNextButton().setEnabled(false);
            this.mCancelButton = setupWizardNavBar.getBackButton();
        } else {
            this.mCancelButton = (Button) v.findViewById(R.id.cancel_button);
        }
        this.mCancelButton.setOnClickListener(this);
        this.mCancelButton.setText(R.string.device_association_back_button);
        return v;
    }

    public void setOnResultListener(VerifyAssociationListener listener) {
        this.mListener = listener;
    }

    public void onClick(View v) {
        if (v == this.mCancelButton && this.mListener != null) {
            this.mListener.onVerifyCancel();
        }
    }

    protected int getPlayStoreUiElementType() {
        return 897;
    }
}
