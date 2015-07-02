package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class CommonWalletObject implements SafeParcelable {
    public static final Creator<CommonWalletObject> CREATOR;
    String aTM;
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
    String fl;
    private final int mVersionCode;
    String name;
    int state;

    public final class a {
        final /* synthetic */ CommonWalletObject aVK;

        private a(CommonWalletObject commonWalletObject) {
            this.aVK = commonWalletObject;
        }

        public a el(String str) {
            this.aVK.fl = str;
            return this;
        }

        public CommonWalletObject vc() {
            return this.aVK;
        }
    }

    static {
        CREATOR = new a();
    }

    CommonWalletObject() {
        this.mVersionCode = 1;
        this.aTT = com.google.android.gms.common.util.a.ji();
        this.aTV = com.google.android.gms.common.util.a.ji();
        this.aTY = com.google.android.gms.common.util.a.ji();
        this.aUa = com.google.android.gms.common.util.a.ji();
        this.aUb = com.google.android.gms.common.util.a.ji();
        this.aUc = com.google.android.gms.common.util.a.ji();
    }

    CommonWalletObject(int versionCode, String id, String classId, String name, String issuerName, String barcodeAlternateText, String barcodeType, String barcodeValue, String barcodeLabel, int state, ArrayList<WalletObjectMessage> messages, TimeInterval validTimeInterval, ArrayList<LatLng> locations, String infoModuleDataHexFontColor, String infoModuleDataHexBackgroundColor, ArrayList<LabelValueRow> infoModuleDataLabelValueRows, boolean infoModuleDataShowLastUpdateTime, ArrayList<UriData> imageModuleDataMainImageUris, ArrayList<TextModuleData> textModulesData, ArrayList<UriData> linksModuleDataUris) {
        this.mVersionCode = versionCode;
        this.fl = id;
        this.aTS = classId;
        this.name = name;
        this.aTM = issuerName;
        this.aTO = barcodeAlternateText;
        this.aTP = barcodeType;
        this.aTQ = barcodeValue;
        this.aTR = barcodeLabel;
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
    }

    public static a vb() {
        CommonWalletObject commonWalletObject = new CommonWalletObject();
        commonWalletObject.getClass();
        return new a();
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
