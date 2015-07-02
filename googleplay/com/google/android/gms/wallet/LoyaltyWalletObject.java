package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;

public final class LoyaltyWalletObject implements SafeParcelable {
    public static final Creator<LoyaltyWalletObject> CREATOR;
    String aTM;
    String aTN;
    String aTO;
    String aTP;
    String aTQ;
    String aTR;
    String aTS;
    ArrayList<WalletObjectMessage> aTT;
    TimeInterval aTU;
    ArrayList<LatLng> aTV;
    String aTW;
    String aTX;
    ArrayList<LabelValueRow> aTY;
    boolean aTZ;
    ArrayList<UriData> aUa;
    ArrayList<TextModuleData> aUb;
    ArrayList<UriData> aUc;
    LoyaltyPoints aUd;
    String accountId;
    String accountName;
    String fl;
    private final int mVersionCode;
    int state;

    static {
        CREATOR = new j();
    }

    LoyaltyWalletObject() {
        this.mVersionCode = 4;
        this.aTT = a.ji();
        this.aTV = a.ji();
        this.aTY = a.ji();
        this.aUa = a.ji();
        this.aUb = a.ji();
        this.aUc = a.ji();
    }

    LoyaltyWalletObject(int versionCode, String id, String accountId, String issuerName, String programName, String accountName, String barcodeAlternateText, String barcodeType, String barcodeValue, String barcodeLabel, String classId, int state, ArrayList<WalletObjectMessage> messages, TimeInterval validTimeInterval, ArrayList<LatLng> locations, String infoModuleDataHexFontColor, String infoModuleDataHexBackgroundColor, ArrayList<LabelValueRow> infoModuleDataLabelValueRows, boolean infoModuleDataShowLastUpdateTime, ArrayList<UriData> imageModuleDataMainImageUris, ArrayList<TextModuleData> textModulesData, ArrayList<UriData> linksModuleDataUris, LoyaltyPoints loyaltyPoints) {
        this.mVersionCode = versionCode;
        this.fl = id;
        this.accountId = accountId;
        this.aTM = issuerName;
        this.aTN = programName;
        this.accountName = accountName;
        this.aTO = barcodeAlternateText;
        this.aTP = barcodeType;
        this.aTQ = barcodeValue;
        this.aTR = barcodeLabel;
        this.aTS = classId;
        this.state = state;
        this.aTT = messages;
        this.aTU = validTimeInterval;
        this.aTV = locations;
        this.aTW = infoModuleDataHexFontColor;
        this.aTX = infoModuleDataHexBackgroundColor;
        this.aTY = infoModuleDataLabelValueRows;
        this.aTZ = infoModuleDataShowLastUpdateTime;
        this.aUa = imageModuleDataMainImageUris;
        this.aUb = textModulesData;
        this.aUc = linksModuleDataUris;
        this.aUd = loyaltyPoints;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
