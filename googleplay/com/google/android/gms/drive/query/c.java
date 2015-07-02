package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.query.internal.Operator;
import com.google.android.gms.drive.query.internal.f;
import java.util.List;

public class c implements f<String> {
    public <T> String a(b<T> bVar, T t) {
        return String.format("contains(%s,%s)", new Object[]{bVar.getName(), t});
    }

    public <T> String a(Operator operator, MetadataField<T> metadataField, T t) {
        return String.format("cmp(%s,%s,%s)", new Object[]{operator.getTag(), metadataField.getName(), t});
    }

    public String a(Operator operator, List<String> list) {
        StringBuilder stringBuilder = new StringBuilder(operator.getTag() + "(");
        String str = "";
        for (String str2 : list) {
            stringBuilder.append(str);
            stringBuilder.append(str2);
            str = ",";
        }
        return stringBuilder.append(")").toString();
    }

    public /* synthetic */ Object b(b bVar, Object obj) {
        return a(bVar, obj);
    }

    public /* synthetic */ Object b(Operator operator, MetadataField metadataField, Object obj) {
        return a(operator, metadataField, obj);
    }

    public /* synthetic */ Object b(Operator operator, List list) {
        return a(operator, list);
    }

    public String bA(String str) {
        return String.format("not(%s)", new Object[]{str});
    }

    public String c(MetadataField<?> metadataField) {
        return String.format("fieldOnly(%s)", new Object[]{metadataField.getName()});
    }

    public <T> String c(MetadataField<T> metadataField, T t) {
        return String.format("has(%s,%s)", new Object[]{metadataField.getName(), t});
    }

    public /* synthetic */ Object d(MetadataField metadataField) {
        return c(metadataField);
    }

    public /* synthetic */ Object d(MetadataField metadataField, Object obj) {
        return c(metadataField, obj);
    }

    public String ko() {
        return "all()";
    }

    public /* synthetic */ Object kp() {
        return ko();
    }

    public /* synthetic */ Object l(Object obj) {
        return bA((String) obj);
    }
}
