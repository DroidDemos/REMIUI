package com.google.android.libraries.bind.data;

import java.util.List;
import java.util.concurrent.Executor;

public interface Filter {
    Executor executor();

    boolean isReadOnly();

    boolean load(Data data, RefreshTask refreshTask);

    void onNewDataList(DataList dataList);

    void onPreFilter();

    List<Data> transform(List<Data> list, RefreshTask refreshTask);
}
