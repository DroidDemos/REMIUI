package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.wallet.instrumentmanager.R;

public interface w extends IInterface {

    public static abstract class a extends Binder implements w {

        private static class a implements w {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public CheckFactoryResetPolicyComplianceResponse a(CheckFactoryResetPolicyComplianceRequest checkFactoryResetPolicyComplianceRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (checkFactoryResetPolicyComplianceRequest != null) {
                        obtain.writeInt(1);
                        checkFactoryResetPolicyComplianceRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    CheckFactoryResetPolicyComplianceResponse as = obtain2.readInt() != 0 ? CheckFactoryResetPolicyComplianceResponse.CREATOR.as(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return as;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public AccountNameCheckResponse checkAccountName(AccountNameCheckRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    AccountNameCheckResponse ah = obtain2.readInt() != 0 ? AccountNameCheckResponse.CREATOR.ah(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ah;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public PasswordCheckResponse checkPassword(PasswordCheckRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    PasswordCheckResponse aF = obtain2.readInt() != 0 ? PasswordCheckResponse.CREATOR.aF(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aF;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CheckRealNameResponse checkRealName(CheckRealNameRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    CheckRealNameResponse au = obtain2.readInt() != 0 ? CheckRealNameResponse.CREATOR.au(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return au;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ClearTokenResponse clearToken(ClearTokenRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    ClearTokenResponse aw = obtain2.readInt() != 0 ? ClearTokenResponse.CREATOR.aw(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aw;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public TokenResponse confirmCredentials(ConfirmCredentialsRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    TokenResponse aL = obtain2.readInt() != 0 ? TokenResponse.CREATOR.aL(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aL;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public TokenResponse createAccount(GoogleAccountSetupRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    TokenResponse aL = obtain2.readInt() != 0 ? TokenResponse.CREATOR.aL(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aL;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public TokenResponse createPlusProfile(GoogleAccountSetupRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    TokenResponse aL = obtain2.readInt() != 0 ? TokenResponse.CREATOR.aL(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aL;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean fL() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    this.ld.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public AccountChangeEventsResponse getAccountChangeEvents(AccountChangeEventsRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    AccountChangeEventsResponse createFromParcel = obtain2.readInt() != 0 ? AccountChangeEventsResponse.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public GoogleAccountData getAccountData(String accountName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    obtain.writeString(accountName);
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    GoogleAccountData ay = obtain2.readInt() != 0 ? GoogleAccountData.CREATOR.ay(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ay;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getAccountExportData(String accountName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    obtain.writeString(accountName);
                    this.ld.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getAccountId(String accountName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    obtain.writeString(accountName);
                    this.ld.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public AccountRecoveryData getAccountRecoveryCountryInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    this.ld.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    AccountRecoveryData ai = obtain2.readInt() != 0 ? AccountRecoveryData.CREATOR.ai(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ai;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public AccountRecoveryData getAccountRecoveryData(AccountRecoveryDataRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    AccountRecoveryData ai = obtain2.readInt() != 0 ? AccountRecoveryData.CREATOR.ai(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ai;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public AccountRecoveryGuidance getAccountRecoveryGuidance(AccountRecoveryGuidanceRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    AccountRecoveryGuidance ak = obtain2.readInt() != 0 ? AccountRecoveryGuidance.CREATOR.ak(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ak;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public GplusInfoResponse getGplusInfo(GplusInfoRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    GplusInfoResponse aB = obtain2.readInt() != 0 ? GplusInfoResponse.CREATOR.aB(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aB;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public OtpResponse getOtp(OtpRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    OtpResponse aD = obtain2.readInt() != 0 ? OtpResponse.CREATOR.aD(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aD;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ReauthSettingsResponse getReauthSettings(ReauthSettingsRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    ReauthSettingsResponse aJ = obtain2.readInt() != 0 ? ReauthSettingsResponse.CREATOR.aJ(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aJ;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public TokenResponse getToken(TokenRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    TokenResponse aL = obtain2.readInt() != 0 ? TokenResponse.CREATOR.aL(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aL;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public WebSetupConfig getWebSetupConfig(WebSetupConfigRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    WebSetupConfig aP = obtain2.readInt() != 0 ? WebSetupConfig.CREATOR.aP(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aP;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean installAccountFromExportData(String accountName, Bundle exportData) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    obtain.writeString(accountName);
                    if (exportData != null) {
                        obtain.writeInt(1);
                        exportData.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public AccountRemovalResponse removeAccount(AccountRemovalRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    AccountRemovalResponse ap = obtain2.readInt() != 0 ? AccountRemovalResponse.CREATOR.ap(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ap;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public TokenResponse signIn(AccountSignInRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    TokenResponse aL = obtain2.readInt() != 0 ? TokenResponse.CREATOR.aL(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aL;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public AccountRecoveryUpdateResult updateAccountRecoveryData(AccountRecoveryUpdateRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    AccountRecoveryUpdateResult an = obtain2.readInt() != 0 ? AccountRecoveryUpdateResult.CREATOR.an(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return an;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public TokenResponse updateCredentials(UpdateCredentialsRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    TokenResponse aL = obtain2.readInt() != 0 ? TokenResponse.CREATOR.aL(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aL;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public VerifyPinResponse verifyPin(VerifyPinRequest request) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (request != null) {
                        obtain.writeInt(1);
                        request.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    VerifyPinResponse aO = obtain2.readInt() != 0 ? VerifyPinResponse.CREATOR.aO(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return aO;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static w W(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof w)) ? new a(iBinder) : (w) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CheckFactoryResetPolicyComplianceRequest checkFactoryResetPolicyComplianceRequest = null;
            int i = 0;
            GoogleAccountSetupRequest az;
            TokenResponse createAccount;
            AccountRecoveryData accountRecoveryCountryInfo;
            Bundle accountExportData;
            boolean installAccountFromExportData;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    GoogleAccountData accountData = getAccountData(data.readString());
                    reply.writeNoException();
                    if (accountData != null) {
                        reply.writeInt(1);
                        accountData.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    AccountNameCheckRequest ag;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        ag = AccountNameCheckRequest.CREATOR.ag(data);
                    }
                    AccountNameCheckResponse checkAccountName = checkAccountName(ag);
                    reply.writeNoException();
                    if (checkAccountName != null) {
                        reply.writeInt(1);
                        checkAccountName.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    PasswordCheckRequest aE;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aE = PasswordCheckRequest.CREATOR.aE(data);
                    }
                    PasswordCheckResponse checkPassword = checkPassword(aE);
                    reply.writeNoException();
                    if (checkPassword != null) {
                        reply.writeInt(1);
                        checkPassword.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    CheckRealNameRequest at;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        at = CheckRealNameRequest.CREATOR.at(data);
                    }
                    CheckRealNameResponse checkRealName = checkRealName(at);
                    reply.writeNoException();
                    if (checkRealName != null) {
                        reply.writeInt(1);
                        checkRealName.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        az = GoogleAccountSetupRequest.CREATOR.az(data);
                    }
                    createAccount = createAccount(az);
                    reply.writeNoException();
                    if (createAccount != null) {
                        reply.writeInt(1);
                        createAccount.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    GplusInfoRequest aA;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aA = GplusInfoRequest.CREATOR.aA(data);
                    }
                    GplusInfoResponse gplusInfo = getGplusInfo(aA);
                    reply.writeNoException();
                    if (gplusInfo != null) {
                        reply.writeInt(1);
                        gplusInfo.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        az = GoogleAccountSetupRequest.CREATOR.az(data);
                    }
                    createAccount = createPlusProfile(az);
                    reply.writeNoException();
                    if (createAccount != null) {
                        reply.writeInt(1);
                        createAccount.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    TokenRequest aK;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aK = TokenRequest.CREATOR.aK(data);
                    }
                    createAccount = getToken(aK);
                    reply.writeNoException();
                    if (createAccount != null) {
                        reply.writeInt(1);
                        createAccount.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    AccountSignInRequest aq;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aq = AccountSignInRequest.CREATOR.aq(data);
                    }
                    createAccount = signIn(aq);
                    reply.writeNoException();
                    if (createAccount != null) {
                        reply.writeInt(1);
                        createAccount.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    ConfirmCredentialsRequest ax;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        ax = ConfirmCredentialsRequest.CREATOR.ax(data);
                    }
                    createAccount = confirmCredentials(ax);
                    reply.writeNoException();
                    if (createAccount != null) {
                        reply.writeInt(1);
                        createAccount.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    UpdateCredentialsRequest aM;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aM = UpdateCredentialsRequest.CREATOR.aM(data);
                    }
                    createAccount = updateCredentials(aM);
                    reply.writeNoException();
                    if (createAccount != null) {
                        reply.writeInt(1);
                        createAccount.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    accountRecoveryCountryInfo = getAccountRecoveryCountryInfo();
                    reply.writeNoException();
                    if (accountRecoveryCountryInfo != null) {
                        reply.writeInt(1);
                        accountRecoveryCountryInfo.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    AccountRecoveryDataRequest aj;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aj = AccountRecoveryDataRequest.CREATOR.aj(data);
                    }
                    accountRecoveryCountryInfo = getAccountRecoveryData(aj);
                    reply.writeNoException();
                    if (accountRecoveryCountryInfo != null) {
                        reply.writeInt(1);
                        accountRecoveryCountryInfo.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    AccountRecoveryUpdateRequest am;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        am = AccountRecoveryUpdateRequest.CREATOR.am(data);
                    }
                    AccountRecoveryUpdateResult updateAccountRecoveryData = updateAccountRecoveryData(am);
                    reply.writeNoException();
                    if (updateAccountRecoveryData != null) {
                        reply.writeInt(1);
                        updateAccountRecoveryData.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    AccountRecoveryGuidanceRequest al;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        al = AccountRecoveryGuidanceRequest.CREATOR.al(data);
                    }
                    AccountRecoveryGuidance accountRecoveryGuidance = getAccountRecoveryGuidance(al);
                    reply.writeNoException();
                    if (accountRecoveryGuidance != null) {
                        reply.writeInt(1);
                        accountRecoveryGuidance.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    accountExportData = getAccountExportData(data.readString());
                    reply.writeNoException();
                    if (accountExportData != null) {
                        reply.writeInt(1);
                        accountExportData.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    String readString = data.readString();
                    if (data.readInt() != 0) {
                        accountExportData = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    installAccountFromExportData = installAccountFromExportData(readString, accountExportData);
                    reply.writeNoException();
                    reply.writeInt(installAccountFromExportData ? 1 : 0);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    WebSetupConfigRequest aQ;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aQ = WebSetupConfigRequest.CREATOR.aQ(data);
                    }
                    WebSetupConfig webSetupConfig = getWebSetupConfig(aQ);
                    reply.writeNoException();
                    if (webSetupConfig != null) {
                        reply.writeInt(1);
                        webSetupConfig.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    ClearTokenRequest av;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        av = ClearTokenRequest.CREATOR.av(data);
                    }
                    ClearTokenResponse clearToken = clearToken(av);
                    reply.writeNoException();
                    if (clearToken != null) {
                        reply.writeInt(1);
                        clearToken.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    AccountRemovalRequest ao;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        ao = AccountRemovalRequest.CREATOR.ao(data);
                    }
                    AccountRemovalResponse removeAccount = removeAccount(ao);
                    reply.writeNoException();
                    if (removeAccount != null) {
                        reply.writeInt(1);
                        removeAccount.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    ReauthSettingsRequest aI;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aI = ReauthSettingsRequest.CREATOR.aI(data);
                    }
                    ReauthSettingsResponse reauthSettings = getReauthSettings(aI);
                    reply.writeNoException();
                    if (reauthSettings != null) {
                        reply.writeInt(1);
                        reauthSettings.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    VerifyPinRequest aN;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aN = VerifyPinRequest.CREATOR.aN(data);
                    }
                    VerifyPinResponse verifyPin = verifyPin(aN);
                    reply.writeNoException();
                    if (verifyPin != null) {
                        reply.writeInt(1);
                        verifyPin.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    AccountChangeEventsRequest createFromParcel;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        createFromParcel = AccountChangeEventsRequest.CREATOR.createFromParcel(data);
                    }
                    AccountChangeEventsResponse accountChangeEvents = getAccountChangeEvents(createFromParcel);
                    reply.writeNoException();
                    if (accountChangeEvents != null) {
                        reply.writeInt(1);
                        accountChangeEvents.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    OtpRequest aC;
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        aC = OtpRequest.CREATOR.aC(data);
                    }
                    OtpResponse otp = getOtp(aC);
                    reply.writeNoException();
                    if (otp != null) {
                        reply.writeInt(1);
                        otp.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    String accountId = getAccountId(data.readString());
                    reply.writeNoException();
                    reply.writeString(accountId);
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    installAccountFromExportData = fL();
                    reply.writeNoException();
                    if (installAccountFromExportData) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                    data.enforceInterface("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    if (data.readInt() != 0) {
                        checkFactoryResetPolicyComplianceRequest = CheckFactoryResetPolicyComplianceRequest.CREATOR.ar(data);
                    }
                    CheckFactoryResetPolicyComplianceResponse a = a(checkFactoryResetPolicyComplianceRequest);
                    reply.writeNoException();
                    if (a != null) {
                        reply.writeInt(1);
                        a.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    CheckFactoryResetPolicyComplianceResponse a(CheckFactoryResetPolicyComplianceRequest checkFactoryResetPolicyComplianceRequest) throws RemoteException;

    AccountNameCheckResponse checkAccountName(AccountNameCheckRequest accountNameCheckRequest) throws RemoteException;

    PasswordCheckResponse checkPassword(PasswordCheckRequest passwordCheckRequest) throws RemoteException;

    CheckRealNameResponse checkRealName(CheckRealNameRequest checkRealNameRequest) throws RemoteException;

    ClearTokenResponse clearToken(ClearTokenRequest clearTokenRequest) throws RemoteException;

    TokenResponse confirmCredentials(ConfirmCredentialsRequest confirmCredentialsRequest) throws RemoteException;

    TokenResponse createAccount(GoogleAccountSetupRequest googleAccountSetupRequest) throws RemoteException;

    TokenResponse createPlusProfile(GoogleAccountSetupRequest googleAccountSetupRequest) throws RemoteException;

    boolean fL() throws RemoteException;

    AccountChangeEventsResponse getAccountChangeEvents(AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException;

    GoogleAccountData getAccountData(String str) throws RemoteException;

    Bundle getAccountExportData(String str) throws RemoteException;

    String getAccountId(String str) throws RemoteException;

    AccountRecoveryData getAccountRecoveryCountryInfo() throws RemoteException;

    AccountRecoveryData getAccountRecoveryData(AccountRecoveryDataRequest accountRecoveryDataRequest) throws RemoteException;

    AccountRecoveryGuidance getAccountRecoveryGuidance(AccountRecoveryGuidanceRequest accountRecoveryGuidanceRequest) throws RemoteException;

    GplusInfoResponse getGplusInfo(GplusInfoRequest gplusInfoRequest) throws RemoteException;

    OtpResponse getOtp(OtpRequest otpRequest) throws RemoteException;

    ReauthSettingsResponse getReauthSettings(ReauthSettingsRequest reauthSettingsRequest) throws RemoteException;

    TokenResponse getToken(TokenRequest tokenRequest) throws RemoteException;

    WebSetupConfig getWebSetupConfig(WebSetupConfigRequest webSetupConfigRequest) throws RemoteException;

    boolean installAccountFromExportData(String str, Bundle bundle) throws RemoteException;

    AccountRemovalResponse removeAccount(AccountRemovalRequest accountRemovalRequest) throws RemoteException;

    TokenResponse signIn(AccountSignInRequest accountSignInRequest) throws RemoteException;

    AccountRecoveryUpdateResult updateAccountRecoveryData(AccountRecoveryUpdateRequest accountRecoveryUpdateRequest) throws RemoteException;

    TokenResponse updateCredentials(UpdateCredentialsRequest updateCredentialsRequest) throws RemoteException;

    VerifyPinResponse verifyPin(VerifyPinRequest verifyPinRequest) throws RemoteException;
}
