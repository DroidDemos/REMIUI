package com.google.android.finsky.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.activities.TabbedBrowseFragment;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.GoogleHelpLauncher;
import com.google.android.wallet.instrumentmanager.R;

public class HelpFeedbackUtil {
    private static final Uri FALLBACK_SUPPORT_URL;

    static {
        FALLBACK_SUPPORT_URL = Uri.parse((String) G.supportUrl.get());
    }

    public static void launchHelpFeedback(MainActivity activity) {
        NavigationManager navigationManager = activity.getNavigationManager();
        String helpUrl = FinskyApp.get().getToc().getHelpUrl();
        if (TextUtils.isEmpty(helpUrl)) {
            String helpContext;
            switch (navigationManager.getCurrentPageType()) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    helpContext = "mobile_home";
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    helpContext = getHelpContextForCorpus(navigationManager.getActivePage());
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    helpContext = "mobile_my_apps";
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    helpContext = getHelpContextForObject(navigationManager.getCurrentDocument());
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    helpContext = "mobile_search";
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    helpContext = "mobile_wishlist";
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    helpContext = "mobile_people";
                    break;
                default:
                    helpContext = "mobile_store_default";
                    break;
            }
            new GoogleHelpLauncher(activity).launch(new GoogleHelp(helpContext).setGoogleAccount(FinskyApp.get().getCurrentAccount()).setFallbackSupportUri(FALLBACK_SUPPORT_URL).setScreenshot(GoogleHelp.getScreenshot(activity)).buildHelpIntent(activity));
            return;
        }
        navigationManager.goToUrl(helpUrl);
    }

    private static String getHelpContextForCorpus(PageFragment fragment) {
        if (!(fragment instanceof TabbedBrowseFragment)) {
            return "mobile_store_default";
        }
        switch (((TabbedBrowseFragment) fragment).getBackendId()) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "mobile_books";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "mobile_music";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "mobile_apps";
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return "mobile_movies";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return "mobile_newsstand";
            default:
                return "mobile_store_default";
        }
    }

    private static String getHelpContextForObject(Document document) {
        if (document == null) {
            return "mobile_store_default";
        }
        switch (document.getBackend()) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "mobile_books_object";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "mobile_music_object";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "mobile_apps_object";
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return "mobile_movies_object";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return "mobile_newsstand_object";
            default:
                return "mobile_store_default";
        }
    }
}
