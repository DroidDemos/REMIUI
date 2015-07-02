package com.google.android.finsky.utils;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;

public class DenyAllNetwork implements Network {
    public NetworkResponse performRequest(Request<?> request) throws NoConnectionError {
        throw new BgDataDisabledError();
    }
}
