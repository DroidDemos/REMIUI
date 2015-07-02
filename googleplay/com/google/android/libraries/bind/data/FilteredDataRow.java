package com.google.android.libraries.bind.data;

import com.google.android.libraries.bind.async.Queues;
import java.util.ArrayList;
import java.util.List;

public class FilteredDataRow extends FilteredDataList {
    private boolean emptyWhenNone;
    private final Object rowId;

    public FilteredDataRow(DataList sourceList, Object rowId, Filter filter, int[] equalityFields) {
        super(sourceList, filter, equalityFields, sourceList.primaryKey, false);
        this.rowId = rowId;
    }

    protected RefreshTask makeRefreshTask() {
        int matchingPosition = this.sourceList.findPositionForId(this.rowId);
        if (matchingPosition == -1 && !this.emptyWhenNone) {
            return null;
        }
        final Data rowData = this.sourceList.getData(matchingPosition);
        return new FilterRefreshTask(this, Queues.BIND_IMMEDIATE, this.filter, this.sourceList) {
            protected List<Data> getSourceData() {
                if (rowData != null) {
                    List<Data> sourceData = new ArrayList(1);
                    sourceData.add(rowData);
                    return sourceData;
                } else if (FilteredDataRow.this.emptyWhenNone) {
                    return new ArrayList(0);
                } else {
                    return null;
                }
            }
        };
    }
}
