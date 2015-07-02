package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.details.IDetailsService.Stub;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.billing.lightpurchase.LightPurchaseFlowActivity;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Details.DetailsResponse;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.PurchaseButtonHelper;
import com.google.android.finsky.utils.PurchaseButtonHelper.DocumentAction;
import com.google.android.finsky.utils.PurchaseButtonHelper.DocumentActions;
import com.google.android.finsky.utils.PurchaseButtonHelper.TextStyle;
import com.google.android.finsky.utils.SignatureUtils;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class DetailsService extends Service {
    public IBinder onBind(Intent intent) {
        return new Stub() {
            public Bundle getAppDetails(String packageName) throws RemoteException {
                FinskyApp finskyApp = FinskyApp.get();
                Account currentAccount = finskyApp.getCurrentAccount();
                if (currentAccount == null) {
                    return null;
                }
                FinskyEventLog eventLog = finskyApp.getEventLogger(currentAccount);
                if (((Boolean) G.enableDetailsApi.get()).booleanValue()) {
                    String callingApp = SignatureUtils.getCallingAppIfAuthorized(DetailsService.this, packageName, G.enableThirdPartyDetailsApi, eventLog, 512);
                    if (callingApp == null) {
                        return null;
                    }
                    FinskyLog.d("Received app details request for %s from %s", packageName, callingApp);
                    String detailsUrl = DfeUtils.createDetailsUrlFromId(packageName);
                    DfeApi dfeApi = FinskyApp.get().getDfeApi();
                    RequestFuture<DetailsResponse> future = RequestFuture.newFuture();
                    dfeApi.getDetails(detailsUrl, true, true, null, future, future);
                    try {
                        DocV2 doc = ((DetailsResponse) future.get()).docV2;
                        if (doc == null) {
                            FinskyLog.d("No doc in details response for %s", packageName);
                            SignatureUtils.logEvent(eventLog, 512, packageName, "empty-details-response", callingApp, null);
                            return null;
                        }
                        DfeToc dfeToc = finskyApp.getToc();
                        Installer installer = finskyApp.getInstaller();
                        AppStates appStates = finskyApp.getAppStates();
                        appStates.blockingLoad();
                        Libraries libraries = finskyApp.getLibraries();
                        libraries.blockingLoad();
                        return DetailsService.getBundle(DetailsService.this, new Document(doc), currentAccount, dfeToc, installer, appStates, libraries, callingApp, eventLog);
                    } catch (InterruptedException e) {
                        FinskyLog.d("Interrupted while trying to retrieve app details", new Object[0]);
                        SignatureUtils.logEvent(eventLog, 512, packageName, "fetch-interrupted", callingApp, null);
                        return null;
                    } catch (ExecutionException e2) {
                        String exceptionType;
                        Throwable cause = e2.getCause();
                        if (cause == null) {
                            exceptionType = null;
                        } else {
                            exceptionType = cause.getClass().getSimpleName();
                        }
                        FinskyLog.d("Unable to retrieve app details: %s", exceptionType);
                        SignatureUtils.logEvent(eventLog, 512, packageName, "fetch-error", callingApp, exceptionType);
                        return null;
                    }
                }
                FinskyLog.d("API access is blocked for all apps", new Object[0]);
                SignatureUtils.logEvent(eventLog, 512, packageName, "all-access-blocked", null, null);
                return null;
            }
        };
    }

    public static Bundle getBundle(Context context, Document doc, Account currentAccount, DfeToc dfeToc, Installer installer, AppStates appStates, Libraries libraries, String callingApp, FinskyEventLog eventLog) {
        String packageName = doc.getBackendDocId();
        int backend = doc.getBackend();
        if (backend != 3) {
            FinskyLog.d("Document %s is not an app, backend=%d", packageName, Integer.valueOf(backend));
            SignatureUtils.logEvent(eventLog, 512, packageName, "doc-backend", callingApp, null);
            return null;
        }
        if (doc.getDocumentType() != 1) {
            FinskyLog.d("Document %s is not an app, doc_type=%d", packageName, Integer.valueOf(doc.getDocumentType()));
            SignatureUtils.logEvent(eventLog, 512, packageName, "doc-type", callingApp, null);
            return null;
        }
        DocumentActions actions = new DocumentActions();
        PurchaseButtonHelper.getDocumentActions(currentAccount, installer, libraries, appStates, dfeToc, doc, actions);
        DocumentAction action = getBuyOrInstallAction(actions);
        if (action == null) {
            FinskyLog.d("App %s has no buy or install action, actions=%s", packageName, actions.toString());
            SignatureUtils.logEvent(eventLog, 512, packageName, "doc-actions", callingApp, null);
            return null;
        }
        Resources res = context.getResources();
        Locale locale = res.getConfiguration().locale;
        Bundle response = new Bundle();
        response.putString("title", doc.getTitle());
        Bundle bundle = response;
        bundle.putString("creator", doc.getCreator().toUpperCase(locale));
        if (doc.hasRating()) {
            response.putFloat("star_rating", doc.getStarRating());
            response.putLong("rating_count", doc.getRatingCount());
        }
        if (!addImage(doc, 4, response)) {
            if (addImage(doc, 0, response)) {
                FinskyLog.d("App %s using thumbnail image", doc.getDocId());
            } else {
                FinskyLog.d("App %s failed to find any image", doc.getDocId());
            }
        }
        TextStyle actionStyle = new TextStyle();
        PurchaseButtonHelper.getActionStyleWithoutBuyPrefix(action, backend, actionStyle);
        bundle = response;
        bundle.putString("purchase_button_text", actionStyle.getString(res).toUpperCase(locale));
        Intent purchaseIntent = LightPurchaseFlowActivity.createExternalPurchaseIntent(doc, 1);
        purchaseIntent.setData(Uri.fromParts("detailsservice", packageName, null));
        bundle = response;
        bundle.putParcelable("purchase_intent", PendingIntent.getActivity(context, 0, purchaseIntent, 0));
        bundle = response;
        bundle.putParcelable("details_intent", PendingIntent.getActivity(context, 0, createDetailsIntent(context, packageName, callingApp), 0));
        SignatureUtils.logEvent(eventLog, 512, packageName, null, callingApp, null);
        return response;
    }

    private static Intent createDetailsIntent(Context context, String packageName, String callingApp) {
        Uri detailsUri = new Builder().scheme("market").authority("details").appendQueryParameter("id", packageName).appendQueryParameter("api_source", "DetailsService").appendQueryParameter("referrer_package", callingApp).build();
        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction("android.intent.action.VIEW");
        intent.setData(detailsUri);
        return intent;
    }

    private static DocumentAction getBuyOrInstallAction(DocumentActions actions) {
        for (int i = 0; i < actions.actionCount; i++) {
            DocumentAction action = actions.getAction(i);
            if (action.actionType == 7 || action.actionType == 1) {
                return action;
            }
        }
        return null;
    }

    private static boolean addImage(Document doc, int imageType, Bundle response) {
        List<Image> images = doc.getImages(imageType);
        if (!(images == null || images.isEmpty())) {
            Image image = (Image) images.get(0);
            if (image.hasImageUrl) {
                String imageKey;
                if (image.hasSupportsFifeUrlOptions && image.supportsFifeUrlOptions) {
                    imageKey = "fife_url";
                } else {
                    FinskyLog.d("App %s no FIFE URL for %d", doc.getDocId(), Integer.valueOf(imageType));
                    imageKey = "image_url";
                }
                response.putString(imageKey, image.imageUrl);
                return true;
            }
        }
        return false;
    }
}
