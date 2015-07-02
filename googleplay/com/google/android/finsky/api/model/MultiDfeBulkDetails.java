package com.google.android.finsky.api.model;

import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import java.util.ArrayList;
import java.util.List;

public class MultiDfeBulkDetails extends DfeModel implements OnDataChangedListener {
    private VolleyError mLastVolleyError;
    protected final List<DfeBulkDetails> mRequests;

    public MultiDfeBulkDetails() {
        this.mRequests = new ArrayList();
    }

    public void addRequest(DfeApi dfeApi, List<String> docids) {
        DfeBulkDetails request = new DfeBulkDetails(dfeApi, (List) docids);
        request.addDataChangedListener(this);
        request.addErrorListener(this);
        this.mRequests.add(request);
    }

    protected void addResponsesForTest(DfeApi mockDfe, DocV2[] responses) {
        this.mRequests.add(new DfeBulkDetails(mockDfe, responses));
    }

    public boolean isReady() {
        for (int i = 0; i < this.mRequests.size(); i++) {
            if (!((DfeBulkDetails) this.mRequests.get(i)).isReady()) {
                return false;
            }
        }
        return true;
    }

    public void onDataChanged() {
        int i = 0;
        while (i < this.mRequests.size()) {
            DfeBulkDetails request = (DfeBulkDetails) this.mRequests.get(i);
            if (!request.inErrorState() && request.isReady()) {
                i++;
            } else {
                return;
            }
        }
        collateResponses();
        notifyDataSetChanged();
    }

    public void onErrorResponse(VolleyError error) {
        if (this.mLastVolleyError == null) {
            notifyErrorOccured(error);
            this.mLastVolleyError = error;
        }
    }

    protected void collateResponses() {
    }

    public VolleyError getVolleyError() {
        throw new UnsupportedOperationException();
    }

    public boolean inErrorState() {
        throw new UnsupportedOperationException();
    }

    protected void clearErrors() {
        throw new UnsupportedOperationException();
    }
}
