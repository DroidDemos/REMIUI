package com.google.android.libraries.bind.data;

import java.util.concurrent.Executor;

public class BaseReadonlyFilter extends BaseFilter {
    public BaseReadonlyFilter(Executor executor) {
        super(executor);
    }

    public boolean isReadOnly() {
        return true;
    }
}
