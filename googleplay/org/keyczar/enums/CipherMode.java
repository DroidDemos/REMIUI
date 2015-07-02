package org.keyczar.enums;

import com.google.gson.annotations.Expose;

public enum CipherMode {
    CBC(0, "AES/CBC/PKCS5Padding", true),
    CTR(1, "AES/CTR/NoPadding", true),
    ECB(2, "AES/ECB/NoPadding", false),
    DET_CBC(3, "AES/CBC/PKCS5Padding", false);
    
    private String jceMode;
    @Expose
    private int value;

    private CipherMode(int v, String s, boolean useIv) {
        this.value = v;
        this.jceMode = s;
    }

    public String getMode() {
        return this.jceMode;
    }

    public int getOutputSize(int blockSize, int inputLength) {
        if (this == CBC) {
            return blockSize * ((inputLength / blockSize) + 2);
        }
        if (this == ECB) {
            return blockSize;
        }
        if (this == CTR) {
            return inputLength + (blockSize / 2);
        }
        if (this == DET_CBC) {
            return blockSize * ((inputLength / blockSize) + 1);
        }
        return 0;
    }
}
