package com.google.android.libraries.bind.data;

import java.util.List;
import java.util.concurrent.Executor;

public abstract class BaseFilter implements Filter {
    private final Executor executor;

    public BaseFilter(Executor executor) {
        this.executor = executor;
    }

    public Executor executor() {
        return this.executor;
    }

    public void onNewDataList(DataList dataList) {
    }

    public void onPreFilter() {
    }

    public boolean load(Data data, RefreshTask refreshTask) {
        return true;
    }

    public List<Data> transform(List<Data> inputDataList, RefreshTask refreshTask) {
        return inputDataList;
    }
}
