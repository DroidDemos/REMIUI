package com.google.android.gms.ocr;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CreditCardOcrResult implements SafeParcelable {
    public static final Creator<CreditCardOcrResult> CREATOR;
    String aAW;
    int aAX;
    int aAY;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    CreditCardOcrResult(int versionCode, String cardNumber, int expMonth, int expYear) {
        this.mVersionCode = versionCode;
        this.aAW = cardNumber;
        this.aAX = expMonth;
        this.aAY = expYear;
    }

    public static CreditCardOcrResult fromIntent(Intent data) {
        if (data == null || !data.hasExtra("com.google.android.gms.ocr.CREDIT_CARD_OCR_RESULT")) {
            return null;
        }
        data.setExtrasClassLoader(CreditCardOcrResult.class.getClassLoader());
        return (CreditCardOcrResult) data.getParcelableExtra("com.google.android.gms.ocr.CREDIT_CARD_OCR_RESULT");
    }

    public int describeContents() {
        return 0;
    }

    public String getCardNumber() {
        return this.aAW;
    }

    public int getExpMonth() {
        return this.aAX;
    }

    public int getExpYear() {
        return this.aAY;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
