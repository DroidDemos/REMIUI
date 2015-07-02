package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;
import java.util.List;

public interface f<F> {
    <T> F b(b<T> bVar, T t);

    <T> F b(Operator operator, MetadataField<T> metadataField, T t);

    F b(Operator operator, List<F> list);

    F d(MetadataField<?> metadataField);

    <T> F d(MetadataField<T> metadataField, T t);

    F kp();

    F l(F f);
}
