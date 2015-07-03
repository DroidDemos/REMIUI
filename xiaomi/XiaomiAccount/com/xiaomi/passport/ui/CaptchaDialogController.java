package com.xiaomi.passport.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import com.xiaomi.account.R;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.widget.AlertDialog;
import com.xiaomi.passport.widget.AlertDialog.Builder;

public class CaptchaDialogController {
    private static final String TAG = "CaptchaDialogController";
    private AlertDialog captchaDialog;
    private final CaptchaInterface captchaInterface;
    private final Activity mActivity;
    private String mIck;

    public interface CaptchaInterface {
        void onCaptchaFinished();

        void onCaptchaRequired();

        void tryCaptcha(String str, String str2);
    }

    public CaptchaDialogController(Activity mActivity, CaptchaInterface captchaCallback) {
        this.mActivity = mActivity;
        this.captchaInterface = captchaCallback;
    }

    public boolean shouldForceNewCaptcha() {
        return (this.captchaDialog == null || this.captchaDialog.isShowing()) ? false : true;
    }

    public String getCaptchaCode() {
        EditText et = getCaptchaEditText();
        return et != null ? et.getText().toString() : null;
    }

    public CaptchaInterface getCaptchaInterface() {
        return this.captchaInterface;
    }

    public void dismiss() {
        if (this.captchaDialog != null) {
            this.captchaDialog.dismiss();
        }
    }

    public String getIck() {
        return this.mIck;
    }

    private EditText getCaptchaEditText() {
        if (this.captchaDialog == null || !this.captchaDialog.isShowing()) {
            return null;
        }
        return (EditText) this.captchaDialog.getWindow().getDecorView().findViewById(R.id.et_captcha_code);
    }

    public void triggerNewCaptchaTask() {
        if (this.captchaDialog == null || !this.captchaDialog.isShowing()) {
            View view = LayoutInflater.from(this.mActivity).inflate(R.layout.passport_captcha, null);
            view.findViewById(R.id.et_captcha_image).setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    CaptchaDialogController.this.updateCaptchaImageAsync();
                }
            });
            this.captchaDialog = new Builder(this.mActivity).setTitle((int) R.string.passport_input_captcha_hint).setView(view).setPositiveButton(17039370, null).setNegativeButton(17039360, null).show();
            this.captchaDialog.getButton(-1).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    String captcha = CaptchaDialogController.this.getCaptchaCode();
                    if (captcha.isEmpty()) {
                        CaptchaDialogController.this.getCaptchaEditText().setError(CaptchaDialogController.this.mActivity.getString(R.string.passport_error_empty_captcha_code));
                        return;
                    }
                    CaptchaDialogController.this.getCaptchaEditText().setError(null);
                    CaptchaDialogController.this.captchaInterface.tryCaptcha(captcha, CaptchaDialogController.this.mIck);
                }
            });
        } else {
            EditText et = getCaptchaEditText();
            et.setText("");
            et.setError(this.mActivity.getText(R.string.passport_wrong_captcha));
        }
        updateCaptchaImageAsync();
    }

    private void updateCaptchaImageAsync() {
        if (this.captchaDialog == null || !this.captchaDialog.isShowing()) {
            Log.e(TAG, "updateCaptchaImageAsync() is called when dialog is not showing -- unexpected call in this case.");
        } else {
            new AsyncTask<Void, Void, Pair<Bitmap, String>>() {
                protected Pair<Bitmap, String> doInBackground(Void... arg0) {
                    return AccountHelper.getIckImage(XMPassport.URL_REG_GET_CAPTCHA_CODE);
                }

                protected void onPostExecute(Pair<Bitmap, String> result) {
                    if (CaptchaDialogController.this.captchaDialog != null && CaptchaDialogController.this.captchaDialog.isShowing()) {
                        ((ImageView) CaptchaDialogController.this.captchaDialog.getWindow().getDecorView().findViewById(R.id.et_captcha_image)).setImageBitmap((Bitmap) result.first);
                        CaptchaDialogController.this.mIck = (String) result.second;
                    }
                }
            }.execute(new Void[0]);
        }
    }
}
