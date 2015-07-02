package com.google.android.gms.car;

import android.view.KeyEvent;
import com.google.android.gms.car.CarCall.Details;
import java.util.List;

public class CarCallListener {
    public void dispatchPhoneKeyEvent(KeyEvent event) {
    }

    public void onAudioStateChanged(boolean isMuted, int route, int supportedRouteMask) {
    }

    public void onCallAdded(CarCall call) {
    }

    public void onCallDestroyed(CarCall call) {
    }

    public void onCallRemoved(CarCall call) {
    }

    public void onCannedTextResponsesLoaded(CarCall call, List<String> list) {
    }

    public void onChildrenChanged(CarCall call, List<CarCall> list) {
    }

    public void onConferenceableCallsChanged(CarCall call, List<CarCall> list) {
    }

    public void onDetailsChanged(CarCall call, Details details) {
    }

    public void onParentChanged(CarCall call, CarCall parent) {
    }

    public void onPostDialWait(CarCall call, String remainingPostDialSequence) {
    }

    public void onStateChanged(CarCall call, int state) {
    }
}
