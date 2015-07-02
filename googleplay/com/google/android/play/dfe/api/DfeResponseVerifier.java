package com.google.android.play.dfe.api;

public interface DfeResponseVerifier {

    public static class DfeResponseVerifierException extends Exception {
        public DfeResponseVerifierException(String message) {
            super(message);
        }
    }

    String getSignatureRequest() throws DfeResponseVerifierException;

    void verify(byte[] bArr, String str) throws DfeResponseVerifierException;
}
