package com.google.android.libraries.bind.data;

import com.google.android.libraries.bind.async.Queues;
import com.google.android.libraries.bind.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class FilteredDataList extends DataList {
    private int currentSourceDataVersion;
    private final int[] equalityFields;
    protected final Filter filter;
    private final DataObserver refreshObserver;
    protected final DataList sourceList;

    protected static class FilterRefreshTask extends RefreshTask {
        protected final Filter filter;
        protected final int newSourceDataVersion;
        protected final DataException sourceException;
        protected final Snapshot sourceSnapshot;

        public FilterRefreshTask(DataList dataList, Executor executor, Filter filter, DataList sourceList) {
            if (filter != null) {
                executor = filter.executor();
            }
            super(dataList, executor);
            this.sourceSnapshot = sourceList.getSnapshot();
            this.newSourceDataVersion = sourceList.dataVersion();
            this.sourceException = sourceList.lastRefreshException();
            this.filter = filter;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (this.filter != null) {
                this.filter.onPreFilter();
            }
        }

        protected List<Data> getSourceData() {
            return (this.filter == null || this.filter.isReadOnly()) ? this.sourceSnapshot.list : this.sourceSnapshot.cloneList();
        }

        protected List<Data> getFreshData() throws DataException {
            if (this.sourceException != null) {
                throw this.sourceException;
            }
            List<Data> sourceData = getSourceData();
            if (isCancelled()) {
                return null;
            }
            if (this.filter == null) {
                return sourceData;
            }
            List<Data> result = new ArrayList(sourceData.size());
            if (this.filter != null) {
                for (Data data : sourceData) {
                    if (this.filter.load(data, this)) {
                        result.add(data);
                    }
                }
                if (isCancelled()) {
                    return null;
                }
                result = this.filter.transform(result, this);
            }
            return result;
        }

        protected void postRefresh(Snapshot snapshot, DataChange change) {
            this.dataList.postRefresh(this, snapshot, change, Integer.valueOf(this.newSourceDataVersion));
        }
    }

    public FilteredDataList(DataList sourceList, Filter filter, int[] equalityFields, int primaryKey, final boolean clearOnInvalidation) {
        super(primaryKey);
        Util.checkPrecondition(sourceList != null);
        this.filter = filter;
        this.sourceList = sourceList;
        this.equalityFields = equalityFields;
        this.refreshObserver = new DataObserver() {
            public void onDataChanged(DataChange change) {
                if (change.isInvalidation && clearOnInvalidation && !FilteredDataList.this.stopAutoRefreshAfterRefresh) {
                    FilteredDataList.this.update(null, DataChange.INVALIDATION);
                }
                FilteredDataList.this.invalidateData();
            }
        };
        if (filter != null) {
            filter.onNewDataList(this);
        }
    }

    protected int[] equalityFields() {
        return this.equalityFields;
    }

    public void refresh() {
        if (this.sourceList.hasRefreshedOnce() || this.sourceList.didLastRefreshFail()) {
            super.refresh();
        } else {
            update(null, DataChange.INVALIDATION);
        }
    }

    public boolean isDirty() {
        return super.isDirty() || this.sourceList.dataVersion() != this.currentSourceDataVersion;
    }

    protected void onRegisterForInvalidation() {
        this.sourceList.registerDataObserver(this.refreshObserver);
    }

    protected void onUnregisterForInvalidation() {
        this.sourceList.unregisterDataObserver(this.refreshObserver);
    }

    public String toString() {
        return new StringBuilder(super.toString()).append("\n\nParent:").append(this.sourceList.toString()).toString();
    }

    protected RefreshTask makeRefreshTask() {
        return new FilterRefreshTask(this, this.sourceList.didLastRefreshFail() ? Queues.BIND_IMMEDIATE : Queues.BIND_CPU, this.filter, this.sourceList);
    }
}
