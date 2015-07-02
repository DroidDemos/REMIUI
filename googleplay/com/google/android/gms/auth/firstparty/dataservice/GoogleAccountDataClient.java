package com.google.android.gms.auth.firstparty.dataservice;

public interface GoogleAccountDataClient {
    TokenResponse confirmCredentials(ConfirmCredentialsRequest confirmCredentialsRequest);

    ReauthSettingsResponse getReauthSettings(ReauthSettingsRequest reauthSettingsRequest);

    VerifyPinResponse verifyPin(VerifyPinRequest verifyPinRequest);
}
