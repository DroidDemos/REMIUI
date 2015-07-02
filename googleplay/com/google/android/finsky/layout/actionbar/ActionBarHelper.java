package com.google.android.finsky.layout.actionbar;

import android.app.Activity;
import android.app.ActivityManager.TaskDescription;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.activities.TextSectionTranslatable;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.AutoUpdateSection;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.providers.RecentSuggestionsProvider;
import com.google.android.finsky.search.FinskySearch;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import com.google.android.play.utils.PlayCorpusUtils;
import com.google.android.play.utils.PlayUtils;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Stack;

public class ActionBarHelper {
    private static final boolean IS_SEARCH_ALWAYS_VISIBLE;
    private static Map<Integer, SoftReference<Drawable>> sBackgroundCache;
    private static boolean sSawFirstBackstackChange;
    private ActionBar mActionBar;
    private ActionBarController mActionBarController;
    private Stack<ActionBarState> mActionBarStateStack;
    private Activity mActivity;
    private MenuItem mAutoUpdateItem;
    private int mCurrentActionBarAlpha;
    private Animation mCurrentActionBarAlphaAnimation;
    private int mCurrentBackendId;
    private int mCurrentBackgroundAlpha;
    private Drawable mCurrentBackgroundDrawable;
    private CharSequence mCurrentTitle;
    private String mDefaultSearchQuery;
    private MenuItem mEnvironmentItem;
    private TextSectionTranslatable mExpandedModeTranslatable;
    private FinskySearch mFinskySearchView;
    private boolean mIgnoreActionBarBackground;
    private boolean mIsMenuConfigured;
    private final int mMultiStatusBarColor;
    private NavigationManager mNavigationManager;
    private int mPrevRecentsBackendForColor;
    private CharSequence mPrevRecentsTitle;
    private SoftReference<Bitmap> mRecentsIcon;
    private MenuItem mSearchItem;
    private SearchView mSearchView;
    private AlphaSpan mTitleAlphaSpan;
    private SpannableString mTitleSpannableString;
    private FinskySearchToolbar mToolbar;
    private MenuItem mTranslateItem;
    private final Drawable mTransparentBackgroundDrawable;

    private static class ActionBarState {
        public int mode;
        public CharSequence title;

        public ActionBarState(int mode, CharSequence title) {
            this.mode = mode;
            this.title = title;
        }

        public String toString() {
            return "[type: " + this.mode + ", title: " + this.title + "]";
        }
    }

    private static class AlphaSpan extends ForegroundColorSpan {
        private float mAlpha;

        public AlphaSpan(int color) {
            super(color);
            this.mAlpha = 255.0f;
        }

        public void setAlpha(float alpha) {
            this.mAlpha = alpha;
        }

        public void updateDrawState(TextPaint textPaint) {
            int fgColor = getForegroundColor();
            textPaint.setColor(Color.argb((int) this.mAlpha, Color.red(fgColor), Color.green(fgColor), Color.blue(fgColor)));
        }
    }

    static {
        boolean z;
        if (VERSION.SDK_INT < 11) {
            z = true;
        } else {
            z = false;
        }
        IS_SEARCH_ALWAYS_VISIBLE = z;
        sBackgroundCache = Maps.newHashMap();
        sSawFirstBackstackChange = false;
    }

    public ActionBarHelper(NavigationManager navigationManager, ActionBarActivity parentActivity) {
        this(navigationManager, null, parentActivity);
    }

    public ActionBarHelper(NavigationManager navigationManager, ActionBarController actionBarController, ActionBarActivity parentActivity) {
        this.mCurrentActionBarAlpha = 255;
        this.mDefaultSearchQuery = "";
        this.mPrevRecentsBackendForColor = -1;
        this.mActionBar = parentActivity.getSupportActionBar();
        this.mToolbar = (FinskySearchToolbar) parentActivity.findViewById(R.id.action_bar);
        this.mActivity = parentActivity;
        this.mNavigationManager = navigationManager;
        this.mActionBarController = actionBarController;
        this.mActionBarStateStack = new Stack();
        this.mActionBarStateStack.push(new ActionBarState(0, null));
        this.mCurrentBackendId = 0;
        this.mCurrentBackgroundAlpha = 255;
        this.mMultiStatusBarColor = CorpusResourceUtils.getStatusBarColor(parentActivity, 0);
        if (this.mToolbar != null) {
            this.mToolbar.setCurrentBackendId(this.mCurrentBackendId);
            this.mToolbar.setNavigationManager(this.mNavigationManager);
            this.mToolbar.setActionBarController(this.mActionBarController);
            this.mToolbar.setVisibility(4);
        }
        if (this.mActionBar != null) {
            this.mActionBar.setBackgroundDrawable(getBackgroundColorDrawable(CorpusResourceUtils.getPrimaryColor(this.mActivity, 0)));
        }
        this.mTransparentBackgroundDrawable = new ColorDrawable(0);
        this.mTitleAlphaSpan = new AlphaSpan(parentActivity.getResources().getColor(R.color.play_white));
        navigationManager.addOnBackStackChangedListener(new OnBackStackChangedListener() {
            public void onBackStackChanged() {
                if (ActionBarHelper.sSawFirstBackstackChange) {
                    ActionBarHelper.this.clearSearch();
                } else {
                    ActionBarHelper.sSawFirstBackstackChange = true;
                }
                ActionBarHelper.this.syncState();
            }
        });
    }

    public void updateCurrentBackendId(int backendId, boolean ignoreActionBarBackground) {
        this.mCurrentBackendId = backendId;
        this.mIgnoreActionBarBackground = ignoreActionBarBackground;
        RecentSuggestionsProvider.setCurrentBackendId(this.mCurrentBackendId);
        if (this.mToolbar != null) {
            this.mToolbar.setCurrentBackendId(backendId);
        }
        syncState();
    }

    public void updateDefaultTitle(String text) {
        ((ActionBarState) this.mActionBarStateStack.get(0)).title = text;
        syncState();
    }

    public void updateActionBarMode(boolean searchBoxOnly) {
        if (this.mToolbar != null) {
            this.mToolbar.setMode(searchBoxOnly);
            if (this.mToolbar.getVisibility() != 0) {
                this.mToolbar.setVisibility(0);
            }
        }
    }

    private boolean isInMode(Integer mode) {
        return ((ActionBarState) this.mActionBarStateStack.peek()).mode == mode.intValue();
    }

    private void removeModeFromStack(Integer mode) {
        int stackSize = this.mActionBarStateStack.size();
        for (int i = 0; i < stackSize; i++) {
            if (((ActionBarState) this.mActionBarStateStack.get(i)).mode == mode.intValue()) {
                this.mActionBarStateStack.remove(i);
                return;
            }
        }
    }

    private void syncState() {
        syncTitle();
        syncEnvironment();
        syncStatusBarColor();
        syncRecentsEntry();
        boolean isInSectionExpandedMode = isInMode(Integer.valueOf(2));
        this.mCurrentBackgroundDrawable = isInSectionExpandedMode ? getBackgroundDrawable(this.mActivity, R.drawable.action_bar_bg_neutral) : getBackgroundColorDrawable(CorpusResourceUtils.getPrimaryColor(this.mActivity, this.mCurrentBackendId));
        this.mCurrentBackgroundDrawable.setAlpha(this.mCurrentBackgroundAlpha);
        this.mTitleAlphaSpan = new AlphaSpan(this.mActivity.getResources().getColor(isInSectionExpandedMode ? R.color.play_fg_secondary : R.color.play_white));
        updateTitleAlpha(this.mIgnoreActionBarBackground ? 255.0f : (float) this.mCurrentBackgroundAlpha);
        if (this.mActionBar != null) {
            this.mActionBar.setBackgroundDrawable(this.mIgnoreActionBarBackground ? this.mTransparentBackgroundDrawable : this.mCurrentBackgroundDrawable);
        }
        syncActions(isInMode(Integer.valueOf(2)));
    }

    private void syncStatusBarColor() {
        if (VERSION.SDK_INT < 21) {
            return;
        }
        if (this.mActivity instanceof MainActivity) {
            MainActivity mainActivity = this.mActivity;
            int corpusStatusBarBgColor = CorpusResourceUtils.getPrimaryColor(this.mActivity, this.mCurrentBackendId);
            if (this.mIgnoreActionBarBackground || this.mCurrentBackgroundAlpha == 255) {
                mainActivity.setStatusBarBackgroundColor(corpusStatusBarBgColor);
                return;
            } else {
                mainActivity.setStatusBarBackgroundColor(UiUtils.interpolateColor(corpusStatusBarBgColor, CorpusResourceUtils.getPrimaryColor(this.mActivity, 0), ((float) this.mCurrentBackgroundAlpha) / 255.0f));
                return;
            }
        }
        Window window = this.mActivity.getWindow();
        int corpusStatusBarColor = CorpusResourceUtils.getStatusBarColor(this.mActivity, this.mCurrentBackendId);
        if (this.mIgnoreActionBarBackground || this.mCurrentBackgroundAlpha == 255) {
            window.setStatusBarColor(corpusStatusBarColor);
        } else {
            window.setStatusBarColor(UiUtils.interpolateColor(corpusStatusBarColor, this.mMultiStatusBarColor, ((float) this.mCurrentBackgroundAlpha) / 255.0f));
        }
    }

    private void syncRecentsEntry() {
        if (VERSION.SDK_INT >= 21) {
            boolean isSameBackend;
            CharSequence currTitle = this.mCurrentTitle;
            if (TextUtils.isEmpty(currTitle)) {
                currTitle = this.mActivity.getTitle();
            }
            if (TextUtils.isEmpty(currTitle)) {
                currTitle = this.mActivity.getResources().getString(R.string.launcher_name);
            }
            if (this.mPrevRecentsBackendForColor == this.mCurrentBackendId) {
                isSameBackend = true;
            } else {
                isSameBackend = false;
            }
            boolean isSameTitle;
            if (this.mPrevRecentsTitle == null || !this.mPrevRecentsTitle.equals(currTitle)) {
                isSameTitle = false;
            } else {
                isSameTitle = true;
            }
            if (!isSameBackend || !isSameTitle) {
                if (this.mRecentsIcon == null || this.mRecentsIcon.get() == null) {
                    this.mRecentsIcon = new SoftReference(BitmapFactory.decodeResource(this.mActivity.getResources(), R.mipmap.ic_launcher_play_store));
                }
                this.mActivity.setTaskDescription(new TaskDescription(currTitle.toString(), (Bitmap) this.mRecentsIcon.get(), PlayCorpusUtils.getRecentsColor(this.mActivity, this.mCurrentBackendId)));
                this.mPrevRecentsBackendForColor = this.mCurrentBackendId;
                this.mPrevRecentsTitle = currTitle;
            }
        }
    }

    private void syncEnvironment() {
        if (this.mEnvironmentItem != null) {
            DfeToc toc = FinskyApp.get().getToc();
            if (toc == null || !toc.hasIconOverrideUrl()) {
                this.mEnvironmentItem.setVisible(false);
                return;
            }
            BitmapContainer bc = FinskyApp.get().getBitmapLoader().get(toc.getIconOverrideUrl(), 0, 0, new BitmapLoadedHandler() {
                public void onResponse(BitmapContainer result) {
                    ActionBarHelper.this.mEnvironmentItem.setIcon(new BitmapDrawable(ActionBarHelper.this.mActivity.getResources(), result.getBitmap()));
                    ActionBarHelper.this.mEnvironmentItem.setVisible(true);
                }
            });
            if (bc.getBitmap() != null) {
                this.mEnvironmentItem.setIcon(new BitmapDrawable(this.mActivity.getResources(), bc.getBitmap()));
                this.mEnvironmentItem.setVisible(true);
            }
        }
    }

    private void syncTitle() {
        CharSequence title = ((ActionBarState) this.mActionBarStateStack.peek()).title;
        if (TextUtils.isEmpty(title)) {
            setTitle(this.mActivity.getString(R.string.launcher_name));
            DfeToc dfeToc = FinskyApp.get().getToc();
            if (this.mCurrentBackendId == 9) {
                setTitle(this.mActivity.getString(R.string.side_drawer_social_home));
                return;
            } else if (dfeToc != null && this.mCurrentBackendId != 0) {
                CorpusMetadata corpus = dfeToc.getCorpus(this.mCurrentBackendId);
                if (corpus != null) {
                    setTitle(corpus.name);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        setTitle(title);
    }

    private void setTitle(CharSequence title) {
        if (this.mActionBar != null) {
            this.mCurrentTitle = title;
            if (this.mIgnoreActionBarBackground) {
                this.mActionBar.setTitle(this.mCurrentTitle);
                return;
            }
            this.mTitleSpannableString = new SpannableString(this.mCurrentTitle);
            this.mTitleSpannableString.setSpan(this.mTitleAlphaSpan, 0, this.mTitleSpannableString.length(), 0);
            this.mActionBar.setTitle(this.mTitleSpannableString);
        }
    }

    private void updateTitleAlpha(float alpha) {
        if (!this.mIgnoreActionBarBackground) {
            this.mTitleAlphaSpan.setAlpha(alpha);
            if (this.mTitleSpannableString != null) {
                this.mTitleSpannableString.setSpan(this.mTitleAlphaSpan, 0, this.mTitleSpannableString.length(), 0);
                setTitle(this.mTitleSpannableString);
            }
        }
    }

    public int getCurrentBackendId() {
        return this.mCurrentBackendId;
    }

    private static Drawable getBackgroundDrawable(Context context, int drawableId) {
        SoftReference<Drawable> ref = (SoftReference) sBackgroundCache.get(Integer.valueOf(drawableId));
        if (ref == null || ref.get() == null) {
            Drawable mainLayer = context.getResources().getDrawable(drawableId);
            ref = new SoftReference(new LayerDrawable(new Drawable[]{mainLayer}) {
                public boolean getPadding(Rect padding) {
                    padding.top = 0;
                    padding.bottom = 0;
                    padding.left = 0;
                    padding.right = 0;
                    return false;
                }
            });
            sBackgroundCache.put(Integer.valueOf(drawableId), ref);
        }
        return (Drawable) ref.get();
    }

    private static Drawable getBackgroundColorDrawable(int color) {
        SoftReference<Drawable> ref = (SoftReference) sBackgroundCache.get(Integer.valueOf(color));
        if (ref == null || ref.get() == null) {
            ref = new SoftReference(new PaintDrawable(color));
            sBackgroundCache.put(Integer.valueOf(color), ref);
        }
        return (Drawable) ref.get();
    }

    public void configureMenu(Activity activity, Menu menu) {
        this.mSearchItem = menu.findItem(R.id.search_button);
        this.mToolbar.configureSearchMenuItem(menu);
        View searchView = MenuItemCompat.getActionView(this.mSearchItem);
        if (searchView instanceof FinskySearch) {
            this.mFinskySearchView = (FinskySearch) searchView;
        } else {
            this.mSearchView = (SearchView) searchView;
            this.mSearchView.setOnQueryTextFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean queryTextFocused) {
                    if (queryTextFocused) {
                        ActionBarHelper.this.mSearchView.setQuery(ActionBarHelper.this.mDefaultSearchQuery, false);
                    } else {
                        ActionBarHelper.this.clearSearch();
                    }
                }
            });
            this.mSearchView.setSearchableInfo(((SearchManager) activity.getSystemService("search")).getSearchableInfo(activity.getComponentName()));
        }
        this.mTranslateItem = menu.findItem(R.id.translate_button);
        this.mAutoUpdateItem = menu.findItem(R.id.auto_update_button);
        this.mEnvironmentItem = menu.findItem(R.id.env_button);
        if (this.mNavigationManager == null) {
            searchView.setVisibility(8);
            this.mTranslateItem.setVisible(false);
            this.mAutoUpdateItem.setVisible(false);
            this.mEnvironmentItem.setVisible(false);
            if (!IS_SEARCH_ALWAYS_VISIBLE) {
                this.mSearchItem.setVisible(false);
            }
        }
        this.mIsMenuConfigured = true;
    }

    private void clearSearch() {
        if ((this.mSearchView != null || this.mFinskySearchView != null) && this.mSearchView != null) {
            this.mSearchView.setQuery("", false);
            this.mSearchView.setIconified(true);
            if (MenuItemCompat.isActionViewExpanded(this.mSearchItem)) {
                MenuItemCompat.collapseActionView(this.mSearchItem);
            }
        }
    }

    public void translateButtonClicked() {
        if (this.mExpandedModeTranslatable != null) {
            this.mExpandedModeTranslatable.toggleTranslation();
            boolean isShowingTranslation = this.mExpandedModeTranslatable.isShowingTranslation();
            FinskyApp.get().getEventLogger().logClickEvent(isShowingTranslation ? 256 : 257, null, this.mNavigationManager.getActivePage());
            this.mTranslateItem.setTitle(isShowingTranslation ? R.string.revert_translation : R.string.translate);
        }
    }

    public void autoUpdateButtonClicked(FragmentActivity activity) {
        AutoUpdateSection.handleAutoUpdateButtonClick(activity, this.mNavigationManager, false);
        syncDetailsPageMenuItem();
    }

    public boolean searchButtonClicked() {
        if (this.mSearchItem == null) {
            return false;
        }
        if (MenuItemCompat.isActionViewExpanded(this.mSearchItem)) {
            MenuItemCompat.collapseActionView(this.mSearchItem);
        } else {
            MenuItemCompat.expandActionView(this.mSearchItem);
        }
        return true;
    }

    public void setDefaultSearchQuery(String query) {
        this.mDefaultSearchQuery = query;
    }

    public void setActionBarAlpha(int alpha, boolean isTransient) {
        if (!PlayUtils.isTv(this.mActivity)) {
            if (!isTransient) {
                this.mCurrentActionBarAlpha = alpha;
                if (isActionBarInOpaqueMode()) {
                    return;
                }
            }
            if (alpha != this.mCurrentBackgroundAlpha && !this.mIgnoreActionBarBackground && this.mCurrentBackgroundDrawable != null) {
                this.mCurrentBackgroundDrawable.setAlpha(alpha);
                this.mCurrentBackgroundAlpha = alpha;
                updateTitleAlpha((float) alpha);
                syncStatusBarColor();
            }
        }
    }

    public int getActionBarAlpha() {
        return this.mCurrentActionBarAlpha;
    }

    public boolean isActionBarInOpaqueMode() {
        int stackSize = this.mActionBarStateStack.size();
        for (int i = 0; i < stackSize; i++) {
            Integer mode = Integer.valueOf(((ActionBarState) this.mActionBarStateStack.get(i)).mode);
            if (mode.intValue() == 1 || mode.intValue() == 2) {
                return true;
            }
        }
        return false;
    }

    private void enterActionBarTransientOpacityMode(View view, int newMode, CharSequence newTitle) {
        boolean wasInDefaultMode = true;
        if (!isInMode(Integer.valueOf(newMode))) {
            if (this.mActionBarStateStack.size() != 1) {
                wasInDefaultMode = false;
            }
            this.mActionBarStateStack.push(new ActionBarState(newMode, newTitle));
            if (wasInDefaultMode) {
                cancelCurrentActionBarAlphaAnimation();
                final int currentActionBarAlpha = getActionBarAlpha();
                final int toAlpha = this.mToolbar.isSearchBoxPresent() ? 0 : 255;
                this.mCurrentActionBarAlphaAnimation = new AlphaAnimation((float) currentActionBarAlpha, (float) toAlpha) {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        ActionBarHelper.this.setActionBarAlpha(currentActionBarAlpha + ((int) (((float) (toAlpha - currentActionBarAlpha)) * interpolatedTime)), true);
                    }
                };
                this.mCurrentActionBarAlphaAnimation.setDuration(150);
                this.mCurrentActionBarAlphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                view.startAnimation(this.mCurrentActionBarAlphaAnimation);
            }
        }
    }

    private void exitCurrentActionBarMode(View view) {
        this.mActionBarStateStack.pop();
        if (this.mActionBarStateStack.size() <= 1) {
            cancelCurrentActionBarAlphaAnimation();
            final int originalActionBarAlpha = this.mCurrentActionBarAlpha;
            final int fromAlpha = this.mToolbar.isSearchBoxPresent() ? 0 : 255;
            this.mCurrentActionBarAlphaAnimation = new AlphaAnimation((float) fromAlpha, (float) originalActionBarAlpha) {
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    ActionBarHelper.this.setActionBarAlpha(fromAlpha - ((int) (((float) (fromAlpha - originalActionBarAlpha)) * interpolatedTime)), true);
                }
            };
            this.mCurrentActionBarAlphaAnimation.setDuration(150);
            this.mCurrentActionBarAlphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            view.startAnimation(this.mCurrentActionBarAlphaAnimation);
        }
    }

    public void cancelCurrentActionBarAlphaAnimation() {
        if (this.mCurrentActionBarAlphaAnimation != null && this.mCurrentActionBarAlphaAnimation.isInitialized() && this.mCurrentActionBarAlphaAnimation.hasStarted() && !this.mCurrentActionBarAlphaAnimation.hasEnded()) {
            this.mCurrentActionBarAlphaAnimation.cancel();
        }
    }

    public void enterActionBarSearchMode(View view) {
        enterActionBarTransientOpacityMode(view, 1, null);
    }

    public void exitActionBarSearchMode(View view) {
        if (isInMode(Integer.valueOf(1))) {
            exitCurrentActionBarMode(view);
            syncState();
            return;
        }
        removeModeFromStack(Integer.valueOf(1));
    }

    public void enterActionBarSectionExpandedMode(View view, CharSequence expandedModeTitle, TextSectionTranslatable expandedModeTranslatable) {
        enterActionBarTransientOpacityMode(view, 2, expandedModeTitle);
        clearSearch();
        this.mExpandedModeTranslatable = expandedModeTranslatable;
        if (this.mActionBar != null) {
            this.mActionBar.setHomeAsUpIndicator((int) R.drawable.ic_collapse);
        }
        syncState();
    }

    public void exitActionBarSectionExpandedMode(View view) {
        if (this.mActionBar != null) {
            this.mActionBar.setHomeAsUpIndicator(null);
        }
        this.mExpandedModeTranslatable = null;
        if (isInMode(Integer.valueOf(2))) {
            exitCurrentActionBarMode(view);
            syncState();
            return;
        }
        removeModeFromStack(Integer.valueOf(2));
    }

    private void syncActions(boolean isInExpandedMode) {
        if (this.mActionBar != null) {
            this.mActionBar.setDisplayHomeAsUpEnabled(!this.mNavigationManager.isBackStackEmpty());
        }
        if (!this.mIsMenuConfigured) {
            return;
        }
        if (isInExpandedMode) {
            if (!IS_SEARCH_ALWAYS_VISIBLE) {
                this.mSearchItem.setVisible(false);
            }
            this.mAutoUpdateItem.setVisible(false);
            this.mEnvironmentItem.setVisible(false);
            if (this.mExpandedModeTranslatable != null) {
                this.mTranslateItem.setVisible(this.mExpandedModeTranslatable.hasTranslation());
                this.mTranslateItem.setTitle(this.mExpandedModeTranslatable.isShowingTranslation() ? R.string.revert_translation : R.string.translate);
                return;
            }
            this.mTranslateItem.setVisible(false);
            return;
        }
        syncDetailsPageMenuItem();
        if (this.mSearchItem != null && !IS_SEARCH_ALWAYS_VISIBLE) {
            this.mSearchItem.setVisible(this.mNavigationManager.canSearch());
        }
    }

    private void syncDetailsPageMenuItem() {
        boolean isDetailsPage;
        if (this.mTranslateItem != null) {
            this.mTranslateItem.setVisible(false);
        }
        if (this.mNavigationManager.getCurrentPageType() == 5) {
            isDetailsPage = true;
        } else {
            isDetailsPage = false;
        }
        Document doc = this.mNavigationManager.getCurrentDocument();
        if (this.mAutoUpdateItem == null) {
            return;
        }
        if (doc != null && doc.getBackend() == 3 && AutoUpdateSection.isAutoUpdateVisible(doc.getDocId(), FinskyApp.get().getLibraries(), FinskyApp.get().getAppStates(), FinskyApp.get().getInstaller())) {
            boolean isAutoUpdateEnabled = AutoUpdateSection.isAutoUpdateEnabled(doc.getDocId());
            this.mAutoUpdateItem.setTitle(R.string.allow_auto_updating);
            this.mAutoUpdateItem.setCheckable(true);
            this.mAutoUpdateItem.setChecked(isAutoUpdateEnabled);
            this.mAutoUpdateItem.setVisible(isDetailsPage);
            return;
        }
        this.mAutoUpdateItem.setVisible(false);
    }
}
