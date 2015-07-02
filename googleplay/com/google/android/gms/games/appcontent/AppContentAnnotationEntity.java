package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class AppContentAnnotationEntity implements SafeParcelable, AppContentAnnotation {
    public static final AppContentAnnotationEntityCreator CREATOR;
    private final String Yv;
    private final Uri ajS;
    private final String mDescription;
    private final int mVersionCode;
    private final String vc;

    static {
        CREATOR = new AppContentAnnotationEntityCreator();
    }

    AppContentAnnotationEntity(int versionCode, String description, Uri imageUri, String title, String type) {
        this.mVersionCode = versionCode;
        this.mDescription = description;
        this.ajS = imageUri;
        this.Yv = title;
        this.vc = type;
    }

    public AppContentAnnotationEntity(AppContentAnnotation card) {
        this.mVersionCode = 1;
        this.mDescription = card.getDescription();
        this.ajS = card.mA();
        this.Yv = card.getTitle();
        this.vc = card.getType();
    }

    static int a(AppContentAnnotation appContentAnnotation) {
        return kl.hashCode(appContentAnnotation.getDescription(), appContentAnnotation.mA(), appContentAnnotation.getTitle(), appContentAnnotation.getType());
    }

    static boolean a(AppContentAnnotation appContentAnnotation, Object obj) {
        if (!(obj instanceof AppContentAnnotation)) {
            return false;
        }
        if (appContentAnnotation == obj) {
            return true;
        }
        AppContentAnnotation appContentAnnotation2 = (AppContentAnnotation) obj;
        return kl.equal(appContentAnnotation2.getDescription(), appContentAnnotation.getDescription()) && kl.equal(appContentAnnotation2.mA(), appContentAnnotation.mA()) && kl.equal(appContentAnnotation2.getTitle(), appContentAnnotation.getTitle()) && kl.equal(appContentAnnotation2.getType(), appContentAnnotation.getType());
    }

    static String b(AppContentAnnotation appContentAnnotation) {
        return kl.j(appContentAnnotation).a("Description", appContentAnnotation.getDescription()).a("ImageUri", appContentAnnotation.mA()).a("Title", appContentAnnotation.getTitle()).a("Type", appContentAnnotation.getType()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mB();
    }

    public String getDescription() {
        return this.mDescription;
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

    public AppContentAnnotation mB() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        AppContentAnnotationEntityCreator.a(this, out, flags);
    }
}
