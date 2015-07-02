package com.google.android.finsky.layout.play;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.DebugActivity;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.activities.TabbedBrowseFragment;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.billing.carrierbilling.debug.DcbDebugActivity;
import com.google.android.finsky.config.G;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.actionbar.FinskySearchToolbar;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.GPlusDialogsHelper;
import com.google.android.finsky.utils.HelpFeedbackUtil;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.drawer.PlayDrawerLayout;
import com.google.android.play.drawer.PlayDrawerLayout.PlayDrawerContentClickListener;
import com.google.android.play.drawer.PlayDrawerLayout.PlayDrawerPrimaryAction;
import com.google.android.play.drawer.PlayDrawerLayout.PlayDrawerSecondaryAction;
import java.util.List;

public class FinskyDrawerLayout extends PlayDrawerLayout implements PlayDrawerContentClickListener {
    private final AccountManager mAccountManager;
    private int mCurrentBackendId;
    private DfeToc mDfeToc;
    private MainActivity mMainActivity;
    private NavigationManager mNavigationManager;
    private final OnAccountsUpdateListener mOnAccountsUpdateListener;
    private final Handler mRefreshHandler;
    private final Runnable mRefreshRunnable;

    public FinskyDrawerLayout(Context context) {
        this(context, null);
    }

    public FinskyDrawerLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mAccountManager = AccountManager.get(context);
        this.mOnAccountsUpdateListener = new OnAccountsUpdateListener() {
            public void onAccountsUpdated(Account[] accounts) {
                Utils.syncDebugActivityStatus(context);
                FinskyDrawerLayout.this.refresh();
            }
        };
        this.mCurrentBackendId = 0;
        this.mRefreshRunnable = new Runnable() {
            public void run() {
                FinskyDrawerLayout.this.refresh();
            }
        };
        this.mRefreshHandler = new Handler(Looper.myLooper());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAccountManager.addOnAccountsUpdatedListener(this.mOnAccountsUpdateListener, null, false);
    }

    protected void onDetachedFromWindow() {
        this.mAccountManager.removeOnAccountsUpdatedListener(this.mOnAccountsUpdateListener);
        super.onDetachedFromWindow();
    }

    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        this.mMainActivity.exitDrawerOpenMode();
    }

    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        enterActionBarDrawerMode();
    }

    private void enterActionBarDrawerMode() {
        boolean useLauncherNameForBreadcrumb = isTopLevelSideDrawerDestination() && this.mMainActivity.getCurrentBackend() == 0;
        this.mMainActivity.enterDrawerOpenMode(this.mMainActivity.getResources().getString(useLauncherNameForBreadcrumb ? R.string.launcher_name : R.string.side_drawer_opened_breadcrumb));
    }

    public void configure(MainActivity activity, Bundle instanceState) {
        boolean isAccountListExpanded;
        boolean isDrawerOpen;
        FinskyApp finskyApp = FinskyApp.get();
        int actionBarHeight = FinskySearchToolbar.getToolbarHeight(activity);
        if (instanceState != null) {
            isAccountListExpanded = instanceState.getBoolean("FinskyDrawerLayout.isAccountListExpanded", false);
        } else {
            isAccountListExpanded = false;
        }
        super.configure(activity, isAccountListExpanded, actionBarHeight, finskyApp.getPlayDfeApiProvider(), finskyApp.getBitmapLoader(), this);
        setCurrentAvatarClickable(true);
        this.mMainActivity = activity;
        this.mNavigationManager = activity.getNavigationManager();
        refresh();
        this.mNavigationManager.addOnBackStackChangedListener(new OnBackStackChangedListener() {
            public void onBackStackChanged() {
                FinskyDrawerLayout.this.setDrawerIndicatorEnabled(FinskyDrawerLayout.this.isTopLevelSideDrawerDestination());
                FinskyDrawerLayout.this.mRefreshHandler.post(FinskyDrawerLayout.this.mRefreshRunnable);
            }
        });
        syncDrawerIndicator();
        if (instanceState != null) {
            isDrawerOpen = instanceState.getBoolean("FinskyDrawerLayout.isDrawerOpened", false);
        } else {
            isDrawerOpen = false;
        }
        if (isDrawerOpen) {
            enterActionBarDrawerMode();
        }
    }

    public void onSaveInstanceState(Bundle instanceState) {
        instanceState.putBoolean("FinskyDrawerLayout.isAccountListExpanded", isAccountListExpanded());
        instanceState.putBoolean("FinskyDrawerLayout.isDrawerOpened", isDrawerOpen());
    }

    public boolean onCurrentAccountClicked(boolean isLoaded, DocV2 playDoc) {
        if (!isLoaded) {
            return false;
        }
        if (playDoc == null) {
            if (!this.mDfeToc.isGplusSignupEnabled()) {
                return false;
            }
            GPlusDialogsHelper.showGPlusSignUpDialog(this.mNavigationManager.getActivePage().getFragmentManager());
            return false;
        } else if (TextUtils.isEmpty(playDoc.detailsUrl)) {
            return false;
        } else {
            this.mNavigationManager.goToDocPage(playDoc.detailsUrl);
            FinskyApp.get().getEventLogger().logClickEvent(123, null, this.mNavigationManager.getActivePage());
            return true;
        }
    }

    public boolean onPrimaryActionClicked(PlayDrawerPrimaryAction primaryAction) {
        if (primaryAction.isActive) {
            return false;
        }
        primaryAction.actionSelectedRunnable.run();
        return true;
    }

    public boolean onSecondaryActionClicked(PlayDrawerSecondaryAction secondaryAction) {
        secondaryAction.actionSelectedRunnable.run();
        return true;
    }

    public boolean onSecondaryAccountClicked(String accountName) {
        this.mMainActivity.switchToAccount(accountName);
        return true;
    }

    public void onAccountListToggleButtonClicked(boolean isListExpanded) {
        logMenuClickEvent(isListExpanded ? 284 : 283);
    }

    public void onDownloadToggleClicked(boolean isDownloadOnly) {
    }

    public void refresh() {
        this.mDfeToc = FinskyApp.get().getToc();
        List<PlayDrawerPrimaryAction> primaryActions = Lists.newArrayList();
        List<PlayDrawerSecondaryAction> secondaryActions = Lists.newArrayList();
        if (this.mDfeToc != null) {
            createPrimaryActions(primaryActions);
        }
        createSecondaryActions(secondaryActions);
        updateContent(FinskyApp.get().getCurrentAccountName(), AccountHandler.getAccounts(getContext()), primaryActions, null, secondaryActions);
        setDrawerIndicatorEnabled(isTopLevelSideDrawerDestination());
    }

    public void updateCurrentBackendId(int backendId) {
        if (this.mCurrentBackendId != backendId) {
            this.mCurrentBackendId = backendId;
            this.mRefreshHandler.post(this.mRefreshRunnable);
        }
    }

    private void createPrimaryActions(List<PlayDrawerPrimaryAction> primaryActions) {
        boolean z;
        int i;
        boolean z2;
        boolean z3;
        Context context = getContext();
        int currentPageType = this.mNavigationManager.getCurrentPageType();
        boolean treatAsAppCorpus = this.mCurrentBackendId == 0 || this.mCurrentBackendId == 9;
        boolean onlyHasOneCorpus = this.mDfeToc.getCorpusList().size() == 1;
        String string = context.getString(R.string.menu_home);
        if (currentPageType == 1 || (onlyHasOneCorpus && currentPageType == 2)) {
            z = true;
        } else {
            z = false;
        }
        primaryActions.add(new PlayDrawerPrimaryAction(string, R.drawable.ic_drawer_home, R.drawable.ic_drawer_home_selected, R.color.play_multi_secondary, z, false, new Runnable() {
            public void run() {
                FinskyDrawerLayout.this.mNavigationManager.goToAggregatedHome(FinskyDrawerLayout.this.mDfeToc);
            }
        }));
        if (treatAsAppCorpus) {
            i = 3;
        } else {
            i = this.mCurrentBackendId;
        }
        PlayDrawerPrimaryAction collectionAction = makeMyCollectionAction(i);
        if (collectionAction != null) {
            primaryActions.add(collectionAction);
        }
        if (!(treatAsAppCorpus || onlyHasOneCorpus)) {
            PlayDrawerPrimaryAction shopAction = getShopAction(context, currentPageType);
            if (shopAction != null) {
                primaryActions.add(shopAction);
            }
        }
        String string2 = context.getString(R.string.menu_my_wishlist);
        if (currentPageType == 10) {
            z2 = true;
        } else {
            z2 = false;
        }
        primaryActions.add(new PlayDrawerPrimaryAction(string2, R.drawable.ic_drawer_wishlist, R.drawable.ic_drawer_wishlist_selected, R.color.play_multi_secondary, z2, false, new Runnable() {
            public void run() {
                FinskyDrawerLayout.this.logMenuClickEvent(108);
                FinskyDrawerLayout.this.mNavigationManager.goToMyWishlist();
            }
        }));
        if (!TextUtils.isEmpty(this.mDfeToc.getSocialHomeUrl())) {
            boolean z4;
            String string3 = context.getString(R.string.side_drawer_social_home);
            if (currentPageType == 12) {
                z4 = true;
            } else {
                z4 = false;
            }
            primaryActions.add(new PlayDrawerPrimaryAction(string3, R.drawable.ic_drawer_people, R.drawable.ic_drawer_people_selected, R.color.play_multi_secondary, z4, false, new Runnable() {
                public void run() {
                    FinskyDrawerLayout.this.logMenuClickEvent(124);
                    FinskyDrawerLayout.this.mNavigationManager.goToSocialHome(FinskyDrawerLayout.this.mDfeToc.getSocialHomeUrl(), FinskyDrawerLayout.this.mDfeToc);
                }
            }));
        }
        String string4 = context.getString(R.string.side_drawer_my_account);
        if (currentPageType == 13) {
            z3 = true;
        } else {
            z3 = false;
        }
        primaryActions.add(new PlayDrawerPrimaryAction(string4, R.drawable.ic_drawer_myaccount, R.drawable.ic_drawer_myaccount_selected, R.color.play_multi_secondary, z3, false, new Runnable() {
            public void run() {
                FinskyDrawerLayout.this.logMenuClickEvent(127);
                FinskyDrawerLayout.this.mNavigationManager.goToMyAccount();
            }
        }));
    }

    private PlayDrawerPrimaryAction getShopAction(Context context, int currentPageType) {
        final CorpusMetadata metadata = this.mDfeToc.getCorpus(this.mCurrentBackendId);
        if (metadata == null) {
            return null;
        }
        boolean z;
        String shopName = !TextUtils.isEmpty(metadata.shopName) ? metadata.shopName : context.getString(R.string.side_drawer_shop);
        int drawerShopDrawable = CorpusResourceUtils.getDrawerShopDrawable(this.mCurrentBackendId);
        int drawerShopSelectedDrawable = CorpusResourceUtils.getDrawerShopSelectedDrawable(this.mCurrentBackendId);
        int secondaryColorResId = CorpusResourceUtils.getSecondaryColorResId(this.mCurrentBackendId);
        if (currentPageType == 2) {
            z = true;
        } else {
            z = false;
        }
        return new PlayDrawerPrimaryAction(shopName, drawerShopDrawable, drawerShopSelectedDrawable, secondaryColorResId, z, false, new Runnable() {
            public void run() {
                FinskyDrawerLayout.this.mNavigationManager.goToCorpusHome(metadata.landingUrl, metadata.name, FinskyDrawerLayout.this.mCurrentBackendId, FinskyDrawerLayout.this.mDfeToc);
            }
        });
    }

    private PlayDrawerPrimaryAction makeMyCollectionAction(final int backendId) {
        final Context context = getContext();
        String myCollectionDescription = CorpusResourceUtils.getCorpusMyCollectionDescription(backendId);
        if (TextUtils.isEmpty(myCollectionDescription)) {
            return null;
        }
        boolean isActive;
        if (backendId == 3 && this.mNavigationManager.getCurrentPageType() == 3) {
            isActive = true;
        } else {
            isActive = false;
        }
        return new PlayDrawerPrimaryAction(myCollectionDescription, CorpusResourceUtils.getDrawerMyCollectionDrawable(backendId), CorpusResourceUtils.getDrawerMyCollectionSelectedDrawable(backendId), CorpusResourceUtils.getSecondaryColorResId(backendId), isActive, false, new Runnable() {
            public void run() {
                int clickType;
                switch (backendId) {
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        clickType = 106;
                        break;
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        clickType = 104;
                        break;
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                        clickType = 105;
                        break;
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        clickType = 107;
                        break;
                    default:
                        clickType = 103;
                        break;
                }
                FinskyDrawerLayout.this.logMenuClickEvent(clickType);
                switch (backendId) {
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        if (IntentUtils.isConsumptionAppInstalled(context.getPackageManager(), backendId)) {
                            context.startActivity(IntentUtils.buildConsumptionAppLaunchIntent(context, backendId, FinskyApp.get().getCurrentAccountName()));
                            return;
                        }
                        FinskyDrawerLayout.this.mNavigationManager.showAppNeededDialog(backendId);
                        return;
                    default:
                        FinskyDrawerLayout.this.mNavigationManager.goToMyDownloads(FinskyApp.get().getToc());
                        return;
                }
            }
        });
    }

    private void createSecondaryActions(List<PlayDrawerSecondaryAction> secondaryActions) {
        final Context context = getContext();
        secondaryActions.add(new PlayDrawerSecondaryAction(context.getString(R.string.redeem_gift_card_menu), new Runnable() {
            public void run() {
                FinskyDrawerLayout.this.logMenuClickEvent(109);
                String accountName = FinskyApp.get().getCurrentAccountName();
                if (accountName == null) {
                    FinskyLog.wtf("Redeem chosen with no current account.", new Object[0]);
                } else {
                    FinskyDrawerLayout.this.mNavigationManager.goRedeem(accountName, null);
                }
            }
        }));
        secondaryActions.add(new PlayDrawerSecondaryAction(context.getString(R.string.settings), new Runnable() {
            public void run() {
                FinskyDrawerLayout.this.logMenuClickEvent(111);
                FinskyDrawerLayout.this.mNavigationManager.goToSettings();
            }
        }));
        secondaryActions.add(new PlayDrawerSecondaryAction(context.getString(R.string.help_and_feedback), new Runnable() {
            public void run() {
                FinskyDrawerLayout.this.logMenuClickEvent(112);
                HelpFeedbackUtil.launchHelpFeedback(FinskyDrawerLayout.this.mMainActivity);
            }
        }));
        final String legalNoticeUrl = (String) G.legalNoticeMenuUrl.get();
        if (!TextUtils.isEmpty(legalNoticeUrl)) {
            String legalNoticeTitle = (String) G.legalNoticeMenuTitleOverride.get();
            if (TextUtils.isEmpty(legalNoticeTitle)) {
                legalNoticeTitle = getContext().getString(R.string.legal_notice_title);
            }
            secondaryActions.add(new PlayDrawerSecondaryAction(legalNoticeTitle, new Runnable() {
                public void run() {
                    FinskyDrawerLayout.this.mNavigationManager.goToUrl(legalNoticeUrl);
                }
            }));
        }
        if (((Boolean) G.debugOptionsEnabled.get()).booleanValue()) {
            secondaryActions.add(new PlayDrawerSecondaryAction(context.getString(R.string.menu_debug_settings), new Runnable() {
                public void run() {
                    context.startActivity(new Intent(context, DebugActivity.class));
                }
            }));
        }
        if (((Boolean) G.dcbDebugOptionsEnabled.get()).booleanValue()) {
            secondaryActions.add(new PlayDrawerSecondaryAction(context.getString(R.string.launch_dcb_debugger), new Runnable() {
                public void run() {
                    context.startActivity(new Intent(context, DcbDebugActivity.class));
                }
            }));
        }
    }

    private void logMenuClickEvent(int elementType) {
        FinskyApp.get().getEventLogger().logClickEvent(elementType, null, this.mNavigationManager.getActivePage());
    }

    private boolean isTopLevelSideDrawerDestination() {
        boolean isTopLevelSideDrawerDestination = true;
        int currPageType = this.mNavigationManager.getCurrentPageType();
        if (!(currPageType == 1 || currPageType == 2 || currPageType == 10 || currPageType == 3 || currPageType == 12 || currPageType == 13)) {
            isTopLevelSideDrawerDestination = false;
        }
        PageFragment activePage = this.mNavigationManager.getActivePage();
        if (currPageType != 4 || !(activePage instanceof TabbedBrowseFragment)) {
            return isTopLevelSideDrawerDestination;
        }
        TabbedBrowseFragment browseFragment = (TabbedBrowseFragment) activePage;
        if (browseFragment.getToc().getCorpus(browseFragment.getUrl()) != null) {
            return true;
        }
        return isTopLevelSideDrawerDestination;
    }
}
