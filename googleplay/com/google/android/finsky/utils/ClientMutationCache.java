package com.google.android.finsky.utils;

import android.content.Context;
import android.net.Uri;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.model.CirclesModel;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.finsky.utils.persistence.FileBasedKeyValueStore;
import com.google.android.finsky.utils.persistence.WriteThroughKeyValueStore;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClientMutationCache {
    private static final CachedReview DELETED_REVIEW;
    private static final int MAX_NUM_CACHED_CIRCLES_MODEL;
    private final String mAccount;
    private Boolean mAgeVerificationRequired;
    private List<CirclesModel> mCachedCirclesModels;
    private Set<String> mDismissedRecommendationDocIds;
    private Map<String, CachedReview> mReviewedDocIds;
    private WriteThroughKeyValueStore mUnsubmittedReviewsBackingStore;

    public static final class CachedReview {
        public final String docDetailsUrl;
        public final String docId;
        private final Review review;
        private boolean submitted;

        public CachedReview(String docId, int rating, String title, String comment, Document author, String docDetailsUrl, long timestamp) {
            this.review = new Review();
            this.submitted = false;
            this.review.starRating = rating;
            this.review.hasStarRating = true;
            this.review.title = title;
            this.review.hasTitle = true;
            this.review.comment = comment;
            this.review.hasComment = true;
            this.review.author = author.getBackingDocV2();
            this.review.timestampMsec = timestamp;
            this.review.hasTimestampMsec = true;
            this.docId = docId;
            this.docDetailsUrl = docDetailsUrl;
        }

        public Map<String, String> toMap() {
            Map<String, String> map = Maps.newHashMap();
            map.put("doc_id", this.docId);
            map.put("rating", this.review.starRating + "");
            map.put("title", this.review.title);
            map.put("content", this.review.comment);
            map.put("doc_details_url_key", this.docDetailsUrl);
            map.put("doc_timestamp", this.review.timestampMsec + "");
            return map;
        }

        public static CachedReview fromMap(Map<String, String> map, Document author) {
            if (map.containsKey("doc_id") && map.containsKey("rating") && map.containsKey("title") && map.containsKey("content") && map.containsKey("doc_details_url_key") && map.containsKey("doc_timestamp")) {
                try {
                    return new CachedReview((String) map.get("doc_id"), Integer.parseInt((String) map.get("rating")), (String) map.get("title"), (String) map.get("content"), author, (String) map.get("doc_details_url_key"), Long.parseLong((String) map.get("doc_timestamp")));
                } catch (NumberFormatException e) {
                    FinskyLog.e("Error parsing numbers from persisted cache: %s", e.toString());
                    return null;
                }
            }
            FinskyLog.e("Review badly persisted: %s", map.keySet().toString());
            return null;
        }

        public Review getReview() {
            return this.review;
        }

        public boolean isSubmitted() {
            return this.submitted;
        }

        public void setSubmitted(boolean isSubmitted) {
            this.submitted = isSubmitted;
        }
    }

    static {
        MAX_NUM_CACHED_CIRCLES_MODEL = ((Integer) G.clientMutationCacheCirclesModelCacheSize.get()).intValue();
        DELETED_REVIEW = null;
    }

    public ClientMutationCache(Context context, String account) {
        this.mReviewedDocIds = Maps.newHashMap();
        this.mDismissedRecommendationDocIds = Sets.newHashSet();
        this.mCachedCirclesModels = Lists.newArrayList();
        this.mAccount = account;
        this.mUnsubmittedReviewsBackingStore = new WriteThroughKeyValueStore(new FileBasedKeyValueStore(context.getCacheDir(), getCacheStoreId("unsubmitted_reviews_")));
        loadUnsubmittedReviewsCache();
    }

    public Review getCachedReview(String docId, Review defaultReview) {
        if (!this.mReviewedDocIds.containsKey(docId)) {
            return defaultReview;
        }
        CachedReview cachedReview = (CachedReview) this.mReviewedDocIds.get(docId);
        if (cachedReview == null) {
            return null;
        }
        return cachedReview.getReview();
    }

    public void removeCachedReview(String docId) {
        this.mUnsubmittedReviewsBackingStore.delete(docId);
        if (this.mReviewedDocIds.containsKey(docId)) {
            this.mReviewedDocIds.remove(docId);
        }
    }

    public void markReviewAsSubmitted(String docId) {
        this.mUnsubmittedReviewsBackingStore.delete(docId);
        if (this.mReviewedDocIds.containsKey(docId)) {
            ((CachedReview) this.mReviewedDocIds.get(docId)).setSubmitted(true);
        }
    }

    public void updateCachedReview(String docId, int rating, String title, String comment, Document author, String docDetailsUrl) {
        updateCachedReview(docId, rating, title, comment, author, docDetailsUrl, System.currentTimeMillis());
    }

    private void updateCachedReview(String docId, int rating, String title, String comment, Document author, String docDetailsUrl, long timestamp) {
        CachedReview review = new CachedReview(docId, rating, title, comment, author, docDetailsUrl, timestamp);
        this.mReviewedDocIds.put(docId, review);
        this.mUnsubmittedReviewsBackingStore.put(docId, review.toMap());
    }

    public void updateCachedReviewDeleted(String docId) {
        this.mReviewedDocIds.put(docId, DELETED_REVIEW);
        this.mUnsubmittedReviewsBackingStore.delete(docId);
    }

    public Collection<CachedReview> getUnsubmittedReviews() {
        Collection<CachedReview> unsubmittedReviews = new ArrayList();
        for (CachedReview r : this.mReviewedDocIds.values()) {
            if (!(r == DELETED_REVIEW || r.isSubmitted())) {
                unsubmittedReviews.add(r);
            }
        }
        return unsubmittedReviews;
    }

    public static void pruneUnsubmittedReviews(Context context) {
        try {
            File[] files = context.getCacheDir().listFiles();
            if (files != null) {
                long expireTime = System.currentTimeMillis() - ((Long) G.unsubmittedReviewLifespanMs.get()).longValue();
                for (File file : files) {
                    if (file.getName().startsWith("unsubmitted_reviews_") && (file.length() == 0 || file.lastModified() < expireTime)) {
                        file.delete();
                    }
                }
            }
        } catch (Exception e) {
            FinskyLog.e("Error pruning unsubmitted reviews: %s", e.toString());
        }
    }

    public void setAgeVerificationRequired(boolean ageVerificationRequired) {
        this.mAgeVerificationRequired = Boolean.valueOf(ageVerificationRequired);
    }

    public boolean isAgeVerificationRequired() {
        if (this.mAgeVerificationRequired == null) {
            this.mAgeVerificationRequired = Boolean.valueOf(FinskyApp.get().getToc().isAgeVerificationRequired());
        }
        return this.mAgeVerificationRequired.booleanValue();
    }

    public boolean isDismissedRecommendation(String docId) {
        return this.mDismissedRecommendationDocIds.contains(docId);
    }

    public void dismissRecommendation(String docId) {
        this.mDismissedRecommendationDocIds.add(docId);
    }

    private void putCirclesModel(CirclesModel circlesModel) {
        if (this.mCachedCirclesModels.size() >= MAX_NUM_CACHED_CIRCLES_MODEL) {
            this.mCachedCirclesModels.remove(0);
        }
        this.mCachedCirclesModels.add(circlesModel);
    }

    public CirclesModel getCachedCirclesModel(Document targetPerson, String ownerAccountName) {
        for (int i = 0; i < this.mCachedCirclesModels.size(); i++) {
            CirclesModel cachedModel = (CirclesModel) this.mCachedCirclesModels.get(i);
            if (cachedModel.getTargetPersonDoc().getDocId().equals(targetPerson.getDocId()) && cachedModel.getOwnerAccountName().equals(ownerAccountName)) {
                this.mCachedCirclesModels.remove(i);
                this.mCachedCirclesModels.add(cachedModel);
                return cachedModel;
            }
        }
        CirclesModel circlesModel = new CirclesModel(targetPerson, ownerAccountName);
        putCirclesModel(circlesModel);
        return circlesModel;
    }

    private void loadUnsubmittedReviewsCache() {
        this.mUnsubmittedReviewsBackingStore.load(new Runnable() {
            public void run() {
                final Collection<Map<String, String>> unsubmittedReviewMaps = ClientMutationCache.this.mUnsubmittedReviewsBackingStore.fetchAll().values();
                if (!unsubmittedReviewMaps.isEmpty()) {
                    FinskyApp.get().getPlayDfeApi(ClientMutationCache.this.mAccount).getPlusProfile(new Listener<PlusProfileResponse>() {
                        public void onResponse(PlusProfileResponse response) {
                            if (response.partialUserProfile == null) {
                                FinskyLog.e("Error getting user's g+ profile: %s", FinskyLog.scrubPii(ClientMutationCache.this.mAccount));
                                return;
                            }
                            Document author = new Document(response.partialUserProfile);
                            for (Map<String, String> map : unsubmittedReviewMaps) {
                                CachedReview cachedReview = CachedReview.fromMap(map, author);
                                if (cachedReview != null) {
                                    ClientMutationCache.this.mReviewedDocIds.put(cachedReview.docId, cachedReview);
                                }
                            }
                        }
                    }, new ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            FinskyLog.w("Connectivity error deserializing reviews: %s", volleyError.toString());
                        }
                    }, false);
                }
            }
        });
    }

    private String getCacheStoreId(String filePrefix) {
        return filePrefix + Uri.encode(this.mAccount);
    }
}
