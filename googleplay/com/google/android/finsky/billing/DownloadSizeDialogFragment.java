package com.google.android.finsky.billing;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;

public class DownloadSizeDialogFragment extends DialogFragment {

    public interface Listener {
        void onDownloadCancel();

        void onDownloadOk(boolean z);

        void onSetupWifi();
    }

    public static DownloadSizeDialogFragment newInstance(Fragment targetFragment, Bundle arguments) {
        if (targetFragment == null || (targetFragment instanceof Listener)) {
            DownloadSizeDialogFragment dialog = new DownloadSizeDialogFragment();
            if (targetFragment != null) {
                dialog.setTargetFragment(targetFragment, -1);
            }
            dialog.setArguments(arguments);
            return dialog;
        }
        throw new IllegalArgumentException("targetFragment must implement DownloadSizeDialog.Listener");
    }

    public static Bundle makeArguments(boolean setWifiOnlyOption, boolean showWifiOnlyOption, boolean onMobileNetwork) {
        Bundle arguments = new Bundle();
        arguments.putBoolean("setWifiOnly", setWifiOnlyOption);
        arguments.putBoolean("showWifiOnly", showWifiOnlyOption);
        arguments.putBoolean("onMobileNetwork", onMobileNetwork);
        return arguments;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int messageTextId;
        Bundle arguments = getArguments();
        final boolean showWifiOnlyOption = arguments.getBoolean("showWifiOnly");
        final boolean setWifiOnlyOption = arguments.getBoolean("setWifiOnly");
        boolean onMobileNetwork = arguments.getBoolean("onMobileNetwork");
        Context context = getActivity();
        ContextThemeWrapper wrappedContext = new ContextThemeWrapper(context, R.style.FinskyDialogTheme);
        Builder builder = new Builder(wrappedContext);
        builder.setTitle(R.string.use_wifi_title);
        View contentLayout = LayoutInflater.from(wrappedContext).inflate(R.layout.download_size_dialog, null);
        TextView messageView = (TextView) contentLayout.findViewById(R.id.wifi_message);
        if (showWifiOnlyOption) {
            messageTextId = R.string.use_wifi_warning;
        } else {
            messageTextId = onMobileNetwork ? R.string.use_wifi_limit_on_mobile : R.string.use_wifi_limit_on_wifi;
        }
        messageView.setText(messageTextId);
        final CheckBox checkboxView = (CheckBox) contentLayout.findViewById(R.id.wifi_checkbox);
        if (showWifiOnlyOption) {
            checkboxView.setVisibility(0);
            if (savedInstanceState == null) {
                checkboxView.setChecked(setWifiOnlyOption);
            }
        }
        builder.setView(contentLayout);
        builder.setPositiveButton(R.string.use_wifi_proceed_button, new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                DownloadSizeDialogFragment.this.getListener().onDownloadOk(showWifiOnlyOption ? checkboxView.isChecked() : setWifiOnlyOption);
            }
        });
        builder.setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DownloadSizeDialogFragment.this.getListener().onDownloadCancel();
            }
        });
        if (FinskyApp.get().getInstallPolicies().isMobileNetwork()) {
            if (context.getPackageManager().queryIntentActivities(new Intent("android.settings.WIFI_SETTINGS"), 65536).size() > 0) {
                builder.setNeutralButton(R.string.setup_wifi_button, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        DownloadSizeDialogFragment.this.getListener().onSetupWifi();
                    }
                });
            }
        }
        return builder.create();
    }

    public void onCancel(DialogInterface dialog) {
        getListener().onDownloadCancel();
        super.onCancel(dialog);
    }

    private Listener getListener() {
        if (getTargetFragment() != null) {
            return (Listener) getTargetFragment();
        }
        return (Listener) getActivity();
    }
}
