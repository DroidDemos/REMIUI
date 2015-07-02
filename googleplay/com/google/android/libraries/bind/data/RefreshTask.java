package com.google.android.libraries.bind.data;

import com.google.android.libraries.bind.util.Util;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class RefreshTask implements Runnable {
    private final AtomicBoolean cancelled;
    protected final DataList dataList;
    private boolean executed;
    private final Executor executor;
    protected final Snapshot previousSnapshot;

    protected abstract List<Data> getFreshData() throws DataException;

    public RefreshTask(DataList dataList, Executor executor) {
        this.cancelled = new AtomicBoolean();
        this.dataList = dataList;
        this.executor = executor;
        this.previousSnapshot = dataList.getSnapshot();
    }

    public void execute() {
        Util.checkPrecondition(!this.executed);
        onPreExecute();
        this.executor.execute(this);
        this.executed = true;
    }

    protected void onPreExecute() {
    }

    public void cancel() {
        this.cancelled.set(true);
    }

    public boolean isCancelled() {
        return this.cancelled.get();
    }

    public void run() {
        DataException e;
        if (!isCancelled()) {
            DataChange change;
            Snapshot newSnapshot;
            try {
                List freshData = getFreshData();
                if (freshData != null && !isCancelled()) {
                    DataChange[] outDataChanged = new DataChange[1];
                    if (hasChanged(freshData, outDataChanged)) {
                        Snapshot newSnapshot2 = new Snapshot(this.dataList.primaryKey, freshData);
                        try {
                            change = outDataChanged[0];
                            newSnapshot = newSnapshot2;
                        } catch (DataException e2) {
                            e = e2;
                            newSnapshot = newSnapshot2;
                            newSnapshot = new Snapshot(this.dataList.primaryKey, e);
                            change = new DataChange(e);
                            if (isCancelled()) {
                                postRefresh(newSnapshot, change);
                            }
                        }
                        if (isCancelled()) {
                            postRefresh(newSnapshot, change);
                        }
                    }
                }
            } catch (DataException e3) {
                e = e3;
                newSnapshot = new Snapshot(this.dataList.primaryKey, e);
                change = new DataChange(e);
                if (isCancelled()) {
                    postRefresh(newSnapshot, change);
                }
            }
        }
    }

    protected void postRefresh(Snapshot snapshot, DataChange change) {
        this.dataList.postRefresh(this, snapshot, change, null);
    }

    private boolean hasChanged(List<Data> newList, DataChange[] outChange) {
        if (this.previousSnapshot.isInvalid()) {
            outChange[0] = DataChange.AFFECTS_PRIMARY_KEY;
            return true;
        }
        List<Data> oldList = this.previousSnapshot.list;
        if (newList.size() != oldList.size()) {
            outChange[0] = DataChange.AFFECTS_PRIMARY_KEY;
            return true;
        }
        int rowCount = oldList.size();
        int i = 0;
        while (i < rowCount) {
            if (Util.objectsEqual(((Data) oldList.get(i)).get(this.previousSnapshot.primaryKey), ((Data) newList.get(i)).get(this.dataList.primaryKey))) {
                i++;
            } else {
                outChange[0] = DataChange.AFFECTS_PRIMARY_KEY;
                return true;
            }
        }
        outChange[0] = DataChange.DOESNT_AFFECT_PRIMARY_KEY;
        int[] equalityFields = this.dataList.equalityFields();
        for (i = 0; i < rowCount; i++) {
            if (!isDataEqual((Data) oldList.get(i), (Data) newList.get(i), equalityFields)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDataEqual(Data oldData, Data newData, int[] equalityFields) {
        if (equalityFields == null) {
            return oldData.equals(newData);
        }
        for (int field : equalityFields) {
            if (!oldData.equalsField(newData, field)) {
                return false;
            }
        }
        return true;
    }
}
