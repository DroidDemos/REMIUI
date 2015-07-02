package com.google.android.vending.verifier.api;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadStatsRequest;

public class PackageVerificationStatsRequest extends BaseVerificationRequest<NetworkResponse, ClientDownloadStatsRequest> {
    public PackageVerificationStatsRequest(String url, ErrorListener errorListener, ClientDownloadStatsRequest request) {
        super(url, errorListener, request);
    }

    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response, null);
    }

    protected void deliverResponse(NetworkResponse response) {
    }
}
