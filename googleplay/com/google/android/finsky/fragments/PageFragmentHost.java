package com.google.android.finsky.fragments;

import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.gms.people.PeopleClient;
import com.google.android.play.image.BitmapLoader;

public interface PageFragmentHost {
    ActionBarController getActionBarController();

    BitmapLoader getBitmapLoader();

    DfeApi getDfeApi(String str);

    NavigationManager getNavigationManager();

    PeopleClient getPeopleClient();

    void goBack();

    void maybeShowSatisfactionSurvey(PageFragment pageFragment, boolean z);

    void showErrorDialog(String str, String str2, boolean z);

    void updateActionBarMode(boolean z);

    void updateBreadcrumb(String str);

    void updateCurrentBackendId(int i, boolean z);
}
