package com.google.android.finsky.billing.carrierbilling.flow.association;

import android.os.Bundle;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.protos.CarrierBilling.VerifyAssociationResponse;

public interface AssociationAction {
    void cancel();

    void resumeState(Bundle bundle, Listener<VerifyAssociationResponse> listener, ErrorListener errorListener);

    void saveState(Bundle bundle);

    void setListener(Listener<VerifyAssociationResponse> listener, ErrorListener errorListener);

    void start(Listener<VerifyAssociationResponse> listener, ErrorListener errorListener);
}
