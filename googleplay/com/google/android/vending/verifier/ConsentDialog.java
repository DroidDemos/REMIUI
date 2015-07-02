package com.google.android.vending.verifier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.android.vending.R;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;

public class ConsentDialog extends Activity implements ClickListener {
    private static String KEY_VERIFICATION_ID;
    private ButtonBar mButtonBar;
    private int mId;
    private boolean mResponseSent;

    public ConsentDialog() {
        this.mResponseSent = false;
    }

    static {
        KEY_VERIFICATION_ID = "verification_id";
    }

    public static void show(Context context, int id) {
        Intent intent = new Intent(context, ConsentDialog.class);
        intent.setFlags(1342177280);
        intent.putExtra(KEY_VERIFICATION_ID, id);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_malware_consent_dialog);
        if (VERSION.SDK_INT >= 11) {
            clearFinishOnTouchOutside();
        }
        this.mId = getIntent().getIntExtra(KEY_VERIFICATION_ID, -1);
        if (PackageVerificationService.registerDialog(this.mId, this)) {
            this.mButtonBar = (ButtonBar) findViewById(R.id.button_bar);
            this.mButtonBar.setClickListener(this);
            this.mButtonBar.setPositiveButtonTitle((int) R.string.accept);
            this.mButtonBar.setNegativeButtonTitle((int) R.string.decline);
            return;
        }
        finish();
    }

    private void clearFinishOnTouchOutside() {
        setFinishOnTouchOutside(false);
    }

    public void onBackPressed() {
    }

    protected void onDestroy() {
        if (!this.mResponseSent && isFinishing()) {
            PackageVerificationService.reportConsentDialog(this.mId, false);
        }
        PackageVerificationService.registerDialog(this.mId, null);
        super.onDestroy();
    }

    public void onPositiveButtonClick() {
        this.mResponseSent = true;
        PackageVerificationService.reportConsentDialog(this.mId, true);
        PackageVerificationService.registerDialog(this.mId, null);
        finish();
    }

    public void onNegativeButtonClick() {
        this.mResponseSent = true;
        PackageVerificationService.reportConsentDialog(this.mId, false);
        PackageVerificationService.registerDialog(this.mId, null);
        finish();
    }
}
