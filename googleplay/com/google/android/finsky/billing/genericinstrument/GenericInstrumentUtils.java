package com.google.android.finsky.billing.genericinstrument;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.widget.EditText;
import com.google.android.finsky.protos.CreateInstrument.ResponseFormat;
import com.google.android.finsky.protos.CreateInstrument.TextFormField;
import com.google.android.finsky.protos.CreateInstrument.TextFormInput;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.wallet.instrumentmanager.R;
import java.security.cert.CertificateException;

public class GenericInstrumentUtils {
    public static void updateEditText(EditText editText, TextFormField field) {
        int inputType;
        CharSequence charSequence = null;
        switch (field.contentType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                inputType = 33;
                break;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                inputType = 2;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                inputType = 3;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                inputType = 129;
                break;
            default:
                inputType = 1;
                break;
        }
        editText.setInputType(inputType);
        editText.setTypeface(Typeface.SANS_SERIF);
        editText.setHint(TextUtils.isEmpty(field.label) ? null : field.label);
        editText.setText(TextUtils.isEmpty(field.defaultValue) ? null : field.defaultValue);
        if (!TextUtils.isEmpty(field.errorMessage)) {
            charSequence = field.errorMessage;
        }
        editText.setError(charSequence);
    }

    public static TextFormInput createTextFormInput(EditText editText, TextFormField field) throws CertificateException {
        TextFormInput input = new TextFormInput();
        CharSequence text = editText.getText();
        if (!TextUtils.isEmpty(text)) {
            ResponseFormat responseFormat = field.responseFormat;
            switch (responseFormat.responseFormatType) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    input.plaintextResponse = text.toString();
                    input.hasPlaintextResponse = true;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    input.paypalAuthResponse = new PaypalPasswordEncryptor().createPaypalAuthResponse(responseFormat.encryptionKey, text.toString(), responseFormat.vendorSpecificSalt);
                    break;
                default:
                    FinskyLog.wtf("Unknown ResponseFormat type: %d", Integer.valueOf(responseFormat.responseFormatType));
                    break;
            }
        }
        return input;
    }
}
