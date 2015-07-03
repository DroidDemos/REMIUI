package com.xiaomi.passport.ui;

import android.app.ActionBar;
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
import com.xiaomi.account.R;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.ui.SimpleDialogFragment.AlertDialogFragmentBuilder;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.SysHelper;
import com.xiaomi.passport.widget.AlertDialog.Builder;
import com.xiaomi.passport.widget.ProgressDialog;
import java.util.concurrent.Future;

public class RegisterAccountActivity extends BaseActivity {
    private static final boolean DEBUG = true;
    protected static final int DIALOG_CHECK_FIELDS_PROGRESS = 2;
    protected static final int DIALOG_REG_FAILED = 4;
    protected static final int DIALOG_REG_PROGRESS = 3;
    protected static final int DIALOG_SEND_SMS_FAILURE = 1;
    private static final int EMAIL_REG_FAILED = 5;
    public static final String REG_EMAIL_USED = "reg_email_used";
    private static final String TAG = "RegisterAccountActivity";
    private AnalyticsTracker mAnalyticsTracker;
    private ProgressDialog mCheckFieldsDialog;
    private Future<Bundle> mCheckFieldsTask;
    private boolean mDisableBackKey;
    private boolean mFindPasswordOnPc;
    Intent mIntent;
    private boolean mOnSetupGuide;
    private String mPackageName;
    private ProgressDialog mRegDialogProgress;

    public void onCreate(Bundle savedInstanceState) {
        if (Build.IS_TABLET) {
            setTheme(R.style.Passport.Theme.Light.Dialog.FixedSize);
        }
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.passport_title_reg);
        }
        SysHelper.setOrientationPortrait(this);
        this.mIntent = getIntent();
        this.mDisableBackKey = this.mIntent.getBooleanExtra(Constants.EXTRA_DISABLE_BACK_KEY, false);
        this.mFindPasswordOnPc = this.mIntent.getBooleanExtra(Constants.EXTRA_FIND_PASSWORD_ON_PC, false);
        this.mOnSetupGuide = this.mIntent.getBooleanExtra(Constants.EXTRA_SHOW_SKIP_LOGIN, false);
        this.mPackageName = this.mIntent.getStringExtra(AccountManager.KEY_ANDROID_PACKAGE_NAME);
        if (PassportExternal.getPassportInterface().useUplinkRegister()) {
            upLinkRegister();
        } else {
            downLinkRegister();
        }
        this.mAnalyticsTracker = AnalyticsTracker.getInstance();
        this.mAnalyticsTracker.startSession(this);
    }

    private void upLinkRegister() {
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, new InputPasswordFragment());
    }

    private void downLinkRegister() {
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, new InputPhoneFragment());
    }

    public void onBackPressed() {
        if (!this.mDisableBackKey) {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                startActivity(SysHelper.buildPreviousActivityIntent(this, getIntent(), Constants.ACTION_WELCOME));
                return DEBUG;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected Dialog onCreateDialog(int id, Bundle args) {
        Builder builder;
        View view;
        Dialog dialog;
        switch (id) {
            case DIALOG_SEND_SMS_FAILURE /*1*/:
                builder = new Builder(this);
                builder.setMessage(getString(R.string.passport_error_no_sms_service));
                builder.setPositiveButton(17039360, null);
                builder.setIconAttribute(16843605);
                return builder.create();
            case DIALOG_CHECK_FIELDS_PROGRESS /*2*/:
                this.mCheckFieldsDialog = new ProgressDialog(this);
                this.mCheckFieldsDialog.setTitle(R.string.passport_checking_input);
                this.mCheckFieldsDialog.getWindow().setGravity(80);
                this.mCheckFieldsDialog.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        if (RegisterAccountActivity.this.mCheckFieldsTask != null) {
                            RegisterAccountActivity.this.mCheckFieldsTask.cancel(RegisterAccountActivity.DEBUG);
                            RegisterAccountActivity.this.mCheckFieldsTask = null;
                        }
                    }
                });
                return this.mCheckFieldsDialog;
            case DIALOG_REG_PROGRESS /*3*/:
                this.mRegDialogProgress = new ProgressDialog(this);
                this.mRegDialogProgress.setMessage(getString(R.string.passport_reging));
                this.mRegDialogProgress.setCancelable(false);
                this.mRegDialogProgress.getWindow().setGravity(80);
                return this.mRegDialogProgress;
            case DIALOG_REG_FAILED /*4*/:
                builder = new Builder(this);
                builder.setTitle((int) R.string.passport_reg_failed);
                builder.setMessage(args.getString("reason"));
                if (args.getInt(Constants.KEY_RESULT) == DIALOG_SEND_SMS_FAILURE || args.getInt(Constants.KEY_RESULT) == 9) {
                    view = LayoutInflater.from(this).inflate(R.layout.passport_reg_failed_dup_phone_dialog, null);
                    builder.setView(view);
                    Button regButton = (Button) view.findViewById(R.id.passport_login_instead_reg);
                    view.findViewById(R.id.passport_login_instead_reg).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(Constants.ACTION_LOGIN);
                            intent.setPackage(RegisterAccountActivity.this.getPackageName());
                            intent.putExtras(RegisterAccountActivity.this.getIntent());
                            RegisterAccountActivity.this.startActivity(intent);
                            RegisterAccountActivity.this.finish();
                        }
                    });
                    if (args.getInt(Constants.KEY_RESULT) == 9) {
                        regButton.setText(R.string.passport_login_instead_reg_with_email);
                    }
                    view.findViewById(R.id.passport_get_back_pwd).setOnClickListener(new OnClickListener() {
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
                            RegisterAccountActivity.this.removeDialog(RegisterAccountActivity.DIALOG_REG_FAILED);
                        }
                    });
                } else {
                    builder.setPositiveButton(17039360, null);
                }
                builder.setIconAttribute(16843605);
                dialog = builder.create();
                dialog.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        RegisterAccountActivity.this.removeDialog(RegisterAccountActivity.DIALOG_REG_FAILED);
                    }
                });
                return dialog;
            case EMAIL_REG_FAILED /*5*/:
                builder = new Builder(this);
                builder.setTitle((int) R.string.passport_reg_failed);
                builder.setMessage(args.getString("reason"));
                if (args.getInt(Constants.KEY_RESULT) == 9) {
                    view = LayoutInflater.from(this).inflate(R.layout.passport_reg_failed_dup_phone_dialog, null);
                    builder.setView(view);
                    view.findViewById(R.id.passport_login_instead_reg).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(Constants.ACTION_LOGIN);
                            intent.setPackage(RegisterAccountActivity.this.getPackageName());
                            intent.putExtras(RegisterAccountActivity.this.getIntent());
                            RegisterAccountActivity.this.startActivity(intent);
                            RegisterAccountActivity.this.finish();
                        }
                    });
                    view.findViewById(R.id.passport_get_back_pwd).setOnClickListener(new OnClickListener() {
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
                            RegisterAccountActivity.this.removeDialog(RegisterAccountActivity.DIALOG_REG_FAILED);
                        }
                    });
                } else {
                    builder.setPositiveButton(17039360, null);
                }
                builder.setIconAttribute(16843605);
                dialog = builder.create();
                dialog.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        RegisterAccountActivity.this.removeDialog(RegisterAccountActivity.DIALOG_REG_FAILED);
                    }
                });
                return dialog;
            default:
                return super.onCreateDialog(id, args);
        }
    }

    private void showFindPasswordOnWebDialog() {
        SimpleDialogFragment dialog = new AlertDialogFragmentBuilder(DIALOG_SEND_SMS_FAILURE).setTitle(getString(R.string.passport_forget_password)).setMessage(getString(R.string.passport_find_password_on_web_msg)).create();
        dialog.setNegativeButton(R.string.passport_re_register, null);
        dialog.setPositiveButton(R.string.passport_skip_register, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AccountHelper.setLoginResultAndFinish(RegisterAccountActivity.this, -1);
            }
        });
        dialog.show(getFragmentManager(), "FindPassword");
    }

    protected void onDestroy() {
        this.mAnalyticsTracker.endSession();
        if (this.mCheckFieldsTask != null) {
            this.mCheckFieldsTask.cancel(DEBUG);
        }
        super.onDestroy();
    }

    private void dismissDialogs() {
        if (this.mCheckFieldsDialog != null && this.mCheckFieldsDialog.isShowing()) {
            removeDialog(DIALOG_CHECK_FIELDS_PROGRESS);
        }
        this.mCheckFieldsDialog = null;
        if (this.mRegDialogProgress != null && this.mRegDialogProgress.isShowing()) {
            removeDialog(DIALOG_REG_PROGRESS);
        }
        this.mRegDialogProgress = null;
    }
}
