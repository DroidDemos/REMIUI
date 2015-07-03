package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.provider.ContactsContract.DisplayPhoto;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import miui.accounts.ExtraAccountManager;
import miui.app.AlertDialog.Builder;
import miui.os.Build;
import org.json.JSONObject;

public class UserAvatarUpdateFragment extends PreferenceFragment {
    public static final String FILE_PROVIDER_AUTHORITY = "com.xiaomi.account.files";
    private static final String MIUI_GALLERY = "com.miui.gallery";
    private static final String PHOTO_DATE_FORMAT = "'photo'_yyyyMMdd_HHmmss";
    private static final String PREF_BY_TAKE_A_PHOTO = "pref_by_taking_a_photo";
    private static final String PREF_USING_ALBUM_PHOTO = "pref_using_album_photo";
    private static final String PREF_USING_FB_AVATAR = "pref_using_facebook_avatar";
    private static final String PREF_USING_QQ_AVATAR = "pref_using_qq_avatar";
    private static final String PREF_USING_SNS_AVATAR = "pref_using_sns_avatar";
    private static final String PREF_USING_WEIBO_AVATAR = "pref_using_weibo_avatar";
    private static final int REQUEST_CROP_PHOTO = 1004;
    private static final int REQUEST_GET_SNS_AVATAR = 1001;
    private static final int REQUEST_PICK_PHOTO_FROM_GALLERY = 1003;
    private static final int REQUEST_SNS_RELOGIN_FOR_EXPIRED_TOKEN = 1005;
    private static final int REQUEST_TAKE_PHOTO = 1002;
    private static final String TAG = "UserAvatarUpdateFragment";
    private Account mAccount;
    private ImageView mAvatarView;
    private String mCroppedPhotoPath;
    private AsyncTask<Void, Void, Integer> mSnsLoadTask;
    private String mSnsType;
    private String mTempPhotoPath;
    private AsyncTask<Void, Void, Integer> mUploadAvatarTask;

    private class LoadSnsInfo extends AsyncTask<Void, Void, Integer> {
        private SnsAccountInfo mSnsAccountInfo;

        public LoadSnsInfo(SnsAccountInfo snsAccountInfo) {
            this.mSnsAccountInfo = snsAccountInfo;
        }

        protected Integer doInBackground(Void... params) {
            int exceptionType = 0;
            AccountManager am = AccountManager.get(UserAvatarUpdateFragment.this.getActivity());
            if (!TextUtils.isEmpty(am.getUserData(UserAvatarUpdateFragment.this.mAccount, this.mSnsAccountInfo.getUserAvatarAbsPathKey()))) {
                return Integer.valueOf(0);
            }
            String accessToken = am.getUserData(UserAvatarUpdateFragment.this.mAccount, this.mSnsAccountInfo.getAccessTokenKey());
            if (TextUtils.isEmpty(accessToken)) {
                Log.i(UserAvatarUpdateFragment.TAG, "LoadSnsInfo accessToken empty");
                return null;
            }
            try {
                SnsUserInfo userInfo = CloudHelper.getBindedUserInfo(this.mSnsAccountInfo.getSnsType(), accessToken);
                if (userInfo == null) {
                    Log.i(UserAvatarUpdateFragment.TAG, "LoadSnsInfo userInfo is null");
                    return Integer.valueOf(5);
                }
                am.setUserData(UserAvatarUpdateFragment.this.mAccount, Constants.ACCOUNT_AVATAR_URL, userInfo.getAvatarUrl());
                am.setUserData(UserAvatarUpdateFragment.this.mAccount, this.mSnsAccountInfo.getUserAvatarAbsPathKey(), SnsUtils.saveUserAvatarByUrl(UserAvatarUpdateFragment.this.getActivity(), userInfo.getAvatarUrl(), this.mSnsAccountInfo.getAccountPath()));
                return Integer.valueOf(exceptionType);
            } catch (IOException e) {
                exceptionType = 2;
                Log.e(UserAvatarUpdateFragment.TAG, "IOException", e);
            } catch (AuthenticationFailureException e2) {
                exceptionType = 1;
                Log.e(UserAvatarUpdateFragment.TAG, "AuthenticationFailureException", e2);
            } catch (AccessDeniedException e3) {
                exceptionType = 4;
                Log.e(UserAvatarUpdateFragment.TAG, "AccessDeniedException", e3);
            } catch (SNSAccessTokenExpiredException e4) {
                exceptionType = 6;
                Log.e(UserAvatarUpdateFragment.TAG, "AccessTokenExpiredException", e4);
            } catch (SNSBindedInfoException e5) {
                exceptionType = 5;
                Log.e(UserAvatarUpdateFragment.TAG, "GetSNSInfoException", e5);
            }
        }

        protected void onPostExecute(Integer result) {
            if (result == null) {
                Log.i(UserAvatarUpdateFragment.TAG, "LoadSnsInfo result is null");
                return;
            }
            AsyncTaskResult taskResult = new AsyncTaskResult(result.intValue());
            if (result.intValue() == 6) {
                UserAvatarUpdateFragment.this.showSnsTokenExpiredDialog();
            } else if (taskResult.hasException()) {
                Toast.makeText(UserAvatarUpdateFragment.this.getActivity(), taskResult.getExceptionReason(), 0).show();
            } else {
                String avatarAbsPath = AccountManager.get(UserAvatarUpdateFragment.this.getActivity()).getUserData(UserAvatarUpdateFragment.this.mAccount, this.mSnsAccountInfo.getUserAvatarAbsPathKey());
                if (TextUtils.isEmpty(avatarAbsPath)) {
                    Log.i(UserAvatarUpdateFragment.TAG, "LoadSnsInfo avatarAbsPath empty");
                    return;
                }
                Bitmap bm = SnsUtils.getUserAvatarByAbsPath(UserAvatarUpdateFragment.this.getActivity(), avatarAbsPath);
                if (bm == null) {
                    bm = BitmapFactory.decodeResource(UserAvatarUpdateFragment.this.getActivity().getResources(), R.drawable.default_avatar_in_settings);
                }
                UserAvatarUpdateFragment.this.mTempPhotoPath = UserAvatarUpdateFragment.pathForTempPhoto(UserAvatarUpdateFragment.this.getActivity(), "sns");
                try {
                    if (miui.graphics.BitmapFactory.saveToFile(bm, UserAvatarUpdateFragment.this.mTempPhotoPath)) {
                        UserAvatarUpdateFragment.this.doCropPhoto(Uri.fromFile(new File(UserAvatarUpdateFragment.this.mTempPhotoPath)));
                    }
                } catch (IOException e) {
                    Log.i(UserAvatarUpdateFragment.TAG, "LoadSnsInfo failed Save the bitmap to file", e);
                }
            }
        }
    }

    private class UploadUserAvatarTask extends AsyncTask<Void, Void, Integer> {
        private Bitmap mBitmap;
        private SimpleDialogFragment mProgressDialog;

        public UploadUserAvatarTask(Bitmap bitmap) {
            this.mBitmap = bitmap;
            this.mProgressDialog = new AlertDialogFragmentBuilder(2).setMessage(UserAvatarUpdateFragment.this.getString(R.string.user_avatar_uploading)).create();
            this.mProgressDialog.setOnDismissListener(new OnDismissListener(UserAvatarUpdateFragment.this) {
                public void onDismiss(DialogInterface dialog) {
                    UploadUserAvatarTask.this.cancel(true);
                }
            });
            this.mProgressDialog.show(UserAvatarUpdateFragment.this.getFragmentManager(), "uploadAvatarProgress");
        }

        protected Integer doInBackground(Void... params) {
            Activity activity = UserAvatarUpdateFragment.this.getActivity();
            UserAvatarUpdateFragment.this.mAccount = ExtraAccountManager.getXiaomiAccount(activity);
            if (UserAvatarUpdateFragment.this.mAccount == null) {
                Log.d(UserAvatarUpdateFragment.TAG, "no Xiaomi account");
                return null;
            }
            AccountManager am = AccountManager.get(activity);
            String userId = UserAvatarUpdateFragment.this.mAccount.name;
            int exceptionType = 0;
            int count = 0;
            while (count < 2) {
                String token = SysHelper.getAuthToken(am, UserAvatarUpdateFragment.this.mAccount, Constants.PASSPORT_API_SID);
                try {
                    ExtendedAuthToken extToken = ExtendedAuthToken.parse(token);
                    if (extToken != null) {
                        String serviceToken = extToken.authToken;
                        String security = extToken.security;
                        String encryptedUserId = am.getUserData(UserAvatarUpdateFragment.this.mAccount, Constants.KEY_ENCRYPTED_USER_ID);
                        String uploadUrl = CloudHelper.requestUploadUserIcon(userId, encryptedUserId, serviceToken, security);
                        if (uploadUrl == null) {
                            Log.d(UserAvatarUpdateFragment.TAG, "uploadUrl is null");
                            return null;
                        }
                        JSONObject uploadResult = SysHelper.uploadImageToServer(activity, userId, uploadUrl, this.mBitmap);
                        if (uploadResult == null) {
                            Log.d(UserAvatarUpdateFragment.TAG, "uploadResult is null");
                            return null;
                        }
                        String imageUrl = CloudHelper.commitUploadUserIcon(userId, encryptedUserId, serviceToken, security, uploadResult);
                        am.setUserData(UserAvatarUpdateFragment.this.mAccount, Constants.ACCOUNT_AVATAR_URL, imageUrl);
                    }
                } catch (IOException e) {
                    Log.e(UserAvatarUpdateFragment.TAG, "uploadInfoToServer error", e);
                    exceptionType = 2;
                } catch (AuthenticationFailureException e2) {
                    Log.e(UserAvatarUpdateFragment.TAG, "uploadInfoToServer error", e2);
                    am.invalidateAuthToken(UserAvatarUpdateFragment.this.mAccount.type, token);
                    exceptionType = 1;
                    count++;
                } catch (AccessDeniedException e3) {
                    Log.e(UserAvatarUpdateFragment.TAG, "uploadInfoToServer error", e3);
                    exceptionType = 4;
                } catch (InvalidResponseException e4) {
                    Log.e(UserAvatarUpdateFragment.TAG, "uploadInfoToServer error", e4);
                    exceptionType = 3;
                } catch (CipherException e5) {
                    Log.e(UserAvatarUpdateFragment.TAG, "uploadInfoToServer error", e5);
                    exceptionType = 3;
                }
                return Integer.valueOf(exceptionType);
            }
            return Integer.valueOf(exceptionType);
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            this.mProgressDialog.dismissAllowingStateLoss();
            AsyncTaskResult taskResult = new AsyncTaskResult(result.intValue());
            if (taskResult.hasException()) {
                Toast.makeText(UserAvatarUpdateFragment.this.getActivity(), taskResult.getExceptionReason(), 0).show();
                return;
            }
            Bitmap avatar = miui.graphics.BitmapFactory.createPhoto(UserAvatarUpdateFragment.this.getActivity(), this.mBitmap, UserAvatarUpdateFragment.this.getActivity().getResources().getDimensionPixelSize(R.dimen.account_setting_avatar_size));
            if (avatar != null) {
                UserAvatarUpdateFragment.this.mAvatarView.setImageBitmap(avatar);
                this.mBitmap.recycle();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SysHelper.setMiuiActionBarTitle(getActivity(), getString(R.string.account_user_details));
        View view = inflater.inflate(R.layout.user_avatar_update_fragment, container, false);
        this.mAvatarView = (ImageView) view.findViewById(R.id.avatar_image);
        addPreferencesFromResource(R.xml.user_avatar_update_preference);
        Preference prefWeiboAvatar = findPreference(PREF_USING_WEIBO_AVATAR);
        Preference prefQQAvatar = findPreference(PREF_USING_QQ_AVATAR);
        Preference prefFBAvatar = findPreference(PREF_USING_FB_AVATAR);
        PreferenceCategory prefSnsAvatar = (PreferenceCategory) findPreference(PREF_USING_SNS_AVATAR);
        if (Build.IS_INTERNATIONAL_BUILD) {
            prefSnsAvatar.removePreference(prefWeiboAvatar);
            prefSnsAvatar.removePreference(prefQQAvatar);
        } else {
            prefSnsAvatar.removePreference(prefFBAvatar);
        }
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mAccount = ExtraAccountManager.getXiaomiAccount(getActivity());
        if (this.mAccount == null) {
            Log.w(TAG, "no xiaomi account");
            getActivity().finish();
            return;
        }
        Bitmap avatar = SysHelper.createPhoto(getActivity(), AccountManager.get(getActivity()).getUserData(this.mAccount, Constants.ACCOUNT_AVATAR_FILE_NAME));
        if (avatar != null) {
            this.mAvatarView.setImageBitmap(avatar);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GET_SNS_AVATAR /*1001*/:
            case REQUEST_SNS_RELOGIN_FOR_EXPIRED_TOKEN /*1005*/:
                if (TextUtils.isEmpty(this.mSnsType)) {
                    Log.i(TAG, "onActivityResult mSnsType is empty");
                    return;
                } else {
                    startLoadSnsInfo(SnsAccountInfo.newSnsAccountInfo(this.mSnsType));
                    return;
                }
            case REQUEST_TAKE_PHOTO /*1002*/:
            case REQUEST_PICK_PHOTO_FROM_GALLERY /*1003*/:
                if (resultCode == -1) {
                    Uri uri;
                    if (data == null || data.getData() == null) {
                        uri = Uri.fromFile(new File(this.mTempPhotoPath));
                    } else {
                        uri = data.getData();
                    }
                    doCropPhoto(uri);
                    return;
                }
                return;
            case REQUEST_CROP_PHOTO /*1004*/:
                File tempPhototFile = new File(this.mTempPhotoPath);
                if (tempPhototFile.exists() && tempPhototFile.isFile()) {
                    tempPhototFile.delete();
                }
                if (resultCode == -1) {
                    startUploadAvatar(BitmapFactory.decodeFile(this.mCroppedPhotoPath));
                    File tempCroppedPhototFile = new File(this.mCroppedPhotoPath);
                    if (tempCroppedPhototFile.exists() && tempCroppedPhototFile.isFile()) {
                        tempCroppedPhototFile.delete();
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        if (this.mUploadAvatarTask != null) {
            this.mUploadAvatarTask.cancel(true);
            this.mUploadAvatarTask = null;
        }
        if (this.mSnsLoadTask != null) {
            this.mSnsLoadTask.cancel(true);
            this.mSnsLoadTask = null;
        }
        super.onDestroy();
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (PREF_USING_WEIBO_AVATAR.equals(preference.getKey())) {
            startUsingSnsAvatar(SnsAccountInfo.SINA_SNS_TYPE);
        } else if (PREF_USING_QQ_AVATAR.equals(preference.getKey())) {
            startUsingSnsAvatar(SnsAccountInfo.QQ_SNS_TYPE);
        } else if (PREF_USING_FB_AVATAR.equals(preference.getKey())) {
            startUsingSnsAvatar(SnsAccountInfo.FB_SNS_TYPE);
        } else if (PREF_BY_TAKE_A_PHOTO.equals(preference.getKey())) {
            startTakePhoto();
        } else if (PREF_USING_ALBUM_PHOTO.equals(preference.getKey())) {
            startPickPhotoFromGallery();
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void startUsingSnsAvatar(String snsType) {
        this.mSnsType = snsType;
        SnsAccountInfo snsAccountInfo = SnsAccountInfo.newSnsAccountInfo(this.mSnsType);
        Activity activity = getActivity();
        if (TextUtils.isEmpty(AccountManager.get(activity).getUserData(this.mAccount, snsAccountInfo.getAccessTokenKey()))) {
            Intent intent = new Intent(activity, SnsAddAccountActivity.class);
            intent.putExtra("extra_sns_type", snsType);
            intent.putExtra(SnsWebViewActivity.EXTRA_SHOW_SNS_ACCOUNT_AFTER_BINDING_SUCCESS, false);
            startActivityForResult(intent, REQUEST_GET_SNS_AVATAR);
            return;
        }
        startLoadSnsInfo(snsAccountInfo);
    }

    private void startLoadSnsInfo(SnsAccountInfo snsAccountInfo) {
        if (snsAccountInfo == null) {
            Log.i(TAG, "startLoadSnsInfo snsAccountInfo is null");
            return;
        }
        if (this.mSnsLoadTask != null) {
            this.mSnsLoadTask.cancel(true);
            this.mSnsLoadTask = null;
        }
        this.mSnsLoadTask = new LoadSnsInfo(snsAccountInfo);
        this.mSnsLoadTask.execute(new Void[0]);
    }

    private void startTakePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        this.mTempPhotoPath = pathForTempPhoto(getActivity(), "");
        intent.putExtra("output", Uri.fromFile(new File(this.mTempPhotoPath)));
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    private void startPickPhotoFromGallery() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setPackage(MIUI_GALLERY);
        intent.setType("image/*");
        this.mTempPhotoPath = pathForTempPhoto(getActivity(), "");
        intent.putExtra("output", Uri.fromFile(new File(this.mTempPhotoPath)));
        startActivityForResult(intent, REQUEST_PICK_PHOTO_FROM_GALLERY);
    }

    private void doCropPhoto(Uri inputUri) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setPackage(MIUI_GALLERY);
            intent.setDataAndType(inputUri, "image/*");
            this.mCroppedPhotoPath = pathForTempPhoto(getActivity(), "-cropped");
            intent.putExtra("output", Uri.fromFile(new File(this.mCroppedPhotoPath)));
            intent.putExtra("tips", getString(R.string.account_crop_user_avatar));
            addCropExtras(intent, getPhotoPickSize());
            startActivityForResult(intent, REQUEST_CROP_PHOTO);
        } catch (Exception e) {
            Log.e(TAG, "Cannot crop image", e);
            Toast.makeText(getActivity(), R.string.photoPickerNotFoundText, 1).show();
        }
    }

    private static void addCropExtras(Intent intent, int photoSize) {
        intent.putExtra("crop", Constants.TRUE);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", photoSize);
        intent.putExtra("outputY", photoSize);
    }

    private int getPhotoPickSize() {
        int dimensionPixelSize;
        Cursor c = getActivity().getContentResolver().query(DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI, new String[]{"display_max_dim"}, null, null, null);
        if (c == null) {
            try {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.upload_user_avatar_size);
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        } else {
            c.moveToFirst();
            dimensionPixelSize = c.getInt(0);
            if (c != null) {
                c.close();
            }
        }
        return dimensionPixelSize;
    }

    private static String pathForTempPhoto(Context context, String name) {
        Date date = new Date(System.currentTimeMillis());
        return new File(context.getExternalFilesDir(null), "account_" + new SimpleDateFormat(PHOTO_DATE_FORMAT).format(date) + name + ".jpg").getAbsolutePath();
    }

    private void startUploadAvatar(Bitmap bitmap) {
        if (bitmap == null) {
            Log.i(TAG, "startUploadAvatar bitmap is null");
            return;
        }
        if (this.mUploadAvatarTask != null) {
            this.mUploadAvatarTask.cancel(true);
            this.mUploadAvatarTask = null;
        }
        this.mUploadAvatarTask = new UploadUserAvatarTask(bitmap);
        this.mUploadAvatarTask.execute(new Void[0]);
    }

    private void showSnsTokenExpiredDialog() {
        new Builder(getActivity()).setTitle(R.string.sns_access_token_expired_warning).setPositiveButton(R.string.confirm, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(UserAvatarUpdateFragment.this.getActivity(), SnsWebViewActivity.class);
                intent.putExtra("extra_sns_type", UserAvatarUpdateFragment.this.mSnsType);
                intent.putExtra(SnsWebViewActivity.EXTRA_SHOW_SNS_ACCOUNT_AFTER_BINDING_SUCCESS, false);
                UserAvatarUpdateFragment.this.startActivityForResult(intent, UserAvatarUpdateFragment.REQUEST_SNS_RELOGIN_FOR_EXPIRED_TOKEN);
            }
        }).setNegativeButton(17039360, null).show();
    }
}
