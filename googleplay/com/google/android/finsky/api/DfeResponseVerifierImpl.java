package com.google.android.finsky.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.finsky.api.utils.AndroidKeyczarReader;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.dfe.api.DfeResponseVerifier;
import com.google.android.play.dfe.api.DfeResponseVerifier.DfeResponseVerifierException;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.keyczar.KeyczarFileReader;
import org.keyczar.Verifier;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.KeyczarReader;

public class DfeResponseVerifierImpl implements DfeResponseVerifier {
    private static final String FALLBACK_KEYS_FILES_SUBDIR;
    private static final String PROD_KEYS_ASSETS_SUBDIR;
    private static final SecureRandom SECURE_RANDOM;
    private static KeyczarReader sFallbackReader;
    private static boolean sFallbackReaderInitialized;
    private static KeyczarReader sProdReader;
    private final Context mContext;
    private byte[] mNonce;
    private boolean mNonceInitialized;

    static {
        SecureRandom random;
        PROD_KEYS_ASSETS_SUBDIR = "keys" + File.separator + "dfe-response-auth";
        FALLBACK_KEYS_FILES_SUBDIR = "keys" + File.separator + "dfe-response-auth-dev";
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            FinskyLog.e("Could not initialize SecureRandom, SHA1PRNG not supported. %s", e);
            random = null;
        }
        SECURE_RANDOM = random;
    }

    public DfeResponseVerifierImpl(Context context) {
        this.mNonce = new byte[256];
        this.mContext = context;
    }

    public synchronized String getSignatureRequest() throws DfeResponseVerifierException {
        if (SECURE_RANDOM == null) {
            throw new DfeResponseVerifierException("Uninitialized SecureRandom.");
        }
        if (!this.mNonceInitialized) {
            SECURE_RANDOM.nextBytes(this.mNonce);
            this.mNonceInitialized = true;
        }
        return "nonce=" + Base64.encodeToString(this.mNonce, 11);
    }

    public void verify(byte[] response, String responseSignature) throws DfeResponseVerifierException {
        boolean verified = internalVerify(getProdReader(this.mContext), this.mNonce, response, responseSignature);
        if (!verified) {
            KeyczarReader reader = getFallbackReader(this.mContext);
            if (reader != null) {
                FinskyLog.d("Retry verification using fallback keys.", new Object[0]);
                verified = internalVerify(reader, this.mNonce, response, responseSignature);
            }
        }
        if (!verified || FinskyLog.DEBUG) {
            FinskyLog.d("Response signature verified: %b", Boolean.valueOf(verified));
        }
        if (!verified) {
            throw new DfeResponseVerifierException("Response signature mismatch.");
        }
    }

    private boolean internalVerify(KeyczarReader reader, byte[] nonce, byte[] response, String responseSignature) throws DfeResponseVerifierException {
        boolean z = false;
        try {
            Verifier verifier = new Verifier(reader);
            byte[] allBytes = new byte[(nonce.length + response.length)];
            System.arraycopy(nonce, 0, allBytes, 0, nonce.length);
            System.arraycopy(response, 0, allBytes, nonce.length, response.length);
            z = verifier.verify(allBytes, extractResponseSignature(responseSignature));
        } catch (KeyczarException e) {
            FinskyLog.d("Keyczar exception during signature verification: %s", e);
        }
        return z;
    }

    private static byte[] extractResponseSignature(String responseSignature) throws DfeResponseVerifierException {
        if (TextUtils.isEmpty(responseSignature)) {
            FinskyLog.e("No signing response found.", new Object[0]);
            throw new DfeResponseVerifierException("No signing response found.");
        }
        for (String part : responseSignature.split(";")) {
            String part2 = part2.trim();
            if (part2.startsWith("signature=")) {
                return Base64.decode(part2.substring(10), 11);
            }
        }
        throw new DfeResponseVerifierException("Signature not found in response: " + responseSignature);
    }

    private static synchronized KeyczarReader getProdReader(Context context) {
        KeyczarReader keyczarReader;
        synchronized (DfeResponseVerifierImpl.class) {
            if (sProdReader == null) {
                sProdReader = new AndroidKeyczarReader(context.getResources(), PROD_KEYS_ASSETS_SUBDIR);
            }
            keyczarReader = sProdReader;
        }
        return keyczarReader;
    }

    private static synchronized KeyczarReader getFallbackReader(Context context) {
        KeyczarReader keyczarReader;
        synchronized (DfeResponseVerifierImpl.class) {
            if (!sFallbackReaderInitialized) {
                File fallbackDirectory = new File(context.getFilesDir(), FALLBACK_KEYS_FILES_SUBDIR);
                if (fallbackDirectory.exists()) {
                    sFallbackReader = new KeyczarFileReader(fallbackDirectory.getAbsolutePath());
                }
                sFallbackReaderInitialized = true;
            }
            keyczarReader = sFallbackReader;
        }
        return keyczarReader;
    }
}
