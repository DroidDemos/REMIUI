package com.google.android.finsky.billing.iab;

import android.accounts.Account;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Pair;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.IabParameters;
import com.google.android.finsky.billing.InAppBillingSetting;
import com.google.android.finsky.billing.iab.InAppBillingUtils.ResponseCode;
import com.google.android.finsky.billing.lightpurchase.IabV2Activity;
import com.google.android.finsky.billing.lightpurchase.PurchaseParams;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.VendingProtos.AckNotificationsRequestProto;
import com.google.android.finsky.protos.VendingProtos.AckNotificationsResponseProto;
import com.google.android.finsky.protos.VendingProtos.InAppPurchaseInformationRequestProto;
import com.google.android.finsky.protos.VendingProtos.InAppPurchaseInformationResponseProto;
import com.google.android.finsky.protos.VendingProtos.InAppRestoreTransactionsRequestProto;
import com.google.android.finsky.protos.VendingProtos.InAppRestoreTransactionsResponseProto;
import com.google.android.finsky.protos.VendingProtos.PurchaseResultProto;
import com.google.android.finsky.protos.VendingProtos.SignatureHashProto;
import com.google.android.finsky.protos.VendingProtos.StatusBarNotificationProto;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Md5Util;
import com.google.android.finsky.utils.Sets;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Random;
import java.util.Set;

public class MarketBillingService extends Service {
    private static final String[] EMPTY_STRING_ARRAY;
    private static Random sRandom;
    private final com.android.vending.billing.IMarketBillingService.Stub mBinder;
    BillingNotifier mNotifier;
    PackageManager mPackageManager;
    UidProvider mUidProvider;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest;

        static {
            $SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest = new int[BillingRequest.values().length];
            try {
                $SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest[BillingRequest.CHECK_BILLING_SUPPORTED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest[BillingRequest.REQUEST_PURCHASE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest[BillingRequest.GET_PURCHASE_INFORMATION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest[BillingRequest.RESTORE_TRANSACTIONS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest[BillingRequest.CONFIRM_NOTIFICATIONS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    protected static class BillingNotifier {
        private MarketBillingService mService;

        public BillingNotifier(MarketBillingService service) {
            this.mService = service;
        }

        protected void showStatusBarNotifications(Context context, String packageName, InAppPurchaseInformationResponseProto response) {
            String data = response.signedResponse.signedData;
            String signature = response.signedResponse.signature;
            for (StatusBarNotificationProto notification : response.statusBarNotification) {
                String tickerText = notification.tickerText;
                String contentTitle = notification.contentTitle;
                String contentText = notification.contentText;
                Intent intent = this.mService.mPackageManager.getLaunchIntentForPackage(packageName);
                intent.putExtra("inapp_signed_data", data);
                intent.putExtra("inapp_signature", signature);
                FinskyApp.get().getNotifier().showNotification(packageName, tickerText, contentTitle, contentText, 17301642, intent, "status");
            }
        }

        protected boolean sendPurchaseStateChanged(String packageName, String data, String signature) {
            Intent intent = IntentUtils.createIntentForReceiver(this.mService.mPackageManager, packageName, new Intent("com.android.vending.billing.PURCHASE_STATE_CHANGED"));
            if (intent == null) {
                return false;
            }
            intent.putExtra("inapp_signed_data", data);
            intent.putExtra("inapp_signature", signature);
            this.mService.sendBroadcast(intent);
            return true;
        }

        protected boolean sendResponseCode(String packageName, long requestId, ResponseCode responseCode) {
            return MarketBillingService.sendResponseCode(this.mService, packageName, requestId, responseCode);
        }
    }

    private enum BillingRequest {
        CHECK_BILLING_SUPPORTED,
        REQUEST_PURCHASE,
        GET_PURCHASE_INFORMATION,
        RESTORE_TRANSACTIONS,
        CONFIRM_NOTIFICATIONS
    }

    class Stub extends com.android.vending.billing.IMarketBillingService.Stub {

        private class NotifyingErrorListener implements ErrorListener {
            private final String mPackageName;
            private final long mRequestId;

            public NotifyingErrorListener(String packageName, long requestId) {
                this.mPackageName = packageName;
                this.mRequestId = requestId;
            }

            public void onErrorResponse(VolleyError error) {
                FinskyLog.e("Server error on InAppPurchaseInformationRequest: %s", error);
                MarketBillingService.this.mNotifier.sendResponseCode(this.mPackageName, this.mRequestId, ResponseCode.RESULT_SERVICE_UNAVAILABLE);
            }
        }

        Stub() {
        }

        public Bundle sendBillingRequest(Bundle arguments) {
            Bundle response;
            BillingRequest billingRequest = getBillingRequest(arguments);
            if (billingRequest == null) {
                response = new Bundle();
                response.putInt("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.ordinal());
            } else {
                response = handleBillingRequest(billingRequest, arguments);
            }
            MarketBillingService.this.stopSelf();
            return response;
        }

        private Bundle handleBillingRequest(BillingRequest billingRequest, Bundle arguments) {
            int billingApiVersion = arguments.getInt("API_VERSION", -1);
            String packageName = arguments.getString("PACKAGE_NAME");
            String developerPayload = arguments.getString("DEVELOPER_PAYLOAD");
            String itemId = arguments.getString("ITEM_ID");
            String itemType = arguments.getString("ITEM_TYPE");
            long nonce = arguments.getLong("NONCE");
            String[] notifyIds = arguments.getStringArray("NOTIFY_IDS");
            Bundle response = new Bundle();
            ResponseCode responseCode = checkBillingEnabled(billingApiVersion, packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return setResponseCode(response, responseCode);
            }
            switch (AnonymousClass1.$SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest[billingRequest.ordinal()]) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    if (argumentsMatch(arguments, new String[]{"PACKAGE_NAME"}, new String[]{"ITEM_TYPE"})) {
                        if (itemType == null) {
                            itemType = "inapp";
                        }
                        responseCode = checkTypeSupported(billingApiVersion, itemType);
                        break;
                    }
                    return setResponseCode(response, ResponseCode.RESULT_DEVELOPER_ERROR);
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    if (argumentsMatch(arguments, new String[]{"PACKAGE_NAME", "ITEM_ID"}, new String[]{"DEVELOPER_PAYLOAD", "ITEM_TYPE"})) {
                        if (itemType == null) {
                            itemType = "inapp";
                        }
                        responseCode = checkTypeSupported(billingApiVersion, itemType);
                        if (responseCode == ResponseCode.RESULT_OK) {
                            Pair<Long, PendingIntent> requestIntentPair = requestPurchase(billingApiVersion, packageName, itemId, itemType, developerPayload);
                            response.putLong("REQUEST_ID", ((Long) requestIntentPair.first).longValue());
                            response.putParcelable("PURCHASE_INTENT", (Parcelable) requestIntentPair.second);
                            break;
                        }
                        return setResponseCode(response, responseCode);
                    }
                    return setResponseCode(response, ResponseCode.RESULT_DEVELOPER_ERROR);
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    if (argumentsMatchExactly(arguments, "PACKAGE_NAME", "NONCE", "NOTIFY_IDS")) {
                        responseCode = updateWithRequestId(response, getPurchaseInformation(billingApiVersion, packageName, nonce, notifyIds));
                        break;
                    }
                    return setResponseCode(response, ResponseCode.RESULT_DEVELOPER_ERROR);
                case R.styleable.WalletImFormEditText_required /*4*/:
                    if (argumentsMatchExactly(arguments, "PACKAGE_NAME", "NONCE")) {
                        responseCode = updateWithRequestId(response, restoreTransactions(billingApiVersion, packageName, nonce));
                        break;
                    }
                    return setResponseCode(response, ResponseCode.RESULT_DEVELOPER_ERROR);
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    if (argumentsMatchExactly(arguments, "PACKAGE_NAME", "NOTIFY_IDS")) {
                        responseCode = updateWithRequestId(response, confirmNotifications(billingApiVersion, packageName, notifyIds));
                        break;
                    }
                    return setResponseCode(response, ResponseCode.RESULT_DEVELOPER_ERROR);
                default:
                    FinskyLog.wtf("enum %s", billingRequest);
                    return setResponseCode(response, ResponseCode.RESULT_DEVELOPER_ERROR);
            }
            return setResponseCode(response, responseCode);
        }

        private Bundle setResponseCode(Bundle response, ResponseCode code) {
            response.putInt("RESPONSE_CODE", code.ordinal());
            return response;
        }

        private BillingRequest getBillingRequest(Bundle arguments) {
            BillingRequest billingRequest = null;
            String billingRequestString = arguments.getString("BILLING_REQUEST");
            if (billingRequestString == null) {
                FinskyLog.w("Received bundle without billing request type", new Object[0]);
            } else {
                try {
                    billingRequest = BillingRequest.valueOf(billingRequestString);
                } catch (IllegalArgumentException e) {
                    FinskyLog.w("Unknown billing request type: %s", billingRequestString);
                }
            }
            return billingRequest;
        }

        private ResponseCode updateWithRequestId(Bundle bundle, long requestId) {
            if (requestId == -1) {
                return ResponseCode.RESULT_ERROR;
            }
            bundle.putLong("REQUEST_ID", requestId);
            return ResponseCode.RESULT_OK;
        }

        private boolean argumentsMatchExactly(Bundle bundle, String... requiredArguments) {
            return argumentsMatch(bundle, requiredArguments, MarketBillingService.EMPTY_STRING_ARRAY);
        }

        private boolean argumentsMatch(Bundle bundle, String[] requiredArguments, String[] optionalArguments) {
            Set<String> keys = bundle.keySet();
            Set<String> expectedKeys = Sets.newHashSet();
            Set<String> optionalKeys = Sets.newHashSet();
            expectedKeys.add("BILLING_REQUEST");
            expectedKeys.add("API_VERSION");
            for (String argument : requiredArguments) {
                expectedKeys.add(argument);
            }
            for (String argument2 : optionalArguments) {
                optionalKeys.add(argument2);
            }
            keys.removeAll(optionalKeys);
            return keys.equals(expectedKeys);
        }

        public ResponseCode checkBillingEnabled(int billingApiVersion, String packageName) {
            ResponseCode response = checkBillingApiVersionSupport(billingApiVersion);
            if (response != ResponseCode.RESULT_OK) {
                return response;
            }
            if (packageName == null) {
                FinskyLog.w("No packageName given!", new Object[0]);
                return ResponseCode.RESULT_DEVELOPER_ERROR;
            } else if (!isBillingEnabledForPackage(packageName, billingApiVersion)) {
                FinskyLog.d("Billing unavailable for this package.", new Object[0]);
                return ResponseCode.RESULT_BILLING_UNAVAILABLE;
            } else if (getPackageInfo(packageName) != null) {
                return ResponseCode.RESULT_OK;
            } else {
                FinskyLog.d("No package info for %s", packageName);
                return ResponseCode.RESULT_ERROR;
            }
        }

        public ResponseCode checkTypeSupported(int billingApiVersion, String type) {
            if (type.equals("inapp") || type.equals("subs")) {
                if (billingApiVersion == 1) {
                    if (!type.equals("inapp")) {
                        FinskyLog.d("Item type %s not supported in billing api version %d", type, Integer.valueOf(billingApiVersion));
                        return ResponseCode.RESULT_DEVELOPER_ERROR;
                    }
                } else if (billingApiVersion == 2 && type.equals("subs") && !((Boolean) G.subscriptionsEnabled.get()).booleanValue()) {
                    return ResponseCode.RESULT_BILLING_UNAVAILABLE;
                }
                return ResponseCode.RESULT_OK;
            }
            FinskyLog.d("Unknown item type specified %s", type);
            return ResponseCode.RESULT_BILLING_UNAVAILABLE;
        }

        public ResponseCode checkBillingApiVersionSupport(int version) {
            if (version <= 0) {
                FinskyLog.w("No billing API version given!", new Object[0]);
                return ResponseCode.RESULT_DEVELOPER_ERROR;
            } else if (version <= 2) {
                return ResponseCode.RESULT_OK;
            } else {
                FinskyLog.d("Unsupported (future) billing API version: %d", Integer.valueOf(version));
                return ResponseCode.RESULT_BILLING_UNAVAILABLE;
            }
        }

        public Pair<Long, PendingIntent> requestPurchase(int billingApiVersion, String packageName, String itemId, String itemType, String developerPayload) {
            Account account = MarketBillingService.this.getPreferredAccount(packageName);
            PackageInfo packageInfo = getPackageInfo(packageName);
            if (packageInfo == null || !isBillingEnabledForAccount(account, billingApiVersion)) {
                return null;
            }
            long requestId = getNextInAppRequestId();
            String signatureHash = computeSignatureHash(packageInfo);
            String docidStr = InAppBillingUtils.buildDocidStr(itemId, itemType, packageName);
            return Pair.create(Long.valueOf(requestId), PendingIntent.getActivity(MarketBillingService.this, 0, IabV2Activity.createIntent(account, PurchaseParams.builder().setDocid(InAppBillingUtils.buildDocid(docidStr, itemType)).setDocidStr(docidStr).setOfferType(1).setIabParameters(new IabParameters(billingApiVersion, packageName, packageInfo.versionCode, signatureHash, developerPayload, null)).build(), requestId), 1073741824));
        }

        private String computeSignatureHash(PackageInfo packageInfo) {
            return Md5Util.secureHash(packageInfo.signatures[0].toByteArray());
        }

        public long getPurchaseInformation(int billingApiVersion, final String packageName, long nonce, String[] notifyIds) {
            PackageInfo packageInfo = getPackageInfo(packageName);
            if (packageInfo == null) {
                return -1;
            }
            final long requestId = getNextInAppRequestId();
            Account account = MarketBillingService.this.getPreferredAccount(packageName);
            if (!isBillingEnabledForAccount(account, billingApiVersion)) {
                MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, ResponseCode.RESULT_BILLING_UNAVAILABLE);
                return requestId;
            } else if (isRequestAllowed(packageName)) {
                InAppPurchaseInformationRequestProto request = new InAppPurchaseInformationRequestProto();
                request.billingApiVersion = billingApiVersion;
                request.hasBillingApiVersion = true;
                request.signatureHash = makeSignatureHash(packageInfo);
                request.signatureAlgorithm = "SHA1withRSA";
                request.hasSignatureAlgorithm = true;
                request.nonce = nonce;
                request.hasNonce = true;
                request.notificationId = notifyIds;
                FinskyApp.get().getVendingApi(account.name).getInAppPurchaseInformation(request, new Listener<InAppPurchaseInformationResponseProto>() {
                    public void onResponse(InAppPurchaseInformationResponseProto response) {
                        MarketBillingService.this.mNotifier.sendPurchaseStateChanged(packageName, response.signedResponse.signedData, response.signedResponse.signature);
                        MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, MarketBillingService.this.purchaseResultToResponseCode(response.purchaseResult));
                        MarketBillingService.this.mNotifier.showStatusBarNotifications(MarketBillingService.this, packageName, response);
                    }
                }, new NotifyingErrorListener(packageName, requestId));
                return requestId;
            } else {
                MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, ResponseCode.RESULT_ERROR);
                return requestId;
            }
        }

        public long confirmNotifications(int billingApiVersion, final String packageName, String[] notifyIds) {
            PackageInfo packageInfo = getPackageInfo(packageName);
            if (packageInfo == null) {
                return -1;
            }
            final long requestId = getNextInAppRequestId();
            Account account = MarketBillingService.this.getPreferredAccount(packageName);
            if (!isBillingEnabledForAccount(account, billingApiVersion)) {
                MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, ResponseCode.RESULT_BILLING_UNAVAILABLE);
                return requestId;
            } else if (isRequestAllowed(packageName)) {
                AckNotificationsRequestProto request = new AckNotificationsRequestProto();
                request.signatureHash = makeSignatureHash(packageInfo);
                request.notificationId = notifyIds;
                FinskyApp.get().getVendingApi(account.name).ackNotifications(request, new Listener<AckNotificationsResponseProto>() {
                    public void onResponse(AckNotificationsResponseProto response) {
                        MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, ResponseCode.RESULT_OK);
                    }
                }, new NotifyingErrorListener(packageName, requestId));
                return requestId;
            } else {
                MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, ResponseCode.RESULT_ERROR);
                return requestId;
            }
        }

        public long restoreTransactions(int billingApiVersion, final String packageName, long nonce) {
            PackageInfo packageInfo = getPackageInfo(packageName);
            if (packageInfo == null) {
                return -1;
            }
            final long requestId = getNextInAppRequestId();
            Account account = MarketBillingService.this.getPreferredAccount(packageName);
            if (!isBillingEnabledForAccount(account, billingApiVersion)) {
                MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, ResponseCode.RESULT_BILLING_UNAVAILABLE);
                return requestId;
            } else if (isRequestAllowed(packageName)) {
                InAppRestoreTransactionsRequestProto request = new InAppRestoreTransactionsRequestProto();
                request.billingApiVersion = billingApiVersion;
                request.hasBillingApiVersion = true;
                request.signatureHash = makeSignatureHash(packageInfo);
                request.signatureAlgorithm = "SHA1withRSA";
                request.hasSignatureAlgorithm = true;
                request.nonce = nonce;
                request.hasNonce = true;
                FinskyApp.get().getVendingApi(account.name).restoreInAppTransactions(request, new Listener<InAppRestoreTransactionsResponseProto>() {
                    public void onResponse(InAppRestoreTransactionsResponseProto response) {
                        if (response.signedResponse != null) {
                            MarketBillingService.this.mNotifier.sendPurchaseStateChanged(packageName, response.signedResponse.signedData, response.signedResponse.signature);
                        }
                        if (response.purchaseResult != null) {
                            MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, MarketBillingService.this.purchaseResultToResponseCode(response.purchaseResult));
                        }
                    }
                }, new NotifyingErrorListener(packageName, requestId));
                return requestId;
            } else {
                MarketBillingService.this.mNotifier.sendResponseCode(packageName, requestId, ResponseCode.RESULT_ERROR);
                return requestId;
            }
        }

        private long getNextInAppRequestId() {
            return MarketBillingService.sRandom.nextLong() & Long.MAX_VALUE;
        }

        private PackageInfo getPackageInfo(String packageName) {
            try {
                return MarketBillingService.this.mPackageManager.getPackageInfo(packageName, 64);
            } catch (NameNotFoundException e) {
                FinskyLog.w("cannot find package name: %s", packageName);
                return null;
            }
        }

        private SignatureHashProto makeSignatureHash(PackageInfo packageInfo) {
            SignatureHashProto signatureHash = new SignatureHashProto();
            signatureHash.packageName = packageInfo.packageName;
            signatureHash.hasPackageName = true;
            signatureHash.versionCode = packageInfo.versionCode;
            signatureHash.hasVersionCode = true;
            signatureHash.hash = Md5Util.secureHashBytes(packageInfo.signatures[0].toByteArray());
            return signatureHash;
        }

        private boolean isBillingEnabledForPackage(String packageName, int billingApiVersion) {
            return isBillingEnabledForAccount(MarketBillingService.this.getPreferredAccount(packageName), billingApiVersion);
        }

        private boolean isBillingEnabledForAccount(Account account, int billingApiVersion) {
            return account != null && InAppBillingSetting.isEnabled(account.name, billingApiVersion);
        }

        private boolean isRequestAllowed(String packageName) {
            return true;
        }
    }

    public static class UidProvider {
    }

    public MarketBillingService() {
        this.mUidProvider = new UidProvider();
        this.mNotifier = new BillingNotifier(this);
        this.mBinder = new Stub();
    }

    static {
        EMPTY_STRING_ARRAY = new String[0];
        sRandom = new Random();
    }

    public void onCreate() {
        super.onCreate();
        this.mPackageManager = getPackageManager();
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public static boolean sendResponseCode(Context context, String packageName, long requestId, ResponseCode responseCode) {
        Intent intent = IntentUtils.createIntentForReceiver(context.getPackageManager(), packageName, new Intent("com.android.vending.billing.RESPONSE_CODE"));
        if (intent == null) {
            FinskyLog.d("Response %s cannot be delivered to %s. Intent does not resolve.", responseCode.name(), packageName);
            return false;
        }
        FinskyLog.d("Sending response %s for request %d to %s.", responseCode.name(), Long.valueOf(requestId), packageName);
        intent.putExtra("request_id", requestId);
        intent.putExtra("response_code", responseCode.ordinal());
        context.sendBroadcast(intent);
        return true;
    }

    public static boolean sendNotify(Context context, String packageName, String notifyIds) {
        Intent intent = IntentUtils.createIntentForReceiver(context.getPackageManager(), packageName, new Intent("com.android.vending.billing.IN_APP_NOTIFY"));
        if (intent == null) {
            return false;
        }
        intent.putExtra("notification_id", notifyIds);
        context.sendBroadcast(intent);
        return true;
    }

    private ResponseCode purchaseResultToResponseCode(PurchaseResultProto result) {
        switch (result.resultCode) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return ResponseCode.RESULT_OK;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return ResponseCode.RESULT_ITEM_UNAVAILABLE;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return ResponseCode.RESULT_DEVELOPER_ERROR;
            default:
                return ResponseCode.RESULT_ERROR;
        }
    }

    private Account getPreferredAccount(String packageName) {
        return InAppBillingUtils.getPreferredAccount(packageName, this);
    }
}
