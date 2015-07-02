package com.google.android.finsky.api;

import android.net.NetworkInfo;

public interface NetworkStateProvider {
    NetworkInfo getCurrentNetworkInfo();
}
