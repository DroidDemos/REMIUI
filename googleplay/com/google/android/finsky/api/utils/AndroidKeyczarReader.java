package com.google.android.finsky.api.utils;

import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.KeyczarReader;

public class AndroidKeyczarReader implements KeyczarReader {
    private static final Charset CHARSET_UTF8;
    private final AssetManager mAssetManager;
    private final String mSubDirectory;

    static {
        CHARSET_UTF8 = Charset.forName("UTF-8");
    }

    public AndroidKeyczarReader(Resources resources, String assetSubDirectory) {
        this.mAssetManager = resources.getAssets();
        this.mSubDirectory = assetSubDirectory;
    }

    public String getKey(int keyVersion) throws KeyczarException {
        return getFileContentAsString(String.valueOf(keyVersion));
    }

    public String getMetadata() throws KeyczarException {
        return getFileContentAsString("meta");
    }

    private String getFileContentAsString(String filename) throws KeyczarException {
        try {
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[1024];
            InputStreamReader isr = new InputStreamReader(this.mAssetManager.open(getFullFilename(filename)), CHARSET_UTF8);
            while (true) {
                int numRead = isr.read(buf);
                if (numRead <= 0) {
                    return sb.toString();
                }
                sb.append(buf, 0, numRead);
            }
        } catch (IOException e) {
            throw new KeyczarException("Couldn't read Keyczar 'meta' file from assets/", e);
        }
    }

    private String getFullFilename(String filename) {
        return this.mSubDirectory == null ? filename : this.mSubDirectory + File.separator + filename;
    }
}
