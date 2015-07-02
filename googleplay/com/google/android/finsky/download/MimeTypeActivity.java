package com.google.android.finsky.download;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.finsky.utils.FinskyLog;

public class MimeTypeActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        FinskyLog.wtf("MimeTypeActivity finishing", new Object[0]);
        finish();
    }
}
