package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.ui.bc;
import com.miui.yellowpage.ui.cm;
import com.miui.yellowpage.ui.cs;

public class GeneralYellowPageActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        dB();
    }

    private void dB() {
        boolean z = true;
        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            Uri data = intent.getData();
            boolean equals = "com.miui.yellowpage.action.VIEW_RECENT".equals(action);
            boolean equals2 = "com.miui.yellowpage.action.VIEW_FAVORITE".equals(action);
            boolean z2 = "android.intent.action.VIEW".equals(action) && data != null && data.getPathSegments().contains("lookup_starred_yellowpage");
            if (equals) {
                showFragment("RecentYellowPageFragment", getString(R.string.account_yellowpage_recent), null, false);
            } else if (equals2 || z2) {
                showFragment("FavoriteYellowPageFragment", getString(R.string.account_yellowpage_favorite), null, false);
            }
            if (!z) {
                this.mActionBar.setFragmentViewPagerMode(this, getFragmentManager());
                this.mActionBar.addFragmentTab("RecentYellowPageFragment", this.mActionBar.newTab().setText(R.string.general_recent_title), cm.class, null, false);
                this.mActionBar.addFragmentTab("FavoriteYellowPageFragment", this.mActionBar.newTab().setText(R.string.general_favorite_title), bc.class, null, false);
            }
        }
        z = false;
        if (!z) {
            this.mActionBar.setFragmentViewPagerMode(this, getFragmentManager());
            this.mActionBar.addFragmentTab("RecentYellowPageFragment", this.mActionBar.newTab().setText(R.string.general_recent_title), cm.class, null, false);
            this.mActionBar.addFragmentTab("FavoriteYellowPageFragment", this.mActionBar.newTab().setText(R.string.general_favorite_title), bc.class, null, false);
        }
    }

    protected cs newFragmentByTag(String str) {
        if ("RecentYellowPageFragment".equals(str)) {
            return new cm();
        }
        if ("FavoriteYellowPageFragment".equals(str)) {
            return new bc();
        }
        return null;
    }
}
