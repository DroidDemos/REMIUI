package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.installer.InstallPolicies.InstallWarnings;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PermissionsBucketer;
import com.google.android.play.layout.PlayActionButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MultiInstallActivity extends FragmentActivity implements OnClickListener {
    private InstallApprovalFragment mCurrentFragment;
    private int mCurrentInstallIndex;
    private int mCurrentPageType;
    private String mInstallAccountName;
    private Installer mInstaller;
    private ArrayList<InstallDetails> mInstallsForApproval;
    private int mMode;
    private PlayActionButton mNegativeButton;
    private PlayActionButton mPositiveButton;
    private boolean mShowPermissionBucketsLearnMoreHeader;

    private static class InstallDetails implements Parcelable {
        public static final Creator<InstallDetails> CREATOR;
        private boolean autoUpdateDisabled;
        private final String docId;
        private boolean largeDownload;
        private boolean newPermissions;
        private final String packageName;
        private final String packageTitle;
        private final String[] permissions;
        private final String reason;
        private final int versionCode;

        public InstallDetails(Document document, InstallWarnings installWarnings, String reason) {
            this.autoUpdateDisabled = installWarnings.autoUpdateDisabled;
            this.largeDownload = installWarnings.largeDownload;
            this.newPermissions = installWarnings.newPermissions;
            this.docId = document.getDocId();
            this.packageName = document.getDocId();
            this.packageTitle = document.getTitle();
            this.versionCode = document.getVersionCode();
            this.reason = reason;
            if (this.newPermissions) {
                this.permissions = document.getAppDetails().permission;
            } else {
                this.permissions = null;
            }
        }

        public boolean needsAutoUpdateWarning() {
            return this.autoUpdateDisabled;
        }

        public void setAutoUpdateDisableWarningAccepted() {
            this.autoUpdateDisabled = false;
        }

        public boolean needsLargeDownloadWarning() {
            return this.largeDownload;
        }

        public void setLargeDownloadWarningAccepted() {
            this.largeDownload = false;
        }

        public boolean needsPermissionsWarning() {
            return this.newPermissions;
        }

        public void setPermissionsWarningAccepted() {
            this.newPermissions = false;
        }

        public boolean done() {
            return (this.largeDownload || this.newPermissions || this.autoUpdateDisabled) ? false : true;
        }

        protected InstallDetails(Parcel in) {
            boolean z;
            boolean z2 = true;
            this.largeDownload = in.readByte() > (byte) 0;
            if (in.readByte() > (byte) 0) {
                z = true;
            } else {
                z = false;
            }
            this.newPermissions = z;
            if (in.readByte() <= (byte) 0) {
                z2 = false;
            }
            this.autoUpdateDisabled = z2;
            this.docId = in.readString();
            this.packageName = in.readString();
            this.packageTitle = in.readString();
            this.versionCode = in.readInt();
            this.reason = in.readString();
            if (this.newPermissions) {
                this.permissions = new String[in.readInt()];
                in.readStringArray(this.permissions);
                return;
            }
            this.permissions = null;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            int i;
            int i2 = 1;
            dest.writeByte((byte) (this.largeDownload ? 1 : 0));
            if (this.newPermissions) {
                i = 1;
            } else {
                i = 0;
            }
            dest.writeByte((byte) i);
            if (!this.autoUpdateDisabled) {
                i2 = 0;
            }
            dest.writeByte((byte) i2);
            dest.writeString(this.docId);
            dest.writeString(this.packageName);
            dest.writeString(this.packageTitle);
            dest.writeInt(this.versionCode);
            dest.writeString(this.reason);
            if (this.newPermissions) {
                dest.writeInt(this.permissions.length);
                dest.writeStringArray(this.permissions);
            }
        }

        static {
            CREATOR = new Creator<InstallDetails>() {
                public InstallDetails createFromParcel(Parcel in) {
                    return new InstallDetails(in);
                }

                public InstallDetails[] newArray(int size) {
                    return new InstallDetails[size];
                }
            };
        }
    }

    public static Intent getInstallIntent(Context context, List<Document> documents, String accountName) {
        Intent intent = new Intent(context, MultiInstallActivity.class);
        intent.putExtra("MultiInstallActivity.installs", Lists.newArrayList((Collection) documents));
        intent.putExtra("MultiInstallActivity.mode", 1);
        intent.putExtra("MultiInstallActivity.install-account-name", accountName);
        return intent;
    }

    public static Intent getUpdateIntent(Context context, List<Document> documents) {
        Intent intent = new Intent(context, MultiInstallActivity.class);
        intent.putExtra("MultiInstallActivity.installs", Lists.newArrayList((Collection) documents));
        intent.putExtra("MultiInstallActivity.mode", 2);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_all_activity);
        this.mInstaller = FinskyApp.get().getInstaller();
        this.mPositiveButton = (PlayActionButton) findViewById(R.id.positive_button);
        this.mPositiveButton.configure(3, (int) R.string.ok, (OnClickListener) this);
        this.mNegativeButton = (PlayActionButton) findViewById(R.id.negative_button);
        this.mNegativeButton.configure(3, getResources().getString(R.string.cancel), (OnClickListener) this);
        this.mMode = getIntent().getIntExtra("MultiInstallActivity.mode", 1);
        this.mInstallAccountName = getIntent().getStringExtra("MultiInstallActivity.install-account-name");
        if (savedInstanceState == null) {
            this.mCurrentInstallIndex = 0;
            this.mCurrentPageType = 0;
            installAndShowWarnings(getIntent().getParcelableArrayListExtra("MultiInstallActivity.installs"));
            return;
        }
        this.mInstallsForApproval = savedInstanceState.getParcelableArrayList("MultiInstallActivity.installs-for-approval");
        this.mShowPermissionBucketsLearnMoreHeader = savedInstanceState.getBoolean("MultiInstallActivity.show-learn-more-header");
        this.mCurrentInstallIndex = savedInstanceState.getInt("MultiInstallActivity.current-install-index", 0);
        this.mCurrentPageType = savedInstanceState.getInt("MultiInstallActivity.current-page-type", 0);
        setButtonText(this.mCurrentPageType);
        this.mCurrentFragment = (InstallApprovalFragment) getSupportFragmentManager().findFragmentById(R.id.main_layout);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("MultiInstallActivity.installs-for-approval", this.mInstallsForApproval);
        outState.putBoolean("MultiInstallActivity.show-learn-more-header", this.mShowPermissionBucketsLearnMoreHeader);
        outState.putInt("MultiInstallActivity.current-install-index", this.mCurrentInstallIndex);
        outState.putInt("MultiInstallActivity.current-page-type", this.mCurrentPageType);
    }

    private void installAndShowWarnings(List<Document> documents) {
        ArrayList<InstallDetails> installsWithNoWarnings = Lists.newArrayList();
        ArrayList<InstallDetails> installsWithWarnings = Lists.newArrayList();
        splitDocumentsOnWarnings(documents, this.mMode, installsWithNoWarnings, installsWithWarnings);
        performBulkInstall(installsWithNoWarnings);
        this.mInstallsForApproval = installsWithWarnings;
        if (((Boolean) FinskyPreferences.hasSeenPermissionBucketsLearnMore.get()).booleanValue()) {
            this.mShowPermissionBucketsLearnMoreHeader = false;
        } else {
            Iterator i$ = this.mInstallsForApproval.iterator();
            while (i$.hasNext()) {
                if (((InstallDetails) i$.next()).needsPermissionsWarning()) {
                    this.mShowPermissionBucketsLearnMoreHeader = true;
                }
            }
        }
        displayPageOrFinish(true);
    }

    private static void splitDocumentsOnWarnings(List<Document> documents, int mode, ArrayList<InstallDetails> installsWithNoWarnings, ArrayList<InstallDetails> installsWithWarnings) {
        if (!(installsWithNoWarnings.isEmpty() && installsWithWarnings.isEmpty())) {
            FinskyLog.wtf("The output lists are not initially empty.", new Object[0]);
        }
        InstallPolicies installPolicies = FinskyApp.get().getInstallPolicies();
        String reason = mode == 2 ? "bulk_update" : "bulk_install";
        boolean treatDisabledUpdatesAsWarnings = ((Boolean) FinskyPreferences.AUTO_UPDATE_ENABLED.get()).booleanValue();
        for (Document doc : documents) {
            InstallWarnings installWarnings;
            if (mode == 2) {
                installWarnings = installPolicies.getUpdateWarningsForDocument(doc, treatDisabledUpdatesAsWarnings);
            } else {
                installWarnings = installPolicies.getInstallWarningsForDocument(doc);
            }
            InstallDetails installDetails = new InstallDetails(doc, installWarnings, reason);
            if (installDetails.done()) {
                installsWithNoWarnings.add(installDetails);
            } else {
                installsWithWarnings.add(installDetails);
            }
        }
    }

    private void setButtonText(int pageType) {
        int positiveStringId;
        switch (pageType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                positiveStringId = R.string.install_approval_auto_update_disabled_yes;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                positiveStringId = R.string.install_approval_large_size_yes;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                positiveStringId = R.string.install_approval_permissions_yes;
                break;
            default:
                FinskyLog.wtf("Invalid current page type: %d", Integer.valueOf(pageType));
                positiveStringId = R.string.install_approval_permissions_yes;
                break;
        }
        this.mPositiveButton.setText(getResources().getString(positiveStringId).toUpperCase());
        this.mNegativeButton.setText(getResources().getString(R.string.install_approval_no).toUpperCase());
    }

    private void showInstallApprovalPage(int installIndex, int pageType, boolean fadeInAnimation) {
        setButtonText(pageType);
        InstallDetails installDetails = (InstallDetails) this.mInstallsForApproval.get(installIndex);
        Fragment nextFragment = InstallApprovalFragment.newInstance(installDetails.packageName, installDetails.packageTitle, installIndex + 1, this.mInstallsForApproval.size(), getApprovalType(), installDetails.permissions, this.mShowPermissionBucketsLearnMoreHeader);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fadeInAnimation) {
            transaction.setCustomAnimations(R.anim.play_fade_in, R.anim.fade_out);
        } else {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if (this.mCurrentFragment != null) {
            transaction.remove(this.mCurrentFragment);
        }
        transaction.add((int) R.id.main_layout, nextFragment);
        transaction.commit();
        this.mCurrentFragment = nextFragment;
        this.mPositiveButton.setEnabled(true);
        this.mNegativeButton.setEnabled(true);
    }

    private int getApprovalType() {
        switch (this.mCurrentPageType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 1;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 3;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 2;
            default:
                FinskyLog.wtf("Invalid current page type: %d", Integer.valueOf(this.mCurrentPageType));
                return 2;
        }
    }

    private void displayPageOrFinish(boolean sameInstallAsPrevPage) {
        if (this.mCurrentInstallIndex >= this.mInstallsForApproval.size()) {
            finish();
            return;
        }
        InstallDetails installDetails = (InstallDetails) this.mInstallsForApproval.get(this.mCurrentInstallIndex);
        if (installDetails.needsAutoUpdateWarning()) {
            this.mCurrentPageType = 1;
        } else if (installDetails.needsLargeDownloadWarning()) {
            this.mCurrentPageType = 2;
        } else if (installDetails.needsPermissionsWarning()) {
            this.mCurrentPageType = 3;
        } else {
            FinskyLog.wtf("Failed to determine the next page type when updating %s.", installDetails.packageName);
            finish();
            return;
        }
        showInstallApprovalPage(this.mCurrentInstallIndex, this.mCurrentPageType, sameInstallAsPrevPage);
    }

    private void performBulkInstall(List<InstallDetails> installs) {
        for (InstallDetails install : installs) {
            performSingleInstall(install);
        }
    }

    private void performSingleInstall(InstallDetails install) {
        if (this.mMode == 2) {
            this.mInstaller.updateSingleInstalledApp(install.packageName, install.versionCode, install.packageTitle, install.reason, false, 3);
        } else {
            this.mInstaller.requestInstall(install.packageName, install.versionCode, this.mInstallAccountName, install.packageTitle, false, install.reason, 3);
        }
    }

    public void onClick(View view) {
        if (this.mCurrentPageType == 0) {
            FinskyLog.wtf("Unexpected click for page type: %d", Integer.valueOf(0));
            return;
        }
        if (view == this.mPositiveButton || view == this.mNegativeButton) {
            this.mPositiveButton.setEnabled(false);
            this.mNegativeButton.setEnabled(false);
        }
        if (view == this.mPositiveButton) {
            InstallDetails installDetails = (InstallDetails) this.mInstallsForApproval.get(this.mCurrentInstallIndex);
            switch (this.mCurrentPageType) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    installDetails.setAutoUpdateDisableWarningAccepted();
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    installDetails.setLargeDownloadWarningAccepted();
                    this.mInstaller.setMobileDataAllowed(installDetails.packageName);
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    installDetails.setPermissionsWarningAccepted();
                    PermissionsBucketer.setAcceptedNewBuckets(installDetails.docId);
                    FinskyPreferences.hasSeenPermissionBucketsLearnMore.put(Boolean.valueOf(true));
                    break;
            }
            if (installDetails.done()) {
                performSingleInstall(installDetails);
                this.mCurrentInstallIndex++;
                displayPageOrFinish(false);
                return;
            }
            displayPageOrFinish(true);
        } else if (view == this.mNegativeButton) {
            this.mCurrentInstallIndex++;
            displayPageOrFinish(false);
        }
    }
}
