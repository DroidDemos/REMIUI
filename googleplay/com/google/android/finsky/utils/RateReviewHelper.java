package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.NetworkError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.finsky.protos.Rev.ReviewResponse;
import com.google.android.finsky.utils.ClientMutationCache.CachedReview;
import java.util.Collection;

public class RateReviewHelper {
    private static final int[] DESCRIPTION_MAP;

    public interface RateReviewListener {
        void onRateReviewCommitted(int i, String str, String str2);

        void onRateReviewFailed();
    }

    public interface CheckAndConfirmGPlusListener {
        void onCheckAndConfirmGPlusFailed();

        void onCheckAndConfirmGPlusPassed(Document document);
    }

    static {
        DESCRIPTION_MAP = new int[]{R.string.review_dialog_unrated, R.string.review_dialog_poor, R.string.review_dialog_below_average, R.string.review_dialog_average, R.string.review_dialog_above_average, R.string.review_dialog_excellent};
    }

    public static void checkAndConfirmGPlus(final FragmentActivity activity, final CheckAndConfirmGPlusListener listener) {
        FinskyApp.get().getPlayDfeApi().getPlusProfile(new Listener<PlusProfileResponse>() {
            public void onResponse(PlusProfileResponse response) {
                if (!activity.isFinishing()) {
                    DocV2 plusDocV2 = response.partialUserProfile;
                    if (plusDocV2 == null) {
                        GPlusDialogsHelper.showGPlusSignUpAndPublicReviewsDialog(activity.getSupportFragmentManager());
                        listener.onCheckAndConfirmGPlusFailed();
                    } else if (GPlusDialogsHelper.showPublicReviewsDialog(activity.getSupportFragmentManager())) {
                        listener.onCheckAndConfirmGPlusFailed();
                    } else {
                        listener.onCheckAndConfirmGPlusPassed(new Document(plusDocV2));
                    }
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError e) {
                FinskyLog.e("Error getting G+ profile: %s", e.toString());
                RateReviewHelper.showReviewError(activity, null);
                listener.onCheckAndConfirmGPlusFailed();
            }
        }, true);
    }

    public static void updateReview(String accountName, String docId, String docDetailsUrl, int rating, String title, String content, Document author, Context context, RateReviewListener rateListener, int reviewSource) {
        ClientMutationCache clientMutationCache = FinskyApp.get().getClientMutationCache(accountName);
        if (TextUtils.isEmpty(content) && !TextUtils.isEmpty(title)) {
            content = title;
            title = "";
        }
        String finalTitle = title;
        String finalContent = content;
        clientMutationCache.updateCachedReview(docId, rating, finalTitle, finalContent, author, docDetailsUrl);
        sendReviewToServer(accountName, docId, docDetailsUrl, rating, finalTitle, finalContent, context, rateListener);
        FinskyEventLog eventLogger = FinskyApp.get().getEventLogger(accountName);
        boolean z = (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) ? false : true;
        eventLogger.logReviewAdded(reviewSource, rating, z);
    }

    private static void sendReviewToServer(String accountName, final String docId, String docDetailsUrl, int rating, String finalTitle, String finalContent, Context context, RateReviewListener rateListener) {
        final DfeApi dfeApi = FinskyApp.get().getDfeApi(accountName);
        final ClientMutationCache clientMutationCache = FinskyApp.get().getClientMutationCache(accountName);
        final String str = docId;
        final String str2 = docDetailsUrl;
        final RateReviewListener rateReviewListener = rateListener;
        final int i = rating;
        final String str3 = finalTitle;
        final String str4 = finalContent;
        AnonymousClass3 anonymousClass3 = new Listener<ReviewResponse>() {
            public void onResponse(ReviewResponse response) {
                clientMutationCache.markReviewAsSubmitted(str);
                dfeApi.invalidateDetailsCache(str2, true);
                MyPeoplePageHelper.onMutationOccurred(dfeApi);
                if (rateReviewListener != null) {
                    rateReviewListener.onRateReviewCommitted(i, str3, str4);
                }
            }
        };
        final Context context2 = context;
        final RateReviewListener rateReviewListener2 = rateListener;
        dfeApi.addReview(docId, finalTitle, finalContent, rating, true, anonymousClass3, new ErrorListener() {
            public void onErrorResponse(VolleyError e) {
                if (!(e instanceof NetworkError)) {
                    clientMutationCache.removeCachedReview(docId);
                    FinskyLog.e("Error posting review: %s", e.toString());
                    RateReviewHelper.showReviewError(context2, ErrorStrings.get(context2, e));
                }
                if (rateReviewListener2 != null) {
                    rateReviewListener2.onRateReviewFailed();
                }
            }
        });
    }

    public static void updateUnsubmittedReviews(String accountName, Context context) {
        Collection<CachedReview> unsubmittedReviews = FinskyApp.get().getClientMutationCache(accountName).getUnsubmittedReviews();
        if (!unsubmittedReviews.isEmpty()) {
            for (CachedReview cachedReview : unsubmittedReviews) {
                FinskyLog.w("Sending usubmitted review for account: %s and docId: %s", FinskyLog.scrubPii(accountName), cachedReview.docId);
                Review r = cachedReview.getReview();
                sendReviewToServer(accountName, cachedReview.docId, cachedReview.docDetailsUrl, r.starRating, r.title, r.comment, context, null);
            }
        }
    }

    public static void deleteReview(String accountName, final String docId, final String docDetailsUrl, final Context context, final RateReviewListener listener) {
        final ClientMutationCache clientMutationCache = FinskyApp.get().getClientMutationCache(accountName);
        clientMutationCache.updateCachedReviewDeleted(docId);
        final DfeApi dfeApi = FinskyApp.get().getDfeApi(accountName);
        dfeApi.deleteReview(docId, new Listener<ReviewResponse>() {
            public void onResponse(ReviewResponse response) {
                dfeApi.invalidateDetailsCache(docDetailsUrl, true);
                MyPeoplePageHelper.onMutationOccurred(dfeApi);
                if (listener != null) {
                    listener.onRateReviewCommitted(-1, "", "");
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                clientMutationCache.removeCachedReview(docId);
                FinskyLog.e("Error posting review: %s", error.toString());
                RateReviewHelper.showReviewDeleteError(context);
                if (listener != null) {
                    listener.onRateReviewFailed();
                }
            }
        });
    }

    public static void rateDocument(String accountName, String docId, String docDetailsUrl, int rating, FragmentActivity activity, RateReviewListener rateListener, int reviewSource) {
        final String str = accountName;
        final String str2 = docId;
        final int i = rating;
        final String str3 = docDetailsUrl;
        final RateReviewListener rateReviewListener = rateListener;
        final FragmentActivity fragmentActivity = activity;
        final int i2 = reviewSource;
        checkAndConfirmGPlus(activity, new CheckAndConfirmGPlusListener() {
            public void onCheckAndConfirmGPlusPassed(final Document plusDoc) {
                if (((Boolean) FinskyPreferences.internalFakeItemRaterEnabled.get()).booleanValue()) {
                    new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                        public void run() {
                            FinskyApp.get().getClientMutationCache(str).updateCachedReview(str2, i, "", "", plusDoc, str3);
                            rateReviewListener.onRateReviewCommitted(i, "", "");
                        }
                    }, (long) (((int) (Math.random() * 1500.0d)) + 500));
                    return;
                }
                RateReviewHelper.updateReview(str, str2, str3, i, "", "", plusDoc, fragmentActivity, rateReviewListener, i2);
            }

            public void onCheckAndConfirmGPlusFailed() {
                if (rateReviewListener != null) {
                    rateReviewListener.onRateReviewFailed();
                }
            }
        });
    }

    private static void showReviewError(Context context, String serverMessage) {
        if (context != null) {
            CharSequence text;
            if (TextUtils.isEmpty(serverMessage)) {
                text = context.getResources().getText(R.string.review_error);
            } else {
                Object text2 = serverMessage;
            }
            Toast.makeText(context, text, 0).show();
        }
    }

    private static void showReviewDeleteError(Context context) {
        if (context != null) {
            Toast.makeText(context, R.string.review_deleted_error, 0).show();
        }
    }

    public static int getRatingDescription(int rating) {
        return DESCRIPTION_MAP[rating];
    }
}
