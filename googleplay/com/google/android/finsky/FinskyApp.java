package com.google.android.finsky;

import android.accounts.Account;
import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.provider.SearchRecentSuggestions;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.android.vending.AssetBrowserActivity;
import com.android.vending.R;
import com.android.vending.VendingBackupAgent;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.ByteArrayPool;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.NoCache;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.DfeAnalytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.StubAnalytics;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiContext;
import com.google.android.finsky.api.DfeApiImpl;
import com.google.android.finsky.api.DfeApiProvider;
import com.google.android.finsky.api.DfeNotificationManager;
import com.google.android.finsky.api.DfeRequest;
import com.google.android.finsky.api.InstrumentedBitmapLoader;
import com.google.android.finsky.api.NetworkStateInfo;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStatesReplicator;
import com.google.android.finsky.appstate.GmsCoreHelper;
import com.google.android.finsky.appstate.GmsCoreHelper.GMSCoreNotifier;
import com.google.android.finsky.appstate.InMemoryInstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.MigrationAsyncTask;
import com.google.android.finsky.appstate.PackageManagerRepository;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.appstate.SQLiteInstallerDataStore;
import com.google.android.finsky.appstate.WriteThroughInstallerDataStore;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.creditcard.EscrowRequest;
import com.google.android.finsky.billing.iab.PendingNotificationsService;
import com.google.android.finsky.config.ContentLevel;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PreferenceFile;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.download.DownloadBroadcastReceiver;
import com.google.android.finsky.download.DownloadManagerFactory;
import com.google.android.finsky.download.DownloadNetworkQualityListener;
import com.google.android.finsky.download.DownloadQueue;
import com.google.android.finsky.download.DownloadQueueImpl;
import com.google.android.finsky.download.obb.ObbFactory;
import com.google.android.finsky.download.obb.ObbPackageTracker;
import com.google.android.finsky.experiments.Experiments;
import com.google.android.finsky.experiments.FinskyExperiments;
import com.google.android.finsky.gearhead.GearheadStateMonitor;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.installer.PackageInstallerFactory;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Accounts;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.library.LibraryReplicatorsImpl;
import com.google.android.finsky.library.SQLiteLibrary;
import com.google.android.finsky.protos.VendingProtos.PendingNotificationsProto;
import com.google.android.finsky.receivers.AccountsChangedReceiver;
import com.google.android.finsky.receivers.BootCompletedReceiver;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.InstallerImpl;
import com.google.android.finsky.receivers.NetworkStateChangedReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.RegisteredReceiver;
import com.google.android.finsky.receivers.RemoveAssetReceiver;
import com.google.android.finsky.services.ContentSyncService;
import com.google.android.finsky.services.DailyHygiene;
import com.google.android.finsky.services.RestoreService;
import com.google.android.finsky.utils.ArrayUtils;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.DenyAllNetwork;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.GELInstallerListener;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.NotificationManager;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.finsky.utils.SelfUpdateScheduler;
import com.google.android.finsky.utils.UninstallRefundTracker;
import com.google.android.finsky.utils.UninstallSubscriptionsTracker;
import com.google.android.finsky.utils.Users;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.widget.WidgetUtils;
import com.google.android.gms.ads.identifier.AdIdProvider;
import com.google.android.gms.ads.identifier.ElegantAdvertisingIdHelper;
import com.google.android.play.dfe.api.PlayDfeApi;
import com.google.android.play.dfe.api.PlayDfeApiContext;
import com.google.android.play.dfe.api.PlayDfeApiImpl;
import com.google.android.play.dfe.api.PlayDfeApiProvider;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeUrlUtils;
import com.google.android.play.image.FifeUrlUtils.NetworkInfoCache;
import com.google.android.play.image.TentativeGcRunner;
import com.google.android.play.utils.config.GservicesValue;
import com.google.android.play.utils.config.PlayG;
import com.google.android.vending.remoting.api.PendingNotificationsHandler;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.api.VendingApiFactory;
import com.google.android.vending.remoting.api.VendingRequest;
import com.google.android.vending.verifier.PackageVerificationService;
import com.google.android.volley.GoogleHttpClient;
import com.google.android.volley.GoogleHttpClientStack;
import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import java.util.Map;

public class FinskyApp extends Application {
    private static final Class<?>[] RECEIVERS;
    private static FinskyApp sInstance;
    private AdIdProvider mAdIdProvider;
    private Analytics mAnalytics;
    private VendingApiFactory mApiFactory;
    private AppStates mAppStates;
    private AppStatesReplicator mAppStatesReplicator;
    private Cache mBitmapCache;
    private BitmapLoader mBitmapLoader;
    private RequestQueue mBitmapRequestQueue;
    private Cache mCache;
    private final Map<String, ClientMutationCache> mClientMutationCaches;
    private Account mCurrentAccount;
    private DfeApi mDfeApiNonAuthenticated;
    private DfeApiProvider mDfeApiProvider;
    private final Map<String, DfeApi> mDfeApis;
    private DfeNotificationManager mDfeNotificationManager;
    private DownloadQueue mDownloadQueue;
    private boolean mEventLoggerInUnitTestMode;
    private final Map<String, FinskyEventLog> mEventLoggers;
    private Map<String, FinskyExperiments> mExperimentsByAccountName;
    private InstallPolicies mInstallPolicies;
    private Installer mInstaller;
    private InstallerDataStore mInstallerDataStore;
    private Libraries mLibraries;
    private LibraryReplicators mLibraryReplicators;
    private Notifier mNotificationHelper;
    private PackageMonitorReceiver mPackageMonitorReceiver;
    private PackageStateRepository mPackageStateRepository;
    private final PendingNotificationsHandler mPendingNotificationsHandler;
    private PlayDfeApiProvider mPlayDfeApiProvider;
    private final Map<String, PlayDfeApi> mPlayDfeApis;
    private SearchRecentSuggestions mRecentSuggestions;
    private RequestQueue mRequestQueue;
    private SelfUpdateScheduler mSelfUpdateScheduler;
    private DfeToc mToc;
    private Users mUsers;
    private int mVersionCodeOfLastRun;

    private class CrashHandler implements UncaughtExceptionHandler {
        private final File mFile;
        private final UncaughtExceptionHandler mOriginalHandler;

        public CrashHandler(File file) {
            this.mOriginalHandler = Thread.getDefaultUncaughtExceptionHandler();
            this.mFile = file;
        }

        public void uncaughtException(Thread thread, Throwable ex) {
            try {
                this.mFile.createNewFile();
            } catch (Throwable th) {
            }
            this.mOriginalHandler.uncaughtException(thread, ex);
        }
    }

    public FinskyApp() {
        this.mDfeApis = Maps.newHashMap();
        this.mPlayDfeApis = Maps.newHashMap();
        this.mClientMutationCaches = Maps.newHashMap();
        this.mEventLoggers = Maps.newHashMap();
        this.mExperimentsByAccountName = Maps.newHashMap();
        this.mEventLoggerInUnitTestMode = false;
        this.mPendingNotificationsHandler = new PendingNotificationsHandler() {
            public boolean handlePendingNotifications(Context context, String accountName, PendingNotificationsProto pendingNotifications, boolean allowCancellation) {
                return PendingNotificationsService.handlePendingNotifications(context, accountName, pendingNotifications, allowCancellation);
            }
        };
    }

    static {
        RECEIVERS = new Class[]{BootCompletedReceiver.class, RegisteredReceiver.class, AccountsChangedReceiver.class};
    }

    public void drainAllRequests(int sequenceNumber, int bitmapSequenceNumber) {
        drain(this.mRequestQueue, sequenceNumber);
        get().getBitmapLoader().removeInFlightRequests(bitmapSequenceNumber);
    }

    public static void drain(RequestQueue queue) {
        drain(queue, queue.getSequenceNumber());
    }

    public static void drain(RequestQueue queue, final int seq) {
        queue.cancelAll(new RequestFilter() {
            public boolean apply(Request<?> request) {
                if (request instanceof DfeRequest) {
                    if (((DfeRequest) request).getAvoidBulkCancel()) {
                        return false;
                    }
                } else if (request instanceof VendingRequest) {
                    if (((VendingRequest) request).getAvoidBulkCancel()) {
                        return false;
                    }
                } else if (request instanceof EscrowRequest) {
                    return false;
                }
                if (request.getSequence() < seq) {
                    return true;
                }
                return false;
            }
        });
    }

    private boolean checkForCrashOnLastRun(Context context) {
        File crashFile = new File(context.getCacheDir(), "crashed");
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(crashFile));
        return crashFile.delete();
    }

    public int getVersionCodeOfLastRun() {
        return this.mVersionCodeOfLastRun;
    }

    public void onCreate() {
        sInstance = this;
        GservicesValue.init(this, (String[]) ArrayUtils.concatenate(G.GSERVICES_KEY_PREFIXES, PlayG.GSERVICES_KEY_PREFIXES));
        PreferenceFile.init(this);
        this.mAdIdProvider = new ElegantAdvertisingIdHelper(getContentResolver(), this);
        this.mVersionCodeOfLastRun = ((Integer) FinskyPreferences.versionCode.get()).intValue();
        this.mCache = new DiskBasedCache(getCacheDir("main"), (((Integer) G.mainCacheSizeMb.get()).intValue() * 1024) * 1024);
        if (checkForCrashOnLastRun(this)) {
            FinskyLog.d("Clearing cache due to crash on previous run.", new Object[0]);
            this.mCache.clear();
        }
        whoopsWeBrokeEverything();
        FinskyLog.d("Initializing network with DFE %s", new GoogleHttpClient(this, "", true).rewriteURI(DfeApi.BASE_URI.toString()));
        this.mRequestQueue = new RequestQueue(this.mCache, createNetwork(), 2);
        this.mRequestQueue.start();
        this.mApiFactory = new VendingApiFactory(getApplicationContext(), this.mRequestQueue);
        this.mPackageMonitorReceiver = new PackageMonitorReceiver();
        this.mPackageMonitorReceiver.attach(new GMSCoreNotifier(this));
        BillingLocator.initSingleton();
        VendingBackupAgent.registerWithBackup(getApplicationContext());
        ObbFactory.initialize();
        this.mNotificationHelper = new NotificationManager(this);
        PackageInstallerFactory.init(this);
        this.mDownloadQueue = new DownloadQueueImpl(this, DownloadManagerFactory.getDownloadManager(this));
        RemoveAssetReceiver.initialize(this.mNotificationHelper);
        DownloadBroadcastReceiver.initialize(this.mDownloadQueue);
        this.mDownloadQueue.addListener(new DownloadNetworkQualityListener());
        this.mPackageStateRepository = new PackageManagerRepository(getPackageManager(), getPackageMonitorReceiver(), (DevicePolicyManager) getSystemService("device_policy"));
        PackageState vendingPackage = this.mPackageStateRepository.get(getPackageName());
        this.mSelfUpdateScheduler = new SelfUpdateScheduler(this.mDownloadQueue, vendingPackage.installedVersion);
        this.mBitmapCache = new DiskBasedCache(getCacheDir("images"), (((Integer) G.imageCacheSizeMb.get()).intValue() * 1024) * 1024);
        this.mBitmapRequestQueue = new RequestQueue(this.mBitmapCache, createNetwork());
        this.mBitmapRequestQueue.start();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        this.mBitmapLoader = new InstrumentedBitmapLoader(getExperiments(), getEventLogger(), new NetworkStateInfo(getApplicationContext()), this.mBitmapRequestQueue, metrics.widthPixels, metrics.heightPixels, new TentativeGcRunner());
        DailyHygiene.goMakeHygieneIfDirty(this, vendingPackage.installedVersion);
        this.mPackageMonitorReceiver.attach(new ObbPackageTracker());
        this.mRecentSuggestions = new SearchRecentSuggestions(this, "com.google.android.finsky.RecentSuggestionsProvider", 1);
        this.mUsers = new Users(this);
        enableReceivers();
        GearheadStateMonitor.initialize(null);
        HandlerThread handlerThread = new HandlerThread("libraries-thread", 10);
        handlerThread.start();
        Handler notificationHandler = new Handler(Looper.getMainLooper());
        Handler handler = new Handler(handlerThread.getLooper());
        Accounts accounts = new Accounts() {
            public List<Account> getAccounts() {
                return Lists.newArrayList(AccountHandler.getAccounts(FinskyApp.this));
            }

            public Account getAccount(String name) {
                return AccountHandler.findAccount(name, FinskyApp.this);
            }
        };
        setupAccountLibrariesAndReplicator(notificationHandler, handler, accounts);
        WidgetUtils.registerLibraryMutationsListener(sInstance, getLibraryReplicators());
        Handler appStatehandler = new Handler(handlerThread.getLooper());
        WriteThroughInstallerDataStore writeThroughInstallerDataStore = new WriteThroughInstallerDataStore(new InMemoryInstallerDataStore(), new SQLiteInstallerDataStore(this), appStatehandler, notificationHandler);
        this.mInstallerDataStore = writeThroughInstallerDataStore;
        this.mAppStates = new AppStates(writeThroughInstallerDataStore, this.mPackageStateRepository);
        this.mAppStatesReplicator = new AppStatesReplicator(accounts, this.mLibraries, this.mAppStates, this.mApiFactory, notificationHandler, appStatehandler, this.mAdIdProvider);
        ContentSyncService.setupListeners(this.mLibraryReplicators, this.mPackageMonitorReceiver);
        this.mInstallPolicies = new InstallPolicies(getContentResolver(), getPackageManager(), this.mAppStates, this.mLibraries);
        this.mInstaller = new InstallerImpl(this, this.mAppStates, this.mLibraries, this.mDownloadQueue, this.mNotificationHelper, this.mInstallPolicies, this.mPackageMonitorReceiver, this.mUsers, PackageInstallerFactory.getPackageInstaller());
        this.mInstaller.start(new Runnable() {
            public void run() {
                GmsCoreHelper.cleanupNlp(FinskyApp.sInstance);
                RestoreService.recoverRestore(FinskyApp.sInstance);
            }
        });
        GELInstallerListener gELInstallerListener = new GELInstallerListener(this, this.mInstaller);
        this.mDfeNotificationManager = new DfeNotificationManagerImpl(this, this.mInstaller, this.mNotificationHelper, this.mAppStates, this.mLibraryReplicators, accounts);
        PlayCardUtils.initializeCardTrackers();
        UninstallRefundTracker uninstallRefundTracker = new UninstallRefundTracker(this, this.mAppStates, this.mPackageMonitorReceiver);
        UninstallSubscriptionsTracker uninstallSubscriptionsTracker = new UninstallSubscriptionsTracker(this, this.mLibraries, this.mPackageMonitorReceiver);
        new MigrationAsyncTask(this).execute(new Void[0]);
        Utils.syncDebugActivityStatus(this);
        FifeUrlUtils.setNetworkInfoCacheInstance(new NetworkInfoCache() {
            public NetworkInfo getNetworkInfo(Context context) {
                return NetworkStateChangedReceiver.getCachedNetworkInfo(context);
            }
        });
        if (((Boolean) G.analyticsEnabled.get()).booleanValue()) {
            this.mAnalytics = new DfeAnalytics(new Handler(getMainLooper()), getDfeApi());
        } else {
            this.mAnalytics = new StubAnalytics();
        }
        PackageVerificationService.migrateAntiMalwareConsent(this);
        cleanupOldFinsky();
    }

    private void setupAccountLibrariesAndReplicator(Handler notificationHandler, Handler backgroundHandler, Accounts accounts) {
        SQLiteLibrary sqLiteLibrary = new SQLiteLibrary(this);
        this.mLibraries = new Libraries(accounts, sqLiteLibrary, new Handler(Looper.getMainLooper()), backgroundHandler);
        this.mLibraryReplicators = new LibraryReplicatorsImpl(getDfeApiProvider(), sqLiteLibrary, this.mLibraries, notificationHandler, backgroundHandler, ((Boolean) G.debugOptionsEnabled.get()).booleanValue());
        this.mLibraries.addListener(new Listener() {
            public void onAllLibrariesLoaded() {
                FinskyApp.this.mLibraryReplicators.reinitialize();
            }

            public void onLibraryContentsChanged(AccountLibrary library) {
            }
        });
        this.mLibraries.load(null);
    }

    private void whoopsWeBrokeEverything() {
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, AssetBrowserActivity.class), 1, 1);
    }

    private void cleanupOldFinsky() {
        PackageState finskyPackage = this.mPackageStateRepository.get("com.google.android.finsky");
        if (finskyPackage != null) {
            try {
                PackageManager pm = getPackageManager();
                if (pm.getApplicationEnabledSetting("com.google.android.finsky") != 2) {
                    pm.setApplicationEnabledSetting("com.google.android.finsky", 2, 1);
                }
            } catch (SecurityException e) {
                FinskyLog.w("Unable to disable old finsky package.", new Object[0]);
            }
            int minVersion = ((Integer) G.tabskyMinVersion.get()).intValue();
            if (finskyPackage.installedVersion < minVersion) {
                FinskyLog.d("Updating com.google.android.finsky from %d to %d", Integer.valueOf(finskyPackage.installedVersion), Integer.valueOf(minVersion));
                downloadSuicidalTabsky("com.google.android.finsky", minVersion);
            }
        }
    }

    private void downloadSuicidalTabsky(String packageName, int version) {
        Account account = AccountHandler.getFirstAccount(this);
        if (account == null) {
            FinskyLog.w("No current account", new Object[0]);
            return;
        }
        this.mInstaller.setMobileDataAllowed(packageName);
        this.mInstaller.setVisibility(packageName, false, false, false);
        this.mInstaller.requestInstall(packageName, version, account.name, getString(R.string.app_name), false, "suicidal_tabsky", 3);
    }

    private void enableReceivers() {
        PackageManager pm = getPackageManager();
        for (Class<?> cls : RECEIVERS) {
            try {
                ComponentName component = new ComponentName(this, cls);
                if (pm.getComponentEnabledSetting(component) != 1) {
                    pm.setComponentEnabledSetting(component, 1, 1);
                }
            } catch (SecurityException e) {
                FinskyLog.wtf("Unable to enable %s", cls.getSimpleName());
            }
        }
    }

    public Cache getBitmapCache() {
        return this.mBitmapCache;
    }

    public static File getCacheDir(String suffix) {
        File dir = new File(sInstance.getCacheDir(), suffix);
        dir.mkdirs();
        return dir;
    }

    public static Network createNetwork() {
        if (Utils.isBackgroundDataEnabled(sInstance)) {
            return new BasicNetwork(new GoogleHttpClientStack(sInstance, ((Boolean) G.enableSensitiveLogging.get()).booleanValue()), new ByteArrayPool(((Integer) G.volleyBufferPoolSizeKb.get()).intValue() * 1024));
        }
        return new DenyAllNetwork();
    }

    public static FinskyApp get() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public RequestQueue getBitmapRequestQueue() {
        return this.mBitmapRequestQueue;
    }

    public Libraries getLibraries() {
        return this.mLibraries;
    }

    public LibraryReplicators getLibraryReplicators() {
        return this.mLibraryReplicators;
    }

    public DfeNotificationManager getDfeNotificationManager() {
        return this.mDfeNotificationManager;
    }

    public InstallerDataStore getInstallerDataStore() {
        return this.mInstallerDataStore;
    }

    public AppStates getAppStates() {
        return this.mAppStates;
    }

    public AppStatesReplicator getAppStatesReplicator() {
        return this.mAppStatesReplicator;
    }

    public PackageStateRepository getPackageInfoRepository() {
        return this.mPackageStateRepository;
    }

    public Users getUsers() {
        return this.mUsers;
    }

    public ClientMutationCache getClientMutationCache(String account) {
        if (TextUtils.isEmpty(account)) {
            FinskyLog.wtf("No account specified.", new Object[0]);
        }
        ClientMutationCache clientMutations = (ClientMutationCache) this.mClientMutationCaches.get(account);
        if (clientMutations != null) {
            return clientMutations;
        }
        clientMutations = new ClientMutationCache(getApplicationContext(), account);
        this.mClientMutationCaches.put(account, clientMutations);
        return clientMutations;
    }

    public DfeApi getDfeApi() {
        return getDfeApi(null);
    }

    public PlayDfeApi getPlayDfeApi() {
        return getPlayDfeApi((Account) null);
    }

    public synchronized DfeApiProvider getDfeApiProvider() {
        if (this.mDfeApiProvider == null) {
            this.mDfeApiProvider = new DfeApiProvider() {
                public DfeApi getDfeApi(String accountName) {
                    return FinskyApp.this.getDfeApi(accountName);
                }
            };
        }
        return this.mDfeApiProvider;
    }

    public synchronized PlayDfeApiProvider getPlayDfeApiProvider() {
        if (this.mPlayDfeApiProvider == null) {
            this.mPlayDfeApiProvider = new PlayDfeApiProvider() {
                public PlayDfeApi getPlayDfeApi(Account account) {
                    return FinskyApp.this.getPlayDfeApi(account);
                }
            };
        }
        return this.mPlayDfeApiProvider;
    }

    public AdIdProvider getAdIdProvider() {
        return this.mAdIdProvider;
    }

    public DfeApi getDfeApi(String accountName) {
        DfeApi dfeApi;
        if (accountName == null) {
            accountName = getCurrentAccountName();
            if (accountName == null) {
                FinskyLog.w("No account configured on this device.", new Object[0]);
                return null;
            }
        }
        synchronized (this.mDfeApis) {
            dfeApi = (DfeApi) this.mDfeApis.get(accountName);
            Experiments experiment = getExperiments(accountName);
            if (dfeApi == null) {
                String str = accountName;
                DfeApiContext dfeApiContext = DfeApiContext.create(this, getCache(), experiment, this.mDfeNotificationManager, str, ContentLevel.importFromSettings(this).getDfeValue(), this.mAdIdProvider, getEventLogger(accountName));
                if (FinskyLog.DEBUG) {
                    FinskyLog.d("Created new context: %s", dfeApiContext);
                }
                dfeApi = new DfeApiImpl(this.mRequestQueue, dfeApiContext);
                this.mDfeApis.put(accountName, dfeApi);
            }
        }
        return dfeApi;
    }

    public synchronized DfeApi getDfeApiNonAuthenticated() {
        if (this.mDfeApiNonAuthenticated == null) {
            this.mDfeApiNonAuthenticated = new DfeApiImpl(this.mRequestQueue, DfeApiContext.createNonAuthenticated(this, new NoCache()));
        }
        return this.mDfeApiNonAuthenticated;
    }

    public PlayDfeApi getPlayDfeApi(String accountName) {
        Account account = null;
        if (accountName != null) {
            account = AccountHandler.findAccount(accountName, this);
        }
        return getPlayDfeApi(account);
    }

    public PlayDfeApi getPlayDfeApi(Account account) {
        if (account == null) {
            account = getCurrentAccount();
        }
        if (account == null) {
            FinskyLog.w("No account configured on this device.", new Object[0]);
            return null;
        }
        PlayDfeApi playDfeApi;
        synchronized (this.mPlayDfeApis) {
            playDfeApi = (PlayDfeApi) this.mPlayDfeApis.get(account.name);
            if (playDfeApi == null) {
                PlayDfeApiContext playDfeApiContext = PlayDfeApiContext.create(this, getCache(), account);
                if (FinskyLog.DEBUG) {
                    FinskyLog.d("Created new PlayDfeApiContext: %s", playDfeApiContext);
                }
                playDfeApi = new PlayDfeApiImpl(this.mRequestQueue, playDfeApiContext);
                this.mPlayDfeApis.put(account.name, playDfeApi);
            }
        }
        return playDfeApi;
    }

    public VendingApi getVendingApi() {
        Account account = this.mCurrentAccount;
        if (account == null) {
            FinskyLog.w("Fall back to primary account.", new Object[0]);
            account = AccountHandler.getFirstAccount(this);
        }
        if (account != null) {
            return getVendingApi(account.name);
        }
        FinskyLog.w("No account configured on this device.", new Object[0]);
        return null;
    }

    public FinskyExperiments getExperiments(String accountName) {
        FinskyExperiments experiments = (FinskyExperiments) this.mExperimentsByAccountName.get(accountName);
        if (experiments != null) {
            return experiments;
        }
        experiments = new FinskyExperiments(accountName);
        this.mExperimentsByAccountName.put(accountName, experiments);
        return experiments;
    }

    public FinskyExperiments getExperiments() {
        return getExperiments(getCurrentAccountName());
    }

    public VendingApi getVendingApi(String accountName) {
        return this.mApiFactory.getApi(accountName);
    }

    public Analytics getAnalytics() {
        return this.mAnalytics;
    }

    public Analytics getAnalytics(String accountName) {
        return new DfeAnalytics(new Handler(Looper.getMainLooper()), getDfeApi(accountName));
    }

    public PackageMonitorReceiver getPackageMonitorReceiver() {
        return this.mPackageMonitorReceiver;
    }

    void initEventLoggingForTest() {
        this.mEventLoggerInUnitTestMode = true;
    }

    public FinskyEventLog getEventLogger(Account account) {
        FinskyEventLog logger;
        if (this.mEventLoggerInUnitTestMode) {
            account = null;
        }
        synchronized (this.mEventLoggers) {
            String accountName = account == null ? null : account.name;
            logger = (FinskyEventLog) this.mEventLoggers.get(accountName);
            if (logger == null) {
                logger = new FinskyEventLog(this, account, getExperiments(accountName));
                this.mEventLoggers.put(accountName, logger);
            }
        }
        return logger;
    }

    public FinskyEventLog getEventLogger(String accountName) {
        if (this.mEventLoggerInUnitTestMode) {
            return getEventLogger((Account) null);
        }
        return getEventLogger(AccountHandler.findAccount(accountName, this));
    }

    public FinskyEventLog getEventLogger() {
        if (this.mEventLoggerInUnitTestMode) {
            return getEventLogger((Account) null);
        }
        return getEventLogger(getCurrentAccount());
    }

    public void clearCacheAsync(final Runnable callback) {
        Runnable innerCallback = new Runnable() {
            private int mClearedCount;

            public void run() {
                this.mClearedCount++;
                if (this.mClearedCount == 2 && callback != null) {
                    callback.run();
                }
            }
        };
        this.mRequestQueue.add(new ClearCacheRequest(this.mCache, innerCallback));
        this.mBitmapRequestQueue.add(new ClearCacheRequest(this.mBitmapCache, innerCallback));
    }

    public void clearRequestCacheAsync(Runnable callback) {
        this.mRequestQueue.add(new ClearCacheRequest(this.mCache, callback));
    }

    public BitmapLoader getBitmapLoader() {
        return this.mBitmapLoader;
    }

    public Cache getCache() {
        return this.mCache;
    }

    public DfeToc getToc() {
        return this.mToc;
    }

    public void switchCurrentAccount(Account newAccount) {
        synchronized (this.mDfeApis) {
            this.mDfeApis.clear();
        }
        boolean changed = this.mCurrentAccount == null || !this.mCurrentAccount.equals(newAccount);
        this.mCurrentAccount = newAccount;
        if (changed) {
            AccountHandler.saveAccountToPreferences(this.mCurrentAccount, FinskyPreferences.currentAccount);
            this.mAnalytics.reset();
            WidgetUtils.notifyAccountSwitch(this);
        }
    }

    public Account getCurrentAccount() {
        if (this.mCurrentAccount == null) {
            Account account = AccountHandler.getAccountFromPreferences(this, FinskyPreferences.currentAccount);
            if (account == null) {
                FinskyLog.w("No account configured on this device.", new Object[0]);
                return null;
            }
            this.mCurrentAccount = account;
        }
        return this.mCurrentAccount;
    }

    public String getCurrentAccountName() {
        Account account = getCurrentAccount();
        return account != null ? account.name : null;
    }

    public void setToc(DfeToc toc) {
        boolean isOldTocNull = this.mToc == null;
        this.mToc = toc;
        if (this.mToc != null) {
            boolean widgetUrlsChanged = updateCachedWidgetUrls(toc);
            if (isOldTocNull || widgetUrlsChanged) {
                sendBroadcast(new Intent("com.google.android.finsky.action.TOC_SET"));
            }
        }
    }

    private static boolean updateCachedWidgetUrls(DfeToc toc) {
        PrefixSharedPreference<String> pref = FinskyPreferences.widgetUrlsByBackend;
        boolean changed = updateWidgetUrl(pref.get(3), toc.getWidgetUrl(3)) || updateWidgetUrl(pref.get(0), toc.getRecsWidgetUrl());
        if (updateWidgetUrl(pref.get(6), toc.getWidgetUrl(6)) || changed) {
            changed = true;
        } else {
            changed = false;
        }
        if (updateWidgetUrl(pref.get(2), toc.getWidgetUrl(2)) || changed) {
            changed = true;
        } else {
            changed = false;
        }
        if (updateWidgetUrl(pref.get(1), toc.getWidgetUrl(1)) || changed) {
            changed = true;
        } else {
            changed = false;
        }
        if (updateWidgetUrl(pref.get(4), toc.getWidgetUrl(4)) || changed) {
            return true;
        }
        return false;
    }

    private static boolean updateWidgetUrl(SharedPreference<String> preference, String newUrl) {
        if (TextUtils.equals((CharSequence) preference.get(), newUrl)) {
            return false;
        }
        preference.put(newUrl);
        return true;
    }

    public SearchRecentSuggestions getRecentSuggestions() {
        return this.mRecentSuggestions;
    }

    public int getVersionCode() {
        return this.mPackageStateRepository.get(getPackageName()).installedVersion;
    }

    public Installer getInstaller() {
        return this.mInstaller;
    }

    public InstallPolicies getInstallPolicies() {
        return this.mInstallPolicies;
    }

    public Notifier getNotifier() {
        return this.mNotificationHelper;
    }

    public SelfUpdateScheduler getSelfUpdateScheduler() {
        return this.mSelfUpdateScheduler;
    }

    public DownloadQueue getDownloadQueue() {
        return this.mDownloadQueue;
    }

    public PendingNotificationsHandler getPendingNotificationsHandler() {
        return this.mPendingNotificationsHandler;
    }
}
