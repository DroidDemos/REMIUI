package com.google.android.gms.internal;

import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.i;
import com.google.android.gms.drive.metadata.internal.j;
import com.google.android.gms.drive.metadata.internal.k;
import com.google.android.gms.drive.metadata.internal.l;
import com.google.android.gms.drive.metadata.internal.m;
import java.util.Collections;

public class lp {
    public static final MetadataField<Boolean> abA;
    public static final MetadataField<Boolean> abB;
    public static final MetadataField<Boolean> abC;
    public static final b abD;
    public static final MetadataField<Boolean> abE;
    public static final MetadataField<Boolean> abF;
    public static final MetadataField<Boolean> abG;
    public static final MetadataField<Boolean> abH;
    public static final c abI;
    public static final MetadataField<String> abJ;
    public static final com.google.android.gms.drive.metadata.b<String> abK;
    public static final m abL;
    public static final m abM;
    public static final d abN;
    public static final e abO;
    public static final f abP;
    public static final MetadataField<BitmapTeleporter> abQ;
    public static final g abR;
    public static final h abS;
    public static final MetadataField<String> abT;
    public static final MetadataField<String> abU;
    public static final MetadataField<String> abV;
    public static final com.google.android.gms.drive.metadata.internal.b abW;
    public static final MetadataField<String> abX;
    public static final MetadataField<DriveId> abr;
    public static final MetadataField<String> abs;
    public static final a abt;
    public static final MetadataField<String> abu;
    public static final MetadataField<String> abv;
    public static final MetadataField<String> abw;
    public static final MetadataField<Long> abx;
    public static final MetadataField<Boolean> aby;
    public static final MetadataField<String> abz;

    public static class a extends lq implements SearchableMetadataField<AppVisibleCustomProperties> {
        public a(int i) {
            super(i);
        }
    }

    public static class b extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean> {
        public b(String str, int i) {
            super(str, i);
        }
    }

    public static class c extends l implements SearchableMetadataField<String> {
        public c(String str, int i) {
            super(str, i);
        }
    }

    public static class d extends i<DriveId> implements SearchableCollectionMetadataField<DriveId> {
        public d(String str, int i) {
            super(str, i);
        }
    }

    public static class e extends com.google.android.gms.drive.metadata.internal.g implements SortableMetadataField<Long> {
        public e(String str, int i) {
            super(str, i);
        }
    }

    public static class f extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean> {
        public f(String str, int i) {
            super(str, i);
        }
    }

    public static class g extends l implements SearchableMetadataField<String>, SortableMetadataField<String> {
        public g(String str, int i) {
            super(str, i);
        }
    }

    public static class h extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean> {
        public h(String str, int i) {
            super(str, i);
        }
    }

    static {
        abr = ls.acd;
        abs = new l("alternateLink", 4300000);
        abt = new a(5000000);
        abu = new l("description", 4300000);
        abv = new l("embedLink", 4300000);
        abw = new l("fileExtension", 4300000);
        abx = new com.google.android.gms.drive.metadata.internal.g("fileSize", 4300000);
        aby = new com.google.android.gms.drive.metadata.internal.b("hasThumbnail", 4300000);
        abz = new l("indexableText", 4300000);
        abA = new com.google.android.gms.drive.metadata.internal.b("isAppData", 4300000);
        abB = new com.google.android.gms.drive.metadata.internal.b("isCopyable", 4300000);
        abC = new com.google.android.gms.drive.metadata.internal.b("isEditable", 4100000);
        abD = new b("isPinned", 4100000);
        abE = new com.google.android.gms.drive.metadata.internal.b("isRestricted", 4300000);
        abF = new com.google.android.gms.drive.metadata.internal.b("isShared", 4300000);
        abG = new com.google.android.gms.drive.metadata.internal.b("isTrashable", 4400000);
        abH = new com.google.android.gms.drive.metadata.internal.b("isViewed", 4300000);
        abI = new c("mimeType", 4100000);
        abJ = new l("originalFilename", 4300000);
        abK = new k("ownerNames", 4300000);
        abL = new m("lastModifyingUser", 6000000);
        abM = new m("sharingUser", 6000000);
        abN = new d("parents", 4100000);
        abO = new e("quotaBytesUsed", 4300000);
        abP = new f("starred", 4100000);
        abQ = new j<BitmapTeleporter>("thumbnail", Collections.emptySet(), Collections.emptySet(), 4400000) {
        };
        abR = new g("title", 4100000);
        abS = new h("trashed", 4100000);
        abT = new l("webContentLink", 4300000);
        abU = new l("webViewLink", 4300000);
        abV = new l("uniqueIdentifier", 5000000);
        abW = new com.google.android.gms.drive.metadata.internal.b("writersCanShare", 6000000);
        abX = new l("role", 6000000);
    }
}
