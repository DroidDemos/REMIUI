package com.google.android.finsky.api;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;

public class DfeRetryPolicy extends DefaultRetryPolicy {
    private final DfeApiContext mDfeApiContext;
    private boolean mHadAuthException;

    public DfeRetryPolicy(DfeApiContext context) {
        super(((Integer) DfeApiConfig.dfeRequestTimeoutMs.get()).intValue(), ((Integer) DfeApiConfig.dfeMaxRetries.get()).intValue(), ((Float) DfeApiConfig.dfeBackoffMultipler.get()).floatValue());
        this.mDfeApiContext = context;
    }

    public DfeRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier, DfeApiContext context) {
        super(initialTimeoutMs, maxNumRetries, backoffMultiplier);
        this.mDfeApiContext = context;
    }

    public void retry(VolleyError error) throws VolleyError {
        if (error instanceof AuthFailureError) {
            if (this.mHadAuthException) {
                throw error;
            }
            this.mHadAuthException = true;
            this.mDfeApiContext.invalidateAuthToken();
        }
        super.retry(error);
    }
}
