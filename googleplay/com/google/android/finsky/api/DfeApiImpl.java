package com.google.android.finsky.api;

import android.accounts.Account;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi.DfePostRequest;
import com.google.android.finsky.api.DfeApi.GaiaAuthParameters;
import com.google.android.finsky.api.DfeApi.IabParameters;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.lightpurchase.VoucherParams;
import com.google.android.finsky.protos.AckNotification.AckNotificationResponse;
import com.google.android.finsky.protos.Browse.BrowseResponse;
import com.google.android.finsky.protos.Buy.BuyResponse;
import com.google.android.finsky.protos.BuyInstruments.BillingProfileResponse;
import com.google.android.finsky.protos.BuyInstruments.CheckIabPromoResponse;
import com.google.android.finsky.protos.BuyInstruments.CheckInstrumentResponse;
import com.google.android.finsky.protos.BuyInstruments.CreateInstrumentRequest;
import com.google.android.finsky.protos.BuyInstruments.CreateInstrumentResponse;
import com.google.android.finsky.protos.BuyInstruments.GetInitialInstrumentFlowStateResponse;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.protos.CarrierBilling.InitiateAssociationResponse;
import com.google.android.finsky.protos.CarrierBilling.VerifyAssociationResponse;
import com.google.android.finsky.protos.ChallengeAction.ChallengeResponse;
import com.google.android.finsky.protos.CheckPromoOffer.CheckPromoOfferResponse;
import com.google.android.finsky.protos.ConsumePurchaseResponse;
import com.google.android.finsky.protos.ContentFlagging.FlagContentResponse;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentFlowHandle;
import com.google.android.finsky.protos.DebugSettings.DebugSettingsResponse;
import com.google.android.finsky.protos.Delivery.DeliveryResponse;
import com.google.android.finsky.protos.Details.BulkDetailsRequest;
import com.google.android.finsky.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.protos.Details.DetailsResponse;
import com.google.android.finsky.protos.DeviceConfigurationProto;
import com.google.android.finsky.protos.DocList.ListResponse;
import com.google.android.finsky.protos.EarlyUpdate.EarlyUpdateRequest;
import com.google.android.finsky.protos.EarlyUpdate.EarlyUpdateResponse;
import com.google.android.finsky.protos.LibraryReplication.LibraryReplicationRequest;
import com.google.android.finsky.protos.LibraryReplication.LibraryReplicationResponse;
import com.google.android.finsky.protos.Log.LogRequest;
import com.google.android.finsky.protos.Log.LogResponse;
import com.google.android.finsky.protos.ModifyLibrary.ModifyLibraryRequest;
import com.google.android.finsky.protos.ModifyLibrary.ModifyLibraryResponse;
import com.google.android.finsky.protos.MyAccount.MyAccountResponse;
import com.google.android.finsky.protos.PlusOne.PlusOneResponse;
import com.google.android.finsky.protos.Preloads.PreloadsResponse;
import com.google.android.finsky.protos.PromoCode.RedeemCodeRequest;
import com.google.android.finsky.protos.PromoCode.RedeemCodeResponse;
import com.google.android.finsky.protos.Purchase.CommitPurchaseResponse;
import com.google.android.finsky.protos.Purchase.PreparePurchaseResponse;
import com.google.android.finsky.protos.RateSuggestedContentResponse;
import com.google.android.finsky.protos.ResolveLink.ResolvedLink;
import com.google.android.finsky.protos.Restore.GetBackupDeviceChoicesResponse;
import com.google.android.finsky.protos.Restore.GetBackupDocumentChoicesResponse;
import com.google.android.finsky.protos.Rev.ReviewResponse;
import com.google.android.finsky.protos.RevokeResponse;
import com.google.android.finsky.protos.Search.SearchResponse;
import com.google.android.finsky.protos.SearchSuggest.SearchSuggestResponse;
import com.google.android.finsky.protos.SelfUpdate.SelfUpdateResponse;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.protos.Tos.AcceptTosResponse;
import com.google.android.finsky.protos.UploadDeviceConfig.UploadDeviceConfigRequest;
import com.google.android.finsky.protos.UploadDeviceConfig.UploadDeviceConfigResponse;
import com.google.android.wallet.instrumentmanager.pub.InstrumentManagerUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DfeApiImpl implements DfeApi {
    private static final float ADD_REVIEW_BACKOFF_MULT;
    private static final int ADD_REVIEW_MAX_RETRIES;
    private static final int ADD_REVIEW_TIMEOUT_MS;
    private static final int AGE_VERIFICATION_TIMEOUT_MS;
    private static final float BACKUP_DEVICES_BACKOFF_MULT;
    private static final int BACKUP_DEVICES_MAX_RETRIES;
    private static final int BACKUP_DEVICES_TIMEOUT_MS;
    private static final float BULK_DETAILS_BACKOFF_MULT;
    private static final int BULK_DETAILS_MAX_RETRIES;
    private static final int BULK_DETAILS_TIMEOUT_MS;
    private static final float EARLY_BACKOFF_MULT;
    private static final int EARLY_MAX_RETRIES;
    private static final int EARLY_TIMEOUT_MS;
    private static final int PURCHASE_TIMEOUT_MS;
    private static final float REPLICATE_LIBRARY_BACKOFF_MULT;
    private static final int REPLICATE_LIBRARY_MAX_RETRIES;
    private static final int REPLICATE_LIBRARY_TIMEOUT_MS;
    private static final boolean SEND_AD_ID_IN_REQUESTS_FOR_RADS;
    private static final boolean SEND_PUBLIC_ANDROID_ID_IN_REQUESTS_FOR_RADS;
    private static final int VERIFY_ASSOCIATION_MAX_RETRIES;
    private static final int VERIFY_ASSOCIATION_TIMEOUT_MS;
    private final DfeApiContext mApiContext;
    private final RequestQueue mQueue;

    private class TocListener implements Listener<TocResponse> {
        private final Listener<TocResponse> mListener;

        public TocListener(Listener<TocResponse> listener) {
            this.mListener = listener;
        }

        public void onResponse(TocResponse response) {
            if (!(DfeApiImpl.this.mApiContext.getExperiments() == null || response.experiments == null)) {
                DfeApiImpl.this.mApiContext.getExperiments().setExperiments(response.experiments.experimentId);
            }
            if (this.mListener != null) {
                this.mListener.onResponse(response);
            }
        }
    }

    static {
        PURCHASE_TIMEOUT_MS = ((Integer) DfeApiConfig.purchaseStatusTimeoutMs.get()).intValue();
        SEND_PUBLIC_ANDROID_ID_IN_REQUESTS_FOR_RADS = ((Boolean) DfeApiConfig.sendPublicAndroidIdInRequestsForRads.get()).booleanValue();
        SEND_AD_ID_IN_REQUESTS_FOR_RADS = ((Boolean) DfeApiConfig.sendAdIdInRequestsForRads.get()).booleanValue();
        AGE_VERIFICATION_TIMEOUT_MS = ((Integer) DfeApiConfig.ageVerificationTimeoutMs.get()).intValue();
        BACKUP_DEVICES_TIMEOUT_MS = ((Integer) DfeApiConfig.backupDevicesTimeoutMs.get()).intValue();
        BACKUP_DEVICES_MAX_RETRIES = ((Integer) DfeApiConfig.backupDevicesMaxRetries.get()).intValue();
        BACKUP_DEVICES_BACKOFF_MULT = ((Float) DfeApiConfig.backupDevicesBackoffMultiplier.get()).floatValue();
        BULK_DETAILS_TIMEOUT_MS = ((Integer) DfeApiConfig.bulkDetailsTimeoutMs.get()).intValue();
        BULK_DETAILS_MAX_RETRIES = ((Integer) DfeApiConfig.bulkDetailsMaxRetries.get()).intValue();
        BULK_DETAILS_BACKOFF_MULT = ((Float) DfeApiConfig.bulkDetailsBackoffMultiplier.get()).floatValue();
        VERIFY_ASSOCIATION_TIMEOUT_MS = ((Integer) DfeApiConfig.verifyAssociationTimeoutMs.get()).intValue();
        VERIFY_ASSOCIATION_MAX_RETRIES = ((Integer) DfeApiConfig.verifyAssociationMaxRetries.get()).intValue();
        REPLICATE_LIBRARY_TIMEOUT_MS = ((Integer) DfeApiConfig.replicateLibraryTimeoutMs.get()).intValue();
        REPLICATE_LIBRARY_MAX_RETRIES = ((Integer) DfeApiConfig.replicateLibraryMaxRetries.get()).intValue();
        REPLICATE_LIBRARY_BACKOFF_MULT = ((Float) DfeApiConfig.replicateLibraryBackoffMultiplier.get()).floatValue();
        EARLY_TIMEOUT_MS = ((Integer) DfeApiConfig.earlyUpdateTimeoutMs.get()).intValue();
        EARLY_MAX_RETRIES = ((Integer) DfeApiConfig.earlyUpdateMaxRetries.get()).intValue();
        EARLY_BACKOFF_MULT = ((Float) DfeApiConfig.earlyUpdateBackoffMultiplier.get()).floatValue();
        ADD_REVIEW_TIMEOUT_MS = ((Integer) DfeApiConfig.addReviewTimeoutMs.get()).intValue();
        ADD_REVIEW_MAX_RETRIES = ((Integer) DfeApiConfig.addReviewMaxRetries.get()).intValue();
        ADD_REVIEW_BACKOFF_MULT = ((Float) DfeApiConfig.addReviewBackoffMultiplier.get()).floatValue();
    }

    public DfeApiImpl(RequestQueue queue, DfeApiContext apiContext) {
        this.mQueue = queue;
        this.mApiContext = apiContext;
    }

    public DfeApiContext getApiContext() {
        return this.mApiContext;
    }

    public Account getAccount() {
        return this.mApiContext.getAccount();
    }

    public String getAccountName() {
        return this.mApiContext.getAccountName();
    }

    public Request<?> getToc(boolean allowDouble, String deviceConfigurationToken, Listener<TocResponse> listener, ErrorListener errorListener) {
        DfeRequest<TocResponse> request = new DfeRequest(CHANNELS_URI.toString(), this.mApiContext, TocResponse.class, new TocListener(listener), errorListener);
        request.setAllowMultipleResponses(allowDouble);
        if (!TextUtils.isEmpty(deviceConfigurationToken)) {
            request.addExtraHeader("X-DFE-Device-Config-Token", Uri.encode(deviceConfigurationToken));
        }
        return this.mQueue.add(request);
    }

    public void invalidateTocCache() {
        this.mQueue.add(new DfeClearCacheRequest(this.mApiContext.getCache(), new DfeRequest(CHANNELS_URI.toString(), this.mApiContext, TocResponse.class, null, null).getCacheKey(), true, null));
    }

    public Request<?> getSelfUpdate(String deviceConfigurationToken, Listener<SelfUpdateResponse> listener, ErrorListener errorListener) {
        DfeRequest<SelfUpdateResponse> request = new DfeRequest(SELFUPDATE_URI.toString(), this.mApiContext, SelfUpdateResponse.class, listener, errorListener);
        if (!TextUtils.isEmpty(deviceConfigurationToken)) {
            request.addExtraHeader("X-DFE-Device-Config-Token", Uri.encode(deviceConfigurationToken));
        }
        return this.mQueue.add(request);
    }

    public void invalidateSelfUpdateCache() {
        this.mQueue.add(new DfeClearCacheRequest(this.mApiContext.getCache(), new DfeRequest(SELFUPDATE_URI.toString(), this.mApiContext, SelfUpdateResponse.class, null, null).getCacheKey(), true, null));
    }

    public Request<?> getBrowseLayout(String url, Listener<BrowseResponse> listener, ErrorListener errorListener) {
        return this.mQueue.add(new DfeRequest(url, this.mApiContext, BrowseResponse.class, listener, errorListener));
    }

    public Request<?> flagContent(String docId, int contentFlagType, String message, Listener<FlagContentResponse> listener, ErrorListener errorListener) {
        DfePostRequest<FlagContentResponse> request = new DfePostRequest(FLAG_CONTENT_URI.toString(), this.mApiContext, FlagContentResponse.class, listener, errorListener);
        request.addPostParam("doc", docId);
        request.addPostParam("content", message);
        request.addPostParam("cft", Integer.toString(contentFlagType));
        return this.mQueue.add(request);
    }

    public Request<?> getList(String url, Listener<ListResponse> listener, ErrorListener errorListener) {
        return this.mQueue.add(new DfeRequest(url, this.mApiContext, ListResponse.class, listener, errorListener));
    }

    public Request<?> search(String url, Listener<SearchResponse> listener, ErrorListener errorListener) {
        return this.mQueue.add(new DfeRequest(url, this.mApiContext, SearchResponse.class, listener, errorListener));
    }

    public Request<?> searchSuggest(String query, int backendId, int iconSize, boolean requestQuery, boolean requestNavigational, Listener<SearchSuggestResponse> listener, ErrorListener errorListener) {
        Builder uriBuilder = SEARCH_SUGGEST_URI.buildUpon().appendQueryParameter("q", query).appendQueryParameter("c", Integer.toString(backendId)).appendQueryParameter("ssis", Integer.toString(iconSize));
        if (requestQuery) {
            uriBuilder.appendQueryParameter("sst", Integer.toString(2));
        }
        if (requestNavigational) {
            uriBuilder.appendQueryParameter("sst", Integer.toString(3));
        }
        return this.mQueue.add(new DfeRequest(uriBuilder.toString(), this.mApiContext, SearchSuggestResponse.class, listener, errorListener));
    }

    public Request<?> debugSettings(String playCountryOverride, Listener<DebugSettingsResponse> listener, ErrorListener errorListener) {
        Builder uriBuilder = DEBUG_SETTINGS_URI.buildUpon();
        if (playCountryOverride != null) {
            uriBuilder.appendQueryParameter("playCountryOverride", playCountryOverride);
        }
        return this.mQueue.add(new DfeRequest(uriBuilder.toString(), this.mApiContext, DebugSettingsResponse.class, listener, errorListener));
    }

    public Request<?> getMyAccount(Listener<MyAccountResponse> listener, ErrorListener errorListener) {
        return this.mQueue.add(new DfeRequest(MY_ACCOUNT_URI.toString(), this.mApiContext, MyAccountResponse.class, listener, errorListener));
    }

    public Request<?> addReview(String docId, String title, String content, int docRating, boolean isPlusReview, Listener<ReviewResponse> listener, ErrorListener errorListener) {
        DfePostRequest<ReviewResponse> request = new DfePostRequest(ADDREVIEW_URI.toString(), this.mApiContext, ReviewResponse.class, listener, errorListener);
        request.setRetryPolicy(new DfeRetryPolicy(ADD_REVIEW_TIMEOUT_MS, ADD_REVIEW_MAX_RETRIES, ADD_REVIEW_BACKOFF_MULT, this.mApiContext));
        request.addPostParam("doc", docId);
        request.addPostParam("title", title);
        request.addPostParam("content", content);
        request.addPostParam("rating", Integer.toString(docRating));
        request.addPostParam("ipr", Boolean.toString(isPlusReview));
        return this.mQueue.add(request);
    }

    public Request<?> deleteReview(String docId, Listener<ReviewResponse> listener, ErrorListener errorListener) {
        DfePostRequest<ReviewResponse> request = new DfePostRequest(DELETEREVIEW_URI.toString(), this.mApiContext, ReviewResponse.class, listener, errorListener);
        request.addPostParam("doc", docId);
        return this.mQueue.add(request);
    }

    public Request<?> rateReview(String docId, String reviewId, int reviewRating, Listener<ReviewResponse> listener, ErrorListener errorListener) {
        DfeRequest<ReviewResponse> request = new DfeRequest(RATEREVIEW_URI.buildUpon().appendQueryParameter("doc", docId).appendQueryParameter("revId", reviewId).appendQueryParameter("rating", Integer.toString(reviewRating)).build().toString(), this.mApiContext, ReviewResponse.class, listener, errorListener);
        request.setShouldCache(false);
        request.setAvoidBulkCancel();
        return this.mQueue.add(request);
    }

    public Request<?> getReviews(String url, boolean filterByDevice, int versionFilter, int ratingFilter, int sortOrder, Listener<ReviewResponse> listener, ErrorListener errorListener) {
        return this.mQueue.add(new DfeRequest(getReviewsUrl(url, filterByDevice, versionFilter, ratingFilter, sortOrder), this.mApiContext, ReviewResponse.class, listener, errorListener));
    }

    private static String getReviewsUrl(String baseUrl, boolean filterByDevice, int versionFilter, int ratingFilter, int sortOrder) {
        Builder builder = Uri.parse(baseUrl).buildUpon();
        if (filterByDevice) {
            builder.appendQueryParameter("dfil", "1");
        }
        if (versionFilter > 0) {
            builder.appendQueryParameter("vc", Integer.toString(versionFilter));
        }
        if (ratingFilter > 0) {
            builder.appendQueryParameter("rating", Integer.toString(ratingFilter));
        }
        if (sortOrder >= 0) {
            builder.appendQueryParameter("sort", Integer.toString(sortOrder));
        }
        return builder.toString();
    }

    public Request<?> ackNotification(String notificationId, Listener<AckNotificationResponse> listener, ErrorListener errorListener) {
        return this.mQueue.add(new DfeRequest(ACK_NOTIFICATION_URI.toString() + "?" + "nid" + "=" + notificationId, this.mApiContext, AckNotificationResponse.class, listener, errorListener));
    }

    public Request<?> acceptTos(String tosToken, Boolean optedIntoMarketingEmails, Listener<AcceptTosResponse> listener, ErrorListener errorListener) {
        DfePostRequest<AcceptTosResponse> request = new DfePostRequest(ACCEPT_TOS_URI.toString(), this.mApiContext, AcceptTosResponse.class, listener, errorListener);
        request.addPostParam("tost", tosToken);
        if (optedIntoMarketingEmails != null) {
            request.addPostParam("toscme", optedIntoMarketingEmails.toString());
        }
        return this.mQueue.add(request);
    }

    public Request<?> getDetails(String url, boolean noPrefetch, boolean avoidBulkCancel, Collection<String> voucherIds, Listener<DetailsResponse> listener, ErrorListener errorListener) {
        DfeRequest<DetailsResponse> request = new DfeRequest(url, this.mApiContext, DetailsResponse.class, listener, errorListener);
        if (noPrefetch) {
            request.addExtraHeader("X-DFE-No-Prefetch", "true");
        }
        if (avoidBulkCancel) {
            request.setAvoidBulkCancel();
        }
        if (!(voucherIds == null || voucherIds.isEmpty() || !((Boolean) DfeApiConfig.vouchersInDetailsRequestsEnabled.get()).booleanValue())) {
            request.addExtraHeader("X-DFE-Client-Has-Vouchers", "true");
            if (voucherIds.size() <= ((Integer) DfeApiConfig.maxVouchersInDetailsRequest.get()).intValue()) {
                StringBuilder sb = new StringBuilder();
                for (String voucherId : voucherIds) {
                    if (sb.length() > 0) {
                        sb.append(',');
                    }
                    sb.append(Uri.encode(voucherId));
                }
                request.addExtraHeader("X-DFE-Vouchers-Backend-Docids-CSV", sb.toString());
            }
        }
        return this.mQueue.add(request);
    }

    public Request<?> getDetails(Collection<String> docids, Listener<BulkDetailsResponse> listener, ErrorListener errorListener) {
        return getDetails(docids, null, false, listener, errorListener);
    }

    public Request<?> getDetails(Collection<String> docids, String packageName, boolean includeDetails, Listener<BulkDetailsResponse> listener, ErrorListener errorListener) {
        BulkDetailsRequest bulkDetailsRequest = new BulkDetailsRequest();
        final List<String> sortedDocids = new ArrayList(docids);
        Collections.sort(sortedDocids);
        bulkDetailsRequest.docid = (String[]) sortedDocids.toArray(new String[sortedDocids.size()]);
        bulkDetailsRequest.includeDetails = includeDetails;
        bulkDetailsRequest.hasIncludeDetails = true;
        if (!TextUtils.isEmpty(packageName)) {
            bulkDetailsRequest.sourcePackageName = packageName;
            bulkDetailsRequest.hasSourcePackageName = true;
        }
        ProtoDfeRequest<BulkDetailsResponse> request = new ProtoDfeRequest<BulkDetailsResponse>(BULK_DETAILS_URI.toString(), bulkDetailsRequest, this.mApiContext, BulkDetailsResponse.class, listener, errorListener) {
            public String getCacheKey() {
                return super.getCacheKey() + "/docidhash=" + computeDocumentIdHash();
            }

            private String computeDocumentIdHash() {
                long hash = 0;
                for (String docid : sortedDocids) {
                    hash = (31 * hash) + ((long) docid.hashCode());
                }
                return Long.toString(hash);
            }
        };
        request.setShouldCache(true);
        request.setRetryPolicy(new DfeRetryPolicy(BULK_DETAILS_TIMEOUT_MS, BULK_DETAILS_MAX_RETRIES, BULK_DETAILS_BACKOFF_MULT, this.mApiContext));
        return this.mQueue.add(request);
    }

    public Request<?> setPlusOne(String docId, boolean plusOne, Listener<PlusOneResponse> listener, ErrorListener errorListener) {
        DfePostRequest<PlusOneResponse> request = new DfePostRequest(PLUSONE_URI.toString(), this.mApiContext, PlusOneResponse.class, listener, errorListener);
        request.addPostParam("doc", docId);
        request.addPostParam("rating", Integer.toString(plusOne ? 1 : 0));
        return this.mQueue.add(request);
    }

    public Request<?> makePurchase(Document doc, int offerType, Map<String, String> extraPostParams, Listener<BuyResponse> listener, ErrorListener errorListener) {
        return makePurchase(doc, offerType, -1, null, null, -1, null, extraPostParams, listener, errorListener);
    }

    public Request<?> makePurchase(Document doc, int offerType, int apiVersion, String iabPackageName, String iabPackageSignatureHash, int iabPackageVersion, String iabDeveloperPayload, Map<String, String> extraPostParams, Listener<BuyResponse> listener, ErrorListener errorListener) {
        DfePostRequest<BuyResponse> request = new DfePostRequest(PURCHASE_URI.toString(), this.mApiContext, BuyResponse.class, listener, errorListener);
        request.setRetryPolicy(makePurchaseRetryPolicy());
        request.addPostParam("doc", doc.getDocId());
        request.addPostParam("ot", Integer.toString(offerType));
        if (doc.getDocumentType() == 1) {
            request.addPostParam("vc", String.valueOf(doc.getAppDetails().versionCode));
        }
        request.addPostParam("ct", "dummy-token");
        if (iabPackageName != null) {
            request.addPostParam("bav", Integer.toString(apiVersion));
            request.addPostParam("shpn", iabPackageName);
            request.addPostParam("shh", iabPackageSignatureHash);
            request.addPostParam("shvc", Integer.toString(iabPackageVersion));
            if (iabDeveloperPayload == null) {
                iabDeveloperPayload = "";
            }
            request.addPostParam("payload", iabDeveloperPayload);
        }
        if (extraPostParams != null) {
            for (Entry<String, String> extraPostParam : extraPostParams.entrySet()) {
                request.addPostParam((String) extraPostParam.getKey(), (String) extraPostParam.getValue());
            }
        }
        addPublicAndroidIdHeaderIfNecessary(request);
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        return this.mQueue.add(request);
    }

    private void addPublicAndroidIdHeaderIfNecessary(DfeRequest<?> request) {
        if (SEND_PUBLIC_ANDROID_ID_IN_REQUESTS_FOR_RADS) {
            String publicAndroidId = getApiContext().getPublicAndroidId();
            if (!TextUtils.isEmpty(publicAndroidId)) {
                request.addExtraHeader("X-Public-Android-Id", publicAndroidId);
            }
        }
        if (SEND_AD_ID_IN_REQUESTS_FOR_RADS) {
            String adId = getApiContext().getAdId();
            Boolean isLimitAdTrackingEnabled = getApiContext().isLimitAdTrackingEnabled();
            if (!TextUtils.isEmpty(adId)) {
                request.addExtraHeader("X-Ad-Id", adId);
            }
            if (isLimitAdTrackingEnabled != null) {
                request.addExtraHeader("X-Limit-Ad-Tracking-Enabled", isLimitAdTrackingEnabled.toString());
            }
        }
    }

    public DfeRequest<?> preparePurchase(String docid, int offerType, IabParameters iabParameters, GaiaAuthParameters gaiaAuthParameters, String instrumentId, VoucherParams voucherParams, int appVersionCode, Map<String, String> extraPostParams, Listener<PreparePurchaseResponse> listener, ErrorListener errorListener) {
        DfePostRequest<PreparePurchaseResponse> request = new DfePostRequest(PREPARE_PURCHASE_URI.toString(), this.mApiContext, PreparePurchaseResponse.class, listener, errorListener);
        request.setRetryPolicy(makePurchaseRetryPolicy());
        request.addPostParam("doc", docid);
        request.addPostParam("ot", Integer.toString(offerType));
        request.addPostParam("ct", "dummy-token");
        request.addPostParam("bppcc", base64EncodeToken(InstrumentManagerUtil.createClientToken(this.mApiContext.getContext())));
        if (instrumentId != null) {
            request.addPostParam("ii", instrumentId);
        }
        request.addPostParam("chv", String.valueOf(voucherParams.hasVouchers));
        request.addPostParam("aav", String.valueOf(voucherParams.autoApply));
        if (!TextUtils.isEmpty(voucherParams.selectedVoucherId)) {
            request.addPostParam("usvid", voucherParams.selectedVoucherId);
        }
        if (appVersionCode > 0) {
            request.addPostParam("vc", String.valueOf(appVersionCode));
        }
        if (iabParameters != null) {
            iabParameters.addToRequest(request);
        }
        if (gaiaAuthParameters != null) {
            gaiaAuthParameters.addToRequest(request);
        }
        if (extraPostParams != null) {
            for (Entry<String, String> extraPostParam : extraPostParams.entrySet()) {
                request.addPostParam((String) extraPostParam.getKey(), (String) extraPostParam.getValue());
            }
        }
        return (DfeRequest) this.mQueue.add(request);
    }

    public DfeRequest<?> commitPurchase(String purchaseContextToken, Map<String, String> extraPostParams, String riskHeader, Listener<CommitPurchaseResponse> listener, ErrorListener errorListener) {
        DfePostRequest<CommitPurchaseResponse> request = new DfePostRequest(COMMIT_PURCHASE_URI.toString(), this.mApiContext, CommitPurchaseResponse.class, listener, errorListener);
        request.addPostParam("pct", purchaseContextToken);
        request.setRetryPolicy(makePurchaseRetryPolicy());
        request.addPostParam("ct", "dummy-token");
        if (extraPostParams != null) {
            for (Entry<String, String> extraPostParam : extraPostParams.entrySet()) {
                request.addPostParam((String) extraPostParam.getKey(), (String) extraPostParam.getValue());
            }
        }
        if (riskHeader != null) {
            request.addPostParam("chdi", riskHeader);
        }
        addPublicAndroidIdHeaderIfNecessary(request);
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        return (DfeRequest) this.mQueue.add(request);
    }

    public Request<?> revoke(String docId, int offerType, Listener<RevokeResponse> listener, ErrorListener errorListener) {
        DfePostRequest<RevokeResponse> request = new DfePostRequest(REVOKE_URI.toString(), this.mApiContext, RevokeResponse.class, listener, errorListener);
        request.setRetryPolicy(makePurchaseRetryPolicy());
        request.addPostParam("doc", docId);
        request.addPostParam("ot", Integer.toString(offerType));
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        return this.mQueue.add(request);
    }

    public Request<?> updateInstrument(UpdateInstrumentRequest request, Listener<UpdateInstrumentResponse> listener, ErrorListener errorListener) {
        request.checkoutToken = "dummy-token";
        request.hasCheckoutToken = true;
        ProtoDfeRequest<?> dfeRequest = new ProtoDfeRequest(UPDATE_INSTRUMENT_URI.toString(), request, this.mApiContext, UpdateInstrumentResponse.class, listener, errorListener);
        dfeRequest.setAvoidBulkCancel();
        dfeRequest.setRetryPolicy(makePurchaseRetryPolicy());
        if (VERSION.SDK_INT >= 9) {
            dfeRequest.addExtraHeader("X-DFE-Hardware-Id", DfeApiContext.sanitizeHeaderValue(Build.SERIAL));
        }
        return this.mQueue.add(dfeRequest);
    }

    public Request<?> checkInstrument(Listener<CheckInstrumentResponse> listener, ErrorListener errorListener) {
        DfePostRequest<CheckInstrumentResponse> request = new DfePostRequest(CHECK_INSTRUMENT_URI.toString(), this.mApiContext, CheckInstrumentResponse.class, listener, errorListener);
        request.addPostParam("ct", "dummy-token");
        return this.mQueue.add(request);
    }

    public Request<?> getInitialInstrumentFlowState(DeviceCreateInstrumentFlowHandle instrumentFlowHandle, Listener<GetInitialInstrumentFlowStateResponse> listener, ErrorListener errorListener) {
        DfePostRequest<GetInitialInstrumentFlowStateResponse> dfeRequest = new DfePostRequest(GET_INITIAL_INSTRUMENT_FLOW_STATE_URI.toString(), this.mApiContext, GetInitialInstrumentFlowStateResponse.class, listener, errorListener);
        dfeRequest.setRetryPolicy(makePurchaseRetryPolicy());
        dfeRequest.addPostParam("ifh", base64EncodeToken(instrumentFlowHandle.token));
        return this.mQueue.add(dfeRequest);
    }

    public Request<?> createInstrument(CreateInstrumentRequest request, Listener<CreateInstrumentResponse> listener, ErrorListener errorListener) {
        ProtoDfeRequest<CreateInstrumentResponse> dfeRequest = new ProtoDfeRequest(CREATE_INSTRUMENT_URI.toString(), request, this.mApiContext, CreateInstrumentResponse.class, listener, errorListener);
        dfeRequest.setAvoidBulkCancel();
        dfeRequest.setRetryPolicy(makePurchaseRetryPolicy());
        return this.mQueue.add(dfeRequest);
    }

    public Request<?> getBackupDeviceChoices(String checkinConsistencyTokenHeader, Listener<GetBackupDeviceChoicesResponse> listener, ErrorListener errorListener) {
        DfeRequest<GetBackupDeviceChoicesResponse> request = new DfeRequest(GET_BACKUP_DEVICE_CHOICES_URI.toString(), this.mApiContext, GetBackupDeviceChoicesResponse.class, listener, errorListener);
        if (!TextUtils.isEmpty(checkinConsistencyTokenHeader)) {
            request.addExtraHeader("X-DFE-Device-Checkin-Consistency-Token", checkinConsistencyTokenHeader);
        }
        request.setRetryPolicy(new DfeRetryPolicy(BACKUP_DEVICES_TIMEOUT_MS, BACKUP_DEVICES_MAX_RETRIES, BACKUP_DEVICES_BACKOFF_MULT, this.mApiContext));
        return this.mQueue.add(request);
    }

    public Request<?> getBackupDocumentChoices(long androidId, String checkinConsistencyTokenHeader, Listener<GetBackupDocumentChoicesResponse> listener, ErrorListener errorListener) {
        Builder builder = GET_BACKUP_DOCUMENT_CHOICES_URI.buildUpon();
        builder.appendQueryParameter("raid", Long.toString(androidId));
        DfeRequest<GetBackupDocumentChoicesResponse> request = new DfeRequest(builder.toString(), this.mApiContext, GetBackupDocumentChoicesResponse.class, listener, errorListener);
        if (!TextUtils.isEmpty(checkinConsistencyTokenHeader)) {
            request.addExtraHeader("X-DFE-Device-Checkin-Consistency-Token", checkinConsistencyTokenHeader);
        }
        return this.mQueue.add(request);
    }

    public Request<?> checkIabPromo(int docType, String packageName, String simIdentifier, Listener<CheckIabPromoResponse> listener, ErrorListener errorListener) {
        DfePostRequest<CheckIabPromoResponse> request = new DfePostRequest(CHECK_IAB_PROMO_URI.toString(), this.mApiContext, CheckIabPromoResponse.class, listener, errorListener);
        request.addPostParam("dt", Integer.toString(docType));
        request.addPostParam("shpn", packageName);
        if (!TextUtils.isEmpty(simIdentifier)) {
            request.addPostParam("dcbch", simIdentifier);
        }
        return this.mQueue.add(request);
    }

    public Request<?> checkPromoOffers(Listener<CheckPromoOfferResponse> listener, ErrorListener errorListener) {
        DfePostRequest<CheckPromoOfferResponse> request = new DfePostRequest(CHECK_PROMO_OFFER_URI.toString(), this.mApiContext, CheckPromoOfferResponse.class, listener, errorListener);
        request.setRetryPolicy(makePurchaseRetryPolicy());
        if (VERSION.SDK_INT >= 9) {
            request.addExtraHeader("X-DFE-Hardware-Id", DfeApiContext.sanitizeHeaderValue(Build.SERIAL));
        }
        return this.mQueue.add(request);
    }

    public Request<?> billingProfile(String purchaseContextToken, Map<String, String> extraPostParams, Listener<BillingProfileResponse> listener, ErrorListener errorListener) {
        DfePostRequest<BillingProfileResponse> request = new DfePostRequest(BILLING_PROFILE_URI.toString(), this.mApiContext, BillingProfileResponse.class, listener, errorListener);
        request.setRetryPolicy(makePurchaseRetryPolicy());
        request.addPostParam("ct", "dummy-token");
        if (purchaseContextToken != null) {
            request.addPostParam("pct", purchaseContextToken);
        }
        request.addPostParam("bppcc", base64EncodeToken(InstrumentManagerUtil.createClientToken(this.mApiContext.getContext())));
        if (extraPostParams != null) {
            for (Entry<String, String> entry : extraPostParams.entrySet()) {
                request.addPostParam((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return this.mQueue.add(request);
    }

    public Request<?> initiateAssociation(String dcbInstrumentKey, Listener<InitiateAssociationResponse> listener, ErrorListener errorListener) {
        DfePostRequest<InitiateAssociationResponse> request = new DfePostRequest(DCB_INITIATE_ASSOCIATION_URI.toString(), this.mApiContext, InitiateAssociationResponse.class, listener, errorListener);
        request.addPostParam("dcbch", dcbInstrumentKey);
        return this.mQueue.add(request);
    }

    public Request<?> verifyAssociation(String dcbInstrumentKey, String acceptedPiiTosVersion, boolean getSubscriberAddress, Listener<VerifyAssociationResponse> listener, ErrorListener errorListener) {
        DfePostRequest<VerifyAssociationResponse> request = new DfePostRequest(DCB_VERIFY_ASSOCIATION_URI.toString(), this.mApiContext, VerifyAssociationResponse.class, listener, errorListener);
        request.addPostParam("dcbch", dcbInstrumentKey);
        if (!TextUtils.isEmpty(acceptedPiiTosVersion)) {
            request.addPostParam("dcbptosv", acceptedPiiTosVersion);
        }
        request.addPostParam("dcbreqaddr", Boolean.toString(getSubscriberAddress));
        request.setRetryPolicy(new DfeRetryPolicy(VERIFY_ASSOCIATION_TIMEOUT_MS, VERIFY_ASSOCIATION_MAX_RETRIES, 0.0f, this.mApiContext));
        return this.mQueue.add(request);
    }

    public Request<?> log(LogRequest request, Listener<LogResponse> listener, ErrorListener errorListener) {
        ProtoDfeRequest<?> dfeRequest = new ProtoDfeRequest(LOG_URI.toString(), request, this.mApiContext, LogResponse.class, listener, errorListener);
        dfeRequest.setAvoidBulkCancel();
        return this.mQueue.add(dfeRequest);
    }

    public Request<?> replicateLibrary(LibraryReplicationRequest replicateLibraryRequest, Listener<LibraryReplicationResponse> listener, ErrorListener errorListener) {
        ProtoDfeRequest<LibraryReplicationResponse> request = new ProtoDfeRequest(REPLICATE_LIBRARY_URI.toString(), replicateLibraryRequest, this.mApiContext, LibraryReplicationResponse.class, listener, errorListener);
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        request.setAvoidBulkCancel();
        request.setRetryPolicy(new DfeRetryPolicy(REPLICATE_LIBRARY_TIMEOUT_MS, REPLICATE_LIBRARY_MAX_RETRIES, REPLICATE_LIBRARY_BACKOFF_MULT, this.mApiContext));
        return this.mQueue.add(request);
    }

    public Request<?> delivery(String docid, int offerType, byte[] serverToken, Integer appVersionCode, Integer previousVersionCode, String[] patchFormats, String certificateHashSelfUpdateMD5, String certificateHash, String deliveryToken, String checkinConsistencyToken, Listener<DeliveryResponse> listener, ErrorListener errorListener) {
        Builder uriBuilder = DELIVERY_URI.buildUpon().appendQueryParameter("doc", docid).appendQueryParameter("ot", Integer.toString(offerType));
        if (serverToken != null) {
            uriBuilder.appendQueryParameter("st", base64EncodeToken(serverToken));
        }
        if (appVersionCode != null) {
            uriBuilder.appendQueryParameter("vc", appVersionCode.toString());
        }
        if (previousVersionCode != null) {
            uriBuilder.appendQueryParameter("bvc", previousVersionCode.toString());
            if (patchFormats != null) {
                for (String patchFormat : patchFormats) {
                    uriBuilder.appendQueryParameter("pf", patchFormat);
                }
            }
        }
        if (!TextUtils.isEmpty(certificateHashSelfUpdateMD5)) {
            uriBuilder.appendQueryParameter("shh", certificateHashSelfUpdateMD5);
        }
        if (!TextUtils.isEmpty(certificateHash)) {
            uriBuilder.appendQueryParameter("ch", certificateHash);
        }
        if (!TextUtils.isEmpty(deliveryToken)) {
            uriBuilder.appendQueryParameter("dtok", deliveryToken);
        }
        DfeRequest<DeliveryResponse> request = new DfeRequest(uriBuilder.build().toString(), this.mApiContext, DeliveryResponse.class, listener, errorListener);
        if (!TextUtils.isEmpty(checkinConsistencyToken)) {
            request.addExtraHeader("X-DFE-Device-Checkin-Consistency-Token", Uri.encode(checkinConsistencyToken));
        }
        request.setShouldCache(false);
        return this.mQueue.add(request);
    }

    public String getLibraryUrl(int corpus, String libraryId, int docType, byte[] serverToken) {
        Builder uriBuilder = LIBRARY_URI.buildUpon().appendQueryParameter("c", Integer.toString(corpus)).appendQueryParameter("dt", Integer.toString(docType)).appendQueryParameter("libid", libraryId);
        if (serverToken != null) {
            uriBuilder.appendQueryParameter("st", base64EncodeToken(serverToken));
        }
        return uriBuilder.toString();
    }

    private static String base64EncodeToken(byte[] token) {
        return Base64.encodeToString(token, 8);
    }

    public Request<?> resolveLink(String url, String referringPackage, Listener<ResolvedLink> listener, ErrorListener errorListener) {
        Builder uriBuilder = RESOLVE_LINK.buildUpon().appendQueryParameter("url", url);
        if (!TextUtils.isEmpty(referringPackage)) {
            uriBuilder.appendQueryParameter("ref", referringPackage);
        }
        DfeRequest<ResolvedLink> request = new DfeRequest(uriBuilder.toString(), this.mApiContext, ResolvedLink.class, listener, errorListener);
        addPublicAndroidIdHeaderIfNecessary(request);
        return this.mQueue.add(request);
    }

    public Request<?> rateSuggestedContent(String dismissalUrl, Listener<RateSuggestedContentResponse> listener, ErrorListener errorListener) {
        return this.mQueue.add(new DfeRequest(dismissalUrl, this.mApiContext, RateSuggestedContentResponse.class, listener, errorListener));
    }

    public Request<?> redeemCode(RedeemCodeRequest redeemCodeRequest, Listener<RedeemCodeResponse> listener, ErrorListener errorListener) {
        ProtoDfeRequest<RedeemCodeResponse> request = new ProtoDfeRequest(REDEEM_CODE_URI.toString(), redeemCodeRequest, this.mApiContext, RedeemCodeResponse.class, listener, errorListener);
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        request.setRetryPolicy(makePurchaseRetryPolicy());
        addPublicAndroidIdHeaderIfNecessary(request);
        request.setAvoidBulkCancel();
        return this.mQueue.add(request);
    }

    public Request<?> requestAgeVerificationForm(Listener<ChallengeResponse> listener, ErrorListener errorListener) {
        DfePostRequest<ChallengeResponse> request = new DfePostRequest(REQUEST_AGE_VERIFICATION_FORM_URI.toString(), this.mApiContext, ChallengeResponse.class, listener, errorListener);
        request.setRetryPolicy(makeAgeVerificationRetryPolicy());
        return this.mQueue.add(request);
    }

    public Request<?> verifyAge(String url, Map<String, String> postParams, Listener<ChallengeResponse> listener, ErrorListener errorListener) {
        DfePostRequest<ChallengeResponse> request = new DfePostRequest(url, this.mApiContext, ChallengeResponse.class, listener, errorListener);
        for (Entry<String, String> entry : postParams.entrySet()) {
            request.addPostParam((String) entry.getKey(), (String) entry.getValue());
        }
        request.setRetryPolicy(makeAgeVerificationRetryPolicy());
        return this.mQueue.add(request);
    }

    public Request<?> resendAgeVerificationCode(String url, Listener<ChallengeResponse> listener, ErrorListener errorListener) {
        DfePostRequest<ChallengeResponse> request = new DfePostRequest(url, this.mApiContext, ChallengeResponse.class, listener, errorListener);
        request.setRetryPolicy(makeAgeVerificationRetryPolicy());
        return this.mQueue.add(request);
    }

    public Request<?> verifyAgeVerificationCode(String url, String codeParamKey, String code, Listener<ChallengeResponse> listener, ErrorListener errorListener) {
        DfePostRequest<ChallengeResponse> request = new DfePostRequest(url, this.mApiContext, ChallengeResponse.class, listener, errorListener);
        request.addPostParam(codeParamKey, code);
        request.setRetryPolicy(makeAgeVerificationRetryPolicy());
        return this.mQueue.add(request);
    }

    private DfeRetryPolicy makeAgeVerificationRetryPolicy() {
        return new DfeRetryPolicy(AGE_VERIFICATION_TIMEOUT_MS, 0, 0.0f, this.mApiContext);
    }

    private DfeRetryPolicy makePurchaseRetryPolicy() {
        return new DfeRetryPolicy(PURCHASE_TIMEOUT_MS, 0, 0.0f, this.mApiContext);
    }

    public Request<?> addToLibrary(Collection<String> docids, String libraryId, Listener<ModifyLibraryResponse> listener, ErrorListener errorListener) {
        ModifyLibraryRequest modifyLibraryRequest = new ModifyLibraryRequest();
        modifyLibraryRequest.libraryId = libraryId;
        modifyLibraryRequest.hasLibraryId = true;
        modifyLibraryRequest.forAddDocid = (String[]) docids.toArray(new String[docids.size()]);
        ProtoDfeRequest<ModifyLibraryResponse> request = new ProtoDfeRequest(MODIFY_LIBRARY_URI.toString(), modifyLibraryRequest, this.mApiContext, ModifyLibraryResponse.class, listener, errorListener);
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        return this.mQueue.add(request);
    }

    public Request<?> removeFromLibrary(Collection<String> docids, String libraryId, Listener<ModifyLibraryResponse> listener, ErrorListener errorListener) {
        ModifyLibraryRequest modifyLibraryRequest = new ModifyLibraryRequest();
        modifyLibraryRequest.libraryId = libraryId;
        modifyLibraryRequest.hasLibraryId = true;
        modifyLibraryRequest.forRemovalDocid = (String[]) docids.toArray(new String[docids.size()]);
        ProtoDfeRequest<ModifyLibraryResponse> request = new ProtoDfeRequest(MODIFY_LIBRARY_URI.toString(), modifyLibraryRequest, this.mApiContext, ModifyLibraryResponse.class, listener, errorListener);
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        return this.mQueue.add(request);
    }

    public Request<?> archiveFromLibrary(Collection<String> docids, String libraryId, Listener<ModifyLibraryResponse> listener, ErrorListener errorListener) {
        ModifyLibraryRequest modifyLibraryRequest = new ModifyLibraryRequest();
        modifyLibraryRequest.libraryId = libraryId;
        modifyLibraryRequest.hasLibraryId = true;
        modifyLibraryRequest.forArchiveDocid = (String[]) docids.toArray(new String[docids.size()]);
        ProtoDfeRequest<ModifyLibraryResponse> request = new ProtoDfeRequest(MODIFY_LIBRARY_URI.toString(), modifyLibraryRequest, this.mApiContext, ModifyLibraryResponse.class, listener, errorListener);
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        return this.mQueue.add(request);
    }

    public Request<?> consumePurchase(String purchaseToken, int offerType, String packageName, Listener<ConsumePurchaseResponse> listener, ErrorListener errorListener) {
        DfePostRequest<ConsumePurchaseResponse> request = new DfePostRequest(CONSUME_PURCHASE_URI.toString(), this.mApiContext, ConsumePurchaseResponse.class, listener, errorListener);
        request.setRetryPolicy(makePurchaseRetryPolicy());
        request.addPostParam("pt", purchaseToken);
        request.addPostParam("ot", Integer.toString(offerType));
        request.addPostParam("shpn", packageName);
        request.setRequireAuthenticatedResponse(new DfeResponseVerifierImpl(this.mApiContext.getContext()));
        return this.mQueue.add(request);
    }

    public void invalidateReviewsCache(String url, boolean filterByDevice, int versionFilter, int ratingFilter, int sortOrder, boolean fullExpire) {
        this.mQueue.add(new DfeClearCacheRequest(this.mApiContext.getCache(), new DfeRequest(getReviewsUrl(url, filterByDevice, versionFilter, ratingFilter, sortOrder), this.mApiContext, ReviewResponse.class, null, null).getCacheKey(), fullExpire, null));
    }

    public void invalidateDetailsCache(String detailsUrl, boolean fullExpire) {
        this.mQueue.add(new DfeClearCacheRequest(this.mApiContext.getCache(), new DfeRequest(detailsUrl, this.mApiContext, DetailsResponse.class, null, null).getCacheKey(), fullExpire, null));
    }

    public void invalidateListCache(String listUrl, boolean fullExpire) {
        this.mQueue.add(new DfeClearCacheRequest(this.mApiContext.getCache(), new DfeRequest(listUrl, this.mApiContext, ListResponse.class, null, null).getCacheKey(), fullExpire, null));
    }

    public Request<?> uploadDeviceConfig(DeviceConfigurationProto deviceConfig, String gcmRegistrationId, String deviceConfigurationToken, Listener<UploadDeviceConfigResponse> listener, ErrorListener errorListener) {
        UploadDeviceConfigRequest requestProto = new UploadDeviceConfigRequest();
        if (deviceConfig != null) {
            requestProto.deviceConfiguration = deviceConfig;
        }
        if (gcmRegistrationId != null) {
            requestProto.gcmRegistrationId = gcmRegistrationId;
            requestProto.hasGcmRegistrationId = true;
        }
        ProtoDfeRequest<UploadDeviceConfigResponse> request = new ProtoDfeRequest(UPLOAD_DEVICE_CONFIG_URI.toString(), requestProto, this.mApiContext, UploadDeviceConfigResponse.class, listener, errorListener);
        request.setRetryPolicy(makePurchaseRetryPolicy());
        if (!TextUtils.isEmpty(deviceConfigurationToken)) {
            request.addExtraHeader("X-DFE-Device-Config-Token", Uri.encode(deviceConfigurationToken));
        }
        return this.mQueue.add(request);
    }

    public Request<?> earlyUpdate(DeviceConfigurationProto deviceConfig, Listener<EarlyUpdateResponse> listener, ErrorListener errorListener) {
        EarlyUpdateRequest requestProto = new EarlyUpdateRequest();
        if (deviceConfig != null) {
            requestProto.deviceConfiguration = deviceConfig;
        }
        ProtoDfeRequest<EarlyUpdateResponse> request = new ProtoDfeRequest(EARLY_UPDATE_URI.toString(), requestProto, this.mApiContext, EarlyUpdateResponse.class, listener, errorListener);
        request.setRetryPolicy(makeEarlyUpdateRetryPolicy());
        request.setShouldCache(false);
        return this.mQueue.add(request);
    }

    public Request<?> earlyDelivery(String docid, int offerType, Integer appVersionCode, Integer previousVersionCode, String[] patchFormats, String certificateHashSelfUpdateMD5, String certificateHash, String deliveryToken, String checkinConsistencyToken, Listener<DeliveryResponse> listener, ErrorListener errorListener) {
        Builder uriBuilder = EARLY_DELIVERY_URI.buildUpon().appendQueryParameter("doc", docid).appendQueryParameter("ot", Integer.toString(offerType));
        if (appVersionCode != null) {
            uriBuilder.appendQueryParameter("vc", appVersionCode.toString());
        }
        if (previousVersionCode != null) {
            uriBuilder.appendQueryParameter("bvc", previousVersionCode.toString());
            if (patchFormats != null) {
                for (String patchFormat : patchFormats) {
                    uriBuilder.appendQueryParameter("pf", patchFormat);
                }
            }
        }
        if (!TextUtils.isEmpty(certificateHashSelfUpdateMD5)) {
            uriBuilder.appendQueryParameter("shh", certificateHashSelfUpdateMD5);
        }
        if (!TextUtils.isEmpty(certificateHash)) {
            uriBuilder.appendQueryParameter("ch", certificateHash);
        }
        if (!TextUtils.isEmpty(deliveryToken)) {
            uriBuilder.appendQueryParameter("dtok", deliveryToken);
        }
        DfeRequest<DeliveryResponse> request = new DfeRequest(uriBuilder.build().toString(), this.mApiContext, DeliveryResponse.class, listener, errorListener);
        if (!TextUtils.isEmpty(checkinConsistencyToken)) {
            request.addExtraHeader("X-DFE-Device-Checkin-Consistency-Token", Uri.encode(checkinConsistencyToken));
        }
        request.setRetryPolicy(makeEarlyUpdateRetryPolicy());
        request.setShouldCache(false);
        return this.mQueue.add(request);
    }

    private DfeRetryPolicy makeEarlyUpdateRetryPolicy() {
        return new DfeRetryPolicy(EARLY_TIMEOUT_MS, EARLY_MAX_RETRIES, EARLY_BACKOFF_MULT, this.mApiContext);
    }

    public Request<?> preloads(String deviceConfigurationToken, String checkinConsistencyToken, String stubPackageDocId, Listener<PreloadsResponse> listener, ErrorListener errorListener) {
        Builder uriBuilder = PRELOADS_URI.buildUpon();
        uriBuilder.appendQueryParameter("doc", stubPackageDocId);
        DfeRequest<PreloadsResponse> request = new DfeRequest(uriBuilder.build().toString(), this.mApiContext, PreloadsResponse.class, listener, errorListener);
        if (!TextUtils.isEmpty(deviceConfigurationToken)) {
            request.addExtraHeader("X-DFE-Device-Config-Token", Uri.encode(deviceConfigurationToken));
        }
        if (!TextUtils.isEmpty(checkinConsistencyToken)) {
            request.addExtraHeader("X-DFE-Device-Checkin-Consistency-Token", Uri.encode(checkinConsistencyToken));
        }
        return this.mQueue.add(request);
    }
}
