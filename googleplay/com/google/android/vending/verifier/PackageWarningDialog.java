package com.google.android.vending.verifier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;

public class PackageWarningDialog extends Activity implements OnCheckedChangeListener, ClickListener {
    private static String KEY_ACTION;
    private static String KEY_APP_NAME;
    private static String KEY_MESSAGE;
    private static String KEY_PACKAGE_NAME;
    private static String KEY_RESPONSE_TOKEN;
    private static String KEY_VERIFICATION_ID;
    public static int NO_ID;
    private int mAction;
    private ButtonBar mButtonBar;
    private CheckBox mCheckBox;
    private int mId;
    private String mPackageName;
    private byte[] mResponseToken;
    private boolean mUserChoiceWasReported;

    public PackageWarningDialog() {
        this.mUserChoiceWasReported = false;
    }

    static {
        NO_ID = -1;
        KEY_VERIFICATION_ID = "verification_id";
        KEY_ACTION = "action";
        KEY_APP_NAME = "app_name";
        KEY_MESSAGE = "message";
        KEY_PACKAGE_NAME = "package_name";
        KEY_RESPONSE_TOKEN = "response_token";
    }

    public static Intent createIntent(Context context, int id, int action, String appName, String packageName, String message, byte[] responseToken) {
        Intent intent = new Intent(context, PackageWarningDialog.class);
        intent.setFlags(1342177280);
        intent.putExtra(KEY_VERIFICATION_ID, id);
        intent.putExtra(KEY_ACTION, action);
        intent.putExtra(KEY_APP_NAME, appName);
        intent.putExtra(KEY_MESSAGE, message);
        intent.putExtra(KEY_PACKAGE_NAME, packageName);
        intent.putExtra(KEY_RESPONSE_TOKEN, responseToken);
        return intent;
    }

    public static void show(Context context, int id, int action, String appName, String message) {
        context.startActivity(createIntent(context, id, action, appName, null, message, null));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_malware_dialog);
        if (VERSION.SDK_INT >= 11) {
            clearFinishOnTouchOutside();
        }
        Intent intent = getIntent();
        this.mId = intent.getIntExtra(KEY_VERIFICATION_ID, NO_ID);
        this.mAction = intent.getIntExtra(KEY_ACTION, 0);
        String appName = intent.getStringExtra(KEY_APP_NAME);
        String message = intent.getStringExtra(KEY_MESSAGE);
        this.mPackageName = intent.getStringExtra(KEY_PACKAGE_NAME);
        this.mResponseToken = intent.getByteArrayExtra(KEY_RESPONSE_TOKEN);
        if (this.mId == NO_ID || PackageVerificationService.registerDialog(this.mId, this)) {
            ImageView badgeView = (ImageView) findViewById(R.id.badge);
            TextView bannerView = (TextView) findViewById(R.id.banner);
            TextView messageView = (TextView) findViewById(R.id.message);
            TextView appNameView = (TextView) findViewById(R.id.app_name);
            this.mCheckBox = (CheckBox) findViewById(R.id.confirm);
            if (!TextUtils.isEmpty(message)) {
                messageView.setText(message);
            }
            if (!TextUtils.isEmpty(appName)) {
                appNameView.setText(appName);
            }
            this.mButtonBar = (ButtonBar) findViewById(R.id.button_bar);
            this.mButtonBar.setClickListener(this);
            switch (this.mAction) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    bannerView.setText(R.string.package_malware_banner_warning);
                    badgeView.setImageResource(R.drawable.ic_shield_warning_dark);
                    this.mCheckBox.setText(R.string.package_malware_checkbox_label_i_understand);
                    this.mCheckBox.setOnCheckedChangeListener(this);
                    this.mButtonBar.setPositiveButtonTitle((int) R.string.package_malware_install);
                    this.mButtonBar.setNegativeButtonTitle((int) R.string.package_malware_cancel);
                    this.mButtonBar.setPositiveButtonEnabled(false);
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    bannerView.setText(R.string.package_malware_banner_blocked);
                    badgeView.setImageResource(R.drawable.ic_shield_dark);
                    this.mCheckBox.setVisibility(8);
                    this.mButtonBar.setPositiveButtonVisible(false);
                    this.mButtonBar.setNegativeButtonTitle((int) R.string.ok);
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bannerView.setText(R.string.package_malware_banner_request_removal);
                    badgeView.setImageResource(R.drawable.ic_shield_warning_dark);
                    this.mCheckBox.setText(R.string.package_malware_checkbox_label_dont_warn);
                    this.mCheckBox.setOnCheckedChangeListener(this);
                    this.mButtonBar.setPositiveButtonTitle((int) R.string.package_malware_uninstall);
                    this.mButtonBar.setNegativeButtonTitle((int) R.string.package_malware_keep);
                    FinskyApp.get().getNotifier().hidePackageRemoveRequestMessage(this.mPackageName);
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bannerView.setText(R.string.package_malware_banner_removed);
                    badgeView.setImageResource(R.drawable.ic_shield_dark);
                    this.mCheckBox.setVisibility(8);
                    this.mButtonBar.setPositiveButtonVisible(false);
                    this.mButtonBar.setNegativeButtonTitle((int) R.string.ok);
                    return;
                default:
                    return;
            }
        }
        finish();
    }

    private void clearFinishOnTouchOutside() {
        setFinishOnTouchOutside(false);
    }

    public void onBackPressed() {
    }

    protected void onDestroy() {
        if (!this.mUserChoiceWasReported && isFinishing()) {
            if (this.mAction == 0) {
                PackageVerificationService.reportUserChoice(this.mId, -1);
            } else if (this.mAction == 2) {
                PackageVerificationService.sendRemovalResponse(this, this.mPackageName, false, this.mCheckBox.isChecked(), this.mResponseToken);
            }
        }
        PackageVerificationService.registerDialog(this.mId, null);
        super.onDestroy();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (this.mAction == 0) {
            this.mButtonBar.setPositiveButtonEnabled(isChecked);
        }
    }

    public void onPositiveButtonClick() {
        if (this.mAction == 0) {
            PackageVerificationService.reportUserChoice(this.mId, 1);
            PackageVerificationService.registerDialog(this.mId, null);
        } else if (this.mAction == 2) {
            PackageVerificationService.sendRemovalResponse(this, this.mPackageName, true, this.mCheckBox.isChecked(), this.mResponseToken);
        }
        this.mUserChoiceWasReported = true;
        finish();
    }

    public void onNegativeButtonClick() {
        if (this.mAction == 0) {
            PackageVerificationService.reportUserChoice(this.mId, -1);
            PackageVerificationService.registerDialog(this.mId, null);
        } else if (this.mAction == 2) {
            PackageVerificationService.sendRemovalResponse(this, this.mPackageName, false, this.mCheckBox.isChecked(), this.mResponseToken);
        }
        this.mUserChoiceWasReported = true;
        finish();
    }
}
