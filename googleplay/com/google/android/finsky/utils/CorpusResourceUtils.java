package com.google.android.finsky.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class CorpusResourceUtils {
    public static int getPrimaryColor(Context context, int backendId) {
        int colorResourceId;
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                colorResourceId = com.android.vending.R.color.play_books_primary;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                colorResourceId = com.android.vending.R.color.play_music_primary;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                colorResourceId = com.android.vending.R.color.play_apps_primary;
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                colorResourceId = com.android.vending.R.color.play_movies_primary;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                colorResourceId = com.android.vending.R.color.play_newsstand_primary;
                break;
            default:
                colorResourceId = com.android.vending.R.color.play_multi_primary;
                break;
        }
        return context.getResources().getColor(colorResourceId);
    }

    public static int getSecondaryColorResId(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.color.play_books_secondary;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.color.play_music_secondary;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.color.play_apps_secondary;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.color.play_movies_secondary;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.color.play_newsstand_secondary;
            default:
                return com.android.vending.R.color.play_multi_secondary;
        }
    }

    public static ColorStateList getSecondaryTextColor(Context context, int backendId) {
        int colorResourceId;
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                colorResourceId = com.android.vending.R.color.play_books_secondary_text;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                colorResourceId = com.android.vending.R.color.play_music_secondary_text;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                colorResourceId = com.android.vending.R.color.play_apps_secondary_text;
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                colorResourceId = com.android.vending.R.color.play_movies_secondary_text;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                colorResourceId = com.android.vending.R.color.play_newsstand_secondary_text;
                break;
            default:
                colorResourceId = com.android.vending.R.color.play_multi_secondary_text;
                break;
        }
        return context.getResources().getColorStateList(colorResourceId);
    }

    public static int getPlayActionButtonBaseBackgroundDrawable(Context context, int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.play_action_button_books_base;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.play_action_button_music_base;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.play_action_button_apps_base;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.play_action_button_movies_base;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.play_action_button_newsstand_base;
            default:
                return com.android.vending.R.drawable.play_action_button_multi_base;
        }
    }

    public static int getPlayActionButtonStartBackgroundDrawable(Context context, int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.play_action_button_books_start;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.play_action_button_music_start;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.play_action_button_apps_start;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.play_action_button_movies_start;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.play_action_button_newsstand_start;
            default:
                return com.android.vending.R.drawable.play_action_button_multi_start;
        }
    }

    public static int getTitleContentDescriptionResourceId(Resources res, int docType) {
        switch (docType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.string.content_description_app_title;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.string.content_description_music_album_title;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.string.content_description_music_artist_title;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.string.content_description_music_track_title;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return com.android.vending.R.string.content_description_book_title;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.string.content_description_movie_title;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return com.android.vending.R.string.content_description_developer_title;
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                return com.android.vending.R.string.content_description_publication_title;
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                return com.android.vending.R.string.content_description_tv_show_title;
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                return com.android.vending.R.string.content_description_tv_season_title;
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                return com.android.vending.R.string.content_description_tv_episode_title;
            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                return com.android.vending.R.string.content_description_person_title;
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                return -1;
            default:
                FinskyLog.wtf("Unsupported doc type (" + docType + ")", new Object[0]);
                return -1;
        }
    }

    public static String getCorpusMyCollectionDescription(int backend) {
        if (backend == 0) {
            backend = 3;
        }
        DfeToc toc = FinskyApp.get().getToc();
        if (toc == null) {
            return null;
        }
        List<CorpusMetadata> corpusList = toc.getCorpusList();
        if (corpusList == null) {
            return null;
        }
        for (CorpusMetadata metadata : corpusList) {
            if (metadata.hasBackend && metadata.backend == backend && !TextUtils.isEmpty(metadata.libraryName)) {
                return metadata.libraryName;
            }
        }
        return null;
    }

    public static int getCorpusSpinnerDrawable(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.spinner_background_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.spinner_background_music;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.spinner_background_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.spinner_background_newsstand;
            default:
                return com.android.vending.R.drawable.spinner_background_apps;
        }
    }

    public static int getRegularDetailsIconWidth(Context context, int docType) {
        Resources res = context.getResources();
        switch (docType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return res.getDimensionPixelSize(com.android.vending.R.dimen.summary_thumbnail_app_size);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                return res.getDimensionPixelSize(com.android.vending.R.dimen.summary_thumbnail_large_size);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                return res.getDimensionPixelSize(com.android.vending.R.dimen.summary_thumbnail_small_size);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                return res.getDimensionPixelSize(com.android.vending.R.dimen.play_profile_avatar_size);
            default:
                throw new IllegalArgumentException("Unsupported document type (" + docType + ")");
        }
    }

    public static int getRegularDetailsIconHeight(Context context, int docType) {
        Resources res = context.getResources();
        switch (docType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return res.getDimensionPixelSize(com.android.vending.R.dimen.summary_thumbnail_app_size);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                return res.getDimensionPixelSize(com.android.vending.R.dimen.summary_thumbnail_large_size);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return res.getDimensionPixelSize(com.android.vending.R.dimen.summary_thumbnail_small_size) / 2;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                return res.getDimensionPixelSize(com.android.vending.R.dimen.play_profile_avatar_size);
            default:
                throw new IllegalArgumentException("Unsupported document type (" + docType + ")");
        }
    }

    public static int getOpenButtonStringId(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.string.read;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.string.listen;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.string.play;
            default:
                return com.android.vending.R.string.open;
        }
    }

    public static int getShareHeaderId(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.string.share_google_plus_music;
            default:
                return com.android.vending.R.string.share_google_plus;
        }
    }

    public static int getMenuExpanderMinimized(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_menu_expander_books_minimized_light;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_menu_expander_music_minimized_light;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_menu_expander_apps_minimized_light;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_menu_expander_movies_minimized_light;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_menu_expander_newsstand_minimized_light;
            default:
                return com.android.vending.R.drawable.ic_menu_expander_minimized_holo_light;
        }
    }

    public static int getMenuExpanderMaximized(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_menu_expander_books_maximized_light;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_menu_expander_music_maximized_light;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_menu_expander_apps_maximized_light;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_menu_expander_movies_maximized_light;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_menu_expander_newsstand_maximized_light;
            default:
                return com.android.vending.R.drawable.ic_menu_expander_maximized_holo_light;
        }
    }

    public static int getOwnedItemIndicator(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_checkshop_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_checkshop_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_checkshop_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_checkshop_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_checkshop_newsstand;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backendId + ")");
        }
    }

    public static int getNoSearchResultsMessageId(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.string.no_results_for_query_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.string.no_results_for_query_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.string.no_results_for_query_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.string.no_results_for_query_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.string.no_results_for_query_newsstand;
            default:
                return com.android.vending.R.string.no_results_for_query;
        }
    }

    public static String getItemThumbnailContentDescription(Document doc, Resources res) {
        if (TextUtils.isEmpty(doc.getTitle())) {
            return null;
        }
        switch (doc.getDocumentType()) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return res.getString(com.android.vending.R.string.content_description_thumbnail_application, new Object[]{title});
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
                return res.getString(com.android.vending.R.string.content_description_thumbnail_album, new Object[]{title});
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return res.getString(com.android.vending.R.string.content_description_thumbnail_artist, new Object[]{title});
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return res.getString(com.android.vending.R.string.content_description_thumbnail_book, new Object[]{title});
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return res.getString(com.android.vending.R.string.content_description_thumbnail_movie, new Object[]{title});
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return res.getString(com.android.vending.R.string.content_description_thumbnail_developer, new Object[]{title});
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                return res.getString(com.android.vending.R.string.content_description_thumbnail_newsstand, new Object[]{title});
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                return res.getString(com.android.vending.R.string.content_description_thumbnail_tvshow, new Object[]{title});
            default:
                return null;
        }
    }

    public static int getRecommendationWidgetStripResourceId(int backend) {
        switch (backend) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.rec_widget_strip_apps;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.rec_widget_strip_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.rec_widget_strip_music;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.rec_widget_strip_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.rec_widget_strip_newsstand;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static String getRateString(Resources res, int docType) {
        switch (docType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return res.getString(com.android.vending.R.string.rate_this_app);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return res.getString(com.android.vending.R.string.rate_this_music_album);
            case R.styleable.WalletImFormEditText_required /*4*/:
                return res.getString(com.android.vending.R.string.rate_this_music_track);
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return res.getString(com.android.vending.R.string.rate_this_book);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return res.getString(com.android.vending.R.string.rate_this_movie);
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                return res.getString(com.android.vending.R.string.rate_this_publication);
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                return res.getString(com.android.vending.R.string.rate_this_tv_show);
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                return res.getString(com.android.vending.R.string.rate_this_tv_season);
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                return res.getString(com.android.vending.R.string.rate_this_tv_episode);
            default:
                throw new IllegalArgumentException("Unsupported doc type (" + docType + ")");
        }
    }

    public static int getRatingBarFilledResourceId(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.btn_rating_star_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.btn_rating_star_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.btn_rating_star_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.btn_rating_star_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.btn_rating_star_newsstand;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static int getRatingBarFilledFocusedResourceId(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.btn_rating_star_books_focused;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.btn_rating_star_music_focused;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.btn_rating_star_apps_focused;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.btn_rating_star_movies_focused;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.btn_rating_star_newsstand_focused;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static int getReviewEditDrawable(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_edit_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_edit_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_edit_app;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_edit_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_edit_newsstand;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static int getWishlistOnDrawable(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_menu_wish_books_on;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_menu_wish_music_on;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_menu_wish_apps_on;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_menu_wish_movies_on;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_menu_wish_newsstand_on;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static int getWishlistOffDrawable(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_menu_wish_books_off;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_menu_wish_music_off;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_menu_wish_apps_off;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_menu_wish_movies_off;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_menu_wish_newsstand_off;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static int getWarningDrawable(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_warning_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_warning_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_warning_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_warning_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_warning_newsstand;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static int getWhatsNewFillColor(Context context, int backend) {
        int colorResourceId;
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                colorResourceId = com.android.vending.R.color.whatsnew_books;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                colorResourceId = com.android.vending.R.color.whatsnew_music;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                colorResourceId = com.android.vending.R.color.whatsnew_apps;
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                colorResourceId = com.android.vending.R.color.whatsnew_movies;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                colorResourceId = com.android.vending.R.color.whatsnew_newsstand;
                break;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
        return context.getResources().getColor(colorResourceId);
    }

    public static int getWhatsNewBackgroundDrawable(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.whatsnew_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.whatsnew_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.whatsnew_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.whatsnew_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.whatsnew_newsstand;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static int getStatusBarColor(Context context, int backendId) {
        int colorResourceId;
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                colorResourceId = com.android.vending.R.color.status_bar_books;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                colorResourceId = com.android.vending.R.color.status_bar_music;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                colorResourceId = com.android.vending.R.color.status_bar_apps;
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                colorResourceId = com.android.vending.R.color.status_bar_movies;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                colorResourceId = com.android.vending.R.color.status_bar_newsstand;
                break;
            default:
                colorResourceId = com.android.vending.R.color.status_bar_multi;
                break;
        }
        return context.getResources().getColor(colorResourceId);
    }

    public static int getDrawerShopDrawable(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_drawer_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_drawer_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_drawer_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_drawer_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_drawer_newsstand;
            default:
                return -1;
        }
    }

    public static int getDrawerShopSelectedDrawable(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_drawer_books_selected;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_drawer_music_selected;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_drawer_apps_selected;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_drawer_movies_selected;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_drawer_newsstand_selected;
            default:
                return -1;
        }
    }

    public static int getDrawerMyCollectionDrawable(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_drawer_mybooks;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_drawer_mymusic;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_drawer_myapps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_drawer_mymovies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_drawer_mynewsstand;
            default:
                return -1;
        }
    }

    public static int getDrawerMyCollectionSelectedDrawable(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_drawer_mybooks_selected;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_drawer_mymusic_selected;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_drawer_myapps_selected;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_drawer_mymovies_selected;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_drawer_mynewsstand_selected;
            default:
                return -1;
        }
    }

    public static int getRewardDrawable(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_reward_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_reward_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_reward_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_reward_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_reward_newsstand;
            default:
                return com.android.vending.R.drawable.ic_reward_store;
        }
    }
}
