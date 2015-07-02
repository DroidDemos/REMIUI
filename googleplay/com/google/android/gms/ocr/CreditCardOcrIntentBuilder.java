package com.google.android.gms.ocr;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.ke;
import com.google.android.gms.internal.kn;

public final class CreditCardOcrIntentBuilder {
    private final Context mContext;
    private final Intent mIntent;

    public CreditCardOcrIntentBuilder(Context context) {
        this.mContext = context;
        this.mIntent = new Intent("com.google.android.gms.ocr.ACTION_CREDIT_CARD_OCR");
        this.mIntent.setPackage("com.google.android.gms");
    }

    public Intent build() {
        if (ke.a(this.mContext.getPackageManager(), this.mIntent)) {
            int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
            if (isGooglePlayServicesAvailable == 0) {
                return this.mIntent;
            }
            Log.w("CreditCardOcrIntentBuilder", "Google Play services is unavailable. Result=" + isGooglePlayServicesAvailable);
            return null;
        }
        Log.d("CreditCardOcrIntentBuilder", "Google Play services OCR activity is disabled or not available");
        return null;
    }

    public CreditCardOcrIntentBuilder setTheme(int theme) {
        boolean z = theme == 0 || theme == 1;
        kn.b(z, "Unexpected value for theme=%d", Integer.valueOf(theme));
        this.mIntent.putExtra("com.google.android.gms.ocr.THEME", theme);
        return this;
    }
}
