package com.google.android.finsky.api.model;

import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.Details.BulkDetailsEntry;
import com.google.android.finsky.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.FinskyLog;
import java.util.ArrayList;
import java.util.List;

public class DfeBulkDetails extends DfeModel implements Listener<BulkDetailsResponse> {
    private BulkDetailsResponse mBulkDetailsResponse;
    private final DfeApi mDfeApi;
    private final List<String> mDocids;

    public DfeBulkDetails(DfeApi dfeApi, List<String> docids) {
        this.mDfeApi = dfeApi;
        this.mDocids = docids;
        dfeApi.getDetails(this.mDocids, this, this);
    }

    protected DfeBulkDetails(DfeApi mockDfe, DocV2[] responses) {
        this.mDocids = null;
        this.mDfeApi = mockDfe;
        this.mBulkDetailsResponse = new BulkDetailsResponse();
        this.mBulkDetailsResponse.entry = new BulkDetailsEntry[responses.length];
        for (int i = 0; i < responses.length; i++) {
            BulkDetailsEntry singleEntry = new BulkDetailsEntry();
            singleEntry.doc = responses[i];
            this.mBulkDetailsResponse.entry[i] = singleEntry;
        }
    }

    public List<Document> getDocuments() {
        if (this.mBulkDetailsResponse == null) {
            return null;
        }
        List<Document> result = new ArrayList();
        for (BulkDetailsEntry bulkDetailsEntry : this.mBulkDetailsResponse.entry) {
            DocV2 doc = bulkDetailsEntry.doc;
            if (doc != null) {
                result.add(new Document(doc));
            } else if (FinskyLog.DEBUG) {
                FinskyLog.v("Null document for requested docid: %s ", this.mDocids.get(i));
            }
        }
        return result;
    }

    public DfeApi getDfeApi() {
        return this.mDfeApi;
    }

    public boolean isReady() {
        return this.mBulkDetailsResponse != null;
    }

    public void onResponse(BulkDetailsResponse response) {
        this.mBulkDetailsResponse = response;
        notifyDataSetChanged();
    }
}
