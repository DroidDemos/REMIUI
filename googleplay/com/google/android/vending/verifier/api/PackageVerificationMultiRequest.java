package com.google.android.vending.verifier.api;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadResponse;
import com.google.android.vending.verifier.protos.CsdClient.ClientMultiDownloadRequest;
import com.google.android.vending.verifier.protos.CsdClient.ClientMultiDownloadResponse;
import java.io.UnsupportedEncodingException;

public class PackageVerificationMultiRequest extends BaseVerificationRequestWithResult<PackageVerificationResult[], ClientMultiDownloadRequest> {
    public PackageVerificationMultiRequest(String url, Listener<PackageVerificationResult[]> listener, ErrorListener errorListener, ClientMultiDownloadRequest request) {
        super(url, listener, errorListener, request);
        setRetryPolicy(new DefaultRetryPolicy(((Integer) G.verifyInstalledPackagesTimeoutMs.get()).intValue(), ((Integer) G.verifyInstalledPackagesNumRetries.get()).intValue(), ((Float) G.verifyInstalledPackagesBackoffMultiplier.get()).floatValue()));
    }

    protected Response<PackageVerificationResult[]> parseNetworkResponse(NetworkResponse response) {
        try {
            ClientDownloadResponse[] responses = ClientMultiDownloadResponse.parseFrom(response.data).responses;
            PackageVerificationResult[] results = new PackageVerificationResult[((ClientMultiDownloadRequest) this.mRequest).requests.length];
            int invalidRequestIds = 0;
            int blankRequestIds = 0;
            for (int i = 0; i < responses.length; i++) {
                if (responses[i].hasRequestId) {
                    try {
                        results[Integer.parseInt(new String(responses[i].requestId, "UTF-8"), 16)] = PackageVerificationResult.fromResponse(responses[i]);
                    } catch (NumberFormatException e) {
                        invalidRequestIds++;
                    } catch (UnsupportedEncodingException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                blankRequestIds++;
            }
            if (invalidRequestIds > 0) {
                FinskyLog.d("Got %d responses with an invalid request id", new Object[0]);
            }
            if (blankRequestIds > 0) {
                FinskyLog.d("Got %d responses with a blank request id", new Object[0]);
            }
            return Response.success(results, null);
        } catch (Throwable ex2) {
            return Response.error(new VolleyError(ex2));
        }
    }
}
