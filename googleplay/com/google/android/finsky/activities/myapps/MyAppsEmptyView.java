package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.AccountSelectorView;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Toc.CorpusMetadata;

public class MyAppsEmptyView extends ScrollView {
    AccountSelectorView mAccountNameView;
    View mAppBrowsing;
    TextView mDescriptionView;
    View mGamesBrowsing;

    public MyAppsEmptyView(Context context) {
        super(context);
    }

    public MyAppsEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAccountNameView = (AccountSelectorView) findViewById(R.id.account_name);
        this.mDescriptionView = (TextView) findViewById(R.id.empty_myapps_textview);
        this.mAppBrowsing = findViewById(R.id.myapps_browse_apps);
        this.mGamesBrowsing = findViewById(R.id.myapps_browse_games);
    }

    public void configure(final DfeToc dfeToc, final NavigationManager navigationManager, AuthenticatedActivity authenticatedActivity, boolean showAccountSelector, int descriptionTextId) {
        if (showAccountSelector) {
            this.mAccountNameView.configure();
        } else {
            this.mAccountNameView.setVisibility(8);
        }
        this.mDescriptionView.setText(descriptionTextId);
        final CorpusMetadata appCorpus = dfeToc == null ? null : dfeToc.getCorpus(3);
        if (appCorpus == null) {
            this.mAppBrowsing.setVisibility(8);
        } else {
            this.mAppBrowsing.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    navigationManager.goToCorpusHome(appCorpus.landingUrl, appCorpus.name, 3, dfeToc);
                }
            });
        }
        final String gamesUrl = (String) G.gamesBrowseUrl.get();
        if (dfeToc == null || TextUtils.isEmpty(gamesUrl)) {
            this.mGamesBrowsing.setVisibility(8);
        } else {
            this.mGamesBrowsing.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    navigationManager.goToCorpusHome(gamesUrl, MyAppsEmptyView.this.getResources().getString(R.string.games_corpus_title), 3, dfeToc);
                }
            });
        }
    }
}
