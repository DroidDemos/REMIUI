package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.internal.kn;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class a<T> implements MetadataField<T> {
    private final String abg;
    private final Set<String> abh;
    private final Set<String> abi;
    private final int abj;

    protected a(String str, int i) {
        this.abg = (String) kn.b((Object) str, (Object) "fieldName");
        this.abh = Collections.singleton(str);
        this.abi = Collections.emptySet();
        this.abj = i;
    }

    protected a(String str, Collection<String> collection, Collection<String> collection2, int i) {
        this.abg = (String) kn.b((Object) str, (Object) "fieldName");
        this.abh = Collections.unmodifiableSet(new HashSet(collection));
        this.abi = Collections.unmodifiableSet(new HashSet(collection2));
        this.abj = i;
    }

    public final String getName() {
        return this.abg;
    }

    public final T j(Bundle bundle) {
        kn.b((Object) bundle, (Object) "bundle");
        return bundle.get(getName()) != null ? k(bundle) : null;
    }

    protected abstract T k(Bundle bundle);

    public String toString() {
        return this.abg;
    }
}
