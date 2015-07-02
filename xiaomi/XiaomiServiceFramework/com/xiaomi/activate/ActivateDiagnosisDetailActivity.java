package com.xiaomi.activate;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import com.xiaomi.xmsf.R;
import java.io.IOException;
import java.io.InputStream;
import miui.app.Activity;

public class ActivateDiagnosisDetailActivity extends Activity {
    private static final String DEFAULT_LANGUAGE = "en_US";
    private static final String TAG = "ActivateDiagnosisDetailActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis_info_detail);
        WebView mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.loadUrl(getDiagnosisDetailUrl());
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.activate_popup_title_diagnosis_detail);
        }
    }

    private String getDiagnosisDetailUrl() {
        String language = getResources().getConfiguration().locale.toString();
        String prefix = "file:///android_asset/";
        String template = "%s/diagnosisDetail.html";
        String path = String.format(template, new Object[]{language});
        if (!isFileExistsInAssets(path)) {
            path = String.format(template, new Object[]{DEFAULT_LANGUAGE});
        }
        return prefix + path;
    }

    private boolean isFileExistsInAssets(String path) {
        InputStream is = null;
        boolean exist = false;
        try {
            is = getAssets().open(path);
            exist = true;
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException e2) {
            Log.e(TAG, "IOException", e2);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e4) {
                }
            }
        }
        return exist;
    }
}
