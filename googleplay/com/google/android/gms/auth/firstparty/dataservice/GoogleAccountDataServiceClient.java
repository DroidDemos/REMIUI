package com.google.android.gms.auth.firstparty.dataservice;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.internal.kn;

public class GoogleAccountDataServiceClient implements GoogleAccountDataClient {
    private final Context mContext;

    private interface a<R> {
        R b(w wVar) throws RemoteException;
    }

    public static class RuntimeInterruptedException extends RuntimeException {
        public RuntimeInterruptedException(InterruptedException e) {
            super(e);
        }
    }

    public static class RuntimeRemoteException extends RuntimeException {
        private final RemoteException Hn;

        public RuntimeRemoteException(RemoteException e) {
            super(e);
            this.Hn = e;
        }
    }

    public GoogleAccountDataServiceClient(Context context) {
        this.mContext = (Context) kn.k(context);
    }

    private <R> R a(a<R> aVar) {
        Intent aC = aC(this.mContext.getPackageName());
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            ServiceConnection aVar2 = new com.google.android.gms.common.a();
            if (this.mContext.bindService(aC, aVar2, 1)) {
                try {
                    R b = aVar.b(com.google.android.gms.auth.firstparty.dataservice.w.a.W(aVar2.hO()));
                    this.mContext.unbindService(aVar2);
                    return b;
                } catch (InterruptedException e) {
                    Log.w("GoogleAccountDataServiceClient", "[GoogleAccountDataServiceClient]  Interrupted when getting service: " + e.getMessage());
                    throw new RuntimeInterruptedException(e);
                } catch (Throwable e2) {
                    Log.w("GoogleAccountDataServiceClient", "[GoogleAccountDataServiceClient]  RemoteException when executing call!", e2);
                    throw new RuntimeRemoteException(e2);
                } catch (Throwable th) {
                    this.mContext.unbindService(aVar2);
                }
            } else {
                Binder.restoreCallingIdentity(clearCallingIdentity);
                return null;
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    private static Intent aC(String str) {
        return new Intent().setPackage("com.google.android.gms").setAction("com.google.android.gms.auth.DATA_PROXY").addCategory("android.intent.category.DEFAULT").putExtra(GoogleAuthUtil.KEY_ANDROID_PACKAGE_NAME, str);
    }

    public TokenResponse confirmCredentials(final ConfirmCredentialsRequest request) {
        return (TokenResponse) a(new a<TokenResponse>(this) {
            final /* synthetic */ GoogleAccountDataServiceClient GS;

            public /* synthetic */ Object b(w wVar) throws RemoteException {
                return d(wVar);
            }

            public TokenResponse d(w wVar) throws RemoteException {
                return wVar.confirmCredentials(request);
            }
        });
    }

    public ReauthSettingsResponse getReauthSettings(final ReauthSettingsRequest request) {
        kn.k(request);
        return (ReauthSettingsResponse) a(new a<ReauthSettingsResponse>(this) {
            final /* synthetic */ GoogleAccountDataServiceClient GS;

            public /* synthetic */ Object b(w wVar) throws RemoteException {
                return l(wVar);
            }

            public ReauthSettingsResponse l(w wVar) throws RemoteException {
                return wVar.getReauthSettings(request);
            }
        });
    }

    public VerifyPinResponse verifyPin(final VerifyPinRequest request) {
        kn.k(request);
        return (VerifyPinResponse) a(new a<VerifyPinResponse>(this) {
            final /* synthetic */ GoogleAccountDataServiceClient GS;

            public /* synthetic */ Object b(w wVar) throws RemoteException {
                return m(wVar);
            }

            public VerifyPinResponse m(w wVar) throws RemoteException {
                return wVar.verifyPin(request);
            }
        });
    }
}
