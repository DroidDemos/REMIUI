package com.google.android.finsky.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.util.SparseIntArray;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.library.LibraryReplicators.Listener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.widget.recommendation.RecommendationsStore;
import com.google.android.finsky.widget.recommendation.RecommendationsViewFactory;
import com.google.android.finsky.widget.recommendation.RecommendedWidgetProvider;
import com.google.android.wallet.instrumentmanager.R;

public class WidgetUtils {
    private static int[] SUPPORTED_BACKENDS;

    static {
        SUPPORTED_BACKENDS = new int[]{0, 3, 2, 6, 1, 4};
    }

    public static int translate(String backendType) {
        if ("all".equals(backendType)) {
            return 0;
        }
        if ("apps".equals(backendType)) {
            return 3;
        }
        if ("books".equals(backendType)) {
            return 1;
        }
        if ("movies".equals(backendType)) {
            return 4;
        }
        if ("music".equals(backendType)) {
            return 2;
        }
        if ("magazines".equals(backendType)) {
            return 6;
        }
        throw new IllegalArgumentException("Invalid backend type: " + backendType);
    }

    public static String translate(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return "all";
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "books";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "music";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "apps";
            case R.styleable.WalletImFormEditText_required /*4*/:
                return "movies";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return "magazines";
            default:
                throw new IllegalArgumentException("Invalid backend ID: " + backendId);
        }
    }

    private static String getWidgetTypeForLibraryId(String libraryId) {
        if (AccountLibrary.LIBRARY_ID_APPS.equals(libraryId)) {
            return "apps";
        }
        if (AccountLibrary.LIBRARY_ID_OCEAN.equals(libraryId)) {
            return "books";
        }
        if (AccountLibrary.LIBRARY_ID_YOUTUBE.equals(libraryId)) {
            return "movies";
        }
        if (AccountLibrary.LIBRARY_ID_MUSIC.equals(libraryId)) {
            return "music";
        }
        if (AccountLibrary.LIBRARY_ID_MAGAZINE.equals(libraryId)) {
            return "magazines";
        }
        return null;
    }

    public static SparseIntArray parseSparseIntArray(String map) {
        String[] tuples = map.split(",");
        SparseIntArray result = new SparseIntArray(tuples.length);
        for (String tuple : tuples) {
            int colonIndex = tuple.indexOf(58);
            if (colonIndex < 0) {
                FinskyLog.w("Invalid tuple: map=%s, tuple=%s", map, tuple);
            } else {
                try {
                    result.put(Integer.parseInt(tuple.substring(0, colonIndex)), Integer.parseInt(tuple.substring(colonIndex + 1, tuple.length())));
                } catch (NumberFormatException e) {
                    FinskyLog.w("Malformed key or value: map=%s, tuple=%s", map, tuple);
                }
            }
        }
        return result;
    }

    public static String serializeSparseIntArray(SparseIntArray array) {
        StringBuilder buf = new StringBuilder();
        boolean isFirst = true;
        for (int i = 0; i < array.size(); i++) {
            int key = array.keyAt(i);
            if (key >= 0) {
                if (!isFirst) {
                    buf.append(',');
                }
                isFirst = false;
                buf.append(key).append(':').append(array.get(key));
            }
        }
        return buf.toString();
    }

    public static void registerLibraryMutationsListener(final Context context, LibraryReplicators replicators) {
        replicators.addListener(new Listener() {
            public void onMutationsApplied(AccountLibrary library, String libraryId) {
                String type = WidgetUtils.getWidgetTypeForLibraryId(libraryId);
                if (type != null) {
                    int[] appWidgetIds = WidgetTypeMap.get(context).getWidgets(RecommendedWidgetProvider.class, type);
                    if (appWidgetIds.length > 0) {
                        RecommendationsViewFactory.notifyDataSetChanged(context, appWidgetIds);
                    }
                }
            }
        });
    }

    public static void notifyAccountSwitch(Context context) {
        for (int backend : SUPPORTED_BACKENDS) {
            RecommendationsStore.deleteCachedRecommendations(context, backend);
        }
        int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, RecommendedWidgetProvider.class));
        if (appWidgetIds.length > 0) {
            RecommendationsViewFactory.notifyDataSetChanged(context, appWidgetIds);
        }
    }

    public static int getDips(Context context, int dimensResource) {
        Resources res = context.getResources();
        return (int) (((float) res.getDimensionPixelSize(dimensResource)) / res.getDisplayMetrics().density);
    }

    public static int getBackendIcon(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.mipmap.ic_launcher_play_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.mipmap.ic_launcher_play_music;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.mipmap.ic_launcher_play_apps;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.mipmap.ic_launcher_play_movietv;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.mipmap.ic_launcher_play_newsstand;
            default:
                return com.android.vending.R.mipmap.ic_menu_play;
        }
    }

    public static int getHotseatCheckIcon(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_hotseat_check_book;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_hotseat_check_music;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_hotseat_check_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_hotseat_check_newsstand;
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }

    public static int getAwarenessThreshold(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return ((Integer) G.corpusAwarenessThresholdBooks.get()).intValue();
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return ((Integer) G.corpusAwarenessThresholdMusic.get()).intValue();
            case R.styleable.WalletImFormEditText_required /*4*/:
                return ((Integer) G.corpusAwarenessThresholdMovies.get()).intValue();
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return ((Integer) G.corpusAwarenessThresholdMagazines.get()).intValue();
            default:
                throw new IllegalArgumentException("Unsupported backend ID (" + backend + ")");
        }
    }
}
