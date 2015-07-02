package com.google.android.gms.car;

import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import com.google.android.gms.car.CarCall.Details;
import java.util.List;

public class CarCallManager {
    private final aj Kn;
    private final au Ko;
    private a Kp;
    private final b Kq;
    private final List<CarCallListener> Kr;

    private class a extends com.google.android.gms.car.ak.a {
        final /* synthetic */ CarCallManager Ks;

        public void dispatchPhoneKeyEvent(final KeyEvent event) {
            Log.d("CAR.TEL.CarCallManager", "dispatchPhoneKeyEvent");
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.dispatchPhoneKeyEvent(event);
                        }
                    });
                }
            }
        }

        public void onAudioStateChanged(boolean isMuted, int route, int supportedRouteMask) {
            Log.d("CAR.TEL.CarCallManager", String.format("onAudioStateChanged isMuted=%b\troute=%d\tsupportedRoutes=%d", new Object[]{Boolean.valueOf(isMuted), Integer.valueOf(route), Integer.valueOf(supportedRouteMask)}));
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    final boolean z = isMuted;
                    final int i = route;
                    final int i2 = supportedRouteMask;
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onAudioStateChanged(z, i, i2);
                        }
                    });
                }
            }
        }

        public void onCallAdded(final CarCall call) {
            Log.d("CAR.TEL.CarCallManager", "onCallAdded " + call);
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onCallAdded(call);
                        }
                    });
                }
            }
        }

        public void onCallDestroyed(final CarCall call) {
            Log.d("CAR.TEL.CarCallManager", "onCallDestroyed");
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onCallDestroyed(call);
                        }
                    });
                }
            }
        }

        public void onCallRemoved(final CarCall call) {
            Log.d("CAR.TEL.CarCallManager", "onCallRemoved " + call);
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onCallRemoved(call);
                        }
                    });
                }
            }
        }

        public void onCannedTextResponsesLoaded(final CarCall call, final List<String> cannedTextResponses) {
            Log.d("CAR.TEL.CarCallManager", "onCannedTextResponsesLoaded");
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onCannedTextResponsesLoaded(call, cannedTextResponses);
                        }
                    });
                }
            }
        }

        public void onChildrenChanged(final CarCall call, final List<CarCall> children) {
            Log.d("CAR.TEL.CarCallManager", "onChildrenChanged");
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onChildrenChanged(call, children);
                        }
                    });
                }
            }
        }

        public void onConferenceableCallsChanged(final CarCall call, final List<CarCall> conferenceableCalls) {
            Log.d("CAR.TEL.CarCallManager", "onConferenceableCallsChanged");
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onConferenceableCallsChanged(call, conferenceableCalls);
                        }
                    });
                }
            }
        }

        public void onDetailsChanged(final CarCall call, final Details details) {
            Log.d("CAR.TEL.CarCallManager", "onDetailsChanged");
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onDetailsChanged(call, details);
                        }
                    });
                }
            }
        }

        public void onParentChanged(final CarCall call, final CarCall parent) {
            Log.d("CAR.TEL.CarCallManager", "onParentChanged");
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onParentChanged(call, parent);
                        }
                    });
                }
            }
        }

        public void onPostDialWait(final CarCall call, final String remainingPostDialSequence) {
            Log.d("CAR.TEL.CarCallManager", "onPostDialWait");
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onPostDialWait(call, remainingPostDialSequence);
                        }
                    });
                }
            }
        }

        public void onStateChanged(final CarCall call, final int state) {
            Log.d("CAR.TEL.CarCallManager", "onStateChanged " + call);
            synchronized (this.Ks.Kr) {
                for (final CarCallListener carCallListener : this.Ks.Kr) {
                    bf.c(new Runnable(this) {
                        final /* synthetic */ a Ku;

                        public void run() {
                            carCallListener.onStateChanged(call, state);
                        }
                    });
                }
            }
        }
    }

    private class b extends com.google.android.gms.car.av.a {
        public void a(String str, String str2, int i) throws RemoteException {
            Log.d("CAR.TEL.CarCallManager", "Action: " + i + " with number: " + str + " id: " + str2);
        }
    }

    public void handleCarDisconnection() {
        Log.d("CAR.TEL.CarCallManager", "handleCarDisconnection.");
        try {
            this.Ko.b(this.Kq);
            this.Kn.b(this.Kp);
        } catch (Exception e) {
        }
    }
}
