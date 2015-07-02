package com.miui.yellowpage.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.ui.bD;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.ui.dv;
import java.util.HashMap;
import java.util.Map;

public class YellowPagePickerActivity extends BaseActivity {
    private static Map nW;
    private View mCustomView;
    private TextView mTitle;

    static {
        nW = new HashMap();
        nW.put("TARGET_DEFAULT", "YellowPagePickerFragment");
        nW.put("TARGET_CITY", "CityPickerFragment");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ch();
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            String b = b(extras);
            CharSequence string = extras.getString("picker_title", getResources().getString(R.string.app_label));
            showFragment(b, string, extras, false);
            this.mTitle.setText(string);
        }
    }

    private String b(Bundle bundle) {
        Object string = bundle.getString("picker_target");
        if (TextUtils.isEmpty(string)) {
            string = "TARGET_DEFAULT";
        }
        return (String) nW.get(string);
    }

    @SuppressLint({"InflateParams"})
    protected void ch() {
        this.mCustomView = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.yellow_page_picker_custom_view, null);
        this.mActionBar.setCustomView(this.mCustomView);
        this.mActionBar.setDisplayOptions(16);
        this.mTitle = (TextView) this.mCustomView.findViewById(R.id.title);
        ((Button) this.mCustomView.findViewById(R.id.cancel)).setOnClickListener(new ar(this));
    }

    protected cs newFragmentByTag(String str) {
        if ("YellowPagePickerFragment".equals(str)) {
            return new dv();
        }
        if ("CityPickerFragment".equals(str)) {
            return new bD();
        }
        return null;
    }

    protected boolean requireNetworking() {
        return false;
    }
}
