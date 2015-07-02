package com.google.android.play.analytics;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.play.analytics.ClientAnalytics.ActiveExperiments;
import com.google.android.play.analytics.ClientAnalytics.AndroidClientInfo;
import com.google.android.play.analytics.ClientAnalytics.ClientInfo;
import com.google.android.play.analytics.ClientAnalytics.LogEvent;
import com.google.android.play.analytics.ClientAnalytics.LogEventKeyValues;
import com.google.android.play.analytics.ClientAnalytics.LogRequest;
import com.google.android.play.analytics.ClientAnalytics.LogResponse;
import com.google.android.play.analytics.RollingFileStream.WriteCallbacks;
import com.google.android.play.utils.Assertions;
import com.google.android.volley.guava.UrlRules;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Semaphore;
import java.util.zip.GZIPOutputStream;

public final class EventLogger implements WriteCallbacks<LogEvent> {
    private static final ActiveExperiments EMPTY_EXPERIMENTS;
    private static final HashSet<Account> sInstantiatedAccounts;
    private final Account mAccount;
    private final long mAndroidId;
    private final String mAppVersion;
    private final String mAuthTokenType;
    private final Context mContext;
    private final String mCountry;
    private final long mDelayBetweenUploadsMs;
    private final long mDeviceId;
    private final Handler mHandler;
    private ActiveExperiments mLastSentExperiments;
    private final int mLogSource;
    private final String mLoggingId;
    private final long mMaxUploadSize;
    private final String mMccmnc;
    private final long mMinDelayBetweenUploadsMs;
    private final long mMinImmediateUploadSize;
    private volatile long mNextAllowedUploadTimeMs;
    private final ProtoCache mProtoCache;
    private final ContentResolver mResolver;
    private final RollingFileStream<LogEvent> mRollingFileStream;
    private final int mServerTimeoutMs;
    private final String mServerUrl;
    private final String mUserAgent;

    public static class Configuration {
        public long delayBetweenUploadsMs;
        public String logDirectoryName;
        public int mServerTimeoutMs;
        public String mServerUrl;
        public long maxStorageSize;
        public long minDelayBetweenUploadsMs;
        public long recommendedLogFileSize;

        public Configuration() {
            this.logDirectoryName = "logs";
            this.maxStorageSize = 2097152;
            this.recommendedLogFileSize = 51200;
            this.delayBetweenUploadsMs = 300000;
            this.minDelayBetweenUploadsMs = 60000;
            this.mServerUrl = "https://play.googleapis.com/log";
            this.mServerTimeoutMs = 10000;
        }
    }

    public enum LogSource {
        MARKET(0),
        MUSIC(1),
        BOOKS(2),
        VIDEO(3),
        MAGAZINES(4),
        GAMES(5),
        LB_A(6),
        ANDROID_IDE(7),
        LB_P(8),
        LB_S(9),
        GMS_CORE(10),
        CW(27),
        UDR(30),
        NEWSSTAND(63);
        
        private final int mProtoValue;

        private LogSource(int protovalue) {
            this.mProtoValue = protovalue;
        }

        public int getProtoValue() {
            return this.mProtoValue;
        }
    }

    static {
        EMPTY_EXPERIMENTS = new ActiveExperiments();
        sInstantiatedAccounts = new HashSet();
    }

    public EventLogger(Context context, String loggingId, String authTokenType, LogSource logSource, String useragent, long androidId, String appVersion, String mccmnc, Configuration config, Account account) {
        this(context, loggingId, authTokenType, logSource, useragent, androidId, appVersion, mccmnc, Locale.getDefault().getCountry(), config, account);
    }

    public EventLogger(Context context, String loggingId, String authTokenType, LogSource logSource, String useragent, long androidId, String appVersion, String mccmnc, String country, Configuration config, Account account) {
        synchronized (sInstantiatedAccounts) {
            Assertions.checkState(sInstantiatedAccounts.add(account), "Already instantiated an EventLogger for " + account);
        }
        this.mContext = context;
        this.mResolver = context.getContentResolver();
        this.mLogSource = logSource.getProtoValue();
        this.mLoggingId = loggingId;
        this.mAccount = account;
        this.mAuthTokenType = authTokenType;
        this.mProtoCache = ProtoCache.getInstance();
        this.mUserAgent = useragent;
        this.mAndroidId = androidId;
        String deviceIdString = Secure.getString(this.mResolver, "android_id");
        long deviceId = 0;
        try {
            deviceId = new BigInteger(deviceIdString, 16).longValue();
        } catch (NumberFormatException e) {
            Log.e("PlayEventLogger", "Invalid device id " + deviceIdString);
        } catch (NullPointerException e2) {
            Log.e("PlayEventLogger", "Null device id");
        }
        this.mDeviceId = deviceId;
        this.mAppVersion = appVersion;
        this.mMccmnc = mccmnc;
        this.mCountry = country;
        this.mServerUrl = config.mServerUrl;
        this.mDelayBetweenUploadsMs = config.delayBetweenUploadsMs;
        this.mMinDelayBetweenUploadsMs = config.minDelayBetweenUploadsMs;
        this.mServerTimeoutMs = config.mServerTimeoutMs;
        this.mMinImmediateUploadSize = ((config.recommendedLogFileSize * 50) / 100) + 1;
        this.mMaxUploadSize = (config.recommendedLogFileSize * 125) / 100;
        File baseDirectory = new File(this.mContext.getCacheDir(), config.logDirectoryName);
        File userDirectory = new File(baseDirectory, Uri.encode(account == null ? "null_account" : account.type + "." + account.name));
        maybeRenameLegacyDir(account, baseDirectory, userDirectory);
        this.mRollingFileStream = new RollingFileStream(userDirectory, "eventlog.store", ".log", config.recommendedLogFileSize, config.maxStorageSize, this);
        this.mHandler = new Handler(startHandlerThread().getLooper()) {
            public void dispatchMessage(Message msg) {
                EventLogger.this.dispatchMessage(msg);
            }
        };
        this.mHandler.sendEmptyMessage(1);
    }

    private static HandlerThread startHandlerThread() {
        final Semaphore semaphore = new Semaphore(0);
        HandlerThread handlerThread = new HandlerThread("PlayEventLogger", 10) {
            protected void onLooperPrepared() {
                semaphore.release();
            }
        };
        handlerThread.start();
        semaphore.acquireUninterruptibly();
        return handlerThread;
    }

    public void logEvent(String tag, ActiveExperiments experiments, byte[] sourceExtension, String... extras) {
        logEvent(tag, experiments, sourceExtension, System.currentTimeMillis(), extras);
    }

    private void logEvent(String tag, ActiveExperiments experiments, byte[] sourceExtension, long eventTimeMs, String... extras) {
        boolean z = extras == null || extras.length % 2 == 0;
        Assertions.checkState(z, "Extras must be null or of even length.");
        LogEvent event = this.mProtoCache.obtainEvent();
        event.eventTimeMs = eventTimeMs;
        event.timezoneOffsetSeconds = (long) (TimeZone.getDefault().getRawOffset() / 1000);
        event.tag = tag;
        event.exp = experiments;
        if (sourceExtension != null) {
            event.sourceExtension = sourceExtension;
        }
        if (!(extras == null || extras.length == 0)) {
            int numPairs = extras.length / 2;
            event.value = new LogEventKeyValues[numPairs];
            for (int i = 0; i < numPairs; i++) {
                LogEventKeyValues keyValue = this.mProtoCache.obtainKeyValue();
                int keyIndex = i * 2;
                keyValue.key = extras[keyIndex];
                keyValue.value = extras[keyIndex + 1] != null ? extras[keyIndex + 1] : "null";
                event.value[i] = keyValue;
            }
        }
        this.mHandler.obtainMessage(2, event).sendToTarget();
    }

    private void queueUpload(long delayMs) {
        long currentTimeMillis = System.currentTimeMillis();
        if (delayMs > 0) {
            if (currentTimeMillis + delayMs < this.mNextAllowedUploadTimeMs) {
                delayMs = this.mNextAllowedUploadTimeMs - currentTimeMillis;
            }
            this.mHandler.sendEmptyMessageDelayed(3, delayMs);
        } else {
            this.mHandler.sendEmptyMessage(3);
        }
        this.mNextAllowedUploadTimeMs = Math.max(this.mNextAllowedUploadTimeMs, this.mMinDelayBetweenUploadsMs + currentTimeMillis);
    }

    private void maybeQueueImmediateUpload() {
        if (this.mRollingFileStream.totalUnreadFileLength() >= this.mMinImmediateUploadSize) {
            queueUpload(0);
        }
    }

    private void dispatchMessage(Message msg) {
        switch (msg.what) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                maybeQueueImmediateUpload();
                queueUpload(this.mDelayBetweenUploadsMs);
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                addEventImpl(msg);
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mHandler.removeMessages(3);
                if (uploadEventsImpl()) {
                    maybeQueueImmediateUpload();
                }
                queueUpload(this.mDelayBetweenUploadsMs);
                return;
            default:
                Log.w("PlayEventLogger", "Unknown msg: " + msg.what);
                return;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addEventImpl(android.os.Message r7) {
        /*
        r6 = this;
        r1 = r7.obj;
        r1 = (com.google.android.play.analytics.ClientAnalytics.LogEvent) r1;
        r3 = r6.mRollingFileStream;	 Catch:{ IOException -> 0x0015 }
        r2 = r3.write(r1);	 Catch:{ IOException -> 0x0015 }
        if (r2 == 0) goto L_0x000f;
    L_0x000c:
        r6.maybeQueueImmediateUpload();	 Catch:{ IOException -> 0x0015 }
    L_0x000f:
        r3 = r6.mProtoCache;
        r3.recycle(r1);
    L_0x0014:
        return;
    L_0x0015:
        r0 = move-exception;
        r3 = "PlayEventLogger";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0042 }
        r4.<init>();	 Catch:{ all -> 0x0042 }
        r5 = "Could not write string (";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0042 }
        r4 = r4.append(r1);	 Catch:{ all -> 0x0042 }
        r5 = ") to file: ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0042 }
        r5 = r0.getMessage();	 Catch:{ all -> 0x0042 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0042 }
        r4 = r4.toString();	 Catch:{ all -> 0x0042 }
        android.util.Log.e(r3, r4, r0);	 Catch:{ all -> 0x0042 }
        r3 = r6.mProtoCache;
        r3.recycle(r1);
        goto L_0x0014;
    L_0x0042:
        r3 = move-exception;
        r4 = r6.mProtoCache;
        r4.recycle(r1);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.analytics.EventLogger.addEventImpl(android.os.Message):void");
    }

    public void onNewOutputFile() {
        this.mLastSentExperiments = null;
    }

    public void onWrite(LogEvent event, OutputStream out) throws IOException {
        if (event.exp == this.mLastSentExperiments) {
            event.exp = null;
        } else {
            this.mLastSentExperiments = event.exp;
            if (event.exp == null) {
                event.exp = EMPTY_EXPERIMENTS;
            }
        }
        byte[] serializedMessage = MessageNano.toByteArray(event);
        writeRawVarint32(serializedMessage.length, out);
        out.write(serializedMessage);
    }

    private void writeRawVarint32(int value, OutputStream out) throws IOException {
        while ((value & -128) != 0) {
            out.write((value & 127) | 128);
            value >>>= 7;
        }
        out.write(value);
    }

    private boolean uploadEventsImpl() {
        boolean z = false;
        if (this.mRollingFileStream.hasUnreadFiles()) {
            try {
                byte[][] serializedLogEvents = readSerializedLogEvents();
                if (serializedLogEvents != null) {
                    AndroidClientInfo androidClientInfo = new AndroidClientInfo();
                    androidClientInfo.androidId = this.mAndroidId;
                    androidClientInfo.deviceId = this.mDeviceId;
                    if (this.mLoggingId != null) {
                        androidClientInfo.loggingId = this.mLoggingId;
                    }
                    androidClientInfo.sdkVersion = VERSION.SDK_INT;
                    androidClientInfo.manufacturer = Build.MANUFACTURER;
                    androidClientInfo.model = Build.MODEL;
                    androidClientInfo.product = Build.PRODUCT;
                    androidClientInfo.hardware = Build.HARDWARE;
                    androidClientInfo.device = Build.DEVICE;
                    androidClientInfo.osBuild = Build.ID;
                    androidClientInfo.brand = Build.BRAND;
                    androidClientInfo.board = Build.BOARD;
                    androidClientInfo.fingerprint = Build.FINGERPRINT;
                    String radioVersion = getRadioVersion();
                    if (radioVersion != null) {
                        androidClientInfo.radioVersion = radioVersion;
                    }
                    if (this.mMccmnc != null) {
                        androidClientInfo.mccMnc = this.mMccmnc;
                    }
                    androidClientInfo.locale = Locale.getDefault().getLanguage();
                    if (this.mCountry != null) {
                        androidClientInfo.country = this.mCountry;
                    }
                    if (this.mAppVersion != null) {
                        androidClientInfo.applicationBuild = this.mAppVersion;
                    }
                    ClientInfo clientInfo = new ClientInfo();
                    clientInfo.clientType = 4;
                    clientInfo.androidClientInfo = androidClientInfo;
                    LogRequest logRequest = new LogRequest();
                    logRequest.requestTimeMs = System.currentTimeMillis();
                    logRequest.serializedLogEvents = serializedLogEvents;
                    logRequest.logSource = this.mLogSource;
                    logRequest.clientInfo = clientInfo;
                    z = uploadLog(logRequest);
                    if (z) {
                        this.mRollingFileStream.deleteAllReadFiles();
                    } else {
                        this.mRollingFileStream.markAllFilesAsUnread();
                    }
                }
            } catch (IOException e) {
                Log.e("PlayEventLogger", "Read failed " + e.getClass() + "(" + e.getMessage() + ")");
                this.mRollingFileStream.markAllFilesAsUnread();
            }
        }
        return z;
    }

    private byte[][] readSerializedLogEvents() throws IOException {
        ArrayList<byte[]> repeatedSerializedLogEvents = new ArrayList();
        long accumulatedLength = 0;
        long nextReadLength;
        do {
            byte[] serializedLogEvents = this.mRollingFileStream.read();
            if (serializedLogEvents == null) {
                break;
            }
            if (serializedLogEvents.length > 0) {
                repeatedSerializedLogEvents.add(serializedLogEvents);
                accumulatedLength += (long) serializedLogEvents.length;
            }
            nextReadLength = this.mRollingFileStream.peekNextReadLength();
            if (nextReadLength < 0) {
                break;
            }
        } while (accumulatedLength + nextReadLength <= this.mMaxUploadSize);
        if (repeatedSerializedLogEvents.isEmpty()) {
            return (byte[][]) null;
        }
        byte[][] repeatedSerializedLogEventsArray = new byte[repeatedSerializedLogEvents.size()][];
        repeatedSerializedLogEvents.toArray(repeatedSerializedLogEventsArray);
        return repeatedSerializedLogEventsArray;
    }

    private boolean uploadLog(LogRequest logRequest) {
        boolean z = false;
        String authToken = getAuthToken(this.mAccount);
        try {
            HttpURLConnection connection = makeConnection(authToken);
            if (connection == null) {
                return true;
            }
            try {
                sendRequest(connection, logRequest);
                z = handleResponse(connection, authToken);
                return z;
            } catch (IOException e) {
                Log.e("PlayEventLogger", "Upload failed " + e.getClass() + "(" + e.getMessage() + ")");
                return z;
            } finally {
                connection.disconnect();
            }
        } catch (IOException e2) {
            return z;
        }
    }

    private HttpURLConnection makeConnection(String authToken) throws IOException {
        String rewrittenServerUrl = UrlRules.getRules(this.mResolver).matchRule(this.mServerUrl).apply(this.mServerUrl);
        if (TextUtils.isEmpty(rewrittenServerUrl)) {
            return null;
        }
        HttpURLConnection connection = (HttpURLConnection) new URL(rewrittenServerUrl).openConnection();
        connection.setConnectTimeout(this.mServerTimeoutMs);
        connection.setReadTimeout(this.mServerTimeoutMs);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Encoding", "gzip");
        connection.setRequestProperty("Content-Type", "application/x-gzip");
        connection.setRequestProperty("User-Agent", this.mUserAgent);
        if (authToken != null) {
            connection.setRequestProperty("Authorization", (this.mAuthTokenType.startsWith("oauth2:") ? "Bearer " : "GoogleLogin auth=") + authToken);
        }
        connection.connect();
        return connection;
    }

    private void sendRequest(HttpURLConnection connection, LogRequest logRequest) throws IOException {
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(connection.getOutputStream());
        gzipOutputStream.write(MessageNano.toByteArray(logRequest));
        gzipOutputStream.flush();
        gzipOutputStream.close();
    }

    private boolean handleResponse(HttpURLConnection connection, String authToken) throws IOException {
        int responseCode = connection.getResponseCode();
        String responseMessage = connection.getResponseMessage();
        if (200 <= responseCode && responseCode < 300) {
            handleResponseBodyQuietly(connection);
            return true;
        } else if (300 <= responseCode && responseCode < 400) {
            Log.e("PlayEventLogger", "Too many redirects for HttpUrlConnection. " + responseCode);
            return false;
        } else if (responseCode == 400) {
            Log.e("PlayEventLogger", "Server returned 400... deleting local malformed logs");
            return true;
        } else if (responseCode == 401) {
            Log.w("PlayEventLogger", "Server returned 401... invalidating auth token");
            AccountManager.get(this.mContext).invalidateAuthToken(this.mAccount.type, authToken);
            return false;
        } else if (responseCode == 500) {
            Log.w("PlayEventLogger", "Server returned 500... server crashed");
            return false;
        } else if (responseCode == 501) {
            Log.w("PlayEventLogger", "Server returned 501... service doesn't seem to exist");
            return false;
        } else if (responseCode == 502) {
            Log.w("PlayEventLogger", "Server returned 502... servers are down");
            return false;
        } else if (responseCode == 503) {
            String retryHeader = connection.getHeaderField("Retry-After");
            if (retryHeader != null) {
                boolean parsedRetryValue = false;
                try {
                    long retryAfterInSeconds = Long.valueOf(retryHeader).longValue();
                    Log.w("PlayEventLogger", "Server said to retry after " + retryAfterInSeconds + " seconds");
                    setNextUploadTimeAfter(1000 * retryAfterInSeconds);
                    parsedRetryValue = true;
                } catch (NumberFormatException e) {
                    Log.e("PlayEventLogger", "Unknown retry value: " + retryHeader);
                }
                return !parsedRetryValue;
            }
            Log.e("PlayEventLogger", "Status 503 without retry-after header");
            return true;
        } else if (responseCode == 504) {
            Log.w("PlayEventLogger", "Server returned 504... timeout");
            return false;
        } else {
            Log.e("PlayEventLogger", "Unexpected error received from server: " + responseCode + " " + responseMessage);
            return true;
        }
    }

    private void handleResponseBodyQuietly(HttpURLConnection connection) {
        try {
            InputStream inputStream = connection.getInputStream();
            LogResponse logResponse = LogResponse.parseFrom(createByteArrayFrom(inputStream, 128));
            if (logResponse.nextRequestWaitMillis >= 0) {
                setNextUploadTimeAfter(logResponse.nextRequestWaitMillis);
            }
            inputStream.close();
        } catch (InvalidProtocolBufferNanoException e) {
            Log.e("PlayEventLogger", "Error parsing content: " + e.getMessage());
        } catch (IllegalStateException e2) {
            Log.e("PlayEventLogger", "Error getting the content of the response body: " + e2.getMessage());
        } catch (IOException e3) {
            Log.e("PlayEventLogger", "Error reading the content of the response body: " + e3.getMessage());
        }
    }

    private byte[] createByteArrayFrom(InputStream inputStream, int byteBufferSize) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[byteBufferSize];
        int length;
        do {
            length = inputStream.read(buffer);
            if (length > 0) {
                byteStream.write(buffer, 0, length);
                continue;
            }
        } while (length >= 0);
        return byteStream.toByteArray();
    }

    private void setNextUploadTimeAfter(long waitTimeInMillis) {
        this.mNextAllowedUploadTimeMs = System.currentTimeMillis() + Math.max(this.mMinDelayBetweenUploadsMs, waitTimeInMillis);
    }

    private String getAuthToken(Account account) {
        String str = null;
        if (account == null) {
            Log.w("PlayEventLogger", "No account for auth token provided");
        } else {
            try {
                str = AccountManager.get(this.mContext).blockingGetAuthToken(account, this.mAuthTokenType, true);
            } catch (OperationCanceledException e) {
                Log.e("PlayEventLogger", "Failed to get auth token: " + e.getMessage(), e);
            } catch (AuthenticatorException e2) {
                Log.e("PlayEventLogger", "Failed to get auth token: " + e2.getMessage(), e2);
            } catch (IOException e3) {
                Log.e("PlayEventLogger", "Failed to get auth token: " + e3.getMessage(), e3);
            } catch (IllegalArgumentException e4) {
                Log.e("PlayEventLogger", "Failed to get auth token: " + e4.getMessage(), e4);
            }
        }
        return str;
    }

    private static void maybeRenameLegacyDir(Account account, File baseDir, File newDir) {
        if (account != null && !newDir.exists()) {
            File legacyDir = new File(baseDir, Uri.encode(account.name));
            if (legacyDir.exists() && legacyDir.isDirectory()) {
                legacyDir.renameTo(newDir);
            }
        }
    }

    private static String getRadioVersion() {
        if (VERSION.SDK_INT >= 14) {
            return Build.getRadioVersion();
        }
        return Build.RADIO;
    }
}
