package com.google.android.vending.verifier.api;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadResponse;

public class PackageVerificationRequest extends BaseVerificationRequestWithResult<PackageVerificationResult, ClientDownloadRequest> {
    public PackageVerificationRequest(String url, Listener<PackageVerificationResult> listener, ErrorListener errorListener, ClientDownloadRequest request) {
        super(url, listener, errorListener, request);
    }

    protected Response<PackageVerificationResult> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(PackageVerificationResult.fromResponse(ClientDownloadResponse.parseFrom(response.data)), null);
        } catch (Throwable ex) {
            return Response.error(new VolleyError(ex));
        }
    }
}
