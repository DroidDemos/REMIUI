package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.layout.actionbar.ActionBarHelper;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.gms.people.PeopleClient;
import com.google.android.play.image.BitmapLoader;

public class ReviewsActivity extends AuthenticatedActivity implements PageFragmentHost {
    private Document mDocument;
    private boolean mIsRottenTomatoesReviews;
    private NavigationManager mNavigationManager;
    private String mReviewsUrl;

    public static void show(Context context, Document document, String reviewsUrl, boolean isRottenTomatoesReviews) {
        Intent intent = new Intent(context, ReviewsActivity.class);
        intent.putExtra("finsky.ReviewsActivity.document", document);
        intent.putExtra("finsky.ReviewsActivity.reviewsUrl", reviewsUrl);
        intent.putExtra("finsky.ReviewsActivity.isRottenTomatoesReviews", isRottenTomatoesReviews);
        intent.setFlags(536870912);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.dialog_when_large);
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        Intent intent = getIntent();
        this.mDocument = (Document) intent.getParcelableExtra("finsky.ReviewsActivity.document");
        this.mReviewsUrl = intent.getStringExtra("finsky.ReviewsActivity.reviewsUrl");
        this.mIsRottenTomatoesReviews = intent.getBooleanExtra("finsky.ReviewsActivity.isRottenTomatoesReviews", false);
        this.mNavigationManager = new FakeNavigationManager(this);
        this.mActionBarHelper = new ActionBarHelper(this.mNavigationManager, this);
        this.mActionBarHelper.updateDefaultTitle(this.mDocument.getTitle());
        this.mActionBarHelper.updateCurrentBackendId(this.mDocument.getBackend(), false);
        this.mActionBarHelper.updateActionBarMode(false);
        FragmentManager manager = getSupportFragmentManager();
        if (manager.findFragmentById(R.id.content_frame) == null) {
            ReviewsFragment fragment = ReviewsFragment.newInstance(this.mDocument, this.mReviewsUrl, this.mIsRottenTomatoesReviews);
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
    }

    protected void onReady(boolean shouldHandleIntent) {
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                if (!this.mNavigationManager.goUp()) {
                    super.onBackPressed();
                }
                return true;
            default:
                return false;
        }
    }

    public NavigationManager getNavigationManager() {
        return this.mNavigationManager;
    }

    public ActionBarController getActionBarController() {
        return null;
    }

    public void updateBreadcrumb(String breadcrumb) {
        this.mActionBarHelper.updateDefaultTitle(breadcrumb);
    }

    public void updateCurrentBackendId(int backend, boolean ignoreActionBarBackground) {
        this.mActionBarHelper.updateCurrentBackendId(backend, ignoreActionBarBackground);
    }

    public void updateActionBarMode(boolean searchBoxOnly) {
        this.mActionBarHelper.updateActionBarMode(searchBoxOnly);
    }

    public void goBack() {
        finish();
    }

    public DfeApi getDfeApi(String dfeAccount) {
        return FinskyApp.get().getDfeApi(dfeAccount);
    }

    public BitmapLoader getBitmapLoader() {
        return FinskyApp.get().getBitmapLoader();
    }

    public void showErrorDialog(String title, String message, boolean goBack) {
    }

    public PeopleClient getPeopleClient() {
        throw new UnsupportedOperationException();
    }

    public void maybeShowSatisfactionSurvey(PageFragment fragment, boolean skipPrompt) {
    }
}
