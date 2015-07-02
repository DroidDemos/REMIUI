package com.google.android.play.drawer;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.play.dfe.api.PlayDfeApiProvider;
import com.google.android.play.drawer.PlayDrawerDownloadSwitchRow.OnCheckedChangeListener;
import com.google.android.play.drawer.PlayDrawerLayout.PlayDrawerContentClickListener;
import com.google.android.play.drawer.PlayDrawerLayout.PlayDrawerDownloadSwitchConfig;
import com.google.android.play.drawer.PlayDrawerLayout.PlayDrawerPrimaryAction;
import com.google.android.play.drawer.PlayDrawerLayout.PlayDrawerSecondaryAction;
import com.google.android.play.drawer.PlayDrawerProfileInfoView.OnAvatarClickedListener;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.utils.PlayCommonLog;
import com.google.android.play.utils.PlayUtils;
import com.google.android.play.utils.collections.Lists;
import com.google.android.play.utils.collections.Maps;
import com.google.android.play.utils.collections.Sets;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;
import java.util.Map;
import java.util.Set;

class PlayDrawerAdapter extends BaseAdapter {
    private final Map<String, DocV2> mAccountDocV2s;
    private boolean mAccountListExpanded;
    private final BitmapLoader mBitmapLoader;
    private Context mContext;
    private Account mCurrentAccount;
    private boolean mCurrentAvatarClickable;
    private boolean mDownloadOnlyEnabled;
    private PlayDrawerDownloadSwitchConfig mDownloadSwitchConfig;
    private boolean mHasAccounts;
    private final LayoutInflater mInflater;
    private final Set<String> mIsAccountDocLoaded;
    private ListView mListView;
    private Account[] mNonCurrentAccounts;
    private PlayDfeApiProvider mPlayDfeApiProvider;
    private PlayDrawerContentClickListener mPlayDrawerContentClickListener;
    private PlayDrawerLayout mPlayDrawerLayout;
    private final List<PlayDrawerPrimaryAction> mPrimaryActions;
    private int mProfileContainerPosition;
    private PlayDrawerProfileInfoView mProfileInfoView;
    private final List<PlayDrawerSecondaryAction> mSecondaryActions;
    private boolean mShowDownloadOnlyToggle;

    public PlayDrawerAdapter(Context context, boolean isAccountListExpanded, PlayDrawerContentClickListener playDrawerContentClickListener, PlayDfeApiProvider playDfeApiProvider, BitmapLoader bitmapLoader, PlayDrawerLayout playDrawerLayout, ListView listView) {
        this.mPrimaryActions = Lists.newArrayList();
        this.mSecondaryActions = Lists.newArrayList();
        this.mNonCurrentAccounts = new Account[0];
        this.mAccountDocV2s = Maps.newHashMap();
        this.mIsAccountDocLoaded = Sets.newHashSet();
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mPlayDfeApiProvider = playDfeApiProvider;
        this.mBitmapLoader = bitmapLoader;
        this.mPlayDrawerContentClickListener = playDrawerContentClickListener;
        this.mPlayDrawerLayout = playDrawerLayout;
        this.mListView = listView;
        this.mProfileContainerPosition = -1;
        this.mAccountListExpanded = isAccountListExpanded;
    }

    public boolean isAccountListExpanded() {
        return this.mAccountListExpanded;
    }

    public void updateContent(String currentAccountName, Account[] accounts, List<PlayDrawerPrimaryAction> primaryActions, PlayDrawerDownloadSwitchConfig downloadSwitchConfig, List<PlayDrawerSecondaryAction> secondaryActions) {
        int numAccounts = accounts.length;
        if (numAccounts == 0) {
            this.mCurrentAccount = null;
            this.mNonCurrentAccounts = new Account[0];
        } else {
            this.mNonCurrentAccounts = new Account[(numAccounts - 1)];
            Account[] arr$ = accounts;
            int len$ = arr$.length;
            int i$ = 0;
            int nonCurrentIndex = 0;
            while (i$ < len$) {
                int nonCurrentIndex2;
                Account account = arr$[i$];
                if (currentAccountName.equals(account.name)) {
                    this.mCurrentAccount = account;
                    nonCurrentIndex2 = nonCurrentIndex;
                } else if (nonCurrentIndex == numAccounts - 1) {
                    PlayCommonLog.wtf("current account not found in accounts", new Object[0]);
                    this.mCurrentAccount = account;
                    break;
                } else {
                    nonCurrentIndex2 = nonCurrentIndex + 1;
                    this.mNonCurrentAccounts[nonCurrentIndex] = account;
                }
                i$++;
                nonCurrentIndex = nonCurrentIndex2;
            }
        }
        this.mHasAccounts = numAccounts > 0;
        this.mPrimaryActions.clear();
        this.mSecondaryActions.clear();
        this.mPrimaryActions.addAll(primaryActions);
        this.mSecondaryActions.addAll(secondaryActions);
        this.mDownloadSwitchConfig = downloadSwitchConfig;
        this.mShowDownloadOnlyToggle = downloadSwitchConfig != null;
        this.mDownloadOnlyEnabled = this.mShowDownloadOnlyToggle ? downloadSwitchConfig.isChecked : false;
        notifyDataSetChanged();
    }

    public void collapseAccountListIfNeeded() {
        if (this.mNonCurrentAccounts.length > 0 && this.mAccountListExpanded) {
            toggleAccountsList();
        }
    }

    public void setCurrentAvatarClickable(boolean clickable) {
        this.mCurrentAvatarClickable = clickable;
    }

    public int getCount() {
        int result = this.mHasAccounts ? 1 : 0;
        if (this.mAccountListExpanded) {
            return result + this.mNonCurrentAccounts.length;
        }
        result = ((result + 1) + this.mPrimaryActions.size()) + 1;
        if (this.mShowDownloadOnlyToggle) {
            result++;
        }
        return result + this.mSecondaryActions.size();
    }

    public Object getItem(int position) {
        if (this.mHasAccounts) {
            if (position == 0) {
                return this.mCurrentAccount.name;
            }
            position--;
        }
        if (this.mAccountListExpanded) {
            return this.mNonCurrentAccounts[position];
        }
        if (position == 0) {
            return null;
        }
        position--;
        int primaryActionsCount = this.mPrimaryActions.size();
        if (position < primaryActionsCount) {
            return this.mPrimaryActions.get(position);
        }
        position -= primaryActionsCount;
        if (this.mShowDownloadOnlyToggle) {
            if (position == 0) {
                return this.mDownloadSwitchConfig;
            }
            position--;
        }
        if (position == 0) {
            return null;
        }
        return this.mSecondaryActions.get(position - 1);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getItemViewType(int position) {
        if (this.mHasAccounts) {
            if (position == 0) {
                return 0;
            }
            position--;
        }
        if (this.mAccountListExpanded) {
            return 1;
        }
        if (position == 0) {
            return 2;
        }
        position--;
        if (position < this.mPrimaryActions.size()) {
            PlayDrawerPrimaryAction action = (PlayDrawerPrimaryAction) this.mPrimaryActions.get(position);
            if (this.mShowDownloadOnlyToggle && this.mDownloadOnlyEnabled && !action.isAvailableInDownloadOnly) {
                return 5;
            }
            if (action.isActive) {
                return 3;
            }
            return 4;
        }
        position -= this.mPrimaryActions.size();
        if (position == 0) {
            return 6;
        }
        position--;
        if (this.mShowDownloadOnlyToggle) {
            if (position == 0) {
                return 8;
            }
            position--;
        }
        return 7;
    }

    public int getViewTypeCount() {
        return 9;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
        switch (getItemViewType(position)) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return false;
            default:
                return true;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        Object data = getItem(position);
        switch (viewType) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return getProfileInfoView(convertView, parent, position);
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return getAccountView(convertView, parent, (Account) data);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return getPrimaryActionsTopSpacing(convertView, parent);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return getPrimaryActionView(convertView, parent, (PlayDrawerPrimaryAction) data, true, false);
            case R.styleable.WalletImFormEditText_required /*4*/:
                return getPrimaryActionView(convertView, parent, (PlayDrawerPrimaryAction) data, false, false);
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return getPrimaryActionView(convertView, parent, (PlayDrawerPrimaryAction) data, false, true);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return getSecondaryActionsTopSeparator(convertView, parent);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return getSecondaryActionView(convertView, parent, (PlayDrawerSecondaryAction) data);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return getDownloadToggleView(convertView, parent);
            default:
                throw new UnsupportedOperationException("View type " + viewType + " not supported");
        }
    }

    private View getProfileInfoView(View convertView, ViewGroup parent, int position) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = this.mInflater.inflate(com.google.android.play.R.layout.play_drawer_profile_info, parent, false);
        }
        this.mProfileInfoView = (PlayDrawerProfileInfoView) view;
        this.mProfileContainerPosition = position;
        final Account finalCurrentAccount = this.mCurrentAccount;
        final String finalCurrentAccountName = this.mCurrentAccount.name;
        this.mProfileInfoView.configure(finalCurrentAccount, this.mNonCurrentAccounts, this.mAccountDocV2s, this.mBitmapLoader);
        this.mProfileInfoView.setCurrentAvatarClickable(this.mCurrentAvatarClickable);
        this.mPlayDfeApiProvider.getPlayDfeApi(finalCurrentAccount).getPlusProfile(new Listener<PlusProfileResponse>() {
            public void onResponse(PlusProfileResponse plusProfileResponse) {
                DocV2 prevDocV2 = (DocV2) PlayDrawerAdapter.this.mAccountDocV2s.get(finalCurrentAccountName);
                DocV2 newDocV2 = plusProfileResponse.partialUserProfile;
                PlayDrawerAdapter.this.mAccountDocV2s.put(finalCurrentAccountName, newDocV2);
                PlayDrawerAdapter.this.mIsAccountDocLoaded.add(finalCurrentAccountName);
                if (prevDocV2 == null && newDocV2 != null && finalCurrentAccountName.equals(PlayDrawerAdapter.this.mCurrentAccount.name) && PlayDrawerAdapter.this.isProfileContainerVisible()) {
                    PlayDrawerAdapter.this.mProfileInfoView.configure(finalCurrentAccount, PlayDrawerAdapter.this.mNonCurrentAccounts, PlayDrawerAdapter.this.mAccountDocV2s, PlayDrawerAdapter.this.mBitmapLoader);
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }, true);
        loadAllSecondaryAccountDocV2sOnce();
        this.mProfileInfoView.setAccountListExpanded(this.mAccountListExpanded);
        this.mProfileInfoView.setOnAvatarClickListener(new OnAvatarClickedListener() {
            public void onAvatarClicked(Account account) {
                if (account == PlayDrawerAdapter.this.mCurrentAccount) {
                    if (PlayDrawerAdapter.this.mPlayDrawerContentClickListener.onCurrentAccountClicked(PlayDrawerAdapter.this.mIsAccountDocLoaded.contains(PlayDrawerAdapter.this.mCurrentAccount.name), (DocV2) PlayDrawerAdapter.this.mAccountDocV2s.get(PlayDrawerAdapter.this.mCurrentAccount.name))) {
                        PlayDrawerAdapter.this.mPlayDrawerLayout.closeDrawer();
                    }
                } else if (PlayDrawerAdapter.this.mPlayDrawerContentClickListener.onSecondaryAccountClicked(account.name)) {
                    PlayDrawerAdapter.this.mPlayDrawerLayout.closeDrawer();
                }
            }
        });
        if (this.mNonCurrentAccounts.length > 0) {
            this.mProfileInfoView.setAccountListEnabled(true);
            this.mProfileInfoView.setAccountTogglerListener(new OnClickListener() {
                public void onClick(View v) {
                    PlayDrawerAdapter.this.mPlayDrawerContentClickListener.onAccountListToggleButtonClicked(!PlayDrawerAdapter.this.mAccountListExpanded);
                    PlayDrawerAdapter.this.toggleAccountsList();
                }
            });
        } else {
            this.mProfileInfoView.setAccountListEnabled(false);
            this.mProfileInfoView.setAccountTogglerListener(null);
        }
        return this.mProfileInfoView;
    }

    private void loadAllSecondaryAccountDocV2sOnce() {
        for (Account account : this.mNonCurrentAccounts) {
            final String accountName = account.name;
            if (!this.mIsAccountDocLoaded.contains(accountName)) {
                this.mPlayDfeApiProvider.getPlayDfeApi(account).getPlusProfile(new Listener<PlusProfileResponse>() {
                    public void onResponse(PlusProfileResponse plusProfileResponse) {
                        PlayDrawerAdapter.this.mAccountDocV2s.put(accountName, plusProfileResponse.partialUserProfile);
                        PlayDrawerAdapter.this.mIsAccountDocLoaded.add(accountName);
                        boolean isAllSeconaryDocV2sLoaded = true;
                        for (int i = 0; i < PlayDrawerAdapter.this.mNonCurrentAccounts.length; i++) {
                            if (!PlayDrawerAdapter.this.mIsAccountDocLoaded.contains(accountName)) {
                                isAllSeconaryDocV2sLoaded = false;
                                break;
                            }
                        }
                        if (isAllSeconaryDocV2sLoaded) {
                            PlayDrawerAdapter.this.notifyDataSetChanged();
                        }
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                }, true);
            }
        }
    }

    private void toggleAccountsList() {
        this.mAccountListExpanded = !this.mAccountListExpanded;
        notifyDataSetChanged();
    }

    private View getAccountView(View convertView, ViewGroup parent, Account account) {
        PlayDrawerAccountRow accountRow = (PlayDrawerAccountRow) (convertView != null ? convertView : this.mInflater.inflate(com.google.android.play.R.layout.play_drawer_account_row, parent, false));
        final String accountName = account.name;
        accountRow.bind((DocV2) this.mAccountDocV2s.get(accountName), accountName, this.mBitmapLoader);
        accountRow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PlayDrawerAdapter.this.mPlayDrawerContentClickListener.onSecondaryAccountClicked(accountName)) {
                    PlayDrawerAdapter.this.mPlayDrawerLayout.closeDrawer();
                }
            }
        });
        return accountRow;
    }

    private View getPrimaryActionsTopSpacing(View convertView, ViewGroup parent) {
        return convertView != null ? convertView : this.mInflater.inflate(com.google.android.play.R.layout.play_drawer_primary_actions_top_spacing, parent, false);
    }

    private View getPrimaryActionView(View convertView, ViewGroup parent, final PlayDrawerPrimaryAction primaryAction, boolean active, boolean disabled) {
        int layoutId;
        Resources res = parent.getResources();
        if (active) {
            layoutId = com.google.android.play.R.layout.play_drawer_primary_action_active;
        } else if (disabled) {
            layoutId = com.google.android.play.R.layout.play_drawer_primary_action_disabled;
        } else {
            layoutId = com.google.android.play.R.layout.play_drawer_primary_action_regular;
        }
        TextView destinationRow = (TextView) (convertView != null ? convertView : this.mInflater.inflate(layoutId, parent, false));
        destinationRow.setText(primaryAction.actionText);
        if (primaryAction.iconResId > 0) {
            Drawable drawable;
            if (!active || primaryAction.activeIconResId <= 0) {
                drawable = res.getDrawable(primaryAction.iconResId);
            } else {
                drawable = res.getDrawable(primaryAction.activeIconResId);
            }
            if (disabled) {
                drawable.setAlpha(66);
            } else {
                drawable.setAlpha(255);
            }
            setDrawableStart(destinationRow, drawable, PlayUtils.useLtr(this.mContext));
        } else {
            destinationRow.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        if (active && primaryAction.activeTextColorResId > 0) {
            destinationRow.setTextColor(res.getColor(primaryAction.activeTextColorResId));
        } else if (disabled) {
            destinationRow.setTextColor(res.getColor(com.google.android.play.R.color.play_disabled_grey));
        } else {
            destinationRow.setTextColor(res.getColor(com.google.android.play.R.color.play_fg_primary));
        }
        destinationRow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PlayDrawerAdapter.this.mPlayDrawerContentClickListener.onPrimaryActionClicked(primaryAction)) {
                    PlayDrawerAdapter.this.mPlayDrawerLayout.closeDrawer();
                }
            }
        });
        setTextAlignmentStart(destinationRow, PlayUtils.useLtr(this.mContext));
        return destinationRow;
    }

    private View getSecondaryActionsTopSeparator(View convertView, ViewGroup parent) {
        return convertView != null ? convertView : this.mInflater.inflate(com.google.android.play.R.layout.play_drawer_secondary_actions_top_separator, parent, false);
    }

    private View getSecondaryActionView(View convertView, ViewGroup parent, final PlayDrawerSecondaryAction secondaryAction) {
        TextView secondaryRow = (TextView) (convertView != null ? convertView : this.mInflater.inflate(com.google.android.play.R.layout.play_drawer_secondary_action, parent, false));
        secondaryRow.setText(secondaryAction.actionText);
        secondaryRow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PlayDrawerAdapter.this.mPlayDrawerContentClickListener.onSecondaryActionClicked(secondaryAction)) {
                    PlayDrawerAdapter.this.mPlayDrawerLayout.closeDrawer();
                }
            }
        });
        setTextAlignmentStart(secondaryRow, PlayUtils.useLtr(this.mContext));
        return secondaryRow;
    }

    private View getDownloadToggleView(View convertView, ViewGroup parent) {
        PlayDrawerDownloadSwitchRow toggleRow;
        if (convertView == null) {
            toggleRow = (PlayDrawerDownloadSwitchRow) this.mInflater.inflate(com.google.android.play.R.layout.play_drawer_download_toggle, parent, false);
            toggleRow.configure(this.mDownloadSwitchConfig);
            toggleRow.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(PlayDrawerDownloadSwitchRow row, boolean checked) {
                    PlayDrawerAdapter.this.mDownloadOnlyEnabled = checked;
                    PlayDrawerAdapter.this.mPlayDrawerContentClickListener.onDownloadToggleClicked(checked);
                    PlayDrawerAdapter.this.notifyDataSetChanged();
                }
            });
        } else {
            toggleRow = (PlayDrawerDownloadSwitchRow) convertView;
        }
        toggleRow.setCheckedNoCallbacks(this.mDownloadOnlyEnabled);
        return toggleRow;
    }

    public boolean hasStableIds() {
        return false;
    }

    private boolean isProfileContainerVisible() {
        int firstVisiblePosition = this.mListView.getFirstVisiblePosition();
        return this.mProfileContainerPosition >= firstVisiblePosition && this.mProfileContainerPosition <= (this.mListView.getChildCount() + firstVisiblePosition) - 1;
    }

    public static void setDrawableStart(TextView textView, Drawable drawable, boolean useLtr) {
        if (useLtr) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }
    }

    public static void setTextAlignmentStart(TextView textView, boolean useLtr) {
        if (useLtr) {
            textView.setGravity(19);
        } else {
            textView.setGravity(21);
        }
    }
}
