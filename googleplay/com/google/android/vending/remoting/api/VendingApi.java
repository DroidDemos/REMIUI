package com.google.android.vending.remoting.api;

import android.text.TextUtils;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.protos.VendingProtos.AckNotificationsRequestProto;
import com.google.android.finsky.protos.VendingProtos.AckNotificationsResponseProto;
import com.google.android.finsky.protos.VendingProtos.BillingEventRequestProto;
import com.google.android.finsky.protos.VendingProtos.BillingEventResponseProto;
import com.google.android.finsky.protos.VendingProtos.CheckForNotificationsRequestProto;
import com.google.android.finsky.protos.VendingProtos.CheckLicenseRequestProto;
import com.google.android.finsky.protos.VendingProtos.CheckLicenseResponseProto;
import com.google.android.finsky.protos.VendingProtos.ContentSyncRequestProto;
import com.google.android.finsky.protos.VendingProtos.ContentSyncResponseProto;
import com.google.android.finsky.protos.VendingProtos.GetMarketMetadataResponseProto;
import com.google.android.finsky.protos.VendingProtos.InAppPurchaseInformationRequestProto;
import com.google.android.finsky.protos.VendingProtos.InAppPurchaseInformationResponseProto;
import com.google.android.finsky.protos.VendingProtos.InAppRestoreTransactionsRequestProto;
import com.google.android.finsky.protos.VendingProtos.InAppRestoreTransactionsResponseProto;
import com.google.android.finsky.protos.VendingProtos.ModifyCommentRequestProto;
import com.google.android.finsky.protos.VendingProtos.ModifyCommentResponseProto;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataRequestProto;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.gms.ads.identifier.AdIdProvider;

public class VendingApi {
    private static final boolean SEND_AD_ID_FOR_CONTENT_SYNC;
    private final VendingApiContext mApiContext;
    private final RequestQueue mRequestQueue;

    private static class CountriesConverter implements Listener<PurchaseMetadataResponseProto> {
        private final Listener<Country[]> mListener;

        public CountriesConverter(Listener<Country[]> listener) {
            this.mListener = listener;
        }

        public void onResponse(PurchaseMetadataResponseProto response) {
            if (response == null || response.countries == null) {
                this.mListener.onResponse(new Country[0]);
            } else {
                this.mListener.onResponse(response.countries.country);
            }
        }
    }

    static {
        SEND_AD_ID_FOR_CONTENT_SYNC = ((Boolean) DfeApiConfig.sendAdIdInRequestsForRads.get()).booleanValue();
    }

    public VendingApi(RequestQueue requestQueue, VendingApiContext apiContext) {
        this.mRequestQueue = requestQueue;
        this.mApiContext = apiContext;
    }

    public VendingApiContext getApiContext() {
        return this.mApiContext;
    }

    public void flagAsset(String assetId, int flagType, String flagMessage, Listener<ModifyCommentResponseProto> listener, ErrorListener errorListener) {
        ModifyCommentRequestProto request = new ModifyCommentRequestProto();
        request.assetId = assetId;
        request.hasAssetId = true;
        request.flagType = flagType;
        request.hasFlagType = true;
        if (!TextUtils.isEmpty(flagMessage)) {
            request.flagMessage = flagMessage;
            request.hasFlagMessage = true;
        }
        this.mRequestQueue.add(VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", ModifyCommentRequestProto.class, request, ModifyCommentResponseProto.class, listener, this.mApiContext, errorListener));
    }

    public void checkLicense(CheckLicenseRequestProto licenseRequest, Listener<CheckLicenseResponseProto> listener, ErrorListener errorListener) {
        this.mRequestQueue.add(VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", CheckLicenseRequestProto.class, licenseRequest, CheckLicenseResponseProto.class, listener, this.mApiContext, errorListener));
    }

    public void getInAppPurchaseInformation(InAppPurchaseInformationRequestProto request, Listener<InAppPurchaseInformationResponseProto> listener, ErrorListener errorListener) {
        this.mRequestQueue.add(VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", InAppPurchaseInformationRequestProto.class, request, InAppPurchaseInformationResponseProto.class, listener, this.mApiContext, errorListener));
    }

    public void restoreInAppTransactions(InAppRestoreTransactionsRequestProto request, Listener<InAppRestoreTransactionsResponseProto> listener, ErrorListener errorListener) {
        this.mRequestQueue.add(VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", InAppRestoreTransactionsRequestProto.class, request, InAppRestoreTransactionsResponseProto.class, listener, this.mApiContext, errorListener));
    }

    public void checkForPendingNotifications(Listener<GetMarketMetadataResponseProto> listener, ErrorListener errorListener) {
        this.mRequestQueue.add(VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", CheckForNotificationsRequestProto.class, new CheckForNotificationsRequestProto(), GetMarketMetadataResponseProto.class, listener, this.mApiContext, errorListener));
    }

    public void ackNotifications(AckNotificationsRequestProto request, Listener<AckNotificationsResponseProto> listener, ErrorListener errorListener) {
        VendingRequest<?, ?> vendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", AckNotificationsRequestProto.class, request, AckNotificationsResponseProto.class, listener, this.mApiContext, errorListener);
        vendingRequest.setAvoidBulkCancel();
        this.mRequestQueue.add(vendingRequest);
    }

    public void syncContent(ContentSyncRequestProto request, AdIdProvider adIdProvider, String accountOrdinal, Listener<ContentSyncResponseProto> listener, ErrorListener errorListener) {
        VendingRequest<?, ?> vendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", ContentSyncRequestProto.class, request, ContentSyncResponseProto.class, listener, this.mApiContext, errorListener);
        if (adIdProvider != null) {
            if (!TextUtils.isEmpty(adIdProvider.getPublicAndroidId())) {
                vendingRequest.addExtraHeader("X-Public-Android-Id", adIdProvider.getPublicAndroidId());
            }
            if (SEND_AD_ID_FOR_CONTENT_SYNC) {
                if (!TextUtils.isEmpty(adIdProvider.getAdId())) {
                    vendingRequest.addExtraHeader("X-Ad-Id", adIdProvider.getAdId());
                }
                if (adIdProvider.isLimitAdTrackingEnabled() != null) {
                    vendingRequest.addExtraHeader("X-Limit-Ad-Tracking-Enabled", adIdProvider.isLimitAdTrackingEnabled().toString());
                }
            }
        }
        if (!TextUtils.isEmpty(accountOrdinal)) {
            vendingRequest.addExtraHeader("X-Account-Ordinal", accountOrdinal);
        }
        vendingRequest.setAvoidBulkCancel();
        this.mRequestQueue.add(vendingRequest);
    }

    public void recordBillingEvent(BillingEventRequestProto request, Listener<BillingEventResponseProto> listener, ErrorListener errorListener) {
        VendingRequest<?, ?> vendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", BillingEventRequestProto.class, request, BillingEventResponseProto.class, listener, this.mApiContext, errorListener);
        vendingRequest.setAvoidBulkCancel();
        this.mRequestQueue.add(vendingRequest);
    }

    public void getBillingCountries(Listener<Country[]> listener, ErrorListener errorListener) {
        this.mRequestQueue.add(VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", PurchaseMetadataRequestProto.class, new PurchaseMetadataRequestProto(), PurchaseMetadataResponseProto.class, new CountriesConverter(listener), this.mApiContext, errorListener));
    }
}
