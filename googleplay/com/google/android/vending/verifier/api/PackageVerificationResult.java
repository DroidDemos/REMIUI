package com.google.android.vending.verifier.api;

import android.net.Uri;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadResponse;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadResponse.MoreInfo;

public class PackageVerificationResult {
    public final String description;
    public final Uri moreInfoUri;
    public final byte[] token;
    public final boolean uploadApk;
    public final int verdict;

    public PackageVerificationResult(int verdict, String description, Uri moreInfoUri, byte[] token, boolean uploadApk) {
        this.verdict = verdict;
        this.description = description;
        this.moreInfoUri = moreInfoUri;
        this.token = token;
        this.uploadApk = uploadApk;
    }

    public static PackageVerificationResult fromResponse(ClientDownloadResponse response) {
        String description = null;
        Uri uri = null;
        MoreInfo moreInfo = response.moreInfo;
        if (moreInfo != null) {
            description = moreInfo.description;
            if (moreInfo.url != null) {
                uri = Uri.parse(moreInfo.url);
            }
        }
        return new PackageVerificationResult(response.verdict, description, uri, response.token, response.uploadApk);
    }
}
