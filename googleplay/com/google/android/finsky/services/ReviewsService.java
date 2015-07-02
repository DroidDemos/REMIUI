package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.reviews.IReviewsService.Stub;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.RateReviewActivity;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Details.DetailsResponse;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.SignatureUtils;
import com.google.android.play.dfe.api.PlayDfeApi;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class ReviewsService extends Service {
    private static AtomicInteger sRequestCounter;
    private final Stub mBinder;

    public ReviewsService() {
        this.mBinder = new Stub() {
            public Bundle getRateAndReviewIntent(String accountName, String documentId) throws RemoteException {
                Throwable cause;
                String exceptionType;
                FinskyApp finskyApp = FinskyApp.get();
                Account accountToUse = null;
                for (Account deviceAccount : AccountHandler.getAccounts(finskyApp)) {
                    if (deviceAccount.name.equals(accountName)) {
                        accountToUse = deviceAccount;
                        break;
                    }
                }
                if (accountToUse == null) {
                    FinskyLog.d("No account found for %s", FinskyLog.scrubPii(accountName));
                    return null;
                }
                FinskyEventLog eventLog = finskyApp.getEventLogger(accountName);
                String callingApp = SignatureUtils.getCallingAppIfAuthorized(ReviewsService.this, documentId, null, eventLog, 514);
                if (callingApp == null) {
                    return null;
                }
                FinskyLog.d("Received rate&review request for %s from %s", documentId, callingApp);
                Bundle response = new Bundle();
                DfeApi dfeApi = FinskyApp.get().getDfeApi(accountName);
                PlayDfeApi playDfeApi = FinskyApp.get().getPlayDfeApi();
                RequestFuture<PlusProfileResponse> futurePlusCheck = RequestFuture.newFuture();
                playDfeApi.getPlusProfile(futurePlusCheck, futurePlusCheck, true);
                try {
                    DocV2 plusDocV2 = ((PlusProfileResponse) futurePlusCheck.get()).partialUserProfile;
                    if (!((Boolean) FinskyPreferences.acceptedPlusReviews.get(accountName).get()).booleanValue() || plusDocV2 == null) {
                        return response;
                    }
                    Document document = new Document(plusDocV2);
                    RequestFuture<DetailsResponse> futureDetails = RequestFuture.newFuture();
                    dfeApi.getDetails(DfeUtils.createDetailsUrlFromId(documentId), true, true, null, futureDetails, futureDetails);
                    try {
                        DetailsResponse responseDetails = (DetailsResponse) futureDetails.get();
                        DocV2 doc = responseDetails.docV2;
                        if (doc == null) {
                            FinskyLog.d("No doc in details response for %s", documentId);
                            return response;
                        }
                        populateResponseBundle(response, accountName, document, doc, finskyApp.getClientMutationCache(accountName).getCachedReview(documentId, responseDetails.userReview));
                        SignatureUtils.logEvent(eventLog, 514, documentId, null, callingApp, null);
                        return response;
                    } catch (InterruptedException e) {
                        FinskyLog.d("Interrupted while trying to retrieve item details", new Object[0]);
                        return response;
                    } catch (ExecutionException e2) {
                        cause = e2.getCause();
                        if (cause == null) {
                            exceptionType = null;
                        } else {
                            exceptionType = cause.getClass().getSimpleName();
                        }
                        FinskyLog.d("Unable to retrieve item details: %s", exceptionType);
                        SignatureUtils.logEvent(eventLog, 514, documentId, "fetch-doc-error", callingApp, exceptionType);
                        return response;
                    }
                } catch (InterruptedException e3) {
                    FinskyLog.d("Interrupted while trying to retrieve plus profile", new Object[0]);
                    return response;
                } catch (ExecutionException e22) {
                    cause = e22.getCause();
                    if (cause == null) {
                        exceptionType = null;
                    } else {
                        exceptionType = cause.getClass().getSimpleName();
                    }
                    FinskyLog.d("Unable to retrieve plus profile: %s", exceptionType);
                    SignatureUtils.logEvent(eventLog, 514, documentId, "fetch-plus-error", callingApp, exceptionType);
                    return response;
                }
            }

            private void populateResponseBundle(Bundle response, String accountName, Document plusProfileDoc, DocV2 doc, Review review) {
                int i;
                Document document = new Document(doc);
                if (review != null) {
                    i = review.starRating;
                } else {
                    i = 0;
                }
                Intent intent = RateReviewActivity.createIntent(accountName, document, plusProfileDoc, review, i, true);
                intent.setData(Uri.fromParts("reviewsservice", doc.docid, Integer.toString(ReviewsService.sRequestCounter.getAndIncrement())));
                response.putParcelable("rate_and_review_intent", PendingIntent.getActivity(ReviewsService.this, 0, intent, 1073741824));
                response.putInt("rate_and_review_request_code", 43);
                response.putString("doc_id", doc.docid);
                response.putString("doc_title", doc.title);
                if (review != null) {
                    response.putInt("rating", review.starRating);
                    response.putString("review_title", review.title);
                    response.putString("review_comment", review.comment);
                }
                response.putString("author_title", plusProfileDoc.getTitle());
                response.putString("author_profile_image_url", ((Image) plusProfileDoc.getImages(4).get(0)).imageUrl);
            }
        };
    }

    static {
        sRequestCounter = new AtomicInteger(0);
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
