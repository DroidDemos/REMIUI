package com.google.android.finsky.layout;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.GmsCoreHelper;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;

public class AutoUpdateSection extends SeparatorLinearLayout {
    private CheckBox mCheckBox;
    private TextView mLabel;

    public static class AutoUpdateDialog extends DialogFragment implements OnClickListener {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Builder b = new Builder(getActivity());
            b.setMessage(R.string.auto_update_enable_dialog_message);
            b.setPositiveButton(R.string.yes, this);
            b.setNegativeButton(R.string.no, this);
            return b.create();
        }

        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case -2:
                    dismiss();
                    break;
                case -1:
                    dismiss();
                    FinskyPreferences.AUTO_UPDATE_ENABLED.put(Boolean.valueOf(true));
                    FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.put(Boolean.valueOf(true));
                    break;
            }
            Activity activity = getActivity();
            if (activity != null && (activity instanceof MainActivity)) {
                ((MainActivity) activity).updateBreadcrumb(null);
            }
        }
    }

    public AutoUpdateSection(Context context) {
        this(context, null);
    }

    public AutoUpdateSection(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCheckBox = (CheckBox) findViewById(R.id.auto_update_checkbox);
        this.mLabel = (TextView) findViewById(R.id.auto_update_checkbox_label);
    }

    public static boolean isAutoUpdateVisible(String packageName, Libraries libraries, AppStates appStates, Installer installer) {
        if (packageName == null || GmsCoreHelper.isGmsCore(packageName) || libraries.getAppOwners(packageName).isEmpty()) {
            return false;
        }
        AppState appState = appStates.getApp(packageName);
        if (appState == null) {
            return false;
        }
        boolean isInstalled;
        if (appState.packageManagerState != null) {
            isInstalled = true;
        } else {
            isInstalled = false;
        }
        InstallerState installerState = installer.getState(packageName);
        if (!isInstalled && !installerState.isDownloadingOrInstalling()) {
            return false;
        }
        if (isInstalled && appState.packageManagerState.isDisabled) {
            return false;
        }
        return true;
    }

    public static boolean isAutoUpdateEnabled(String docId) {
        AppState appState = FinskyApp.get().getAppStates().getApp(docId);
        boolean isAutoUpdateEnabled = ((Boolean) FinskyPreferences.AUTO_UPDATE_ENABLED.get()).booleanValue();
        if (appState.installerData != null) {
            return isAutoUpdateEnabled && appState.installerData.getAutoUpdate() == AutoUpdateState.USE_GLOBAL;
        } else {
            return isAutoUpdateEnabled;
        }
    }

    public static void handleAutoUpdateButtonClick(FragmentActivity activity, NavigationManager navMgr, boolean showToast) {
        boolean isAutoUpdateEnabledNewSetting = false;
        Document doc = navMgr.getCurrentDocument();
        if (doc == null) {
            FinskyLog.wtf("tried to operate on a null doc", new Object[0]);
        } else if (doc.getBackend() != 3) {
            FinskyLog.wtf("tried to operate on a non-apps doc.", new Object[0]);
        } else {
            String docId = doc.getDocId();
            if (!isAutoUpdateEnabled(docId)) {
                isAutoUpdateEnabledNewSetting = true;
            }
            updateAutoUpdateForThisApp(activity.getSupportFragmentManager(), FinskyApp.get().getAppStates(), docId, isAutoUpdateEnabledNewSetting, showToast, activity);
        }
    }

    private static void updateAutoUpdateForThisApp(FragmentManager fragmentMgr, AppStates appStates, String packageName, boolean isAutoUpdateEnabledNewSetting, boolean showToast, Context context) {
        AppState appState = FinskyApp.get().getAppStates().getApp(packageName);
        boolean oldEnabled = appState.installerData != null && appState.installerData.getAutoUpdate() == AutoUpdateState.USE_GLOBAL;
        appStates.getInstallerDataStore().setAutoUpdate(packageName, isAutoUpdateEnabledNewSetting ? AutoUpdateState.USE_GLOBAL : AutoUpdateState.DISABLED);
        if (isAutoUpdateEnabledNewSetting && !((Boolean) FinskyPreferences.AUTO_UPDATE_ENABLED.get()).booleanValue()) {
            new AutoUpdateDialog().show(fragmentMgr, "auto_update_dialog");
        } else if (showToast) {
            Toast.makeText(context, isAutoUpdateEnabledNewSetting ? R.string.toast_allow_auto_updating : R.string.toast_always_update_manually, 1).show();
        }
        FinskyApp.get().getEventLogger().logSettingsForPackageEvent(403, isAutoUpdateEnabledNewSetting ? 1 : 0, Integer.valueOf(oldEnabled ? 1 : 0), packageName);
    }
}
