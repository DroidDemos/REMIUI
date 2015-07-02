package com.google.android.libraries.bind.data;

import com.google.android.libraries.bind.async.AsyncUtil;
import com.google.android.libraries.bind.logging.Logd;
import com.google.android.libraries.bind.util.Util;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;

public class DataList {
    protected RefreshTask currentRefreshTask;
    private boolean dataDirty;
    private CopyOnWriteArraySet<DataListListener> dataListListeners;
    private int dataVersion;
    private final Snapshot invalidSnapshot;
    private boolean isAutoRefreshing;
    protected Logd logd;
    private final PriorityDataObservable observable;
    protected final int primaryKey;
    private boolean registeredForInvalidation;
    private Snapshot snapshot;
    protected boolean stopAutoRefreshAfterRefresh;

    public interface DataListListener {
        void onDataListRegisteredForInvalidation();

        void onDataListUnregisteredForInvalidation();
    }

    public DataList(int primaryKey) {
        this(primaryKey, null);
    }

    public DataList(int primaryKey, List<Data> optInitialData) {
        this.observable = new PriorityDataObservable();
        this.dataListListeners = new CopyOnWriteArraySet();
        this.primaryKey = primaryKey;
        this.invalidSnapshot = new Snapshot(primaryKey);
        if (optInitialData != null) {
            this.snapshot = new Snapshot(primaryKey, (List) optInitialData);
            this.dataVersion = 1;
            return;
        }
        this.snapshot = this.invalidSnapshot;
    }

    public String toString() {
        Locale locale = Locale.US;
        String str = "%s - primaryKey: %s, size: %d, exception: %s";
        Object[] objArr = new Object[4];
        objArr[0] = getClass().getSimpleName();
        objArr[1] = Data.keyName(this.primaryKey);
        objArr[2] = Integer.valueOf(getCount());
        objArr[3] = didLastRefreshFail() ? this.snapshot.getException().getMessage() : "no";
        return String.format(locale, str, objArr);
    }

    protected void finalize() throws Throwable {
        if (this.registeredForInvalidation || this.observable.size() > 0) {
            logd().e("Leaked datalist", new Object[0]);
            logd().e("  Observables: %s", this.observable);
        }
        super.finalize();
    }

    protected Logd logd() {
        if (this.logd == null) {
            this.logd = Logd.get(getClass());
        }
        return this.logd;
    }

    public void invalidateData() {
        invalidateData(false);
    }

    public void invalidateData(boolean clearList) {
        AsyncUtil.checkMainThread();
        logd().i("invalidateData(clearList=%b)", Boolean.valueOf(clearList));
        if (clearList) {
            update(null, DataChange.INVALIDATION);
        }
        setDirty(true);
        if (this.isAutoRefreshing && hasDataSetObservers()) {
            refresh();
        }
    }

    public int getCount() {
        AsyncUtil.checkMainThread();
        return this.snapshot.getCount();
    }

    public Data getData(int position) {
        AsyncUtil.checkMainThread();
        return this.snapshot.getData(position);
    }

    public Object getItemId(int position) {
        AsyncUtil.checkMainThread();
        return this.snapshot.getItemId(position);
    }

    public boolean isEmpty() {
        AsyncUtil.checkMainThread();
        return this.snapshot.isEmpty();
    }

    public boolean isInvalidPosition(int position) {
        AsyncUtil.checkMainThread();
        return this.snapshot.isInvalidPosition(position);
    }

    public int findPositionForId(Object id) {
        AsyncUtil.checkMainThread();
        return this.snapshot.findPositionForPrimaryValue(id);
    }

    public FilteredDataRow filterRow(Object rowId, Filter filter, int[] equalityFields) {
        FilteredDataRow filteredRow = new FilteredDataRow(this, rowId, filter, equalityFields);
        filteredRow.startAutoRefresh();
        return filteredRow;
    }

    public Snapshot getSnapshot() {
        AsyncUtil.checkMainThread();
        return this.snapshot;
    }

    public void registerDataObserver(DataObserver observer) {
        registerDataObserver(observer, 0);
    }

    public void registerDataObserver(DataObserver observer, int priority) {
        AsyncUtil.checkMainThread();
        if (this.observable.add(observer, priority)) {
            registerForInvalidation();
        }
        logd().d("registerDataSetObserver - count: %d, registeredForInvalidation: %b", Integer.valueOf(this.observable.size()), Boolean.valueOf(this.registeredForInvalidation));
    }

    public void unregisterDataObserver(DataObserver observer) {
        AsyncUtil.checkMainThread();
        if (this.observable.remove(observer)) {
            unregisterForInvalidation();
            stopRefreshTask();
        }
        logd().d("unregisterDataSetObserver - count: %d, registeredForInvalidation: %b", Integer.valueOf(this.observable.size()), Boolean.valueOf(this.registeredForInvalidation));
    }

    public boolean hasDataSetObservers() {
        return this.observable.size() > 0;
    }

    public boolean isDirty() {
        return this.dataDirty;
    }

    protected void setDirty(boolean dirty) {
        this.dataDirty = dirty;
    }

    protected final void registerForInvalidation() {
        boolean z = false;
        logd().d("registerForInvalidation", new Object[0]);
        if (!this.registeredForInvalidation) {
            z = true;
        }
        Util.checkPrecondition(z);
        this.registeredForInvalidation = true;
        onRegisterForInvalidation();
        if (isDirty() && (this.isAutoRefreshing || !hasRefreshedOnce())) {
            refresh();
        }
        Iterator i$ = this.dataListListeners.iterator();
        while (i$.hasNext()) {
            ((DataListListener) i$.next()).onDataListRegisteredForInvalidation();
        }
    }

    protected final void unregisterForInvalidation() {
        logd().d("unregisterForInvalidation", new Object[0]);
        Util.checkPrecondition(this.registeredForInvalidation);
        this.registeredForInvalidation = false;
        onUnregisterForInvalidation();
        Iterator i$ = this.dataListListeners.iterator();
        while (i$.hasNext()) {
            ((DataListListener) i$.next()).onDataListUnregisteredForInvalidation();
        }
    }

    protected void onRegisterForInvalidation() {
    }

    protected void onUnregisterForInvalidation() {
    }

    protected int[] equalityFields() {
        return null;
    }

    public void update(Snapshot newSnapshot, DataChange change) {
        update(newSnapshot, change, null);
    }

    protected void update(Snapshot newSnapshot, DataChange change, Integer optNewDataVersion) {
        boolean z;
        boolean newSnapshotInvalid;
        AsyncUtil.checkMainThread();
        if (newSnapshot == null) {
            newSnapshot = this.invalidSnapshot;
        }
        if (newSnapshot.hasException()) {
            z = false;
        } else {
            z = true;
        }
        setDirty(z);
        if (newSnapshot == this.invalidSnapshot) {
            newSnapshotInvalid = true;
        } else {
            newSnapshotInvalid = false;
        }
        boolean exceptionTransition;
        if (this.snapshot.hasException() || newSnapshot.hasException()) {
            exceptionTransition = true;
        } else {
            exceptionTransition = false;
        }
        if (!newSnapshotInvalid || this.snapshot != this.invalidSnapshot || exceptionTransition) {
            this.snapshot = newSnapshot;
            this.dataVersion = optNewDataVersion == null ? this.dataVersion + 1 : optNewDataVersion.intValue();
            notifyDataChanged(change);
            if (!newSnapshotInvalid && this.stopAutoRefreshAfterRefresh) {
                stopAutoRefresh();
            }
        }
    }

    public void postRefresh(RefreshTask refreshTask, Snapshot snapshot, DataChange change, Integer optDataVersion) {
        final RefreshTask refreshTask2 = refreshTask;
        final Snapshot snapshot2 = snapshot;
        final DataChange dataChange = change;
        final Integer num = optDataVersion;
        Runnable updateRunnable = new Runnable() {
            public void run() {
                if (refreshTask2 == DataList.this.currentRefreshTask) {
                    DataList.this.update(snapshot2, dataChange, num);
                    DataList.this.currentRefreshTask = null;
                }
            }
        };
        if (AsyncUtil.isMainThread()) {
            updateRunnable.run();
        } else {
            AsyncUtil.mainThreadHandler().post(updateRunnable);
        }
    }

    public int dataVersion() {
        return this.dataVersion;
    }

    protected void notifyDataChanged(DataChange change) {
        logd().d("notifyDataChanged", new Object[0]);
        this.observable.notifyDataChanged(change);
    }

    public boolean hasRefreshedOnce() {
        AsyncUtil.checkMainThread();
        return this.snapshot != this.invalidSnapshot;
    }

    public boolean didLastRefreshFail() {
        return this.snapshot.hasException();
    }

    public DataException lastRefreshException() {
        return this.snapshot.getException();
    }

    public DataList stopAutoRefresh() {
        AsyncUtil.checkMainThread();
        logd().d("stopAutoRefresh", new Object[0]);
        this.isAutoRefreshing = false;
        return this;
    }

    public DataList startAutoRefresh() {
        logd().d("startAutoRefresh", new Object[0]);
        this.stopAutoRefreshAfterRefresh = false;
        if (!this.isAutoRefreshing) {
            this.isAutoRefreshing = true;
            if (isDirty() && hasDataSetObservers()) {
                refresh();
            }
        }
        return this;
    }

    public void refresh() {
        AsyncUtil.checkMainThread();
        logd().d("refresh", new Object[0]);
        startRefreshTask();
    }

    private void startRefreshTask() {
        boolean z = false;
        logd().d("startRefreshTask", new Object[0]);
        stopRefreshTask();
        this.currentRefreshTask = makeRefreshTask();
        if (this.currentRefreshTask == null) {
            logd().d("no refresh task", new Object[0]);
            if (!hasRefreshedOnce()) {
                z = true;
            }
            setDirty(z);
            return;
        }
        this.currentRefreshTask.execute();
    }

    protected void stopRefreshTask() {
        logd().d("stopRefreshTask", new Object[0]);
        if (this.currentRefreshTask != null) {
            this.currentRefreshTask.cancel();
            this.currentRefreshTask = null;
        }
    }

    protected RefreshTask makeRefreshTask() {
        return null;
    }
}
