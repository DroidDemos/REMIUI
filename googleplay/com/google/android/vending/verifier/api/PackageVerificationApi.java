package com.google.android.vending.verifier.api;

import android.net.Uri;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.FinskyApp;
import com.google.android.vending.verifier.PackageVerificationData;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.ApkInfo;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.CertificateChain;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.CertificateChain.Element;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.Digests;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.Resource;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.SignatureInfo;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadStatsRequest;
import com.google.android.vending.verifier.protos.CsdClient.ClientMultiDownloadRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.Collection;

public class PackageVerificationApi {

    public static class FileInfo {
        public final byte[] digest;
        public final String name;
        public final int verificationErrors;

        public FileInfo(String name, byte[] digest, int verificationErrors) {
            this.name = name;
            this.digest = digest;
            this.verificationErrors = verificationErrors;
        }
    }

    public static Request<?> verifyApp(byte[] sha256, long length, String packageName, Integer versionCode, byte[][] signatures, FileInfo[] apkFiles, Uri originatingUri, Uri referrerUri, InetAddress originatingIp, InetAddress referrerIp, String[] originatingPackageNames, byte[][] originatingSignatures, String locale, long androidId, Listener<PackageVerificationResult> responseListener, ErrorListener errorListener) {
        return FinskyApp.get().getRequestQueue().add(new PackageVerificationRequest("https://safebrowsing.google.com/safebrowsing/clientreport/download", responseListener, errorListener, buildRequestForApp(sha256, length, packageName, versionCode, signatures, (byte[][]) null, apkFiles, originatingUri, referrerUri, originatingIp, referrerIp, originatingPackageNames, originatingSignatures, locale, Long.valueOf(androidId), null, false, false, false, false)));
    }

    public static Request<?> verifyInstalledApps(Collection<PackageVerificationData> applicationsData, String locale, Long androidId, boolean userConsented, Listener<PackageVerificationResult[]> responseListener, ErrorListener errorListener) {
        ClientMultiDownloadRequest multiRequestProto = new ClientMultiDownloadRequest();
        multiRequestProto.requests = new ClientDownloadRequest[applicationsData.size()];
        multiRequestProto.userConsented = userConsented;
        multiRequestProto.hasUserConsented = true;
        int index = 0;
        for (PackageVerificationData applicationData : applicationsData) {
            String str = locale;
            Long l = androidId;
            multiRequestProto.requests[index] = buildRequestForApp(applicationData.mSha256Digest, applicationData.mApkLength, applicationData.mPackageName, null, (byte[][]) null, applicationData.mCertFingerprints, null, null, null, null, null, null, (byte[][]) null, str, l, Integer.valueOf(index), applicationData.mInstalledByPlay, applicationData.mForwardLocked, applicationData.mInStoppedState, applicationData.mSuppressUserWarning);
            locale = null;
            androidId = null;
            index++;
        }
        return FinskyApp.get().getRequestQueue().add(new PackageVerificationMultiRequest("https://safebrowsing.google.com/safebrowsing/clientreport/download-multi", responseListener, errorListener, multiRequestProto));
    }

    private static ClientDownloadRequest buildRequestForApp(byte[] sha256, long length, String packageName, Integer versionCode, byte[][] signatures, byte[][] certFingerprints, FileInfo[] apkFiles, Uri originatingUri, Uri referrerUri, InetAddress originatingIp, InetAddress referrerIp, String[] originatingPackageNames, byte[][] originatingSignatures, String locale, Long androidId, Integer requestIndex, boolean installedByPlay, boolean forwardLocked, boolean inStoppedState, boolean dontWarnAgain) {
        ClientDownloadRequest requestProto = new ClientDownloadRequest();
        if (!(packageName == null && versionCode == null)) {
            ApkInfo apkInfo = new ApkInfo();
            if (packageName != null) {
                apkInfo.packageName = packageName;
                apkInfo.hasPackageName = true;
            }
            if (versionCode != null) {
                apkInfo.versionCode = versionCode.intValue();
                apkInfo.hasVersionCode = true;
            }
            if (apkFiles != null) {
                apkInfo.files = new com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.FileInfo[apkFiles.length];
                for (int i = 0; i < apkFiles.length; i++) {
                    apkInfo.files[i] = createFileInfo(apkFiles[i]);
                }
            }
            if (installedByPlay) {
                apkInfo.installedByPlay = true;
                apkInfo.hasInstalledByPlay = true;
            }
            if (forwardLocked) {
                apkInfo.forwardLocked = true;
                apkInfo.hasForwardLocked = true;
            }
            if (inStoppedState) {
                apkInfo.inStoppedState = true;
                apkInfo.hasInStoppedState = true;
            }
            if (dontWarnAgain) {
                apkInfo.dontWarnAgain = true;
                apkInfo.hasDontWarnAgain = true;
            }
            requestProto.apkInfo = apkInfo;
        }
        if (signatures != null && signatures.length > 0) {
            requestProto.signature = createSignatureInfo(signatures);
        } else if (certFingerprints != null && certFingerprints.length > 0) {
            requestProto.signature = createFingerprintSignatureInfo(certFingerprints);
        }
        if (originatingUri != null) {
            requestProto.url = originatingUri.toString();
            requestProto.hasUrl = true;
            if (referrerUri != null) {
                requestProto.resources = new Resource[2];
            } else {
                requestProto.resources = new Resource[1];
            }
            requestProto.resources[0] = createResource(originatingUri, originatingIp, referrerUri, 0);
            if (referrerUri != null) {
                requestProto.resources[1] = createResource(referrerUri, referrerIp, null, 2);
            }
        } else {
            requestProto.url = "";
            requestProto.hasUrl = true;
        }
        if (originatingPackageNames != null) {
            requestProto.originatingPackages = originatingPackageNames;
        }
        if (originatingSignatures != null && originatingSignatures.length > 0) {
            requestProto.originatingSignature = createSignatureInfo(originatingSignatures);
        }
        requestProto.length = length;
        requestProto.hasLength = true;
        requestProto.digests = buildSha256Digest(sha256);
        requestProto.downloadType = 2;
        requestProto.hasDownloadType = true;
        if (locale != null) {
            requestProto.locale = locale;
            requestProto.hasLocale = true;
        }
        if (androidId != null) {
            requestProto.androidId = androidId.longValue();
            requestProto.hasAndroidId = true;
        }
        if (requestIndex != null) {
            try {
                requestProto.requestId = Integer.toHexString(requestIndex.intValue()).getBytes("UTF-8");
                requestProto.hasRequestId = true;
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            }
        }
        return requestProto;
    }

    public static Request<?> reportUserDecision(int userDecision, boolean dontWarn, byte[] requestToken, ErrorListener errorListener) {
        ClientDownloadStatsRequest requestProto = new ClientDownloadStatsRequest();
        if (requestToken != null) {
            requestProto.token = requestToken;
            requestProto.hasToken = true;
        }
        requestProto.userDecision = userDecision;
        requestProto.hasUserDecision = true;
        if (dontWarn) {
            requestProto.dontWarnAgain = true;
            requestProto.hasDontWarnAgain = true;
        }
        return FinskyApp.get().getRequestQueue().add(new PackageVerificationStatsRequest("https://safebrowsing.google.com/safebrowsing/clientreport/download-stat", errorListener, requestProto));
    }

    private static Resource createResource(Uri uri, InetAddress ip, Uri referrerUri, int type) {
        Resource resource = new Resource();
        resource.url = uri.toString();
        resource.hasUrl = true;
        resource.type = type;
        resource.hasType = true;
        if (referrerUri != null) {
            resource.referrer = referrerUri.toString();
            resource.hasReferrer = true;
        }
        if (ip != null) {
            try {
                resource.remoteIp = ip.getHostAddress().getBytes("UTF-8");
                resource.hasRemoteIp = true;
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            }
        }
        return resource;
    }

    private static Digests buildSha256Digest(byte[] sha256hash) {
        Digests digests = new Digests();
        digests.sha256 = sha256hash;
        digests.hasSha256 = true;
        return digests;
    }

    private static SignatureInfo createSignatureInfo(byte[][] signatures) {
        SignatureInfo sigInfo = new SignatureInfo();
        sigInfo.certificateChain = new CertificateChain[signatures.length];
        for (int i = 0; i < signatures.length; i++) {
            CertificateChain certChain = new CertificateChain();
            Element certElement = new Element();
            certElement.certificate = signatures[i];
            certElement.hasCertificate = true;
            certChain.element = new Element[]{certElement};
            sigInfo.certificateChain[i] = certChain;
        }
        return sigInfo;
    }

    private static SignatureInfo createFingerprintSignatureInfo(byte[][] certFingerprints) {
        SignatureInfo sigInfo = new SignatureInfo();
        sigInfo.certificateChain = new CertificateChain[certFingerprints.length];
        for (int i = 0; i < certFingerprints.length; i++) {
            CertificateChain certChain = new CertificateChain();
            Element certElement = new Element();
            certElement.fingerprint = certFingerprints[i];
            certElement.hasFingerprint = true;
            certChain.element = new Element[]{certElement};
            sigInfo.certificateChain[i] = certChain;
        }
        return sigInfo;
    }

    private static com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.FileInfo createFileInfo(FileInfo fileInfo) {
        com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.FileInfo fileInfoProto = new com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.FileInfo();
        fileInfoProto.name = fileInfo.name;
        fileInfoProto.hasName = true;
        fileInfoProto.digests = buildSha256Digest(fileInfo.digest);
        fileInfoProto.verificationErrors = fileInfo.verificationErrors;
        fileInfoProto.hasVerificationErrors = true;
        return fileInfoProto;
    }
}
