package com.google.android.finsky.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class IntentUtils {
    private static final ConsumptionApp BOOKS_APP;
    private static final ConsumptionApp GPLUS_APP;
    private static final ConsumptionApp MAGAZINES_APP;
    private static final ConsumptionApp MUSIC_APP;
    private static final ConsumptionApp MY_APPS;
    private static final ConsumptionApp VIDEOS_APP;

    private static abstract class ConsumptionApp {
        public abstract Intent buildViewCollectionIntent(Context context, String str);

        public abstract Intent buildViewItemIntent(Context context, Document document, String str);

        public abstract String getPackageName();

        private ConsumptionApp() {
        }

        public Intent buildManageItemIntent(Context context, Document doc, String accountName) {
            return buildViewItemIntent(context, doc, accountName);
        }

        protected Intent buildUrlIntent(Context context, String url, String accountName) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            intent.setPackage(getPackageName());
            addAccountExtra(intent, "authAccount", accountName);
            return intent;
        }

        protected static final void addAccountExtra(Intent intent, String key, String accountName) {
            if (!TextUtils.isEmpty(accountName)) {
                intent.putExtra(key, accountName);
            }
        }

        protected static final void setDefaultFlags(Intent intent) {
            intent.setFlags(268435456);
        }
    }

    public static String getPackageName(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "com.google.android.apps.books";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "com.google.android.music";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "com.android.vending";
            case R.styleable.WalletImFormEditText_required /*4*/:
                return "com.google.android.videos";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return "com.google.android.apps.magazines";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return "com.google.android.apps.plus";
            default:
                return null;
        }
    }

    public static int getBackendId(String packageName) {
        if ("com.google.android.apps.books".equals(packageName)) {
            return 1;
        }
        if ("com.google.android.videos".equals(packageName)) {
            return 4;
        }
        if ("com.google.android.music".equals(packageName)) {
            return 2;
        }
        if ("com.google.android.apps.magazines".equals(packageName)) {
            return 6;
        }
        if ("com.android.vending".equals(packageName)) {
            return 3;
        }
        if ("com.google.android.apps.plus".equals(packageName)) {
            return 9;
        }
        return -1;
    }

    static {
        BOOKS_APP = new ConsumptionApp() {
            public Intent buildViewCollectionIntent(Context context, String accountName) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.apps.books");
                intent.setAction("android.intent.action.MAIN");
                ConsumptionApp.setDefaultFlags(intent);
                ConsumptionApp.addAccountExtra(intent, "authAccount", accountName);
                return intent;
            }

            public Intent buildViewItemIntent(Context context, Document doc, String accountName) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String) G.readBookUrl.get()).buildUpon().appendQueryParameter("id", doc.getBackendDocId()).build());
                intent.setPackage("com.google.android.apps.books");
                intent.setFlags(268451840);
                ConsumptionApp.addAccountExtra(intent, "authAccount", accountName);
                for (Offer offer : doc.getAvailableOffers()) {
                    if (offer.hasOfferType) {
                        intent.putExtra("offerType", offer.offerType);
                        break;
                    }
                }
                intent.putExtra("books:addToMyEBooks", false);
                return intent;
            }

            public String getPackageName() {
                return "com.google.android.apps.books";
            }
        };
        MY_APPS = new ConsumptionApp() {
            public Intent buildViewCollectionIntent(Context context, String accountName) {
                Intent intent = new Intent("com.google.android.finsky.VIEW_MY_DOWNLOADS");
                intent.setClass(context, MainActivity.class);
                ConsumptionApp.setDefaultFlags(intent);
                ConsumptionApp.addAccountExtra(intent, "account", accountName);
                return intent;
            }

            private Intent buildItemIntent(Context context, Document doc, String action, String accountName) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.android.vending");
                intent.setAction(action);
                ConsumptionApp.setDefaultFlags(intent);
                ConsumptionApp.addAccountExtra(intent, "account", accountName);
                intent.putExtra("asset_package", doc.getAppDetails().packageName);
                return intent;
            }

            public Intent buildViewItemIntent(Context context, Document doc, String accountName) {
                String packageName = doc.getAppDetails().packageName;
                AppState appState = FinskyApp.get().getAppStates().getApp(packageName);
                String continueUrl = null;
                if (!(appState == null || appState.installerData == null)) {
                    continueUrl = appState.installerData.getContinueUrl();
                }
                Intent intent = IntentUtils.createLaunchIntent(packageName, continueUrl, context.getPackageManager());
                if (intent == null) {
                    intent = buildItemIntent(context, doc, "android.intent.action.RUN", accountName);
                }
                ConsumptionApp.setDefaultFlags(intent);
                return intent;
            }

            public Intent buildManageItemIntent(Context context, Document doc, String accountName) {
                return buildItemIntent(context, doc, "android.intent.action.VIEW", accountName);
            }

            public String getPackageName() {
                return "com.android.vending";
            }
        };
        VIDEOS_APP = new ConsumptionApp() {
            public Intent buildViewCollectionIntent(Context context, String accountName) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.videos");
                intent.setAction("android.intent.action.MAIN");
                ConsumptionApp.setDefaultFlags(intent);
                intent.addFlags(67108864);
                ConsumptionApp.addAccountExtra(intent, "authAccount", accountName);
                return intent;
            }

            public Intent buildManageItemIntent(Context context, Document doc, String accountName) {
                Intent intent = buildViewCollectionIntent(context, accountName);
                intent.putExtra("download_video_id", doc.getBackendDocId());
                return intent;
            }

            public Intent buildViewItemIntent(Context context, Document doc, String accountName) {
                Intent intent = new Intent("com.google.android.videos.intent.action.VIEW", Uri.parse(doc.getYouTubeWatchUrl()));
                intent.setPackage("com.google.android.videos");
                intent.setFlags(268451840);
                ConsumptionApp.addAccountExtra(intent, "authAccount", accountName);
                return intent;
            }

            public String getPackageName() {
                return "com.google.android.videos";
            }
        };
        MAGAZINES_APP = new ConsumptionApp() {
            private final Uri BASE_URI;
            private final Uri HOME_URI;
            private final Uri ISSUE_URI;
            private final Uri NEWS_URI;

            {
                this.BASE_URI = new Builder().scheme("http").authority("play.google.com").appendPath("magazines").appendPath("reader").build();
                this.HOME_URI = this.BASE_URI.buildUpon().appendPath("home").appendPath("carousel").build();
                this.ISSUE_URI = this.BASE_URI.buildUpon().appendPath("issue").build();
                this.NEWS_URI = this.BASE_URI.buildUpon().appendPath("news").build();
            }

            private Intent buildIntent() {
                return new Intent("android.intent.action.VIEW").setPackage("com.google.android.apps.magazines");
            }

            public Intent buildViewCollectionIntent(Context context, String accountName) {
                Intent intent = buildIntent();
                intent.setData(this.HOME_URI);
                ConsumptionApp.setDefaultFlags(intent);
                ConsumptionApp.addAccountExtra(intent, "authAccount", accountName);
                return intent;
            }

            public Intent buildViewItemIntent(Context context, Document doc, String accountName) {
                Intent intent = buildIntent();
                if (doc.getDocumentType() == 24 || doc.getDocumentType() == 25) {
                    intent.setData(this.NEWS_URI.buildUpon().appendPath(doc.getBackendDocId()).build());
                } else {
                    intent.setData(this.ISSUE_URI.buildUpon().appendPath(doc.getBackendDocId()).build());
                }
                ConsumptionApp.setDefaultFlags(intent);
                ConsumptionApp.addAccountExtra(intent, "authAccount", accountName);
                return intent;
            }

            public String getPackageName() {
                return "com.google.android.apps.magazines";
            }
        };
        MUSIC_APP = new ConsumptionApp() {
            public Intent buildViewItemIntent(Context context, Document doc, String accountName) {
                Intent intent = new Intent("com.google.android.music.PLAY");
                intent.putExtra("storeId", doc.getBackendDocId());
                ConsumptionApp.addAccountExtra(intent, "authAccount", accountName);
                ConsumptionApp.setDefaultFlags(intent);
                return intent;
            }

            public Intent buildViewCollectionIntent(Context context, String accountName) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.music");
                intent.setAction("android.intent.action.VIEW");
                ConsumptionApp.addAccountExtra(intent, "authAccount", accountName);
                ConsumptionApp.setDefaultFlags(intent);
                return intent;
            }

            public String getPackageName() {
                return "com.google.android.music";
            }
        };
        GPLUS_APP = new ConsumptionApp() {
            public Intent buildViewItemIntent(Context context, Document doc, String accountName) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setPackage("com.google.android.apps.plus");
                intent.setData(Uri.parse(String.format("http://plus.google.com/%s/posts", new Object[]{doc.getBackendDocId()})));
                return context.getPackageManager().resolveActivity(intent, 65536) != null ? intent : null;
            }

            public Intent buildViewCollectionIntent(Context context, String accountName) {
                FinskyLog.wtf("View collection intent not supported for gplus app", new Object[0]);
                return null;
            }

            public String getPackageName() {
                return "com.google.android.apps.plus";
            }
        };
    }

    private static boolean isBooksAppInstalled(PackageManager pm) {
        return isAppInstalled(pm, "com.google.android.apps.books");
    }

    private static boolean isVideosAppInstalled(PackageManager pm) {
        if (!isAppInstalled(pm, "com.google.android.videos") || FinskyApp.get().getPackageInfoRepository().get("com.google.android.videos").installedVersion < getMinimumRequiredVideosAppVersion()) {
            return false;
        }
        return true;
    }

    private static boolean isVideosAppTrailerPlaybackSupported(PackageManager pm) {
        if (!isAppInstalled(pm, "com.google.android.videos")) {
            return false;
        }
        if (FinskyApp.get().getPackageInfoRepository().get("com.google.android.videos").installedVersion >= ((Integer) G.videoAppTrailerPlaybackMinVersion.get()).intValue()) {
            return true;
        }
        return false;
    }

    private static boolean isMagazinesAppInstalled(PackageManager pm) {
        if (!isAppInstalled(pm, "com.google.android.apps.magazines")) {
            return false;
        }
        boolean needsToUpgradeToNewsstand;
        if (FinskyApp.get().getAppStates().getPackageStateRepository().get("com.google.android.apps.magazines").installedVersion < ((Integer) G.firstNewsstandAppVersion.get()).intValue()) {
            needsToUpgradeToNewsstand = true;
        } else {
            needsToUpgradeToNewsstand = false;
        }
        if (needsToUpgradeToNewsstand) {
            return false;
        }
        return true;
    }

    private static boolean isMusicAppInstalled(PackageManager pm) {
        List<ResolveInfo> resolveInfo = pm.queryIntentActivities(new Intent("com.google.android.music.PLAY"), 65536);
        return (resolveInfo == null || resolveInfo.isEmpty()) ? false : true;
    }

    public static boolean isMusicAppWithAllAccessFlowInstalled(PackageManager pm) {
        if (!isAppInstalled(pm, "com.google.android.music")) {
            return false;
        }
        boolean needsToUpgrade;
        if (FinskyApp.get().getAppStates().getPackageStateRepository().get("com.google.android.music").installedVersion < ((Integer) G.minimumMusicVersionCode.get()).intValue()) {
            needsToUpgrade = true;
        } else {
            needsToUpgrade = false;
        }
        if (needsToUpgrade) {
            return false;
        }
        return true;
    }

    private static boolean isGPlusAppInstalled(PackageManager pm) {
        return isAppInstalled(pm, "com.google.android.apps.plus");
    }

    private static boolean isAppInstalled(PackageManager pm, String packageName) {
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        if (intent == null) {
            return false;
        }
        List<ResolveInfo> resolveInfo = pm.queryIntentActivities(intent, 65536);
        if (resolveInfo == null || resolveInfo.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean canResolveIntent(PackageManager pm, Intent intent) {
        List<ResolveInfo> resolveInfo = pm.queryIntentActivities(intent, 0);
        if (resolveInfo == null || resolveInfo.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isConsumptionAppInstalled(PackageManager pm, int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return isBooksAppInstalled(pm);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return isMusicAppInstalled(pm);
            case R.styleable.WalletImFormEditText_required /*4*/:
                return isVideosAppInstalled(pm);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return isMagazinesAppInstalled(pm);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return isGPlusAppInstalled(pm);
            default:
                return false;
        }
    }

    public static boolean isConsumptionAppDisabled(PackageStateRepository packageStateRepository, int backendId) {
        PackageState packageState = packageStateRepository.get(getPackageName(backendId));
        return packageState != null && packageState.isDisabled;
    }

    private static ConsumptionApp getConsumptionApp(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return BOOKS_APP;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return MUSIC_APP;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return MY_APPS;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return VIDEOS_APP;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return MAGAZINES_APP;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return GPLUS_APP;
            default:
                throw new IllegalStateException("Unknown backend " + backendId);
        }
    }

    public static Intent buildConsumptionAppLaunchIntent(Context context, int backendId, String accountName) {
        return getConsumptionApp(backendId).buildViewCollectionIntent(context, accountName);
    }

    public static Intent buildConsumptionAppUrlIntent(Context context, int backendId, String url, String accountName) {
        return getConsumptionApp(backendId).buildUrlIntent(context, url, accountName);
    }

    public static Intent buildConsumptionAppViewItemIntent(Context context, Document doc, String accountName) {
        return getConsumptionApp(doc.getBackend()).buildViewItemIntent(context, doc, accountName);
    }

    public static Intent buildConsumptionAppManageItemIntent(Context context, Document doc, String accountName) {
        return getConsumptionApp(doc.getBackend()).buildManageItemIntent(context, doc, accountName);
    }

    public static Intent createViewIntentForUrl(Uri uri) {
        if (uri.getScheme() == null) {
            uri = Uri.parse("http://" + uri.toString());
        }
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setFlags(524288);
        return intent;
    }

    private static Intent makeResolvableIntent(PackageManager pm, Intent intent) {
        if (pm.resolveActivity(intent, 65536) != null) {
            return intent;
        }
        Intent fallback = new Intent(intent.getAction(), intent.getData());
        fallback.setFlags(intent.getFlags());
        return fallback;
    }

    public static Intent createYouTubeIntentForUrl(PackageManager pm, Uri uri, String accountName) {
        if (uri != null) {
            uri = Uri.parse(uri.toString().trim());
        }
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setPackage("com.google.android.youtube");
        intent.putExtra("authAccount", accountName);
        intent.putExtra("force_fullscreen", true);
        intent.putExtra("finish_on_ended", true);
        intent.setFlags(524288);
        return makeResolvableIntent(pm, intent);
    }

    public static Intent createMovieTrailerIntentForUrl(PackageManager pm, Uri uri, String accountName) {
        if (!isVideosAppTrailerPlaybackSupported(pm)) {
            return createYouTubeIntentForUrl(pm, uri, accountName);
        }
        Intent trailerIntent = new Intent("com.google.android.videos.intent.action.trailers.VIEW", uri);
        trailerIntent.putExtra("authAccount", accountName);
        trailerIntent.setPackage("com.google.android.videos");
        trailerIntent.setFlags(524288);
        return makeResolvableIntent(pm, trailerIntent);
    }

    public static Intent createSendIntentForUrl(Uri uri) {
        Intent intent = new Intent("android.intent.action.SENDTO", uri);
        intent.setFlags(524288);
        return intent;
    }

    public static Intent createAccountSpecificIntent(Context context, Class<?> cls, String accountKey, String accountName) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(accountKey, accountName);
        return intent;
    }

    public static Intent buildShareIntent(Context context, Document doc) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.setFlags(524288);
        intent.putExtra("android.intent.extra.TEXT", doc.getShareUrl());
        intent.putExtra("android.intent.extra.SUBJECT", context.getString(com.android.vending.R.string.share_subject, new Object[]{doc.getTitle()}));
        return intent;
    }

    public static Intent createForwardToMainActivityIntent(Context context, Intent originalIntent) {
        Intent intent = createAccountSpecificIntent(context, MainActivity.class, "authAccount", originalIntent.getStringExtra("authAccount"));
        intent.setData(originalIntent.getData());
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    public static Intent createIntentForReceiver(PackageManager packageManager, String packageName, Intent intent) {
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 0)) {
            if (resolveInfo.activityInfo != null && packageName.equals(resolveInfo.activityInfo.packageName)) {
                Intent result = new Intent(intent);
                result.setClassName(packageName, resolveInfo.activityInfo.name);
                return result;
            }
        }
        FinskyLog.e("Could not find receiver for %s", packageName);
        return null;
    }

    private static Intent createLaunchIntent(String packageName, String continueUrl, PackageManager pm) {
        Intent intent = null;
        if (!TextUtils.isEmpty(continueUrl)) {
            intent = makeResolvableIntent(pm, createContinueUrlIntent(packageName, continueUrl));
        }
        if (intent != null) {
            return intent;
        }
        intent = pm.getLaunchIntentForPackage(packageName);
        if (intent == null) {
            return NotificationManager.createDefaultClickIntent(FinskyApp.get(), packageName, null, null, DfeUtils.createDetailsUrlFromId(packageName));
        }
        return intent;
    }

    public static boolean canResolveUrl(PackageManager pm, String packageName, String url) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.setPackage(packageName);
        return canResolveIntent(pm, intent);
    }

    public static Intent createContinueUrlIntent(String packageName, String url) {
        Intent intent = new Intent("android.intent.action.VIEW");
        Builder uri = new Builder();
        uri.scheme("market");
        uri.authority("details");
        uri.appendQueryParameter("id", packageName);
        uri.appendQueryParameter("url", Utils.urlEncode(url));
        intent.setData(uri.build());
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage("com.android.vending");
        return intent;
    }

    public static Intent createViewDocumentIntent(Context context, Document document) {
        return createViewDocumentUrlIntent(context, document.getDetailsUrl());
    }

    public static Intent createViewDocumentUrlIntent(Context context, String documentUrl) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction("com.google.android.finsky.DETAILS");
        intent.setData(Uri.parse(documentUrl));
        return intent;
    }

    public static Intent createCorpusIntent(Context context, int backendId, DfeToc dfeToc) {
        CorpusMetadata corpusMetadata = dfeToc.getCorpus(backendId);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setData(Uri.parse(corpusMetadata.landingUrl));
        intent.setAction("com.google.android.finsky.CORPUS_HOME");
        intent.putExtra("title", corpusMetadata.name);
        intent.putExtra("backend_id", backendId);
        return intent;
    }

    public static Intent createAggregatedHomeIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public static Intent createBrowseIntent(Context context, String browseUrl, String title, int backendId, boolean clearBackStack) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setData(Uri.parse(browseUrl));
        intent.setAction("com.google.android.finsky.VIEW_BROWSE");
        intent.putExtra("title", title);
        intent.putExtra("backend_id", backendId);
        intent.putExtra("clear_back_stack", clearBackStack);
        return intent;
    }

    public static int getMinimumRequiredVideosAppVersion() {
        return (VERSION.SDK_INT < 11 ? (Integer) G.minimumVideosVersionCodePreHoneycomb.get() : (Integer) G.minimumVideosVersionCodeHoneycombPlus.get()).intValue();
    }
}
