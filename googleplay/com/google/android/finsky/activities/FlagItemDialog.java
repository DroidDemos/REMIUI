package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.layout.actionbar.ActionBarHelper;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.gms.people.PeopleClient;
import com.google.android.play.image.BitmapLoader;

public class FlagItemDialog extends AuthenticatedActivity implements PageFragmentHost {
    private NavigationManager mNavigationManager;

    public FlagItemDialog() {
        this.mNavigationManager = new FakeNavigationManager(this);
    }

    public static void show(Context context, String url) {
        Intent intent = new Intent(context, FlagItemDialog.class);
        intent.putExtra("url", url);
        intent.setFlags(536936448);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.dialog_when_large);
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        String url = getIntent().getStringExtra("url");
        this.mActionBarHelper = new ActionBarHelper(this.mNavigationManager, this);
        if (getSupportFragmentManager().findFragmentById(R.id.content_frame) == null) {
            Fragment fragment = FlagItemFragment.newInstance(url);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add((int) R.id.content_frame, fragment);
            ft.commit();
        }
    }

    protected void onReady(boolean shouldHandleIntent) {
    }

    public BitmapLoader getBitmapLoader() {
        return FinskyApp.get().getBitmapLoader();
    }

    public DfeApi getDfeApi(String dfeAccount) {
        return FinskyApp.get().getDfeApi(dfeAccount);
    }

    public NavigationManager getNavigationManager() {
        return null;
    }

    public ActionBarController getActionBarController() {
        return null;
    }

    public void goBack() {
        finish();
    }

    public void showErrorDialog(String title, String message, boolean goBack) {
        ErrorDialog.show(getSupportFragmentManager(), title, message, goBack);
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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }

    public PeopleClient getPeopleClient() {
        throw new UnsupportedOperationException();
    }

    public void maybeShowSatisfactionSurvey(PageFragment fragment, boolean skipPrompt) {
    }
}
