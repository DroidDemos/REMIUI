package com.google.android.libraries.bind.data;

public interface DataProperty<ReturnT> {
    ReturnT apply(Data data);
}
