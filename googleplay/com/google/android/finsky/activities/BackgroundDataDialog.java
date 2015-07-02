package com.google.android.finsky.activities;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.config.G;

public class BackgroundDataDialog extends SimpleAlertDialog {
    private BackgroundDataSettingListener mListener;

    public interface BackgroundDataSettingListener {
        void onBackgroundDataNotEnabled();
    }

    public static void show(FragmentManager fragmentManager, Activity mainActivity) {
        if (fragmentManager.findFragmentByTag("bg_data_dialog") == null) {
            BackgroundDataDialog dialog = new BackgroundDataDialog();
            if (mainActivity instanceof BackgroundDataSettingListener) {
                dialog.mListener = (BackgroundDataSettingListener) mainActivity;
            }
            new Builder().setTitleId(R.string.background_data_prompt_title).setNegativeId(R.string.background_data_prompt_cancel).setMessageHtml(mainActivity.getString(R.string.background_data_error, new Object[]{G.helpCenterBackgroundDataUrl.get()})).setCanceledOnTouchOutside(false).setEventLog(320, null, -1, -1, FinskyApp.get().getCurrentAccount()).configureDialog(dialog);
            dialog.show(fragmentManager, "bg_data_dialog");
        }
    }

    public static void dismissExisting(FragmentManager fragmentManager) {
        Fragment previousBgDataDialog = fragmentManager.findFragmentByTag("bg_data_dialog");
        if (previousBgDataDialog != null) {
            ((DialogFragment) previousBgDataDialog).dismiss();
        }
    }

    protected void onNegativeClick() {
        super.onNegativeClick();
        if (this.mListener != null) {
            this.mListener.onBackgroundDataNotEnabled();
        }
    }
}
