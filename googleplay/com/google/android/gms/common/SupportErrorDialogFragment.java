package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.internal.kn;

public class SupportErrorDialogFragment extends DialogFragment {
    private OnCancelListener Rl;
    private Dialog mDialog;

    public SupportErrorDialogFragment() {
        this.mDialog = null;
        this.Rl = null;
    }

    public static SupportErrorDialogFragment newInstance(Dialog dialog, OnCancelListener cancelListener) {
        SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        Dialog dialog2 = (Dialog) kn.b((Object) dialog, (Object) "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        supportErrorDialogFragment.mDialog = dialog2;
        if (cancelListener != null) {
            supportErrorDialogFragment.Rl = cancelListener;
        }
        return supportErrorDialogFragment;
    }

    public void onCancel(DialogInterface dialog) {
        if (this.Rl != null) {
            this.Rl.onCancel(dialog);
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (this.mDialog == null) {
            setShowsDialog(false);
        }
        return this.mDialog;
    }

    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }
}
