package com.google.android.finsky.activities.myapps;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.adapters.PaginatedListAdapter;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.AccountSelectorView;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.PlayCardViewMyApps;
import com.google.android.finsky.layout.play.PlayCardViewMyApps.OnArchiveActionListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.wallet.instrumentmanager.R;

public class MyAppsLibraryAdapter extends PaginatedListAdapter implements MyAppsListAdapter {
    private static boolean sEnableRemoveAppsFromLibrary;
    private final BitmapLoader mBitmapLoader;
    private boolean mHasAccountSwitcher;
    private Installer mInstaller;
    private boolean mIsMultiChoiceMode;
    private final int mLeadingSpacerHeight;
    private Libraries mLibraries;
    private DfeList mList;
    private final OnArchiveActionListener mOnArchiveActionListener;
    private final OnClickListener mOnClickListener;
    private final OnLongClickListener mOnLongClickListener;
    private PackageStateRepository mPackageStateRepository;
    private PlayStoreUiElementNode mParentNode;
    private DfeToc mToc;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode;

        static {
            $SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode = new int[FooterMode.values().length];
            try {
                $SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode[FooterMode.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode[FooterMode.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode[FooterMode.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        sEnableRemoveAppsFromLibrary = ((Boolean) G.enableRemoveAppsFromLibrary.get()).booleanValue();
    }

    public MyAppsLibraryAdapter(AuthenticatedActivity authenticatedActivity, NavigationManager navManager, DfeToc toc, Libraries libraries, PackageStateRepository packageStateRepository, Installer installer, BitmapLoader bitmapLoader, OnClickListener onClickListener, OnArchiveActionListener onArchiveActionListener, OnLongClickListener onLongClickListener, PlayStoreUiElementNode parentNode) {
        super(authenticatedActivity, navManager, false, true);
        this.mParentNode = null;
        this.mBitmapLoader = bitmapLoader;
        this.mOnClickListener = onClickListener;
        this.mOnArchiveActionListener = onArchiveActionListener;
        this.mOnLongClickListener = onLongClickListener;
        this.mParentNode = parentNode;
        this.mIsMultiChoiceMode = false;
        this.mToc = toc;
        this.mLibraries = libraries;
        this.mPackageStateRepository = packageStateRepository;
        this.mInstaller = installer;
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(authenticatedActivity, 0, 0);
    }

    public void setDfeList(DfeList dfeList) {
        this.mList = dfeList;
        notifyDataSetChanged();
    }

    public void showAccountSwitcher() {
        this.mHasAccountSwitcher = true;
        notifyDataSetChanged();
    }

    public Document getDocument(int position) {
        Object item = getItem(position);
        return item instanceof Document ? (Document) item : null;
    }

    public int getCount() {
        if (this.mList == null) {
            return 0;
        }
        int count = this.mList.getCount();
        if (isMoreDataAvailable()) {
            count++;
        }
        if (count == 0) {
            return 0;
        }
        if (this.mHasAccountSwitcher) {
            count++;
        }
        return count + 1;
    }

    public int getViewTypeCount() {
        return 5;
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return 4;
        }
        if (position != getCount() - 1) {
            return (this.mHasAccountSwitcher && position == 1) ? 3 : 0;
        } else {
            switch (AnonymousClass1.$SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode[getFooterMode().ordinal()]) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    return 1;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return 2;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return 0;
                default:
                    throw new IllegalStateException("No footer or item at row " + position);
            }
        }
    }

    public Object getItem(int position) {
        if (position == 0) {
            return null;
        }
        position--;
        if (this.mHasAccountSwitcher && position == 0) {
            return null;
        }
        if (this.mHasAccountSwitcher) {
            position--;
        }
        return this.mList.getItem(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return getDocView(position, getDocument(position), convertView, parent);
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return getLoadingFooterView(convertView, parent);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return getErrorFooterView(convertView, parent);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return getAccountSwitcherView(convertView, parent);
            case R.styleable.WalletImFormEditText_required /*4*/:
                return getLeadingSpacerView(convertView, parent);
            default:
                throw new IllegalStateException("Unknown type for getView " + position);
        }
    }

    private View getDocView(int position, Document doc, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.mLayoutInflater.inflate(com.android.vending.R.layout.play_card_myapps, parent, false);
        }
        PlayCardViewMyApps entry = (PlayCardViewMyApps) convertView;
        if (doc == null) {
            entry.bindLoading();
        } else {
            boolean availableForInstall = isDocAvailableForInstall(doc);
            boolean clickable = this.mIsMultiChoiceMode ? availableForInstall : true;
            PlayCardUtils.bindCard(entry, doc, "my_apps:library", this.mBitmapLoader, null, !clickable, null, this.mParentNode, true, -1);
            if (clickable) {
                entry.setOnClickListener(this.mOnClickListener);
            } else {
                entry.setOnClickListener(null);
            }
            if (sEnableRemoveAppsFromLibrary) {
                if (this.mIsMultiChoiceMode || !AppActionAnalyzer.canRemoveFromLibrary(doc)) {
                    entry.setArchivable(false, null);
                } else {
                    entry.setArchivable(true, this.mOnArchiveActionListener);
                }
            }
            if (this.mIsMultiChoiceMode || !availableForInstall) {
                entry.setOnLongClickListener(null);
            } else {
                entry.setOnLongClickListener(this.mOnLongClickListener);
            }
        }
        entry.setTag(doc);
        entry.setIdentifier(doc.getDocId());
        return convertView;
    }

    private boolean isDocAvailableForInstall(Document doc) {
        boolean isInstalled;
        String packageName = doc.getAppDetails().packageName;
        if (this.mPackageStateRepository.get(packageName) != null) {
            isInstalled = true;
        } else {
            isInstalled = false;
        }
        InstallerState installerState = this.mInstaller.getState(packageName);
        if (isInstalled || installerState.isDownloadingOrInstalling() || !LibraryUtils.isAvailable(doc, this.mToc, this.mLibraries)) {
            return false;
        }
        return true;
    }

    private View getLeadingSpacerView(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflate(com.android.vending.R.layout.play_list_vspacer, parent, false);
        }
        convertView.getLayoutParams().height = this.mLeadingSpacerHeight;
        convertView.setId(com.android.vending.R.id.play_header_spacer);
        return convertView;
    }

    private View getAccountSwitcherView(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflate(com.android.vending.R.layout.my_apps_library_account_switcher, parent, false);
        }
        AccountSelectorView accountNameView = (AccountSelectorView) convertView.findViewById(com.android.vending.R.id.account_name);
        accountNameView.configure();
        accountNameView.setIdentifier("account_switcher");
        return convertView;
    }

    protected void retryLoadingItems() {
        if (this.mList != null) {
            this.mList.retryLoadItems();
        }
    }

    protected String getLastRequestError() {
        return ErrorStrings.get(this.mContext, this.mList.getVolleyError());
    }

    protected boolean isMoreDataAvailable() {
        return this.mList != null && this.mList.isMoreAvailable();
    }

    public static Document getViewDoc(View v) {
        return (Document) v.getTag();
    }

    public void setMultiChoiceMode(boolean isMultiChoiceMode) {
        this.mIsMultiChoiceMode = isMultiChoiceMode;
        notifyDataSetChanged();
    }
}
