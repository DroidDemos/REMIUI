package com.google.android.vending.remoting.api;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.finsky.config.G;

public class VendingRetryPolicy extends DefaultRetryPolicy {
    private static final int VENDING_TIMEOUT_MS;
    private boolean mHadAuthException;
    private boolean mUseSecureToken;
    private final VendingApiContext mVendingApiContext;

    static {
        VENDING_TIMEOUT_MS = ((Integer) G.vendingRequestTimeoutMs.get()).intValue();
    }

    public VendingRetryPolicy(VendingApiContext context, boolean useSecureToken) {
        super(VENDING_TIMEOUT_MS, 1, 0.0f);
        this.mVendingApiContext = context;
        this.mUseSecureToken = useSecureToken;
    }

    public void retry(VolleyError error) throws VolleyError {
        if (error instanceof AuthFailureError) {
            if (this.mHadAuthException) {
                throw error;
            }
            this.mHadAuthException = true;
            this.mVendingApiContext.invalidateAuthToken(this.mUseSecureToken);
        }
        super.retry(error);
    }
}
