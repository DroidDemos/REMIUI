package com.miui.yellowpage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.model.q;

/* compiled from: AccountFragment */
public class aT extends PreferenceFragment {
    private Activity mActivity;
    private p mLoader;
    private k tg;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.account);
        this.mActivity = getActivity();
        q.b(this.mActivity, getPreferenceScreen());
        ec();
        this.tg = new dQ();
        getLoaderManager().initLoader(0, null, this.tg);
    }

    private void ec() {
        ThreadPool.execute(new cw(this));
    }

    private BaseRequest cy() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getAccountPreferenceListUrl(), 0);
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }
}
