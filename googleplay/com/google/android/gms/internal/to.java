package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class to extends FastSafeParcelableJsonResponse implements ItemScope {
    public static final tp CREATOR;
    private static final HashMap<String, Field<?, ?>> aIy;
    String CB;
    to aIA;
    List<String> aIB;
    to aIC;
    String aID;
    String aIE;
    String aIF;
    List<to> aIG;
    int aIH;
    List<to> aII;
    to aIJ;
    List<to> aIK;
    String aIL;
    String aIM;
    to aIN;
    String aIO;
    String aIP;
    List<to> aIQ;
    String aIR;
    String aIS;
    String aIT;
    String aIU;
    String aIV;
    String aIW;
    String aIX;
    String aIY;
    to aIZ;
    final Set<Integer> aIz;
    String aJa;
    String aJb;
    String aJc;
    to aJd;
    to aJe;
    to aJf;
    List<to> aJg;
    String aJh;
    String aJi;
    String aJj;
    String aJk;
    to aJl;
    String aJm;
    String aJn;
    String aJo;
    to aJp;
    String aJq;
    String aJr;
    String aJs;
    String aJt;
    double atp;
    double atq;
    String mDescription;
    String mName;
    final int mVersionCode;
    String ox;
    String vc;
    String vf;

    static {
        CREATOR = new tp();
        aIy = new HashMap();
        aIy.put("about", Field.forConcreteType("about", 2, to.class));
        aIy.put("additionalName", Field.forStrings("additionalName", 3));
        aIy.put("address", Field.forConcreteType("address", 4, to.class));
        aIy.put("addressCountry", Field.forString("addressCountry", 5));
        aIy.put("addressLocality", Field.forString("addressLocality", 6));
        aIy.put("addressRegion", Field.forString("addressRegion", 7));
        aIy.put("associated_media", Field.forConcreteTypeArray("associated_media", 8, to.class));
        aIy.put("attendeeCount", Field.forInteger("attendeeCount", 9));
        aIy.put("attendees", Field.forConcreteTypeArray("attendees", 10, to.class));
        aIy.put("audio", Field.forConcreteType("audio", 11, to.class));
        aIy.put("author", Field.forConcreteTypeArray("author", 12, to.class));
        aIy.put("bestRating", Field.forString("bestRating", 13));
        aIy.put("birthDate", Field.forString("birthDate", 14));
        aIy.put("byArtist", Field.forConcreteType("byArtist", 15, to.class));
        aIy.put("caption", Field.forString("caption", 16));
        aIy.put("contentSize", Field.forString("contentSize", 17));
        aIy.put("contentUrl", Field.forString("contentUrl", 18));
        aIy.put("contributor", Field.forConcreteTypeArray("contributor", 19, to.class));
        aIy.put("dateCreated", Field.forString("dateCreated", 20));
        aIy.put("dateModified", Field.forString("dateModified", 21));
        aIy.put("datePublished", Field.forString("datePublished", 22));
        aIy.put("description", Field.forString("description", 23));
        aIy.put("duration", Field.forString("duration", 24));
        aIy.put("embedUrl", Field.forString("embedUrl", 25));
        aIy.put("endDate", Field.forString("endDate", 26));
        aIy.put("familyName", Field.forString("familyName", 27));
        aIy.put("gender", Field.forString("gender", 28));
        aIy.put("geo", Field.forConcreteType("geo", 29, to.class));
        aIy.put("givenName", Field.forString("givenName", 30));
        aIy.put("height", Field.forString("height", 31));
        aIy.put("id", Field.forString("id", 32));
        aIy.put("image", Field.forString("image", 33));
        aIy.put("inAlbum", Field.forConcreteType("inAlbum", 34, to.class));
        aIy.put("latitude", Field.forDouble("latitude", 36));
        aIy.put("location", Field.forConcreteType("location", 37, to.class));
        aIy.put("longitude", Field.forDouble("longitude", 38));
        aIy.put("name", Field.forString("name", 39));
        aIy.put("partOfTVSeries", Field.forConcreteType("partOfTVSeries", 40, to.class));
        aIy.put("performers", Field.forConcreteTypeArray("performers", 41, to.class));
        aIy.put("playerType", Field.forString("playerType", 42));
        aIy.put("postOfficeBoxNumber", Field.forString("postOfficeBoxNumber", 43));
        aIy.put("postalCode", Field.forString("postalCode", 44));
        aIy.put("ratingValue", Field.forString("ratingValue", 45));
        aIy.put("reviewRating", Field.forConcreteType("reviewRating", 46, to.class));
        aIy.put("startDate", Field.forString("startDate", 47));
        aIy.put("streetAddress", Field.forString("streetAddress", 48));
        aIy.put("text", Field.forString("text", 49));
        aIy.put("thumbnail", Field.forConcreteType("thumbnail", 50, to.class));
        aIy.put("thumbnailUrl", Field.forString("thumbnailUrl", 51));
        aIy.put("tickerSymbol", Field.forString("tickerSymbol", 52));
        aIy.put("type", Field.forString("type", 53));
        aIy.put("url", Field.forString("url", 54));
        aIy.put("width", Field.forString("width", 55));
        aIy.put("worstRating", Field.forString("worstRating", 56));
    }

    public to() {
        this.mVersionCode = 1;
        this.aIz = new HashSet();
    }

    to(Set<Integer> set, int i, to toVar, List<String> list, to toVar2, String str, String str2, String str3, List<to> list2, int i2, List<to> list3, to toVar3, List<to> list4, String str4, String str5, to toVar4, String str6, String str7, String str8, List<to> list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, to toVar5, String str18, String str19, String str20, String str21, to toVar6, double d, to toVar7, double d2, String str22, to toVar8, List<to> list6, String str23, String str24, String str25, String str26, to toVar9, String str27, String str28, String str29, to toVar10, String str30, String str31, String str32, String str33, String str34, String str35) {
        this.aIz = set;
        this.mVersionCode = i;
        this.aIA = toVar;
        this.aIB = list;
        this.aIC = toVar2;
        this.aID = str;
        this.aIE = str2;
        this.aIF = str3;
        this.aIG = list2;
        this.aIH = i2;
        this.aII = list3;
        this.aIJ = toVar3;
        this.aIK = list4;
        this.aIL = str4;
        this.aIM = str5;
        this.aIN = toVar4;
        this.aIO = str6;
        this.aIP = str7;
        this.ox = str8;
        this.aIQ = list5;
        this.aIR = str9;
        this.aIS = str10;
        this.aIT = str11;
        this.mDescription = str12;
        this.aIU = str13;
        this.aIV = str14;
        this.aIW = str15;
        this.aIX = str16;
        this.aIY = str17;
        this.aIZ = toVar5;
        this.aJa = str18;
        this.aJb = str19;
        this.CB = str20;
        this.aJc = str21;
        this.aJd = toVar6;
        this.atp = d;
        this.aJe = toVar7;
        this.atq = d2;
        this.mName = str22;
        this.aJf = toVar8;
        this.aJg = list6;
        this.aJh = str23;
        this.aJi = str24;
        this.aJj = str25;
        this.aJk = str26;
        this.aJl = toVar9;
        this.aJm = str27;
        this.aJn = str28;
        this.aJo = str29;
        this.aJp = toVar10;
        this.aJq = str30;
        this.aJr = str31;
        this.vc = str32;
        this.vf = str33;
        this.aJs = str34;
        this.aJt = str35;
    }

    public int describeContents() {
        tp tpVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof to)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        to toVar = (to) obj;
        for (Field field : aIy.values()) {
            if (isFieldSet(field)) {
                if (!toVar.isFieldSet(field)) {
                    return false;
                }
                if (!getFieldValue(field).equals(toVar.getFieldValue(field))) {
                    return false;
                }
            } else if (toVar.isFieldSet(field)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return rK();
    }

    public HashMap<String, Field<?, ?>> getFieldMappings() {
        return aIy;
    }

    protected Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return this.aIA;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return this.aIB;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return this.aIC;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return this.aID;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return this.aIE;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return this.aIF;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return this.aIG;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return Integer.valueOf(this.aIH);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return this.aII;
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                return this.aIJ;
            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                return this.aIK;
            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                return this.aIL;
            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                return this.aIM;
            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                return this.aIN;
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                return this.aIO;
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                return this.aIP;
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                return this.ox;
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                return this.aIQ;
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                return this.aIR;
            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                return this.aIS;
            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                return this.aIT;
            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                return this.mDescription;
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                return this.aIU;
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                return this.aIV;
            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                return this.aIW;
            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                return this.aIX;
            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                return this.aIY;
            case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                return this.aIZ;
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                return this.aJa;
            case com.google.android.play.R.styleable.Theme_actionModePasteDrawable /*31*/:
                return this.aJb;
            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                return this.CB;
            case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                return this.aJc;
            case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                return this.aJd;
            case com.google.android.play.R.styleable.Theme_actionModePopupWindowStyle /*36*/:
                return Double.valueOf(this.atp);
            case com.google.android.play.R.styleable.Theme_textAppearanceLargePopupMenu /*37*/:
                return this.aJe;
            case com.google.android.play.R.styleable.Theme_textAppearanceSmallPopupMenu /*38*/:
                return Double.valueOf(this.atq);
            case com.google.android.play.R.styleable.Theme_actionDropDownStyle /*39*/:
                return this.mName;
            case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                return this.aJf;
            case com.google.android.play.R.styleable.Theme_spinnerStyle /*41*/:
                return this.aJg;
            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                return this.aJh;
            case com.google.android.play.R.styleable.Theme_homeAsUpIndicator /*43*/:
                return this.aJi;
            case com.google.android.play.R.styleable.Theme_actionButtonStyle /*44*/:
                return this.aJj;
            case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                return this.aJk;
            case com.google.android.play.R.styleable.Theme_buttonBarButtonStyle /*46*/:
                return this.aJl;
            case com.google.android.play.R.styleable.Theme_selectableItemBackground /*47*/:
                return this.aJm;
            case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                return this.aJn;
            case com.google.android.play.R.styleable.Theme_dividerVertical /*49*/:
                return this.aJo;
            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                return this.aJp;
            case com.google.android.play.R.styleable.Theme_activityChooserViewStyle /*51*/:
                return this.aJq;
            case com.google.android.play.R.styleable.Theme_toolbarStyle /*52*/:
                return this.aJr;
            case com.google.android.play.R.styleable.Theme_toolbarNavigationButtonStyle /*53*/:
                return this.vc;
            case com.google.android.play.R.styleable.Theme_popupMenuStyle /*54*/:
                return this.vf;
            case com.google.android.play.R.styleable.Theme_popupWindowStyle /*55*/:
                return this.aJs;
            case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                return this.aJt;
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
        }
    }

    public int hashCode() {
        int i = 0;
        for (Field field : aIy.values()) {
            int hashCode;
            if (isFieldSet(field)) {
                hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    protected boolean isFieldSet(Field field) {
        return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    public to rK() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        tp tpVar = CREATOR;
        tp.a(this, out, flags);
    }
}
