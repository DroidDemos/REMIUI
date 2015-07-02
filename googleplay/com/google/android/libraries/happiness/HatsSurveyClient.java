package com.google.android.libraries.happiness;

public interface HatsSurveyClient {
    void onSurveyCanceled();

    void onSurveyComplete(boolean z, boolean z2);

    void onSurveyReady();

    void onSurveyResponse(String str, String str2);

    void onWindowError();
}
