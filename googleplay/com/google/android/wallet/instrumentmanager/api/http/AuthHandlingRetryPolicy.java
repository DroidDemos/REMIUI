package com.google.android.wallet.instrumentmanager.api.http;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;

public class AuthHandlingRetryPolicy extends DefaultRetryPolicy {
    private final ApiContext mApiContext;
    private boolean mHadAuthException;

    public AuthHandlingRetryPolicy(int initialTimeoutMs, ApiContext context) {
        super(initialTimeoutMs, 1, 1.0f);
        this.mApiContext = context;
    }

    public void retry(VolleyError error) throws VolleyError {
        if (!(error instanceof AuthFailureError)) {
            throw error;
        } else if (((AuthFailureError) error).getResolutionIntent() != null || this.mHadAuthException) {
            throw error;
        } else {
            this.mHadAuthException = true;
            this.mApiContext.invalidateAuthToken();
            super.retry(error);
        }
    }
}
