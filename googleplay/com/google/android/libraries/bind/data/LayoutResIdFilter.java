package com.google.android.libraries.bind.data;

import com.google.android.libraries.bind.async.Queues;
import java.util.List;

public class LayoutResIdFilter extends BaseReadonlyFilter {
    private final Integer originalResId;
    private final int resIdKey;

    public LayoutResIdFilter(int originalResId, int resIdKey) {
        super(Queues.BIND_IMMEDIATE);
        this.originalResId = Integer.valueOf(originalResId);
        this.resIdKey = resIdKey;
    }

    public List<Data> transform(List<Data> inputDataList, RefreshTask refreshTask) {
        if (inputDataList.size() <= 0 || this.originalResId.equals(((Data) inputDataList.get(0)).getAsInteger(this.resIdKey))) {
            return inputDataList;
        }
        return null;
    }
}
