package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class AppContentConditionEntity implements SafeParcelable, AppContentCondition {
    public static final AppContentConditionEntityCreator CREATOR;
    private final String ajY;
    private final String ajZ;
    private final String aka;
    private final Bundle akb;
    private final int mVersionCode;

    static {
        CREATOR = new AppContentConditionEntityCreator();
    }

    AppContentConditionEntity(int versionCode, String defaultValue, String expectedValue, String predicate, Bundle predicateParameters) {
        this.mVersionCode = versionCode;
        this.ajY = defaultValue;
        this.ajZ = expectedValue;
        this.aka = predicate;
        this.akb = predicateParameters;
    }

    public AppContentConditionEntity(AppContentCondition condition) {
        this.mVersionCode = 1;
        this.ajY = condition.mI();
        this.ajZ = condition.mI();
        this.aka = condition.mK();
        this.akb = condition.mL();
    }

    static int a(AppContentCondition appContentCondition) {
        return kl.hashCode(appContentCondition.mI(), appContentCondition.mJ(), appContentCondition.mK(), appContentCondition.mL());
    }

    static boolean a(AppContentCondition appContentCondition, Object obj) {
        if (!(obj instanceof AppContentCondition)) {
            return false;
        }
        if (appContentCondition == obj) {
            return true;
        }
        AppContentCondition appContentCondition2 = (AppContentCondition) obj;
        return kl.equal(appContentCondition2.mI(), appContentCondition.mI()) && kl.equal(appContentCondition2.mJ(), appContentCondition.mJ()) && kl.equal(appContentCondition2.mK(), appContentCondition.mK()) && kl.equal(appContentCondition2.mL(), appContentCondition.mL());
    }

    static String b(AppContentCondition appContentCondition) {
        return kl.j(appContentCondition).a("DefaultValue", appContentCondition.mI()).a("ExpectedValue", appContentCondition.mJ()).a("Predicate", appContentCondition.mK()).a("PredicateParameters", appContentCondition.mL()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mM();
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public String mI() {
        return this.ajY;
    }

    public String mJ() {
        return this.ajZ;
    }

    public String mK() {
        return this.aka;
    }

    public Bundle mL() {
        return this.akb;
    }

    public AppContentCondition mM() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        AppContentConditionEntityCreator.a(this, out, flags);
    }
}
