package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.account.R;
import com.xiaomi.account.data.AsyncTaskResult;
import com.xiaomi.account.data.SnsAccountInfo;
import com.xiaomi.account.data.SnsUserInfo;
import com.xiaomi.account.exception.SNSAccessTokenExpiredException;
import com.xiaomi.account.exception.SNSBindedInfoException;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.account.utils.SnsUtils;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.ui.SimpleDialogFragment;
import com.xiaomi.passport.ui.SimpleDialogFragment.AlertDialogFragmentBuilder;
import java.io.IOException;
import miui.accounts.ExtraAccountManager;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.app.AlertDialog.Builder;
import miui.graphics.BitmapFactory;
import miui.os.Build;

public class SnsAccountActivity extends Activity {
    private static final int ACTIVITY_UPDATE_CONTACTS_FROM_WEIBO = 10000;
    private static final int ERROR_ACCESS_REVOKED = 21319;
    private static final int ERROR_APPKEY_MISS = 10006;
    private static final int ERROR_EXPIRED_TOKEN = 21327;
    private static final int ERROR_INVALID_TOKEN = 21332;
    private static final int ERROR_TOKEN_EXPIRED = 21315;
    private static final int ERROR_TOKEN_INVALID = 21501;
    private static final String TAG = "SnsAccountActivity";
    private Account mAccount;
    private TextView mBindedDsptView;
    private LinearLayout mContainerView;
    private AsyncTask<Boolean, Void, DeleteResult> mDeleteAccessTokenTask;
    private boolean mIsUpdatingContacts;
    private AsyncTask<Void, Void, LoadResult> mLoadUserInfoTask;
    private ProgressBar mLoadingProgressBar;
    private SnsAccountInfo mSnsAccountInfo;
    private Button mUnBindBtn;
    private Button mUpdateContactsBtn;
    private ImageView mUserAvatarView;
    private TextView mUserNameView;

    private class DeleteResult extends AsyncTaskResult {
        public boolean mDeleted;

        public DeleteResult(int exceptionType, boolean deleted) {
            super(exceptionType);
            this.mDeleted = deleted;
        }
    }

    private class LoadResult extends AsyncTaskResult {
        public SnsUserInfo mUserInfo;

        public LoadResult(int exceptionType, SnsUserInfo userInfo) {
            super(exceptionType);
            this.mUserInfo = userInfo;
        }
    }

    public static void start(Context context, String snsType) {
        Intent intent = new Intent(context, SnsAccountActivity.class);
        intent.putExtra("extra_sns_type", snsType);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sns_account);
        this.mContainerView = (LinearLayout) findViewById(R.id.account_container);
        this.mLoadingProgressBar = (ProgressBar) findViewById(R.id.account_loading);
        this.mAccount = ExtraAccountManager.getXiaomiAccount(this);
        if (this.mAccount == null) {
            Log.i(TAG, "no xiaomi account");
            finish();
            return;
        }
        String snsType = getIntent().getStringExtra("extra_sns_type");
        if (TextUtils.isEmpty(snsType)) {
            Log.i(TAG, "snsType is null");
            finish();
            return;
        }
        String bindedDsptStr;
        this.mSnsAccountInfo = SnsAccountInfo.newSnsAccountInfo(snsType);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(this.mSnsAccountInfo.getTitleResId());
        }
        this.mUserNameView = (TextView) findViewById(R.id.user_name);
        this.mUserAvatarView = (ImageView) findViewById(R.id.user_avatar);
        loadUserInfo();
        this.mUpdateContactsBtn = (Button) findViewById(R.id.update_contacts_btn);
        this.mUpdateContactsBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SnsAccountActivity.this.updateContacts();
            }
        });
        if (this.mSnsAccountInfo.getType().equals(SnsAccountInfo.SINA_SNS_TYPE)) {
            this.mUpdateContactsBtn.setVisibility(0);
            bindedDsptStr = getString(R.string.update_contacts_description);
        } else {
            bindedDsptStr = String.format(getString(R.string.binded_description), new Object[]{getString(this.mSnsAccountInfo.getTypeName()), getString(this.mSnsAccountInfo.getTypeName())});
        }
        this.mBindedDsptView = (TextView) findViewById(R.id.binded_description);
        this.mBindedDsptView.setText(bindedDsptStr);
        this.mUnBindBtn = (Button) findViewById(R.id.unbind_btn);
        this.mUnBindBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String message;
                if (SnsAccountActivity.this.mSnsAccountInfo.getType().equals(SnsAccountInfo.SINA_SNS_TYPE)) {
                    message = SnsAccountActivity.this.getString(R.string.unbind_weibo_description);
                } else {
                    message = String.format(SnsAccountActivity.this.getString(R.string.unbind_sns_description), new Object[]{SnsAccountActivity.this.getString(SnsAccountActivity.this.mSnsAccountInfo.getTypeName())});
                }
                SimpleDialogFragment dialog = new AlertDialogFragmentBuilder(1).setTitle(SnsAccountActivity.this.getString(R.string.confirm_unbind)).setMessage(message).create();
                dialog.setNegativeButton(R.string.button_cancel, null);
                dialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SnsAccountActivity.this.deleteAccessToken(false);
                    }
                });
                dialog.show(SnsAccountActivity.this.getFragmentManager(), "UnbindSns");
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.mIsUpdatingContacts) {
            updateContacts();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_UPDATE_CONTACTS_FROM_WEIBO && data != null) {
            this.mIsUpdatingContacts = false;
            int errCode = data.getIntExtra("miui.intent.extra.EXTRA_SINA_WEIBO_CODE", 0);
            if (errCode == 0) {
                int count = data.getIntExtra("miui.intent.extra.EXTRA_IMPORTED_WEIBO_COUNT", -1);
                String result = null;
                if (count == 0) {
                    result = getString(R.string.updated_nothing);
                } else if (count != -1) {
                    result = getResources().getQuantityString(R.plurals.updated_contacts, count, new Object[]{Integer.valueOf(count)});
                }
                if (!TextUtils.isEmpty(result)) {
                    this.mUpdateContactsBtn.setText(result);
                    return;
                }
                return;
            }
            Toast.makeText(this, getString(getReasonByErrCode(errCode)), 0).show();
            if (accessTokenExpired(errCode) || accessTokenInvalid(errCode)) {
                deleteAccessToken(true);
            }
        }
    }

    private int getReasonByErrCode(int errCode) {
        if (errCode == ERROR_TOKEN_EXPIRED || errCode == ERROR_EXPIRED_TOKEN) {
            return R.string.access_token_expired_warning;
        }
        if (errCode == ERROR_INVALID_TOKEN || errCode == ERROR_ACCESS_REVOKED || errCode == ERROR_TOKEN_INVALID || errCode == ERROR_APPKEY_MISS) {
            return R.string.access_token_invalid_warning;
        }
        return R.string.service_error;
    }

    private boolean accessTokenExpired(int errCode) {
        return errCode == ERROR_TOKEN_EXPIRED || errCode == ERROR_EXPIRED_TOKEN;
    }

    private boolean accessTokenInvalid(int errCode) {
        return errCode == ERROR_INVALID_TOKEN || errCode == ERROR_ACCESS_REVOKED || errCode == ERROR_TOKEN_INVALID || errCode == ERROR_APPKEY_MISS;
    }

    private void updateContacts() {
        this.mIsUpdatingContacts = true;
        AccountManager am = AccountManager.get(this);
        String accessToken = am.getUserData(this.mAccount, this.mSnsAccountInfo.getAccessTokenKey());
        String userId = am.getUserData(this.mAccount, this.mSnsAccountInfo.getUserIdKey());
        Intent intent = new Intent("miui.intent.action.ACTION_IMPORT_SINA_WEIBO");
        intent.putExtra("miui.intent.action.EXTRA_SINA_WEIBO_ACCESS_TOKEN", accessToken);
        intent.putExtra("miui.intent.extra.EXTRA_SINA_WEIBO_BINDED_ID", userId);
        startActivityForResult(intent, ACTIVITY_UPDATE_CONTACTS_FROM_WEIBO);
    }

    protected void onDestroy() {
        if (this.mDeleteAccessTokenTask != null) {
            this.mDeleteAccessTokenTask.cancel(true);
            this.mDeleteAccessTokenTask = null;
        }
        if (this.mLoadUserInfoTask != null) {
            this.mLoadUserInfoTask.cancel(true);
            this.mLoadUserInfoTask = null;
        }
        super.onDestroy();
    }

    private void showSnsTokenExpiredDialog() {
        new Builder(this).setTitle(R.string.sns_access_token_expired_warning).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SnsWebViewActivity.start(SnsAccountActivity.this, SnsAccountActivity.this.mSnsAccountInfo.getType(), true);
                SnsAccountActivity.this.finish();
            }
        }).setNegativeButton(17039360, null).show();
    }

    private void loadUserInfo() {
        AccountManager am = AccountManager.get(this);
        String userName = am.getUserData(this.mAccount, this.mSnsAccountInfo.getUserNameKey());
        String userAvatarAbsPath = am.getUserData(this.mAccount, this.mSnsAccountInfo.getUserAvatarAbsPathKey());
        if (!(TextUtils.isEmpty(userName) || TextUtils.isEmpty(userAvatarAbsPath))) {
            Bitmap bm = SnsUtils.getUserAvatarByAbsPath(this, userAvatarAbsPath);
            if (bm != null) {
                Bitmap avatar = BitmapFactory.createPhoto(this, bm);
                if (avatar != null) {
                    this.mUserNameView.setText(userName);
                    this.mUserAvatarView.setImageBitmap(avatar);
                    showContainerView();
                    return;
                }
            }
        }
        this.mLoadUserInfoTask = new AsyncTask<Void, Void, LoadResult>() {
            protected void onPreExecute() {
                SnsAccountActivity.this.showLoadingProgressBar();
            }

            protected LoadResult doInBackground(Void... params) {
                SnsUserInfo userInfo = null;
                int exceptionType = 0;
                try {
                    userInfo = CloudHelper.getBindedUserInfo(SnsAccountActivity.this.mSnsAccountInfo.getSnsType(), AccountManager.get(SnsAccountActivity.this).getUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getAccessTokenKey()));
                    if (userInfo == null) {
                        Log.i(SnsAccountActivity.TAG, "mLoadUserInfoTask userInfo is null");
                        return new LoadResult(5, null);
                    }
                    userInfo.setAvatarAbsPath(SnsUtils.saveUserAvatarByUrl(SnsAccountActivity.this, userInfo.getAvatarUrl(), SnsAccountActivity.this.mSnsAccountInfo.getAccountPath()));
                    return new LoadResult(exceptionType, userInfo);
                } catch (IOException e) {
                    Log.e(SnsAccountActivity.TAG, "IOException", e);
                    exceptionType = 2;
                } catch (AuthenticationFailureException e2) {
                    Log.e(SnsAccountActivity.TAG, "AuthenticationFailureException", e2);
                    exceptionType = 3;
                } catch (AccessDeniedException e3) {
                    Log.e(SnsAccountActivity.TAG, "AccessDeniedException", e3);
                    exceptionType = 4;
                } catch (SNSAccessTokenExpiredException e4) {
                    Log.e(SnsAccountActivity.TAG, "AccessTokenExpiredException", e4);
                    exceptionType = 6;
                } catch (SNSBindedInfoException e5) {
                    Log.e(SnsAccountActivity.TAG, "GetSNSInfoException", e5);
                    exceptionType = 5;
                }
            }

            protected void onPostExecute(LoadResult result) {
                SnsAccountActivity.this.showContainerView();
                SnsAccountActivity.this.mLoadUserInfoTask = null;
                if (result.getExceptionType() == 6) {
                    SnsAccountActivity.this.showSnsTokenExpiredDialog();
                } else if (result.hasException()) {
                    Toast.makeText(SnsAccountActivity.this, result.getExceptionReason(), 0).show();
                } else if (result.mUserInfo != null) {
                    Bitmap bm;
                    AccountManager am = AccountManager.get(SnsAccountActivity.this);
                    String name = result.mUserInfo.getName();
                    String avatarAbsPath = result.mUserInfo.getAvatarAbsPath();
                    am.setUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getUserIdKey(), result.mUserInfo.getUserId());
                    am.setUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getUserNameKey(), name);
                    am.setUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getUserAvatarAbsPathKey(), avatarAbsPath);
                    SnsAccountActivity.this.mUserNameView.setText(name);
                    if (TextUtils.isEmpty(avatarAbsPath)) {
                        bm = android.graphics.BitmapFactory.decodeResource(SnsAccountActivity.this.getResources(), R.drawable.default_avatar_in_details);
                    } else {
                        bm = SnsUtils.getUserAvatarByAbsPath(SnsAccountActivity.this, avatarAbsPath);
                    }
                    if (bm != null) {
                        SnsAccountActivity.this.mUserAvatarView.setImageBitmap(BitmapFactory.createPhoto(SnsAccountActivity.this, bm));
                    }
                }
            }
        };
        this.mLoadUserInfoTask.execute(new Void[0]);
    }

    private void deleteAccessToken(boolean resSetUp) {
        this.mDeleteAccessTokenTask = new AsyncTask<Boolean, Void, DeleteResult>() {
            private boolean mReSetup;

            protected void onPreExecute() {
                SnsAccountActivity.this.showLoadingProgressBar();
            }

            protected DeleteResult doInBackground(Boolean... params) {
                boolean deleted = false;
                int exceptionType = 0;
                this.mReSetup = params[0].booleanValue();
                AccountManager am = AccountManager.get(SnsAccountActivity.this);
                if (am.getUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getAccessTokenKey()) == null) {
                    return new DeleteResult(0, true);
                }
                try {
                    ExtendedAuthToken serviceToken = ExtendedAuthToken.parse(SysHelper.getAuthToken(am, SnsAccountActivity.this.mAccount, Constants.PASSPORT_API_SID));
                    deleted = CloudHelper.deleteBindedAccessToken(SnsAccountActivity.this.mAccount.name, am.getUserData(SnsAccountActivity.this.mAccount, Constants.KEY_ENCRYPTED_USER_ID), SnsAccountActivity.this.mSnsAccountInfo.getSnsType(), serviceToken.authToken, serviceToken.security);
                } catch (IOException e) {
                    Log.e(SnsAccountActivity.TAG, "InvalidAccessTokenRunnable error", e);
                    exceptionType = 2;
                } catch (AccessDeniedException e2) {
                    Log.e(SnsAccountActivity.TAG, "InvalidAccessTokenRunnable error", e2);
                    exceptionType = 4;
                } catch (AuthenticationFailureException e3) {
                    Log.e(SnsAccountActivity.TAG, "InvalidAccessTokenRunnable error", e3);
                    exceptionType = 1;
                } catch (InvalidResponseException e4) {
                    Log.e(SnsAccountActivity.TAG, "InvalidAccessTokenRunnable error", e4);
                    exceptionType = 3;
                } catch (CipherException e5) {
                    Log.e(SnsAccountActivity.TAG, "InvalidAccessTokenRunnable error", e5);
                    exceptionType = 3;
                }
                if (exceptionType == 0 && deleted) {
                    am.setUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getAccessTokenKey(), null);
                    am.setUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getUserIdKey(), null);
                    am.setUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getUserNameKey(), null);
                    am.setUserData(SnsAccountActivity.this.mAccount, SnsAccountActivity.this.mSnsAccountInfo.getUserAvatarAbsPathKey(), null);
                    SnsAccountActivity.this.sendBroadcast(new Intent("miui.intent.action.ACTION_IMPORT_SINA_WEIBO_CANCELED"));
                }
                return new DeleteResult(exceptionType, deleted);
            }

            protected void onPostExecute(DeleteResult result) {
                SnsAccountActivity.this.showContainerView();
                SnsAccountActivity.this.mDeleteAccessTokenTask = null;
                if (result.hasException()) {
                    Toast.makeText(SnsAccountActivity.this, result.getExceptionReason(), 0).show();
                } else if (result.mDeleted) {
                    if (this.mReSetup) {
                        SnsWebViewActivity.start(SnsAccountActivity.this, SnsAccountActivity.this.mSnsAccountInfo.getType(), true);
                    }
                    SnsAccountActivity.this.finish();
                }
            }
        };
        this.mDeleteAccessTokenTask.execute(new Boolean[]{Boolean.valueOf(resSetUp)});
    }

    private void showLoadingProgressBar() {
        if (this.mContainerView.getVisibility() == 0) {
            this.mContainerView.setVisibility(8);
        }
        if (this.mLoadingProgressBar.getVisibility() != 0) {
            this.mLoadingProgressBar.setVisibility(0);
        }
    }

    private void showContainerView() {
        if (this.mLoadingProgressBar.getVisibility() == 0) {
            this.mLoadingProgressBar.setVisibility(8);
        }
        if (this.mContainerView.getVisibility() != 0) {
            this.mContainerView.setVisibility(0);
        }
    }
}
