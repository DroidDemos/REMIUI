package com.google.android.finsky.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.BackgroundDataDialog.BackgroundDataSettingListener;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.billing.InAppBillingSetting;
import com.google.android.finsky.layout.actionbar.ActionBarHelper;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.receivers.NetworkStateChangedReceiver;
import com.google.android.finsky.services.DailyHygiene;
import com.google.android.finsky.utils.ApplicationDismissedDeferrer;
import com.google.android.finsky.utils.DeviceConfigurationHelper;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.GetTocHelper;
import com.google.android.finsky.utils.GetTocHelper.Listener;
import com.google.android.finsky.utils.RestrictedDeviceHelper;
import com.google.android.finsky.utils.RestrictedDeviceHelper.OnCompleteListener;
import com.google.android.finsky.utils.SelfUpdateScheduler;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.utils.VendingUtils;
import com.google.android.play.R;
import java.io.IOException;

public abstract class AuthenticatedActivity extends ActionBarActivity implements BackgroundDataSettingListener {
    private static final boolean ADD_ACCOUNT_SUPPORTS_CUSTOM_MESSAGE;
    private static boolean sCheckedVersionChanges;
    protected ActionBarHelper mActionBarHelper;
    private boolean mCallOnReadyOnResume;
    private boolean mCallToOnReadyShouldUseIntent;
    private final OnClickListener mDeclineCreateAccountListener;
    private final Handler mHandler;
    private boolean mJustReturnedFromDialog;
    private final NetworkStateChangedReceiver mNetworkChangeReceiver;
    private final OnClickListener mOnClickCreateAccountListener;
    private Runnable mOnResumeRunnable;
    boolean mStateSaved;
    private boolean mWaitingForUserInput;

    protected abstract void onReady(boolean z);

    public AuthenticatedActivity() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mNetworkChangeReceiver = new NetworkStateChangedReceiver();
        this.mJustReturnedFromDialog = false;
        this.mCallOnReadyOnResume = false;
        this.mCallToOnReadyShouldUseIntent = false;
        this.mDeclineCreateAccountListener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AuthenticatedActivity.this.finish();
            }
        };
        this.mOnClickCreateAccountListener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AuthenticatedActivity.this.addAccount();
                dialog.cancel();
            }
        };
    }

    static {
        boolean z;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        ADD_ACCOUNT_SUPPORTS_CUSTOM_MESSAGE = z;
        sCheckedVersionChanges = false;
    }

    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mWaitingForUserInput = savedInstanceState.getBoolean("waiting_for_user_input");
            String currentAccount = determineAccount();
            String previousAccount = savedInstanceState.getString("last_used_account");
            if (currentAccount == null) {
                savedInstanceState = null;
            } else if (!(previousAccount == null || currentAccount.equals(previousAccount))) {
                savedInstanceState = null;
            }
        }
        super.onCreate(savedInstanceState);
        showLoadingIndicator();
        if (sCheckedVersionChanges || !(hasDiffVersionCode() || VendingUtils.wasSystemUpgraded())) {
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Same version & system as before", new Object[0]);
            }
            if (this.mWaitingForUserInput) {
                FinskyLog.d("Waiting for user to return from auth screen.", new Object[0]);
            } else {
                startInitializationActions(true);
            }
        } else {
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Diff version or system, clear cache", new Object[0]);
            }
            DeviceConfigurationHelper.invalidateToken();
            FinskyApp.get().clearCacheAsync(new Runnable() {
                public void run() {
                    AuthenticatedActivity.this.startInitializationActions(true);
                }
            });
        }
        sCheckedVersionChanges = true;
    }

    protected void onStart() {
        super.onStart();
        NetworkStateChangedReceiver.flushCachedState();
        registerReceiver(this.mNetworkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.mStateSaved = false;
    }

    protected void onResumeFragments() {
        super.onResumeFragments();
        this.mStateSaved = false;
        if (Utils.isBackgroundDataEnabled(this)) {
            BackgroundDataDialog.dismissExisting(getSupportFragmentManager());
        } else {
            showBackgroundDataDialog();
        }
        if (this.mOnResumeRunnable != null) {
            this.mOnResumeRunnable.run();
        } else if (getJustReturnedFromDialog()) {
            setJustReturnedFromDialog(false);
            startInitializationActions(true);
        }
    }

    protected void showBackgroundDataDialog() {
        BackgroundDataDialog.show(getSupportFragmentManager(), this);
    }

    public void onBackgroundDataNotEnabled() {
        finish();
    }

    protected void onSaveInstanceState(Bundle outState) {
        this.mStateSaved = true;
        outState.putBoolean("waiting_for_user_input", this.mWaitingForUserInput);
        outState.putString("last_used_account", determineAccount());
        super.onSaveInstanceState(outState);
    }

    protected void onStop() {
        super.onStop();
        unregisterReceiver(this.mNetworkChangeReceiver);
        NetworkStateChangedReceiver.flushCachedState();
        this.mStateSaved = true;
    }

    protected void onResume() {
        super.onResume();
        if (this.mCallOnReadyOnResume) {
            this.mCallOnReadyOnResume = false;
            fireOnReadyRunnable(this.mCallToOnReadyShouldUseIntent);
        }
    }

    public boolean onSearchRequested() {
        return isTosAccepted() && super.onSearchRequested();
    }

    protected void authenticateOnNewIntent(boolean shouldHandleIntent) {
        startInitializationActions(shouldHandleIntent);
    }

    protected void handleAuthenticationError(VolleyError error) {
    }

    public boolean isStateSaved() {
        return this.mStateSaved;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case R.styleable.Toolbar_navigationIcon /*20*/:
                this.mWaitingForUserInput = false;
                if (resultCode == 0) {
                    finish();
                    return;
                } else {
                    setJustReturnedFromDialog(true);
                    return;
                }
            case R.styleable.Toolbar_navigationContentDescription /*21*/:
                this.mWaitingForUserInput = false;
                if (AccountHandler.getFirstAccount(this) == null) {
                    FinskyLog.d("No new account added: Assume the user canceled and finish.", new Object[0]);
                    finish();
                    return;
                }
                setJustReturnedFromDialog(true);
                return;
            case R.styleable.Theme_actionMenuTextAppearance /*22*/:
                if (resultCode == 0) {
                    chooseAccount();
                    return;
                }
                this.mWaitingForUserInput = false;
                setJustReturnedFromDialog(true);
                return;
            case R.styleable.Theme_actionMenuTextColor /*23*/:
                if (resultCode == -1) {
                    String accountName = intent.getStringExtra("authAccount");
                    if (accountName != null) {
                        FinskyLog.d("b/5160617: Switch account to %s on resume", FinskyLog.scrubPii(accountName));
                        switchToAccount(accountName);
                        return;
                    }
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, intent);
                return;
        }
    }

    public void switchToAccount(final String accountName) {
        runOrScheduleActiveStateRunnable(new Runnable() {
            public void run() {
                AuthenticatedActivity.this.switchAccount(accountName, null);
            }
        });
    }

    public void chooseAccount() {
        if (VERSION.SDK_INT >= 14) {
            startActivityForResult(AccountManager.newChooseAccountIntent(FinskyApp.get().getCurrentAccount(), null, AccountHandler.getAccountTypes(), true, null, "androidmarket", null, createAddAccountOptions(this)), 23);
        } else {
            showDialog(0);
        }
        FinskyApp.get().getEventLogger().logPathImpression(0, 310, null);
    }

    protected void onCleanup() {
    }

    protected Dialog onCreateDialog(int id, Bundle args) {
        switch (id) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return setupAccountDialog();
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return setupAccountCreationDialog();
            default:
                throw new IllegalStateException("Invalid dialog type id " + id);
        }
    }

    protected boolean getJustReturnedFromDialog() {
        return this.mJustReturnedFromDialog;
    }

    protected void setJustReturnedFromDialog(boolean b) {
        this.mJustReturnedFromDialog = b;
    }

    protected void onNewIntentDirect(Intent intent) {
        super.onNewIntent(intent);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        boolean shouldHandleIntent = true;
        if ("android.intent.action.MAIN".equals(intent.getAction()) && intent.hasCategory("android.intent.category.LAUNCHER")) {
            shouldHandleIntent = false;
        }
        authenticateOnNewIntent(shouldHandleIntent);
    }

    protected void reinitialize(Account account, Intent intentToReuse, boolean clearCache) {
        clearActiveStateRunnable();
        if (account == null) {
            account = FinskyApp.get().getCurrentAccount();
            if (account == null) {
                restart();
                return;
            }
        }
        FinskyApp application = FinskyApp.get();
        onCleanup();
        if (intentToReuse != null) {
            setIntent(intentToReuse);
        } else {
            Intent viewIntent = new Intent(this, MainActivity.class);
            viewIntent.setAction("android.intent.action.MAIN");
            setIntent(viewIntent);
        }
        if (clearCache) {
            application.clearCacheAsync(null);
        }
        application.setToc(null);
        application.switchCurrentAccount(account);
        startInitializationActions(true);
    }

    private static String[] convertToStringArray(Account[] accounts) {
        String[] accountNames = new String[accounts.length];
        for (int i = 0; i < accounts.length; i++) {
            accountNames[i] = accounts[i].name;
        }
        return accountNames;
    }

    private String determineAccount() {
        String accountNameFromIntent = getIntent().getStringExtra("authAccount");
        if (accountNameFromIntent != null) {
            Account accountFromIntent = AccountHandler.findAccount(accountNameFromIntent, this);
            if (accountFromIntent != null) {
                return accountFromIntent.name;
            }
            FinskyLog.wtf("This app was called with an intent that specified the account %s, which is not a valid account on this device", accountNameFromIntent);
            finish();
            return null;
        }
        String currentAccount = FinskyApp.get().getCurrentAccountName();
        if (AccountHandler.hasAccount(currentAccount, this)) {
            return currentAccount;
        }
        Account account = AccountHandler.getFirstAccount(this);
        return account != null ? account.name : null;
    }

    private boolean displayTos() {
        String account = determineAccount();
        if (!TosActivity.requiresAcceptance(account, FinskyApp.get().getToc())) {
            return false;
        }
        this.mWaitingForUserInput = true;
        Intent showTos = TosActivity.getIntent(this, account, FinskyApp.get().getToc());
        showTos.setFlags(67108864);
        startActivityForResult(showTos, 20);
        return true;
    }

    public boolean isTosAccepted() {
        String account = FinskyApp.get().getCurrentAccountName();
        if (account == null) {
            return false;
        }
        DfeToc toc = FinskyApp.get().getToc();
        if (toc == null || TosActivity.requiresAcceptance(account, toc)) {
            return false;
        }
        return true;
    }

    private void fireOnReadyRunnable(final boolean shouldHandleIntent) {
        FinskyApp.get().getInstaller().startDeferredInstalls();
        DailyHygiene.cancelHoldoffPeriod();
        FinskyApp.get().getAdIdProvider().refreshCachedData();
        hideLoadingIndicator();
        this.mHandler.post(new Runnable() {
            public void run() {
                if (AuthenticatedActivity.this.mStateSaved) {
                    FinskyLog.d("onSaveInstanceState() called, not firing onReady().", new Object[0]);
                    AuthenticatedActivity.this.mCallOnReadyOnResume = true;
                    AuthenticatedActivity.this.mCallToOnReadyShouldUseIntent = shouldHandleIntent;
                    return;
                }
                AuthenticatedActivity.this.onReady(shouldHandleIntent);
            }
        });
    }

    private static int getIndexOfAccount(String[] accountNames, String accountToFind) {
        for (int i = 0; i < accountNames.length; i++) {
            if (accountNames[i].equals(accountToFind)) {
                return i;
            }
        }
        return -1;
    }

    private boolean hasDiffVersionCode() {
        int currentVersionCode = FinskyApp.get().getVersionCode();
        if (((Integer) FinskyPreferences.versionCode.get()).intValue() == currentVersionCode) {
            return false;
        }
        FinskyPreferences.versionCode.put(Integer.valueOf(currentVersionCode));
        return true;
    }

    private boolean isAccountSwitchNeeded(String accountName) {
        return !accountName.equals(FinskyApp.get().getCurrentAccountName());
    }

    protected void showLoadingIndicator() {
        ViewGroup contentFrame = (ViewGroup) getWindow().findViewById(com.android.vending.R.id.content_frame);
        if (contentFrame != null) {
            contentFrame.findViewById(com.android.vending.R.id.placeholder_loading).setVisibility(0);
        }
    }

    protected void hideLoadingIndicator() {
        ViewGroup contentFrame = (ViewGroup) getWindow().findViewById(com.android.vending.R.id.content_frame);
        if (contentFrame != null) {
            contentFrame.findViewById(com.android.vending.R.id.placeholder_loading).setVisibility(8);
        }
    }

    private Dialog setupAccountCreationDialog() {
        Resources resources = getResources();
        Builder builder = new Builder(this);
        builder.setMessage(resources.getString(com.android.vending.R.string.account_required)).setCancelable(false).setPositiveButton(resources.getString(com.android.vending.R.string.yes), this.mOnClickCreateAccountListener).setNegativeButton(resources.getString(com.android.vending.R.string.no), this.mDeclineCreateAccountListener).setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == 84;
            }
        });
        return builder.create();
    }

    private AlertDialog setupAccountDialog() {
        final Account[] accounts = AccountHandler.getAccounts(getApplicationContext());
        String[] accountNames = convertToStringArray(accounts);
        int currentAccountIndex = getIndexOfAccount(accountNames, FinskyApp.get().getCurrentAccountName());
        Builder builder = new Builder(this);
        builder.setTitle(getString(com.android.vending.R.string.select_account));
        builder.setSingleChoiceItems(accountNames, currentAccountIndex, new OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String newAccount = accounts[item].name;
                if (AuthenticatedActivity.this.isAccountSwitchNeeded(newAccount)) {
                    AuthenticatedActivity.this.setIntent(new Intent());
                    FinskyLog.d("b/5160617: Switching account to %s on user action", FinskyLog.scrubPii(newAccount));
                    AuthenticatedActivity.this.switchAccount(newAccount, null);
                }
                AuthenticatedActivity.this.removeDialog(0);
            }
        });
        builder.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                AuthenticatedActivity.this.removeDialog(0);
            }
        });
        return builder.create();
    }

    protected void startInitializationActions(boolean shouldHandleIntent) {
        showLoadingIndicator();
        setupAccountAndContinue(shouldHandleIntent);
    }

    private void setupAccountAndContinue(boolean shouldHandleIntent) {
        String accountName = determineAccount();
        if (accountName == null) {
            if (ADD_ACCOUNT_SUPPORTS_CUSTOM_MESSAGE) {
                addAccount();
            } else {
                showDialog(2);
            }
        } else if (isAccountSwitchNeeded(accountName)) {
            switchAccount(accountName, shouldHandleIntent ? getIntent() : null);
        } else {
            startLoadingLibrariesAndContinue(shouldHandleIntent);
        }
    }

    private void startLoadingLibrariesAndContinue(boolean shouldHandleIntent) {
        FinskyApp.get().getAppStates().load(null);
        FinskyApp.get().getLibraries().load(null);
        loadTocAndContinue(shouldHandleIntent);
    }

    private void loadTocAndContinue(final boolean shouldHandleIntent) {
        final boolean[] responseReceived = new boolean[1];
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        final String accountName = dfeApi.getAccountName();
        GetTocHelper.getToc(dfeApi, true, new Listener() {
            public void onResponse(TocResponse response) {
                if (responseReceived[0]) {
                    new ApplicationDismissedDeferrer(AuthenticatedActivity.this.getApplicationContext()).runOnApplicationClose(new Runnable() {
                        public void run() {
                            FinskyLog.d("Exiting process due to toc change", new Object[0]);
                            System.exit(0);
                        }
                    }, 10000);
                    return;
                }
                responseReceived[0] = true;
                FinskyApp.get().setToc(new DfeToc(response));
                SelfUpdateScheduler selfUpdateScheduler = FinskyApp.get().getSelfUpdateScheduler();
                selfUpdateScheduler.checkForSelfUpdate(selfUpdateScheduler.getNewVersion(response), accountName);
                if (response.billingConfig != null) {
                    InAppBillingSetting.setVersionFromBillingConfig(FinskyApp.get().getCurrentAccountName(), response.billingConfig);
                }
                AuthenticatedActivity.this.restrictLimitedOrEduUserAndContinue(shouldHandleIntent);
            }

            public void onErrorResponse(VolleyError error) {
                AuthenticatedActivity.this.hideLoadingIndicator();
                AuthenticatedActivity.this.handleAuthenticationError(error);
            }
        });
    }

    private void restrictLimitedOrEduUserAndContinue(final boolean shouldHandleIntent) {
        RestrictedDeviceHelper.isLimitedOrSchoolEduUser(new OnCompleteListener() {
            public void onComplete(boolean isLimitedOrSchoolEduUser) {
                if (isLimitedOrSchoolEduUser) {
                    AccessRestrictedActivity.showLimitedUserUI(AuthenticatedActivity.this);
                    AuthenticatedActivity.this.finish();
                    return;
                }
                AuthenticatedActivity.this.loadPlusProfileAndContinue(shouldHandleIntent);
            }
        });
    }

    private void loadPlusProfileAndContinue(boolean shouldHandleIntent) {
        FinskyApp.get().getPlayDfeApi().getPlusProfile(new Response.Listener<PlusProfileResponse>() {
            public void onResponse(PlusProfileResponse response) {
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }, false);
        checkTosAcceptanceAndContinue(shouldHandleIntent);
    }

    private void checkTosAcceptanceAndContinue(boolean shouldHandleIntent) {
        if (!displayTos()) {
            waitForLibrariesAndContinue(shouldHandleIntent);
        }
    }

    private void waitForLibrariesAndContinue(final boolean shouldHandleIntent) {
        Runnable continueWhenDone = new Runnable() {
            private int mFinished;

            public void run() {
                this.mFinished++;
                if (this.mFinished == 2) {
                    AuthenticatedActivity.this.fireOnReadyRunnable(shouldHandleIntent);
                }
            }
        };
        FinskyApp.get().getAppStates().load(continueWhenDone);
        FinskyApp.get().getLibraries().load(continueWhenDone);
    }

    protected void switchAccount(String accountName, Intent intentToReuse) {
        FinskyApp.get().getEventLogger().logSettingsBackgroundEvent(406, Integer.valueOf(0), Integer.valueOf(0), null);
        Account account = AccountHandler.findAccount(accountName, this);
        if (account == null) {
            throw new IllegalArgumentException(String.format("Error, could not switch to %s because the account could not be found on the device", new Object[]{FinskyLog.scrubPii(accountName)}));
        } else {
            reinitialize(account, intentToReuse, false);
        }
    }

    protected void handleUserAuthentication(Intent intent) {
        this.mWaitingForUserInput = true;
        startActivityForResult(intent, 22);
    }

    private void addAccount() {
        AccountManager.get(this).addAccount("com.google", "androidmarket", null, createAddAccountOptions(this), null, new AccountManagerCallback<Bundle>() {
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    AuthenticatedActivity.this.startActivityForResult((Intent) ((Bundle) future.getResult()).getParcelable("intent"), 21);
                    AuthenticatedActivity.this.mWaitingForUserInput = true;
                } catch (OperationCanceledException e) {
                    FinskyLog.d("Account add canceled. Finishing.", new Object[0]);
                    AuthenticatedActivity.this.finish();
                } catch (IOException e2) {
                    FinskyLog.d("IOException while adding account: %s. Finishing.", e2);
                    AuthenticatedActivity.this.finish();
                } catch (AuthenticatorException e3) {
                    FinskyLog.d("AuthenticatorException while adding account: %s. Finishing.", e3);
                    AuthenticatedActivity.this.finish();
                }
            }
        }, null);
    }

    public static Bundle createAddAccountOptions(Context ctx) {
        Bundle addAccountOptions = new Bundle();
        addAccountOptions.putParcelable("pendingIntent", PendingIntent.getBroadcast(ctx, 0, new Intent(), 0));
        addAccountOptions.putString("introMessage", ctx.getString(com.android.vending.R.string.account_required_external));
        addAccountOptions.putBoolean("allowSkip", false);
        return addAccountOptions;
    }

    protected void runOrScheduleActiveStateRunnable(Runnable runnable) {
        if (this.mStateSaved) {
            this.mOnResumeRunnable = runnable;
        } else {
            runnable.run();
        }
    }

    private void clearActiveStateRunnable() {
        this.mOnResumeRunnable = null;
    }

    public void restartOnResume() {
        runOrScheduleActiveStateRunnable(new Runnable() {
            public void run() {
                AuthenticatedActivity.this.restart();
            }
        });
    }

    protected void restart() {
        clearActiveStateRunnable();
        if (VERSION.SDK_INT >= 11) {
            recreate();
            return;
        }
        finish();
        final Intent intent = getIntent();
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                try {
                    AuthenticatedActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    FinskyLog.wtf(e, "Intent: %s", intent.toString());
                    throw e;
                }
            }
        }, 250);
    }
}
