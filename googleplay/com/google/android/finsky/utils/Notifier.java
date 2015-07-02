package com.google.android.finsky.utils;

import android.content.Intent;
import com.google.android.finsky.api.model.Document;
import java.util.List;

public interface Notifier {
    void hideAllMessagesForPackage(String str);

    void hideInstallingMessage();

    void hidePackageRemoveRequestMessage(String str);

    void hidePackageRemovedMessage(String str);

    void hideUpdatesAvailableMessage();

    void setNotificationListener(NotificationListener notificationListener);

    void showDownloadErrorMessage(String str, String str2, int i, String str3, boolean z);

    void showExternalStorageFull(String str, String str2);

    void showExternalStorageMissing(String str, String str2);

    void showHarmfulAppRemoveRequestMessage(String str, String str2, String str3, byte[] bArr);

    void showHarmfulAppRemovedMessage(String str, String str2, String str3);

    void showInstallationFailureMessage(String str, String str2, String str3, int i);

    void showInstallingMessage(String str, String str2, boolean z);

    void showInternalStorageFull(String str, String str2);

    void showMaliciousAssetRemovedMessage(String str, String str2);

    void showMessage(String str, String str2, String str3);

    void showNewUpdatesAvailableMessage(List<Document> list, int i);

    void showNormalAssetRemovedMessage(String str, String str2);

    void showNotification(String str, String str2, String str3, String str4, int i, Intent intent, String str5);

    void showOutstandingUpdatesMessage(List<Document> list);

    void showPurchaseErrorMessage(String str, String str2, String str3, String str4, String str5);

    void showSubscriptionsWarningMessage(String str, String str2, String str3);

    void showSuccessfulInstallMessage(String str, String str2, String str3, boolean z);

    void showUpdatesNeedApprovalMessage(List<Document> list);
}
