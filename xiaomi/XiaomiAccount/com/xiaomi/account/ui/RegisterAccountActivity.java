package com.xiaomi.account.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.account.utils.FriendlyFragmentUtils;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.ui.InputPasswordFragment;
import com.xiaomi.passport.ui.InputPhoneFragment;
import com.xiaomi.passport.ui.SimpleDialogFragment;
import com.xiaomi.passport.ui.SimpleDialogFragment.AlertDialogFragmentBuilder;
import com.xiaomi.passport.ui.SmsAlertFragment;
import com.xiaomi.passport.utils.AccountHelper;
import java.util.concurrent.Future;
import miui.analytics.Analytics;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.app.AlertDialog.Builder;
import miui.app.ProgressDialog;
import miui.os.Build;
import miui.telephony.CloudTelephonyManager;

public class RegisterAccountActivity extends Activity {
    private static final boolean DEBUG = true;
    private static final int DIALOG_CHECK_FIELDS_PROGRESS = 2;
    private static final int DIALOG_REG_FAILED = 4;
    private static final int DIALOG_REG_PROGRESS = 3;
    private static final int DIALOG_SEND_SMS_FAILURE = 1;
    private static final int EMAIL_REG_FAILED = 5;
    public static final int ERROR_EMAIL_USED = 9;
    public static final int ERROR_INVALID_DEV_ID = 6;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_PHONE_ERROR = 8;
    public static final int ERROR_PHONE_USED = 1;
    public static final int ERROR_REQUIRE_CAPTCHA = 10;
    public static final int ERROR_SERVER = 3;
    public static final int ERROR_SIM_NOT_READY = 4;
    public static final int ERROR_UNKNOWN = 5;
    public static final int ERROR_VERIFY_CODE = 7;
    public static final String REG_EMAIL_USED = "reg_email_used";
    private static final int SHOW_SYNC_REQUEST_CODE = 1;
    private static final String TAG = "RegisterAccountActivity";
    private Analytics mAnalytics;
    private ProgressDialog mCheckFieldsDialog;
    private Future<Bundle> mCheckFieldsTask;
    private boolean mDisableBackKey;
    private boolean mFindPasswordOnPc;
    Intent mIntent;
    private String mPackageName;
    private ProgressDialog mRegDialogProgress;
    private boolean mShowAccountSettings;
    private SmsAlertFragment mSmsAlertFragment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mIntent = getIntent();
        this.mDisableBackKey = this.mIntent.getBooleanExtra(Constants.EXTRA_DISABLE_BACK_KEY, false);
        this.mFindPasswordOnPc = this.mIntent.getBooleanExtra(Constants.EXTRA_FIND_PASSWORD_ON_PC, false);
        this.mPackageName = this.mIntent.getStringExtra(AccountManager.KEY_ANDROID_PACKAGE_NAME);
        this.mShowAccountSettings = this.mIntent.getBooleanExtra(Constants.EXTRA_SHOW_ACCOUNT_SETTINGS, false);
        if (Build.IS_TABLET) {
            setContentView(R.layout.passport_activity_login_reg_layout);
            ((TextView) findViewById(16908310)).setText(R.string.title_reg);
        } else {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(R.string.title_reg);
            }
        }
        if (hasSimInserted()) {
            upLinkRegister();
        } else {
            downLinkRegister();
        }
        this.mAnalytics = Analytics.getInstance();
        this.mAnalytics.startSession(this);
    }

    private boolean hasSimInserted() {
        try {
            return CloudTelephonyManager.getAvailableSimCount() > 0 ? DEBUG : false;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    private void upLinkRegister() {
        InputPasswordFragment passwordFragment = new InputPasswordFragment();
        if (Build.IS_TABLET) {
            FriendlyFragmentUtils.addFragment(getFragmentManager(), R.id.content_fragment, passwordFragment);
        } else {
            FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, passwordFragment);
        }
    }

    private void downLinkRegister() {
        InputPhoneFragment inputPhoneFragment = new InputPhoneFragment();
        if (Build.IS_TABLET) {
            FriendlyFragmentUtils.addFragment(getFragmentManager(), R.id.content_fragment, inputPhoneFragment);
        } else {
            FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, inputPhoneFragment);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SHOW_SYNC_REQUEST_CODE /*1*/:
                if (resultCode == -1 && this.mShowAccountSettings) {
                    navigateToSettings();
                }
                AccountHelper.setLoginResultAndFinish(this, -1);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
        if (!this.mDisableBackKey) {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                startActivity(SysHelper.buildPreviousActivityIntent(this, getIntent(), WelcomeActivity.class));
                return DEBUG;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onResume() {
        super.onResume();
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
    }

    protected Dialog onCreateDialog(int id, Bundle args) {
        Builder builder;
        View view;
        Dialog dialog;
        switch (id) {
            case SHOW_SYNC_REQUEST_CODE /*1*/:
                builder = new Builder(this);
                builder.setMessage(getString(R.string.error_no_sms_service));
                builder.setPositiveButton(17039360, null);
                return builder.create();
            case ERROR_NETWORK /*2*/:
                this.mCheckFieldsDialog = new ProgressDialog(this);
                this.mCheckFieldsDialog.setTitle(R.string.checking_input);
                this.mCheckFieldsDialog.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        if (RegisterAccountActivity.this.mCheckFieldsTask != null) {
                            RegisterAccountActivity.this.mCheckFieldsTask.cancel(RegisterAccountActivity.DEBUG);
                            RegisterAccountActivity.this.mCheckFieldsTask = null;
                        }
                    }
                });
                return this.mCheckFieldsDialog;
            case ERROR_SERVER /*3*/:
                this.mRegDialogProgress = new ProgressDialog(this);
                this.mRegDialogProgress.setMessage(getString(R.string.reging));
                this.mRegDialogProgress.setCancelable(false);
                return this.mRegDialogProgress;
            case ERROR_SIM_NOT_READY /*4*/:
                builder = new Builder(this);
                builder.setTitle(R.string.reg_failed);
                builder.setMessage(args.getString("reason"));
                if (args.getInt(Constants.KEY_RESULT) == SHOW_SYNC_REQUEST_CODE || args.getInt(Constants.KEY_RESULT) == ERROR_EMAIL_USED) {
                    view = LayoutInflater.from(this).inflate(R.layout.reg_failed_dup_phone_dialog, null);
                    builder.setView(view);
                    Button regButton = (Button) view.findViewById(R.id.login_instead_reg);
                    view.findViewById(R.id.login_instead_reg).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(Constants.ACTION_LOGIN);
                            intent.setPackage(RegisterAccountActivity.this.getPackageName());
                            intent.putExtras(RegisterAccountActivity.this.getIntent());
                            RegisterAccountActivity.this.startActivity(intent);
                            RegisterAccountActivity.this.finish();
                        }
                    });
                    if (args.getInt(Constants.KEY_RESULT) == ERROR_EMAIL_USED) {
                        regButton.setText(R.string.login_instead_reg_with_email);
                    }
                    view.findViewById(R.id.get_back_pwd).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            if (RegisterAccountActivity.this.mFindPasswordOnPc) {
                                RegisterAccountActivity.this.showFindPasswordOnWebDialog();
                            } else {
                                SysHelper.getbackPassword(RegisterAccountActivity.this);
                            }
                        }
                    });
                    view.findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            RegisterAccountActivity.this.removeDialog(RegisterAccountActivity.ERROR_SIM_NOT_READY);
                        }
                    });
                } else {
                    builder.setPositiveButton(17039360, null);
                }
                dialog = builder.create();
                dialog.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        RegisterAccountActivity.this.removeDialog(RegisterAccountActivity.ERROR_SIM_NOT_READY);
                    }
                });
                return dialog;
            case ERROR_UNKNOWN /*5*/:
                builder = new Builder(this);
                builder.setTitle(R.string.reg_failed);
                builder.setMessage(args.getString("reason"));
                if (args.getInt(Constants.KEY_RESULT) == ERROR_EMAIL_USED) {
                    view = LayoutInflater.from(this).inflate(R.layout.reg_failed_dup_phone_dialog, null);
                    builder.setView(view);
                    view.findViewById(R.id.login_instead_reg).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(Constants.ACTION_LOGIN);
                            intent.setPackage(RegisterAccountActivity.this.getPackageName());
                            intent.putExtras(RegisterAccountActivity.this.getIntent());
                            RegisterAccountActivity.this.startActivity(intent);
                            RegisterAccountActivity.this.finish();
                        }
                    });
                    view.findViewById(R.id.get_back_pwd).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            if (RegisterAccountActivity.this.mFindPasswordOnPc) {
                                RegisterAccountActivity.this.showFindPasswordOnWebDialog();
                            } else {
                                SysHelper.getbackPassword(RegisterAccountActivity.this);
                            }
                        }
                    });
                    view.findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            RegisterAccountActivity.this.removeDialog(RegisterAccountActivity.ERROR_SIM_NOT_READY);
                        }
                    });
                } else {
                    builder.setPositiveButton(17039360, null);
                }
                dialog = builder.create();
                dialog.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        RegisterAccountActivity.this.removeDialog(RegisterAccountActivity.ERROR_SIM_NOT_READY);
                    }
                });
                return dialog;
            default:
                return super.onCreateDialog(id, args);
        }
    }

    private void showFindPasswordOnWebDialog() {
        SimpleDialogFragment dialog = new AlertDialogFragmentBuilder(SHOW_SYNC_REQUEST_CODE).setTitle(getString(R.string.forget_password)).setMessage(getString(R.string.find_password_on_web_msg)).create();
        dialog.setNegativeButton(R.string.re_register, null);
        dialog.setPositiveButton(R.string.skip_register, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AccountHelper.setLoginResultAndFinish(RegisterAccountActivity.this, -1);
            }
        });
        dialog.show(getFragmentManager(), "FindPassword");
    }

    protected void onDestroy() {
        this.mAnalytics.endSession();
        if (this.mCheckFieldsTask != null) {
            this.mCheckFieldsTask.cancel(DEBUG);
        }
        super.onDestroy();
    }

    private void navigateToSettings() {
        Intent settingsIntent = new Intent(this, AccountSettingsActivity.class);
        settingsIntent.putExtras(getIntent());
        startActivity(settingsIntent);
    }

    private void dismissDialogs() {
        if (this.mCheckFieldsDialog != null && this.mCheckFieldsDialog.isShowing()) {
            removeDialog(ERROR_NETWORK);
        }
        this.mCheckFieldsDialog = null;
        if (this.mRegDialogProgress != null && this.mRegDialogProgress.isShowing()) {
            removeDialog(ERROR_SERVER);
        }
        this.mRegDialogProgress = null;
    }
}
