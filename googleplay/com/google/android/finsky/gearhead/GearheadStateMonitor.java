package com.google.android.finsky.gearhead;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.gms.car.Car;
import com.google.android.gms.car.Car.CarConnectionListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

public class GearheadStateMonitor {
    private static final boolean GEARHEAD_SUPPORTED;
    private static GoogleApiClient sApiClient;
    private static CountDownLatch sConnectionLatch;
    private static volatile boolean sHasInitialized;
    private static volatile boolean sIsProjecting;
    private static LinkedList<Runnable> sOnReadyRunnables;

    static {
        GEARHEAD_SUPPORTED = VERSION.SDK_INT >= 21;
        sIsProjecting = false;
        sOnReadyRunnables = Lists.newLinkedList();
        sHasInitialized = false;
        sConnectionLatch = new CountDownLatch(1);
        sApiClient = null;
    }

    public static synchronized void initialize(Runnable onReadyRunnable) {
        synchronized (GearheadStateMonitor.class) {
            if (GEARHEAD_SUPPORTED) {
                if (sConnectionLatch.getCount() != 0) {
                    if (onReadyRunnable != null) {
                        synchronized (sOnReadyRunnables) {
                            sOnReadyRunnables.add(onReadyRunnable);
                        }
                    }
                    if (!sHasInitialized) {
                        IntentFilter connectionChangeFilter = new IntentFilter();
                        connectionChangeFilter.addAction("com.google.android.gms.car.CONNECTED");
                        connectionChangeFilter.addAction("com.google.android.gms.car.DISCONNECTED");
                        Context context = FinskyApp.get();
                        context.registerReceiver(new BroadcastReceiver() {
                            public void onReceive(Context context, Intent intent) {
                                if ("com.google.android.gms.car.CONNECTED".equals(intent.getAction())) {
                                    GearheadStateMonitor.sIsProjecting = true;
                                    GearheadStateMonitor.onReady();
                                    return;
                                }
                                GearheadStateMonitor.sIsProjecting = false;
                                GearheadStateMonitor.onReady();
                            }
                        }, connectionChangeFilter, "com.google.android.gms.permission.CAR", null);
                        sApiClient = Car.buildGoogleApiClientForCar(context, new ConnectionCallbacks() {
                            public void onConnected(Bundle bundle) {
                                GearheadStateMonitor.sIsProjecting = Car.CarApi.isConnectedToCar(GearheadStateMonitor.sApiClient);
                                GearheadStateMonitor.onReady();
                                GearheadStateMonitor.sApiClient.disconnect();
                            }

                            public void onConnectionSuspended(int i) {
                                GearheadStateMonitor.onReady();
                            }
                        }, new OnConnectionFailedListener() {
                            public void onConnectionFailed(ConnectionResult connectionResult) {
                                FinskyLog.w("Could not connect to GMS for Auto connection state: %s", connectionResult);
                                GearheadStateMonitor.onReady();
                            }
                        }, new CarConnectionListener() {
                            public void onConnected(int connectionType) {
                                GearheadStateMonitor.sIsProjecting = true;
                                GearheadStateMonitor.onReady();
                            }

                            public void onDisconnected() {
                                GearheadStateMonitor.sIsProjecting = false;
                                GearheadStateMonitor.onReady();
                            }
                        });
                        sHasInitialized = true;
                        sApiClient.connect();
                    }
                } else if (onReadyRunnable != null) {
                    onReadyRunnable.run();
                }
            } else if (onReadyRunnable != null) {
                onReadyRunnable.run();
            }
        }
    }

    private static void onReady() {
        FinskyLog.v("sIsProjecting:%b", Boolean.valueOf(sIsProjecting));
        sConnectionLatch.countDown();
        synchronized (sOnReadyRunnables) {
            while (!sOnReadyRunnables.isEmpty()) {
                ((Runnable) sOnReadyRunnables.remove()).run();
            }
        }
    }

    public static boolean isReady() {
        if (GEARHEAD_SUPPORTED && sConnectionLatch.getCount() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isProjecting() {
        if (!GEARHEAD_SUPPORTED) {
            return false;
        }
        try {
            sConnectionLatch.await();
            return sIsProjecting;
        } catch (InterruptedException e) {
            FinskyLog.w("Interrupted while awaiting projection result!", new Object[0]);
            return false;
        }
    }
}
