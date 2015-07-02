package com.google.android.finsky.api;

import android.accounts.Account;
import android.net.Uri;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
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
import com.google.android.finsky.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.protos.Details.DetailsResponse;
import com.google.android.finsky.protos.DeviceConfigurationProto;
import com.google.android.finsky.protos.DocList.ListResponse;
import com.google.android.finsky.protos.EarlyUpdate.EarlyUpdateResponse;
import com.google.android.finsky.protos.LibraryReplication.LibraryReplicationRequest;
import com.google.android.finsky.protos.LibraryReplication.LibraryReplicationResponse;
import com.google.android.finsky.protos.Log.LogRequest;
import com.google.android.finsky.protos.Log.LogResponse;
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
import com.google.android.finsky.protos.UploadDeviceConfig.UploadDeviceConfigResponse;
import com.google.protobuf.nano.MessageNano;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DfeApi {
    public static final Uri ACCEPT_TOS_URI;
    public static final Uri ACK_NOTIFICATION_URI;
    public static final Uri ADDREVIEW_URI;
    public static final Uri BASE_URI;
    public static final Uri BILLING_PROFILE_URI;
    public static final Uri BULK_DETAILS_URI;
    public static final Uri CHANNELS_URI;
    public static final Uri CHECK_IAB_PROMO_URI;
    public static final Uri CHECK_INSTRUMENT_URI;
    public static final Uri CHECK_PROMO_OFFER_URI;
    public static final Uri COMMIT_PURCHASE_URI;
    public static final Uri CONSUME_PURCHASE_URI;
    public static final Uri CREATE_INSTRUMENT_URI;
    public static final Uri DCB_INITIATE_ASSOCIATION_URI;
    public static final Uri DCB_VERIFY_ASSOCIATION_URI;
    public static final Uri DEBUG_SETTINGS_URI;
    public static final Uri DELETEREVIEW_URI;
    public static final Uri DELIVERY_URI;
    public static final Uri EARLY_DELIVERY_URI;
    public static final Uri EARLY_UPDATE_URI;
    public static final Uri FLAG_CONTENT_URI;
    public static final Uri GET_BACKUP_DEVICE_CHOICES_URI;
    public static final Uri GET_BACKUP_DOCUMENT_CHOICES_URI;
    public static final Uri GET_INITIAL_INSTRUMENT_FLOW_STATE_URI;
    public static final Uri LIBRARY_URI;
    public static final Uri LOG_URI;
    public static final Uri MODIFY_LIBRARY_URI;
    public static final Uri MY_ACCOUNT_URI;
    public static final Uri PLUSONE_URI;
    public static final Uri PRELOADS_URI;
    public static final Uri PREPARE_PURCHASE_URI;
    public static final Uri PURCHASE_URI;
    public static final Uri RATEREVIEW_URI;
    public static final Uri REDEEM_CODE_URI;
    public static final Uri REPLICATE_LIBRARY_URI;
    public static final Uri REQUEST_AGE_VERIFICATION_FORM_URI;
    public static final Uri RESOLVE_LINK;
    public static final Uri REVOKE_URI;
    public static final Uri SEARCH_CHANNEL_URI;
    public static final Uri SEARCH_SUGGEST_URI;
    public static final Uri SELFUPDATE_URI;
    public static final Uri UPDATE_INSTRUMENT_URI;
    public static final Uri UPLOAD_DEVICE_CONFIG_URI;

    public static class DfePostRequest<T extends MessageNano> extends DfeRequest<T> {
        private final Map<String, String> mPostParams;

        public DfePostRequest(String url, DfeApiContext apiContext, Class<T> responseClass, Listener<T> listener, ErrorListener errorListener) {
            super(1, url, apiContext, responseClass, listener, errorListener);
            this.mPostParams = new HashMap();
            setShouldCache(false);
            setAvoidBulkCancel();
        }

        public void addPostParam(String key, String value) {
            this.mPostParams.put(key, value);
        }

        public Map<String, String> getParams() {
            return this.mPostParams;
        }
    }

    public static class GaiaAuthParameters {
        private final long mLastAuthTimestamp;
        private final int mPurchaseAuth;

        public GaiaAuthParameters(long lastAuthTimestamp, int purchaseAuth) {
            this.mLastAuthTimestamp = lastAuthTimestamp;
            this.mPurchaseAuth = purchaseAuth;
        }

        public void addToRequest(DfePostRequest<?> request) {
            if (this.mPurchaseAuth == 1) {
                if (this.mLastAuthTimestamp > 0) {
                    request.addPostParam("pclats", String.valueOf(this.mLastAuthTimestamp));
                }
                request.addPostParam("pcauth", String.valueOf(4));
            } else if (this.mPurchaseAuth == 0) {
                request.addPostParam("pcauth", String.valueOf(3));
            }
        }
    }

    public static class IabParameters {
        private final int mApiVersion;
        private final String mDeveloperPayload;
        private final List<String> mOldSkusAsDocidStrings;
        private final String mPackageName;
        private final String mPackageSignatureHash;
        private final int mPackageVersion;

        public IabParameters(int iabApiVersion, String iabPackageName, String iabPackageSignatureHash, int iabPackageVersion, String iabDeveloperPayload, List<String> oldSkusAsDocidStrings) {
            this.mApiVersion = iabApiVersion;
            this.mPackageName = iabPackageName;
            this.mPackageSignatureHash = iabPackageSignatureHash;
            this.mPackageVersion = iabPackageVersion;
            this.mDeveloperPayload = iabDeveloperPayload;
            this.mOldSkusAsDocidStrings = oldSkusAsDocidStrings;
        }

        public void addToRequest(DfePostRequest<?> request) {
            request.addPostParam("bav", Integer.toString(this.mApiVersion));
            request.addPostParam("shpn", this.mPackageName);
            request.addPostParam("shh", this.mPackageSignatureHash);
            request.addPostParam("shvc", Integer.toString(this.mPackageVersion));
            if (this.mDeveloperPayload != null) {
                request.addPostParam("payload", this.mDeveloperPayload);
            }
            if (this.mOldSkusAsDocidStrings != null) {
                for (String oldSku : this.mOldSkusAsDocidStrings) {
                    request.addPostParam("odid", oldSku);
                }
            }
        }
    }

    Request<?> acceptTos(String str, Boolean bool, Listener<AcceptTosResponse> listener, ErrorListener errorListener);

    Request<?> ackNotification(String str, Listener<AckNotificationResponse> listener, ErrorListener errorListener);

    Request<?> addReview(String str, String str2, String str3, int i, boolean z, Listener<ReviewResponse> listener, ErrorListener errorListener);

    Request<?> addToLibrary(Collection<String> collection, String str, Listener<ModifyLibraryResponse> listener, ErrorListener errorListener);

    Request<?> archiveFromLibrary(Collection<String> collection, String str, Listener<ModifyLibraryResponse> listener, ErrorListener errorListener);

    Request<?> billingProfile(String str, Map<String, String> map, Listener<BillingProfileResponse> listener, ErrorListener errorListener);

    Request<?> checkIabPromo(int i, String str, String str2, Listener<CheckIabPromoResponse> listener, ErrorListener errorListener);

    Request<?> checkInstrument(Listener<CheckInstrumentResponse> listener, ErrorListener errorListener);

    Request<?> checkPromoOffers(Listener<CheckPromoOfferResponse> listener, ErrorListener errorListener);

    DfeRequest<?> commitPurchase(String str, Map<String, String> map, String str2, Listener<CommitPurchaseResponse> listener, ErrorListener errorListener);

    Request<?> consumePurchase(String str, int i, String str2, Listener<ConsumePurchaseResponse> listener, ErrorListener errorListener);

    Request<?> createInstrument(CreateInstrumentRequest createInstrumentRequest, Listener<CreateInstrumentResponse> listener, ErrorListener errorListener);

    Request<?> debugSettings(String str, Listener<DebugSettingsResponse> listener, ErrorListener errorListener);

    Request<?> deleteReview(String str, Listener<ReviewResponse> listener, ErrorListener errorListener);

    Request<?> delivery(String str, int i, byte[] bArr, Integer num, Integer num2, String[] strArr, String str2, String str3, String str4, String str5, Listener<DeliveryResponse> listener, ErrorListener errorListener);

    Request<?> earlyDelivery(String str, int i, Integer num, Integer num2, String[] strArr, String str2, String str3, String str4, String str5, Listener<DeliveryResponse> listener, ErrorListener errorListener);

    Request<?> earlyUpdate(DeviceConfigurationProto deviceConfigurationProto, Listener<EarlyUpdateResponse> listener, ErrorListener errorListener);

    Request<?> flagContent(String str, int i, String str2, Listener<FlagContentResponse> listener, ErrorListener errorListener);

    Account getAccount();

    String getAccountName();

    DfeApiContext getApiContext();

    Request<?> getBackupDeviceChoices(String str, Listener<GetBackupDeviceChoicesResponse> listener, ErrorListener errorListener);

    Request<?> getBackupDocumentChoices(long j, String str, Listener<GetBackupDocumentChoicesResponse> listener, ErrorListener errorListener);

    Request<?> getBrowseLayout(String str, Listener<BrowseResponse> listener, ErrorListener errorListener);

    Request<?> getDetails(String str, boolean z, boolean z2, Collection<String> collection, Listener<DetailsResponse> listener, ErrorListener errorListener);

    Request<?> getDetails(Collection<String> collection, Listener<BulkDetailsResponse> listener, ErrorListener errorListener);

    Request<?> getDetails(Collection<String> collection, String str, boolean z, Listener<BulkDetailsResponse> listener, ErrorListener errorListener);

    Request<?> getInitialInstrumentFlowState(DeviceCreateInstrumentFlowHandle deviceCreateInstrumentFlowHandle, Listener<GetInitialInstrumentFlowStateResponse> listener, ErrorListener errorListener);

    String getLibraryUrl(int i, String str, int i2, byte[] bArr);

    Request<?> getList(String str, Listener<ListResponse> listener, ErrorListener errorListener);

    Request<?> getMyAccount(Listener<MyAccountResponse> listener, ErrorListener errorListener);

    Request<?> getReviews(String str, boolean z, int i, int i2, int i3, Listener<ReviewResponse> listener, ErrorListener errorListener);

    Request<?> getSelfUpdate(String str, Listener<SelfUpdateResponse> listener, ErrorListener errorListener);

    Request<?> getToc(boolean z, String str, Listener<TocResponse> listener, ErrorListener errorListener);

    Request<?> initiateAssociation(String str, Listener<InitiateAssociationResponse> listener, ErrorListener errorListener);

    void invalidateDetailsCache(String str, boolean z);

    void invalidateListCache(String str, boolean z);

    void invalidateReviewsCache(String str, boolean z, int i, int i2, int i3, boolean z2);

    void invalidateSelfUpdateCache();

    void invalidateTocCache();

    Request<?> log(LogRequest logRequest, Listener<LogResponse> listener, ErrorListener errorListener);

    Request<?> makePurchase(Document document, int i, Map<String, String> map, Listener<BuyResponse> listener, ErrorListener errorListener);

    Request<?> preloads(String str, String str2, String str3, Listener<PreloadsResponse> listener, ErrorListener errorListener);

    DfeRequest<?> preparePurchase(String str, int i, IabParameters iabParameters, GaiaAuthParameters gaiaAuthParameters, String str2, VoucherParams voucherParams, int i2, Map<String, String> map, Listener<PreparePurchaseResponse> listener, ErrorListener errorListener);

    Request<?> rateReview(String str, String str2, int i, Listener<ReviewResponse> listener, ErrorListener errorListener);

    Request<?> rateSuggestedContent(String str, Listener<RateSuggestedContentResponse> listener, ErrorListener errorListener);

    Request<?> redeemCode(RedeemCodeRequest redeemCodeRequest, Listener<RedeemCodeResponse> listener, ErrorListener errorListener);

    Request<?> removeFromLibrary(Collection<String> collection, String str, Listener<ModifyLibraryResponse> listener, ErrorListener errorListener);

    Request<?> replicateLibrary(LibraryReplicationRequest libraryReplicationRequest, Listener<LibraryReplicationResponse> listener, ErrorListener errorListener);

    Request<?> requestAgeVerificationForm(Listener<ChallengeResponse> listener, ErrorListener errorListener);

    Request<?> resendAgeVerificationCode(String str, Listener<ChallengeResponse> listener, ErrorListener errorListener);

    Request<?> resolveLink(String str, String str2, Listener<ResolvedLink> listener, ErrorListener errorListener);

    Request<?> revoke(String str, int i, Listener<RevokeResponse> listener, ErrorListener errorListener);

    Request<?> search(String str, Listener<SearchResponse> listener, ErrorListener errorListener);

    Request<?> searchSuggest(String str, int i, int i2, boolean z, boolean z2, Listener<SearchSuggestResponse> listener, ErrorListener errorListener);

    Request<?> setPlusOne(String str, boolean z, Listener<PlusOneResponse> listener, ErrorListener errorListener);

    Request<?> updateInstrument(UpdateInstrumentRequest updateInstrumentRequest, Listener<UpdateInstrumentResponse> listener, ErrorListener errorListener);

    Request<?> uploadDeviceConfig(DeviceConfigurationProto deviceConfigurationProto, String str, String str2, Listener<UploadDeviceConfigResponse> listener, ErrorListener errorListener);

    Request<?> verifyAge(String str, Map<String, String> map, Listener<ChallengeResponse> listener, ErrorListener errorListener);

    Request<?> verifyAgeVerificationCode(String str, String str2, String str3, Listener<ChallengeResponse> listener, ErrorListener errorListener);

    Request<?> verifyAssociation(String str, String str2, boolean z, Listener<VerifyAssociationResponse> listener, ErrorListener errorListener);

    static {
        BASE_URI = Uri.parse("https://android.clients.google.com/fdfe/");
        CHANNELS_URI = Uri.parse("toc");
        SELFUPDATE_URI = Uri.parse("selfUpdate");
        SEARCH_CHANNEL_URI = Uri.parse("search");
        SEARCH_SUGGEST_URI = Uri.parse("searchSuggest");
        DEBUG_SETTINGS_URI = Uri.parse("debugSettings");
        MY_ACCOUNT_URI = Uri.parse("myAccount");
        ADDREVIEW_URI = Uri.parse("addReview");
        DELETEREVIEW_URI = Uri.parse("deleteReview");
        RATEREVIEW_URI = Uri.parse("rateReview");
        PURCHASE_URI = Uri.parse("purchase");
        PREPARE_PURCHASE_URI = Uri.parse("preparePurchase");
        COMMIT_PURCHASE_URI = Uri.parse("commitPurchase");
        REVOKE_URI = Uri.parse("revoke");
        UPDATE_INSTRUMENT_URI = Uri.parse("updateInstrument");
        CHECK_INSTRUMENT_URI = Uri.parse("checkInstrument");
        GET_BACKUP_DEVICE_CHOICES_URI = Uri.parse("getBackupDeviceChoices");
        GET_BACKUP_DOCUMENT_CHOICES_URI = Uri.parse("getBackupDocumentChoices");
        GET_INITIAL_INSTRUMENT_FLOW_STATE_URI = Uri.parse("getInitialInstrumentFlowState");
        CREATE_INSTRUMENT_URI = Uri.parse("createInstrument");
        CHECK_PROMO_OFFER_URI = Uri.parse("checkPromoOffer");
        CHECK_IAB_PROMO_URI = Uri.parse("checkIabPromo");
        BILLING_PROFILE_URI = Uri.parse("billingProfile");
        LOG_URI = Uri.parse("log");
        FLAG_CONTENT_URI = Uri.parse("flagContent");
        PLUSONE_URI = Uri.parse("plusOne");
        ACK_NOTIFICATION_URI = Uri.parse("ack");
        ACCEPT_TOS_URI = Uri.parse("acceptTos");
        LIBRARY_URI = Uri.parse("library");
        BULK_DETAILS_URI = Uri.parse("bulkDetails");
        RESOLVE_LINK = Uri.parse("resolveLink");
        DCB_INITIATE_ASSOCIATION_URI = Uri.parse("dcbInitiateAssociation");
        DCB_VERIFY_ASSOCIATION_URI = Uri.parse("dcbVerifyAssociation");
        REPLICATE_LIBRARY_URI = Uri.parse("replicateLibrary");
        DELIVERY_URI = Uri.parse("delivery");
        MODIFY_LIBRARY_URI = Uri.parse("modifyLibrary");
        CONSUME_PURCHASE_URI = Uri.parse("consumePurchase");
        UPLOAD_DEVICE_CONFIG_URI = Uri.parse("uploadDeviceConfig");
        EARLY_UPDATE_URI = Uri.parse("earlyUpdate");
        EARLY_DELIVERY_URI = Uri.parse("earlyDelivery");
        PRELOADS_URI = Uri.parse("preloads");
        REDEEM_CODE_URI = Uri.parse("redeemCode");
        REQUEST_AGE_VERIFICATION_FORM_URI = Uri.parse("verifyAge");
    }
}
