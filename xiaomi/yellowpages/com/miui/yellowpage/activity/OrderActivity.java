package com.miui.yellowpage.activity;

import android.os.Bundle;
import android.os.Parcelable;
import com.miui.yellowpage.ui.OrderFragment;
import com.miui.yellowpage.ui.cs;

public class OrderActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Parcelable data = getIntent().getData();
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("com.miui.yellowpage.extra.DATA", data);
        showFragment("OrderFragment", null, bundle2, false);
    }

    protected cs newFragmentByTag(String str) {
        if ("OrderFragment".equals(str)) {
            return new OrderFragment();
        }
        return null;
    }

    protected boolean requireLogin() {
        return true;
    }
}
