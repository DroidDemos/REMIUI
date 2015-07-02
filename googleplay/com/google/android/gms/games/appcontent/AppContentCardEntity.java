package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.List;

public final class AppContentCardEntity implements SafeParcelable, AppContentCard {
    public static final AppContentCardEntityCreator CREATOR;
    private final String Yv;
    private final Uri aiK;
    private final ArrayList<AppContentConditionEntity> ajO;
    private final String ajP;
    private final Uri ajS;
    private final ArrayList<AppContentAnnotationEntity> ajT;
    private final int ajU;
    private final Bundle ajV;
    private final String ajW;
    private final int ajX;
    private final ArrayList<AppContentActionEntity> mActions;
    private final String mDescription;
    private final int mVersionCode;
    private final String vc;

    static {
        CREATOR = new AppContentCardEntityCreator();
    }

    AppContentCardEntity(int versionCode, ArrayList<AppContentActionEntity> actions, ArrayList<AppContentAnnotationEntity> annotations, ArrayList<AppContentConditionEntity> conditions, String contentDescription, int currentProgress, String description, Bundle extraData, Uri iconImageUri, Uri imageUri, String subtitle, String title, int totalProgress, String type) {
        this.mVersionCode = versionCode;
        this.mActions = actions;
        this.ajT = annotations;
        this.ajO = conditions;
        this.ajP = contentDescription;
        this.ajU = currentProgress;
        this.mDescription = description;
        this.ajV = extraData;
        this.aiK = iconImageUri;
        this.ajS = imageUri;
        this.ajW = subtitle;
        this.Yv = title;
        this.ajX = totalProgress;
        this.vc = type;
    }

    public AppContentCardEntity(AppContentCard card) {
        int i;
        int i2 = 0;
        this.mVersionCode = 1;
        this.ajP = card.mx();
        this.ajU = card.mD();
        this.mDescription = card.getDescription();
        this.ajV = card.mE();
        this.aiK = card.getIconImageUri();
        this.ajS = card.mA();
        this.Yv = card.getTitle();
        this.ajW = card.mF();
        this.ajX = card.mG();
        this.vc = card.getType();
        List actions = card.getActions();
        int size = actions.size();
        this.mActions = new ArrayList(size);
        for (i = 0; i < size; i++) {
            this.mActions.add((AppContentActionEntity) ((AppContentAction) actions.get(i)).freeze());
        }
        actions = card.mC();
        size = actions.size();
        this.ajT = new ArrayList(size);
        for (i = 0; i < size; i++) {
            this.ajT.add((AppContentAnnotationEntity) ((AppContentAnnotation) actions.get(i)).freeze());
        }
        List mw = card.mw();
        int size2 = mw.size();
        this.ajO = new ArrayList(size2);
        while (i2 < size2) {
            this.ajO.add((AppContentConditionEntity) ((AppContentCondition) mw.get(i2)).freeze());
            i2++;
        }
    }

    static int a(AppContentCard appContentCard) {
        return kl.hashCode(appContentCard.getActions(), appContentCard.mC(), appContentCard.mw(), appContentCard.mx(), Integer.valueOf(appContentCard.mD()), appContentCard.getDescription(), appContentCard.mE(), appContentCard.getIconImageUri(), appContentCard.mA(), appContentCard.mF(), appContentCard.getTitle(), Integer.valueOf(appContentCard.mG()), appContentCard.getType());
    }

    static boolean a(AppContentCard appContentCard, Object obj) {
        if (!(obj instanceof AppContentCard)) {
            return false;
        }
        if (appContentCard == obj) {
            return true;
        }
        AppContentCard appContentCard2 = (AppContentCard) obj;
        return kl.equal(appContentCard2.getActions(), appContentCard.getActions()) && kl.equal(appContentCard2.mC(), appContentCard.mC()) && kl.equal(appContentCard2.mw(), appContentCard.mw()) && kl.equal(appContentCard2.mx(), appContentCard.mx()) && kl.equal(Integer.valueOf(appContentCard2.mD()), Integer.valueOf(appContentCard.mD())) && kl.equal(appContentCard2.getDescription(), appContentCard.getDescription()) && kl.equal(appContentCard2.mE(), appContentCard.mE()) && kl.equal(appContentCard2.getIconImageUri(), appContentCard.getIconImageUri()) && kl.equal(appContentCard2.mA(), appContentCard.mA()) && kl.equal(appContentCard2.mF(), appContentCard.mF()) && kl.equal(appContentCard2.getTitle(), appContentCard.getTitle()) && kl.equal(Integer.valueOf(appContentCard2.mG()), Integer.valueOf(appContentCard.mG())) && kl.equal(appContentCard2.getType(), appContentCard.getType());
    }

    static String b(AppContentCard appContentCard) {
        return kl.j(appContentCard).a("Actions", appContentCard.getActions()).a("Annotations", appContentCard.mC()).a("Conditions", appContentCard.mw()).a("ContentDescription", appContentCard.mx()).a("CurrentSteps", Integer.valueOf(appContentCard.mD())).a("Description", appContentCard.getDescription()).a("ExtraData", appContentCard.mE()).a("IconImageUri", appContentCard.getIconImageUri()).a("ImageUri", appContentCard.mA()).a("Subtitle", appContentCard.mF()).a("Title", appContentCard.getTitle()).a("TotalSteps", Integer.valueOf(appContentCard.mG())).a("Type", appContentCard.getType()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mH();
    }

    public List<AppContentAction> getActions() {
        return new ArrayList(this.mActions);
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Uri getIconImageUri() {
        return this.aiK;
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

    public Uri mA() {
        return this.ajS;
    }

    public List<AppContentAnnotation> mC() {
        return new ArrayList(this.ajT);
    }

    public int mD() {
        return this.ajU;
    }

    public Bundle mE() {
        return this.ajV;
    }

    public String mF() {
        return this.ajW;
    }

    public int mG() {
        return this.ajX;
    }

    public AppContentCard mH() {
        return this;
    }

    public List<AppContentCondition> mw() {
        return new ArrayList(this.ajO);
    }

    public String mx() {
        return this.ajP;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        AppContentCardEntityCreator.a(this, out, flags);
    }
}
