package com.google.android.gms.games.appcontent;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;
import java.util.ArrayList;

public abstract class MultiDataBufferRef extends c {
    protected final ArrayList<DataHolder> akc;

    protected MultiDataBufferRef(ArrayList<DataHolder> dataHolderCollection, int indexForRef, int dataRow) {
        super((DataHolder) dataHolderCollection.get(indexForRef), dataRow);
        this.akc = dataHolderCollection;
    }
}
