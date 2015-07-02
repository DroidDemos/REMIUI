package com.google.android.gms.people.model;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.rq;
import com.google.android.gms.internal.sb.a;
import com.google.android.gms.internal.sb.b;

public final class PersonBuffer extends DataBufferWithSyncInfo<Person> {
    private final b aCf;
    private final a aCg;

    public PersonBuffer(DataHolder dataHolder, b phoneDecoder, a emailDecoder) {
        super(dataHolder);
        this.aCf = phoneDecoder;
        this.aCg = emailDecoder;
    }

    public Person get(int position) {
        return new rq(this.mDataHolder, position, getMetadata(), this.aCf, this.aCg);
    }

    public String toString() {
        return "People:size=" + getCount();
    }
}
