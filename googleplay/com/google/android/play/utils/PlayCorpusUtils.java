package com.google.android.play.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import com.google.android.wallet.instrumentmanager.R;

public class PlayCorpusUtils {
    public static ColorStateList getPrimaryTextColor(Context context, int backendId) {
        int colorResourceId;
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                colorResourceId = com.google.android.play.R.color.play_books_primary_text;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                colorResourceId = com.google.android.play.R.color.play_music_primary_text;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                colorResourceId = com.google.android.play.R.color.play_apps_primary_text;
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                colorResourceId = com.google.android.play.R.color.play_movies_primary_text;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                colorResourceId = com.google.android.play.R.color.play_newsstand_primary_text;
                break;
            default:
                colorResourceId = com.google.android.play.R.color.play_multi_primary_text;
                break;
        }
        return context.getResources().getColorStateList(colorResourceId);
    }

    public static int getPlayActionButtonBackgroundDrawable(Context context, int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.google.android.play.R.drawable.play_action_button_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.google.android.play.R.drawable.play_action_button_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.google.android.play.R.drawable.play_action_button_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.google.android.play.R.drawable.play_action_button_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.google.android.play.R.drawable.play_action_button_newsstand;
            default:
                return com.google.android.play.R.drawable.play_action_button_multi;
        }
    }

    public static int getPlayActionButtonBackgroundSecondaryDrawable(Context context, int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.google.android.play.R.drawable.play_action_button_books_secondary;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.google.android.play.R.drawable.play_action_button_music_secondary;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.google.android.play.R.drawable.play_action_button_apps_secondary;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.google.android.play.R.drawable.play_action_button_movies_secondary;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.google.android.play.R.drawable.play_action_button_newsstand_secondary;
            default:
                return com.google.android.play.R.drawable.play_action_button_multi_secondary;
        }
    }

    public static int getRecentsColor(Context context, int backendId) {
        int colorResourceId;
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                colorResourceId = com.google.android.play.R.color.play_books_recents;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                colorResourceId = com.google.android.play.R.color.play_music_recents;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                colorResourceId = com.google.android.play.R.color.play_apps_recents;
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                colorResourceId = com.google.android.play.R.color.play_movies_recents;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                colorResourceId = com.google.android.play.R.color.play_newsstand_recents;
                break;
            default:
                colorResourceId = com.google.android.play.R.color.play_multi_recents;
                break;
        }
        return context.getResources().getColor(colorResourceId);
    }
}
