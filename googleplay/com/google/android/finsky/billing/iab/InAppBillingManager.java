package com.google.android.finsky.billing.iab;

import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.IabParameters;
import com.google.android.finsky.billing.InAppBillingSetting;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.iab.InAppBillingUtils.ResponseCode;
import com.google.android.finsky.billing.lightpurchase.IabV3Activity;
import com.google.android.finsky.billing.lightpurchase.PurchaseParams;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryInAppEntry;
import com.google.android.finsky.library.LibraryInAppSubscriptionEntry;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.protos.BuyInstruments.CheckIabPromoResponse;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.ConsumePurchaseResponse;
import com.google.android.finsky.protos.Details.BulkDetailsEntry;
import com.google.android.finsky.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Md5Util;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppBillingManager {
    private static final int DETAILS_REQUEST_SKU_LIST_MAX_SIZE;
    private static final int MAX_PURCHASES_IN_RESPONSE;
    private static final long TIMEOUT_MS;
    private final Context mContext;
    private final DfeApi mDfeApi;
    private final Libraries mLibraries;
    private final LibraryReplicators mLibraryReplicators;

    static {
        DETAILS_REQUEST_SKU_LIST_MAX_SIZE = ((Integer) G.iabV3SkuDetailsMaxSize.get()).intValue();
        TIMEOUT_MS = ((Long) G.iabV3NetworkTimeoutMs.get()).longValue();
        MAX_PURCHASES_IN_RESPONSE = ((Integer) G.iabV3MaxPurchasesInResponse.get()).intValue();
    }

    public InAppBillingManager(Context context, Libraries libraries, LibraryReplicators libraryReplicators, DfeApi dfeApi) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        } else if (libraries == null) {
            throw new IllegalArgumentException("libraries must not be null");
        } else if (libraryReplicators == null) {
            throw new IllegalArgumentException("libraryReplicators must not be null");
        } else if (dfeApi.getAccount() == null) {
            throw new IllegalArgumentException("dfeApi must specify an account");
        } else {
            this.mContext = context;
            this.mLibraries = libraries;
            this.mLibraryReplicators = libraryReplicators;
            this.mDfeApi = dfeApi;
        }
    }

    public int isBillingSupported(int apiVersion, String packageName, String type) {
        ResponseCode responseCode = checkBillingEnabled(apiVersion);
        if (responseCode != ResponseCode.RESULT_OK) {
            return responseCode.responseCode();
        }
        responseCode = checkTypeSupported(type);
        if (responseCode != ResponseCode.RESULT_OK) {
            return responseCode.responseCode();
        }
        return responseCode.responseCode();
    }

    public Bundle getSkuDetails(int apiVersion, String packageName, String type, Bundle skusBundle) {
        Bundle response = new Bundle();
        ResponseCode responseCode = checkBillingEnabled(apiVersion);
        if (responseCode != ResponseCode.RESULT_OK) {
            response.putInt("RESPONSE_CODE", responseCode.responseCode());
        } else {
            responseCode = checkTypeSupported(type);
            if (responseCode != ResponseCode.RESULT_OK) {
                response.putInt("RESPONSE_CODE", responseCode.responseCode());
            } else if (skusBundle == null) {
                FinskyLog.w("Input Error: Non-null argument expected for skusBundle.", new Object[0]);
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
            } else {
                ArrayList<String> skuList = skusBundle.getStringArrayList("ITEM_ID_LIST");
                if (skuList == null) {
                    FinskyLog.w("Input Error: skusBundle must contain an array associated with key %s.", "ITEM_ID_LIST");
                    response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
                } else if (skuList.isEmpty()) {
                    FinskyLog.w("Input Error: skusBundle array associated with key %s cannot be empty.", "ITEM_ID_LIST");
                    response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
                } else if (skuList.size() > DETAILS_REQUEST_SKU_LIST_MAX_SIZE) {
                    FinskyLog.w("Input Error: skusBundle array associated with key %s cannot contain more than %d items.", "ITEM_ID_LIST", Integer.valueOf(DETAILS_REQUEST_SKU_LIST_MAX_SIZE));
                    response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
                } else {
                    for (int i = 0; i < skuList.size(); i++) {
                        if (TextUtils.isEmpty((CharSequence) skuList.get(i))) {
                            FinskyLog.w("Input Error: skusBundle array associated with key %s contains an empty/null sku at index %d.", "ITEM_ID_LIST", Integer.valueOf(i));
                            response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
                            break;
                        }
                    }
                    fetchSkuDetails(packageName, skuList, type, response);
                }
            }
        }
        return response;
    }

    public Bundle getBuyIntent(int apiVersion, String packageName, String sku, String type, String developerPayload) {
        Bundle response = new Bundle();
        ResponseCode responseCode = checkBillingEnabled(apiVersion);
        if (responseCode != ResponseCode.RESULT_OK) {
            response.putInt("RESPONSE_CODE", responseCode.responseCode());
        } else {
            responseCode = checkTypeSupported(type);
            if (responseCode != ResponseCode.RESULT_OK) {
                response.putInt("RESPONSE_CODE", responseCode.responseCode());
            } else if (TextUtils.isEmpty(sku)) {
                FinskyLog.w("Input Error: Non empty/null argument expected for sku.", new Object[0]);
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
            } else if (isDocumentInLibrary(type, sku, packageName)) {
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_ITEM_ALREADY_OWNED.responseCode());
            } else {
                response.putParcelable("BUY_INTENT", makePurchaseIntent(apiVersion, packageName, null, sku, type, developerPayload));
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_OK.responseCode());
            }
        }
        return response;
    }

    public Bundle getPurchases(int apiVersion, String packageName, String type, String continuationToken) {
        Bundle response = new Bundle();
        ResponseCode responseCode = checkBillingEnabled(apiVersion);
        if (responseCode != ResponseCode.RESULT_OK) {
            response.putInt("RESPONSE_CODE", responseCode.responseCode());
        } else {
            responseCode = checkTypeSupported(type);
            if (responseCode != ResponseCode.RESULT_OK) {
                response.putInt("RESPONSE_CODE", responseCode.responseCode());
            } else {
                populatePurchasesForPackage(packageName, type, continuationToken, response);
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_OK.responseCode());
            }
        }
        return response;
    }

    public int consumePurchase(int apiVersion, String packageName, String purchaseToken) {
        ResponseCode responseCode = checkBillingEnabled(apiVersion);
        if (responseCode != ResponseCode.RESULT_OK) {
            return responseCode.responseCode();
        }
        if (!TextUtils.isEmpty(purchaseToken)) {
            return consumeIabPurchase(packageName, purchaseToken).responseCode();
        }
        FinskyLog.w("Input Error: Non empty/null argument expected for purchaseToken.", new Object[0]);
        return ResponseCode.RESULT_DEVELOPER_ERROR.responseCode();
    }

    public int isPromoEligible(int apiVersion, String packageName, String type) throws RemoteException {
        ResponseCode responseCode = checkBillingEnabled(apiVersion);
        if (responseCode != ResponseCode.RESULT_OK) {
            return responseCode.responseCode();
        }
        responseCode = checkTypeSupported(type);
        if (responseCode != ResponseCode.RESULT_OK) {
            return responseCode.responseCode();
        }
        if (apiVersion >= 4) {
            return performIabPromoCheck(packageName, type).responseCode();
        }
        FinskyLog.w("Input Error: isPromoEligible was introduced in API version 4.", new Object[0]);
        return ResponseCode.RESULT_DEVELOPER_ERROR.responseCode();
    }

    public Bundle getBuyIntentToReplaceSkus(int apiVersion, String packageName, List<String> oldSkus, String newSku, String type, String developerPayload) {
        Bundle response = new Bundle();
        ResponseCode responseCode = checkBillingEnabled(apiVersion);
        if (responseCode != ResponseCode.RESULT_OK) {
            response.putInt("RESPONSE_CODE", responseCode.responseCode());
        } else if (apiVersion < 5) {
            FinskyLog.w("Input Error: getBuyIntentToReplaceSkus was introduced in API version 5.", new Object[0]);
            response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
        } else {
            responseCode = checkTypeSupported(type);
            if (responseCode != ResponseCode.RESULT_OK) {
                response.putInt("RESPONSE_CODE", responseCode.responseCode());
            } else if (TextUtils.isEmpty(newSku)) {
                FinskyLog.w("Input Error: Non empty/null argument expected for newSku.", new Object[0]);
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
            } else if (isDocumentInLibrary(type, newSku, packageName)) {
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_ITEM_ALREADY_OWNED.responseCode());
            } else {
                response.putParcelable("BUY_INTENT", makePurchaseIntent(apiVersion, packageName, oldSkus, newSku, type, developerPayload));
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_OK.responseCode());
            }
        }
        return response;
    }

    private ResponseCode checkBillingApiVersionSupport(int version) {
        if (version >= 3 && version <= 5) {
            return ResponseCode.RESULT_OK;
        }
        FinskyLog.w("Unsupported billing API version: %d", Integer.valueOf(version));
        return ResponseCode.RESULT_BILLING_UNAVAILABLE;
    }

    private ResponseCode checkTypeSupported(String type) {
        if (TextUtils.isEmpty(type)) {
            FinskyLog.w("Input Error: Non empty/null argument expected for type.", new Object[0]);
            return ResponseCode.RESULT_DEVELOPER_ERROR;
        } else if (!TextUtils.equals(type, "inapp") && !TextUtils.equals(type, "subs")) {
            FinskyLog.w("Unknown item type specified %s", type);
            return ResponseCode.RESULT_BILLING_UNAVAILABLE;
        } else if (!type.equals("subs") || ((Boolean) G.iabV3SubscriptionsEnabled.get()).booleanValue()) {
            return ResponseCode.RESULT_OK;
        } else {
            FinskyLog.w("Subscriptions are not supported", new Object[0]);
            return ResponseCode.RESULT_BILLING_UNAVAILABLE;
        }
    }

    private ResponseCode checkBillingEnabled(int billingApiVersion) {
        ResponseCode response = checkBillingApiVersionSupport(billingApiVersion);
        if (response != ResponseCode.RESULT_OK) {
            return response;
        }
        if (InAppBillingSetting.isEnabled(this.mDfeApi.getAccountName(), billingApiVersion)) {
            return ResponseCode.RESULT_OK;
        }
        FinskyLog.w("Billing unavailable for this package and user.", new Object[0]);
        return ResponseCode.RESULT_BILLING_UNAVAILABLE;
    }

    private PackageInfo getPackageInfo(String packageName) {
        try {
            return this.mContext.getPackageManager().getPackageInfo(packageName, 64);
        } catch (NameNotFoundException e) {
            FinskyLog.w("cannot find package name: %s", packageName);
            return null;
        }
    }

    private void fetchSkuDetails(String packageName, ArrayList<String> skuList, String type, final Bundle response) {
        List<String> docIds = Lists.newArrayList(skuList.size());
        for (int i = 0; i != skuList.size(); i++) {
            docIds.add(InAppBillingUtils.buildDocidStr((String) skuList.get(i), type, packageName));
        }
        final Semaphore semaphore = new Semaphore(0);
        this.mDfeApi.getDetails(docIds, packageName, true, new Listener<BulkDetailsResponse>() {
            public void onResponse(BulkDetailsResponse detailsResponse) {
                BulkDetailsEntry[] bulkDetailsEntry = detailsResponse.entry;
                ArrayList<String> detailsList = Lists.newArrayList();
                for (BulkDetailsEntry entry : bulkDetailsEntry) {
                    if (entry.doc != null) {
                        detailsList.add(InAppBillingManager.this.buildDetailsJson(entry));
                    }
                }
                response.putStringArrayList("DETAILS_LIST", detailsList);
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_OK.responseCode());
                semaphore.release();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_ERROR.responseCode());
                semaphore.release();
            }
        });
        try {
            if (!semaphore.tryAcquire(TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_ERROR.responseCode());
            }
        } catch (InterruptedException e) {
            response.putInt("RESPONSE_CODE", ResponseCode.RESULT_ERROR.responseCode());
        }
    }

    private String buildDetailsJson(BulkDetailsEntry entry) {
        JSONObject details = new JSONObject();
        try {
            DocV2 doc = entry.doc;
            Offer offer = doc.offer[0];
            String docId = doc.backendDocid;
            details.put("productId", DocUtils.extractSkuForInApp(docId));
            details.put("type", getInAppType(docId));
            details.put("price", offer.formattedAmount);
            if (offer.convertedPrice.length > 0) {
                details.put("price_amount_micros", offer.convertedPrice[0].micros);
                details.put("price_currency_code", offer.convertedPrice[0].currencyCode);
            } else {
                details.put("price_amount_micros", offer.micros);
                details.put("price_currency_code", offer.currencyCode);
            }
            details.put("title", doc.title);
            details.put("description", Html.fromHtml(doc.descriptionHtml));
        } catch (JSONException e) {
            FinskyLog.wtf("Exception when creating json: %s", e);
        }
        return details.toString();
    }

    private boolean isDocumentInLibrary(String itemType, String itemId, String packageName) {
        this.mLibraries.blockingLoad();
        return this.mLibraries.getAccountLibrary(this.mDfeApi.getAccount()).getInAppEntry(InAppBillingUtils.buildDocidStr(itemId, itemType, packageName)) != null;
    }

    private String getInAppType(String docId) {
        if (docId.startsWith("inapp:")) {
            return "inapp";
        }
        if (docId.startsWith("subs:")) {
            return "subs";
        }
        return null;
    }

    private PendingIntent makePurchaseIntent(int billingApiVersion, String packageName, List<String> oldSkus, String newSku, String itemType, String developerPayload) {
        PackageInfo packageInfo = getPackageInfo(packageName);
        String signatureHash = computeSignatureHash(packageInfo);
        List<String> oldSkusAsDocidStrings = null;
        if (!(oldSkus == null || oldSkus.isEmpty())) {
            oldSkusAsDocidStrings = new ArrayList(oldSkus.size());
            for (String oldSku : oldSkus) {
                oldSkusAsDocidStrings.add(InAppBillingUtils.buildDocidStr(oldSku, itemType, packageName));
            }
        }
        String newSkuAsDocidStr = InAppBillingUtils.buildDocidStr(newSku, itemType, packageName);
        return PendingIntent.getActivity(this.mContext, 0, IabV3Activity.createIntent(this.mDfeApi.getAccount(), PurchaseParams.builder().setDocid(InAppBillingUtils.buildDocid(newSkuAsDocidStr, itemType)).setDocidStr(newSkuAsDocidStr).setOfferType(1).setIabParameters(new IabParameters(billingApiVersion, packageName, packageInfo.versionCode, signatureHash, developerPayload, oldSkusAsDocidStrings)).build()), 1073741824);
    }

    private String computeSignatureHash(PackageInfo packageInfo) {
        return Md5Util.secureHash(packageInfo.signatures[0].toByteArray());
    }

    private void populatePurchasesForPackage(String packageName, String type, String continuationToken, Bundle currentPurchases) {
        AccountLibrary library = this.mLibraries.getAccountLibrary(this.mDfeApi.getAccount());
        ArrayList<String> docIdList = new ArrayList();
        ArrayList<String> transactionList = new ArrayList();
        ArrayList<String> signatureList = new ArrayList();
        if (type.equals("inapp")) {
            Pair<List<LibraryInAppEntry>, String> listAndToken = getListAndContinuationToken(library.getInAppPurchasesForPackage(packageName), continuationToken);
            List<LibraryInAppEntry> inAppList = listAndToken.first;
            continuationToken = listAndToken.second;
            for (LibraryInAppEntry entry : inAppList) {
                docIdList.add(DocUtils.extractSkuForInApp(entry.getDocId()));
                transactionList.add(entry.signedPurchaseData);
                signatureList.add(entry.signature);
            }
        } else {
            if (type.equals("subs")) {
                Pair<List<LibraryInAppSubscriptionEntry>, String> listAndToken2 = getListAndContinuationToken(library.getSubscriptionPurchasesForPackage(packageName), continuationToken);
                List<LibraryInAppSubscriptionEntry> subsList = listAndToken2.first;
                continuationToken = listAndToken2.second;
                for (LibraryInAppSubscriptionEntry entry2 : subsList) {
                    docIdList.add(DocUtils.extractSkuForInApp(entry2.getDocId()));
                    transactionList.add(entry2.signedPurchaseData);
                    signatureList.add(entry2.signature);
                }
            }
        }
        currentPurchases.putStringArrayList("INAPP_PURCHASE_ITEM_LIST", docIdList);
        currentPurchases.putStringArrayList("INAPP_PURCHASE_DATA_LIST", transactionList);
        currentPurchases.putStringArrayList("INAPP_DATA_SIGNATURE_LIST", signatureList);
        if (continuationToken != null) {
            currentPurchases.putString("INAPP_CONTINUATION_TOKEN", continuationToken);
        }
    }

    private static <T> Pair<List<T>, String> getListAndContinuationToken(List<T> docIdList, String continuationToken) {
        if (docIdList.size() <= MAX_PURCHASES_IN_RESPONSE) {
            return Pair.create(docIdList, null);
        }
        int start = 0;
        if (!TextUtils.isEmpty(continuationToken)) {
            start = tokenToPosition(continuationToken);
            if (start < 0 || start >= docIdList.size()) {
                start = 0;
            }
        }
        int end = start + MAX_PURCHASES_IN_RESPONSE;
        if (end < docIdList.size()) {
            continuationToken = positionToToken(end);
        } else {
            end = docIdList.size();
            continuationToken = null;
        }
        return Pair.create(docIdList.subList(start, end), continuationToken);
    }

    private static String positionToToken(int value) {
        return Base64.encodeToString(("CONT-TOKEN-" + value).getBytes(), 0);
    }

    private static int tokenToPosition(String token) {
        String tokenDecoded = new String(Base64.decode(token, 0));
        if (tokenDecoded.startsWith("CONT-TOKEN-")) {
            return Integer.parseInt(tokenDecoded.substring("CONT-TOKEN-".length()));
        }
        return -1;
    }

    private ResponseCode consumeIabPurchase(String packageName, String purchaseToken) {
        final Semaphore semaphore = new Semaphore(0);
        final ResponseCode[] responseCode = new ResponseCode[]{ResponseCode.RESULT_OK};
        this.mDfeApi.consumePurchase(purchaseToken, 1, packageName, new Listener<ConsumePurchaseResponse>() {
            public void onResponse(final ConsumePurchaseResponse consumeResponse) {
                InAppBillingManager.this.mLibraryReplicators.applyLibraryUpdates(InAppBillingManager.this.mDfeApi.getAccount(), "consumePurchase", new Runnable() {
                    public void run() {
                        switch (consumeResponse.status) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                responseCode[0] = ResponseCode.RESULT_OK;
                                break;
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                responseCode[0] = ResponseCode.RESULT_DEVELOPER_ERROR;
                                break;
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                responseCode[0] = ResponseCode.RESULT_ITEM_NOT_OWNED;
                                break;
                            default:
                                responseCode[0] = ResponseCode.RESULT_ERROR;
                                break;
                        }
                        semaphore.release();
                    }
                }, consumeResponse.libraryUpdate);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.w("Error response on confirmPurchase: %s", error.toString());
                responseCode[0] = ResponseCode.RESULT_ERROR;
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                return responseCode[0];
            }
            return ResponseCode.RESULT_ERROR;
        } catch (InterruptedException e) {
            return ResponseCode.RESULT_ERROR;
        }
    }

    private ResponseCode performIabPromoCheck(String packageName, String type) {
        final Semaphore semaphore = new Semaphore(0);
        final ResponseCode[] responseCode = new ResponseCode[]{ResponseCode.RESULT_ERROR};
        int docType = 11;
        if (type.equals("subs")) {
            docType = 15;
        }
        this.mDfeApi.checkIabPromo(docType, packageName, CarrierBillingUtils.getSimIdentifier(), new Listener<CheckIabPromoResponse>() {
            public void onResponse(CheckIabPromoResponse response) {
                responseCode[0] = response.eligible ? ResponseCode.RESULT_PROMO_ELIGIBLE : ResponseCode.RESULT_NOT_PROMO_ELIGIBLE;
                semaphore.release();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.w("Error response on checkIabPromo: %s", error.toString());
                responseCode[0] = ResponseCode.RESULT_ERROR;
                semaphore.release();
            }
        });
        try {
            if (!semaphore.tryAcquire(TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                responseCode[0] = ResponseCode.RESULT_ERROR;
            }
            return responseCode[0];
        } catch (InterruptedException e) {
            FinskyLog.d("Interrupted: %s", e.getMessage());
            return ResponseCode.RESULT_ERROR;
        }
    }
}
