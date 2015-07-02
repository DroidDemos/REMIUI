package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.a;
import java.util.ArrayList;

public final class AppContentUtils {

    private interface AppContentRunner {
        void b(ArrayList<DataHolder> arrayList, int i);
    }

    public static ArrayList<AppContentAction> a(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        final ArrayList<AppContentAction> arrayList2 = new ArrayList();
        DataHolder dataHolder2 = dataHolder;
        String str2 = str;
        a(dataHolder2, 1, str2, "action_id", i, new AppContentRunner() {
            public void b(ArrayList<DataHolder> arrayList, int i) {
                arrayList2.add(new AppContentActionRef(arrayList, i));
            }
        }, arrayList);
        return arrayList2;
    }

    private static void a(DataHolder dataHolder, int i, String str, String str2, int i2, AppContentRunner appContentRunner, ArrayList<DataHolder> arrayList) {
        DataHolder dataHolder2 = (DataHolder) arrayList.get(i);
        Object c = dataHolder.c(str, i2, dataHolder.cW(i2));
        if (!TextUtils.isEmpty(c)) {
            int count = dataHolder2.getCount();
            String[] split = c.split(",");
            for (int i3 = 0; i3 < count; i3++) {
                CharSequence c2 = dataHolder2.c(str2, i3, dataHolder2.cW(i3));
                if (!TextUtils.isEmpty(c2) && a.b(split, c2)) {
                    appContentRunner.b(arrayList, i3);
                }
            }
        }
    }

    public static ArrayList<AppContentAnnotation> b(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        final ArrayList<AppContentAnnotation> arrayList2 = new ArrayList();
        DataHolder dataHolder2 = dataHolder;
        String str2 = str;
        a(dataHolder2, 2, str2, "annotation_id", i, new AppContentRunner() {
            public void b(ArrayList<DataHolder> arrayList, int i) {
                arrayList2.add(new AppContentAnnotationRef(arrayList, i));
            }
        }, arrayList);
        return arrayList2;
    }

    public static ArrayList<AppContentCondition> c(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        final ArrayList<AppContentCondition> arrayList2 = new ArrayList();
        DataHolder dataHolder2 = dataHolder;
        String str2 = str;
        a(dataHolder2, 4, str2, "condition_id", i, new AppContentRunner() {
            public void b(ArrayList<DataHolder> arrayList, int i) {
                arrayList2.add(new AppContentConditionRef(arrayList, i));
            }
        }, arrayList);
        return arrayList2;
    }

    public static Bundle d(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        final Bundle bundle = new Bundle();
        final DataHolder dataHolder2 = (DataHolder) arrayList.get(3);
        AppContentRunner anonymousClass4 = new AppContentRunner() {
            public void b(ArrayList<DataHolder> arrayList, int i) {
                AppContentTuple appContentTupleRef = new AppContentTupleRef(dataHolder2, i);
                bundle.putString(appContentTupleRef.getName(), appContentTupleRef.getValue());
            }
        };
        a(dataHolder, 3, str, "tuple_id", i, anonymousClass4, arrayList);
        return bundle;
    }
}
