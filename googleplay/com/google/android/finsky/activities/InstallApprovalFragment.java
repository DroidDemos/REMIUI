package com.google.android.finsky.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.AppPermissionAdapter;
import com.google.android.finsky.layout.AppSecurityPermissions;

public class InstallApprovalFragment extends Fragment {
    private TextView mContentTextView;
    private View mPermissionsLearnMoreHeader;
    private AppSecurityPermissions mPermissionsView;
    private TextView mProgressTextView;
    private Bundle mSavedInstanceState;
    private TextView mTitleView;

    public static InstallApprovalFragment newInstance(String packageName, String packageTitle, int installNumber, int totalInstalls, int approvalType, String[] permissions, boolean showLearnMoreHeader) {
        InstallApprovalFragment installApprovalFragment = new InstallApprovalFragment();
        Bundle arguments = new Bundle();
        arguments.putString("InstallApprovalFragment.packageName", packageName);
        arguments.putString("InstallApprovalFragment.packageTitle", packageTitle);
        arguments.putInt("InstallApprovalFragment.installNumber", installNumber);
        arguments.putInt("InstallApprovalFragment.totalInstalls", totalInstalls);
        arguments.putInt("InstallApprovalFragment.approvalType", approvalType);
        arguments.putBoolean("InstallApprovalFragment.showPermissionsLearnMoreHeader", showLearnMoreHeader);
        arguments.putStringArray("InstallApprovalFragment.permissions", permissions);
        installApprovalFragment.setArguments(arguments);
        return installApprovalFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSavedInstanceState = savedInstanceState;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mPermissionsView != null) {
            this.mPermissionsView.saveInstanceState(outState);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.install_approval_layout, container, false);
        this.mTitleView = (TextView) view.findViewById(R.id.title);
        this.mProgressTextView = (TextView) view.findViewById(R.id.progress_text);
        this.mContentTextView = (TextView) view.findViewById(R.id.content_text);
        this.mPermissionsLearnMoreHeader = view.findViewById(R.id.learn_more_header);
        this.mPermissionsView = (AppSecurityPermissions) view.findViewById(R.id.app_permissions);
        displayInfo();
        return view;
    }

    private void displayInfo() {
        Resources resources = getResources();
        Bundle arguments = getArguments();
        TextView textView = this.mProgressTextView;
        r14 = new Object[2];
        r14[0] = Integer.valueOf(arguments.getInt("InstallApprovalFragment.installNumber"));
        r14[1] = Integer.valueOf(arguments.getInt("InstallApprovalFragment.totalInstalls"));
        textView.setText(resources.getString(R.string.install_approval_progress_text, r14));
        switch (arguments.getInt("InstallApprovalFragment.approvalType")) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mTitleView.setText(R.string.install_approval_auto_update_disabled_title);
                textView = this.mContentTextView;
                r14 = new Object[1];
                r14[0] = arguments.getString("InstallApprovalFragment.packageTitle");
                textView.setText(Html.fromHtml(resources.getString(R.string.install_approval_auto_update_disabled_text, r14)));
                this.mContentTextView.setVisibility(0);
                this.mPermissionsView.setVisibility(8);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mTitleView.setText(R.string.permissions_title);
                String packageName = arguments.getString("InstallApprovalFragment.packageName");
                String[] permissions = arguments.getStringArray("InstallApprovalFragment.permissions");
                InstallerData installerData = FinskyApp.get().getAppStates().getInstallerDataStore().get(packageName);
                boolean hasAcceptedBuckets = installerData != null && installerData.getPermissionsVersion() == 1;
                AppPermissionAdapter adapter = new AppPermissionAdapter(getActivity(), packageName, permissions, hasAcceptedBuckets);
                this.mPermissionsView.bindInfo(adapter, getArguments().getString("InstallApprovalFragment.packageTitle"), this.mSavedInstanceState);
                this.mPermissionsView.setVisibility(0);
                this.mContentTextView.setVisibility(0);
                int headerId = (adapter.isAppInstalled() && hasAcceptedBuckets) ? R.string.app_also_needs_access_to : R.string.app_needs_access_to;
                this.mContentTextView.setText(Html.fromHtml(resources.getString(headerId, new Object[]{appName})));
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mTitleView.setText(R.string.install_approval_large_size_title);
                textView = this.mContentTextView;
                r14 = new Object[1];
                r14[0] = arguments.getString("InstallApprovalFragment.packageTitle");
                textView.setText(Html.fromHtml(resources.getString(R.string.install_approval_large_size_text, r14)));
                this.mContentTextView.setVisibility(0);
                this.mPermissionsView.setVisibility(8);
                break;
        }
        if (getArguments().getBoolean("InstallApprovalFragment.showPermissionsLearnMoreHeader")) {
            this.mPermissionsLearnMoreHeader.setVisibility(0);
            final String learnMoreLink = (String) G.permissionBucketsLearnMoreLink.get();
            if (!TextUtils.isEmpty(learnMoreLink)) {
                this.mPermissionsLearnMoreHeader.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        InstallApprovalFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(learnMoreLink)));
                    }
                });
            }
        }
    }
}
