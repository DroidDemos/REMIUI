package com.google.android.finsky.remoting;

import android.net.ConnectivityManager;
import android.net.Uri;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.wallet.instrumentmanager.R;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RadioConnectionImpl implements RadioConnection {
    private final ConnectivityManager mConnMgr;
    private final int mConnectionType;
    private final PhoneFeature mPhoneFeature;

    public RadioConnectionImpl(ConnectivityManager connMgr, int connectionType, PhoneFeature phoneFeature) {
        this.mConnectionType = connectionType;
        this.mPhoneFeature = phoneFeature;
        this.mConnMgr = connMgr;
    }

    private void startRadio() throws RadioConnectionException {
        int result = this.mConnMgr.startUsingNetworkFeature(0, this.mPhoneFeature.getValue());
        switch (result) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (FinskyLog.DEBUG) {
                    FinskyLog.v(this.mPhoneFeature + ": APN request started: " + Thread.currentThread(), new Object[0]);
                    return;
                }
                return;
            default:
                throw new RadioConnectionException(this.mPhoneFeature + ": Start network failed - " + result);
        }
    }

    private boolean waitForRadio(int pollTimeoutMs, int pollIntervalMs) throws RadioConnectionException {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < ((long) pollTimeoutMs) + startTime) {
            try {
                Thread.sleep((long) pollIntervalMs);
                if (isRadioActive()) {
                    if (!FinskyLog.DEBUG) {
                        return true;
                    }
                    FinskyLog.v("Radio came up after %dms (timeoutMs=%d, pollIntervalMs=%d).", Long.valueOf(System.currentTimeMillis() - startTime), Integer.valueOf(pollTimeoutMs), Integer.valueOf(pollIntervalMs));
                    return true;
                }
            } catch (Throwable e) {
                throw new RadioConnectionException(e);
            }
        }
        return false;
    }

    public void ensureRouteToHost(String url) throws RadioConnectionException {
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            url = "http://" + url;
        }
        try {
            InetAddress inetAddr = InetAddress.getByName(Uri.parse(url).getHost());
            byte[] addrBytes = inetAddr.getAddress();
            if (!this.mConnMgr.requestRouteToHost(this.mConnectionType, ((((addrBytes[3] & 255) << 24) | ((addrBytes[2] & 255) << 16)) | ((addrBytes[1] & 255) << 8)) | (addrBytes[0] & 255))) {
                throw new RadioConnectionException("Cannot establish route to " + inetAddr + " for " + url);
            }
        } catch (UnknownHostException e) {
            throw new RadioConnectionException("Cannot establish route for " + url + ": Unknown host");
        }
    }

    private boolean isRadioActive() {
        return this.mConnMgr.getNetworkInfo(this.mConnectionType).isConnected();
    }

    public void start() throws RadioConnectionException {
        if (isRadioActive()) {
            this.mConnMgr.startUsingNetworkFeature(0, this.mPhoneFeature.getValue());
            return;
        }
        startRadio();
        if (!waitForRadio(((Integer) G.vendingDcbPollTimeoutMs.get()).intValue(), 500)) {
            throw new RadioConnectionException("Timeout waiting for radio to come up");
        }
    }

    public void stop() {
        FinskyLog.d("Giving back radio.", new Object[0]);
        this.mConnMgr.stopUsingNetworkFeature(0, this.mPhoneFeature.getValue());
    }
}
