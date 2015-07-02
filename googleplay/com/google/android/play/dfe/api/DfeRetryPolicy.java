package com.google.android.play.dfe.api;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.play.utils.config.PlayG;

public class DfeRetryPolicy extends DefaultRetryPolicy {
    private boolean mHadAuthException;
    private final PlayDfeApiContext mPlayDfeApiContext;

    public DfeRetryPolicy(PlayDfeApiContext context) {
        super(((Integer) PlayG.dfeRequestTimeoutMs.get()).intValue(), ((Integer) PlayG.dfeMaxRetries.get()).intValue(), ((Float) PlayG.dfeBackoffMultipler.get()).floatValue());
        this.mPlayDfeApiContext = context;
    }

    public DfeRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier, PlayDfeApiContext context) {
        super(initialTimeoutMs, maxNumRetries, backoffMultiplier);
        this.mPlayDfeApiContext = context;
    }

    public void retry(VolleyError error) throws VolleyError {
        if (error instanceof AuthFailureError) {
            if (this.mHadAuthException) {
                throw error;
            }
            this.mHadAuthException = true;
            this.mPlayDfeApiContext.invalidateAuthToken();
        }
        super.retry(error);
    }
}
