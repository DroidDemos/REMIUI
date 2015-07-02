package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.List;

public final class AppContentActionEntity implements SafeParcelable, AppContentAction {
    public static final AppContentActionEntityCreator CREATOR;
    private final ArrayList<AppContentConditionEntity> ajO;
    private final String ajP;
    private final String ajQ;
    private final String ajR;
    private final Bundle mExtras;
    private final int mVersionCode;
    private final String vc;

    static {
        CREATOR = new AppContentActionEntityCreator();
    }

    AppContentActionEntity(int versionCode, ArrayList<AppContentConditionEntity> conditions, String contentDescription, Bundle extras, String label, String labelStyle, String type) {
        this.mVersionCode = versionCode;
        this.ajO = conditions;
        this.ajP = contentDescription;
        this.mExtras = extras;
        this.ajQ = label;
        this.ajR = labelStyle;
        this.vc = type;
    }

    public AppContentActionEntity(AppContentAction action) {
        this.mVersionCode = 1;
        this.ajP = action.mx();
        this.mExtras = action.getExtras();
        this.ajQ = action.getLabel();
        this.ajR = action.my();
        this.vc = action.getType();
        List mw = action.mw();
        int size = mw.size();
        this.ajO = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.ajO.add((AppContentConditionEntity) ((AppContentCondition) mw.get(i)).freeze());
        }
    }

    static int a(AppContentAction appContentAction) {
        return kl.hashCode(appContentAction.mw(), appContentAction.mx(), appContentAction.getExtras(), appContentAction.getLabel(), appContentAction.my(), appContentAction.getType());
    }

    static boolean a(AppContentAction appContentAction, Object obj) {
        if (!(obj instanceof AppContentAction)) {
            return false;
        }
        if (appContentAction == obj) {
            return true;
        }
        AppContentAction appContentAction2 = (AppContentAction) obj;
        return kl.equal(appContentAction2.mw(), appContentAction.mw()) && kl.equal(appContentAction2.mx(), appContentAction.mx()) && kl.equal(appContentAction2.getExtras(), appContentAction.getExtras()) && kl.equal(appContentAction2.getLabel(), appContentAction.getLabel()) && kl.equal(appContentAction2.my(), appContentAction.my()) && kl.equal(appContentAction2.getType(), appContentAction.getType());
    }

    static String b(AppContentAction appContentAction) {
        return kl.j(appContentAction).a("Conditions", appContentAction.mw()).a("ContentDescription", appContentAction.mx()).a("Extras", appContentAction.getExtras()).a("Label", appContentAction.getLabel()).a("LabelStyle", appContentAction.my()).a("Type", appContentAction.getType()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mz();
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public String getLabel() {
        return this.ajQ;
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

    public List<AppContentCondition> mw() {
        return new ArrayList(this.ajO);
    }

    public String mx() {
        return this.ajP;
    }

    public String my() {
        return this.ajR;
    }

    public AppContentAction mz() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        AppContentActionEntityCreator.a(this, out, flags);
    }
}
