package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.ActionBar.LayoutParams;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.miui.internal.widget.ActionBarMovableLayout;
import com.miui.internal.widget.ActionBarMovableLayout.OnScrollListener;
import com.xiaomi.account.R;
import com.xiaomi.account.ui.AccountSettingsFragment.RefreshUserInfoInterface;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.ui.AccountUnactivatedFragment;
import com.xiaomi.passport.ui.SimpleDialogFragment;
import java.io.File;
import java.io.IOException;
import miui.accounts.ExtraAccountManager;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;

public class AccountSettingsActivity extends Activity implements OnClickListener, OnScrollListener, RefreshUserInfoInterface {
    public static final String ACTION_VIEW_SCAN_BARCODE = "android.intent.action.scanbarcode";
    private static final int REQUEST_CODE_SCAN_BARCODE = 1001;
    public static final String START_PACKAGENAME_MIUI_HOME = "miui_home";
    private static final String TAG = "AccountSettingsActivity";
    private static final String TAG_FRM_ACTIVATED = "activated";
    private static int sBlurBackgroundHeight;
    private static int sBlurBackgroundWidth;
    private Account mAccount;
    private ActionBar mActionBar;
    private ImageView mAvatarView;
    ListView mListView;
    private OnPreDrawListener mOnPreDrawListener;
    private LinearLayout mTitleAvatarView;
    private LinearLayout mTitleBarView;
    private LinearLayout mTitleViewUserIdInfo;
    private float mTitleYBase;
    private float mTitleYRange;
    private TextView mUserIdView;
    private TextView mUserInfoView;

    public void onCreate(Bundle savedInstanceState) {
        if (Build.IS_TABLET) {
            setTheme(R.style.Theme.Settings);
        }
        super.onCreate(savedInstanceState);
        if (!Build.IS_TABLET) {
            setContentView(R.layout.action_bar_movable_layout);
            initTitle();
        }
    }

    private void initTitle() {
        this.mTitleYRange = (float) getResources().getDimensionPixelSize(R.dimen.user_id_range_h);
        View mCustomView = getLayoutInflater().inflate(R.layout.account_profile_action_bar, null);
        LayoutParams para = new LayoutParams(-1, -1);
        para.gravity = 48;
        this.mActionBar = getActionBar();
        this.mActionBar.setCustomView(mCustomView, para);
        this.mActionBar.setDisplayOptions(16);
        this.mActionBar.setFragmentViewPagerMode(this, getFragmentManager());
        this.mActionBar.setDisplayShowCustomEnabled(true);
        ((ActionBarMovableLayout) findViewById(com.miui.internal.R.id.action_bar_overlay_layout)).setOnScrollListener(this);
        View scaleDisplayView = this.mActionBar.getCustomView();
        ((Button) scaleDisplayView.findViewById(R.id.btn_scan_barcode)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fromApp", AccountSettingsActivity.this.getPackageName());
                bundle.putString(SimpleDialogFragment.ARG_TITLE, AccountSettingsActivity.this.getString(R.string.scan_barcode_title));
                bundle.putBoolean("isBackToThirdApp", true);
                Intent intent = new Intent(AccountSettingsActivity.ACTION_VIEW_SCAN_BARCODE);
                intent.putExtras(bundle);
                AccountSettingsActivity.this.startActivityForResult(intent, AccountSettingsActivity.REQUEST_CODE_SCAN_BARCODE);
            }
        });
        this.mTitleBarView = (LinearLayout) scaleDisplayView.findViewById(R.id.title_bar);
        this.mAvatarView = (ImageView) scaleDisplayView.findViewById(R.id.avatar);
        this.mUserIdView = (TextView) scaleDisplayView.findViewById(R.id.user_id);
        this.mUserInfoView = (TextView) scaleDisplayView.findViewById(R.id.user_info);
        this.mTitleAvatarView = (LinearLayout) scaleDisplayView.findViewById(R.id.avatar_view);
        this.mTitleViewUserIdInfo = (LinearLayout) scaleDisplayView.findViewById(R.id.user_id_info);
        if (this.mTitleBarView != null) {
            this.mTitleBarView.setOnClickListener(this);
        }
        this.mOnPreDrawListener = new OnPreDrawListener() {
            public boolean onPreDraw() {
                AccountSettingsActivity.this.mTitleViewUserIdInfo.getViewTreeObserver().removeOnPreDrawListener(AccountSettingsActivity.this.mOnPreDrawListener);
                AccountSettingsActivity.this.mTitleYBase = (float) AccountSettingsActivity.this.mTitleViewUserIdInfo.getTop();
                return true;
            }
        };
        this.mTitleViewUserIdInfo.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        Bitmap background = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        background.eraseColor(getResources().getColor(R.color.title_view_color));
        this.mActionBar.setBackgroundDrawable(new BitmapDrawable(getResources(), background));
        updateBlurMeasureSize();
    }

    protected void onResume() {
        super.onResume();
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
        AccountManager accountManager = AccountManager.get(this);
        this.mAccount = ExtraAccountManager.getXiaomiAccount(this);
        if (this.mAccount == null) {
            startWelcomeActivity();
            finish();
            return;
        }
        if (TextUtils.isEmpty(accountManager.getPassword(this.mAccount))) {
            accountManager.getAuthToken(this.mAccount, Constants.PASSPORT_API_SID, null, this, new AccountManagerCallback<Bundle>() {
                public void run(AccountManagerFuture<Bundle> future) {
                    try {
                        if (TextUtils.isEmpty(((Bundle) future.getResult()).getString(MiAccountManager.KEY_AUTHTOKEN))) {
                            AccountSettingsActivity.this.finish();
                        }
                    } catch (OperationCanceledException e) {
                        e.printStackTrace();
                        AccountSettingsActivity.this.finish();
                    } catch (AuthenticatorException e2) {
                        e2.printStackTrace();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }, null);
        }
        FragmentManager fm = getFragmentManager();
        if (MiAccountManager.XIAOMI_ACCOUNT_TYPE.equals(this.mAccount.type)) {
            AccountSettingsFragment settingFragment = (AccountSettingsFragment) fm.findFragmentByTag(TAG_FRM_ACTIVATED);
            if (settingFragment == null) {
                addSettingFragment(fm);
            } else {
                settingFragment.setRefreshUserInfoInterface(this);
            }
        }
    }

    private void startWelcomeActivity() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.EXTRA_SHOW_ACCOUNT_SETTINGS, true);
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setPackage(getPackageName());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClick(View view) {
        if (this.mTitleBarView == view) {
            Intent intent = new Intent(this, UserDetailInfoActivity.class);
            intent.putExtra(Constants.EXTRA_ACCOUNT, this.mAccount);
            startActivity(intent);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SCAN_BARCODE && resultCode == -1) {
            String url = data.getStringExtra(Constants.KEY_RESULT);
            if (!TextUtils.isEmpty(url) && url.contains("account.xiaomi.com/longPolling/login")) {
                Intent webViewIntent = new Intent(this, AccountWebActivity.class);
                webViewIntent.putExtra(AccountWebActivity.EXTRA_REQUEST_URL, url);
                startActivity(webViewIntent);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addSettingFragment(FragmentManager fm) {
        AccountSettingsFragment f = new AccountSettingsFragment();
        f.setRefreshUserInfoInterface(this);
        Bundle args = new Bundle();
        args.putParcelable(AccountUnactivatedFragment.EXTRA_ACCOUNT, this.mAccount);
        f.setArguments(args);
        fm.beginTransaction().setTransition(4099).replace(16908290, f, TAG_FRM_ACTIVATED).commit();
    }

    public void onRefreshUserInfo() {
        AccountManager am = (AccountManager) getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT);
        Account account = this.mAccount;
        if (account != null) {
            if (this.mUserIdView != null) {
                String userName = am.getUserData(account, Constants.ACCOUNT_USER_NAME);
                if (TextUtils.isEmpty(userName)) {
                    this.mUserIdView.setText(account.name);
                } else {
                    this.mUserIdView.setText(userName);
                }
            }
            if (!(this.mUserInfoView == null || TextUtils.isEmpty(account.name))) {
                this.mUserInfoView.setVisibility(0);
                this.mUserInfoView.setText(account.name);
            }
            if (this.mAvatarView != null) {
                Bitmap origin;
                this.mAvatarView.setVisibility(0);
                Bitmap avatar = null;
                String avatarFileName = am.getUserData(account, Constants.ACCOUNT_AVATAR_FILE_NAME);
                if (avatarFileName != null) {
                    File avatarFile = getFileStreamPath(avatarFileName);
                    if (avatarFile.exists()) {
                        origin = BitmapFactory.decodeFile(avatarFile.getAbsolutePath());
                        if (origin != null) {
                            avatar = miui.graphics.BitmapFactory.createPhoto(this, origin);
                            setActionBarBlurBackground(origin);
                            origin.recycle();
                        }
                    }
                }
                if (avatar == null) {
                    origin = BitmapFactory.decodeResource(getResources(), R.drawable.default_avatar_in_settings);
                    if (origin != null) {
                        avatar = miui.graphics.BitmapFactory.createPhoto(this, origin);
                        origin.recycle();
                    }
                }
                if (avatar != null) {
                    this.mAvatarView.setImageBitmap(avatar);
                }
            }
        }
    }

    public void setListView(ListView listView) {
        this.mListView = listView;
    }

    public void onStartScroll() {
    }

    public void onStopScroll() {
    }

    public void onScroll(int state, float offset) {
        float alpha = offset * 2.0f;
        if (((double) alpha) > 2.0d) {
            alpha = 2.0f;
        } else if (alpha < 0.0f) {
            alpha = 0.0f;
        }
        if (alpha <= 1.0f) {
            this.mTitleAvatarView.setAlpha(0.0f);
        } else {
            this.mTitleAvatarView.setAlpha(alpha - 1.0f);
        }
        this.mTitleViewUserIdInfo.setY(this.mTitleYBase - ((2.0f - alpha) * this.mTitleYRange));
        this.mTitleViewUserIdInfo.setScaleX((float) ((((double) (2.0f - alpha)) * 0.2d) + 1.0d));
        this.mTitleViewUserIdInfo.setScaleY((float) ((((double) (2.0f - alpha)) * 0.2d) + 1.0d));
    }

    public void onFling(float distance, int duration) {
    }

    public boolean onContentScrolled() {
        if (this.mListView == null) {
            return true;
        }
        View child = this.mListView.getChildAt(0);
        if (child == null) {
            return false;
        }
        if (this.mListView.getFirstVisiblePosition() == 0 && child.getTop() == 0) {
            return false;
        }
        return true;
    }

    public void setUserInfoView(View view) {
        if (Build.IS_TABLET) {
            this.mTitleBarView = (LinearLayout) view.findViewById(R.id.title_bar);
            this.mAvatarView = (ImageView) view.findViewById(R.id.avatar);
            this.mUserIdView = (TextView) view.findViewById(R.id.user_id);
            this.mUserInfoView = (TextView) view.findViewById(R.id.user_info);
            if (this.mTitleBarView != null) {
                this.mTitleBarView.setOnClickListener(this);
            }
        }
    }

    public void setActionBarBlurBackground(Bitmap bm) {
        if (!Build.IS_TABLET) {
            Bitmap resize = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), (int) ((((float) bm.getHeight()) * ((float) sBlurBackgroundHeight)) / ((float) sBlurBackgroundWidth)));
            Bitmap blur = fastBlur(resize, 25);
            new Canvas(blur).drawColor(1392508928);
            this.mActionBar.setBackgroundDrawable(new BitmapDrawable(getResources(), blur));
            resize.recycle();
        }
    }

    public static Bitmap fastBlur(Bitmap bmpIn, int radius) {
        return miui.graphics.BitmapFactory.fastBlur(bmpIn, radius);
    }

    private void updateBlurMeasureSize() {
        sBlurBackgroundWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        sBlurBackgroundHeight = getResources().getDimensionPixelSize(R.dimen.action_bar_height);
    }
}
