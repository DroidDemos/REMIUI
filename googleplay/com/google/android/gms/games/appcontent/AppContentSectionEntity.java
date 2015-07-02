package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.List;

public final class AppContentSectionEntity implements SafeParcelable, AppContentSection {
    public static final AppContentSectionEntityCreator CREATOR;
    private final String Yv;
    private final String ajP;
    private final Bundle ajV;
    private final String ajW;
    private final Uri akd;
    private final ArrayList<AppContentCardEntity> ake;
    private final ArrayList<AppContentActionEntity> mActions;
    private final int mVersionCode;
    private final String vc;

    static {
        CREATOR = new AppContentSectionEntityCreator();
    }

    AppContentSectionEntity(int versionCode, ArrayList<AppContentActionEntity> actions, Uri backgroundImageUri, ArrayList<AppContentCardEntity> cards, String contentDescription, Bundle extraData, String subtitle, String title, String type) {
        this.mVersionCode = versionCode;
        this.mActions = actions;
        this.akd = backgroundImageUri;
        this.ake = cards;
        this.ajP = contentDescription;
        this.ajV = extraData;
        this.ajW = subtitle;
        this.Yv = title;
        this.vc = type;
    }

    public AppContentSectionEntity(AppContentSection cardSection) {
        int i = 0;
        this.mVersionCode = 1;
        this.akd = cardSection.mN();
        this.ajP = cardSection.mx();
        this.ajV = cardSection.mE();
        this.ajW = cardSection.mF();
        this.Yv = cardSection.getTitle();
        this.vc = cardSection.getType();
        List actions = cardSection.getActions();
        int size = actions.size();
        this.mActions = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.mActions.add((AppContentActionEntity) ((AppContentAction) actions.get(i2)).freeze());
        }
        List mO = cardSection.mO();
        int size2 = mO.size();
        this.ake = new ArrayList(size2);
        while (i < size2) {
            this.ake.add((AppContentCardEntity) ((AppContentCard) mO.get(i)).freeze());
            i++;
        }
    }

    static int a(AppContentSection appContentSection) {
        return kl.hashCode(appContentSection.getActions(), appContentSection.mN(), appContentSection.mO(), appContentSection.mx(), appContentSection.mE(), appContentSection.mF(), appContentSection.getTitle(), appContentSection.getType());
    }

    static boolean a(AppContentSection appContentSection, Object obj) {
        if (!(obj instanceof AppContentSection)) {
            return false;
        }
        if (appContentSection == obj) {
            return true;
        }
        AppContentSection appContentSection2 = (AppContentSection) obj;
        return kl.equal(appContentSection2.getActions(), appContentSection.getActions()) && kl.equal(appContentSection2.mN(), appContentSection.mN()) && kl.equal(appContentSection2.mO(), appContentSection.mO()) && kl.equal(appContentSection2.mx(), appContentSection.mx()) && kl.equal(appContentSection2.mE(), appContentSection.mE()) && kl.equal(appContentSection2.mF(), appContentSection.mF()) && kl.equal(appContentSection2.getTitle(), appContentSection.getTitle()) && kl.equal(appContentSection2.getType(), appContentSection.getType());
    }

    static String b(AppContentSection appContentSection) {
        return kl.j(appContentSection).a("Actions", appContentSection.getActions()).a("BackgroundImageUri", appContentSection.mN()).a("Cards", appContentSection.mO()).a("ContentDescription", appContentSection.mx()).a("ExtraData", appContentSection.mE()).a("Subtitle", appContentSection.mF()).a("Title", appContentSection.getTitle()).a("Type", appContentSection.getType()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mP();
    }

    public List<AppContentAction> getActions() {
        return new ArrayList(this.mActions);
    }

    public String getTitle() {
        return this.Yv;
    }

    public String getType() {
        return this.vc;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public Bundle mE() {
        return this.ajV;
    }

    public String mF() {
        return this.ajW;
    }

    public Uri mN() {
        return this.akd;
    }

    public List<AppContentCard> mO() {
        return new ArrayList(this.ake);
    }

    public AppContentSection mP() {
        return this;
    }

    public String mx() {
        return this.ajP;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        AppContentSectionEntityCreator.a(this, out, flags);
    }
}
