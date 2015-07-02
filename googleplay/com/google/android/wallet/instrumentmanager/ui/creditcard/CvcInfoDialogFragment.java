package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.ui.common.BaseInstrumentManagerDialogFragment;

public class CvcInfoDialogFragment extends BaseInstrumentManagerDialogFragment {
    public static CvcInfoDialogFragment newInstance(int themeResourceId) {
        CvcInfoDialogFragment fragment = new CvcInfoDialogFragment();
        fragment.setArguments(BaseInstrumentManagerDialogFragment.createArgs(themeResourceId));
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Builder(getActivity()).setView(getThemedLayoutInflater().inflate(R.layout.view_cvc_information, null, false)).setPositiveButton(R.string.wallet_im_close, null).create();
    }
}
